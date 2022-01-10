package dev.udd.shared.application;

public final class ErrorResponse {

    private String message;
    private int code;

    public ErrorResponse(String message, int code) {

        this.message = message;
        this.code = code;
    }

    public String getMessage() {

        return message;
    }

    public int getCode() {

        return code;
    }

}
