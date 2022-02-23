package com.example.structure.ui.session_info;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.DetailedSessionResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.SessionInfoResponseModel;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.ui.leaderboard.listener.PaginationListener;
import com.example.structure.ui.session_info.adapter.SessionInfoAdapter;
import com.example.structure.ui.session_info.listener.SessionItemListener;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.SwipeTouchListener;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.SessionInfoViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;
import static com.example.structure.ui.leaderboard.listener.PaginationListener.PAGE_START;

/**
 * Updated Chart By Prasanth.S 18.03.2020
 */
public
class SessionInfoFragment extends BaseFragment implements SessionItemListener {

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
    @BindView(R.id.session_info_rview)
    RecyclerView session_info_rview;
    @BindView(R.id.plugin_lbl)
    TextView plugin_lbl;
    @BindView(R.id.session_duration_lbl)
    TextView session_duration_lbl;
    @BindView(R.id.charge_duration_lbl)
    TextView charge_duration_lbl;
    @BindView(R.id.energy_kwh_lbl)
    TextView energy_kwh_lbl;
    @BindView(R.id.session_list_llay)
    LinearLayout session_list_llay;
    //    @BindView(R.id.session_swiperefresh)
//    SwipeRefreshLayout session_swiperefresh;
    /*@BindView(R.id.transaction_id_lbl)
    TextView transaction_id_lbl;
    @BindView(R.id.device_name_lbl)
    TextView device_name_lbl;
    @BindView(R.id.port_name_lbl)
    TextView port_name_lbl;
    @BindView(R.id.group_name_lbl)
    TextView group_name_lbl;
    @BindView(R.id.station_type_lbl)
    TextView station_type_lbl;
    @BindView(R.id.peak_power_lbl)
    TextView peak_power_lbl;
    @BindView(R.id.avg_power_lbl)
    TextView avg_power_lbl;
    @BindView(R.id.id_tag_lbl)
    TextView id_tag_lbl;
    @BindView(R.id.session_start_lbl)
    TextView session_start_lbl;
    @BindView(R.id.session_end_lbl)
    TextView session_end_lbl;
    @BindView(R.id.charge_start_lbl)
    TextView charge_start_lbl;
    @BindView(R.id.charge_end_lbl)
    TextView charge_end_lbl;
    @BindView(R.id.end_reason_lbl)
    TextView end_reason_lbl;
    @BindView(R.id.ghg_savings_lbl)
    TextView ghg_savings_lbl;
    @BindView(R.id.ghg_saving_gals_lbl)
    TextView ghg_saving_gals_lbl;
    @BindView(R.id.start_soc_lbl)
    TextView start_soc_lbl;
    @BindView(R.id.end_soc_lbl)
    TextView end_soc_lbl;
    @BindView(R.id.session_hscroll)
    HorizontalScrollView session_hscroll;
    @BindView(R.id.left_arrow_img)
    ImageView left_arrow_img;
    @BindView(R.id.right_arrow_img)
    ImageView right_arrow_img;*/
    @BindView(R.id.no_rec_found_lbl)
    TextView no_rec_found_lbl;
    @BindView(R.id.bottom_sheet)
    CoordinatorLayout bottom_sheet;
    @BindView(R.id.session_graph_chart)
    LineChart chart;
    @BindView(R.id.close_btn)
    View close_btn;
    private ArrayList<SessionInfoResponseModel.SessionInfoBean> sessionInfoList;
    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private SessionInfoViewModel sessionInfoViewModel;
    private CustomProgressDialog customProgressDialog;
    private SessionInfoAdapter sessionInfoAdapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage;
    private int totalPage, limit, totalRecCount;
    private boolean isLoading;
    private Date startDate, endDate;
    private String myFormat;
    private SimpleDateFormat sdf;// = new SimpleDateFormat(myFormat, Locale.US);
    private String token;// = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
    private ParamModel paramModel = new ParamModel();


