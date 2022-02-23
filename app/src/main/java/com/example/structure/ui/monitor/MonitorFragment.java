package com.example.structure.ui.monitor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.structure.R;
import com.example.structure.data.models.MonitorEVSEResponseModel;
import com.example.structure.data.models.MonitorResponseModel;
import com.example.structure.data.models.MonitorSCAResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.repository.MonitorRepository;
import com.example.structure.retrofit.MonitorWebService;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.ui.monitor.adapter.MonitorEVSEAdapter;
import com.example.structure.ui.monitor.adapter.MonitorGridAdapter;
import com.example.structure.ui.monitor.listener.MonitorEVSEPortListener;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.ResponseInterface;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.MonitorViewModel;

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
public class MonitorFragment extends BaseFragment implements ResponseInterface, MonitorEVSEPortListener {
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    MonitorRepository monitorRepository;
    @Inject
    MonitorWebService monitorWebService;
    @BindView(R.id.monitor_title)
    TextView monitor_title;
    @BindView(R.id.monitor_grid_rview)
    RecyclerView monitor_grid_rview;
    @BindView(R.id.settings_img)
    ImageView settings_img;
    @BindView(R.id.ic_online_status)
    ImageView ic_online_status;
    @BindView(R.id.online_status_lbl)
    TextView online_status_lbl;
    @BindView(R.id.ic_monitor)
    ImageView ic_monitor;
    @BindView(R.id.charging_status_lbl)
    TextView charging_status_lbl;
    @BindView(R.id.station_lbl)
    TextView station_lbl;
    @BindView(R.id.station_adapter_val)
    TextView station_adapter_val;
    @BindView(R.id.sca_battery_llay)
    LinearLayout sca_battery_llay;
    @BindView(R.id.sca_battery_lbl)
    TextView sca_battery_lbl;
    @BindView(R.id.sca_battery_val)
    TextView sca_battery_val;
    @BindView(R.id.last_sync_lbl)
    TextView last_sync_lbl;
    @BindView(R.id.last_sync_val)
    TextView last_sync_val;
    @BindView(R.id.mode_lbl)
    TextView mode_lbl;
    @BindView(R.id.mode_val)
    TextView mode_val;
    @BindView(R.id.evsc_llay)
    LinearLayout evsc_llay;
    @BindView(R.id.evsc_lbl)
    TextView evsc_lbl;
    @BindView(R.id.evsc_type_llay)
    LinearLayout evsc_type_llay;
    @BindView(R.id.ic_evsc_type)
    ImageView ic_evsc_type;
    @BindView(R.id.evsc_type_lbl)
    TextView evsc_type_lbl;
    @BindView(R.id.evsc_type_val)
    TextView evsc_type_val;
    @BindView(R.id.max_amperage_llay)
    LinearLayout max_amperage_llay;
    @BindView(R.id.ic_max_amperage)
    ImageView ic_max_amperage;
    @BindView(R.id.max_amperage_lbl)
    TextView max_amperage_lbl;
    @BindView(R.id.max_amperage_val)
    TextView max_amperage_val;
    @BindView(R.id.status_rlay)
    RelativeLayout status_rlay;
    @BindView(R.id.ic_monitor_status)
    ImageView ic_monitor_status;
    @BindView(R.id.monitor_status_tview)
    TextView monitor_status_tview;
    @BindView(R.id.monitor_status_lay)
    LinearLayout monitor_status_lay;
    @BindView(R.id.status_ev_max_draw_lay)
    LinearLayout status_ev_max_draw_lay;
    @BindView(R.id.ev_max_draw_arrow)
    ImageView ev_max_draw_arrow;
    @BindView(R.id.ev_max_draw_tview)
    TextView ev_max_draw_tview;
    @BindView(R.id.limitted_evsc_lay)
    LinearLayout limitted_evsc_lay;
    @BindView(R.id.limitted_evsc_arrow)
    ImageView limitted_evsc_arrow;
    @BindView(R.id.limitted_evsc_tview)
    TextView limitted_evsc_tview;
    @BindView(R.id.charge_status_lay)
    LinearLayout charge_status_lay;
    @BindView(R.id.charge_status_arrow)
    ImageView charge_status_arrow;
    @BindView(R.id.charge_status_tview)
    TextView charge_status_tview;
    @BindView(R.id.monitor_evse_port_lay)
    LinearLayout monitor_evse_port_lay;
    @BindView(R.id.monitor_evse_port_lbl)
    TextView monitor_evse_port_lbl;
    @BindView(R.id.monitor_evse_port_tview)
    TextView monitor_evse_port_tview;
    @BindView(R.id.port_arrow_img)
    ImageView port_arrow_img;
    @BindView(R.id.port_dropdown_llay)
    LinearLayout port_dropdown_llay;

