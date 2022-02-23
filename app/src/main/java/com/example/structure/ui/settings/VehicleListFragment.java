package com.example.structure.ui.settings;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.MakeModelResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.VehicleListResponseModel;
import com.example.structure.data.models.YearListResponseModel;
import com.example.structure.support.CustomNumberPicker;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.support.RecyclerTouchListener;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.ui.settings.adapter.VehicleListAdapter;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.AddElectricVehicleViewModel;
import com.example.structure.viewmodel.VehicleViewModel;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleListFragment extends BaseFragment {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private VehicleListAdapter vehicleListAdapter;
    private RecyclerTouchListener touchListener;
    private CustomProgressDialog customProgressDialog;
    private AddElectricVehicleViewModel addElectricVehicleViewModel;
    private VehicleViewModel vehicleViewModel;
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
    @BindView(R.id.vehicle_list_rview)
    RecyclerView vehicle_list_rview;
    @BindView(R.id.no_rec_found_lbl)
    TextView no_rec_found_lbl;

    private NavController navController;
    ArrayList<VehicleListResponseModel.UserVehiclesBean> vehicleArrayList;
    private String[] yearListStrings, makeListStrings, modelListStrings;
    private String itemName;
    private int indexPos = 0, disableVehicleId = -1;

    public VehicleListFragment() {
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
        vehicleViewModel = ViewModelProviders.of(this, viewModelFactory).get(VehicleViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((BottomMenuActivity) mContext).setNavigationalVisibility(false);
        if(getArguments() != null) {
            String bundleTag = getArguments().getString("tag");
            Log.i(TAG, "initUI:bundleTag " + bundleTag);
            no_rec_found_lbl.setVisibility(View.VISIBLE);
            if (bundleTag != null)
                settings_img.performClick();
        } else {
            no_rec_found_lbl.setVisibility(View.GONE);
            if (sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
                vehicelListAPICall();
            }
        }
    }

    /**
     * Get the Year List
     */
    private void getYearAPICall() {
        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
        new Handler().post(() -> addElectricVehicleViewModel.getYearViewModel().observe((LifecycleOwner) mContext, yearListResponseModelApiResponse ->
                yearListResponse(yearListResponseModelApiResponse)));
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
        View view = inflater.inflate(R.layout.fragment_vehicle_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();
        initUI();
        return view;
    }

    private void initUI() {
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();

        navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
        touchListener = new RecyclerTouchListener(getActivity(), vehicle_list_rview);
        touchListener.setClickable(new RecyclerTouchListener.OnRowClickListener() {
            @Override
            public void onRowClicked(int position) {
                toastMessage(vehicleArrayList.get(position).getMake());
            }

            @Override
            public void onIndependentViewClicked(int independentViewID, int position) {

            }
        }).setSwipeOptionViews(R.id.delete_img, R.id.edit_img)
                .setSwipeable(R.id.vehicle_row_item_llay, R.id.swipe_menu_item_llay, (viewID, position) -> {
                    switch (viewID) {
                        case R.id.delete_img:
//                            vehicleArrayList.remove(position);
//                            vehicleListAdapter.updateVehicleList(vehicleArrayList, position);
                            int deleteVehicleId = vehicleArrayList.get(position).getVehicle_id();
                            Log.i(TAG, "initUI:getVehicle_id " + deleteVehicleId + " disableVehicleId:" + disableVehicleId);
                            if(disableVehicleId == deleteVehicleId) {
                                Utils.showOKCustomAlert(mContext, null, "Unable to delete the vehicle because it's connected to evmileage control.", "disableVehicleId");
                            } else {
                                if (deleteVehicleId > 0) {
                                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                                    String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
//                                    ParamModel paramModel = new ParamModel();
//                                    paramModel.setVehicle_id(vehicleArrayList.get(position).getVehicle_id());
//                                    paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                                    vehicleViewModel.getDeleteVehicleResponse(token, String.valueOf(vehicleArrayList.get(position).getVehicle_id()))
                                            .observe((LifecycleOwner) mContext, commonResponseModelApiResponse -> commonResponse(commonResponseModelApiResponse));
                                }
                            }
                            break;
                        case R.id.edit_img:
                            toastMessage("Edit Available");
                            int editVehicleId = vehicleArrayList.get(position).getVehicle_id();
                            Log.i(TAG, "initUI:getVehicle_id " + editVehicleId + " disableVehicleId:" + disableVehicleId);
                            if(disableVehicleId == editVehicleId) {
                                Utils.showOKCustomAlert(mContext, null, "Unable to edit the vehicle because it's connected to evmileage control.", "disableVehicleId");
                            } else
                                showAddVehicleAlert(mContext, "update_vehicle", vehicleArrayList.get(position));
//                            vehicleViewModel.getAddVehicleResponse("Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN), paramModel)
//                                    .observe((LifecycleOwner) mContext, commonResponseModelApiResponse  -> commonResponse(commonResponseModelApiResponse)));
                            break;

                    }
                });
    }

    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[]{title_lbl, no_rec_found_lbl});
//        Utils.setBoldFonts(mContext, new TextView[]{sca_lbl});
    }

    private void setHeader() {
        settings_img.setImageResource(R.drawable.ic_add);
        ic_back_arrow.setVisibility(View.VISIBLE);
        settings_img.setVisibility(View.VISIBLE);
        title_lbl.setText(getString(R.string.vehicles));
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.settings_img, R.id.ic_back_arrow})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.ic_back_arrow:
                navController.popBackStack();
                break;
            case R.id.settings_img:
//                getYearAPICall();
                showAddVehicleAlert(mContext, "new_vehicle", null);
                break;
        }
    }

    /**
     * call the vehicle list API
     */
    private void vehicelListAPICall() {
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
//            ParamModel paramModel = new ParamModel();
//            paramModel.setUser_id(user_id);
//            Log.i(TAG, "dashboardAPICall: " + paramModel.getUser_id());
            new Handler().post(() -> vehicleViewModel.getVehicleListResponse(token)
                    .observe((LifecycleOwner) mContext, vehicleListResponseModelApiResponse -> vehicleListResponse(vehicleListResponseModelApiResponse)));
        }
    }

    private void setVehicleListRecyclerViewAdapter(ArrayList<VehicleListResponseModel.UserVehiclesBean> vehicleArrayList) {
        vehicleListAdapter = new VehicleListAdapter(mContext, vehicleArrayList, disableVehicleId);
        vehicle_list_rview.setLayoutManager(new LinearLayoutManager(mContext, VERTICAL, false));
        vehicle_list_rview.setHasFixedSize(true);
        vehicle_list_rview.setNestedScrollingEnabled(false);
        vehicle_list_rview.setItemAnimator(new DefaultItemAnimator());
        vehicle_list_rview.setAdapter(vehicleListAdapter);
    }

    //setting launching  fragment
    /*public void setFragment(Fragment fragment) {
        //For initially add stack for backpress event
//        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.qmulus_frame, fragment);
        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }*/

    private void getVehicleListUI(VehicleListResponseModel vehicleListResponseModel) {
        if (vehicleListResponseModel.getStatus().equals("1")) {
            disableVehicleId = vehicleListResponseModel.getDisableVehicleId();
            if (vehicleListResponseModel.getUserVehicles().size() > 0) {
                vehicle_list_rview.setVisibility(View.VISIBLE);
                no_rec_found_lbl.setVisibility(View.GONE);
                vehicleArrayList = new ArrayList<>();
                for (int i = 0; i < vehicleListResponseModel.getUserVehicles().size(); i++) {
                    VehicleListResponseModel.UserVehiclesBean tempUserVehiclesBean;
                    tempUserVehiclesBean = vehicleListResponseModel.getUserVehicles().get(i);
                    VehicleListResponseModel.UserVehiclesBean userVehiclesBean = new VehicleListResponseModel.UserVehiclesBean();
                    userVehiclesBean.setVehicle_id(tempUserVehiclesBean.getVehicle_id());
                    userVehiclesBean.setYear(tempUserVehiclesBean.getYear());
                    userVehiclesBean.setMake(tempUserVehiclesBean.getMake());
                    userVehiclesBean.setModel(tempUserVehiclesBean.getModel());
                    userVehiclesBean.setRange(tempUserVehiclesBean.getRange());
                    userVehiclesBean.setRangeA(tempUserVehiclesBean.getRangeA());
                    userVehiclesBean.setCombE(tempUserVehiclesBean.getCombE());
                    vehicleArrayList.add(userVehiclesBean);
                }
                setVehicleListRecyclerViewAdapter(vehicleArrayList);
            } else {
                vehicle_list_rview.setVisibility(View.GONE);
                no_rec_found_lbl.setVisibility(View.VISIBLE);
            }
        }
    }

    private void updateYear(YearListResponseModel yearListResponseModel) {
        if (yearListResponseModel.getStatus().equals("1")) {
            if (yearListResponseModel.getYear_list().size() > 0) {
                yearListStrings = new String[yearListResponseModel.getYear_list().size()];
                for (int i = 0; i < yearListResponseModel.getYear_list().size(); i++) {
                    yearListStrings[i] = yearListResponseModel.getYear_list().get(i);
                }
            }
        }
    }

    private void vehicleListResponse(ApiResponse<VehicleListResponseModel> vehicleListResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        VehicleListResponseModel vehicleListResponseModel = vehicleListResponseModelApiResponse.getData();
        switch (vehicleListResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (vehicleListResponseModel.getStatus().equals("1")) {
                    toastMessage(vehicleListResponseModel.getSuccess() + " API Success");
                    getVehicleListUI(vehicleListResponseModel);
                } else if (vehicleListResponseModel.getError().equals("TOKEN_INVALID")) {
                    sharedPreferencesUtil.deleteAllSession();
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                } else
                    Utils.showCustomAlert(mContext, getString(R.string.alert), vehicleListResponseModel.getError(),
                            "dashboard_failed");
                break;
            case FAILURE:
                Utils.showCustomAlert(mContext, getString(R.string.alert),
                        Utils.getConvertedErrorBody(vehicleListResponseModelApiResponse.getFailureResponse()), "dashboard_failed");
                break;
            case ERROR:
                Utils.handleErrorResponse(vehicleListResponseModelApiResponse.getError(), mContext);
                break;
        }
    }

    private void commonResponse(ApiResponse<CommonResponseModel> commonResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        CommonResponseModel commonResponseModel = commonResponseModelApiResponse.getData();
        switch (commonResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (commonResponseModel.getStatus().equals("1")) {
                    toastMessage(commonResponseModel.getMessage());
//                    if (sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
                        vehicelListAPICall();
//                    }
//                    getVehicleListUI(commonResponseModel);
                } else if (commonResponseModel.getError().equals("TOKEN_INVALID")) {
                    sharedPreferencesUtil.deleteAllSession();
                    Utils.showOKCustomAlert(mContext, getString(R.string.alert), "Session expired!",
                            "session_expired");
                } else
                    Utils.showOKCustomAlert(mContext, null, commonResponseModel.getError(),
                            "vehicle_list_failed");
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

    /**
     * Show Add Vehicle Alert
     */
    @SuppressLint("ClickableViewAccessibility")
    private void showAddVehicleAlert(final Context context, String from, VehicleListResponseModel.UserVehiclesBean userVehiclesBean) {
        getYearAPICall();
        final Dialog dialog = new Dialog(context, R.style.add_vehicle_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_vehicle);
        dialog.setCancelable(false);
//        dialog.getWindow().setGravity(Gravity.END);
//        dialog.getWindow().setLayout(((getWidth(context) / 100) * 90), LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();

        ImageView ic_close = dialog.findViewById(R.id.ic_close);
        TextView add_vehicle_lbl = dialog.findViewById(R.id.add_vehicle_lbl);
        TextView year_lbl = dialog.findViewById(R.id.year_lbl);
        LinearLayout year_layout = dialog.findViewById(R.id.year_layout);
        EditText year_etext = dialog.findViewById(R.id.year_etext);
        TextView make_lbl = dialog.findViewById(R.id.make_lbl);
        LinearLayout make_layout = dialog.findViewById(R.id.make_layout);
        EditText make_etext = dialog.findViewById(R.id.make_etext);
        TextView model_lbl = dialog.findViewById(R.id.model_lbl);
        LinearLayout model_layout = dialog.findViewById(R.id.model_layout);
        EditText model_etext = dialog.findViewById(R.id.model_etext);
        Button add_btn = dialog.findViewById(R.id.add_btn);

        Utils.setBoldFonts(context, new TextView[]{add_btn, add_vehicle_lbl, year_lbl, make_lbl, model_lbl});
        Utils.setRegularFonts(context, new TextView[]{year_etext, make_etext, model_etext});

        disableEdittextInput(year_etext);
        disableEdittextInput(make_etext);
        disableEdittextInput(model_etext);

        year_etext.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                String existingVal = null;
                if (year_etext.getText().toString().trim().length() > 0)
                    existingVal = year_etext.getText().toString();
                try {
                    if (yearListStrings.length > 0)
                        pickerDialog(mContext, yearListStrings, indexPos - 2,
                                year_etext, "Select Year", existingVal, year_etext, make_etext, model_etext);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return true;
        });

        make_etext.setOnTouchListener((view, motionEvent) -> {
            if (year_etext.getText().toString().length() > 0) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    String existingVal = null;
                    if (make_etext.getText().toString().trim().length() > 0)
                        existingVal = make_etext.getText().toString();
                    try {
                        if (makeListStrings.length > 0)
                            pickerDialog(mContext, makeListStrings, indexPos - 2,
                                    make_etext, "Select Make", existingVal, year_etext, make_etext, model_etext);
                    } catch ( NullPointerException e) {
                        e.printStackTrace();
                    }
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
                    try {
                        if (modelListStrings.length > 0)
                            pickerDialog(mContext, modelListStrings, indexPos - 2,
                                    model_etext, "Select Model", existingVal, year_etext, make_etext, model_etext);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        });

        ic_close.setOnClickListener(view -> {
            dialog.dismiss();
//            dialog.cancel();
        });

        if (from.equalsIgnoreCase("update_vehicle") && userVehiclesBean != null) {
            year_etext.setText(userVehiclesBean.getYear());
            make_etext.setText(userVehiclesBean.getMake());
            model_etext.setText(userVehiclesBean.getModel());
            add_btn.setText(getString(R.string.update));
            if (year_etext.getText().toString().length() > 0) {
//                ParamModel paramModel = new ParamModel();
//                paramModel.setYear(year_etext.getText().toString());
                String year = year_etext.getText().toString();
                new Handler().post(() -> {
//                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                    addElectricVehicleViewModel.getMakeResponse(year).observe((LifecycleOwner) context, makeModelResponseModelApiResponse ->
                            getMakeModelResponse(makeModelResponseModelApiResponse));
                });
            }
            if (make_etext.getText().toString().length() > 0) {
//                ParamModel paramModel = new ParamModel();
//                paramModel.setYear(year_etext.getText().toString());
//                paramModel.setMake(make_etext.getText().toString());
                String year = year_etext.getText().toString();
                String make = make_etext.getText().toString();
                new Handler().post(() -> {
//                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                    addElectricVehicleViewModel.getModelResponse(year, make).observe((LifecycleOwner) context, makeModelResponseModelApiResponse ->
                            getMakeModelResponse(makeModelResponseModelApiResponse));
                });
            }
        }

        add_btn.setOnClickListener(view -> {
            if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
                String year = year_etext.getText().toString();
                String make = make_etext.getText().toString();
                String model = model_etext.getText().toString();
                if (!TextUtils.isEmpty(year)) {
                    if (!TextUtils.isEmpty(make)) {
                        if (!TextUtils.isEmpty(model)) {
                            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
                            ParamModel paramModel = new ParamModel();
//                            paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                            paramModel.setYear(year);
                            paramModel.setMake(make);
                            paramModel.setModel(model);
                            if (add_btn.getText().equals("Update")) {
                                paramModel.setVehicle_id(userVehiclesBean.getVehicle_id());
                                new Handler().post(() -> vehicleViewModel.geUpdatedVehicleResponse(token, paramModel)
                                        .observe((LifecycleOwner) mContext, commonResponseModelApiResponse -> commonResponse(commonResponseModelApiResponse)));
                            } else {
                                new Handler().post(() -> vehicleViewModel.getAddVehicleResponse(token, paramModel)
                                        .observe((LifecycleOwner) mContext, commonResponseModelApiResponse -> commonResponse(commonResponseModelApiResponse)));
                            }
                            dialog.dismiss();
                        } else toastMessage("The Model field is required.");
                    } else toastMessage("The Make field is required.");
                } else toastMessage("The Year field is required.");
            }

//            dialog.cancel();
        });
    }

    /**
     * display the dialog which contains secret questions
     * @param existingValue will shown when value is available
     */
    private void pickerDialog(final Context context, String[] list, int index, EditText eText, String pickerType,
                              String existingValue, EditText year_etext, EditText make_etext, EditText model_etext) {
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
//                ParamModel paramModel = new ParamModel();
//                paramModel.setYear(selectedYear);
//                paramModel.setMake(itemName);
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

    private void yearListResponse(ApiResponse<YearListResponseModel> apiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        YearListResponseModel yearListResponseModel = apiResponse.getData();
        switch (apiResponse.getStatus()) {
            case LOADING:
                break;

            case SUCCESS:
                if (yearListResponseModel != null)
                    updateYear(yearListResponseModel);
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

    @Override
    public void onResume() {
        super.onResume();
        vehicle_list_rview.addOnItemTouchListener(touchListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