    //Chart Data
    private Typeface tfRegular;
    private View view;
    private YAxis leftAxis;
    private XAxis xAxis;
    private View itemView, highLightView;
    private int adapterPosition;

    public SessionInfoFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    /**
     * Initialize the Session info Viewmodel
     */
    private void configureViewModel() {
        sessionInfoViewModel = ViewModelProviders.of(this, viewModelFactory).get(SessionInfoViewModel.class);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_session_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();
        currentPage = PAGE_START;
        isLastPage = false;
        totalPage = 0;
        limit = 0;
        totalRecCount = 0;
        isLoading = false;
        token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
        setSessionInfoRecyclerViewAdapter(new ArrayList<>());
        sessionInfoList = new ArrayList<>();
        myFormat = "MM/dd/yyyy"; // date format
        sdf = new SimpleDateFormat(myFormat, Locale.US);

        sessionInfoAPICall();
        initUI();
        initChart();
        return view;
    }

    /**
     * call the session info API
     */
    private void sessionInfoAPICall() {
        if (sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
            Log.i(TAG, "sessionInfoAPICall:timezone " + Utils.getDefaultTimeZone());
//            paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
            paramModel.setOffset(String.valueOf(limit));
            paramModel.setLimit("15");
            if (startDate != null && endDate != null) {
                paramModel.setStartDate(sdf.format(startDate));
                paramModel.setEndDate(sdf.format(endDate));
            }
            if (timeDiffUTCandLocal().length() > 0)
                paramModel.setTimeDiffUTCandLocal(timeDiffUTCandLocal());
        }
        Log.i(TAG, "sessionInfoAPICall: " + paramModel.getUser_id());
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
            new Handler().post(() -> {
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                sessionInfoViewModel.getSessionInfoViewModel(token, paramModel).observe((LifecycleOwner) mContext, new Observer<ApiResponse<SessionInfoResponseModel>>() {
                    @Override
                    public void onChanged(ApiResponse<SessionInfoResponseModel> sessionInfoResponseModelApiResponse) {
                        sessionInfoResponse(sessionInfoResponseModelApiResponse);
                    }
                });
            });
        }
    }

    private String timeDiffUTCandLocal () {
        String timeZoneValue = null, actualTimeZone, timeDiffMin = null, indicator = null;
        String[] splitTimeZone = new String[1];
        timeZoneValue = Utils.getDefaultTimeZone();
        if (timeZoneValue.contains("+")) {
            indicator = "-";
            actualTimeZone = timeZoneValue.substring(1);
            splitTimeZone = actualTimeZone.split(":");
            int hour = Integer.parseInt(splitTimeZone[0]) * 60;
            int min = Integer.parseInt(splitTimeZone[1]);
            int totMin = hour + min;
            timeDiffMin = String.valueOf(totMin);
        } else if (timeZoneValue.contains("-")) {
            indicator = "+";
            actualTimeZone = timeZoneValue.substring(1);
            splitTimeZone = actualTimeZone.split(":");
            int hour = Integer.parseInt(splitTimeZone[0]) * 60;
            int min = Integer.parseInt(splitTimeZone[1]);
            int totMin = hour + min;
            timeDiffMin = String.valueOf(totMin);
        }
        Log.i(TAG, "timeDiffUTCandLocal: " + indicator+timeDiffMin);
        return indicator+timeDiffMin;
    }

    private void initUI() {
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();
//        session_swiperefresh.setOnRefreshListener(this);

//        session_hscroll.setSmoothScrollingEnabled(true);

        /*session_hscroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (!session_hscroll.canScrollHorizontally(1)) {
                    right_arrow_img.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.seekbar_non_progress)));
                } else
                    right_arrow_img.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.hint_color)));

                if (!session_hscroll.canScrollHorizontally(-1)) {
                    left_arrow_img.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.seekbar_non_progress)));
                } else
                    left_arrow_img.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.hint_color)));
            }
        });*/
        /*int hScrollX = session_hscroll.getScrollX() + (plugin_lbl.getWidth() * 4);
        int oldHScrollX = session_hscroll.getScrollX() + (plugin_lbl.getWidth() * 4);
        left_arrow_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session_hscroll.scrollTo( hScrollX, session_hscroll.getScrollY());
            }
        });

        right_arrow_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session_hscroll.scrollTo( hScrollX, session_hscroll.getScrollY());
            }
        });*/

        /*right_arrow_img.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;
            private long mInitialDelay = 300;
            private long mRepeatDelay = 100;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null)
                            return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, mInitialDelay);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null)
                            return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    session_hscroll.scrollTo(hScrollX, (int) session_hscroll.getScrollY());
                    mHandler.postDelayed(mAction, mRepeatDelay);
                }
            };
        });*/

        /**
         * add scroll listener while user reach in bottom load more will call
         */
        LinearLayoutManager layoutManager = (LinearLayoutManager) session_info_rview.getLayoutManager();
        try {
            session_info_rview.addOnScrollListener(new PaginationListener(layoutManager) {
                @Override
                protected void loadMoreItems() {
                    if (totalPage >= currentPage) {
                        isLoading = true;
                        limit += 15;
                        currentPage++;
                        Log.i(TAG, "loadMoreItems: " + currentPage);
                        sessionInfoAPICall();
                    } else toastMessage("Pagination end");
                }

                @Override
                public int getTotalPageCount() {
                    return totalPage;
                }

                @Override
                public boolean isLastPage() {
                    Log.i(TAG, "isLastPage: " + isLastPage);
                    return isLastPage;
                }

                @Override
                public boolean isLoading() {
                    Log.i(TAG, "isLoading: " + isLoading);
                    return isLoading;
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        close_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
           /*  if(itemView!=null && bottom_sheet.getVisibility()==View.VISIBLE){
                    itemView.setBackgroundColor(Color.parseColor("#ffffff"));
                    highLightView.setVisibility(View.GONE);
                    sessionInfoAdapter.notifyItemChanged(adapterPosition);
                }*/
                Utils.slideDown(bottom_sheet);
                return false;
            }
        });
        bottom_sheet.setOnTouchListener(new SwipeTouchListener(mContext) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
            }

            public void onSwipeLeft() {
            }

            public void onSwipeBottom() {
               /* if(itemView!=null && bottom_sheet.getVisibility()==View.VISIBLE){
                    itemView.setBackgroundColor(Color.parseColor("#ffffff"));
                    highLightView.setVisibility(View.GONE);
                    sessionInfoAdapter.notifyItemChanged(adapterPosition);
                }*/
                Utils.slideDown(bottom_sheet);
            }

        });

    }


    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[]{title_lbl, no_rec_found_lbl});
        Utils.setBoldFonts(mContext, new TextView[]{charge_duration_lbl, energy_kwh_lbl, plugin_lbl, session_duration_lbl});
        /*Utils.setBoldFonts(mContext, new TextView[]{charge_duration_lbl, energy_kwh_lbl, plugin_lbl, session_duration_lbl,
                transaction_id_lbl, device_name_lbl, port_name_lbl, group_name_lbl, station_type_lbl, peak_power_lbl, avg_power_lbl,
                id_tag_lbl, session_start_lbl, session_end_lbl, charge_start_lbl, charge_end_lbl, end_reason_lbl, ghg_savings_lbl,
                ghg_saving_gals_lbl, start_soc_lbl, end_soc_lbl});*/
    }

    private void setHeader() {
        ic_back_arrow.setImageResource(R.drawable.ic_filter);
        ic_back_arrow.setVisibility(View.VISIBLE);
        settings_img.setVisibility(View.VISIBLE);
        title_lbl.setText(getString(R.string.session_info));
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.settings_img, R.id.ic_back_arrow})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.settings_img:
                NavController navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);
                navController.navigate(R.id.action_sessionInfoFragment_to_navigation_settings);
                break;
            case R.id.ic_back_arrow:
                showDateFilterAlert(mContext, getString(R.string.filter));
                break;
        }
    }

    private void setSessionInfoRecyclerViewAdapter(ArrayList<SessionInfoResponseModel.SessionInfoBean> sessionInfoBeanArrayList) {

        sessionInfoAdapter = new SessionInfoAdapter(mContext, sessionInfoBeanArrayList, this);
        session_info_rview.setLayoutManager(new LinearLayoutManager(mContext, VERTICAL, false));
        session_info_rview.setHasFixedSize(true);
        session_info_rview.setNestedScrollingEnabled(false);
        session_info_rview.setItemAnimator(new DefaultItemAnimator());
        session_info_rview.setAdapter(sessionInfoAdapter);
    }

    private void sessionInfoResponse(ApiResponse<SessionInfoResponseModel> sessionInfoResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        SessionInfoResponseModel sessionInfoResponseModel = sessionInfoResponseModelApiResponse.getData();
        switch (sessionInfoResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (sessionInfoResponseModel.getStatus().equals("1")) {
                    toastMessage(sessionInfoResponseModel.getSuccess() + " API Success");
                    Log.i(TAG, "sessionInfoResponse:getSessionInfo " + sessionInfoResponseModelApiResponse.getData().getSessionInfo().size());
                    totalRecCount = sessionInfoResponseModel.getTotalRecordsCount();
                    if (totalRecCount > 0) {
                        int div = totalRecCount / 15;
                        int mod = totalRecCount % 15;
                        totalPage = mod > 0 ? div + 1 : div;
                    }
                    Log.i(TAG, "sessionInfoResponse:totalRecCount " + totalRecCount + " totalPage: " + totalPage);
                    updateUI(sessionInfoResponseModel);
                } else if (sessionInfoResponseModel.getError().equals("TOKEN_INVALID")) {
                    sharedPreferencesUtil.deleteAllSession();
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                } else
                    Utils.showCustomAlert(mContext, getString(R.string.alert), sessionInfoResponseModel.getError(),
                            "session_info_failed");
                break;
            case FAILURE:
                Utils.showCustomAlert(mContext, getString(R.string.alert),
                        Utils.getConvertedErrorBody(sessionInfoResponseModelApiResponse.getFailureResponse()), "session_info_failed");
                break;
            case ERROR:
                Utils.handleErrorResponse(sessionInfoResponseModelApiResponse.getError(), mContext);
                break;
        }
    }

    private void updateUI(SessionInfoResponseModel sessionInfoResponseModel) {
        if (totalRecCount > 0) {
            no_rec_found_lbl.setVisibility(View.GONE);
            session_list_llay.setVisibility(View.VISIBLE);
            session_info_rview.setVisibility(View.VISIBLE);
            sessionInfoList = new ArrayList<>();
//            final ArrayList<SessionInfoResponseModel.SessionInfoBean> sessionInfoList = new ArrayList<>();
            new Handler().postDelayed(() -> {
                for (int i = 0; i < sessionInfoResponseModel.getSessionInfo().size(); i++) {
                    SessionInfoResponseModel.SessionInfoBean sessionInfoBean = new SessionInfoResponseModel.SessionInfoBean();
                    sessionInfoBean = sessionInfoResponseModel.getSessionInfo().get(i);
                    sessionInfoList.add(sessionInfoBean);
                }
                sessionInfoAdapter.clear();
                /**
                 * manage progress view
                 */
                if (currentPage != PAGE_START)
                    sessionInfoAdapter.removeLoading();
                sessionInfoAdapter.addItems(sessionInfoList);
//                session_swiperefresh.setRefreshing(false);
                Log.i(TAG, "run:currentPage " + currentPage + " totalPage " + totalPage);
                // check weather is last page or not
                if (currentPage < totalPage) {
                    sessionInfoAdapter.addLoading();
                } else {
                    isLastPage = true;
                }
                isLoading = false;
            }, 1000);
        } else {
            no_rec_found_lbl.setVisibility(View.VISIBLE);
            session_list_llay.setVisibility(View.GONE);
            session_info_rview.setVisibility(View.GONE);
        }
    }

    @Override
    public void SessionGraphItem(SessionInfoResponseModel.SessionInfoBean sessionInfoBean, View view, View highLightView, int position
            , boolean value) {
        this.itemView = view;
        this.highLightView = highLightView;
        this.adapterPosition = position;
        if (value) {
            showGraphSheet(sessionInfoBean);
        } else {
            itemView = null;
            Utils.slideDown(bottom_sheet);
        }
    }

    /**
     * Show Date Filter Alert
     */
    @SuppressLint("ClickableViewAccessibility")
    private void showDateFilterAlert(final Context context, String from) {
        final Dialog dialog = new Dialog(context, R.style.add_vehicle_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.date_filter_dialog);
        dialog.setCancelable(false);
//        dialog.getWindow().setGravity(Gravity.END);
//        dialog.getWindow().setLayout(((getWidth(context) / 100) * 90), LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();

        ImageView ic_close = dialog.findViewById(R.id.ic_close);
        TextView filter_lbl = dialog.findViewById(R.id.filter_lbl);
        TextView date_from_lbl = dialog.findViewById(R.id.date_from_lbl);
        RelativeLayout date_from_layout = dialog.findViewById(R.id.date_from_layout);
        EditText date_from_etext = dialog.findViewById(R.id.date_from_etext);
        ImageView ic_date_from = dialog.findViewById(R.id.ic_date_from);
        TextView date_to_lbl = dialog.findViewById(R.id.date_to_lbl);
        RelativeLayout date_to_layout = dialog.findViewById(R.id.date_to_layout);
        EditText date_to_etext = dialog.findViewById(R.id.date_to_etext);
        ImageView ic_date_to = dialog.findViewById(R.id.ic_date_to);
        Button filter_btn = dialog.findViewById(R.id.filter_btn);
        TextView clear_filter_lbl = dialog.findViewById(R.id.clear_filter_lbl);

        Utils.setBoldFonts(context, new TextView[]{filter_lbl, date_from_lbl, date_to_lbl, filter_btn});
        Utils.setRegularFonts(context, new TextView[]{date_from_etext, date_to_etext, clear_filter_lbl});

        disableEdittextInput(date_from_etext);
        disableEdittextInput(date_to_etext);

        if (startDate != null && endDate != null) {
            date_from_etext.setText(sdf.format(startDate));
            date_to_etext.setText(sdf.format(endDate));
        } else {
            date_from_etext.setText("");
            date_to_etext.setText("");
        }

        date_from_etext.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                /*String existingVal = null;
                if (date_from_etext.getText().toString().trim().length() > 0)
                    existingVal = date_from_etext.getText().toString();*/
                final Calendar myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        //UIHelper.showLongToastInCenter(context, sdf.format(myCalendar.getTime()));
                        date_from_etext.setText(sdf.format(myCalendar.getTime()));
                        startDate = myCalendar.getTime();
                    }
                };
                DatePickerDialog fromDatePickerDialog = new DatePickerDialog(mContext, R.style.DatePickerDialogTheme, dateSetListener,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                fromDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                fromDatePickerDialog.show();
            }
            return true;
        });

        date_to_etext.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
               /* String existingVal = null;
                if (date_to_etext.getText().toString().trim().length() > 0)
                    existingVal = date_to_etext.getText().toString();*/
                final Calendar myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        //UIHelper.showLongToastInCenter(context, sdf.format(myCalendar.getTime()));
                        date_to_etext.setText(sdf.format(myCalendar.getTime()));
                        endDate = myCalendar.getTime();
                    }
                };
                new DatePickerDialog(mContext, R.style.DatePickerDialogTheme, dateSetListener, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

            return true;
        });

        filter_btn.setOnClickListener(view -> {
            if (date_from_etext.getText().toString().length() > 0) {
                if (date_to_etext.getText().toString().length() > 0) {
                    Log.i(TAG, "showDateFilterAlert:startDate " + startDate + " endDate:" + endDate);
//                    String dateCompareResult = Utils.checkDates(startDate, endDate);
                    if (startDate.compareTo(endDate) > 0) { // check start date after end date
                        Utils.showOKCustomAlert(mContext, null, "Invalid Date. Please select valid date", "invalid_date");
                    } else {
                        limit = 0;
                        currentPage = PAGE_START;
                        isLastPage = false;
                        sessionInfoList = new ArrayList<>();
                        sessionInfoAdapter.filterClear();
                        sessionInfoAPICall();
                        dialog.dismiss();
                        dialog.cancel();
                    }

                } else toastMessage("Please select To Date!");
            } else toastMessage("Please select From Date!");
        });

        clear_filter_lbl.setOnClickListener(view -> {
            if (date_from_etext.getText().toString().length() > 0 || date_to_etext.getText().toString().length() > 0) {
                startDate = null;
                endDate = null;
                paramModel = new ParamModel();
                currentPage = PAGE_START;
                isLastPage = false;
                sessionInfoList = new ArrayList<>();
                sessionInfoAdapter.filterClear();
                date_from_etext.setText("");
                date_to_etext.setText("");
                dialog.dismiss();
                dialog.cancel();
                limit = 0;
                sessionInfoAPICall();
            }
        });

        ic_close.setOnClickListener(view -> {
            dialog.dismiss();
            dialog.cancel();
        });


    }

    private void showGraphSheet(SessionInfoResponseModel.SessionInfoBean sessionInfoBean) {
        String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
//        ParamModel paramModel = new ParamModel();
//        paramModel.setTransaction_id(sessionInfoBean.getTransactionId());
//        paramModel.setTransaction_type(sessionInfoBean.getTransactionType());
        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
        sessionInfoViewModel.getSessionGraphInfoViewModel(token, sessionInfoBean.getTransactionId(),
                sessionInfoBean.getTransactionType()).observe((LifecycleOwner) mContext, new Observer<ApiResponse<DetailedSessionResponseModel>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(ApiResponse<DetailedSessionResponseModel> detailedSessionResponseModelApiResponse) {
                showGraphResponse(detailedSessionResponseModelApiResponse);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showGraphResponse(ApiResponse<DetailedSessionResponseModel> detailedSessionResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        DetailedSessionResponseModel detailedSessionResponseModel = detailedSessionResponseModelApiResponse.getData();
        switch (detailedSessionResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (detailedSessionResponseModel.getStatus().equals("1")) {
                    toastMessage(detailedSessionResponseModel.getSuccess() + " API Success");
                    Log.i(TAG, "sessionInfoResponse:getSessionInfo " + detailedSessionResponseModelApiResponse.getData().getDetailedSession().size());
                 /*   SessionGraphFragment sessionGraphFragment = new SessionGraphFragment(detailedSessionResponseModel);
                    sessionGraphFragment.show(fragmentManager, "SessionGraphFragment");*/
                    if (bottom_sheet.getVisibility() != View.VISIBLE)
                        Utils.slideUp(bottom_sheet);

                    updateChart((ArrayList<DetailedSessionResponseModel.DetailedSessionBean>) detailedSessionResponseModel.getDetailedSession());
             /*       ArrayList<DetailedSessionResponseModel.DetailedSessionBean> detailedSessionBeans=new ArrayList<>();
                    for (int i = 0; i < 9; i++) {
                        DetailedSessionResponseModel.DetailedSessionBean detailedSessionBean=new DetailedSessionResponseModel.DetailedSessionBean();
                        if(i==0) {
                            detailedSessionBean.setEnergy("5");
                            detailedSessionBean.setTime("02.45 PM");
                        }else if(i==1){
                            detailedSessionBean.setEnergy("6");
                            detailedSessionBean.setTime("04.45 PM");
                        }else if(i==2){
                            detailedSessionBean.setEnergy("8");
                            detailedSessionBean.setTime("05.45 PM");
                        }else if(i==3){
                            detailedSessionBean.setEnergy("100");
                            detailedSessionBean.setTime("07.00 PM");
                        }else if(i==4){
                            detailedSessionBean.setEnergy("25");
                            detailedSessionBean.setTime("08.00 PM");
                        }else if(i==5){
                            detailedSessionBean.setEnergy("56");
                            detailedSessionBean.setTime("09.00 PM");
                        }else if(i==6){
                            detailedSessionBean.setEnergy("12");
                            detailedSessionBean.setTime("10.00 PM");
                        }else if(i==7){
                            detailedSessionBean.setEnergy("15");
                            detailedSessionBean.setTime("11.00 PM");
                        }else if(i==8){
                            detailedSessionBean.setEnergy("5");
                            detailedSessionBean.setTime("12.00 PM");
                        }
                        detailedSessionBeans.add(detailedSessionBean);
                    }
                    updateChart((ArrayList<DetailedSessionResponseModel.DetailedSessionBean>) detailedSessionBeans);*/


                } else if (detailedSessionResponseModel.getError().equals("TOKEN_INVALID")) {
                    sharedPreferencesUtil.deleteAllSession();
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                } else
                    Utils.showCustomAlert(mContext, getString(R.string.alert), detailedSessionResponseModel.getError(),
                            "session_info_failed");
                break;
            case FAILURE:
                Utils.showCustomAlert(mContext, getString(R.string.alert),
                        Utils.getConvertedErrorBody(detailedSessionResponseModelApiResponse.getFailureResponse()), "session_info_failed");
                break;
            case ERROR:
                Utils.handleErrorResponse(detailedSessionResponseModelApiResponse.getError(), mContext);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /*@Override
    public void onRefresh() {
        if (totalRecCount >= limit) {
            limit = 0;
            currentPage = PAGE_START;
            isLastPage = false;
//            sessionInfoList = new ArrayList<>();
//            sessionInfoAdapter.filterClear();
            startDate = null;
            endDate = null;
            sessionInfoAPICall();
        } else {
            session_swiperefresh.setEnabled(false);
            session_swiperefresh.setRefreshing(false);
        }
    }*/


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateChart(ArrayList<DetailedSessionResponseModel.DetailedSessionBean> detailedSessionBeans) {
        if (detailedSessionBeans != null && detailedSessionBeans.size() > 0) {
            ArrayList<Entry> entries = new ArrayList<>();
            ArrayList<String> xAxisValueArray = new ArrayList<>();
            int[] avg_powerList = new int[detailedSessionBeans.size()];
            for (int i = 0; i < detailedSessionBeans.size(); i++) {
                avg_powerList[i] = Math.round(Float.parseFloat(detailedSessionBeans.get(i).getAvgPower()));
                Entry lineEntry = new Entry(i, Float.parseFloat(detailedSessionBeans.get(i).getAvgPower()));
                entries.add(lineEntry);
                String formatConversion = "yyyy-MM-dd hh:mm:ss Z";
                String time = Utils.getLocalTimeFromUTCByFormat(detailedSessionBeans.get(i).getTime(),
                        formatConversion, "hh:mm a");
//                time = time.substring(0, time.indexOf("+"));
        /*     if(time.contains("PM"))
                    time.replaceAll("PM"," pm");
                else
                    time.replaceAll("AM"," am");
*/
                xAxisValueArray.add(time.replace(" ", "\n"));
            }
            //Set lineChart dynamic data
            setLineChartData(entries, xAxisValueArray, avg_powerList);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setLineChartData(ArrayList<Entry> entries, ArrayList<String> xAxisValueArray, int[] avg_powerList) {
        leftAxis.setAxisMaximum(Float.parseFloat(String.valueOf(Arrays.stream(avg_powerList).max().getAsInt() + 2)));

        xAxis.removeAllLimitLines();
        xAxis.resetAxisMaximum();
        xAxis.resetAxisMinimum();
        xAxis = chart.getXAxis();
        xAxis.setLabelCount(5);
        xAxis.setDrawGridLines(true);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(xAxisValueArray.size());
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValueArray));
        leftAxis.setValueFormatter(setMonthFormator());


        LineDataSet lineDataSet = new LineDataSet(entries, "");
//        lineDataSet.setColor(Color.rgb(39, 143, 255));
        lineDataSet.setColor(Color.parseColor("#BFD45B"));
        lineDataSet.setLineWidth(3f);
        lineDataSet.setCircleRadius(0f);
        // draw points as solid circles
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setFillAlpha(65);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setCircleHoleRadius(0f);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawVerticalHighlightIndicator(false);
        lineDataSet.setDrawFilled(true);
        // set color of filled area
        if (com.github.mikephil.charting.utils.Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            Drawable drawable = getResources().getDrawable(R.drawable.graph_gradient);
            lineDataSet.setFillDrawable(drawable);
        } else {
            lineDataSet.setFillColor(Color.parseColor("#DDE0CF"));
        }

        LineData lineData = new LineData(lineDataSet);
        lineData.addDataSet(lineDataSet);

        chart.setData(lineData);
        chart.notifyDataSetChanged();
        chart.invalidate();
        chart.animateX(1500, Easing.Linear);
        chart.setVisibleXRangeMaximum(5);
        chart.setVisibleXRangeMinimum(5);
    }


    private void initChart() {
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);
        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setHighlightPerDragEnabled(true);
        chart.animateXY(1000, 1000);
        // enable touch gestures
        chart.setTouchEnabled(true);
        tfRegular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NunitoSans-Regular.ttf");


        chart.getLegend().setEnabled(false);
        chart.setScaleMinima(1f, 1f);
        chart.setExtraOffsets(0, 5, 0, 5);
        chart.setExtraBottomOffset(15);
        chart.clearFocus();
        chart.invalidate();
        chart.setScaleXEnabled(false);
        chart.setScaleYEnabled(false);
        chart.getAxisRight().setEnabled(false);
//        chart.setViewPortOffsets(0f, 0f, 0f, 0f);

        leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridLineWidth(1f);
//        leftAxis.setGridColor(Color.rgb(248, 248, 248));
        leftAxis.setGridColor(Color.parseColor("#80CDCDCD"));
        leftAxis.setTypeface(tfRegular);
        leftAxis.setTextSize(12f);
        leftAxis.setAxisMinimum(0);
        leftAxis.setGranularity(1f);

        xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        // vertical grid lines
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        //        xAxis.setGridColor(Color.rgb(248, 248, 248));
        xAxis.setGridColor(Color.parseColor("#80CDCDCD"));
        xAxis.setTypeface(tfRegular);
        xAxis.setTextSize(12f);
        xAxis.setSpaceMax(0.5f);
        xAxis.setSpaceMin(0.5f);
        xAxis.setXOffset(5f);
//        chart.setExtraRightOffset(25f);
//        chart.setExtraLeftOffset(25f);
        xAxis.setAvoidFirstLastClipping(false);
        xAxis.setDrawLimitLinesBehindData(true);

    }

    private ValueFormatter setMonthFormator() {
        ValueFormatter yAxisFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return Math.round(value) + " KW";
            }
        };
        return yAxisFormatter;

    }


}
