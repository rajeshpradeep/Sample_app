package com.example.structure.retrofit;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.DashboardEvseResponseModel;
import com.example.structure.data.models.DashboardResponseModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Rajesh Pradeep G on 11-11-2019
 */
public interface DashboardWebService {

    /**
     * Dashboard API
     */
    @GET(Constant.DASHBOARD_ENDPOINT)
    Call<DashboardResponseModel> dashboardAPI(@Header("Authorization") String authHeader);

    /**
     * Dashboard EVSE API
     */
    @GET(Constant.DASHBOARD_ENDPOINT)
    Call<DashboardEvseResponseModel> dashboardEvseAPI(@Header("Authorization") String authHeader);

    /**
     * Dashboard EVSE API
     */
    @GET(Constant.DASHBOARD_ENDPOINT + "/{" + Constant.CONNECTOR_ID_PARAM + "}")
    Call<DashboardEvseResponseModel> dashboardEvsePortAPI(@Header("Authorization") String authHeader,@Path("connectorId") String connectorId);

    /**
     * Resend Email API
     */
    @POST(Constant.RESEND_EMAIL_ENDPOINT)
    Call<CommonResponseModel> resendEmailAPI(@Header("Authorization") String authHeader);

}
