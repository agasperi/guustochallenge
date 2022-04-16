package com.aega.guusto.cqrs.application;


/**
 * Represents an command that accepts a single input argument and returns no
 * result.
 *
 * @param <T> the type of the input to the operation
 *
 */
@FunctionalInterface
public interface CommandConsume<T> {

    /**
     * Performs this command on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);

}