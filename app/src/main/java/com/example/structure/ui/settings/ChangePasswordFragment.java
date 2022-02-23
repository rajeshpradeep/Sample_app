package com.example.structure.ui.settings;


import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;
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

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.support.PasswordStrength;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.ChangePasswordViewmodel;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends BaseFragment {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private ChangePasswordViewmodel changePasswordViewmodel;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.title_lbl)
    TextView title_lbl;
    @BindView(R.id.ic_back_arrow)
    ImageView ic_back_arrow;
    @BindView(R.id.settings_img)
    ImageView settings_img;
    @BindView(R.id.current_password_lbl)
    TextView current_password_lbl;
    @BindView(R.id.current_password_etext)
    EditText current_password_etext;
    @BindView(R.id.new_password_lbl)
    TextView new_password_lbl;
    @BindView(R.id.new_password_etext)
    EditText new_password_etext;
    @BindView(R.id.change_password_info_img)
    ImageView change_password_info_img;
    @BindView(R.id.password_status_lbl)
    TextView password_status_lbl;
    @BindView(R.id.password_status_seekbar)
    AppCompatSeekBar password_status_seekbar;
    @BindView(R.id.repeat_password_lbl)
    TextView repeat_password_lbl;
    @BindView(R.id.repeat_password_etext)
    EditText repeat_password_etext;
    @BindView(R.id.change_password_btn)
    Button change_password_btn;

    private NavController navController;
    private CustomProgressDialog customProgressDialog;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    /**
     * Initialize the Leaderboard Viewmodel
     */
    private void configureViewModel() {
        changePasswordViewmodel = ViewModelProviders.of(this, viewModelFactory).get(ChangePasswordViewmodel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        configureDagger();
        configureViewModel();
        mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((BottomMenuActivity) mContext).setNavigationalVisibility(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        fragmentManager = getFragmentManager();
        initUI();
        return view;
    }

    private void initUI() {
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();
        navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
        new_password_etext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 0) {
                    password_status_lbl.setVisibility(View.VISIBLE);
                    password_status_seekbar.setVisibility(View.VISIBLE);
                    int passwordVal = PasswordStrength.calculateStrength(charSequence.toString());
                    password_status_lbl.setVisibility(View.VISIBLE);
                    switch (passwordVal) {
                        case 0:
                            password_status_lbl.setText(getString(R.string.weak));
                            password_status_seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.lite_yellow, null), PorterDuff.Mode.SRC_IN);
                            password_status_seekbar.setProgress(25);
                            break;
                        case 1:
                            password_status_lbl.setText(getString(R.string.fair));
                            password_status_seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.red, null), PorterDuff.Mode.SRC_IN);
                            password_status_seekbar.setProgress(50);
                            break;
                        case 2:
                            password_status_lbl.setText(getString(R.string.good));
                            password_status_seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.yellow_gradient, null), PorterDuff.Mode.SRC_IN);
                            password_status_seekbar.setProgress(75);
                            break;
                        case 3:
                            password_status_lbl.setText(getString(R.string.strong));
                            password_status_seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.green_gradient, null), PorterDuff.Mode.SRC_IN);
                            password_status_seekbar.setProgress(100);
                            break;
                    }
                } else {
                    password_status_lbl.setVisibility(View.GONE);
                    password_status_seekbar.setVisibility(View.GONE);
                    /*password_status_seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.hint_color, null), PorterDuff.Mode.SRC_IN);
                    password_status_seekbar.setProgress(0);*/
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[]{title_lbl, current_password_lbl, new_password_lbl, password_status_lbl,
                repeat_password_lbl});
        Utils.setRegularFonts(mContext, new TextView[]{current_password_etext, new_password_etext, repeat_password_etext,
                change_password_btn});
    }

    private void setHeader() {
        ic_back_arrow.setVisibility(View.VISIBLE);
        settings_img.setVisibility(View.GONE);
        title_lbl.setText(getString(R.string.change_password));
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.ic_back_arrow, R.id.change_password_btn, R.id.change_password_info_img})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.ic_back_arrow:
//                setFragment(new SettingsFragment());
                navController.popBackStack();
                break;
            case R.id.change_password_btn:
                String oldPassword = current_password_etext.getText().toString().trim();
                String newPassword = new_password_etext.getText().toString().trim();
                String repeatPassword = repeat_password_etext.getText().toString().trim();
                if (!TextUtils.isEmpty(oldPassword)) {
                    if (!TextUtils.isEmpty(newPassword)) {
                        if (!TextUtils.isEmpty(repeatPassword)) {
                        if (newPassword.equals(repeatPassword)) {
                            ParamModel paramModel = new ParamModel();
                            if(sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
                                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                                String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
//                                paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                                paramModel.setCurrent_password(oldPassword);
                                paramModel.setNew_password(newPassword);
                                changePasswordViewmodel.getChangePasswordViewModel(token, paramModel).observe((LifecycleOwner) mContext, new Observer<ApiResponse<CommonResponseModel>>() {
                                    @Override
                                    public void onChanged(ApiResponse<CommonResponseModel> commonResponseModelApiResponse) {
                                        changePasswordResponse(commonResponseModelApiResponse);
                                    }
                                });
                            }
                        } else {
                            Utils.showCustomAlert(mContext, getString(R.string.alert), "Password Incorrect. please try again", "change_password_mismatched");
                            new_password_etext.setText("");
                            repeat_password_etext.setText("");
                        }

                        } else
                            Utils.showCustomAlert(mContext, getString(R.string.alert),  "Please enter Repeat password", "repeat_pwd_empty");

                    } else Utils.showCustomAlert(mContext, getString(R.string.alert),  "Please enter New password", "new_pwd_empty");

                } else Utils.showCustomAlert(mContext, getString(R.string.alert),  "Please enter Current password", "old_pwd_empty");
                break;
            case R.id.change_password_info_img:
                Utils.showOKCustomAlert(mContext, null, getString(R.string.password_tip), "password_unmatched");
                break;
        }
    }

    private void updateUI(CommonResponseModel commonResponseModel) {
//        toastMessage(commonResponseModel.getSuccess() + " API Success");
        Utils.showOKCustomAlert(mContext, null, commonResponseModel.getMessage(), "cp_success");
    }

    private void changePasswordResponse(ApiResponse<CommonResponseModel> commonResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        CommonResponseModel commonResponseModel = commonResponseModelApiResponse.getData();
        switch (commonResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (commonResponseModel.getStatus().equals("1")) {
                    new_password_etext.setText("");
                    repeat_password_etext.setText("");
                    current_password_etext.setText("");
                    updateUI(commonResponseModel);
                } else if (commonResponseModel.getError().equals("TOKEN_INVALID")) {
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                } else
                    Utils.showCustomAlert(mContext, getString(R.string.alert), commonResponseModel.getMessage(),
                            "cp_failed");
                break;
            case FAILURE:
                Utils.showCustomAlert(mContext, getString(R.string.alert),
                        Utils.getConvertedErrorBody(commonResponseModelApiResponse.getFailureResponse()), "change_password_failed");
                break;
            case ERROR:
                Utils.handleErrorResponse(commonResponseModelApiResponse.getError(), mContext);
                break;
        }
    }

    //setting launching  fragment
    /*private void setFragment(Fragment fragment) {
        //FOr initially add stack for backpress event
//        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.qmulus_frame, fragment);
        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
