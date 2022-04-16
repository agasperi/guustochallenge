package com.aega.guusto.cqrs.application;


/**
 * Represents a command that accepts one argument and produces a result.
 *
 * @param <T> the type of the input to the command
 * @param <R> the type of the result of the command
 *
 */
@FunctionalInterface
public interface CommandOperate<T, R> {

    /**
     * Applies this command to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);

}
