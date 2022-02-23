package com.example.structure.retrofit;

import com.example.structure.data.models.MonitorEVSEResponseModel;
import com.example.structure.data.models.MonitorResponseModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Rajesh Pradeep G on 07-02-2020
 */
public interface MonitorWebService {

    /**
     * Monitor SCA API
     */
    @GET(Constant.MONITOR_ENDPOINT)
    Call<MonitorResponseModel> monitorAPI(@Header("Authorization") String authHeader);

    /**
     * Monitor EVSE API
     */
    @GET(Constant.MONITOR_ENDPOINT)
    Call<MonitorEVSEResponseModel> monitorEVSEAPI(@Header("Authorization") String authHeader);

    /**
     * Monitor EVSE API
     */
    @GET(Constant.MONITOR_ENDPOINT + "/{" + Constant.PORT_ID_PARAM + "}")
    Call<MonitorEVSEResponseModel> monitorEVSEPortAPI(@Header("Authorization") String authHeader, @Path("portId") String portId);

}
