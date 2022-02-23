package com.example.structure.viewmodel;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.CountryStateResponseModel;
import com.example.structure.data.models.CreateAccountParamModel;
import com.example.structure.data.models.GetQuestionResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.repository.CreateAccountRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 03-12-2019
 */
public class CreateAccountViewModel extends ViewModel {

    private CustomLiveData<CountryStateResponseModel> countryCustomLiveData;
    private CustomLiveData<CountryStateResponseModel> stateCustomLiveData;
    private CustomLiveData<CommonResponseModel> emailUsernameLiveData;
    private CustomLiveData<CommonResponseModel> signupCustomLiveData;
    private CustomLiveData<GetQuestionResponseModel> questionResponseModelCustomLiveData;
    private CreateAccountRepository createAccountRepository;

    @Inject
    public CreateAccountViewModel(CreateAccountRepository createAccountRepository) {
        this.createAccountRepository = createAccountRepository;
    }

    /**
     * Calling Email available View model
     */
    public CustomLiveData<CommonResponseModel> getEmailExistViewModel(ParamModel paramModel) {
        emailUsernameLiveData = createAccountRepository.getEmailExist(paramModel);
        return emailUsernameLiveData;
    }

    /**
     * Calling Username available View model
     */
    public CustomLiveData<CommonResponseModel> getUsernameExistViewModel(ParamModel paramModel) {
        emailUsernameLiveData = createAccountRepository.getUserNameExist(paramModel);
        return emailUsernameLiveData;
    }

    /**
     * Calling Country View model
     */
    public CustomLiveData<CountryStateResponseModel> getCountryViewModel() {
        countryCustomLiveData = createAccountRepository.getCountryListAPIRepository();
        return countryCustomLiveData;
    }

    /**
     * Calling State View model
     */
    public CustomLiveData<CountryStateResponseModel> getStateViewModel() {
        stateCustomLiveData = createAccountRepository.getStateListAPIRepository();
        return stateCustomLiveData;
    }

    /**
     * Calling Get secret question View model
     */
    public CustomLiveData<GetQuestionResponseModel> getSecretQuestionViewModel() {
        questionResponseModelCustomLiveData = createAccountRepository.getSecretQuestionAPIRepository();
        return questionResponseModelCustomLiveData;
    }

    /**
     * Get Country Response
     */
    public CustomLiveData<CountryStateResponseModel> getCountryResponse() {
        return countryCustomLiveData;
    }

    /**
     * Get State Response
     */
    public CustomLiveData<CountryStateResponseModel> getStateResponse() {
        return stateCustomLiveData;
    }

    /**
     * Get Username and Email available
     */
    public CustomLiveData<CommonResponseModel> getEmailUsernameResponse() {
        return emailUsernameLiveData;
    }

    /**
     * Call Sign Up API
     */
    public CustomLiveData<CommonResponseModel> callSignUpViewmodel(CreateAccountParamModel createAccountParamModel) {
        signupCustomLiveData = createAccountRepository.signUpRepo(createAccountParamModel);
        return signupCustomLiveData;
    }

    /**
     * Call Alias Exist API
     */
    public CustomLiveData<CommonResponseModel> getAliasExistViewmodel(ParamModel paramModel) {
        emailUsernameLiveData = createAccountRepository.getAliasExistRepo(paramModel);
        return emailUsernameLiveData;
    }
}
