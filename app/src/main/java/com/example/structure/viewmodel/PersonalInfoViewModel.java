package com.example.structure.viewmodel;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.CountryStateResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.PersonalInfoResponseModel;
import com.example.structure.data.repository.PeronalInfoRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 04-02-2020
 */
public class PersonalInfoViewModel extends ViewModel {

    private String TAG = getClass().getSimpleName();
    private CustomLiveData<CountryStateResponseModel> countryCustomLiveData;
    private CustomLiveData<CountryStateResponseModel> stateCustomLiveData;
    private CustomLiveData<CommonResponseModel> emailUsernameLiveData;
    private CustomLiveData<PersonalInfoResponseModel> personalInfoCustomLiveData;
    private PeronalInfoRepository peronalInfoRepository;

    @Inject
    public PersonalInfoViewModel(PeronalInfoRepository peronalInfoRepository) {
        this.peronalInfoRepository = peronalInfoRepository;
    }

    /**
     * Calling Email available View model
     */
    public CustomLiveData<CommonResponseModel> getEmailExistViewModel(ParamModel paramModel) {
        emailUsernameLiveData = peronalInfoRepository.getEmailExist(paramModel);
        return emailUsernameLiveData;
    }

    /**
     * Calling Country View model
     *//*
    public void getCountryViewModel() {
        countryCustomLiveData = peronalInfoRepository.getCountryListAPIRepository();
    }

    *//**
     * Calling State View model
     *//*
    public void getStateViewModel() {
        stateCustomLiveData = peronalInfoRepository.getStateListAPIRepository();
    }*/

    /**
     * Get Country Response
     */
    public CustomLiveData<CountryStateResponseModel> getCountryResponse() {
        countryCustomLiveData = peronalInfoRepository.getCountryListAPIRepository();
        return countryCustomLiveData;
    }

    /**
     * Get State Response
     */
    public CustomLiveData<CountryStateResponseModel> getStateResponse() {
        stateCustomLiveData = peronalInfoRepository.getStateListAPIRepository();
        return stateCustomLiveData;
    }

    /**
     * Get Email available
     */
    public CustomLiveData<CommonResponseModel> getEmailUsernameResponse() {
        return emailUsernameLiveData;
    }

    /**
     * Call Alias Exist API
     */
    public CustomLiveData<CommonResponseModel> getAliasExistViewmodel(ParamModel paramModel) {
        emailUsernameLiveData = peronalInfoRepository.getAliasExistRepo(paramModel);
        return emailUsernameLiveData;
    }

    public CustomLiveData<PersonalInfoResponseModel> personalInfoViewModel(String beararToken) {
        personalInfoCustomLiveData = peronalInfoRepository.personalInfoAPIRepository(beararToken);
        return personalInfoCustomLiveData;
    }

    /**
     * Update the user info except username
     */
    public CustomLiveData<CommonResponseModel> updatePersonalInfoViewModel(String beararToken, ParamModel paramModel) {
        emailUsernameLiveData = peronalInfoRepository.updatePersonalInfoAPIRepository(beararToken, paramModel);
        return emailUsernameLiveData;
    }
}
