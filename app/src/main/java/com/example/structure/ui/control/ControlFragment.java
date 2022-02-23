package com.example.structure.ui.control;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.structure.R;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ControlResponseModel;
import com.example.structure.data.models.EvMileageChangeVehicleResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.repository.ControlRepository;
import com.example.structure.support.CustomNumberPicker;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.support.CustomWebView;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.ResponseInterface;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.ControlViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ControlFragment extends BaseFragment implements ResponseInterface {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private Animation animShow, animHide;
    private CustomProgressDialog customProgressDialog;
    private ControlResponseModel controlResponseModel;
    @Inject
    ControlViewModel controlViewModel;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.title_lbl)
    TextView title_lbl;
    @BindView(R.id.settings_img)
    ImageView settings_img;
    @BindView(R.id.ic_back_arrow)
    ImageView ic_back_arrow;
    @BindView(R.id.control_type_lbl)
    TextView control_type_lbl;
    @BindView(R.id.control_type_tview)
    TextView control_type_tview;
    @BindView(R.id.sca_mode_lbl)
    TextView sca_mode_lbl;
    @BindView(R.id.sca_mode_tview)
    TextView sca_mode_tview;
    @BindView(R.id.ic_milage_arrow)
    ImageView ic_milage_arrow;
    @BindView(R.id.ic_ev_arrow)
    ImageView ic_ev_arrow;
    @BindView(R.id.ic_timer_arrow)
    ImageView ic_timer_arrow;
    @BindView(R.id.sca_control_lay)
    LinearLayout sca_control_lay;
    @BindView(R.id.control_seekbar_lay)
    LinearLayout control_seekbar_lay;
    @BindView(R.id.ev_milage_lay)
    LinearLayout ev_milage_lay;
    @BindView(R.id.timer_based_lay)
    LinearLayout timer_based_lay;
    @BindView(R.id.sca_type_img)
    ImageView sca_type_img;
    @BindView(R.id.sca_type_tview)
    TextView sca_type_tview;
    @BindView(R.id.sca_type_val_tview)
    TextView sca_type_val_tview;
    @BindView(R.id.sca_charge_lbl)
    TextView sca_charge_lbl;
    @BindView(R.id.sca_info_img)
    ImageView sca_info_img;
    @BindView(R.id.charge_tview)
    TextView charge_tview;
    @BindView(R.id.charge_key_tview)
    TextView charge_key_tview;
    @BindView(R.id.power_tview)
    TextView power_tview;
    @BindView(R.id.power_key_tview)
    TextView power_key_tview;
    @BindView(R.id.sca_mode_title)
    TextView sca_mode_title;
    @BindView(R.id.sca_mode_rgroup)
    RadioGroup sca_mode_rgroup;
    @BindView(R.id.charge_noramlly_rbtn)
    RadioButton charge_noramlly_rbtn;
    @BindView(R.id.disable_charge_rbtn)
    RadioButton disable_charge_rbtn;
    @BindView(R.id.control_charge_rbtn)
    RadioButton control_charge_rbtn;
    @BindView(R.id.charge_val)
    TextView charge_val;
    @BindView(R.id.charge_seekbar)
    AppCompatSeekBar charge_seekbar;
    @BindView(R.id.seekbar_start_val)
    TextView seekbar_start_val;
    @BindView(R.id.seekbar_end_val)
    TextView seekbar_end_val;
    @BindView(R.id.power_val)
    TextView power_val;
    @BindView(R.id.power_seekbar)
    AppCompatSeekBar power_seekbar;
    @BindView(R.id.power_seekbar_start_val)
    TextView power_seekbar_start_val;
    @BindView(R.id.power_seekbar_end_val)
    TextView power_seekbar_end_val;
    @BindView(R.id.apply_btn)
    Button apply_btn;
    @BindView(R.id.milage_img)
    ImageView milage_img;
    @BindView(R.id.milage_tview)
    TextView milage_tview;
    @BindView(R.id.milage_val_tview)
    TextView milage_val_tview;
    @BindView(R.id.ev_milage_lbl)
    TextView ev_milage_lbl;
    @BindView(R.id.ev_milage_info_img)
    ImageView ev_milage_info_img;
    @BindView(R.id.ev_milage_switchcompat)
    SwitchCompat ev_milage_switchcompat;
    @BindView(R.id.ev_milage_switchcompat_tview)
    TextView ev_milage_switchcompat_tview;
    @BindView(R.id.electric_vehicle_tview)
    TextView electric_vehicle_tview;
    @BindView(R.id.electric_vehicle_layout)
    LinearLayout electric_vehicle_layout;
    @BindView(R.id.electric_vehicle_etext)
    EditText electric_vehicle_etext;
    @BindView(R.id.ev_milage_needed_tview)
    TextView ev_milage_needed_tview;
    @BindView(R.id.ev_milage_needed_info_img)
    ImageView ev_milage_needed_info_img;
    @BindView(R.id.ev_milage_seekbar)
    AppCompatSeekBar ev_milage_seekbar;
    @BindView(R.id.ev_milage_sc_val)
    TextView ev_milage_sc_val;
    @BindView(R.id.ev_milage_sc_tot)
    TextView ev_milage_sc_tot;
    @BindView(R.id.ev_milage_apply_btn)
    Button ev_milage_apply_btn;
    @BindView(R.id.acc_ev_milage_lay)
    LinearLayout acc_ev_milage_lay;
    @BindView(R.id.acc_ev_milage_img)
    ImageView acc_ev_milage_img;
    @BindView(R.id.acc_ev_milage_lbl)
    TextView acc_ev_milage_lbl;
    @BindView(R.id.eVehicle_lbl)
    TextView eVehicle_lbl;
    @BindView(R.id.acc_ev_milage_progressbar)
    ProgressBar acc_ev_milage_progressbar;
    @BindView(R.id.acc_ev_milage_sc_val)
    TextView acc_ev_milage_sc_val;
    @BindView(R.id.acc_ev_milage_sc_tot)
    TextView acc_ev_milage_sc_tot;
    @BindView(R.id.acc_ev_milage_needed_info_img)
    ImageView acc_ev_milage_needed_info_img;
    @BindView(R.id.timer_img)
    ImageView timer_img;
    @BindView(R.id.timer_tview)
    TextView timer_tview;
    @BindView(R.id.timer_val_tview)
    TextView timer_val_tview;
    @BindView(R.id.timer_based_lbl)
    TextView timer_based_lbl;
    @BindView(R.id.timer_based_info_img)
    ImageView timer_based_info_img;
    @BindView(R.id.tb_control_switchcompat)
    SwitchCompat tb_control_switchcompat;
    @BindView(R.id.tb_control_switchcompat_tview)
    TextView tb_control_switchcompat_tview;
    @BindView(R.id.set_duration_lbl)
    TextView set_duration_lbl;
    @BindView(R.id.start_time_etext)
    TextView start_time_etext;
    @BindView(R.id.end_time_etext)
    TextView end_time_etext;
    @BindView(R.id.time_remaining_lay)
    LinearLayout time_remaining_lay;
    @BindView(R.id.ic_timer_img)
    ImageView ic_timer_img;
    @BindView(R.id.time_remaing_tview)
    TextView time_remaing_tview;
    @BindView(R.id.time_remaining_key_tview)
    TextView time_remaining_key_tview;
    @BindView(R.id.timer_apply_btn)
    Button timer_apply_btn;
    @BindView(R.id.sca_type_rlay)
    RelativeLayout sca_type_rlay;
    @BindView(R.id.milage_rlay)
    RelativeLayout milage_rlay;
    @BindView(R.id.timer_rlay)
    RelativeLayout timer_rlay;
    @BindView(R.id.start_timer_layout)
    LinearLayout start_timer_layout;
    @BindView(R.id.end_timer_layout)
    LinearLayout end_timer_layout;
    @BindView(R.id.add_vehicle_rlay)
    RelativeLayout add_vehicle_rlay;
    @BindView(R.id.add_vehicle_tview)
    TextView add_vehicle_tview;
    @BindView(R.id.add_vehicle_btn)
    TextView add_vehicle_btn;

    private String itemName, updateChargeMode, vehicleName, powerProgress, chargeProgress, chargeMode, scaMode, evMileageStatus,
            timerControlStatus, selectedVehicle, hour, min, updateControlType;
    StringBuilder controlTypes;
    private int indexPos = 0, selectedVehicleID, vehicleListCount, ev_mileage_progress;
    private String[] electricVehicleDetails;
    private ArrayList<String> electricVehicleDetailList;
    private CountDownTimer countDownTimer;
    private boolean isCountDownTimerRunning = false;
    private NavController navController;
    int delay = 15000, tempDuration = -1; // 15 sec
    private Handler handler = new Handler();
    private Runnable runnable;
    private ResponseInterface responseInterface;
    private Dialog dialog = null;

    @Inject
    ControlRepository controlRepository;

    public ControlFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    /**
     * Initialize the Control ViewModel Viewmodel
     */
    private void configureViewModel() {
        controlViewModel = ViewModelProviders.of(this, viewModelFactory).get(ControlViewModel.class);
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
        ((BottomMenuActivity) mContext).setNavigationalVisibility(true);
        initControlAPICall();
    }

    @Override
    public void onStop() {
        try {
            stopHandler();
            if (countDownTimer != null) {
                isCountDownTimerRunning = false;
                countDownTimer.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    public void onPause() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        super.onPause();
    }

    private void stopHandler() {
        try {
            Log.i(TAG, "stopHandler: ");
            handler.removeCallbacks(runnable); //stop handler when fragment is not visible
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void restartHandler() {
        if (handler != null) {
            Log.i(TAG, "restartHandler: ");
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, delay);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_control, container, false);
        unbinder = ButterKnife.bind(this, view);
        responseInterface = this;
        fragmentManager = getFragmentManager();
        initUI();

        return view;
    }

    private void initUI() {
//        setTransparentStatusBar();
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();
        navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
        disableEdittextInput(electric_vehicle_etext);
        animShow = AnimationUtils.loadAnimation(mContext, R.anim.view_show);
        animHide = AnimationUtils.loadAnimation(mContext, R.anim.view_hide);
//        String charge = "https://web1.qmulusev.com.farshore.net:3000/d-solo/sCZ8PiJZk/argonne-control?orgId=1&refresh=1m&var-device_id=SCA-7906-F4CF&panelId=6&theme=light&kiosk&fullscreen";
//        String power = "https://web1.qmulusev.com.farshore.net:3000/d-solo/sCZ8PiJZk/argonne-control?orgId=1&refresh=1m&var-device_id=SCA-7906-F4CF&panelId=8&theme=light&kiosk&fullscreen";
//        loadWebview(charge_tview, charge);
//        loadWebview(power_tview, power);
        tb_control_switchcompat.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                start_time_etext.setEnabled(true);
                end_time_etext.setEnabled(true);
                start_timer_layout.setBackgroundResource(R.drawable.bg_edittext);
                end_timer_layout.setBackgroundResource(R.drawable.bg_edittext);
                tb_control_switchcompat_tview.setText(getString(R.string.enable));
                timer_val_tview.setText(getString(R.string.enable));
                timer_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
            } else {
                start_time_etext.setEnabled(false);
                end_time_etext.setEnabled(false);
                start_timer_layout.setBackgroundResource(R.drawable.bg_edittext_gray);
                end_timer_layout.setBackgroundResource(R.drawable.bg_edittext_gray);
                tb_control_switchcompat_tview.setText(getString(R.string.disable));
                timer_val_tview.setText(getString(R.string.disable));
                timer_val_tview.setBackgroundResource(R.drawable.bg_lite_pink);
            }
        });

        ev_milage_switchcompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ev_milage_switchcompat_tview.setText(getString(R.string.enable));
                    milage_val_tview.setText(getString(R.string.enable));
                    milage_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
                } else {
                    ev_milage_switchcompat_tview.setText(getString(R.string.disable));
                    milage_val_tview.setText(getString(R.string.disable));
                    milage_val_tview.setBackgroundResource(R.drawable.bg_lite_pink);
                }
            }
        });

        start_time_etext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*if (charSequence.toString().length() > 0) {
                    if (Integer.parseInt(charSequence.toString()) > 23) {
                        Utils.showCustomAlert(mContext, getString(R.string.alert), "Enter correct hour", "hour_error");
                        start_time_etext.setText("");
                    }
                }*/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                hour = editable.toString();
            }
        });

        end_time_etext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 0) {
                    if (Integer.parseInt(charSequence.toString()) > 59) {
                        Utils.showCustomAlert(mContext, getString(R.string.alert), "Enter correct mintues", "min_error");
                        end_time_etext.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                min = editable.toString();
            }
        });

        /*15 sec refresh update initialize*/
        updateChargeMode = null;
        chargeMode = null;
        scaMode = null;
        evMileageStatus = null;
        timerControlStatus = null;
        selectedVehicle = null;
        powerProgress = null;
        chargeProgress = null;
        hour = null;
        min = null;
        ev_mileage_progress = -1;
        selectedVehicleID = -1;
        updateControlType = null;
    }

    private void setFont() {
        Utils.setBoldFonts(mContext, new TextView[]{control_type_tview, sca_mode_tview, sca_type_tview, sca_charge_lbl, electric_vehicle_tview,
                charge_val, power_val, apply_btn, milage_tview, ev_milage_lbl, ev_milage_needed_tview, ev_milage_apply_btn, acc_ev_milage_lbl,
                acc_ev_milage_sc_val, timer_tview, timer_based_lbl, set_duration_lbl, timer_apply_btn, sca_mode_title, add_vehicle_btn});
        Utils.setRegularFonts(mContext, new TextView[]{title_lbl, control_type_lbl, sca_mode_lbl, sca_type_val_tview, power_key_tview,
                charge_key_tview, charge_noramlly_rbtn, disable_charge_rbtn, control_charge_rbtn, seekbar_start_val, seekbar_end_val,
                power_seekbar_start_val, power_seekbar_end_val, milage_val_tview, ev_milage_switchcompat_tview, ev_milage_sc_tot,
                eVehicle_lbl, acc_ev_milage_sc_tot, timer_val_tview, tb_control_switchcompat_tview, start_time_etext,
                end_time_etext, time_remaing_tview, time_remaining_key_tview, charge_tview, power_tview, add_vehicle_tview});
    }

    private void setHeader() {
        ic_back_arrow.setVisibility(View.GONE);
        settings_img.setVisibility(View.VISIBLE);
        title_lbl.setText(getString(R.string.control_sca));
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.settings_img, R.id.ic_milage_arrow, R.id.ic_ev_arrow, R.id.ic_timer_arrow, R.id.ev_milage_apply_btn,
            R.id.timer_apply_btn, R.id.apply_btn, R.id.milage_rlay, R.id.sca_type_rlay, R.id.timer_rlay, R.id.add_vehicle_btn,
            R.id.sca_info_img, R.id.ev_milage_needed_info_img, R.id.ev_milage_info_img, R.id.timer_based_info_img, R.id.acc_ev_milage_needed_info_img})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.settings_img:
                navController.navigate(R.id.action_controlFragment_to_navigation_settings);
                break;
            case R.id.ic_milage_arrow:
                if (sca_control_lay.getVisibility() == View.VISIBLE) {
                    ic_milage_arrow.setRotation(-90);
//                    sca_control_lay.startAnimation(animHide);
                    sca_control_lay.setVisibility(View.GONE);
                } else {
                    ic_milage_arrow.setRotation(0);
                    sca_control_lay.setVisibility(View.VISIBLE);
//                    sca_control_lay.startAnimation(animShow);
                }
                break;
            case R.id.sca_type_rlay:
                if (sca_control_lay.getVisibility() == View.VISIBLE) {
                    ic_milage_arrow.setRotation(-90);
//                    sca_control_lay.startAnimation(animHide);
                    sca_control_lay.setVisibility(View.GONE);
                } else {
                    ic_milage_arrow.setRotation(0);
                    sca_control_lay.setVisibility(View.VISIBLE);
//                    sca_control_lay.startAnimation(animShow);
                }
                break;
            case R.id.milage_rlay:
                if (vehicleListCount > 0) {
                    if (ev_milage_lay.getVisibility() == View.VISIBLE) {
                        ic_ev_arrow.setRotation(-90);
//                    ev_milage_lay.startAnimation(animHide);
                        ev_milage_lay.setVisibility(View.GONE);
                    } else {
                        ic_ev_arrow.setRotation(0);
                        ev_milage_lay.setVisibility(View.VISIBLE);
//                    ev_milage_lay.startAnimation(animShow);
                    }
                }
                break;
            case R.id.ic_ev_arrow:
                if (vehicleListCount > 0) {
                    if (ev_milage_lay.getVisibility() == View.VISIBLE) {
                        ic_ev_arrow.setRotation(-90);
//                    ev_milage_lay.startAnimation(animHide);
                        ev_milage_lay.setVisibility(View.GONE);
                    } else {
                        ic_ev_arrow.setRotation(0);
                        ev_milage_lay.setVisibility(View.VISIBLE);
//                    ev_milage_lay.startAnimation(animShow);
                    }
                }
                break;
            case R.id.ic_timer_arrow:
                if (timer_based_lay.getVisibility() == View.VISIBLE) {
                    ic_timer_arrow.setRotation(-90);
//                    timer_based_lay.startAnimation(animHide);
                    timer_based_lay.setVisibility(View.GONE);
                } else {
                    ic_timer_arrow.setRotation(0);
                    timer_based_lay.setVisibility(View.VISIBLE);
//                    timer_based_lay.startAnimation(animShow);
                }
                break;
            case R.id.timer_rlay:
                if (timer_based_lay.getVisibility() == View.VISIBLE) {
                    ic_timer_arrow.setRotation(-90);
//                    timer_based_lay.startAnimation(animHide);
                    timer_based_lay.setVisibility(View.GONE);
                } else {
                    ic_timer_arrow.setRotation(0);
                    timer_based_lay.setVisibility(View.VISIBLE);
//                    timer_based_lay.startAnimation(animShow);
                }
                break;
            case R.id.apply_btn:
                callPublishChargeModeMileageAPI();
                break;
            case R.id.ev_milage_apply_btn:
                callApplyEvMileageAPI();
                break;
            case R.id.timer_apply_btn:
                callApplyTimerAPI();
                break;
            case R.id.add_vehicle_btn:
                Bundle bundle = new Bundle();
                bundle.putString("tag", TAG);
                navController.navigate(R.id.action_bottom_menu_control_to_vehicleListFragment, bundle);
                break;
            case R.id.sca_info_img:
                Utils.showOKCustomAlert(mContext, null, String.valueOf(getText(R.string.sca_charge_control_tip)), "control_tip");
                break;
            case R.id.ev_milage_needed_info_img:
                Utils.showOKCustomAlert(mContext, null, String.valueOf(getText(R.string.ev_mileage_needed_tip)), "control_tip");
                break;
            case R.id.ev_milage_info_img:
                Utils.showOKCustomAlert(mContext, null, String.valueOf(getText(R.string.ev_mileage_control_tip)), "control_tip");
                break;
            case R.id.acc_ev_milage_needed_info_img:
                Utils.showOKCustomAlert(mContext, null, String.valueOf(getText(R.string.accumulated_ev_mileage_tip)), "control_tip");
                break;
            case R.id.timer_based_info_img:
                Utils.showOKCustomAlert(mContext, null, String.valueOf(getText(R.string.timer_based_control_tip)), "control_tip");
                break;
        }
    }

    private void initControlAPICall() {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN)) &&
                sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
            ParamModel paramModel = new ParamModel();
