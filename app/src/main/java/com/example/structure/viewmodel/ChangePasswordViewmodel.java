package com.example.structure.viewmodel;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.repository.ChangePasswordRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 20-02-2020
 */
public class ChangePasswordViewmodel extends ViewModel {

    private CustomLiveData<CommonResponseModel> customLiveData;
    private ChangePasswordRepository changePasswordRepository;

    @Inject
    public ChangePasswordViewmodel(ChangePasswordRepository changePasswordRepository) {
        this.changePasswordRepository = changePasswordRepository;
    }

    /**
     * Calling Change password View model
     */
    public CustomLiveData<CommonResponseModel> getChangePasswordViewModel(String token, ParamModel paramModel) {
        customLiveData = changePasswordRepository.getChangePasswordResponse(token, paramModel);
        return customLiveData;
    }
}
