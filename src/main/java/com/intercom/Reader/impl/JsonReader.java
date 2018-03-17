package com.intercom.Reader.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.intercom.DTO.LocationEntity;
import com.intercom.DTO.Person;
import com.intercom.Reader.IReader;
import com.intercom.exception.ErrorCode;
import com.intercom.exception.NearestCustomerApplicationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;

/**
 * Created by hadoop on 13/3/18.
 */
public class JsonReader<T extends LocationEntity> implements IReader<T> {
    BufferedReader bufferedReader;
    Gson gson;
    String nextLine = null;
    public T read(Type type) throws NearestCustomerApplicationException{
        T locationEntity = null;
        if(nextLine == null){
            throw new NearestCustomerApplicationException("No Element Found",ErrorCode.NO_ELLEMENT_REMAINING_IN_FILE);
        }
        try {
            locationEntity =(T) gson.fromJson(nextLine,type);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new NearestCustomerApplicationException("Parsing Element Error" +e,ErrorCode.NO_ELLEMENT_REMAINING_IN_FILE);
        }

        return locationEntity;
    }

    public void registerFilePath(String path) throws NearestCustomerApplicationException {
        try {
            GsonBuilder builder = new GsonBuilder();
            gson = builder.create();
            bufferedReader = new BufferedReader(new FileReader(path));
        }
        catch (Exception e){
            throw  new NearestCustomerApplicationException("Could not load file into memory ", ErrorCode.UNABLE_TO_READ_FILE);
        }
    }

    public boolean hasNextEntity() throws NearestCustomerApplicationException {
        try {
            nextLine = bufferedReader.readLine();
            return (nextLine != null);
        }
        catch (Exception e){
            throw new NearestCustomerApplicationException("Unable to read",ErrorCode.UNABLE_TO_READ_FILE);
        }
    }
}