//            paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
           /* controlViewModel.getControlViewmodel(token, paramModel).observe((LifecycleOwner) mContext, controlResponseModelApiResponse ->
                    controlResponse(controlResponseModelApiResponse));*/
            controlRepository.getControlData(token, responseInterface);
            runnable = new Runnable() {
                @Override
                public void run() {
                    controlRepository.getControlData(token, responseInterface);
                    handler.postDelayed(this, delay);
                }
            };
            handler.postDelayed(runnable, delay);
        }
    }

    private void controlAPICall() {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN)) &&
                sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
            ParamModel paramModel = new ParamModel();
//            paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
            controlRepository.getControlData(token, responseInterface);
        }
    }

    private void callPublishChargeModeMileageAPI() {
        ParamModel paramModel = new ParamModel();
        if (charge_noramlly_rbtn.isChecked() || disable_charge_rbtn.isChecked() || control_charge_rbtn.isChecked()) {
            if (controlResponseModel != null) {
                String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
//                paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                paramModel.setDeviceId(controlResponseModel.getDeviceId());
                paramModel.setChargeMode(updateChargeMode);
                if (control_charge_rbtn.isChecked()) {
                    paramModel.setCurrent(Utils.roundTwoDecimals(charge_seekbar.getProgress() * 0.1f));
                    paramModel.setPower(Utils.roundTwoDecimals(power_seekbar.getProgress() * 0.1f));
                }
                updateControlType = "charge_mode";
                Log.i(TAG, "callPublishChargeModeMileageAPI:current " + Utils.roundTwoDecimals(paramModel.getCurrent())
                        + " power:" + Utils.roundTwoDecimals(paramModel.getPower()));
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                /*controlViewModel.getApplyPublishChargeModeViewmodel(token,
                        paramModel).observe((LifecycleOwner) mContext, commonResponseModelApiResponse ->
                        applyControlResponse(commonResponseModelApiResponse, "charge_mode"));*/
                controlRepository.getApplyPublishChargeModeResponse(token, paramModel, this);
            }
        }
    }

    private void callApplyEvMileageAPI() {
        ParamModel paramModel = new ParamModel();
        if (selectedVehicleID < 0)
            selectedVehicleID = Integer.parseInt(controlResponseModel.getEvMileageStatus().getVehicleId());
        if (selectedVehicleID > 0) {
            if (controlResponseModel != null) {
                int ev_milage_seekbar_range = ev_milage_seekbar.getProgress();
                updateControlType = "ev_mileage";
                String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
                paramModel.setVehicleId(selectedVehicleID);
                paramModel.setDeviceId(controlResponseModel.getDeviceId());
                paramModel.setEvMileageNeeded(ev_milage_seekbar_range == 0 ? 1 : ev_milage_seekbar_range);
//                paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                paramModel.setEvStatus(ev_milage_switchcompat.isChecked() ? "Enabled" : "Disabled");
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                /*controlViewModel.getApplyEvMileageViewmodel(token,
                        paramModel).observe((LifecycleOwner) mContext, commonResponseModelApiResponse ->
                        applyControlResponse(commonResponseModelApiResponse, "ev_mileage"));*/
                controlRepository.getApplyEvMileageResponse(token, paramModel, this);
            }
        }
    }

    private void callApplyTimerAPI() {
        ParamModel paramModel = new ParamModel();
        if (controlResponseModel != null) {
            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
            if (tb_control_switchcompat.isChecked()) {
                String startTime = start_time_etext.getText().toString();
                String endTime = end_time_etext.getText().toString();
                if (startTime.length() == 0 && endTime.length() == 0) {
                    Utils.showOKCustomAlert(mContext, null, "Please enter time", "time_empty");
                } else {
                    startTime = startTime.length() > 0 ? startTime : "0";
                    endTime = endTime.length() > 0 ? endTime : "0";
                    if (Integer.parseInt(startTime) > 0 || Integer.parseInt(endTime) > 0) {
                        paramModel.setHour(Integer.parseInt(startTime.length() > 0 ? startTime : "0"));
                        paramModel.setMinute(Integer.parseInt(endTime.length() > 0 ? endTime : "0"));
                        paramModel.setDeviceId(controlResponseModel.getDeviceId());
//                        paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                        paramModel.setTimerstatus("Enabled");
                        updateControlType = "timer";
                        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                     /*   controlViewModel.getApplyTimerViewmodel("Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN),
                                paramModel).observe((LifecycleOwner) mContext, new Observer<ApiResponse<CommonResponseModel>>() {
                            @Override
                            public void onChanged(ApiResponse<CommonResponseModel> commonResponseModelApiResponse) {
                                applyControlResponse(commonResponseModelApiResponse, "timer");
                            }
                        }); */
                        controlRepository.getapplyTimeControlResponse(token, paramModel, responseInterface);
                    } else
                        Utils.showOKCustomAlert(mContext, null, "Please enter valid time", "time_empty");
                }

            } else {
                paramModel.setHour(0);
                paramModel.setMinute(0);
                paramModel.setDeviceId(controlResponseModel.getDeviceId());
//                paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                paramModel.setTimerstatus("Disabled");
                updateControlType = "timer";
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                controlRepository.getapplyTimeControlResponse(token, paramModel, responseInterface);
                /*controlViewModel.getApplyTimerViewmodel("Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN),
                        paramModel).observe((LifecycleOwner) mContext, new Observer<ApiResponse<CommonResponseModel>>() {
                    @Override
                    public void onChanged(ApiResponse<CommonResponseModel> commonResponseModelApiResponse) {
                        applyControlResponse(commonResponseModelApiResponse, "timer");
                    }
                });*/
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void updateUI(ControlResponseModel controlResponseModel) {
        try {
            controlTypes = new StringBuilder();
            controlTypes.append("Manual");
            if (controlResponseModel.getStatus().equalsIgnoreCase("1")) {
                this.controlResponseModel = controlResponseModel;
                if (controlResponseModel.isActiveSession()) {
                    electric_vehicle_etext.setEnabled(false);
                    control_charge_rbtn.setEnabled(true);
                    control_charge_rbtn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.hint_color, mContext.getTheme())));
                    control_charge_rbtn.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow_gradient, mContext.getTheme())));
                } else {
                    electric_vehicle_etext.setEnabled(true);
                    control_charge_rbtn.setEnabled(false);
                    control_charge_rbtn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.switch_bg_color, mContext.getTheme())));
                    control_charge_rbtn.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.lite_yellow)));
                }
                double initialPower = controlResponseModel.getPowerSliderMin();
                double initialCharge = (int) controlResponseModel.getCurrentSliderMin();
                double chargeVal = Utils.roundTwoDecimals(Double.parseDouble(controlResponseModel.getCurrent()));
                double powerVal = Utils.roundTwoDecimals(Double.parseDouble(controlResponseModel.getPower()));
                double chargeMax = Utils.roundTwoDecimals(Double.parseDouble(controlResponseModel.getMaxAmperage()));
                double powerMax = Utils.roundTwoDecimals(Double.parseDouble(controlResponseModel.getMaxPower()));
                double initialEMileage = (int) controlResponseModel.getMinEvRange();

                if (!TextUtils.isEmpty(controlResponseModel.getChargeMode())) {
                    if (updateChargeMode != null && !updateChargeMode.equalsIgnoreCase(controlResponseModel.getChargeMode())) {
                        Log.i(TAG, "updateUI:updateChargeMode " + updateChargeMode + " response:" +
                                controlResponseModel.getChargeMode());
                    } else {
                        switch (controlResponseModel.getChargeMode()) {
                            case "Disable":
                                disable_charge_rbtn.setChecked(true);
                                charge_noramlly_rbtn.setChecked(false);
                                control_charge_rbtn.setChecked(false);
                                updateChargeMode = "disable";
                                scaMode = "Disabled";
                                chargeMode = "Disable Charging";
                                control_seekbar_lay.setVisibility(View.GONE);
                                sca_type_val_tview.setText(chargeMode);
                                sca_type_val_tview.setBackgroundResource(R.drawable.bg_lite_pink);
                                break;
                            case "Enable":
                                charge_noramlly_rbtn.setChecked(true);
                                disable_charge_rbtn.setChecked(false);
                                control_charge_rbtn.setChecked(false);
                                updateChargeMode = "enable";
                                scaMode = "Enabled";
                                chargeMode = "Charge Normally";
                                control_seekbar_lay.setVisibility(View.GONE);
                                sca_type_val_tview.setText(chargeMode);
                                sca_type_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
                                break;
                            case "Control":
                                control_charge_rbtn.setChecked(true);
                                disable_charge_rbtn.setChecked(false);
                                charge_noramlly_rbtn.setChecked(false);
                                updateChargeMode = "Control";
                                scaMode = "Controlled";
                                chargeMode = "Controlled, " + (powerMax < 6 ? 6 : chargeVal) + " A";
                                control_seekbar_lay.setVisibility(View.VISIBLE);
                                sca_type_val_tview.setText(chargeMode);
                                sca_type_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
                                break;
                            case "false":
                                disable_charge_rbtn.setChecked(false);
                                charge_noramlly_rbtn.setChecked(false);
                                control_charge_rbtn.setChecked(false);
                                break;
                        }
                    }
                }

                control_charge_rbtn.setOnCheckedChangeListener((compoundButton, b) -> {
                    if (b) {
                        updateChargeMode = "Control";
                        control_seekbar_lay.setVisibility(View.VISIBLE);

                        control_charge_rbtn.setChecked(true);
                        disable_charge_rbtn.setChecked(false);
                        charge_noramlly_rbtn.setChecked(false);
                        updateChargeMode = "Control";
                        scaMode = "Controlled";
                        chargeMode = "Controlled, " + (powerMax < 6 ? 6 : chargeVal) + " A";
                        sca_type_val_tview.setText(chargeMode);
                        sca_type_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
                    }
                });

                charge_noramlly_rbtn.setOnCheckedChangeListener((compoundButton, b) -> {
                    if (b) {
                        updateChargeMode = "enable";
                        control_seekbar_lay.setVisibility(View.GONE);

                        charge_noramlly_rbtn.setChecked(true);
                        disable_charge_rbtn.setChecked(false);
                        control_charge_rbtn.setChecked(false);
                        updateChargeMode = "enable";
                        scaMode = "Enabled";
                        chargeMode = "Charge Normally";
                        sca_type_val_tview.setText(chargeMode);
                        sca_type_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
                    }
                });

                disable_charge_rbtn.setOnCheckedChangeListener((compoundButton, b) -> {
                    if (b) {
                        updateChargeMode = "disable";
                        control_seekbar_lay.setVisibility(View.GONE);

                        disable_charge_rbtn.setChecked(true);
                        charge_noramlly_rbtn.setChecked(false);
                        control_charge_rbtn.setChecked(false);
                        updateChargeMode = "disable";
                        scaMode = "Disabled";
                        chargeMode = "Disable Charging";
                        sca_type_val_tview.setText(chargeMode);
                        sca_type_val_tview.setBackgroundResource(R.drawable.bg_lite_pink);
                    }
                });

                double chargeCurrent = Utils.roundTwoDecimals(Double.parseDouble(controlResponseModel.getControlSCAData().getChargeCurrent()));
                double activePower = Utils.roundTwoDecimals(Double.parseDouble(controlResponseModel.getControlSCAData().getActivePower()));

                charge_tview.setText(String.valueOf(chargeCurrent));
                power_tview.setText(String.valueOf(activePower));
