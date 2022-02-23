package com.example.structure.ui.control_evse;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.structure.R;
import com.example.structure.data.models.ControlEVSEResponseModel;
import com.example.structure.data.models.EVSEModeStatusResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.repository.ControlEVSERepository;
import com.example.structure.retrofit.ControlEVSEWebService;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.ui.control_evse.adapter.ControlEVSEAdapter;
import com.example.structure.ui.control_evse.listener.EVSEPortListener;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.ResponseInterface;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.ControlEVSEViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import retrofit2.Response;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ControlEVSEFragment extends BaseFragment implements EVSEPortListener, ResponseInterface {
    ControlEVSEAdapter controlEVSEAdapter;
    @Inject
    ControlEVSEViewModel controlEVSEViewModel;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    ControlEVSEWebService controlEVSEWebService;
    @Inject
    ControlEVSERepository controlEVSERepository;
    @BindView(R.id.title_lbl)
    TextView title_lbl;
    @BindView(R.id.settings_img)
    ImageView settings_img;
    @BindView(R.id.ic_back_arrow)
    ImageView ic_back_arrow;
    @BindView(R.id.control_evse_parent_layout)
    ConstraintLayout control_evse_parent_layout;
    @BindView(R.id.control_evse_top_lay)
    LinearLayout control_evse_top_lay;
    @BindView(R.id.control_evse_port_lay)
    RelativeLayout control_evse_port_lay;
    @BindView(R.id.control_evse_port_lbl)
    TextView control_evse_port_lbl;
    @BindView(R.id.control_evse_port_tview)
    TextView control_evse_port_tview;
    @BindView(R.id.control_evse_type_lay)
    LinearLayout control_evse_type_lay;
    @BindView(R.id.control_evse_type_lbl)
    TextView control_evse_type_lbl;
    @BindView(R.id.control_evse_type_tview)
    TextView control_evse_type_tview;
    @BindView(R.id.evse_mode_lay)
    LinearLayout evse_mode_lay;
    @BindView(R.id.evse_mode_lbl)
    TextView evse_mode_lbl;
    @BindView(R.id.evse_mode_tview)
    TextView evse_mode_tview;
    @BindView(R.id.evse_type_lay)
    LinearLayout evse_type_lay;
    @BindView(R.id.evse_type_rlay)
    RelativeLayout evse_type_rlay;
    @BindView(R.id.evse_type_img)
    ImageView evse_type_img;
    @BindView(R.id.evse_type_tview)
    TextView evse_type_tview;
    @BindView(R.id.ic_manual_arrow)
    ImageView ic_manual_arrow;
    @BindView(R.id.evse_type_val_tview)
    TextView evse_type_val_tview;
    @BindView(R.id.evse_control_lay)
    LinearLayout evse_control_lay;
    @BindView(R.id.evse_charge_lbl)
    TextView evse_charge_lbl;
    @BindView(R.id.control_evse_mode_lay)
    LinearLayout control_evse_mode_lay;
    @BindView(R.id.evse_mode_lbl2)
    TextView evse_mode_lbl2;
    @BindView(R.id.evse_mode_switchcompat)
    SwitchCompat evse_mode_switchcompat;
    @BindView(R.id.evse_mode_switchcompat_tview)
    TextView evse_mode_switchcompat_tview;
    @BindView(R.id.remote_start_btn)
    Button remote_start_btn;
    @BindView(R.id.remote_stop_btn)
    Button remote_stop_btn;

    @BindView(R.id.start_btn_progress_llay)
    LinearLayout start_btn_progress_llay;
    @BindView(R.id.start_btn_progress_img)
    ImageView start_btn_progress_img;
    @BindView(R.id.start_btn_progress_tview)
    TextView start_btn_progress_tview;
    @BindView(R.id.stop_btn_progress_llay)
    LinearLayout stop_btn_progress_llay;
    @BindView(R.id.stop_btn_progress_img)
    ImageView stop_btn_progress_img;
    @BindView(R.id.stop_btn_progress_tview)
    TextView stop_btn_progress_tview;

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private CustomProgressDialog customProgressDialog;
    private ControlEVSEResponseModel controlEVSEResponseModel;
    private NavController navController;
    private ArrayList<String> evsePortList;
    private ArrayList<Integer> evsePortIdList;
    private String chargebox_id, token;
    private int indexPos = 0, currentPort, delay = 15000; // 15 sec;
    private Handler handler = new Handler(), buttonStatus_Handler;
    private Runnable runnable, buttonStatus_runnable;
    private ParamModel paramModel;
    private ResponseInterface responseInterface;
    private boolean is_remote_button_clicked;
    private Dialog dialog = null;

    public ControlEVSEFragment() {
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
        controlEVSEViewModel = ViewModelProviders.of(this, viewModelFactory).get(ControlEVSEViewModel.class);
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
        initcontrolEVSEAPICall();
    }

    @Override
    public void onPause() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        super.onPause();
    }

    @Override
    public void onStop() {
        try {
            stopHandler();
            stopButtonStatusHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    private void stopHandler() {
        try {
            Log.i(TAG, "stopHandler: ");
            handler.removeCallbacks(runnable); //stop handler when fragment is not visible
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopButtonStatusHandler() {
        try {
            buttonStatus_Handler.removeCallbacks(buttonStatus_runnable); //stop handler when fragment is not visible
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_control_evse, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();
        initUI();
        return view;
    }

    private void initUI() {
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();
        is_remote_button_clicked = false;
        responseInterface = this;
        buttonStatus_Handler = new Handler();

        token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
        paramModel = new ParamModel();
        navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);

        evse_mode_switchcompat.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                remote_start_btn.setVisibility(View.VISIBLE);
                remote_stop_btn.setVisibility(View.VISIBLE);
                setEvseModeAPICall("Enabled");
            } else {
                remote_start_btn.setVisibility(View.GONE);
                remote_stop_btn.setVisibility(View.GONE);
                setEvseModeAPICall("Disabled");
            }
        });
    }

    private void setHeader() {
        ic_back_arrow.setVisibility(View.GONE);
        settings_img.setVisibility(View.VISIBLE);
        title_lbl.setText(getString(R.string.control_evse));
    }

    private void setFont() {
        Utils.setBoldFonts(mContext, new TextView[]{remote_start_btn, remote_stop_btn, control_evse_port_tview, evse_mode_tview,
                control_evse_type_tview, evse_type_tview, evse_charge_lbl, evse_mode_lbl2});
        Utils.setRegularFonts(mContext, new TextView[]{title_lbl, control_evse_port_lbl, evse_mode_lbl, control_evse_type_lbl,
                evse_mode_switchcompat_tview, evse_type_val_tview, start_btn_progress_tview, stop_btn_progress_tview});
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.settings_img, R.id.remote_start_btn, R.id.remote_stop_btn, R.id.evse_type_rlay, R.id.ic_manual_arrow})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.settings_img:
                navController.navigate(R.id.action_bottom_menu_control_evse_to_navigation_settings);
                break;
            case R.id.evse_type_rlay:
                if (evse_control_lay.getVisibility() == View.VISIBLE) {
                    ic_manual_arrow.setRotation(-90);
//                    timer_based_lay.startAnimation(animHide);
                    evse_control_lay.setVisibility(View.GONE);
                } else {
                    ic_manual_arrow.setRotation(0);
                    evse_control_lay.setVisibility(View.VISIBLE);
//                    timer_based_lay.startAnimation(animShow);
                }
                break;
            case R.id.ic_manual_arrow:
                if (evse_control_lay.getVisibility() == View.VISIBLE) {
                    ic_manual_arrow.setRotation(-90);
//                    timer_based_lay.startAnimation(animHide);
                    evse_control_lay.setVisibility(View.GONE);
                } else {
                    ic_manual_arrow.setRotation(0);
                    evse_control_lay.setVisibility(View.VISIBLE);
//                    timer_based_lay.startAnimation(animShow);
                }
                break;
            case R.id.remote_start_btn:
                remoteOperationAPICall("remote_start");
                break;
            case R.id.remote_stop_btn:
                remoteOperationAPICall("remote_stop");
                break;
        }
    }

    /**
     * Control EVSE API
     **/
    private void initcontrolEVSEAPICall() {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN)) &&
                sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
