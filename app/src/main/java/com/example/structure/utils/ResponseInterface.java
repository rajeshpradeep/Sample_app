package com.example.structure.utils;

import java.util.List;

import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 29-10-2019
 */
public interface ResponseInterface <T> {
    /**
     * return the API response as Object
     * */
    void onSuccess(T data);

    /**
     * return the API response as list
     * */
    void onSuccess(List<T> data);

    /**
     * return the API error
     * */
    void onError(Throwable throwable);

    /**
     * return the API failiure
     * */
    void onFailure(Response<T> response);
}