//            charge_seekbar.setEnabled(control_charge_rbtn.isChecked());
//            power_seekbar.setEnabled(control_charge_rbtn.isChecked());e

                if (powerMax > 6 || Double.parseDouble(controlResponseModel.getControlSCAData().getMaxAmperage()) > 6) {
                    seekbar_start_val.setText(controlResponseModel.getCurrentSliderMin() + "A");
                    seekbar_end_val.setText(chargeMax + "A");
                    charge_seekbar.setMax((int) (chargeMax * 10));

                    power_seekbar_start_val.setText(Utils.roundTwoDecimals(initialPower) + "W");
                    power_seekbar_end_val.setText(powerMax + "W");
                    power_seekbar.setMax((int) (powerMax * 10));
                    Log.i(TAG, "updateUI:chargeProgress " + chargeProgress + " charge_val: " + charge_val.getText().toString());
                    if (chargeProgress != null && chargeProgress.equalsIgnoreCase(charge_val.getText().toString())) {
                    } else {
                        if (chargeVal > 0)
                            charge_seekbar.setProgress((int) (chargeVal * 10));
                        else charge_seekbar.setProgress((int) (initialCharge * 10));


                        if (powerVal > 0)
                            power_seekbar.setProgress((int) (powerVal * 10));
                        else power_seekbar.setProgress((int) (initialPower * 10));

                        charge_val.setText(chargeVal + "A");
                        power_val.setText(powerVal + " W");
                    }
                } else {
                    seekbar_start_val.setText("6A");
                    seekbar_end_val.setText("6A");
                    charge_seekbar.setMax(6 * 10);
                    charge_seekbar.setProgress(6 * 10);

                    power_seekbar.setMax(6 * controlResponseModel.getRoundedLineVoltage());
                    power_seekbar.setProgress(6 * controlResponseModel.getRoundedLineVoltage());

                    power_seekbar_start_val.setText(6 * controlResponseModel.getRoundedLineVoltage() + "W");
                    power_seekbar_end_val.setText(6 * controlResponseModel.getRoundedLineVoltage() + "W");
                    charge_val.setText("6A");
                    power_val.setText(6 * controlResponseModel.getRoundedLineVoltage() + "W");

                    power_seekbar.setEnabled(false);
                    charge_seekbar.setEnabled(false);
                }
                charge_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        if (i < initialCharge * 10)
                            i = (int) initialCharge * 10;
                        float currentProgress = i * 0.1f;
                        String yourprogress = String.format("%.1f", currentProgress);
                        charge_val.setText(yourprogress + " A");
                        chargeProgress = charge_val.getText().toString(); // to validate progress change
                        Log.i(TAG, "onProgressChanged:charge_seekbar " + yourprogress);
                        Log.i(TAG, "onProgressChanged:charge_seekbar " + i + " chargeVal:" + (int) (chargeVal * 10));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        power_seekbar.setProgress((int) (charge_seekbar.getProgress() * Double.parseDouble(controlResponseModel.getLineVoltage())));
                        Log.i(TAG, "onStopTrackingTouch:powerProgress " + powerProgress + " charge_seekbar:" + charge_seekbar.getProgress());
                    }
                });

                power_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        if (i < initialPower * 10)
                            i = (int) initialPower * 10;
                        float powerRange = i * 0.1f;
                        String yourprogress = String.format("%.1f", powerRange);
                        Log.i(TAG, "onProgressChanged:power_seekbar " + yourprogress);
                        power_val.setText(yourprogress + " kW");
                        powerProgress = power_val.getText().toString(); // to validate progress change
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        charge_seekbar.setProgress((int) (power_seekbar.getProgress() / Double.parseDouble(controlResponseModel.getLineVoltage())));
                        Log.i(TAG, "onStopTrackingTouch:powerProgress " + powerProgress + " charge_seekbar:" + charge_seekbar.getProgress());
                    }
                });


                /*try {
                    if (chargeMode.equalsIgnoreCase("Disable Charging"))
                        sca_type_val_tview.setBackgroundResource(R.drawable.bg_lite_pink);
                    else
                        sca_type_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                sca_type_val_tview.setText(chargeMode);*/
                sca_mode_tview.setText(scaMode);

                vehicleListCount = controlResponseModel.getUserVehicles().size();

                if (vehicleListCount > 0) {
                    add_vehicle_rlay.setVisibility(View.GONE);
                    ic_ev_arrow.setVisibility(View.VISIBLE);
                    milage_val_tview.setVisibility(View.VISIBLE);
                    electricVehicleDetails = new String[controlResponseModel.getUserVehicles().size()];
                    electricVehicleDetailList = new ArrayList<>();
                    String vehicleDetail = null;
                    for (int i = 0; i < controlResponseModel.getUserVehicles().size(); i++) {
                        ControlResponseModel.UserVehiclesBean userVehiclesBean = controlResponseModel.getUserVehicles().get(i);
                        electricVehicleDetails[i] = userVehiclesBean.getYear() + " " + userVehiclesBean.getMake() + " " +
                                userVehiclesBean.getModel();
                        if (controlResponseModel.getEvMileageStatus() != null) {
                            if (Integer.parseInt(controlResponseModel.getEvMileageStatus().getVehicleId()) == controlResponseModel.getUserVehicles().get(i).getId()) {
                                vehicleDetail = userVehiclesBean.getYear() + " " + userVehiclesBean.getMake() + " " +
                                        userVehiclesBean.getModel();
                            }
                        }
                    }
                    Log.i(TAG, "updateUI:vehicleDetail " + vehicleDetail + " selectedVehicle:" + selectedVehicle);
                    if (selectedVehicle != null && !selectedVehicle.equalsIgnoreCase(vehicleDetail)) {
                    } else {
                        if (vehicleDetail != null) {
                            electric_vehicle_etext.setText(vehicleDetail);
                            eVehicle_lbl.setText(vehicleDetail);
                        }
                        int ev_mileage_needed = Integer.parseInt(controlResponseModel.getEvMileageStatus().getEvMileageNeeded());
                        if (ev_mileage_progress != -1 && ev_mileage_progress != ev_mileage_needed) {
                            ev_milage_sc_val.setText((ev_mileage_progress > 0 ? ev_mileage_progress : 1) + " miles");
                            ev_milage_sc_tot.setText("of Max " + controlResponseModel.getMaxEvRange() + " mi");
                            ev_milage_seekbar.setMax((int) Integer.parseInt(controlResponseModel.getMaxEvRange()));
                            ev_milage_seekbar.setProgress(ev_mileage_progress > 0 ? ev_mileage_progress : 1);
                            acc_ev_milage_sc_tot.setText("of " + (ev_mileage_progress > 0 ? ev_mileage_progress : 1) + " mi");
                            acc_ev_milage_progressbar.setMax((ev_mileage_progress > 0 ? ev_mileage_progress : 1) * 10);
                        } else {
                            ev_milage_sc_val.setText((ev_mileage_needed > 0 ? ev_mileage_needed : 1) + " miles");
                            ev_milage_sc_tot.setText("of Max " + controlResponseModel.getMaxEvRange() + " mi");
                            ev_milage_seekbar.setMax((int) Integer.parseInt(controlResponseModel.getMaxEvRange()));
                            ev_milage_seekbar.setProgress(ev_mileage_needed > 0 ? ev_mileage_needed : 1);
                            acc_ev_milage_sc_tot.setText("of " + (ev_mileage_needed > 0 ? ev_mileage_needed : 1) + " mi");
                            acc_ev_milage_progressbar.setMax((ev_mileage_needed > 0 ? ev_mileage_needed : 1) * 10);
                        }
                    }
                    electricVehicleDetailList.addAll(Arrays.asList(electricVehicleDetails));
                } else {
                    add_vehicle_rlay.setVisibility(View.VISIBLE);
                    ic_ev_arrow.setVisibility(View.GONE);
                    milage_val_tview.setVisibility(View.GONE);
                    ev_milage_lay.setVisibility(View.GONE);
                }

                if (controlResponseModel.getEvMileageStatus() != null) {
//
                    String evmileagestatus = controlResponseModel.getEvMileageStatus().getIsMileageControlEnabled();
                    if (evMileageStatus != null && !evMileageStatus.equalsIgnoreCase(evmileagestatus)) {
                    } else {
                        if (evmileagestatus.equalsIgnoreCase("Enabled")) {
                            controlTypes.append(", EV Mileage");
                            ev_milage_switchcompat.setChecked(true);
                        } else {
                            ev_milage_switchcompat.setChecked(false);
                            evMileageStatus = "Disabled";
                        }
                    }
                } else milage_val_tview.setText(getString(R.string.disable));

                if (ev_milage_switchcompat.isChecked()) {
                    evMileageStatus = "Enabled";
                    milage_val_tview.setText(getString(R.string.enable));
                    ev_milage_switchcompat_tview.setText(getString(R.string.enable));
                    milage_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
                } else {
                    evMileageStatus = "Disabled";
                    milage_val_tview.setText(getString(R.string.disable));
                    ev_milage_switchcompat_tview.setText(getString(R.string.disable));
                    milage_val_tview.setBackgroundResource(R.drawable.bg_lite_pink);
                }

                ev_milage_switchcompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            evMileageStatus = "Enabled";
                            milage_val_tview.setText(getString(R.string.enable));
                            ev_milage_switchcompat_tview.setText(getString(R.string.enable));
                            milage_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
                        } else {
                            evMileageStatus = "Disabled";
                            evMileageStatus = "Disabled";
                            milage_val_tview.setText(getString(R.string.disable));
                            ev_milage_switchcompat_tview.setText(getString(R.string.disable));
                            milage_val_tview.setBackgroundResource(R.drawable.bg_lite_pink);
                        }
                    }
                });

                if (controlResponseModel.getControlSCAData().getUnPluginTime().equalsIgnoreCase("N/A"))
                    acc_ev_milage_lay.setVisibility(View.VISIBLE);
                else acc_ev_milage_lay.setVisibility(View.GONE);


                electric_vehicle_etext.setOnTouchListener((view, motionEvent) -> {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        String existingVal = null;
                        if (electric_vehicle_etext.getText().toString().trim().length() > 0)
                            existingVal = electric_vehicle_etext.getText().toString();

                        try {
                            if (electricVehicleDetailList.size() > 0 && electricVehicleDetails.length > 0) {
                                Log.i(TAG, "onTouch:secret ques length " + electricVehicleDetails.length);
                                pickerDialog(mContext, electricVehicleDetails, indexPos - 2,
                                        electric_vehicle_etext, "Select Vehicle", existingVal, controlResponseModel.getUserVehicles());
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                });

                ev_milage_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        if (i < initialEMileage)
                            i = (int) initialEMileage;
                        ev_mileage_progress = i;
                        ev_milage_sc_val.setText(i + " miles");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        acc_ev_milage_progressbar.setMax((seekBar.getProgress() * 10));
                        acc_ev_milage_sc_tot.setText("of " + seekBar.getProgress() + " mi");
                    }
                });

                if (electric_vehicle_etext.getText().toString().length() > 0)
                    eVehicle_lbl.setText(electric_vehicle_etext.getText().toString());

                acc_ev_milage_progressbar.setProgress((int) (Double.parseDouble(controlResponseModel.getAccumulatedEvMileage()) * 10));
                //            acc_ev_milage_progressbar.setEnabled(true);
                acc_ev_milage_progressbar.setClickable(false);
                acc_ev_milage_progressbar.setFocusable(false);
                acc_ev_milage_sc_val.setText(controlResponseModel.getAccumulatedEvMileage() + " mi");

                if (controlResponseModel.getTimerControlStatus() != null) {
                    String timercontrolstatus = controlResponseModel.getTimerControlStatus().getIsTimerEnabled();
                    if (timerControlStatus != null && !timerControlStatus.equalsIgnoreCase(timercontrolstatus)) {
                        Log.i(TAG, "updateUI:timerControlStatus " + timerControlStatus + " response:" + timerControlStatus);
                    } else {
                        if (controlResponseModel.getTimerControlStatus().getIsTimerEnabled().equalsIgnoreCase("Enabled")) {
                            controlTypes.append(", Timer");
                            tb_control_switchcompat.setChecked(true);
                            /*timerControlStatus = "Enabled";
                            start_time_etext.setEnabled(true);
                            end_time_etext.setEnabled(true);
                            start_timer_layout.setBackgroundResource(R.drawable.bg_edittext);
                            end_timer_layout.setBackgroundResource(R.drawable.bg_edittext);
                            timer_val_tview.setText(getString(R.string.enable));
                            tb_control_switchcompat_tview.setText(getString(R.string.enable));
                            timer_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);*/
                        } else {
                            timerControlStatus = "Disabled";
                            tb_control_switchcompat.setChecked(false);
                            /*start_time_etext.setText("");
                            end_time_etext.setText("");
                            time_remaing_tview.setText("00:00:00");
                            start_time_etext.setEnabled(false);
                            end_time_etext.setEnabled(false);
                            start_timer_layout.setBackgroundResource(R.drawable.bg_edittext_gray);
                            end_timer_layout.setBackgroundResource(R.drawable.bg_edittext_gray);
                            timer_val_tview.setText(getString(R.string.disable));
                            tb_control_switchcompat_tview.setText(getString(R.string.disable));
                            timer_val_tview.setBackgroundResource(R.drawable.bg_lite_pink);*/
                        }
                    }

                    if (tb_control_switchcompat.isChecked()) {
                        timerControlStatus = "Enabled";
                        start_time_etext.setEnabled(true);
                        end_time_etext.setEnabled(true);
                        start_timer_layout.setBackgroundResource(R.drawable.bg_edittext);
                        end_timer_layout.setBackgroundResource(R.drawable.bg_edittext);
                        timer_val_tview.setText(getString(R.string.enable));
                        tb_control_switchcompat_tview.setText(getString(R.string.enable));
                        timer_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
                        Log.i(TAG, "updateUI:hour " + hour + " exist hour: " + start_time_etext.getText().toString());
                        int totalDuration, durationHour = 0, durationMin = 0;
                        if (Integer.parseInt(controlResponseModel.getTimerControlStatus().getDuration()) > 0) {
                            totalDuration = Integer.parseInt(controlResponseModel.getTimerControlStatus().getDuration());
                            durationHour = totalDuration / 60;
                            durationMin = totalDuration % 60;

                            if (tempDuration != -1 && tempDuration != totalDuration) {
                                if (countDownTimer != null) {
                                    isCountDownTimerRunning = false;
                                    countDownTimer.cancel();
                                }
                            }
                            tempDuration = totalDuration;
                        }
                        if ((hour != null && !hour.equalsIgnoreCase(String.valueOf(durationHour))) ||
                                (min != null && !min.equalsIgnoreCase(String.valueOf(durationMin)))) {
                            start_time_etext.setText(hour);
                            end_time_etext.setText(min);
                        } else {
                            if (Integer.parseInt(controlResponseModel.getTimerControlStatus().getDuration()) > 0) {
//                                int totalDuration = Integer.parseInt(controlResponseModel.getTimerControlStatus().getDuration());
//                                int durationHour = totalDuration / 60;
//                                int durationMin = totalDuration % 60;
                                start_time_etext.setText(String.valueOf(durationHour));
                                end_time_etext.setText(String.valueOf(durationMin));
                                /*if (tempDuration != -1 && tempDuration != totalDuration) {
                                    if (countDownTimer != null) {
                                        isCountDownTimerRunning = false;
                                        countDownTimer.cancel();
                                    }
                                }
                                tempDuration = totalDuration;*/
                            } else {
                                start_time_etext.setText("");
                                end_time_etext.setText("");
                            }
                        }
                    } else {
                        timerControlStatus = "Disabled";
                        start_time_etext.setText("");
                        end_time_etext.setText("");
                        time_remaing_tview.setText("00:00:00");
                        start_time_etext.setEnabled(false);
                        end_time_etext.setEnabled(false);
                        start_timer_layout.setBackgroundResource(R.drawable.bg_edittext_gray);
                        end_timer_layout.setBackgroundResource(R.drawable.bg_edittext_gray);
                        timer_val_tview.setText(getString(R.string.disable));
                        tb_control_switchcompat_tview.setText(getString(R.string.disable));
                        timer_val_tview.setBackgroundResource(R.drawable.bg_lite_pink);
                    }

                    tb_control_switchcompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                timerControlStatus = "Enabled";
                                start_time_etext.setEnabled(true);
                                end_time_etext.setEnabled(true);
                                start_timer_layout.setBackgroundResource(R.drawable.bg_edittext);
                                end_timer_layout.setBackgroundResource(R.drawable.bg_edittext);
                                timer_val_tview.setText(getString(R.string.enable));
                                tb_control_switchcompat_tview.setText(getString(R.string.enable));
                                timer_val_tview.setBackgroundResource(R.drawable.bg_lite_yellow_gradient);
                            } else {
                                timerControlStatus = "Disabled";
                                start_time_etext.setText("");
                                end_time_etext.setText("");
                                time_remaing_tview.setText("00:00:00");
                                start_time_etext.setEnabled(false);
                                end_time_etext.setEnabled(false);
                                start_timer_layout.setBackgroundResource(R.drawable.bg_edittext_gray);
                                end_timer_layout.setBackgroundResource(R.drawable.bg_edittext_gray);
                                timer_val_tview.setText(getString(R.string.disable));
                                tb_control_switchcompat_tview.setText(getString(R.string.disable));
                                timer_val_tview.setBackgroundResource(R.drawable.bg_lite_pink);
                            }
                        }
                    });

                    if (Integer.parseInt(controlResponseModel.getTimerMinutesRemaining()) > 0) {
                        startCountdownTimer(Integer.parseInt(controlResponseModel.getTimerMinutesRemaining()));
                    } else {
                        /*start_time_etext.setText("");
                        end_time_etext.setText("");*/
                        time_remaing_tview.setText("00:00:00");
                        if (isCountDownTimerRunning)
                            countDownTimer.cancel();
                    }
                } else timer_val_tview.setText(getString(R.string.disable));
                control_type_tview.setText(controlTypes.toString());

            }
            /*else if (controlResponseModel.getError().equalsIgnoreCase("No Adapter")) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                Utils.addDeviceCustomAlert(mContext, null, controlResponseModel.getMessage(), TAG, R.id.action_bottom_menu_control_to_devicesFragment);
            } else
                Utils.showCustomAlert(mContext, getString(R.string.alert), controlResponseModel.getError(), "control_failed");*/
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startCountdownTimer(int totalSeconds) {
//        if (isCountDownTimerRunning)
//            countDownTimer.cancel();
        if (!isCountDownTimerRunning && totalSeconds > 0) {
            countDownTimer = new CountDownTimer(TimeUnit.SECONDS.toMillis(totalSeconds), 1000) {
                @Override
                public void onTick(long l) {
                    isCountDownTimerRunning = true;
//                    int minutes = (int) ((l / (1000 * 60)) % 60);
//                    int hours = (int) ((l / (1000 * 60 * 60)) % 24);
                    Log.i(TAG, "onTick:hours " + TimeUnit.MILLISECONDS.toHours(l) % 60 +
                            " mins: " + TimeUnit.MILLISECONDS.toMinutes(l) % 60 +
                            " sec: " + TimeUnit.MILLISECONDS.toSeconds(l) % 60);
                    try {
                        time_remaing_tview.setText(String.format("%02d:%02d:%02d",
                                TimeUnit.MILLISECONDS.toHours(l) % 60, TimeUnit.MILLISECONDS.toMinutes(l) % 60,
                                TimeUnit.MILLISECONDS.toSeconds(l) % 60));
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    isCountDownTimerRunning = false;
                    toastMessage("Countdown finished!");
                    time_remaing_tview.setText("00:00:00");
                    start_time_etext.setText("");
                    end_time_etext.setText("");
                }
            };
            if (controlResponseModel.isActiveSession())
                countDownTimer.start();
            else time_remaing_tview.setText(controlResponseModel.getTimerRemaining());
        }
    }

    private void evMileageChangeVehiclelUpdate(EvMileageChangeVehicleResponseModel evMileageChangeVehicleResponseModel) {
        if (evMileageChangeVehicleResponseModel.getStatus().equalsIgnoreCase("1")) {
            ev_milage_sc_tot.setText("of Max" + evMileageChangeVehicleResponseModel.getMaxEvRange().getMaxRange() + " mi");
            ev_milage_sc_val.setText(evMileageChangeVehicleResponseModel.getMaxEvRange().getAccEvVal() + " miles");
            acc_ev_milage_progressbar.setProgress((int) (evMileageChangeVehicleResponseModel.getMaxEvRange().getAccEvVal() * 10));
            ev_milage_seekbar.setMax(Integer.parseInt(evMileageChangeVehicleResponseModel.getMaxEvRange().getMaxRange()));
            eVehicle_lbl.setText(vehicleName);
        } else
            Utils.showOKCustomAlert(mContext, null, evMileageChangeVehicleResponseModel.getMessage(), "mileage_failed");
    }

    private void controlResponse(ApiResponse<ControlResponseModel> controlResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        ControlResponseModel controlResponseModel = controlResponseModelApiResponse.getData();
        switch (controlResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (controlResponseModel != null) {
                    if (Utils.getFunctionalFragment(getActivity(), ((BottomMenuActivity) mContext).getSupportFragmentManager(), getClass().getSimpleName().toString())) {
//                        updateUI(controlResponseModel);
                    }
                } else if (controlResponseModel.getError().equals("TOKEN_INVALID"))
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                else
                    Utils.showCustomAlert(getContext(), getString(R.string.alert), controlResponseModelApiResponse.getData().getError(), "control_failed");
                break;

            case ERROR:
                Log.i(TAG, "controlResponse: ERROR");
                Utils.handleErrorResponse(controlResponseModelApiResponse.getError(), mContext);
                break;
            case FAILURE:
                String failureResponse = Utils.getConvertedErrorBody(controlResponseModelApiResponse.getFailureResponse());
                Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "control_fail");
                break;
            default:
                break;
        }
    }

    private void evMileageChangeVehicleResponse(ApiResponse<EvMileageChangeVehicleResponseModel> evMileageChangeVehicleApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        EvMileageChangeVehicleResponseModel evMileageChangeVehicleResponseModel = evMileageChangeVehicleApiResponse.getData();
        switch (evMileageChangeVehicleApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (evMileageChangeVehicleResponseModel != null)
                    evMileageChangeVehiclelUpdate(evMileageChangeVehicleResponseModel);
                else if (evMileageChangeVehicleResponseModel.getError().equals("TOKEN_INVALID"))
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                else
                    Utils.showCustomAlert(getContext(), getString(R.string.alert), evMileageChangeVehicleApiResponse.getData().getError(), "AddElectricVehicle");
                break;

            case ERROR:
                Utils.handleErrorResponse(evMileageChangeVehicleApiResponse.getError(), mContext);
                break;
            case FAILURE:
                String failureResponse = Utils.getConvertedErrorBody(evMileageChangeVehicleApiResponse.getFailureResponse());
                Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "addelectricvehicleFragment");
                break;

            default:
                break;
        }
    }

    private void applyControlResponse(ApiResponse<CommonResponseModel> commonResponseModelApiResponse, String from) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        CommonResponseModel commonResponseModel = commonResponseModelApiResponse.getData();
        switch (commonResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (commonResponseModel.getStatus().equalsIgnoreCase("1")) {
                    if (from.equals("timer") && isCountDownTimerRunning) {
                        isCountDownTimerRunning = false;
                        countDownTimer.cancel();
                    }
                    controlAPICall();
                } else if (commonResponseModel.getError().equals("TOKEN_INVALID"))
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                else
                    Utils.showCustomAlert(getContext(), getString(R.string.alert), commonResponseModelApiResponse.getData().getError(), "AddElectricVehicle");
                break;

            case ERROR:
                Utils.handleErrorResponse(commonResponseModelApiResponse.getError(), mContext);
                break;
            case FAILURE:
                String failureResponse = Utils.getConvertedErrorBody(commonResponseModelApiResponse.getFailureResponse());
                Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "addelectricvehicleFragment");
                break;

            default:
                break;
        }
    }

    /**
     * display the dialog which contains vehicles
     *
     * @param existingValue will shown when value is available
     * @param vehiclesBeans
     */
    private void pickerDialog(final Context context, String[] list, int index, EditText eText, String pickerType,
                              String existingValue, List<ControlResponseModel.UserVehiclesBean> vehiclesBeans) {
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
            if (itemName != null && !TextUtils.isEmpty(itemName)) {
                eText.setText(itemName);
                selectedVehicle = itemName;
                if (pickerType.equals("Select Vehicle")) {
                    for (int i = 0; i < vehiclesBeans.size(); i++) {
                        ControlResponseModel.UserVehiclesBean userVehiclesBean = vehiclesBeans.get(i);
                        String vehicle = userVehiclesBean.getYear() + " " + userVehiclesBean.getMake() + " " +
                                userVehiclesBean.getModel();
                        if (itemName.equalsIgnoreCase(vehicle))
                            selectedVehicleID = vehiclesBeans.get(i).getId();
                    }
                    if (selectedVehicleID > 0) {
                        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                        ParamModel paramModel = new ParamModel();
                        paramModel.setVehicleId(selectedVehicleID);
                        paramModel.setActiveSession(String.valueOf(controlResponseModel.isActiveSession()));
                        paramModel.setDeviceId(controlResponseModel.getDeviceId());
                        vehicleName = itemName;
                        String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
                        controlViewModel.getEVMileageChangeVehicleViewmodel(token,
                                controlResponseModel.getDeviceId(), String.valueOf(controlResponseModel.isActiveSession()),
                                String.valueOf(selectedVehicleID)).observe((LifecycleOwner) mContext, new Observer<ApiResponse<EvMileageChangeVehicleResponseModel>>() {
                            @Override
                            public void onChanged(ApiResponse<EvMileageChangeVehicleResponseModel> evMileageChangeVehicleResponseModelApiResponse) {
                                evMileageChangeVehicleResponse(evMileageChangeVehicleResponseModelApiResponse);
                            }
                        });
                    }
                }
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

    /**
     * Configured and load the webview
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebview(WebView webView, String webViewURL) {
        try {
            if (webViewURL != null) {
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                webSettings.setAllowFileAccessFromFileURLs(true);
                webSettings.setAllowUniversalAccessFromFileURLs(true);
                webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
                webSettings.setAllowFileAccess(true);
                webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
                webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
//                webview.post(() -> {

                webView.loadUrl(webViewURL);

//                });

                webView.setWebChromeClient(new WebChromeClient());
                webView.setWebViewClient(new CustomWebView());
            }
        } catch (Exception e) {
            Log.i(TAG, "loadWebview: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(Object data) {
        dismissProgress();
        if (data != null) {
            if (data instanceof ControlResponseModel) {
                ControlResponseModel controlResponseModel = (ControlResponseModel) data;
                if (controlResponseModel.getStatus().equalsIgnoreCase("1")) {
                    restartHandler();
                    if (Utils.getFunctionalFragment(getActivity(), ((BottomMenuActivity) mContext).getSupportFragmentManager(), getClass().getSimpleName().toString()))
                        updateUI(controlResponseModel);
                } else if (controlResponseModel.getError().equals("TOKEN_INVALID")) {
                    stopHandler();
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                } else if (controlResponseModel.getError().equalsIgnoreCase("No Adapter")) {
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                    stopHandler();
                    Utils.addDeviceCustomAlert(mContext, null, controlResponseModel.getMessage(), TAG, R.id.action_bottom_menu_control_to_devicesFragment);
                } else {
                    stopHandler();
                    Utils.showCustomAlert(getContext(), getString(R.string.alert), controlResponseModel.getError(), "control_failed");
                }
            } else if (data instanceof CommonResponseModel) {
                CommonResponseModel commonResponseModel = (CommonResponseModel) data;
                applyControlResponseRe(commonResponseModel, updateControlType);
            } else {
                stopHandler();
                Utils.showCustomAlert(getContext(), getString(R.string.alert), controlResponseModel.getError(), "control_failed");
            }
        }
    }

    private void applyControlResponseRe(CommonResponseModel commonResponseModel, String from) {
        if (commonResponseModel.getStatus().equalsIgnoreCase("1")) {
            toastMessage(commonResponseModel.getSuccess());
            if (from.equals("timer") && isCountDownTimerRunning) {
                isCountDownTimerRunning = false;
                countDownTimer.cancel();
            }
            controlAPICall();
        } else
            Utils.showCustomAlert(getContext(), null, controlResponseModel.getError(), "control_failed");
    }

    @Override
    public void onSuccess(List data) {

    }

    @Override
    public void onError(Throwable throwable) {
        dismissProgress();
        Utils.handleErrorResponse(throwable, mContext);
        stopHandler();
    }

    @Override
    public void onFailure(Response response) {
        dismissProgress();
        String failureResponse = Utils.getConvertedErrorBody(response);
        Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "control_fail");
        stopHandler();
    }

    private void dismissProgress() {
        if (customProgressDialog != null)
            customProgressDialog.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
