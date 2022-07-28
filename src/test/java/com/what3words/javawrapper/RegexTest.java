package com.what3words.javawrapper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegexTest {

    @Test
    public void testIsPossible3wa() {
        String text = "index.home raft";
        assertEquals(false, What3WordsV3.isPossible3wa(text));
        text = "index.home.raft";
        assertEquals(true, What3WordsV3.isPossible3wa(text));
    }

    @Test
    public void testDidYouMean3wa() {
        String text = "index.homeraft";
        assertEquals(false, What3WordsV3.didYouMean3wa(text));
        text = "index.home raft";
        assertEquals(true, What3WordsV3.didYouMean3wa(text));
        text = "index,home,raft";
        assertEquals(true, What3WordsV3.didYouMean3wa(text));
        text = "index-home,raft";
        assertEquals(true, What3WordsV3.didYouMean3wa(text));
        text = "index home raft";
        assertEquals(true, What3WordsV3.didYouMean3wa(text));
    }

    @Test
    public void testFindPossible3wa() {
        String words = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse porttitor nunc vitae mauris mattis, et cursus ante posuere. Quisque consequat varius orci, ut auctor ipsum. Integer gravida non eros non posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean consequat interdum lacus, viverra auctor nisi dignissim in. Aenean sed tempor tellus, eget vestibulum dolor. Donec vel mi maximus, commodo diam sit amet, dictum purus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nunc volutpat eu ligula ultricies feugiat. Cras nisi justo, varius vitae augue at, eleifend porttitor velit.\n" +
                "\n" +
                "Aenean id lacus ipsum. Integer ut dolor a enim efficitur aliquam. Aliquam vitae mattis diam, eget tincidunt nibh. Suspendisse mattis leo eu arcu finibus lobortis. Aenean bibendum, turpis id posuere aliquet, orci ante euismod ipsum, nec imperdiet ex felis nec nulla. Nulla facilisi. Nam auctor dapibus nunc, sed maximus quam varius a. Quisque eu lacinia dui. Sed at consectetur magna.";
        assert(What3WordsV3.findPossible3wa(words).size() == 0);
        words = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse porttitor index.home.raft nunc vitae mauris mattis, et cursus ante posuere. Quisque consequat varius orci, ut auctor ipsum. Integer gravida non eros non posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean consequat interdum lacus, viverra auctor nisi dignissim in. Aenean sed tempor ro.do.so tellus, eget vestibulum dolor. Donec vel mi maximus, commodo diam sit amet, dictum purus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nunc volutpat eu ligula ultricies feugiat. Cras nisi justo, varius vitae augue at, eleifend porttitor velit.\n" +
                "\n" +
                "Aenean id lacus ipsum. Integer ut dolor a enim efficitur aliquam. filled.count.soap. Aliquam vitae mattis diam, eget tincidunt nibh. Suspendisse mattis leo eu arcu finibus lobortis. Aenean bibendum, turpis id posuere aliquet, orci ante euismod ipsum, nec imperdiet ex felis nec nulla. Nulla facilisi. Nam auctor dapibus nunc, sed maximus quam varius a. Quisque eu lacinia dui. Sed at consectetur magna.";
        assert(What3WordsV3.findPossible3wa(words).size() == 3);
        assert(What3WordsV3.findPossible3wa(words).contains("index.home.raft"));
        assert(What3WordsV3.findPossible3wa(words).contains("filled.count.soap"));
        assert(What3WordsV3.findPossible3wa(words).contains("ro.do.so"));
    }
}