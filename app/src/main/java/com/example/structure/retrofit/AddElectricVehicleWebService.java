package com.example.structure.retrofit;


import com.example.structure.data.models.MakeModelResponseModel;
import com.example.structure.data.models.YearListResponseModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Rajesh Pradeep G on 02-12-2019
 */
public interface AddElectricVehicleWebService {

    /*
    * Get Year list
    */
    @GET(Constant.GET_YEAR_LIST_ENDPOINT)
    Call<YearListResponseModel> getYearList();

    /*
    * Get Make list
    */
    @GET(Constant.GET_MAKE_LIST_ENDPOINT + "/{" + Constant.YEAR_PARAM + "}")
    Call<MakeModelResponseModel> getMakeList(@Path("year") String year);

    /*
    * Get Model list
    */
    @GET(Constant.GET_MODEL_LIST_ENDPOINT  + "/{" + Constant.YEAR_PARAM + "}" + "/{" + Constant.MAKE_PARAM + "}")
    Call<MakeModelResponseModel> getModelList(@Path("year") String year, @Path("make") String make);
}
