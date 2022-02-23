package com.example.structure.retrofit;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.CountryStateResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.PersonalInfoResponseModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

/**
 * Created by Rajesh Pradeep G on 04-02-2020
 */
public interface PeronalInfoWebService {

    /*
     * Check email exist
     */
    @POST(Constant.CHECK_EMAIL_EXISTS_ENDPOINTS)
    Call<CommonResponseModel> isEmailExist(@Body ParamModel paramModel);

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
     * call Leaderboard Alias Check API
     */
    @POST(Constant.LEADER_BOARD_ALIAS_CHECK_ENDPOINTS)
    Call<CommonResponseModel> isAliasExist(@Body ParamModel paramModel);

    /**
     * Personal Info API
     */
    @GET(Constant.PERSONAL_INFO_ENDPOINTS)
    Call<PersonalInfoResponseModel> personalInfoAPI(@Header("Authorization") String authHeader);

    /**
     * Update Personal Info API
     */
    @PATCH(Constant.UPDATE_PERSONAL_INFO_ENDPOINTS)
    Call<CommonResponseModel> updatePersonalInfoAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);
}
