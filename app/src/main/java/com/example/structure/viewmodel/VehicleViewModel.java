package com.example.structure.viewmodel;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.VehicleListResponseModel;
import com.example.structure.data.repository.VehicleRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 22-01-2020
 */
public class VehicleViewModel extends ViewModel {

    private CustomLiveData<VehicleListResponseModel> vehicleListResponseModelCustomLiveData;
    private CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData;
    private VehicleRepository vehicleRepository;

    @Inject
    public VehicleViewModel(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public CustomLiveData<VehicleListResponseModel> getVehicleListResponse(String beararToken) {
        return vehicleListResponseModelCustomLiveData = vehicleRepository.vehicelListAPIRepository(beararToken);
    }

    public CustomLiveData<CommonResponseModel> getAddVehicleResponse(String beararToken, ParamModel paramModel) {
        return commonResponseModelCustomLiveData = vehicleRepository.addVehicelAPIRepository(beararToken, paramModel);
    }

    public CustomLiveData<CommonResponseModel> geUpdatedVehicleResponse(String beararToken, ParamModel paramModel) {
        return commonResponseModelCustomLiveData = vehicleRepository.updateVehicelAPIRepository(beararToken, paramModel);
    }

    public CustomLiveData<CommonResponseModel> getDeleteVehicleResponse(String beararToken, String vehicleId) {
        return commonResponseModelCustomLiveData = vehicleRepository.deleteVehicelAPIRepository(beararToken, vehicleId);
    }
}