//            paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
            if (currentPort > 0) {
//                paramModel.setChargeboxId(chargebox_id);
                paramModel.setPort_id(String.valueOf(currentPort));
                controlEVSERepository.getControlEVSEPortResponse(token, chargebox_id, String.valueOf(currentPort), responseInterface);
            } else controlEVSERepository.getControlEVSEResponse(token, responseInterface);
            Log.i(TAG, "controlEVSEAPICall:chargebox_id: " + chargebox_id + " portID: " + currentPort);
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);

            runnable = new Runnable() {
                @Override
                public void run() {
                    if (currentPort > 0) {
//                        paramModel.setChargeboxId(chargebox_id);
                        paramModel.setPort_id(String.valueOf(currentPort));
                        Log.i(TAG, "controlEVSEAPICall:selectedPort " + currentPort);
                        controlEVSERepository.getControlEVSEPortResponse(token, chargebox_id, String.valueOf(currentPort), responseInterface);
                    } else
                        controlEVSERepository.getControlEVSEResponse(token, responseInterface);
                    handler.postDelayed(this, delay);
                }
            };
            handler.postDelayed(runnable, delay);
        }
    }

    /**
     * Control EVSE API
     **/
    private void controlEVSEAPICall() {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN)) &&
                sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
//            paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
            Log.i(TAG, "controlEVSEAPICall:chargebox_id: " + chargebox_id + " portID: " + currentPort);
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            if (currentPort > 0) {
//                paramModel.setChargeboxId(chargebox_id);
                controlEVSERepository.getControlEVSEPortResponse(token, chargebox_id, String.valueOf(currentPort), this);
                paramModel.setPortId(String.valueOf(currentPort));
            } else controlEVSERepository.getControlEVSEResponse(token, this);
        }
    }

    /**
     * Control EVSE API
     *
     * @param evsemode set evse mode true/false
     **/
    private void setEvseModeAPICall(String evsemode) {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN)) &&
                sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
            ParamModel paramModel = new ParamModel();
