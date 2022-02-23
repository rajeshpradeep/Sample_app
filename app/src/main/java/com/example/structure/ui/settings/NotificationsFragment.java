package com.example.structure.ui.settings;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.NotificationResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.NotificationViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends BaseFragment {

    private String TAG = getClass().getSimpleName();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    private NotificationViewModel notificationViewModel;
    Context mContext;
    private Unbinder unbinder;
    FragmentManager fragmentManager;

    @BindView(R.id.title_lbl)
    TextView title_lbl;
    @BindView(R.id.ic_back_arrow)
    ImageView ic_back_arrow;
    @BindView(R.id.settings_img)
    ImageView settings_img;

    @BindView(R.id.notify_ev_rlay)
    RelativeLayout notify_ev_rlay;
    @BindView(R.id.notify_ev_tview)
    TextView notify_ev_tview;
    @BindView(R.id.notify_ev_scompat)
    SwitchCompat notify_ev_scompat;
    @BindView(R.id.notify_if_interrupt_rlay)
    RelativeLayout notify_if_interrupt_rlay;
    @BindView(R.id.notify_if_interrupt_tview)
    TextView notify_if_interrupt_tview;
    @BindView(R.id.notify_if_interrupt_scompat)
    SwitchCompat notify_if_interrupt_scompat;
    @BindView(R.id.notify_ev_plugin_rlay)
    RelativeLayout notify_ev_plugin_rlay;
    @BindView(R.id.notify_ev_plugin_tview)
    TextView notify_ev_plugin_tview;
    @BindView(R.id.notify_ev_plugin_scompat)
    SwitchCompat notify_ev_plugin_scompat;
    @BindView(R.id.notify_me_at_rlay)
    RelativeLayout notify_me_at_rlay;
    @BindView(R.id.notify_me_at_lbl)
    TextView notify_me_at_lbl;
    @BindView(R.id.notify_time_layout)
    LinearLayout notify_time_layout;
    @BindView(R.id.notify_time_etext)
    EditText notify_time_etext;
    @BindView(R.id.apply_btn)
    Button apply_btn;

    private NavController navController;
    private CustomProgressDialog customProgressDialog;
    private String selectedTime, isFullyCharged, isChargeInterrupted, isPluginEV, userDateTime, userTimezone;
    private Date dateAndTIme;
    private SimpleDateFormat userTimeFormat;
    private TimePickerDialog timePickerDialog;

    public NotificationsFragment() {
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
        notificationViewModel = ViewModelProviders.of(this, viewModelFactory).get(NotificationViewModel.class);
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
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        fragmentManager = getFragmentManager();
        selectedTime = "invalid";
        isFullyCharged = "";
        isChargeInterrupted = "";
        isPluginEV = "";
        notificationAPICall();
        initUI();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();
        disableEdittextInput(notify_time_etext);
        navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
        notify_ev_plugin_scompat.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                notify_me_at_rlay.setVisibility(View.VISIBLE);
            } else {
                notify_me_at_rlay.setVisibility(View.GONE);
            }
        });

        notify_time_etext.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                // Get Current Time
                Calendar c = Calendar.getInstance();
                c.setTimeZone(TimeZone.getDefault());
                dateAndTIme = c.getTime();
                int mHour, mMinute;
                if (!selectedTime.equalsIgnoreCase("invalid")) {
                    String[] selectedDateTime = selectedTime.split(":");
                    mHour = Integer.parseInt(selectedDateTime[0]);
                    mMinute = Integer.parseInt(selectedDateTime[1]);
                } else {
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);
                }
                // Launch Time Picker Dialog
                @SuppressLint("DefaultLocale") TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, (view1, hourOfDay, minute) -> {
//                    String AM_PM = hourOfDay < 12 ? " AM" : " PM";
                    selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                    boolean isPM = (hourOfDay >= 12);
                    @SuppressLint("DefaultLocale") String displaySelectedTime = (String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                    notify_time_etext.setText(displaySelectedTime);
                    Log.i(TAG, "initUI:selectedTime " + selectedTime);
                }, mHour, mMinute, true);// 24 hour format
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
            return true;
        });
    }

    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[]{title_lbl, notify_ev_tview, notify_if_interrupt_tview,
                notify_ev_plugin_tview, notify_time_etext, notify_me_at_lbl});
        Utils.setBoldFonts(mContext, new TextView[]{apply_btn});
    }

    private void setHeader() {
        ic_back_arrow.setVisibility(View.VISIBLE);
        settings_img.setVisibility(View.GONE);
        title_lbl.setText(getString(R.string.notifications));
    }

    /**
     * implementing the click action
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick({R.id.ic_back_arrow, R.id.apply_btn})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.ic_back_arrow:
//                setFragment(new SettingsFragment());
                navController.popBackStack();
                break;
            case R.id.apply_btn:
                updateNotificationAPICall();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notificationAPICall() {
        if (sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
//            ParamModel paramModel = new ParamModel();
//            paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            notificationViewModel.notificationViewModel(token).observe((LifecycleOwner) mContext, notificationResponseModelApiResponse ->
                    getNotificationResponse(notificationResponseModelApiResponse));
        }
    }

    private void getNotificationResponse(ApiResponse<NotificationResponseModel> notificationResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        NotificationResponseModel notificationResponseModel;
        if (notificationResponseModelApiResponse.getData() != null) {
            notificationResponseModel = notificationResponseModelApiResponse.getData();
            switch (notificationResponseModelApiResponse.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (notificationResponseModel != null)
                        updateUI(notificationResponseModel);
                    else if (notificationResponseModel.getError().equals(getString(R.string.invalid_token)))
                        Utils.showOKCustomAlert(mContext, null, "Session expired!",
                                "session_expired");
                    else
                        Utils.showCustomAlert(mContext, getString(R.string.alert), notificationResponseModelApiResponse.getData().getError(), "CreateAccFailed");
                    break;
                case ERROR:
                    Utils.handleErrorResponse(notificationResponseModelApiResponse.getError(), mContext);
                    break;
                case FAILURE:
                    String failureResponse = Utils.getConvertedErrorBody(notificationResponseModelApiResponse.getFailureResponse());
                    Utils.showCustomAlert(mContext, getString(R.string.alert), failureResponse, "addelectricvehicleFragment");
                    break;

                default:
                    break;
            }
        }
    }

    private void updateUI(NotificationResponseModel notificationResponseModel) {
        if (notificationResponseModel.getStatus().equals("1")) {
            toastMessage(notificationResponseModel.getSuccess() + " success");
            NotificationResponseModel.NotificationBean notificationBean = notificationResponseModel.getNotification();
            int is_fully_charged = notificationBean.getIsFullyCharged();
            int is_unexpectedly_interrupted = notificationBean.getIsUnexpectedlyInterrupted();
            int is_notify_to_plugin_mobile = notificationBean.getIsNotifyToPluginMobile();
            notify_ev_scompat.setChecked(is_fully_charged != 0);
            notify_if_interrupt_scompat.setChecked(is_unexpectedly_interrupted != 0);
            if (is_notify_to_plugin_mobile > 0) {
                notify_me_at_rlay.setVisibility(View.VISIBLE);
                notify_ev_plugin_scompat.setChecked(is_notify_to_plugin_mobile != 0);
                notify_time_etext.setText(Utils.getLocalTimeFromUTC(notificationBean.getUserDatetime()));
                try {
                    selectedTime = Utils.getLocalTimeFromUTCWithoutAMPM(notificationBean.getUserDatetime());
                    userDateTime = notificationBean.getUserDatetime();
                    userTimezone = notificationBean.getUserTimezone();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else notify_me_at_rlay.setVisibility(View.GONE);
        }
    }

    private void updateNotificationAPICall() {
        String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
        ParamModel paramModel = new ParamModel();
        try {
            if (!selectedTime.equalsIgnoreCase("invalid")) {
                userTimeFormat = new SimpleDateFormat("yyyy-MM-dd:ss Z", Locale.getDefault());
                String dateAndZone;
                if(dateAndTIme != null) {
                    dateAndZone = userTimeFormat.format(dateAndTIme);
                    String[] split = dateAndZone.split(":");
                    userDateTime = split[0] + " " + selectedTime + ":" + split[1];
                } else {
                    if(!userDateTime.contains("+0000)"))
                    userDateTime = userDateTime + " +0000";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        userTimezone = TimeZone.getDefault().getID();
        if (notify_ev_plugin_scompat.isChecked()) {
            if (notify_time_etext.getText().toString().length() > 0) {
                new Handler().post(() -> {
                    String tempUserDateTime = (Utils.getUTCTimeFromLocalTimeFormat(userDateTime, "yyyy-MM-dd HH:mm:ss Z", "yyyy-MM-dd HH:mm:ss"));
                    Log.i(TAG, "updateNotificationAPICall:userDateTime " + userDateTime + " utc: " + tempUserDateTime);
                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
//                    paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                    paramModel.setIs_fully_charged(notify_ev_scompat.isChecked() ? 1 : 0);
                    paramModel.setIs_unexpectedly_interrupted(notify_if_interrupt_scompat.isChecked() ? 1 : 0);
                    paramModel.setIs_notify_to_plugin_mobile(notify_ev_plugin_scompat.isChecked() ? 1 : 0);
//                    paramModel.setNotify_plugin_time(Utils.getUTCTimeFromLocal(selectedTime));
                    paramModel.setNotify_plugin_time(Utils.getUTCTimeFromLocal(userDateTime));
                    paramModel.setUser_datetime(tempUserDateTime);
                    paramModel.setUser_timezone(userTimezone);
                    Log.i(TAG, "updateNotificationAPICall:SelectedUTC " + Utils.getUTCTimeFromLocal(userDateTime));
                    Log.i(TAG, "updateNotificationAPICall:userTimeUTC " + tempUserDateTime);
                    Log.i(TAG, "updateNotificationAPICall:TimeZone " + userTimezone);
                    notificationViewModel.updateNotificationViewModel(token, paramModel).observe((LifecycleOwner) mContext,
                            commonResponseModelApiResponse -> getUpdatedNotificationResponse(commonResponseModelApiResponse));
                });
            } else
                Utils.showCustomAlert(mContext, getString(R.string.alert), "Please select time", "time_empty");
        } else {
            new Handler().post(() -> {
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
//                paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                paramModel.setIs_fully_charged(notify_ev_scompat.isChecked() ? 1 : 0);
                paramModel.setIs_unexpectedly_interrupted(notify_if_interrupt_scompat.isChecked() ? 1 : 0);
                paramModel.setIs_notify_to_plugin_mobile(0);
                paramModel.setNotify_plugin_time("00:00");
                paramModel.setUser_datetime("0000-00-00 00:00:00 +0000");
                paramModel.setUser_timezone(userTimezone);
                notificationViewModel.updateNotificationViewModel(token, paramModel).observe((LifecycleOwner) mContext,
                        commonResponseModelApiResponse -> getUpdatedNotificationResponse(commonResponseModelApiResponse));
            });
        }
    }

    private void getUpdatedNotificationResponse(ApiResponse<CommonResponseModel> commonResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        CommonResponseModel commonResponseModel;
        if (commonResponseModelApiResponse.getData() != null) {
            commonResponseModel = commonResponseModelApiResponse.getData();
            switch (commonResponseModelApiResponse.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (commonResponseModel != null)
                        updatedNotificationUI(commonResponseModel);
                    else if (commonResponseModel.getError().equals(getString(R.string.invalid_token)))
                        Utils.showOKCustomAlert(mContext, null, "Session expired!",
                                "session_expired");
                    else
                        Utils.showCustomAlert(mContext, getString(R.string.alert), commonResponseModelApiResponse.getData().getError(), "CreateAccFailed");
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
    }

    private void updatedNotificationUI(CommonResponseModel commonResponseModel) {
        if (commonResponseModel.getStatus().equals("1")) {
            toastMessage(commonResponseModel.getMessage());
        } else
            Utils.showCustomAlert(mContext, getString(R.string.alert), commonResponseModel.getMessage(), "update_notify_fail");
    }

    //setting launching  fragment
    /*public void setFragment(Fragment fragment) {
        //FOr initially add stack for backpress event
        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.qmulus_frame, fragment, Constant.BACK_STACK_ZERO);
        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
