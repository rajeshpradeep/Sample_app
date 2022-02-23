package com.example.structure.utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 09-05-2019
 * this class is not used because it will only used in Livedata
 */
public class ApiResponse<T> {

    private ApiStatus status;

    @Nullable
    private T data;

    @Nullable
    private List<T> listData;

    @Nullable
    private Throwable error;
    @Nullable
    private Response<T> response;

    public ApiResponse() {
        this.status = ApiStatus.CREATED;
        this.data = null;
        this.listData = null;
        this.error = null;
    }

    public ApiResponse<T> loading() {
        this.status = ApiStatus.LOADING;
        this.data = null;
        this.listData = null;
        this.error = null;
        return this;
    }

    public ApiResponse<T> success(@NonNull T data) {
        this.status = ApiStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    public ApiResponse<T> success(@NonNull List<T> data) {
        this.status = ApiStatus.SUCCESS;
        this.listData = data;
        this.error = null;
        return this;
    }

    public ApiResponse<T> error(@NonNull Throwable error) {
        this.status = ApiStatus.ERROR;
        this.data = null;
        this.listData = null;
        this.error = error;
        return this;
    }

    public ApiResponse<T> failure(@NonNull Response<T> response) {
        this.status = ApiStatus.FAILURE;
        this.data = null;
        this.listData = null;
        this.response = response;
        return this;
    }

    public ApiResponse<T> complete() {
        this.status = ApiStatus.COMPLETE;
        return this;
    }

    @NonNull
    public ApiStatus getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public List<T> getListData() {
        return listData;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    @Nullable
    public Response<T> getFailureResponse() {
        return response;
    }
}