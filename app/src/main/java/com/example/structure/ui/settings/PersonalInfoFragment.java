package com.example.structure.ui.settings;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.CountryStateResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.PersonalInfoResponseModel;
import com.example.structure.support.CustomNumberPicker;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.PersonalInfoViewModel;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInfoFragment extends BaseFragment {

    private String TAG = getClass().getSimpleName();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    private PersonalInfoViewModel personalInfoViewModel;
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;

    @BindView(R.id.title_lbl)
    TextView title_lbl;
    @BindView(R.id.settings_img)
    ImageView settings_img;
    @BindView(R.id.ic_back_arrow)
    ImageView ic_back_arrow;
    @BindView(R.id.email_lbl)
    TextView email_lbl;
    @BindView(R.id.email_etext)
    EditText email_etext;
    @BindView(R.id.username_lbl)
    TextView username_lbl;
    @BindView(R.id.username_etext)
    EditText username_etext;
    @BindView(R.id.fname_lbl)
    TextView fname_lbl;
    @BindView(R.id.fname_etext)
    EditText fname_etext;
    @BindView(R.id.lname_lbl)
    TextView lname_lbl;
    @BindView(R.id.lname_etext)
    EditText lname_etext;
    @BindView(R.id.address1_lbl)
    TextView address1_lbl;
    @BindView(R.id.address1_etext)
    EditText address1_etext;
    @BindView(R.id.address2_lbl)
    TextView address2_lbl;
    @BindView(R.id.address2_etext)
    EditText address2_etext;
    @BindView(R.id.city_lbl)
    TextView city_lbl;
    @BindView(R.id.city_etext)
    EditText city_etext;
    @BindView(R.id.state_lbl)
    TextView state_lbl;
    @BindView(R.id.state_etext)
    EditText state_etext;
    @BindView(R.id.country_lbl)
    TextView country_lbl;
    @BindView(R.id.country_etext)
    EditText country_etext;
    @BindView(R.id.postal_code_lbl)
    TextView postal_code_lbl;
    @BindView(R.id.postal_code_etext)
    EditText postal_code_etext;
    @BindView(R.id.leader_board_lbl)
    TextView leader_board_lbl;
    @BindView(R.id.leaderboard_alias_info_img)
    AppCompatImageView leaderboard_alias_info_img;
    @BindView(R.id.leader_board_etext)
    EditText leader_board_etext;
    @BindView(R.id.out_of_leaderboard_checked)
    AppCompatCheckBox out_of_leaderboard_checked;
    @BindView(R.id.out_of_leaderboard_checked_tview)
    TextView out_of_leaderboard_checked_tview;
    @BindView(R.id.save_changes_btn)
    Button save_changes_btn;
    @BindView(R.id.leader_board_layout)
    LinearLayout leader_board_layout;

    private NavController navController;
    private PersonalInfoResponseModel.PersonalInfoBean personalInfoBean;
    private CustomProgressDialog customProgressDialog;
    private String[] countryListBeans, stateListBeans, secretQuestions;
    private String itemName, selectedCountry, selectedState;
    private int indexPos = 0, selectedQuestionId = 0;
    private boolean isEmailExist = true, isAliasExist = true;
    private ParamModel paramModel = new ParamModel();

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    /**
     * Initialize the Personal Info Viewmodel
     */
    private void configureViewModel() {
        personalInfoViewModel = ViewModelProviders.of(this, viewModelFactory).get(PersonalInfoViewModel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        configureDagger();
        configureViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        fragmentManager = getFragmentManager();
        getCountryStateResponse();
        getPersonalInfoAPICall();
//        getCountryStateAPICall();
        initUI();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();
        disableEdittextInput(country_etext);
        disableEdittextInput(state_etext);
        navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
//        email_etext.requestFocus();
        leader_board_etext.setImeOptions(EditorInfo.IME_ACTION_DONE);
        /*email_etext.setOnFocusChangeListener((view, b) -> {
            String email = email_etext.getText().toString();
            if (!b) {
                if (!TextUtils.isEmpty(email)) {
                    if (isValidEmail(email)) {
                        if (!email.equals(personalInfoBean.getEmail())) {
                            new Handler().post(() -> {
                                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                                ParamModel paramModel = new ParamModel();
                                paramModel.setEmail(email);
                                personalInfoViewModel.getEmailExistViewModel(paramModel).observe((LifecycleOwner) mContext, commonResponseModelApiResponse ->
                                        emailUsernameAlaisResponse(commonResponseModelApiResponse));
                            });
                        }
                    } else Utils.showCustomAlert(mContext, getString(R.string.alert),
                            "Invalid Email. Please try again!", "email_invalid");
                }
            }
        });

        leader_board_etext.setOnFocusChangeListener((view, b) -> {
            String alias = leader_board_etext.getText().toString().trim();
            if (!b) {
                if (!TextUtils.isEmpty(alias) && !alias.equals(personalInfoBean.getAlias())) {
                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                    ParamModel paramModel = new ParamModel();
                    paramModel.setAlias(alias);
                    personalInfoViewModel.getAliasExistViewmodel(paramModel).observe((LifecycleOwner) mContext, commonResponseModelApiResponse ->
                            emailUsernameAlaisResponse(commonResponseModelApiResponse));
                }
            }
        });

        leader_board_etext.setOnEditorActionListener((textView, i, keyEvent) -> {
            String alias = leader_board_etext.getText().toString().trim();
            if ((keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) || (i == EditorInfo.IME_ACTION_DONE)) {
                if (!TextUtils.isEmpty(alias) && !alias.equals(personalInfoBean.getAlias())) {
                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                    ParamModel paramModel = new ParamModel();
                    paramModel.setAlias(alias);
                    personalInfoViewModel.getAliasExistViewmodel(paramModel).observe((LifecycleOwner) mContext, commonResponseModelApiResponse ->
                            emailUsernameAlaisResponse(commonResponseModelApiResponse));
                }
                return true;
            }
            return false;
        });*/

        country_etext.setOnTouchListener((view, motionEvent) ->

        {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                String existingVal = null;
                if (country_etext.getText().toString().trim().length() > 0)
                    existingVal = country_etext.getText().toString();
                Log.i(TAG, "onTouch:countryListBeans list " + countryListBeans.length);
                pickerDialog(mContext, countryListBeans, indexPos - 2, country_etext,
                        "Select Country", existingVal);
            }
            return true;
        });

        state_etext.setOnTouchListener((view, motionEvent) ->

        {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                String existingVal = null;
                if (state_etext.getText().toString().trim().length() > 0)
                    existingVal = state_etext.getText().toString();
                Log.i(TAG, "onTouch:stateListBeans list " + stateListBeans.length);
                pickerDialog(mContext, stateListBeans, indexPos - 2, state_etext,
                        "Select State", existingVal);
            }
            return true;
        });

        out_of_leaderboard_checked.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                leader_board_etext.setEnabled(false);
                leader_board_layout.setBackground(getResources().getDrawable(R.drawable.bg_edittext_gray));
//                leader_board_etext.setClickable(true);
            } else {
                leader_board_etext.setEnabled(true);
                leader_board_layout.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
//                leader_board_etext.setFocusable(false);
//                leader_board_etext.setClickable(false);
            }
        });
    }

    private void setHeader() {
        ic_back_arrow.setVisibility(View.VISIBLE);
        settings_img.setVisibility(View.GONE);
        title_lbl.setText(getString(R.string.personal_info));
    }

    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[]{address1_etext, address2_etext, city_etext,
                country_etext, email_etext, fname_etext, lname_etext, leader_board_etext, username_etext, postal_code_etext,
                state_etext, out_of_leaderboard_checked_tview});
        Utils.setBoldFonts(mContext, new TextView[]{save_changes_btn, leader_board_lbl, address1_lbl, address2_lbl, city_lbl,
                country_lbl, email_lbl, fname_lbl, lname_lbl, postal_code_lbl, username_lbl, state_lbl});
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.save_changes_btn, R.id.ic_back_arrow, R.id.leaderboard_alias_info_img})
    void buttonAction(View view) {
        long mLastClickTime = 0;
        switch (view.getId()) {
            case R.id.ic_back_arrow:
                navController.popBackStack();
                break;
            case R.id.save_changes_btn:
                if (email_etext.getText().toString().length() > 0) {
                    String email = email_etext.getText().toString().trim();
                    if (isValidEmail(email)) {
                    /*if (fname_etext.getText().toString().length() > 0) {
                        if (lname_etext.getText().toString().length() > 0) {
                            if (address1_etext.getText().toString().length() > 0) {
                                if (address2_etext.getText().toString().length() > 0) {
                                    if (city_etext.getText().toString().length() > 0) {
                                        if (state_etext.getText().toString().length() > 0) {
                                            if (country_etext.getText().toString().length() > 0) {
                                                if (postal_code_etext.getText().toString().length() > 0) {
                                                    if (leader_board_etext.getText().toString().length() > 0) {
                                                        String alias = leader_board_etext.getText().toString().trim();
                                                        if (!isEmailExist || email.equals(personalInfoBean.getEmail())) {
                                                            if (!isAliasExist || alias.equals(personalInfoBean.getAlias())) {*/

                        String isLeaderBoardEnable = out_of_leaderboard_checked.isChecked() ? "0" : "1";

                        new Handler().post(() -> {

//                            paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                            paramModel.setEmail(email);
                            paramModel.setUsername(username_etext.getText().toString().trim());
                            paramModel.setFirst_name(fname_etext.getText().toString().trim());
                            paramModel.setLast_name(lname_etext.getText().toString().trim());
                            paramModel.setAddress1(address1_etext.getText().toString().trim());
                            paramModel.setAddress2(address2_etext.getText().toString().trim());
                            paramModel.setCity(city_etext.getText().toString().trim());
                            paramModel.setState(state_etext.getText().toString());
                            paramModel.setCountry(country_etext.getText().toString());
                            paramModel.setPostal_code(postal_code_etext.getText().toString());
                            paramModel.setAlias(leader_board_etext.getText().toString().trim());
                            paramModel.setIs_leaderboard_enabled(isLeaderBoardEnable);
                            if (postal_code_etext.getText().length() > 0) {
                                if (postal_code_etext.getText().length() == 5) {
                                    paramModel.setPostal_code(postal_code_etext.getText().toString());
                                    isExisitingEMail();
                                } else
                                    Utils.showOKCustomAlert(mContext, null,
                                            "Postal code must have 5 digits", "postal_code_failed");
                            } else {
                                paramModel.setPostal_code(postal_code_etext.getText().toString());
                                isExisitingEMail();
                            }
                        });
                                                            /*} else
                                                                Utils.showCustomAlert(mContext, getString(R.string.alert),
                                                                        "The given leaderboard alias already exists!", "alias_failed");
                                                        } else
                                                            Utils.showCustomAlert(mContext, getString(R.string.alert),
                                                                    "The given email id already exists!", "email_failed");
                                                    } else
                                                        Utils.showCustomAlert(mContext, getString(R.string.alert), "Please enter Leaderboard aliase name",
                                                                "leaderboard_empty");
                                                } else
                                                    Utils.showCustomAlert(mContext, getString(R.string.alert), "Please enter Postal number",
                                                            "postal_empty");
                                            } else
                                                Utils.showCustomAlert(mContext, getString(R.string.alert), "Please select country",
                                                        "country_empty");
                                        } else
                                            Utils.showCustomAlert(mContext, getString(R.string.alert), "Please select state",
                                                    "state_empty");
                                    } else
                                        Utils.showCustomAlert(mContext, getString(R.string.alert), "Please enter city",
                                                "city_empty");
                                } else
                                    Utils.showCustomAlert(mContext, getString(R.string.alert), "Please enter address 2",
                                            "address2_empty");
                            } else
                                Utils.showCustomAlert(mContext, getString(R.string.alert), "Please enter address 1",
                                        "address1_empty");
                        } else
                            Utils.showCustomAlert(mContext, getString(R.string.alert), "Please Enter Last name",
                                    "lname_empty");
                    } else
                        Utils.showCustomAlert(mContext, getString(R.string.alert), "Please Enter First name",
                                "fname_empty");*/
                    } else Utils.showCustomAlert(mContext, getString(R.string.alert),
                            "Please enter valid email", "email_invalid");
                } else
                    Utils.showCustomAlert(mContext, getString(R.string.alert), "Please enter your email", "email_empty");
                break;
            case R.id.leaderboard_alias_info_img:
                Utils.showOKCustomAlert(mContext, null, getString(R.string.alias_tip), "alias_tip");
                break;
        }
    }

    /**
     * Call the Country and State API
     *
     private void getCountryStateAPICall() {
     new Handler().post(() -> {
     personalInfoViewModel.getCountryViewModel();
     personalInfoViewModel.getStateViewModel();
     });
     }*/

    /**
     * check exisiting EMail
     **/
    private void isExisitingEMail() {
        String email = email_etext.getText().toString().trim();
        if (!email.equals(personalInfoBean.getEmail())) {
            new Handler().post(() -> {
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                ParamModel paramModel = new ParamModel();
                paramModel.setEmail(email);
                personalInfoViewModel.getEmailExistViewModel(paramModel).observe((LifecycleOwner) mContext, commonResponseModelApiResponse ->
                        emailUsernameAlaisResponse(commonResponseModelApiResponse));
            });
        } else isExisitngAliasName();
    }

    /**
     * check exisiting Alias
     **/
    private void isExisitngAliasName() {
        String alias = leader_board_etext.getText().toString().trim();
        if (!TextUtils.isEmpty(alias) && !alias.equals(personalInfoBean.getAlias())) {
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            ParamModel paramModel = new ParamModel();
            paramModel.setAlias(alias);
            personalInfoViewModel.getAliasExistViewmodel(paramModel).observe((LifecycleOwner) mContext, commonResponseModelApiResponse ->
                    emailUsernameAlaisResponse(commonResponseModelApiResponse));
        } else {
            updatePersonalInfoAPI();
        }
    }

    /**
     * check update personal info
     **/
    private void updatePersonalInfoAPI() {
        String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
        personalInfoViewModel.updatePersonalInfoViewModel(token, paramModel)
                .observe((LifecycleOwner) mContext, commonResponseModelApiResponse -> {
                    emailUsernameAlaisResponse(commonResponseModelApiResponse);
                });
    }

    /**
     * Call the Personal info API
     */
    private void getPersonalInfoAPICall() {
        new Handler().post(() -> {
            if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
                ParamModel paramModel = new ParamModel();
                String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
//                paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                personalInfoViewModel.personalInfoViewModel(token).observe((LifecycleOwner) mContext, new Observer<ApiResponse<PersonalInfoResponseModel>>() {
                    @Override
                    public void onChanged(ApiResponse<PersonalInfoResponseModel> personalInfoResponseModelApiResponse) {
                        personalInfoResponse(personalInfoResponseModelApiResponse);
                    }
                });
            }
        });
    }

    /**
     * Get the Country, State and Secret Question Response List
     */
    private void getCountryStateResponse() {
        new Handler().post(() -> {
            personalInfoViewModel.getCountryResponse().observe((LifecycleOwner) mContext, pickerResponseModelApiResponse ->
                    countryStateResponse(pickerResponseModelApiResponse));

            personalInfoViewModel.getStateResponse().observe((LifecycleOwner) mContext, pickerResponseModelApiResponse -> countryStateResponse(pickerResponseModelApiResponse));
        });
    }

    private void countryStateResponse(ApiResponse<CountryStateResponseModel> apiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        CountryStateResponseModel pickerResponseModel;
        if (apiResponse.getData() != null) {
            pickerResponseModel = apiResponse.getData();
            switch (apiResponse.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (pickerResponseModel != null)
                        updateUI(pickerResponseModel);
                    else if (pickerResponseModel.getError().equals(getString(R.string.invalid_token)))
                        Utils.showOKCustomAlert(mContext, null, "Session expired!",
                                "session_expired");
                    else
                        Utils.showCustomAlert(mContext, getString(R.string.alert), apiResponse.getData().getError(), "CreateAccFailed");
                    break;
                case ERROR:
                    Utils.handleErrorResponse(apiResponse.getError(), mContext);
                    break;
                case FAILURE:
                    String failureResponse = Utils.getConvertedErrorBody(apiResponse.getFailureResponse());
                    Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "addelectricvehicleFragment");
                    break;

                default:
                    break;
            }
        }
    }

    private void updateUI(CountryStateResponseModel countryStateResponseModel) {
        Log.i(TAG, "updateUI:countryStateResponseModel " + countryStateResponseModel.toString());
        if (countryStateResponseModel.getStatus().equals("1")) {
            if (countryStateResponseModel.getSuccess().equalsIgnoreCase("Country list")) {
                if (countryStateResponseModel.getCountry_list().size() > 0) {
                    countryListBeans = new String[countryStateResponseModel.getCountry_list().size()];
                    for (int i = 0; i < countryStateResponseModel.getCountry_list().size(); i++) {
                        countryListBeans[i] = countryStateResponseModel.getCountry_list().get(i).getName();
                    }
                }
            }
            if (countryStateResponseModel.getSuccess().equalsIgnoreCase("States list")) {
                if (countryStateResponseModel.getStates_list().size() > 0) {
                    stateListBeans = new String[countryStateResponseModel.getStates_list().size()];
                    for (int i = 0; i < countryStateResponseModel.getStates_list().size(); i++) {
                        stateListBeans[i] = countryStateResponseModel.getStates_list().get(i).getName();
                    }
                }
            }
        }
    }

    private void emailUsernameAlaisResponse(ApiResponse<CommonResponseModel> apiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        CommonResponseModel commonResponseModel;
        if (apiResponse.getData() != null) {
            commonResponseModel = apiResponse.getData();
            switch (apiResponse.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (commonResponseModel != null) {
                        String responseMessage = commonResponseModel.getMessage();
                        if (commonResponseModel.getStatus().equals("1")) {
                            sharedPreferencesUtil.saveUserEmail(Constant.USER_EMAIL, email_etext.getText().toString().trim());
                            switch (responseMessage) {
                                case "Email not registered":
                                    isEmailExist = false;
                                    isExisitngAliasName();
                                    break;
                                case "Alias not registered":
                                    isAliasExist = false;
                                    updatePersonalInfoAPI();
                                    break;
                                case "email id not verified":
//                                    navController.navigate(R.id.action_personalInfoFragment_to_bottom_menu_dashboard);
                                    Utils.showOKCustomAlert(mContext, null, commonResponseModel.getMessage(),
                                            "email_not_verified");
                                    break;
                                case "Profile has been updated.":
//                                    toastMessage(commonResponseModel.getMessage());
                                    Utils.showOKCustomAlert(mContext, null, commonResponseModel.getMessage(),
                                            "profile_updated");
                                    break;
                            }
                        } else
                            Utils.showOKCustomAlert(mContext, null, commonResponseModel.getMessage(), "response_failed");
                    } else if (commonResponseModel.getError().equals(getString(R.string.invalid_token)))
                        Utils.showOKCustomAlert(mContext, null, "Session expired!",
                                "session_expired");
                    else
                        Utils.showCustomAlert(mContext, getString(R.string.alert), apiResponse.getData().getError(), "response_failed");
                    break;
                case ERROR:
                    Utils.handleErrorResponse(apiResponse.getError(), mContext);
                    break;
                case FAILURE:
                    String failureResponse = Utils.getConvertedErrorBody(apiResponse.getFailureResponse());
                    Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "addelectricvehicleFragment");
                    break;

                default:
                    break;
            }
        }
    }

    private void isEmailUsernameExist(CommonResponseModel commonResponseModel) {
        Log.i(TAG, "updateUI:commonResponseModel " + commonResponseModel.toString());
        if (commonResponseModel.getStatus().equals("1")) {

            if (commonResponseModel.getMessage().length() > 0) {
                toastMessage(commonResponseModel.getMessage());
                if (commonResponseModel.getMessage().equalsIgnoreCase("Email not registered")) {
                    isEmailExist = false;
                } else if (commonResponseModel.getMessage().equalsIgnoreCase("Alias not registered"))
                    isAliasExist = false;
            }
        } else {
            if (commonResponseModel.getError().length() > 0) {
                Utils.showCustomAlert(mContext, getString(R.string.alert), commonResponseModel.getError(),
                        "email_username_alias_exist");
            } else if (commonResponseModel.getMessage().length() > 0)
                Utils.showCustomAlert(mContext, getString(R.string.alert), commonResponseModel.getMessage(),
                        "email_username_alias_exist");
        }
    }

    private void personalInfoResponse(ApiResponse<PersonalInfoResponseModel> apiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        PersonalInfoResponseModel personalInfoResponseModel;
        if (apiResponse.getData() != null) {
            personalInfoResponseModel = apiResponse.getData();
            switch (apiResponse.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (personalInfoResponseModel != null)
                        getPersonalInfoResponse(personalInfoResponseModel);
                    else if (personalInfoResponseModel.getError().equals(getString(R.string.invalid_token)))
                        Utils.showOKCustomAlert(mContext, null, "Session expired!",
                                "session_expired");
                    else
                        Utils.showCustomAlert(mContext, getString(R.string.alert), apiResponse.getData().getError(), "CreateAccFailed");
                    break;
                case ERROR:
                    Utils.handleErrorResponse(apiResponse.getError(), mContext);
                    break;
                case FAILURE:
                    String failureResponse = Utils.getConvertedErrorBody(apiResponse.getFailureResponse());
                    Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "addelectricvehicleFragment");
                    break;

                default:
                    break;
            }
        }
    }

    private void getPersonalInfoResponse(PersonalInfoResponseModel personalInfoResponseModel) {
        if (personalInfoResponseModel.getStatus().equals("1")) {
            toastMessage(personalInfoResponseModel.getSuccess() + " success");
            personalInfoBean = new PersonalInfoResponseModel.PersonalInfoBean();
            personalInfoBean = personalInfoResponseModel.getPersonalInfo();
            email_etext.setText(personalInfoBean.getEmail());
            username_etext.setText(personalInfoBean.getUsername());
            fname_etext.setText(personalInfoBean.getFirstName());
            lname_etext.setText(personalInfoBean.getLastName());
            address1_etext.setText(personalInfoBean.getAddress1());
            address2_etext.setText(personalInfoBean.getAddress2());
            city_etext.setText(personalInfoBean.getCity());
            state_etext.setText(personalInfoBean.getState());
            country_etext.setText(personalInfoBean.getCountry());
            postal_code_etext.setText(personalInfoBean.getPostalCode());
            leader_board_etext.setText(personalInfoBean.getAlias());
            String isLeaderboardEnable = personalInfoBean.getIsLeaderboardEnabled();
            if (isLeaderboardEnable.equals("1")) {
                leader_board_etext.setEnabled(true);
                out_of_leaderboard_checked.setChecked(false);
                leader_board_layout.setBackground(getResources().getDrawable(R.drawable.bg_edittext, mContext.getTheme()));
            } else {
                leader_board_etext.setEnabled(true);
                leader_board_layout.setBackground(getResources().getDrawable(R.drawable.bg_edittext_gray, mContext.getTheme()));
                out_of_leaderboard_checked.setChecked(true);
            }
        } else
            Utils.showCustomAlert(mContext, getString(R.string.alert), personalInfoResponseModel.getError(), "personal_info_failed");
    }

    /**
     * display the dialog which contains secret questions
     *
     * @param existingValue will shown when value is available
     */
    private void pickerDialog(final Context context, String[] list, int index, EditText
            eText, String pickerType, String existingValue) {
        final Dialog numberPickerDialog = new Dialog(context, R.style.custom_dialog);
        numberPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        numberPickerDialog.setCancelable(false);
        numberPickerDialog.setContentView(R.layout.custom_number_picker_dialog);

        CustomNumberPicker np = numberPickerDialog.findViewById(R.id.wheel_view_wv);
        TextView positive_btn = numberPickerDialog.findViewById(R.id.dialogButtonOK);
        TextView negative_btn = numberPickerDialog.findViewById(R.id.dialogButtonNO);
        TextView pickerTitle = numberPickerDialog.findViewById(R.id.dialogTitle);
        Utils.setBoldFonts(context, new TextView[]{positive_btn, negative_btn, pickerTitle});

        np.setOffset(2);
        np.setItems(Arrays.asList(list));
        np.setSeletion(index);
        pickerTitle.setText(pickerType);
        np.setOnWheelViewListener(new CustomNumberPicker.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                itemName = item;
            }
        });

        if (existingValue != null && !TextUtils.isEmpty(existingValue)) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].equals(existingValue))
                    np.setSeletion(i);
            }
        }

        positive_btn.setOnClickListener(view -> {
            Log.i(TAG, "pickerDialog:itemName " + itemName);
            if (itemName != null && !TextUtils.isEmpty(itemName)) {
                eText.setText(itemName);
                /*if (pickerType.equals("Secret Questions")) {
                    for (int i = 0; i < questionsBeanArrayList.size(); i++) {
                        if (questionsBeanArrayList.get(i).getQuestion().equals(itemName))
                            selectedQuestionId = questionsBeanArrayList.get(i).getId();
                    }
                }*/
            } else {
                if (!TextUtils.isEmpty(existingValue))
                    itemName = existingValue;
                else itemName = list[0];
                eText.setText(itemName);
            }
            eText.setError(null);

            itemName = "";

            numberPickerDialog.cancel();
            numberPickerDialog.dismiss();
        });

        negative_btn.setOnClickListener(view -> {
            numberPickerDialog.cancel();
            numberPickerDialog.dismiss();
        });
        numberPickerDialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
