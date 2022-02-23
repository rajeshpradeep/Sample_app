package com.example.structure.retrofit;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.CountryStateResponseModel;
import com.example.structure.data.models.CreateAccountParamModel;
import com.example.structure.data.models.GetQuestionResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Rajesh Pradeep G on 03-12-2019
 */
public interface CreateAccountWebservice {

    /*
     * Check email exist
     */
    @POST(Constant.CHECK_EMAIL_EXISTS_ENDPOINTS)
    Call<CommonResponseModel> isEmailExist(@Body ParamModel paramModel);

    /*
     * Check username exist
     */
    @POST(Constant.CHECK_USER_NAME_EXISTS_ENDPOINTS)
    Call<CommonResponseModel> isUsernameExist(@Body ParamModel paramModel);

    /*
     * Get Country list
     */
    @GET(Constant.GET_COUNTRY_ENDPOINTS)
    Call<CountryStateResponseModel> getCountryList();

    /*
     * Get State list
     */
    @GET(Constant.GET_STATES_ENDPOINTS)
    Call<CountryStateResponseModel> getStateList();

    /**
     * get question API
     */
    @GET(Constant.GET_QUESTION_ENDPOINTS)
    Call<GetQuestionResponseModel> getQuestionAPI();

    /**
     * call Sign up API
     */
    @POST(Constant.SIGNUP_ENDPOINTS)
    Call<CommonResponseModel> signUpAPI(@Body CreateAccountParamModel createAccountParamModel);

    /**
     * call Leaderboard Alias Check API
     */
    @POST(Constant.LEADER_BOARD_ALIAS_CHECK_ENDPOINTS)
    Call<CommonResponseModel> isAliasExist(@Body ParamModel paramModel);
}
