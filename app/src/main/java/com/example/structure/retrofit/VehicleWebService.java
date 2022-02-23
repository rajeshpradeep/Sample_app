package com.example.structure.retrofit;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.VehicleListResponseModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Rajesh Pradeep G on 22-01-2020
 */
public interface VehicleWebService {

    /**
     * Vehicle List API
     */
    @GET(Constant.USER_VEHICLES_ENDPOINT)
    Call<VehicleListResponseModel> vehicleListAPI(@Header("Authorization") String authHeader);

    /**
     * Vehicle List API
     */
    @POST(Constant.ADD_VEHICLE_ENDPOINT)
    Call<CommonResponseModel> addVehicleAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);

    /**
     * Update Vehicle detail API
     */
    @PATCH(Constant.UPDATE_VEHICLE_ENDPOINT)
    Call<CommonResponseModel> updateVehicleAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);

    /**
     * Update Vehicle detail API
     */
    @DELETE(Constant.DELETE_VEHICLE_ENDPOINT + "/{" + Constant.VEHICLE_ID_PARAM + "}")
    Call<CommonResponseModel> deleteVehicleAPI(@Header("Authorization") String authHeader, @Path("vehicleId") String vehicle_id);
}
