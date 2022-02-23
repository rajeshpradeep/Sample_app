package com.example.structure.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.structure.R;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.DashboardEvseResponseModel;
import com.example.structure.data.models.DashboardResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.repository.DashboardRepository;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.ui.dashboard.adapter.DashboardEvsePortListAdapter;
import com.example.structure.ui.dashboard.adapter.DashboardGridAdapter;
import com.example.structure.ui.dashboard.adapter.DashboardSCAAdapter;
import com.example.structure.ui.dashboard.listener.DashboardEVSEPortListener;
import com.example.structure.ui.login.LoginActivity;
import com.example.structure.ui.login.asyntask.LoginTask;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.ResponseInterface;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.DashboardViewModel;

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
public
class DashboardFragment extends BaseFragment implements ResponseInterface, DashboardEVSEPortListener, SwipeRefreshLayout.OnRefreshListener {
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    LoginTask loginTask;
    @BindView(R.id.title_lbl)
    TextView title_lbl;
    @BindView(R.id.settings_img)
    ImageView settings_img;
    @BindView(R.id.ic_back_arrow)
    ImageView ic_back_arrow;
    @BindView(R.id.dashboard_grid_rview)
    RecyclerView dashboard_grid_rview;
    @BindView(R.id.sca_rview)
    RecyclerView sca_rview;
    @BindView(R.id.sca_lbl)
    TextView sca_lbl;
    @BindView(R.id.sca_llay)
    LinearLayout sca_llay;
    @BindView(R.id.dashboard_swiperefresh)
    SwipeRefreshLayout dashboard_swiperefresh;
    @BindView(R.id.dashboard_port_llay)
    LinearLayout dashboard_port_llay;
    @BindView(R.id.dashboard_port_lbl)
    TextView dashboard_port_lbl;
    int delay = 15000; // 15 sec
    @Inject
    DashboardRepository dashboardRepository;
    ResponseInterface responseInterface;
    private Handler handler = new Handler();
    private Runnable runnable;
    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private DashboardViewModel dashboardViewModel;
    private CustomProgressDialog customProgressDialog;
    private String connector_id, chargebox_id, token;
    private ParamModel paramModel;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    /**
     * Initialize the Dashboard Viewmodel
     */
    private void configureViewModel() {
        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        configureDagger();
        configureViewModel();
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        responseInterface = this;
        fragmentManager = getFragmentManager();
        token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
        paramModel = new ParamModel();
        setStatusBarGradiant(getActivity());
//        initUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (sharedPreferencesUtil.getUserType(Constant.USER_TYPE) != null) {
            if (sharedPreferencesUtil.getUserType(Constant.USER_TYPE).equalsIgnoreCase("evse")) {
                dashboard_port_llay.setVisibility(View.VISIBLE);
                initDashboardEvseAPICall(false);
            } else {
                dashboard_port_llay.setVisibility(View.GONE);
                dashboardAPICall();
            }
        }
        ((BottomMenuActivity) mContext).setNavigationalVisibility(true);
    }

    @Override
    public void onStop() {
        stopHandler();
        super.onStop();
    }

    @Override
    public void onPause() {
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

    private void dashboardAPICall() {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
            if (sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
//                paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                Log.i(TAG, "dashboardAPICall: " + paramModel.getUser_id());
                dashboardRepository.dashboardAPIdData(token, responseInterface);
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        dashboardRepository.dashboardAPIdData(token, responseInterface);
                        handler.postDelayed(this, delay);
                    }
                };
                handler.postDelayed(runnable, delay);
            }
        }
    }

    /**
     * call the evse dashboard API
     */
    private void dashboardEvseAPICall(boolean isPortSelected) {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
            if (sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
//                paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                if (isPortSelected) {
//                    paramModel.setChargeboxId(chargebox_id);
//                    paramModel.setConnector_id(connector_id);

                    dashboardRepository.dashboardEvsePortAPIData(token, connector_id, responseInterface);
                } else dashboardRepository.dashboardEVSEAPIData(token, responseInterface);
            }
        }
    }

    /**
     * call the evse dashboard API
     */
    private void initDashboardEvseAPICall(boolean isPortSelected) {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
            if (sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
                String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
//                paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                if (isPortSelected) {
//                    paramModel.setChargeboxId(chargebox_id);
//                    paramModel.setConnector_id(connector_id);
                    dashboardRepository.dashboardEvsePortAPIData(token, connector_id, responseInterface);
                } else dashboardRepository.dashboardEVSEAPIData(token, responseInterface);

                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (isPortSelected) {
//                            paramModel.setChargeboxId(chargebox_id);
//                            paramModel.setConnector_id(connector_id);
                            dashboardRepository.dashboardEvsePortAPIData(token, connector_id, responseInterface);
                        } else dashboardRepository.dashboardEVSEAPIData(token, responseInterface);
                        handler.postDelayed(this, delay);
                    }
                };
                handler.postDelayed(runnable, delay);
            }
        }
    }

    /**
     * call the Resend Email API
     */
    private void resendEmailAPICall() {
        Log.i(TAG, "dashboardAPICall: " + paramModel.getUser_id());
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            new Handler().post(() -> {
                String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
                dashboardViewModel.resendEmailAPILiveData(token).observe((LifecycleOwner) mContext, new Observer<ApiResponse<CommonResponseModel>>() {
                    @Override
                    public void onChanged(ApiResponse<CommonResponseModel> commonResponseModelApiResponse) {
                        if (customProgressDialog != null && customProgressDialog.isShowing())
                            customProgressDialog.dismiss();
                        CommonResponseModel commonResponseModel = commonResponseModelApiResponse.getData();
                        switch (commonResponseModelApiResponse.getStatus()) {
                            case LOADING:
                                break;
                            case SUCCESS:
                                if (commonResponseModel.getStatus().equals("1")) {
                                    toastMessage(commonResponseModel.getSuccess() + " API Success");
                                    showOKCustomAlert(mContext, "Thank you!", getString(R.string.email_verification), "resend_mail");
                                } else if (commonResponseModel.getError().equals("TOKEN_INVALID")) {
                                    sharedPreferencesUtil.deleteAllSession();
                                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                                            "session_expired");
                                } else
                                    Utils.showCustomAlert(mContext, getString(R.string.alert), commonResponseModel.getError(),
                                            "dashboard_failed");
                                break;
                            case FAILURE:
                                Utils.showCustomAlert(mContext, getString(R.string.alert),
                                        Utils.getConvertedErrorBody(commonResponseModelApiResponse.getFailureResponse()), "dashboard_failed");
                                break;
                            case ERROR:
                                Utils.handleErrorResponse(commonResponseModelApiResponse.getError(), mContext);
                                break;
                        }
                    }
                });
            });
        }
    }

    private void initSCAUI(DashboardResponseModel dashboardResponseModel) {
        try {
            setHeader();
            setFont();
            dashboard_swiperefresh.setOnRefreshListener(this);
            ArrayList<String> dashboardGridKeyList = new ArrayList<>();
            dashboardGridKeyList.add(getString(R.string.charge_sec));
            dashboardGridKeyList.add(getString(R.string.ghg_save));
            dashboardGridKeyList.add(getString(R.string.monetary_save));
            dashboardGridKeyList.add(getString(R.string.gas_save));
//        if (sharedPreferencesUtil.getDeviceID(Constant.DEVICE_ID) != null) {
            DashboardResponseModel.DataBean scaDataBean = dashboardResponseModel.getData();
            String deviceID = sharedPreferencesUtil.getDeviceID(Constant.DEVICE_ID);
            /*String charge_sessions = Constant.GRAFANA_DASHBOARD_URL + deviceID + "&theme=light&panelId=9&kiosk&fullscreen";
            String ghg_savings = Constant.GRAFANA_DASHBOARD_URL + deviceID + "&theme=light&panelId=11&kiosk";
            String monetary_savings = Constant.GRAFANA_DASHBOARD_URL + deviceID + "&panelId=10&theme=light&kiosk";
            String gasoline_savings = Constant.GRAFANA_DASHBOARD_URL + deviceID + "&theme=light&panelId=18&kiosk";*/
            String charge_sessions = scaDataBean.getChargeSession();
            String ghg_savings = scaDataBean.getGhgSavings();
            String monetary_savings = scaDataBean.getMonetarySavings();
            String gasoline_savings = scaDataBean.getGasolineSavings();
            ArrayList<String> dashboardGridValueList = new ArrayList<>();
            dashboardGridValueList.add(charge_sessions);
            dashboardGridValueList.add(ghg_savings);
            dashboardGridValueList.add(monetary_savings);
            dashboardGridValueList.add(gasoline_savings);
            ArrayList<Integer> dashboardGridListImage = new ArrayList<>();
            dashboardGridListImage.add(R.drawable.ic_charge_green);
            dashboardGridListImage.add(R.drawable.ic_ghg);
            dashboardGridListImage.add(R.drawable.ic_monetary_savings);
            dashboardGridListImage.add(R.drawable.ic_gasoline);
            ArrayList<Integer> sca_Image_List = new ArrayList<>();
            sca_Image_List.add(R.drawable.ic_battery);
            sca_Image_List.add(R.drawable.ic_information_green);
            sca_Image_List.add(R.drawable.ic_electric_car);
            sca_Image_List.add(R.drawable.ic_energy_flash);
            ArrayList<String> sca_KeyList = new ArrayList<>();
            sca_KeyList.add("SCA SOC");
            sca_KeyList.add("Status");
            sca_KeyList.add("Power");
            sca_KeyList.add("Energy");

            /*String sca_soc = Constant.GRAFANA_CONTROL_URL + deviceID + "&panelId=13&theme=light&kiosk";
            String status = Constant.GRAFANA_CONTROL_URL + deviceID + "&theme=light&panelId=14&kiosk";
            String power = Constant.GRAFANA_CONTROL_URL + deviceID + "&panelId=15&theme=light&kiosk";
            String energy = Constant.GRAFANA_CONTROL_URL + deviceID + "&theme=light&panelId=16&kiosk";*/
            String sca_soc = scaDataBean.getScaSOC();
            String status = scaDataBean.getStatus();
            String power = scaDataBean.getPower();
            String energy = scaDataBean.getEnergy();
            ArrayList<String> sca_ValueList = new ArrayList<>();
            sca_ValueList.add(sca_soc);
            sca_ValueList.add(status);
            sca_ValueList.add(power);
            sca_ValueList.add(energy);
            if (sharedPreferencesUtil.getUserType(Constant.USER_TYPE).equalsIgnoreCase("EVSE")) {
                sca_KeyList.remove(0);
                sca_ValueList.remove(0);
                sca_Image_List.remove(0);
            }
            setDashboardGridRecyclerViewAdapter(dashboardGridKeyList, dashboardGridValueList, dashboardGridListImage);
            setDashboardSCARecyclerViewAdapter(sca_KeyList, sca_ValueList, sca_Image_List);
            if (dashboardResponseModel.getStation() != null) {
                DashboardResponseModel.StationBean stationBean = dashboardResponseModel.getStation();
                if (stationBean.getScaName() != null)
                    sca_lbl.setText(stationBean.getScaName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//            RecyclerView.LayoutParams dashboard_grid_rviewParams = (RecyclerView.LayoutParams) dashboard_grid_rview.getLayoutParams();
//            LinearLayout.LayoutParams sca_llayParams = (LinearLayout.LayoutParams) sca_llay.getLayoutParams();
//            sca_llayParams.width = dashboard_grid_rviewParams.width;
//        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initEVSEUI(DashboardEvseResponseModel dashboardEvseResponseModel) {
        try {
            int portSize=0;
            setHeader();
            setFont();
            dashboard_swiperefresh.setOnRefreshListener(this);
            ArrayList<String> dashboardGridKeyList = new ArrayList<>();
            dashboardGridKeyList.add(getString(R.string.charge_sec));
            dashboardGridKeyList.add(getString(R.string.ghg_save));
            dashboardGridKeyList.add(getString(R.string.monetary_save));
            dashboardGridKeyList.add(getString(R.string.gas_save));
//        if (sharedPreferencesUtil.getDeviceID(Constant.DEVICE_ID) != null) {
            DashboardEvseResponseModel.DataBean dataBean = dashboardEvseResponseModel.getData();
//            String deviceID = sharedPreferencesUtil.getDeviceID(Constant.DEVICE_ID);
            ArrayList<DashboardEvseResponseModel.PortsBean> portsBeanArrayList = new ArrayList<>();
            if(dashboardEvseResponseModel.getPorts()!=null) {
                 portSize = dashboardEvseResponseModel.getPorts().size();
                for (int i = 0; i < portSize; i++) {
                    DashboardEvseResponseModel.PortsBean portsBean = new DashboardEvseResponseModel.PortsBean();
                    portsBean = dashboardEvseResponseModel.getPorts().get(i);
                    if (portsBean.getConnectorId() > 0)
                        portsBeanArrayList.add(portsBean);
                }
            }
            /*String charge_sessions = Constant.GRAFANA_DASHBOARD_URL + deviceID + "&theme=light&panelId=9&kiosk&fullscreen";
            String ghg_savings = Constant.GRAFANA_DASHBOARD_URL + deviceID + "&theme=light&panelId=11&kiosk";
            String monetary_savings = Constant.GRAFANA_DASHBOARD_URL + deviceID + "&panelId=10&theme=light&kiosk";
            String gasoline_savings = Constant.GRAFANA_DASHBOARD_URL + deviceID + "&theme=light&panelId=18&kiosk";*/
            String charge_sessions = String.valueOf(dataBean.getChargeSession());
            String ghg_savings = dataBean.getGhgSavings();
            String monetary_savings = dataBean.getMonetarySavings();
            String gasoline_savings = dataBean.getGasolineSavings();
            ArrayList<String> dashboardGridValueList = new ArrayList<>();
            dashboardGridValueList.add(charge_sessions);
            dashboardGridValueList.add(ghg_savings);
            dashboardGridValueList.add(monetary_savings);
            dashboardGridValueList.add(gasoline_savings);
            ArrayList<Integer> dashboardGridListImage = new ArrayList<>();
            dashboardGridListImage.add(R.drawable.ic_charge_green);
            dashboardGridListImage.add(R.drawable.ic_ghg);
            dashboardGridListImage.add(R.drawable.ic_monetary_savings);
            dashboardGridListImage.add(R.drawable.ic_gasoline);
            ArrayList<Integer> sca_Image_List = new ArrayList<>();
//        sca_Image_List.add(R.drawable.ic_battery);
            sca_Image_List.add(R.drawable.ic_information_green);
            sca_Image_List.add(R.drawable.ic_electric_car);
            sca_Image_List.add(R.drawable.ic_energy_flash);
            ArrayList<String> sca_KeyList = new ArrayList<>();
//        sca_KeyList.add("SCA SOC");
            sca_KeyList.add("Status");
            sca_KeyList.add("Power");
            sca_KeyList.add("Energy");

            /*String sca_soc = Constant.GRAFANA_CONTROL_URL + deviceID + "&panelId=13&theme=light&kiosk";
            String status = Constant.GRAFANA_CONTROL_URL + deviceID + "&theme=light&panelId=14&kiosk";
            String power = Constant.GRAFANA_CONTROL_URL + deviceID + "&panelId=15&theme=light&kiosk";
            String energy = Constant.GRAFANA_CONTROL_URL + deviceID + "&theme=light&panelId=16&kiosk";*/
//        String sca_soc = dataBean.getScaSOC();
            String status = dataBean.getStatus();
            String power = dataBean.getPower();
            String energy = dataBean.getEnergy();
            ArrayList<String> sca_ValueList = new ArrayList<>();
//        sca_ValueList.add(sca_soc);
            sca_ValueList.add(status);
            sca_ValueList.add(power);
            sca_ValueList.add(energy);
            setDashboardGridRecyclerViewAdapter(dashboardGridKeyList, dashboardGridValueList, dashboardGridListImage);
            setDashboardSCARecyclerViewAdapter(sca_KeyList, sca_ValueList, sca_Image_List);
            if (dashboardEvseResponseModel.getStation() != null) {
                chargebox_id = dashboardEvseResponseModel.getStation().getChargeboxId();
                if (dashboardEvseResponseModel.getStation() != null) {
                    DashboardEvseResponseModel.StationBean stationBean = dashboardEvseResponseModel.getStation();
                    if (stationBean.getName() != null)
                        sca_lbl.setText(stationBean.getName());
                }
                int currentConnectorId = dashboardEvseResponseModel.getCurrentConnectorId();
                if(portSize!=0) {
                    for (int i = 0; i < portSize; i++) {
                        if (currentConnectorId == dashboardEvseResponseModel.getPorts().get(i).getConnectorId()) {
                            dashboard_port_lbl.setText(chargebox_id + " | " + dashboardEvseResponseModel.getPorts().get(i).getName() + " | " +
                                    currentConnectorId);
                            break;
                        }
                    }
                }
            }
            dashboard_port_lbl.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    String existingVal = null;
                    if (dashboard_port_lbl.getText().toString().trim().length() > 0)
                        existingVal = dashboard_port_lbl.getText().toString();
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        if (portsBeanArrayList.size() > 0) {
                            portSelectionDialog(mContext, chargebox_id, portsBeanArrayList, existingVal);
                        }
                    }
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[]{title_lbl, dashboard_port_lbl});
        Utils.setBoldFonts(mContext, new TextView[]{sca_lbl});
    }

    private void setHeader() {
        ic_back_arrow.setVisibility(View.GONE);
        settings_img.setVisibility(View.VISIBLE);
        title_lbl.setText(getString(R.string.dashboard));
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.settings_img})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.settings_img:
                NavController navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
                navController.navigate(R.id.action_navigation_dashboard_to_navigation_settings);
//                setFragment(new SettingsFragment());
                break;
        }
    }

    private void setDashboardGridRecyclerViewAdapter(ArrayList<String> dashboardGridKeyList, ArrayList<String> dashboardGridValList, ArrayList<Integer> dashboardGridListImage) {
        DashboardGridAdapter dashboardGridAdapter = new DashboardGridAdapter(mContext, dashboardGridKeyList, dashboardGridValList, dashboardGridListImage);
        dashboard_grid_rview.setLayoutManager(new GridLayoutManager(mContext, 2));
        dashboard_grid_rview.setHasFixedSize(true);
        dashboard_grid_rview.setNestedScrollingEnabled(false);
        dashboard_grid_rview.setItemAnimator(new DefaultItemAnimator());
        dashboard_grid_rview.setAdapter(dashboardGridAdapter);
    }

    private void setDashboardSCARecyclerViewAdapter(ArrayList<String> sca_KeyList, ArrayList<String> sca_ValueList,
                                                    ArrayList<Integer> sca_Image_List) {
        DashboardSCAAdapter dashboardSCAAdapter = new DashboardSCAAdapter(mContext, sca_KeyList, sca_ValueList, sca_Image_List);
        sca_rview.setLayoutManager(new LinearLayoutManager(mContext, VERTICAL, false));
        sca_rview.setHasFixedSize(true);
        sca_rview.setNestedScrollingEnabled(false);
        sca_rview.setItemAnimator(new DefaultItemAnimator());
        sca_rview.setAdapter(dashboardSCAAdapter);
    }

    private void dashboardResponse(ApiResponse<DashboardResponseModel> dashboardResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        DashboardResponseModel dashboardResponseModel = dashboardResponseModelApiResponse.getData();
        switch (dashboardResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (dashboardResponseModel.getStatus().equals("1")) {
                    if (Utils.getFunctionalFragment(getActivity(), ((BottomMenuActivity) mContext).getSupportFragmentManager(), getClass().getSimpleName().toString()))
                        updateSCAUI(dashboardResponseModel);
                } else if (dashboardResponseModel.getError().equals("TOKEN_INVALID")) {
                    sharedPreferencesUtil.deleteAllSession();
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                } else
                    Utils.showCustomAlert(mContext, getString(R.string.alert), dashboardResponseModel.getError(),
                            "dashboard_failed");
                break;
            case FAILURE:
                Utils.showCustomAlert(mContext, getString(R.string.alert),
                        Utils.getConvertedErrorBody(dashboardResponseModelApiResponse.getFailureResponse()), "dashboard_failed");
                break;
            case ERROR:
                Utils.handleErrorResponse(dashboardResponseModelApiResponse.getError(), mContext);
                break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        dismissProgress();
        try {
            if (data != null) {
                if (data instanceof DashboardResponseModel) {
                    DashboardResponseModel dashboardResponseModel = (DashboardResponseModel) data;
                    if (dashboardResponseModel.getStatus().equals("1")) {
                        restartHandler();
                        if (Utils.getFunctionalFragment(getActivity(), ((BottomMenuActivity) mContext).getSupportFragmentManager(), getClass().getSimpleName().toString()))
                        updateSCAUI(dashboardResponseModel);
                    } else if (dashboardResponseModel.getError().equals("TOKEN_INVALID")) {
                        stopHandler();
                        sharedPreferencesUtil.deleteAllSession();
                        Utils.showOKCustomAlert(mContext, null, "Session expired!",
                                "session_expired");
                    } else {
                        stopHandler();
                        Utils.showCustomAlert(mContext, getString(R.string.alert), dashboardResponseModel.getError(),
                                "dashboard_failed");
                    }
                } else if (data instanceof DashboardEvseResponseModel) {
                    DashboardEvseResponseModel dashboardEvseResponseModel = (DashboardEvseResponseModel) data;
                    if (dashboardEvseResponseModel.getStatus().equals("1")) {
                        restartHandler();
                        if (Utils.getFunctionalFragment(getActivity(), ((BottomMenuActivity) mContext).getSupportFragmentManager(), getClass().getSimpleName().toString()))
                        updateEVSEUI(dashboardEvseResponseModel);
                    } else if (dashboardEvseResponseModel.getError().equals("TOKEN_INVALID")) {
                        stopHandler();
                        sharedPreferencesUtil.deleteAllSession();
                        Utils.showOKCustomAlert(mContext, null, "Session expired!",
                                "session_expired");
                    } else {
                        stopHandler();
                        Utils.showCustomAlert(mContext, getString(R.string.alert), dashboardEvseResponseModel.getError(),
                                "dashboard_failed");
                    }
                } else if (data instanceof CommonResponseModel) {
                    CommonResponseModel commonResponseModel = (CommonResponseModel) data;
                    if (commonResponseModel.getStatus().equals("1")) {
                        sharedPreferencesUtil.deleteAllSession();
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else
                        Utils.showOKCustomAlert(mContext, null, commonResponseModel.getMessage(), "logout_failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(List data) {
    }

    @Override
    public void onError(Throwable throwable) {
        dismissProgress();
        stopHandler();
        Utils.handleErrorResponse(throwable, mContext);
        /*try {
            handler.removeCallbacks(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onFailure(Response response) {
        dismissProgress();
        stopHandler();
        Utils.showCustomAlert(mContext, getString(R.string.alert),
                Utils.getConvertedErrorBody(response), "dashboard_failed");
        /*try {
            handler.removeCallbacks(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private void dismissProgress() {
        if (customProgressDialog != null)
            customProgressDialog.dismiss();
    }

    private void updateSCAUI(DashboardResponseModel dashboardResponseModel) {
        try {
            if (dashboardResponseModel.getIsUserActivated() > 0) {
                if (dashboardResponseModel.getDeviceId() != null && dashboardResponseModel.getDeviceId().length() > 0)
                    sharedPreferencesUtil.saveDeviceID(Constant.DEVICE_ID, dashboardResponseModel.getDeviceId());
                initSCAUI(dashboardResponseModel);
                dashboard_swiperefresh.setRefreshing(false);
    //            else
    //                Utils.addDeviceCustomAlert(mContext, null, "Please add a device to your account to access this page.", TAG, R.id.action_bottom_menu_dashboard_to_devicesFragment);
            } else {
                showOKCustomAlert(mContext, "Thank you!", getString(R.string.acc_not_verified), "inactive_user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateEVSEUI(DashboardEvseResponseModel dashboardEvseResponseModel) {
        try {
            if (dashboardEvseResponseModel.getIsUserActivated() > 0) {
                if (dashboardEvseResponseModel.getDeviceId() != null && dashboardEvseResponseModel.getDeviceId().length() > 0)
                    sharedPreferencesUtil.saveDeviceID(Constant.DEVICE_ID, dashboardEvseResponseModel.getDeviceId());
                initEVSEUI(dashboardEvseResponseModel);
                dashboard_swiperefresh.setRefreshing(false);
    //            else
    //                Utils.addDeviceCustomAlert(mContext, null, "Please add a device to your account to access this page.", TAG, R.id.action_bottom_menu_dashboard_to_devicesFragment);
            } else {
                showOKCustomAlert(mContext, "Thank you!", getString(R.string.acc_not_verified), "inactive_user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * display the dialog which contains evse port
     */
    private void portSelectionDialog(final Context context, String chargebodId,
                                     ArrayList<DashboardEvseResponseModel.PortsBean> portsBeanArrayList, String existingVal) {
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
        DashboardEvsePortListAdapter dashboardEvsePortListAdapter = new DashboardEvsePortListAdapter(mContext, portsBeanArrayList,
                chargebodId, existingVal, this);
        port_rview.setLayoutManager(new LinearLayoutManager(mContext, VERTICAL, false));
        port_rview.setHasFixedSize(true);
        port_rview.setNestedScrollingEnabled(false);
        port_rview.setItemAnimator(new DefaultItemAnimator());
        port_rview.setAdapter(dashboardEvsePortListAdapter);
        port_ok_btn.setOnClickListener(view -> {
            dashboardEvseAPICall(true);
            dialog.cancel();
            dialog.dismiss();
        });
        ic_close.setOnClickListener(view -> {
            dialog.cancel();
            dialog.dismiss();
        });
    }

    /**
     * Okay Custom Alert
     */
    private void showOKCustomAlert(final Context context, String title, String message, final String from) {
        final Dialog dialog = new Dialog(context, R.style.custom_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.common_custom_ok_dialog);
        dialog.setCancelable(false);
        dialog.show();
        ImageView header_img = dialog.findViewById(R.id.header_img);
        TextView dialog_title = dialog.findViewById(R.id.title_tview);
        TextView dialog_message = dialog.findViewById(R.id.message_tview);
        TextView sub_msg_tview = dialog.findViewById(R.id.sub_msg_tview);
        TextView dialog_ok_btn = dialog.findViewById(R.id.ok_btn);
        LinearLayout parent = dialog.findViewById(R.id.ok_dialog_layout);
        Utils.setBoldFonts(context, new TextView[]{dialog_ok_btn, dialog_title});
        Utils.setRegularFonts(context, new TextView[]{dialog_message, sub_msg_tview});
        if (title != null) {
            dialog_title.setVisibility(View.VISIBLE);
            dialog_title.setText(title);
        } else {
            dialog_title.setVisibility(View.GONE);
//            dialog_title.setText(title);
            /*int paddingDp = 25, paddingBottom = 15;
            float density = context.getResources().getDisplayMetrics().density;
            int paddingPixel = (int) (paddingDp * density);
            int paddingPixel2 = (int) (paddingBottom * density);
            dialog_message.setPadding(paddingPixel, paddingPixel, paddingPixel, paddingPixel2);*/
        }
        header_img.setVisibility(View.GONE);
        sub_msg_tview.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(message))
            dialog_message.setText(message);
        else
            dialog_message.setVisibility(View.GONE);
        if (from.equals("inactive_user")) {
            header_img.setVisibility(View.VISIBLE);
            sub_msg_tview.setVisibility(View.VISIBLE);
            sub_msg_tview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
//                        ParamModel paramModel = new ParamModel();
//                        paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                        resendEmailAPICall();
                    }
                }
            });
            dialog_ok_btn.setText(context.getString(R.string.logout));
            dialog_ok_btn.setAllCaps(false);
            dialog_ok_btn.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();
                if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
                    String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
                    ParamModel paramModel = new ParamModel();
//                    paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                    paramModel.setType("android");
                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                    loginTask.logoutAPI(token, paramModel, this);
                }
            });
        } else if (from.equals("resend_mail")) {
            header_img.setVisibility(View.VISIBLE);
            sub_msg_tview.setVisibility(View.GONE);
            dialog_ok_btn.setText(context.getString(R.string.ok));
            dialog_ok_btn.setAllCaps(true);
            dialog_ok_btn.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();
            });
        } else {
            header_img.setVisibility(View.GONE);
            sub_msg_tview.setVisibility(View.GONE);
            dialog_ok_btn.setText(context.getString(R.string.ok));
            dialog_ok_btn.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void portSelected(String portName, int portID) {
        Log.i(TAG, "portSelected:portName " + portName + " portID: " + portID);
        connector_id = String.valueOf(portID);
    }

    @Override
    public void onRefresh() {
        stopHandler();
        if (sharedPreferencesUtil.getUserType(Constant.USER_TYPE) != null) {
            if (sharedPreferencesUtil.getUserType(Constant.USER_TYPE).equalsIgnoreCase("evse")) {
                dashboard_port_llay.setVisibility(View.VISIBLE);
                initDashboardEvseAPICall(false);
            } else {
                dashboard_port_llay.setVisibility(View.GONE);
                dashboardAPICall();
            }
        }
    }
}
