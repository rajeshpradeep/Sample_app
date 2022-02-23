package com.example.structure.retrofit;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ControlResponseModel;
import com.example.structure.data.models.EvMileageChangeVehicleResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Rajesh Pradeep G on 19-12-2019
 */
public interface ControlWebService {

    /**
     * Control API
     */
    @GET(Constant.CONTROL_ENDPOINT)
    Call<ControlResponseModel> controlAPI(@Header("Authorization") String authHeader);

    /**
     * EV_Mileage_Change_Vehicle API
     */
    @GET(Constant.EV_MILEAGE_CHANGE_VEHICLE_ENDPOINT + "/{" + Constant.DEVICE_ID_PARAM + "}" +
            "/{" + Constant.ACTIVE_SESSION_ID_PARAM + "}" + "/{" + Constant.VEHICLE_ID_PARAM + "}")
    Call<EvMileageChangeVehicleResponseModel> evMileageChangeVehiclelAPI(@Header("Authorization") String authHeader,
                                                                         @Path("deviceId") String deviceId,
                                                                         @Path("activeSession") String activeSession,
                                                                         @Path("vehicleId") String vehicleId);

    /**
     * Apply PublishChargeMode API
     */
    @POST(Constant.PUBLISH_CHARGE_MODE_ENDPOINT)
    Call<CommonResponseModel> applyPublishChargeModeAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);

    /**
     * ApplyEvMileage API
     */
    @PATCH(Constant.UPDATE_EV_MILEAGE_ENDPOINT)
    Call<CommonResponseModel> applyEvMileageAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);

    /**
     * ApplyTimerControl API
     */
    @PATCH(Constant.UPDATE_TIMER_CONTROL_ENDPOINT)
    Call<CommonResponseModel> applyTimerControlAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);
}
