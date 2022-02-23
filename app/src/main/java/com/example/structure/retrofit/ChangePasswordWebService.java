package com.example.structure.retrofit;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PATCH;

/**
 * Created by Rajesh Pradeep G on 20-02-2020
 */
public interface ChangePasswordWebService {

    /**
     * Change Password API
     */
    @PATCH(Constant.CHANGE_PASSWORD_ENDPOINT)
    Call<CommonResponseModel> changePasswordAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);
}
