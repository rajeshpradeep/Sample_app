package com.example.structure.viewmodel;

import com.example.structure.data.models.ControlEVSEResponseModel;
import com.example.structure.data.models.EVSEModeStatusResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.repository.ControlEVSERepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 10-03-2020
 */
public class ControlEVSEViewModel extends ViewModel {

    private CustomLiveData<ControlEVSEResponseModel> controlEVSECustomLiveData;
    private CustomLiveData<EVSEModeStatusResponseModel> evseModeStatusCustomLiveData;
    private ControlEVSERepository controlEVSERepository;

    @Inject
    public ControlEVSEViewModel(ControlEVSERepository controlEVSERepository) {
        this.controlEVSERepository = controlEVSERepository;
    }

    /**
     * Calling Control EVSE Viewmodel
     */
    /*public CustomLiveData<ControlEVSEResponseModel> getControlEVSEViewmodel(String token, ParamModel paramModel) {
        controlEVSECustomLiveData = controlEVSERepository.getControlEVSEResponse(token, paramModel);
        return controlEVSECustomLiveData;
    }*/

    /**
     * Calling EVSEModeStatus Viewmodel
     */
    public CustomLiveData<EVSEModeStatusResponseModel> getEVSEModeStatusViewmodel(String token, ParamModel paramModel) {
        evseModeStatusCustomLiveData = controlEVSERepository.getEVSEModeStatusResponse(token, paramModel);
        return evseModeStatusCustomLiveData;
    }

    /**
     * Calling Remote Operation Viewmodel
     */
    public CustomLiveData<EVSEModeStatusResponseModel> getRemoteOperationViewmodel(String token, ParamModel paramModel) {
        evseModeStatusCustomLiveData = controlEVSERepository.getRemoteOperationResponse(token, paramModel);
        return evseModeStatusCustomLiveData;
    }
}
