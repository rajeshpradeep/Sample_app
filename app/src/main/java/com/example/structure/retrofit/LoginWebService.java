package com.example.structure.retrofit;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.GetQuestionResponseModel;
import com.example.structure.data.models.LoginResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Rajesh Pradeep G on 29-10-2019
 */
public interface LoginWebService {

    /**
     * login API
     * @param paramModel  contains userName/email and password*/
    @POST(Constant.LOGIN_ENDPOINT)
    Call<LoginResponseModel> loginAPI(@Body ParamModel paramModel);

    /**
     * get question API
     */
    @GET(Constant.GET_QUESTION_ENDPOINTS)
    Call<GetQuestionResponseModel> getQuestionAPI();

    /**
     * Forgot Password API
     * @param paramModel  contains email and secret question(question id) and secrect answer*/
    @POST(Constant.FORGOT_PASSWORD_ENDPOINT)
    Call<CommonResponseModel> forgotPasswordAPI(@Body ParamModel paramModel);

    /**
     * get user type API
     **/
    @GET(Constant.GET_USERTYPE_ENDPOINT)
    Call<CommonResponseModel> getUserTypeAPI(@Header("Authorization") String authHeader);

    /**
     * get user type API
     * @param paramModel  contains user id
     **/
    @POST(Constant.LOGOUT_ENDPOINT)
    Call<CommonResponseModel> logoutAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);
}
