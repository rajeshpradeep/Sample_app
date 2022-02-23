package com.example.structure.retrofit;

import com.example.structure.data.models.DetailedSessionResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.SessionInfoResponseModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Rajesh Pradeep G on 27-02-2020
 */
public interface SessionInfoWebService {

    /**
     * Session info API
     */
    @POST(Constant.SESSION_LIST_ENDPOINTS)
    Call<SessionInfoResponseModel> sessionInfoAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);

    /**
     * Session Graph info API
     */
    @GET(Constant.SESSION_DETAILS_ENDPOINTS + "/{" + Constant.TRANSACTION_ID_PARAM + "}" + "/{" + Constant.TRANSACTION_TYPE_PARAM + "}")
    Call<DetailedSessionResponseModel> sessionGraphInfoAPI(@Header("Authorization") String authHeader,
                                                           @Path("transactionId") String transaction_id,
                                                           @Path("transactionType") String transaction_type);
}