//            paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
            paramModel.setStationId(String.valueOf(controlEVSEResponseModel.getControl_evse().getCurrentPort().getStationId()));
            paramModel.setPortId(String.valueOf(controlEVSEResponseModel.getControl_evse().getCurrentPort().getId()));
            paramModel.setEvseMode(evsemode);
            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            controlEVSEViewModel.getEVSEModeStatusViewmodel(token, paramModel).observe((LifecycleOwner) mContext, new Observer<ApiResponse<EVSEModeStatusResponseModel>>() {
                @Override
                public void onChanged(ApiResponse<EVSEModeStatusResponseModel> evseModeStatusResponseModelApiResponse) {
                    evseModeResponse(evseModeStatusResponseModelApiResponse);
                }
            });
        }
    }

    /**
     * Control EVSE API
     *
     * @param remoteOperation set remote start/stop
     **/
    private void remoteOperationAPICall(String remoteOperation) {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN)) &&
                sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
            if (remoteOperation.equalsIgnoreCase("remote_start")) {
                start_btn_progress_llay.setVisibility(View.VISIBLE);
                start_btn_progress_img.setImageResource(R.drawable.ic_loader);
                start_btn_progress_tview.setText(R.string.waiting_for_response);
            } else {
                stop_btn_progress_llay.setVisibility(View.VISIBLE);
                stop_btn_progress_img.setImageResource(R.drawable.ic_loader);
                stop_btn_progress_tview.setText(R.string.waiting_for_response);
            }
            ParamModel paramModel = new ParamModel();