    int delay = 15000; // 15 sec
    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private CustomProgressDialog customProgressDialog;
    private MonitorViewModel monitorViewModel;
    private ArrayList<MonitorSCAResponseModel> monitorSCAArrayList;
    private MonitorGridAdapter monitorGridAdapter;
    private Handler handler = new Handler();
    private Runnable runnable;
    private ResponseInterface responseInterface;
    private View view;
    private String token;
    private int selectedPort;
    ArrayList<String> evsePortList;
    ArrayList<Integer> evsePortIdList;
    ArrayList<MonitorEVSEResponseModel.MonitorOcppBean.StationPortsBean> stationPortList;
    private int[] monitorStatusIcon;
    private ParamModel paramModel;
    private Dialog dialog = null;

    public MonitorFragment() {
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
        monitorViewModel = ViewModelProviders.of(this, viewModelFactory).get(MonitorViewModel.class);
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
        if (sharedPreferencesUtil.getUserType(Constant.USER_TYPE).equals("EVSE")) {
            monitor_evse_port_lay.setVisibility(View.VISIBLE);
            sca_battery_llay.setVisibility(View.GONE);
            monitor_title.setText(getResources().getString(R.string.monitor_evse));
            initmonitorEVSEAPICall();
        } else {
            monitor_evse_port_lay.setVisibility(View.GONE);
            sca_battery_llay.setVisibility(View.VISIBLE);
            monitor_title.setText(getResources().getString(R.string.monitor_sca));
            monitorAPICall();
        }
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
            /*handler.removeCallbacks(runnable); //stop handler when fragment is not visible
            if (monitorWebService.monitorAPI(token).isExecuted())
                monitorWebService.monitorAPI(token).cancel();
            else if (monitorWebService.monitorEVSEAPI(token).isExecuted())
                monitorWebService.monitorEVSEAPI(token).cancel();
            else if (monitorWebService.monitorEVSEPortAPI(token, String.valueOf(selectedPort)).isExecuted())
                monitorWebService.monitorEVSEPortAPI(token, String.valueOf(selectedPort)).cancel();*/
            stopHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_monitor, container, false);
        unbinder = ButterKnife.bind(this, view);
        responseInterface = this;
        fragmentManager = getFragmentManager();
        initUI();
        return view;
    }

    private void initUI() {
        token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
        paramModel = new ParamModel();
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();

        monitorStatusIcon = new int[9];
        monitorStatusIcon[0] = R.drawable.ic_ms_available;
        monitorStatusIcon[1] = R.drawable.ic_ms_charging;
        monitorStatusIcon[2] = R.drawable.ic_ms_faulted;
        monitorStatusIcon[3] = R.drawable.ic_ms_finishing;
        monitorStatusIcon[4] = R.drawable.ic_ms_preparing;
        monitorStatusIcon[5] = R.drawable.ic_ms_suspendedev;
        monitorStatusIcon[6] = R.drawable.ic_ms_suspendedevse;
        monitorStatusIcon[7] = R.drawable.ic_ms_unavailable;
    }

    /**
     * Monitor SCA API
     **/
    private void monitorAPICall() {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            monitorRepository.monitorAPIData(token, responseInterface);
            runnable = new Runnable() {
                @Override
                public void run() {
                    monitorRepository.monitorAPIData(token, responseInterface);
                    handler.postDelayed(this, delay);
                }
            };
            handler.postDelayed(runnable, delay);
        }
    }

    /**
     * Monitor EVSE API
     **/
    private void initmonitorEVSEAPICall() {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            if (selectedPort > 0) {
//                    ParamModel paramModel = new ParamModel();
//                    paramModel.setPort_id(String.valueOf(selectedPort));
                Log.i(TAG, "monitorEVSEAPICall:selectedPort " + selectedPort);
                monitorRepository.monitorEVSEPortAPIData(token, String.valueOf(selectedPort), responseInterface);
            } else monitorRepository.monitorEVSEAPIData(token, responseInterface);
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                if (selectedPort > 0) {
//                        ParamModel paramModel = new ParamModel();
//                        paramModel.setPort_id(String.valueOf(selectedPort));
                    Log.i(TAG, "monitorEVSEAPICall:selectedPort " + selectedPort);
                    monitorRepository.monitorEVSEPortAPIData(token, String.valueOf(selectedPort), responseInterface);
                } else
                    monitorRepository.monitorEVSEAPIData(token, responseInterface);
                handler.postDelayed(this, delay);
            }
        };
        handler.postDelayed(runnable, delay);
    }

    /**
     * Monitor EVSE API
     **/
    private void monitorEVSEAPICall() {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            if (selectedPort > 0) {
//                    ParamModel paramModel = new ParamModel();
//                    paramModel.setPort_id(String.valueOf(selectedPort));
                Log.i(TAG, "monitorEVSEAPICall:selectedPort " + selectedPort);
                monitorRepository.monitorEVSEPortAPIData(token, String.valueOf(selectedPort), responseInterface);
            } else monitorRepository.monitorEVSEAPIData(token, responseInterface);
        }
    }

    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[]{monitor_title, online_status_lbl, charging_status_lbl, station_lbl, station_adapter_val,
                sca_battery_lbl, last_sync_lbl, mode_lbl, evsc_lbl, max_amperage_lbl, monitor_status_tview, ev_max_draw_tview,
                limitted_evsc_tview, charge_status_tview, monitor_evse_port_lbl});
        Utils.setBoldFonts(mContext, new TextView[]{sca_battery_val, last_sync_val, mode_val, evsc_type_val, max_amperage_val, monitor_evse_port_tview});
    }

    private void setHeader() {
//        settings_img.setImageResource(R.drawable.ic_add);
        ic_online_status.setVisibility(View.VISIBLE);
        settings_img.setVisibility(View.VISIBLE);
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.settings_img, R.id.port_dropdown_llay})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.settings_img:
                NavController navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
                navController.navigate(R.id.action_monitorFragment_to_navigation_settings);
                break;
        }
    }

    private void setMonitorGridRecyclerViewAdapter(ArrayList<MonitorSCAResponseModel> monitorSCAArrayList) {
        monitorGridAdapter = new MonitorGridAdapter(mContext, monitorSCAArrayList);
        monitor_grid_rview.setLayoutManager(new GridLayoutManager(mContext, 2));
        monitor_grid_rview.setHasFixedSize(true);
        monitor_grid_rview.setNestedScrollingEnabled(false);
        monitor_grid_rview.setItemAnimator(new DefaultItemAnimator());
        monitor_grid_rview.setAdapter(monitorGridAdapter);
    }

    private void monitorResponse(ApiResponse<MonitorResponseModel> monitorResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        MonitorResponseModel monitorResponseModel = monitorResponseModelApiResponse.getData();
        switch (monitorResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (monitorResponseModel.getStatus().equals("1")) {
                    toastMessage(monitorResponseModel.getSuccess() + " API Success");
                    updateSCAUI(monitorResponseModel);
                } else if (monitorResponseModel.getError().equals("TOKEN_INVALID")) {
                    sharedPreferencesUtil.deleteAllSession();
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                } else
                    Utils.showCustomAlert(mContext, getString(R.string.alert), monitorResponseModel.getError(),
                            "monitor_failed");
                break;
            case FAILURE:
                Utils.showCustomAlert(mContext, getString(R.string.alert),
                        Utils.getConvertedErrorBody(monitorResponseModelApiResponse.getFailureResponse()), "monitor_failed");
                break;
            case ERROR:
                Utils.handleErrorResponse(monitorResponseModelApiResponse.getError(), mContext);
                break;
        }
    }

    private void updateSCAUI(MonitorResponseModel monitorResponseModel) {
        if (monitorResponseModel.getStatus().equals("1")) {
            MonitorResponseModel.MonitorScaBean monitorSca = monitorResponseModel.getMonitorSca();
            String formatConversion = "yyyy-MM-dd hh:mm:ss Z";
            String lastSync = monitorSca.getLastSync();
            if (!lastSync.equalsIgnoreCase("N/A"))
                lastSync = Utils.getLocalTimeFromUTCByFormat(lastSync, formatConversion, "MM/dd/yyyy hh:mm:ss a");
            String monitorStatus = monitorSca.getSessionStatus();
            setMonitorStatusIcon(monitorStatus);
            online_status_lbl.setText(monitorSca.getNetworkStatus());
            charging_status_lbl.setText(monitorStatus);
            station_lbl.setText(monitorSca.getStationName());
            station_adapter_val.setText(monitorSca.getScaId());
            sca_battery_val.setText(monitorSca.getScaBattery());
            last_sync_val.setText(lastSync);
            mode_val.setText(monitorSca.getMode());
            evsc_type_val.setText(monitorSca.getEvseType());
            max_amperage_val.setText(monitorSca.getMaxAmperage() + " Arms");
            int maxDrawStatus = monitorSca.getMaxDrawStatus();
            int limitedbyEVSEStatus = monitorSca.getLimitedbyEVSEStatus();
            int adapterStatus = monitorSca.getAdapterStatus();
            if (maxDrawStatus == 0) {
                status_ev_max_draw_lay.setBackgroundColor(Color.WHITE);
                ev_max_draw_arrow.setVisibility(View.INVISIBLE);
                ev_max_draw_tview.setTextColor(ContextCompat.getColor(mContext, R.color.monitor_disable_text));
            } else if (maxDrawStatus == 1) {
                status_ev_max_draw_lay.setBackgroundResource(R.drawable.bg_edittext_gray);
                ev_max_draw_arrow.setVisibility(View.VISIBLE);
                ev_max_draw_tview.setTextColor(ContextCompat.getColor(mContext, R.color.hint_color));
            }

            if (limitedbyEVSEStatus == 0) {
                limitted_evsc_lay.setBackgroundColor(Color.WHITE);
                limitted_evsc_arrow.setVisibility(View.INVISIBLE);
                limitted_evsc_tview.setTextColor(ContextCompat.getColor(mContext, R.color.monitor_disable_text));
            } else if (limitedbyEVSEStatus == 1) {
                limitted_evsc_lay.setBackgroundResource(R.drawable.bg_edittext_gray);
                limitted_evsc_arrow.setVisibility(View.VISIBLE);
                limitted_evsc_tview.setTextColor(ContextCompat.getColor(mContext, R.color.hint_color));
            }

            if (adapterStatus == 0) {
                charge_status_lay.setBackgroundColor(Color.WHITE);
                charge_status_arrow.setVisibility(View.INVISIBLE);
                charge_status_tview.setTextColor(ContextCompat.getColor(mContext, R.color.monitor_disable_text));
            } else if (adapterStatus == 1) {
                charge_status_lay.setBackgroundResource(R.drawable.bg_edittext_gray);
                charge_status_arrow.setVisibility(View.VISIBLE);
                charge_status_tview.setTextColor(ContextCompat.getColor(mContext, R.color.hint_color));
            }
            String activeEnergy = monitorSca.getActiveEnergy(), activePower = monitorSca.getActivePower(),
                    lineVoltage = monitorSca.getLineVoltage(), chargeCurrent = monitorSca.getChargeCurrent();
            monitorSCAArrayList = new ArrayList<>();
            String[] monitorVal = new String[4], monitorKey = new String[4];
            int[] monitorIcon = new int[4];
            monitorIcon[0] = R.drawable.ic_meter;
            monitorIcon[1] = R.drawable.ic_electric_car;
            monitorIcon[2] = R.drawable.ic_energy_flash;
            monitorIcon[3] = R.drawable.ic_amperage;
            monitorKey[0] = "Active Energy (kWh)";
            monitorKey[1] = "Active Power (kW)";
            monitorKey[2] = "Line Voltage (Vrms)";
            monitorKey[3] = "Charge Current (Arms)";
            monitorVal[0] = activeEnergy;
            monitorVal[1] = activePower;
            monitorVal[2] = lineVoltage;
            monitorVal[3] = chargeCurrent;
            for (int i = 0; i < monitorIcon.length; i++) {
                MonitorSCAResponseModel monitorSCAResponseModel = new MonitorSCAResponseModel();
                monitorSCAResponseModel.setMonitor_icon(monitorIcon[i]);
                monitorSCAResponseModel.setMonitorKey(monitorKey[i]);
                monitorSCAResponseModel.setMonitorVal(monitorVal[i]);
                monitorSCAArrayList.add(monitorSCAResponseModel);
            }
            setMonitorGridRecyclerViewAdapter(monitorSCAArrayList);
        }
    }

    private void setMonitorStatusIcon(String monitorStatus) {
        if (monitorStatus.equalsIgnoreCase("Available"))
            ic_monitor.setImageResource(monitorStatusIcon[0]);
        else if (monitorStatus.equalsIgnoreCase("Charging"))
            ic_monitor.setImageResource(monitorStatusIcon[1]);
        else if (monitorStatus.equalsIgnoreCase("Faulted"))
            ic_monitor.setImageResource(monitorStatusIcon[2]);
        else if (monitorStatus.equalsIgnoreCase("Finishing"))
            ic_monitor.setImageResource(monitorStatusIcon[3]);
        else if (monitorStatus.equalsIgnoreCase("Preparing"))
            ic_monitor.setImageResource(monitorStatusIcon[4]);
            /*else if(monitorStatus.equalsIgnoreCase("Reserved"))
                ic_monitor.setImageResource(monitorStatusIcon[5]);*/
        else if (monitorStatus.equalsIgnoreCase("SuspendedEV"))
            ic_monitor.setImageResource(monitorStatusIcon[5]);
        else if (monitorStatus.equalsIgnoreCase("SuspendedEVSE"))
            ic_monitor.setImageResource(monitorStatusIcon[6]);
        else if (monitorStatus.equalsIgnoreCase("Unavailable"))
            ic_monitor.setImageResource(monitorStatusIcon[7]);
        else if (monitorStatus.equalsIgnoreCase("N/A"))
            ic_monitor.setImageResource(monitorStatusIcon[2]);
        else
            ic_monitor.setImageResource(monitorStatusIcon[2]);
    }

    private void monitorEVSEResponse(ApiResponse<MonitorEVSEResponseModel> monitorEVSEResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        MonitorEVSEResponseModel monitorEVSEResponseModel = monitorEVSEResponseModelApiResponse.getData();
        switch (monitorEVSEResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (monitorEVSEResponseModel.getStatus().equals("1")) {
                    toastMessage(monitorEVSEResponseModel.getSuccess() + " API Success");
                    updateEVSEUI(monitorEVSEResponseModel);
                } else if (monitorEVSEResponseModel.getError().equals("TOKEN_INVALID")) {
                    sharedPreferencesUtil.deleteAllSession();
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                } else
                    Utils.showCustomAlert(mContext, getString(R.string.alert), monitorEVSEResponseModel.getError(),
                            "monitor_evse_failed");
                break;
            case FAILURE:
                Utils.showCustomAlert(mContext, getString(R.string.alert),
                        Utils.getConvertedErrorBody(monitorEVSEResponseModelApiResponse.getFailureResponse()), "monitor_evse_failed");
                break;
            case ERROR:
                Utils.handleErrorResponse(monitorEVSEResponseModelApiResponse.getError(), mContext);
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void updateEVSEUI(MonitorEVSEResponseModel monitorEVSEResponseModel) {
        int connectorID = 0;
        String stationName, chargebox_id, portName;
        if (monitorEVSEResponseModel.getStatus().equals("1")) {
            MonitorEVSEResponseModel.MonitorOcppBean monitorOcppBean = monitorEVSEResponseModel.getMonitorOcpp();
            MonitorEVSEResponseModel.MonitorOcppBean.ControlEvseDataBean controlEvseDataBean =
                    monitorEVSEResponseModel.getMonitorOcpp().getControlEvseData();
            int stationPortSize = monitorEVSEResponseModel.getMonitorOcpp().getStationPorts().size();
            connectorID = monitorOcppBean.getConnectorId();
            stationName = monitorOcppBean.getStationName();
            chargebox_id = monitorOcppBean.getChargeboxId();
            portName = monitorOcppBean.getPortName();
            if (stationPortSize > 0) {
                evsePortList = new ArrayList<>();
                evsePortIdList = new ArrayList<>();
                stationPortList = new ArrayList<>();
//                for (int i = 0; i < stationPortSize; i++) {
                    /*MonitorEVSEResponseModel.MonitorOcppBean.StationPortsBean stationPortsBean = new MonitorEVSEResponseModel.MonitorOcppBean.StationPortsBean();
                    stationPortsBean.setPortId(monitorEVSEResponseModel.getMonitorOcpp().getStationPorts().get(i).getPortId());
                    stationPortsBean.setPortName(monitorEVSEResponseModel.getMonitorOcpp().getStationPorts().get(i).getPortName());
                    stationPortsBeans.add(stationPortsBean);*/
                for (int i = 0; i < stationPortSize; i++) {
                    int portId = monitorEVSEResponseModel.getMonitorOcpp().getStationPorts().get(i).getPortId();
                    int connectorId = monitorEVSEResponseModel.getMonitorOcpp().getStationPorts().get(i).getConnectorId();
                    String multiPortName = monitorEVSEResponseModel.getMonitorOcpp().getStationPorts().get(i).getPortName();
                    if (connectorId > 0) {
                        MonitorEVSEResponseModel.MonitorOcppBean.StationPortsBean stationPortsBean = new MonitorEVSEResponseModel.MonitorOcppBean.StationPortsBean();
                        stationPortsBean.setPortName(multiPortName);
                        stationPortsBean.setPortId(portId);
                        stationPortsBean.setConnectorId(connectorId);
                        stationPortList.add(stationPortsBean);
                        evsePortList.add(chargebox_id + " | " + multiPortName);
                        evsePortIdList.add(portId);
                    }
                }
//                }
            }
            String formatConversion = "yyyy-MM-dd hh:mm:ss Z";
            String evseStatus = controlEvseDataBean.getSessionStatus();
            setMonitorStatusIcon(evseStatus);
            online_status_lbl.setText(controlEvseDataBean.getNetworkStatus());
            charging_status_lbl.setText(evseStatus);
            monitor_evse_port_tview.setText(portName + " " + connectorID);
            station_lbl.setText(monitorOcppBean.getStationName());
            station_adapter_val.setText(monitorOcppBean.getChargeboxId());
//            sca_battery_val.setText(monitorOcppBean.getScaBattery());
            String lastSync = Utils.getLocalTimeFromUTCByFormat(controlEvseDataBean.getLastSync(), formatConversion, "MM/dd/yyyy hh:mm:ss a");
//            lastSync = lastSync.substring(0, lastSync.indexOf("+"));
            last_sync_val.setText(lastSync);
            mode_val.setText(controlEvseDataBean.getEvseMode());
            evsc_type_val.setText(controlEvseDataBean.getEvseType());
            max_amperage_val.setText(controlEvseDataBean.getMaxAmperage() + " Arms");
            int maxDrawStatus = controlEvseDataBean.getMaxDraw();
            int limitedbyEVSEStatus = controlEvseDataBean.getLimitedbyEvse();
            int chargenearend = controlEvseDataBean.getChargeNearEnd();
            if (maxDrawStatus == 0) {
                status_ev_max_draw_lay.setBackgroundColor(Color.WHITE);
                ev_max_draw_arrow.setVisibility(View.INVISIBLE);
                ev_max_draw_tview.setTextColor(ContextCompat.getColor(mContext, R.color.monitor_disable_text));
            } else if (maxDrawStatus == 1) {
                status_ev_max_draw_lay.setBackgroundResource(R.drawable.bg_edittext_gray);
                ev_max_draw_arrow.setVisibility(View.VISIBLE);
                ev_max_draw_tview.setTextColor(ContextCompat.getColor(mContext, R.color.hint_color));
            }
            if (limitedbyEVSEStatus == 0) {
                limitted_evsc_lay.setBackgroundColor(Color.WHITE);
                limitted_evsc_arrow.setVisibility(View.INVISIBLE);
                limitted_evsc_tview.setTextColor(ContextCompat.getColor(mContext, R.color.monitor_disable_text));
            } else if (limitedbyEVSEStatus == 1) {
                limitted_evsc_lay.setBackgroundResource(R.drawable.bg_edittext_gray);
                limitted_evsc_arrow.setVisibility(View.VISIBLE);
                limitted_evsc_tview.setTextColor(ContextCompat.getColor(mContext, R.color.hint_color));
            }
            if (chargenearend == 0) {
                charge_status_lay.setBackgroundColor(Color.WHITE);
                charge_status_arrow.setVisibility(View.INVISIBLE);
                charge_status_tview.setTextColor(ContextCompat.getColor(mContext, R.color.monitor_disable_text));
            } else if (chargenearend == 1) {
                charge_status_lay.setBackgroundResource(R.drawable.bg_edittext_gray);
                charge_status_arrow.setVisibility(View.VISIBLE);
                charge_status_tview.setTextColor(ContextCompat.getColor(mContext, R.color.hint_color));
            }
            String activeEnergy = controlEvseDataBean.getActiveEnergy(), activePower = controlEvseDataBean.getActivePower(),
                    lineVoltage = controlEvseDataBean.getLineVoltage(), chargeCurrent = controlEvseDataBean.getChargeCurrent();
            monitorSCAArrayList = new ArrayList<>();
            String[] monitorVal = new String[4], monitorKey = new String[4];
            int[] monitorIcon = new int[4];
            monitorIcon[0] = R.drawable.ic_meter;
            monitorIcon[1] = R.drawable.ic_electric_car;
            monitorIcon[2] = R.drawable.ic_energy_flash;
            monitorIcon[3] = R.drawable.ic_amperage;
            monitorKey[0] = "Active Energy (kWh)";
            monitorKey[1] = "Active Power (kW)";
            monitorKey[2] = "Line Voltage (Vrms)";
            monitorKey[3] = "Charge Current (Arms)";
            monitorVal[0] = activeEnergy;
            monitorVal[1] = activePower;
            monitorVal[2] = lineVoltage;
            monitorVal[3] = chargeCurrent;
            for (int i = 0; i < monitorIcon.length; i++) {
                MonitorSCAResponseModel monitorSCAResponseModel = new MonitorSCAResponseModel();
                monitorSCAResponseModel.setMonitor_icon(monitorIcon[i]);
                monitorSCAResponseModel.setMonitorKey(monitorKey[i]);
                monitorSCAResponseModel.setMonitorVal(monitorVal[i]);
                monitorSCAArrayList.add(monitorSCAResponseModel);
            }
            setMonitorGridRecyclerViewAdapter(monitorSCAArrayList);

            monitor_evse_port_tview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    String existingVal = null;
                    if (monitor_evse_port_tview.getText().toString().trim().length() > 0)
                        existingVal = monitor_evse_port_tview.getText().toString();
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        if (stationPortSize > 0) {
                            portSelectionDialog(mContext, chargebox_id, stationPortList, existingVal);
                        }
                    }
                    return true;
                }
            });

            port_dropdown_llay.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    String existingVal = null;
                    if (monitor_evse_port_tview.getText().toString().trim().length() > 0)
                        existingVal = monitor_evse_port_tview.getText().toString();
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        if (stationPortSize > 0) {
                            portSelectionDialog(mContext, chargebox_id, stationPortList, existingVal);
                        }
                    }
                    return true;
                }
            });
        }
    }

    /**
     * display the dialog which contains evse port
     *
     * @param existingValue will shown when value is available
     */
    private void portSelectionDialog(final Context context, String chargebodId,
                                     ArrayList<MonitorEVSEResponseModel.MonitorOcppBean.StationPortsBean> stationPortList, String existingValue) {
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

        MonitorEVSEAdapter monitorEVSEAdapter = new MonitorEVSEAdapter(mContext, chargebodId, stationPortList, existingValue, this);
        port_rview.setLayoutManager(new LinearLayoutManager(mContext, VERTICAL, false));
        port_rview.setHasFixedSize(true);
        port_rview.setNestedScrollingEnabled(false);
        port_rview.setItemAnimator(new DefaultItemAnimator());
        port_rview.setAdapter(monitorEVSEAdapter);

        port_ok_btn.setOnClickListener(view -> {
            monitorEVSEAPICall();
            dialog.cancel();
            dialog.dismiss();
        });

        ic_close.setOnClickListener(view -> {
            dialog.cancel();
            dialog.dismiss();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(Object data) {
        dismissProgress();
        if (Utils.getFunctionalFragment(getActivity(), ((BottomMenuActivity) mContext).getSupportFragmentManager(), "MonitorFragment")) {
            try {
                if (data != null) {
                    if (data instanceof MonitorEVSEResponseModel) {
                        MonitorEVSEResponseModel monitorResponseModel = (MonitorEVSEResponseModel) data;
                        if (monitorResponseModel.getStatus().equals("1")) {
                            restartHandler();
                            updateEVSEUI(monitorResponseModel);
                        } else if (monitorResponseModel.getError().equals("TOKEN_INVALID")) {
                            stopHandler();
                            sharedPreferencesUtil.deleteAllSession();
                            Utils.showOKCustomAlert(mContext, null, "Session expired!",
                                    "session_expired");
                        } else if (monitorResponseModel.getError().equalsIgnoreCase("No Adapter")) {
                            if (dialog != null && dialog.isShowing())
                                dialog.dismiss();
                            stopHandler();
                            Utils.addDeviceCustomAlert(mContext, null, monitorResponseModel.getMessage(), TAG, R.id.action_bottom_menu_monitor_to_devicesFragment);
                        } else {
                            stopHandler();
                            Utils.showCustomAlert(mContext, getString(R.string.alert), monitorResponseModel.getMessage(),
                                    "monitor_failed");
                        }
                    } else {
                        MonitorResponseModel monitorResponseModel = (MonitorResponseModel) data;
                        if (monitorResponseModel.getStatus().equals("1")) {
                            restartHandler();
                            updateSCAUI(monitorResponseModel);
                        } else if (monitorResponseModel.getError().equals("TOKEN_INVALID")) {
                            stopHandler();
                            sharedPreferencesUtil.deleteAllSession();
                            Utils.showOKCustomAlert(mContext, null, "Session expired!",
                                    "session_expired");
                        } else if (monitorResponseModel.getError().equalsIgnoreCase("No Adapter")) {
                            if (dialog != null && dialog.isShowing())
                                dialog.dismiss();
                            stopHandler();
                            Utils.addDeviceCustomAlert(mContext, null, monitorResponseModel.getMessage(), TAG, R.id.action_bottom_menu_monitor_to_devicesFragment);
                        } else {
                            stopHandler();
                            Utils.showCustomAlert(mContext, getString(R.string.alert), monitorResponseModel.getMessage(),
                                    "monitor_failed");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        Utils.showCustomAlert(mContext, getString(R.string.alert),
                Utils.getConvertedErrorBody(response), "monitor_failed");
        stopHandler();
    }

    private void dismissProgress() {
        if (customProgressDialog != null)
            customProgressDialog.dismiss();
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
    public void portSelected(String portName, int portID) {
        Log.i(TAG, "portSelected:PortName " + portName + " PortId: " + portID);
        selectedPort = portID;
    }
}
