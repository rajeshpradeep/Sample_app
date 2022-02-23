package com.example.structure.viewmodel;

import com.example.structure.data.models.MakeModelResponseModel;
import com.example.structure.data.models.YearListResponseModel;
import com.example.structure.data.repository.AddElectricVehicleRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 02-12-2019
 */
public class AddElectricVehicleViewModel extends ViewModel {

    private CustomLiveData<YearListResponseModel> addElectricVehicleResponseModelCustomLiveData;
    private CustomLiveData<MakeModelResponseModel> makeModelResponseModelCustomLiveData;
    private AddElectricVehicleRepository addElectricVehicleRepository;

    @Inject
    public AddElectricVehicleViewModel(AddElectricVehicleRepository addElectricVehicleRepository) {
        this.addElectricVehicleRepository = addElectricVehicleRepository;
    }

    /**
     * Calling Year View model
     */
    public CustomLiveData<YearListResponseModel> getYearViewModel() {
        addElectricVehicleResponseModelCustomLiveData = addElectricVehicleRepository.getYearListAPIRepository();
        return addElectricVehicleResponseModelCustomLiveData;
    }

    /**
     * Get AddElectricVehicleResponse
     */
    public CustomLiveData<YearListResponseModel> getAddElectricVehicleResponse() {
        return addElectricVehicleResponseModelCustomLiveData;
    }

    /**
     * Get Make list
     */
    public CustomLiveData<MakeModelResponseModel> getMakeResponse(String year) {
        makeModelResponseModelCustomLiveData = addElectricVehicleRepository.getMakeListAPIRepository(year);
        return makeModelResponseModelCustomLiveData;
    }

    /**
     * Get Model list
     */
    public CustomLiveData<MakeModelResponseModel> getModelResponse(String year, String make) {
        makeModelResponseModelCustomLiveData = addElectricVehicleRepository.getModelListAPIRepository(year, make);
        return makeModelResponseModelCustomLiveData;
    }
}
