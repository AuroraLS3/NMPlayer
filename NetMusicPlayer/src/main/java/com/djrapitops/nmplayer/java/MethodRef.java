package com.djrapitops.nmplayer.java;

/**
 * Functional interface for storing method reference.
 *
 * @author Rsl1122
 */
public interface MethodRef<T> {

    void call(T with);

}
