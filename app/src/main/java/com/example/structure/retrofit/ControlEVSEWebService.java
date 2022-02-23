package com.example.structure.retrofit;

import com.example.structure.data.models.ControlEVSEResponseModel;
import com.example.structure.data.models.EVSEModeStatusResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

/**
 * Created by Rajesh Pradeep G on 10-03-2020
 */
public interface ControlEVSEWebService {

    /**
     * Control EVSE API
     */
    @GET(Constant.CONTROL_ENDPOINT)
    Call<ControlEVSEResponseModel> controlEVSEAPI(@Header("Authorization") String authHeader);

    /**
     * Control EVSE API
     */
    @GET(Constant.CONTROL_ENDPOINT + "/{" + Constant.CHARGEBOX_ID_PARAM + "}" +  "/{" + Constant.PORT_ID_PARAM + "}")
    Call<ControlEVSEResponseModel> controlEVSEPortAPI(@Header("Authorization") String authHeader,
                                                      @Path("chargeboxId") String chargeboxId, @Path("portId") String portId);

    /**
     * setEvseModeStatus API
     */
    @PATCH(Constant.UPDATE_EVSE_MODE_STATUS_ENDPOINT)
    Call<EVSEModeStatusResponseModel> setEvseModeStatusAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);

    /**
     * updateRemoteOperation API
     */
    @PATCH(Constant.UPDATE_REMOTE_OPERATION_ENDPOINT)
    Call<EVSEModeStatusResponseModel> updateRemoteOperationAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);
}
