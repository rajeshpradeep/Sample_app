package com.example.structure.viewmodel;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ControlResponseModel;
import com.example.structure.data.models.EvMileageChangeVehicleResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.repository.ControlRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 19-12-2019
 */
public class ControlViewModel extends ViewModel {
    private CustomLiveData<ControlResponseModel> controlResponseModelCustomLiveData;
    private CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData;
    private CustomLiveData<EvMileageChangeVehicleResponseModel> evMileageChangeVehicleCustomLiveData;
    private CustomLiveData<CommonResponseModel> applyEvMileageCustomLiveData;
    private ControlRepository controlRepository;

    @Inject
    public ControlViewModel(ControlRepository controlRepository) {
        this.controlRepository = controlRepository;
    }

    /**
     * Calling Control Viewmodel
     */
    public CustomLiveData<ControlResponseModel> getControlViewmodel(String token) {
        controlResponseModelCustomLiveData = controlRepository.getControlResponse(token);
        return controlResponseModelCustomLiveData;
    }

    /**
     * Calling evMileageChangeVehicle Viewmodel
     */
    public CustomLiveData<EvMileageChangeVehicleResponseModel> getEVMileageChangeVehicleViewmodel(String token,
                                                                                                  String deviceId,
                                                                                                  String activeSession, String vehicleId) {
        evMileageChangeVehicleCustomLiveData = controlRepository.getEVMileageChangeVehicleResponse(token, deviceId, activeSession, vehicleId);
        return evMileageChangeVehicleCustomLiveData;
    }

    /**
     * Calling ApplyEvMileage Viewmodel
     */
    public CustomLiveData<CommonResponseModel> getApplyPublishChargeModeViewmodel(String token, ParamModel paramModel) {
        applyEvMileageCustomLiveData = controlRepository.getApplyPublishChargeModeResponse(token, paramModel);
        return applyEvMileageCustomLiveData;
    }

    /**
     * Calling ApplyEvMileage Viewmodel
     */
    public CustomLiveData<CommonResponseModel> getApplyEvMileageViewmodel(String token, ParamModel paramModel) {
        applyEvMileageCustomLiveData = controlRepository.getApplyEvMileageResponse(token, paramModel);
        return applyEvMileageCustomLiveData;
    }

    @Override
    public void onCleared() {
        super.onCleared();
    }

    /**
     * Calling Apply Timer Viewmodel
     */
    public CustomLiveData<CommonResponseModel> getApplyTimerViewmodel(String token, ParamModel paramModel) {
        applyEvMileageCustomLiveData = controlRepository.getapplyTimerControlResponse(token, paramModel);
        return applyEvMileageCustomLiveData;
    }
}
