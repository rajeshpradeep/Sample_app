package com.example.structure.ui.registration.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.daos.CreateAccountDao;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.CreateAccountParamModel;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.login.fragment.LoginFragment;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.CreateAccountViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificaionsCreateAccountFragment extends BaseFragment {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    CreateAccountDao createAccountDao;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    private CreateAccountViewModel createAccountViewModel;
    private CustomProgressDialog customProgressDialog;

    @BindView(R.id.notification_lbl)
    TextView notification_lbl;
    @BindView(R.id.notify_ev_tview)
    TextView notify_ev_tview;
    @BindView(R.id.notify_if_interrupt_tview)
    TextView notify_if_interrupt_tview;
    @BindView(R.id.notify_ev_plugin_tview)
    TextView notify_ev_plugin_tview;
    @BindView(R.id.notify_ev_scompat)
    SwitchCompat notify_ev_scompat;
    @BindView(R.id.notify_if_interrupt_scompat)
    SwitchCompat notify_if_interrupt_scompat;
    @BindView(R.id.notify_ev_plugin_scompat)
    SwitchCompat notify_ev_plugin_scompat;

    @BindView(R.id.notify_me_at_rlay)
    LinearLayout notify_me_at_rlay;
    @BindView(R.id.notify_me_at_lbl)
    TextView notify_me_at_lbl;
    @BindView(R.id.notify_time_layout)
    LinearLayout notify_time_layout;
    @BindView(R.id.notify_time_etext)
    EditText notify_time_etext;
    @BindView(R.id.notify_me_end_dview)
    View notify_me_end_dview;
    @BindView(R.id.finish_btn)
    Button finish_btn;
    private Date dateAndTIme;
    private SimpleDateFormat userTimeFormat;
    private String selectedTime, isFullyCharged, isChargeInterrupted, isPluginEV, user_datetime, user_timezone;

    public NotificaionsCreateAccountFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    /**
     * Initialize the Notification Viewmodel
     */
    private void configureViewModel() {
        createAccountViewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateAccountViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureDagger();
        configureViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notificaions_create_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        fragmentManager = getFragmentManager();
        selectedTime = "invalid";
        isFullyCharged = "off";
        isChargeInterrupted = "off";
        isPluginEV = "off";
        initUI();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
        setFont();
        disableEdittextInput(notify_time_etext);
        notify_ev_plugin_scompat.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                isPluginEV = "on";
                notify_me_at_rlay.setVisibility(View.VISIBLE);
                notify_me_end_dview.setVisibility(View.VISIBLE);
            } else {
                isPluginEV = "off";
                notify_me_at_rlay.setVisibility(View.GONE);
                notify_me_end_dview.setVisibility(View.GONE);
            }
        });

        /*isFullyCharged = notify_ev_scompat.isChecked() ? "on" : "off";
        isChargeInterrupted = notify_if_interrupt_scompat.isChecked() ? "on" : "off";
        isPluginEV = notify_ev_plugin_scompat.isChecked() ? "on" : "off";*/

        notify_ev_scompat.setOnCheckedChangeListener((compoundButton, b) -> isFullyCharged = b ? "on" : "off");

        notify_if_interrupt_scompat.setOnCheckedChangeListener((compoundButton, b) -> isChargeInterrupted = b ? "on" : "off");