//            paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
            paramModel.setStationId(String.valueOf(controlEVSEResponseModel.getControl_evse().getCurrentPort().getStationId()));
            paramModel.setPortId(String.valueOf(controlEVSEResponseModel.getControl_evse().getCurrentPort().getId()));
            paramModel.setConnectorId(String.valueOf(controlEVSEResponseModel.getControl_evse().getCurrentPort().getConnectorId()));
            paramModel.setRemoteOperation(remoteOperation);
            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            controlEVSEViewModel.getRemoteOperationViewmodel(token, paramModel).observe((LifecycleOwner) mContext, new Observer<ApiResponse<EVSEModeStatusResponseModel>>() {
                @Override
                public void onChanged(ApiResponse<EVSEModeStatusResponseModel> evseModeStatusResponseModelApiResponse) {
                    remoteOperationResponse(evseModeStatusResponseModelApiResponse);
                }
            });
        }
    }

    private void controlEVSEResponse(ApiResponse<ControlEVSEResponseModel> controlEVSEResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        ControlEVSEResponseModel controlEVSEResponseModel = controlEVSEResponseModelApiResponse.getData();
        switch (controlEVSEResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (controlEVSEResponseModel != null)
                    updateUI(controlEVSEResponseModel);
                else if (controlEVSEResponseModel.getError().equals("TOKEN_INVALID"))
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                else {
                    if (controlEVSEResponseModel.getError().equalsIgnoreCase("No Adapter")) {
                        if (dialog != null && dialog.isShowing())
                            dialog.dismiss();
                        dialog = Utils.addDeviceCustomAlert(mContext, null, controlEVSEResponseModel.getMessage(), TAG, R.id.action_bottom_menu_control_evse_to_devicesFragment);
                    } else
                        Utils.showCustomAlert(getContext(), getString(R.string.alert), controlEVSEResponseModelApiResponse.getData().getError(), "control_evse_failed");
                }
                break;
            case ERROR:
                Log.i(TAG, "controlResponse: ERROR");
                Utils.handleErrorResponse(controlEVSEResponseModelApiResponse.getError(), mContext);
                break;
            case FAILURE:
                String failureResponse = Utils.getConvertedErrorBody(controlEVSEResponseModelApiResponse.getFailureResponse());
                Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "control_fail");
                break;
            default:
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void updateUI(ControlEVSEResponseModel controlEVSEResponseModel) {
        try {
            if (controlEVSEResponseModel.getStatus().equalsIgnoreCase("1")) {
                this.controlEVSEResponseModel = controlEVSEResponseModel;

                if (controlEVSEResponseModel.getControl_evse() != null) {
                    setButtonStatus(controlEVSEResponseModel.getControl_evse().getActiveSessionStatus());
                    chargebox_id = controlEVSEResponseModel.getControl_evse().getOcpp().getChargeboxId();
                    int ocppSize = controlEVSEResponseModel.getControl_evse().getOcpp().getPorts().size();
                    if (ocppSize > 0) {
                        evsePortList = new ArrayList<>();
                        evsePortIdList = new ArrayList<>();
                        for (int i = 0; i < ocppSize; i++) {
                            int connector_id = controlEVSEResponseModel.getControl_evse().getOcpp().getPorts().get(i).getConnectorId();
                            if (connector_id > 0) {
                                evsePortList.add(chargebox_id + " | Port " + connector_id);
                                evsePortIdList.add(controlEVSEResponseModel.getControl_evse().getOcpp().getPorts().get(i).getId());
                            }
                        }
                    }
                    if (controlEVSEResponseModel.getControl_evse().getCurrentPort() != null) {
                        ControlEVSEResponseModel.ControlEvseBean.CurrentPortBean currentPortBean = controlEVSEResponseModel.getControl_evse().getCurrentPort();
                        currentPort = currentPortBean.getId();
                        control_evse_port_tview.setText(chargebox_id + " | Port " + currentPortBean.getConnectorId());
                    }
                    control_evse_port_tview.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            String existingVal = null;
                            if (control_evse_port_tview.getText().toString().trim().length() > 0)
                                existingVal = control_evse_port_tview.getText().toString();
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                if (evsePortList.size() > 0) {
                                    portSelectionDialog(mContext, evsePortList, evsePortIdList, existingVal);
                                }
                            }
                            return true;
                        }
                    });
                    if (controlEVSEResponseModel.getControl_evse().getControlEvseData() != null) {
                        ControlEVSEResponseModel.ControlEvseBean.ControlEvseDataBean controlEvseDataBean = controlEVSEResponseModel.getControl_evse().getControlEvseData();
                        evse_mode_tview.setText(controlEvseDataBean.getIsEvseModeEnabled());

                        if (controlEvseDataBean.getIsEvseModeEnabled().equalsIgnoreCase("Enabled")) {
                            evse_type_val_tview.setText(getString(R.string.enable));
                            evse_mode_switchcompat_tview.setText(getString(R.string.enable));
                            evse_type_val_tview.setBackground(getResources().getDrawable(R.drawable.bg_lite_yellow_gradient, mContext.getTheme()));
                            //                    evse_control_lay.setVisibility(View.VISIBLE);, mContext.getTheme()
                            evse_mode_switchcompat.setChecked(true);
                            remote_start_btn.setVisibility(View.VISIBLE);
                            remote_stop_btn.setVisibility(View.VISIBLE);
                        } else {
                            evse_type_val_tview.setBackground(getResources().getDrawable(R.drawable.bg_lite_pink, mContext.getTheme()));
                            //                    evse_control_lay.setVisibility(View.GONE);
                            evse_type_val_tview.setText(getString(R.string.disable));
                            evse_mode_switchcompat_tview.setText(getString(R.string.disable));
                            evse_mode_switchcompat.setChecked(false);
                            remote_start_btn.setVisibility(View.GONE);
                            remote_stop_btn.setVisibility(View.GONE);
                        }
                    }
                }
            } else
                Utils.showOKCustomAlert(mContext, null, controlEVSEResponseModel.getMessage(), "control_evse_fail");
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void evseModeResponse(ApiResponse<EVSEModeStatusResponseModel> evseModeStatusResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        EVSEModeStatusResponseModel evseModeStatusResponseModel = evseModeStatusResponseModelApiResponse.getData();
        switch (evseModeStatusResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (evseModeStatusResponseModel != null)
                    updatEevseModeUI(evseModeStatusResponseModel);
                else if (evseModeStatusResponseModel.getError().equals("TOKEN_INVALID"))
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                else
                    Utils.showCustomAlert(getContext(), getString(R.string.alert), evseModeStatusResponseModelApiResponse.getData().getMessage(), "control_evse_failed");
                break;
            case ERROR:
                Log.i(TAG, "controlResponse: ERROR");
                Utils.handleErrorResponse(evseModeStatusResponseModelApiResponse.getError(), mContext);
                break;
            case FAILURE:
                String failureResponse = Utils.getConvertedErrorBody(evseModeStatusResponseModelApiResponse.getFailureResponse());
                Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "control_fail");
                break;
            default:
                break;
        }
    }

    private void updatEevseModeUI(EVSEModeStatusResponseModel evseModeStatusResponseModel) {
        try {
            if (evseModeStatusResponseModel.getStatus().equalsIgnoreCase("1")) {
                setButtonStatus(evseModeStatusResponseModel.getActiveSessionStatus());
                evse_mode_tview.setText(evseModeStatusResponseModel.getUpdateEvseMode());
                if (evseModeStatusResponseModel.getUpdateEvseMode().equalsIgnoreCase("enabled")) {
                    evse_mode_switchcompat.setChecked(true);
                    evse_mode_switchcompat_tview.setText(getString(R.string.enable));
                    evse_type_val_tview.setText(getString(R.string.enable));
                    evse_type_val_tview.setBackground(getResources().getDrawable(R.drawable.bg_lite_yellow_gradient, mContext.getTheme()));
                } else {
                    evse_mode_switchcompat.setChecked(false);
                    evse_mode_switchcompat_tview.setText(getString(R.string.disable));
                    evse_type_val_tview.setText(getString(R.string.disable));
                    evse_type_val_tview.setBackground(getResources().getDrawable(R.drawable.bg_lite_pink, mContext.getTheme()));
                }
            } else
                Utils.showOKCustomAlert(mContext, null, controlEVSEResponseModel.getMessage(), "evse_failed");
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setButtonStatus(String buttonStatus) {
        if (!is_remote_button_clicked) {
            if (buttonStatus.equalsIgnoreCase("stop")) {
                remote_stop_btn.setEnabled(false);
                remote_start_btn.setEnabled(true);
                remote_stop_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_edittext_stroke, mContext.getTheme()));
                remote_stop_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.divider_view, mContext.getTheme())));
                remote_stop_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white, mContext.getTheme())));
                remote_start_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_lite_green, mContext.getTheme()));
                remote_start_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.control_evse_play, mContext.getTheme())));
                remote_start_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.yellow_gradient, mContext.getTheme())));
            } else {
                remote_start_btn.setEnabled(false);
                remote_stop_btn.setEnabled(true);
                remote_stop_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_lite_green, mContext.getTheme()));
                remote_stop_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.control_eve_stop, mContext.getTheme())));
                remote_stop_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.yellow_gradient, mContext.getTheme())));
                remote_start_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_edittext_stroke, mContext.getTheme()));
                remote_start_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.divider_view, mContext.getTheme())));
                remote_start_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white, mContext.getTheme())));
            }
        }
    }

    private void setRemoteButtonStatus(String buttonStatus) {
        is_remote_button_clicked = true;
        if (buttonStatus.contains("Remote started")) {
            if (buttonStatus.equalsIgnoreCase("Remote started")) {
                start_btn_progress_img.setImageResource(R.drawable.ic_tick_white);
                start_btn_progress_img.setColorFilter(getResources().getColor(R.color.white, mContext.getTheme()));
                start_btn_progress_tview.setText(R.string.success);
            } else {
                start_btn_progress_img.setImageResource(R.drawable.ic_cross_white);
                start_btn_progress_img.setColorFilter(getResources().getColor(R.color.white, mContext.getTheme()));
                start_btn_progress_tview.setText(R.string.failed_to_start);
            }
            remote_start_btn.setEnabled(false);
            buttonStatus_runnable = new Runnable() {
                @Override
                public void run() {
                    start_btn_progress_llay.setVisibility(View.GONE);
                    if (buttonStatus.equalsIgnoreCase("Remote started")) {
                        remote_start_btn.setEnabled(false);
                        remote_stop_btn.setEnabled(true);
                        remote_stop_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_lite_green, mContext.getTheme()));
                        remote_stop_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.control_eve_stop, mContext.getTheme())));
                        remote_stop_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.yellow_gradient, mContext.getTheme())));
                        remote_start_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_edittext_stroke, mContext.getTheme()));
                        remote_start_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.divider_view, mContext.getTheme())));
                        remote_start_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white, mContext.getTheme())));
                    } else {
                        remote_stop_btn.setEnabled(false);
                        remote_start_btn.setEnabled(true);
                        remote_stop_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_edittext_stroke, mContext.getTheme()));
                        remote_stop_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.divider_view, mContext.getTheme())));
                        remote_stop_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white, mContext.getTheme())));
                        remote_start_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_lite_green, mContext.getTheme()));
                        remote_start_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.control_evse_play, mContext.getTheme())));
                        remote_start_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.yellow_gradient, mContext.getTheme())));
                    }
                }
            };
            buttonStatus_Handler.postDelayed(buttonStatus_runnable, 5000); // 5 seconds
        } else {
            if (buttonStatus.equalsIgnoreCase("Remote stopped")) {
                stop_btn_progress_img.setImageResource(R.drawable.ic_tick_white);
                stop_btn_progress_img.setColorFilter(getResources().getColor(R.color.white, mContext.getTheme()));
                stop_btn_progress_tview.setText(R.string.success);
            } else {
                stop_btn_progress_img.setImageResource(R.drawable.ic_cross_white);
                stop_btn_progress_img.setColorFilter(getResources().getColor(R.color.white, mContext.getTheme()));
                stop_btn_progress_tview.setText(R.string.failed_to_stop);
            }
            remote_stop_btn.setEnabled(false);
            buttonStatus_runnable = new Runnable() {
                @Override
                public void run() {
                    stop_btn_progress_llay.setVisibility(View.GONE);
                    if (buttonStatus.equalsIgnoreCase("Remote stopped")) {
                        remote_stop_btn.setEnabled(false);
                        remote_start_btn.setEnabled(true);
                        remote_stop_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_edittext_stroke, mContext.getTheme()));
                        remote_stop_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.divider_view, mContext.getTheme())));
                        remote_stop_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white, mContext.getTheme())));
                        remote_start_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_lite_green, mContext.getTheme()));
                        remote_start_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.control_evse_play, mContext.getTheme())));
                        remote_start_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.yellow_gradient, mContext.getTheme())));
                    } else {
                        remote_start_btn.setEnabled(false);
                        remote_stop_btn.setEnabled(true);
                        remote_stop_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_lite_green, mContext.getTheme()));
                        remote_stop_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.control_eve_stop, mContext.getTheme())));
                        remote_stop_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.yellow_gradient, mContext.getTheme())));
                        remote_start_btn.setBackground(getResources().getDrawable(R.drawable.bg_edittext_edittext_stroke, mContext.getTheme()));
                        remote_start_btn.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.divider_view, mContext.getTheme())));
                        remote_start_btn.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white, mContext.getTheme())));
                    }
                }
            };
            buttonStatus_Handler.postDelayed(buttonStatus_runnable, 5000); // 5 sec
        }
    }

    private void remoteOperationResponse(ApiResponse<EVSEModeStatusResponseModel> evseModeStatusResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        EVSEModeStatusResponseModel evseModeStatusResponseModel = evseModeStatusResponseModelApiResponse.getData();
        switch (evseModeStatusResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (evseModeStatusResponseModel != null)
                    updateRemoteOperationResponse(evseModeStatusResponseModel);
                else if (evseModeStatusResponseModel.getError().equals("TOKEN_INVALID")) {
                    hideDisableButton();
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                } else {
                    hideDisableButton();
                    Utils.showCustomAlert(getContext(), getString(R.string.alert), evseModeStatusResponseModelApiResponse.getData().getError(), "control_evse_failed");
                }
                break;
            case ERROR:
                Log.i(TAG, "controlResponse: ERROR");
                hideDisableButton();
                Utils.handleErrorResponse(evseModeStatusResponseModelApiResponse.getError(), mContext);
                break;
            case FAILURE:
                hideDisableButton();
                String failureResponse = Utils.getConvertedErrorBody(evseModeStatusResponseModelApiResponse.getFailureResponse());
                Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "control_fail");
                break;
            default:
                break;
        }
    }

    private void updateRemoteOperationResponse(EVSEModeStatusResponseModel evseModeStatusResponseModel) {
        try {
            if (evseModeStatusResponseModel.getStatus().equalsIgnoreCase("0")) { // success response
                setRemoteButtonStatus(evseModeStatusResponseModel.getMessage());
            } else if (evseModeStatusResponseModel.getStatus().equalsIgnoreCase("1")) { // failed response
                setRemoteButtonStatus(evseModeStatusResponseModel.getMessage());
            } else
                Utils.showOKCustomAlert(mContext, null, controlEVSEResponseModel.getMessage(), "remote_operation_failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void portSelected(String portName, int portID) {
        Log.i(TAG, "portSelected:portName: " + portName + " portID: " + portID);
        currentPort = portID;
    }

    /**
     * display the dialog which contains evse port
     *
     * @param existingValue will shown when value is available
     */
    private void portSelectionDialog(final Context context, ArrayList<String> portNameList, ArrayList<Integer> portNameListId, String existingValue) {
        final Dialog dialog = new Dialog(context, R.style.custom_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.evse_port_list_layout);
        dialog.show();
        ImageView ic_close = dialog.findViewById(R.id.ic_close);
        TextView port_title_lbl = dialog.findViewById(R.id.port_title_lbl);
        RecyclerView port_rview = dialog.findViewById(R.id.port_rview);
        Button port_ok_btn = dialog.findViewById(R.id.port_ok_btn);
        Utils.setBoldFonts(context, new TextView[]{port_ok_btn, port_title_lbl});
        port_title_lbl.setText(getString(R.string.evse_id_port));
        ControlEVSEAdapter controlEVSEAdapter = new ControlEVSEAdapter(mContext, portNameList, portNameListId, existingValue, this);
        port_rview.setLayoutManager(new LinearLayoutManager(mContext, VERTICAL, false));
        port_rview.setHasFixedSize(true);
        port_rview.setNestedScrollingEnabled(false);
        port_rview.setItemAnimator(new DefaultItemAnimator());
        port_rview.setAdapter(controlEVSEAdapter);
        port_ok_btn.setOnClickListener(view -> {
            controlEVSEAPICall();
            dialog.cancel();
            dialog.dismiss();
        });
        ic_close.setOnClickListener(view -> {
            dialog.cancel();
            dialog.dismiss();
        });
    }

    private void dismissProgress() {
        if (customProgressDialog != null)
            customProgressDialog.dismiss();
    }

    private void hideDisableButton() {
        if (stop_btn_progress_llay.getVisibility() == View.VISIBLE)
            stop_btn_progress_llay.setVisibility(View.GONE);
        else if (start_btn_progress_llay.getVisibility() == View.VISIBLE)
            start_btn_progress_llay.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(Object data) {
        try {
            dismissProgress();
            ControlEVSEResponseModel controlEVSEResponseModel = (ControlEVSEResponseModel) data;
            if (controlEVSEResponseModel.getStatus().equalsIgnoreCase("1")) {
                restartHandler();
                if (Utils.getFunctionalFragment(getActivity(), ((BottomMenuActivity) mContext).getSupportFragmentManager(), getClass().getSimpleName().toString()))
                    updateUI(controlEVSEResponseModel);
            } else if (controlEVSEResponseModel.getError().equals("TOKEN_INVALID")) {
                stopHandler();
                Utils.showOKCustomAlert(mContext, null, "Session expired!",
                        "session_expired");
            } else if (controlEVSEResponseModel.getError().equalsIgnoreCase("No Adapter")) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                stopHandler();
                Utils.addDeviceCustomAlert(mContext, null, controlEVSEResponseModel.getMessage(), TAG, R.id.action_bottom_menu_control_evse_to_devicesFragment);
            } else {
                stopHandler();
                Utils.showCustomAlert(getContext(), getString(R.string.alert), controlEVSEResponseModel.getMessage(), "control_evse_failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(List data) {
        dismissProgress();
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
        Utils.showCustomAlert(mContext, getString(R.string.alert),
                Utils.getConvertedErrorBody(response), "control_evse_failed");
        stopHandler();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
