package com.orishkevich.web.forms;

/**
 * //TODO add comments
 *
 * @author orishkevich
 * @since 26.11.2017
 */
public class Success<T> extends Result {
    private final T value;

    public Success(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
