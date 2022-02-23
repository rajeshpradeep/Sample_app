package com.example.structure.support;

import com.example.structure.utils.ApiResponse;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 21-11-2019
 */
public class CustomLiveData<T> extends MutableLiveData<ApiResponse<T>> {


    public void postLoading() {
        postValue(new ApiResponse<T>().loading());
    }


    public void postFailure(Response<T> response) {
        postValue(new ApiResponse<T>().failure(response));
    }

    public void postError(Throwable throwable) {
        postValue(new ApiResponse<T>().error(throwable));
    }

    public void postSuccess(T data) {
        postValue(new ApiResponse<T>().success(data));
    }
    public void setSuccess(T data) {
        setValue(new ApiResponse<T>().success(data));
    }


    public void setFailure(Response<T> response) {
        setValue(new ApiResponse<T>().failure(response));
    }

    public void setError(Throwable throwable) {
        setValue(new ApiResponse<T>().error(throwable));
    }
    public void postListSuccess(List<T> data) {
        postValue(new ApiResponse<T>().success(data));
    }

    public void postComplete() {
        postValue(new ApiResponse<T>().complete());
    }
}