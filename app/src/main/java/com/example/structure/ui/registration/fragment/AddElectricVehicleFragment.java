package com.example.structure.ui.registration.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.daos.CreateAccountDao;
import com.example.structure.data.models.MakeModelResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.YearListResponseModel;
import com.example.structure.support.CustomNumberPicker;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.AddElectricVehicleViewModel;

import java.util.Arrays;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.annotation.Nullable;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AddElectricVehicleFragment extends BaseFragment {

    private String TAG = getClass().getSimpleName();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtil sharedPreferences;
    @Inject
    Executor executor;
    @Inject
    CreateAccountDao createAccountDao;
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private AddElectricVehicleViewModel addElectricVehicleViewModel;
    private CustomProgressDialog customProgressDialog;

    @BindView(R.id.add_vehicle_lbl)
    TextView add_vehicle_lbl;
    @BindView(R.id.skip_btn)
    Button skip_btn;
    @BindView(R.id.year_lbl)
    TextView year_lbl;
    @BindView(R.id.year_etext)
    EditText year_etext;
    @BindView(R.id.make_lbl)
    TextView make_lbl;
    @BindView(R.id.make_etext)
    EditText make_etext;
    @BindView(R.id.model_lbl)
    TextView model_lbl;
    @BindView(R.id.model_etext)
    EditText model_etext;
    @BindView(R.id.continue_btn)
    Button continue_btn;

    private String[] yearListStrings, makeListStrings, modelListStrings;
    private String itemName;
    private int indexPos = 0;

    public AddElectricVehicleFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    /**
     * Initialize the Add Electric Vehicle Viewmodel
     */
    private void configureViewModel() {
        addElectricVehicleViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddElectricVehicleViewModel.class);
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
        View view = inflater.inflate(R.layout.fragment_add_electric_vehicle, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        fragmentManager = getFragmentManager();
        initUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
        getYearAPICall();
    }

    /**
     * Get the Year List
     */
    private void getYearAPICall() {
        new Handler().post(() -> addElectricVehicleViewModel.getYearViewModel().observe((LifecycleOwner) mContext, yearListResponseModelApiResponse -> yearListResponse(yearListResponseModelApiResponse)));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
        setStatusBarGradiant(getActivity());
        setFont();
        disableEdittextInput(year_etext);
        disableEdittextInput(make_etext);
        disableEdittextInput(model_etext);

        year_etext.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                String existingVal = null;
                if (year_etext.getText().toString().trim().length() > 0)
                    existingVal = year_etext.getText().toString();
                if (yearListStrings.length > 0)
                    pickerDialog(mContext, yearListStrings, indexPos - 2,
                            year_etext, "Select Year", existingVal);
            }
            return true;
        });

        make_etext.setOnTouchListener((view, motionEvent) -> {
            if (year_etext.getText().toString().length() > 0) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    String existingVal = null;
                    if (make_etext.getText().toString().trim().length() > 0)
                        existingVal = make_etext.getText().toString();
                    if (makeListStrings.length > 0)
                        pickerDialog(mContext, makeListStrings, indexPos - 2,
                                make_etext, "Select Make", existingVal);
                }
            }
            return true;
        });

        model_etext.setOnTouchListener((view, motionEvent) -> {
            if (make_etext.getText().toString().length() > 0) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    String existingVal = null;
                    if (model_etext.getText().toString().trim().length() > 0)
                        existingVal = model_etext.getText().toString();
                    if (modelListStrings.length > 0)
                        pickerDialog(mContext, modelListStrings, indexPos - 2,
                                model_etext, "Select Model", existingVal);
                }
            }
            return true;
        });
    }

    private void setFont() {
        Utils.setLightFonts(mContext, new TextView[] {add_vehicle_lbl});
        Utils.setRegularFonts(mContext, new TextView[]{skip_btn, make_etext, model_etext, year_etext});
        Utils.setBoldFonts(mContext, new TextView[]{make_lbl, model_lbl, year_lbl, continue_btn});
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.continue_btn, R.id.skip_btn})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.continue_btn:
                String year = year_etext.getText().toString();
                String make = make_etext.getText().toString();
                String model = model_etext.getText().toString();
                String email = sharedPreferences.getUserEmail(Constant.USER_EMAIL);
                if (!TextUtils.isEmpty(year)) {
                    if (!TextUtils.isEmpty(make)) {
                        if (!TextUtils.isEmpty(model)) {
                            if (!TextUtils.isEmpty(email)) {
                                new Handler().post(() -> {
                                    createAccountDao.updateCreateAccount2(year, make, model, email);
                                    setFragment(new NotificaionsCreateAccountFragment());
                                });
                            }
                        } else
                            Utils.showOKCustomAlert(mContext, null, "The Model field is required.", "model_empty");
                    } else
                        Utils.showOKCustomAlert(mContext, null, "The Make field is required.", "make_empty");
                } else
                    Utils.showOKCustomAlert(mContext, null, "The Year field is required.", "year_empty");

                break;
            case R.id.skip_btn:
                String email1 = sharedPreferences.getUserEmail(Constant.USER_EMAIL);
                if (!TextUtils.isEmpty(email1)) {
                    new Handler().post(() -> {
                        createAccountDao.updateCreateAccount2("", "", "", email1);
                        setFragment(new NotificaionsCreateAccountFragment());
                    });
                }
                break;
        }
    }

    private void yearListResponse(ApiResponse<YearListResponseModel> apiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        YearListResponseModel yearListResponseModel = apiResponse.getData();
        switch (apiResponse.getStatus()) {
            case LOADING:
                break;

            case SUCCESS:
                if (yearListResponseModel != null)
                    updateUI(yearListResponseModel);
                else if (yearListResponseModel.getError().equals("TOKEN_INVALID"))
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                else
                    Utils.showCustomAlert(getContext(), getString(R.string.alert), apiResponse.getData().getError(), "AddElectricVehicle");
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

    private void getMakeModelResponse(ApiResponse<MakeModelResponseModel> apiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        MakeModelResponseModel makeModelResponseModel = apiResponse.getData();
        switch (apiResponse.getStatus()) {
            case LOADING:
                break;

            case SUCCESS:
                if (makeModelResponseModel != null) getMakeModel(makeModelResponseModel);
                else if (makeModelResponseModel.getError().equals(getString(R.string.invalid_token)))
                    Utils.showOKCustomAlert(mContext, null, "Session expired!", "session_expired");
                else
                    Utils.showCustomAlert(getContext(), getString(R.string.alert), apiResponse.getData().getError(), "AddElectricVehicle");
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

    private void updateUI(YearListResponseModel yearListResponseModel) {
        if (yearListResponseModel.getStatus().equals("1")) {
            if (yearListResponseModel.getYear_list().size() > 0) {
                yearListStrings = new String[yearListResponseModel.getYear_list().size()];
                for (int i = 0; i < yearListResponseModel.getYear_list().size(); i++) {
                    yearListStrings[i] = yearListResponseModel.getYear_list().get(i);
                }
            }
        }
    }

    private void getMakeModel(MakeModelResponseModel makeModelResponseModel) {
        if (makeModelResponseModel.getStatus().equals("1")) {
            if (makeModelResponseModel.getSuccess().equalsIgnoreCase("Make list")) {
                if (makeModelResponseModel.getMake_list().size() > 0) {
                    makeListStrings = new String[makeModelResponseModel.getMake_list().size()];
                    for (int i = 0; i < makeModelResponseModel.getMake_list().size(); i++) {
                        makeListStrings[i] = makeModelResponseModel.getMake_list().get(i);
                    }
                }
            } else if (makeModelResponseModel.getSuccess().equalsIgnoreCase("Model list")) {
                if (makeModelResponseModel.getModel_list().size() > 0) {
                    modelListStrings = new String[makeModelResponseModel.getModel_list().size()];
                    for (int i = 0; i < makeModelResponseModel.getModel_list().size(); i++) {
                        modelListStrings[i] = makeModelResponseModel.getModel_list().get(i);
                    }
                }
            }
        }
    }

    //setting launching  fragment
    public void setFragment(Fragment fragment) {
        //For initially add stack for backpress event
//        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(Constant.BACK_STACK_ROOT_TAG);;
//        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.login_frame, fragment);
//        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }

    /**
     * display the dialog which contains secret questions
     * @param existingValue will shown when value is available
     */
    private void pickerDialog(final Context context, String[] list, int index, EditText eText, String pickerType,
                              String existingValue) {
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
        Arrays.sort(list);
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
            } else {
                itemName = list[0];
                eText.setText(itemName);
            }
            eText.setError(null);

            if (pickerType.equals("Select Year") && !TextUtils.isEmpty(itemName)) {
                make_etext.setText("");
                model_etext.setText("");
                make_etext.setEnabled(true);
                model_etext.setEnabled(false);
//                ParamModel paramModel = new ParamModel();
//                paramModel.setYear(itemName);
                String year = itemName;
                new Handler().post(() -> {
                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                    addElectricVehicleViewModel.getMakeResponse(year).observe((LifecycleOwner) context, makeModelResponseModelApiResponse ->
                            getMakeModelResponse(makeModelResponseModelApiResponse));
                });
            } else if (pickerType.equals("Select Make") && !TextUtils.isEmpty(itemName)) {
                model_etext.setText("");
                model_etext.setEnabled(true);
                String selectedYear = year_etext.getText().toString();
                ParamModel paramModel = new ParamModel();
                paramModel.setYear(selectedYear);
                paramModel.setMake(itemName);
                String make = itemName;
                new Handler().post(() -> {
                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                    addElectricVehicleViewModel.getModelResponse(selectedYear, make).observe((LifecycleOwner) context, makeModelResponseModelApiResponse ->
                            getMakeModelResponse(makeModelResponseModelApiResponse));
                });
            }

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