//        notify_ev_plugin_scompat.setOnCheckedChangeListener((compoundButton, b) -> isPluginEV = b ? "on" : "off");

        notify_time_etext.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, (view1, hourOfDay, minute) -> {
                    selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                    boolean isPM = (hourOfDay >= 12);
                    @SuppressLint("DefaultLocale") String displaySelectedTime = (String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                    notify_time_etext.setText(displaySelectedTime);
                }, mHour, mMinute, true);// 24 hour format
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
            return true;
        });

    }

    private void setFont() {
        Utils.setLightFonts(mContext, new TextView[]{notification_lbl});
        Utils.setRegularFonts(mContext, new TextView[]{notify_ev_tview, notify_if_interrupt_tview,
                notify_ev_plugin_tview, notify_time_etext, notify_me_at_lbl});
        Utils.setBoldFonts(mContext, new TextView[]{finish_btn});
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.finish_btn, R.id.notify_time_etext})
    void buttonAction(View view) {
        if (view.getId() == R.id.finish_btn) {
            String email = sharedPreferencesUtil.getUserEmail(Constant.USER_EMAIL);
     /*       SimpleDateFormat userTimeFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm a", Locale.US);
            Calendar calendar = Calendar.getInstance();
            String user_datetime = userTimeFormat.format(calendar.getTime());*/

            try {
                if (!selectedTime.equalsIgnoreCase("invalid")) {
                    userTimeFormat = new SimpleDateFormat("yyyy-MM-dd:ss Z", Locale.getDefault());
                    String dateAndZone = userTimeFormat.format(dateAndTIme);
                    String[] split = dateAndZone.split(":");
                    user_datetime = split[0] + " " + selectedTime + ":" + split[1];
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            user_timezone = TimeZone.getDefault().getID();
            if (notify_ev_plugin_scompat.isChecked()) {
                if (notify_time_etext.getText().toString().length() > 0) {
                    new Handler().post(() -> {
                        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                        createAccountDao.updateCreateAccount3(isFullyCharged, isChargeInterrupted, isPluginEV,
                                (Utils.getUTCTimeFromLocal(user_datetime)),
                                Utils.getUTCTimeFromLocalTimeFormat(user_datetime, "yyyy-MM-dd HH:mm:ss Z", "yyyy-MM-dd HH:mm:ss"),
                                user_timezone, email);
                        CreateAccountParamModel createAccountParamModel = createAccountDao.getCreateAccountDetails();
                        createAccountAPI(createAccountParamModel);
                    });
                } else
                    Utils.showCustomAlert(mContext, getString(R.string.alert), "Please select time", "time_empty");
            } else {
                new Handler().post(() -> {
                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                    createAccountDao.updateCreateAccount3(isFullyCharged, isChargeInterrupted, isPluginEV,
                            "00:00", "0000-00-00 00:00:00 +0000", user_timezone, email);
                    CreateAccountParamModel createAccountParamModel = createAccountDao.getCreateAccountDetails();
                    createAccountAPI(createAccountParamModel);
                });
            }
        }
    }

    private void createAccountAPI(CreateAccountParamModel createAccountParamModel) {
        Log.i(TAG, "createAccountAPI:request Params " + createAccountParamModel.toString());
        createAccountViewModel.callSignUpViewmodel(createAccountParamModel).observe((LifecycleOwner) mContext,
                commonResponseModelApiResponse -> signUpResponse(commonResponseModelApiResponse));
    }

    private void signUpResponse(ApiResponse<CommonResponseModel> apiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        CommonResponseModel commonResponseModel;
        if (apiResponse.getData() != null) {
            commonResponseModel = apiResponse.getData();
            switch (apiResponse.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (commonResponseModel != null)
                        updateUI(commonResponseModel);
                    else if (commonResponseModel.getError().equals(getString(R.string.invalid_token)))
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

    private void updateUI(CommonResponseModel commonResponseModel) {
        if (commonResponseModel.getStatus().equals("1")) {
            if (commonResponseModel.getUser_id() > 0) {
                sharedPreferencesUtil.saveToken(Constant.USER_TOKEN, commonResponseModel.getAuth_token());
                /*Intent intent = new Intent(mContext, BottomMenuActivity.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();*/
                showOKCustomAlert(mContext, "Thank you for signing up!", "Your account is not verified, use the verification link in the email.",
                        "signup_success");
            }
        } else
            Utils.showOKCustomAlert(mContext, null, commonResponseModel.getMessage(), "signup_failed");
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

        TextView dialog_title = dialog.findViewById(R.id.title_tview);
        TextView dialog_message = dialog.findViewById(R.id.message_tview);
        TextView dialog_ok_btn = dialog.findViewById(R.id.ok_btn);
        LinearLayout parent = dialog.findViewById(R.id.ok_dialog_layout);

        Utils.setBoldFonts(context, new TextView[]{dialog_ok_btn, dialog_title});
        Utils.setRegularFonts(context, new TextView[]{dialog_message});
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

        if (!TextUtils.isEmpty(message))
            dialog_message.setText(message);
        else
            dialog_message.setVisibility(View.GONE);

        if (from.equals("signup_success")) {
            dialog_ok_btn.setText(context.getString(R.string.ok));
            dialog_ok_btn.setOnClickListener(v -> {
                sharedPreferencesUtil.deleteAllSession();
                createAccountDao.deleteCreateAccountDetails();
                setFragment(new LoginFragment());
                dialog.cancel();
                dialog.dismiss();
            });
        } else {
            dialog_ok_btn.setText(context.getString(R.string.ok));
            dialog_ok_btn.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();

            });
        }
    }

    /**
     * Custom Alert
     */
    public void showCustomAlert(final Context context, String title, String message, final String from) {

        final Dialog dialog = new Dialog(context, R.style.custom_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.common_custom_dialog);

        TextView dialog_text = (TextView) dialog.findViewById(R.id.title);
        TextView dialog_message = (TextView) dialog.findViewById(R.id.subtitle);
        TextView dialog_positive = (TextView) dialog.findViewById(R.id.dialogButtonOK);
        TextView dialog_negative = (TextView) dialog.findViewById(R.id.dialogButtonNO);
        LinearLayout parent = (LinearLayout) dialog.findViewById(R.id.dialog_parent_layout);
        View dialog_separator = (View) dialog.findViewById(R.id.separator);

        Utils.setBoldFonts(context, new TextView[]{dialog_positive, dialog_negative});
        Utils.setRegularFonts(context, new TextView[]{dialog_text, dialog_message});

        if (!title.isEmpty())
            dialog_text.setText(title);
        else {
            dialog_text.setVisibility(View.GONE);
            int paddingDp = 25, paddingBottom = 15;
            float density = context.getResources().getDisplayMetrics().density;
            int paddingPixel = (int) (paddingDp * density);
            int paddingPixel2 = (int) (paddingBottom * density);
            dialog_message.setPadding(paddingPixel, paddingPixel, paddingPixel, paddingPixel2);
        }

        if (!TextUtils.isEmpty(message))
            dialog_message.setText(message);
        else
            dialog_message.setVisibility(View.GONE);

        if (from.equals("signup_success")) {
            dialog_negative.setVisibility(View.GONE);
            dialog_separator.setVisibility(View.GONE);
            dialog_positive.setText(context.getString(R.string.ok));
            dialog_negative.setText(context.getString(R.string.cancel));
            dialog_positive.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();
                sharedPreferencesUtil.deleteAllSession();
                createAccountDao.deleteCreateAccountDetails();
                setFragment(new LoginFragment());
            });
            dialog_negative.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();
            });
        } else {
            dialog_negative.setVisibility(View.GONE);
            dialog_separator.setVisibility(View.GONE);
            dialog_positive.setText("OK");
            dialog_positive.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();

            });
        }

        dialog.setCancelable(false);
        dialog.show();
    }

    //setting launching  fragment
    public void setFragment(Fragment fragment) {
        //FOr initially add stack for backpress event
//        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.login_frame, fragment, Constant.BACK_STACK_ZERO);
        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
