package com.intercom.Reader;

import com.intercom.exception.NearestCustomerApplicationException;

import java.lang.reflect.Type;

/**
 * Created by hadoop on 13/3/18.
 */
public interface IReader<T> {
    T read(Type type) throws NearestCustomerApplicationException;
    void registerFilePath(String path) throws NearestCustomerApplicationException;
    boolean hasNextEntity() throws NearestCustomerApplicationException;
}
