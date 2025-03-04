package org.example.param;

public class Response<T> {
    private int code;
    private String message;
    private T data;

    public Response(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(0, data);
    }

    public static <T> Response<T> fail(String message) {
        return new Response<>(-1, message);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
