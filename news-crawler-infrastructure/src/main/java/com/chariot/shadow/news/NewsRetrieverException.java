package com.chariot.shadow.news;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public class NewsRetrieverException extends Exception {

    private static final long serialVersionUID = 1L;

    public NewsRetrieverException() {
    }

    public NewsRetrieverException(String string) {
        super(string);
    }

    public NewsRetrieverException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public NewsRetrieverException(Throwable throwable) {
        super(throwable);
    }
}
