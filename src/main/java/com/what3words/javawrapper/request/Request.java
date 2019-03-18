package com.what3words.javawrapper.request;

import java.io.IOException;
import java.lang.annotation.Annotation;

import com.google.gson.stream.MalformedJsonException;
import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.response.APIError;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.APIResponse.Error;
import com.what3words.javawrapper.response.ErrorResponse;
import com.what3words.javawrapper.response.Response;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;

public class Request<T extends Response<T>> {
    protected What3WordsV3 api;
    
    protected Request(What3WordsV3 api) {
        this.api = api;
    }

    protected T execute(Call<T> call, Class<T> clazz) {
        APIResponse <T> response;
        try {
            response = new APIResponse<>(call.execute());

            if (!response.isSuccessful()) {
                /** Attempt to parse the HTTP body of the error response */
                Converter<ResponseBody, ErrorResponse> errorConverter =
                        api.getRetrofitInstance().responseBodyConverter(ErrorResponse.class, new Annotation[0]);

                APIError error;
                try {
                    error = errorConverter.convert(response.getResponse().errorBody()).getError();
                } catch (MalformedJsonException e) {
                    error = new APIError();
                    error.setCode("UnknownError");
                    error.setMessage(response.getResponse().raw().message());
                } catch (IOException e) {
                    error = new APIError();
                    error.setCode("NetworkError");
                    error.setMessage(e.getMessage());
                } 

                response.setAPIError(error);
            }
        } catch (IOException e) {
            response = new APIResponse<>(null);
            APIError error = new APIError();
            error.setCode("NetworkError");
            error.setMessage(e.getMessage());
            response.setAPIError(error);
        }

        APIError error = response.getAPIError();
        if (error != null) {
            // look for the error within the available error enums
            Error errorEnum = Error.get(error.getCode());
            
            // Haven't found the error, return UNKNOWN_ERROR
            if (errorEnum == null) {
                errorEnum = Error.UNKNOWN_ERROR;
            }
            
            errorEnum.setMessage(error.getMessage());

            response.setError(errorEnum);
        }
        
        T entity = response.body();
        if  (entity == null) {
            try {
                entity = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        entity.setResponse(response);
        
        return entity;
    }
}
