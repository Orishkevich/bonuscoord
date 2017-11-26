package com.orishkevich.web.forms;

/**
 * //TODO add comments
 *
 * @author orishkevich
 * @since 26.11.2017
 */
public class Error extends Result {
    private final String error;

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
