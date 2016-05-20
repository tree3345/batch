package com.lzjf.core.base;

public class JsonException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public JsonException() {
        super();
    }
    
    public JsonException(String msg) {
        super(msg);
    }
    
    public JsonException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public JsonException(Throwable cause) {
        super(cause);
    }
}
