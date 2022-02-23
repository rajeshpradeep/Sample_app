package com.example.structure.ui.registration.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.daos.CreateAccountDao;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.CreateAccountParamModel;
import com.example.structure.data.models.GetQuestionResponseModel;
import com.example.structure.data.models.CountryStateResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.support.CustomNumberPicker;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.support.PasswordStrength;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.login.fragment.LoginFragment;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.CreateAccountViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
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
public class CreateAccountFragment extends BaseFragment {

    private String TAG = getClass().getSimpleName();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtil sharedPreferences;
    @Inject
    CreateAccountDao createAccountDao;
    @Inject
    Executor executor;
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private CreateAccountViewModel createAccountViewModel;
    private ArrayList<GetQuestionResponseModel.QuestionsBean> questionsBeanArrayList = new ArrayList<>();
    ;

    @BindView(R.id.ca_base_lay)
    ConstraintLayout ca_base_lay;
    @BindView(R.id.password_lbl)
    TextView password_lbl;
    @BindView(R.id.password_etext)
    EditText password_etext;
    @BindView(R.id.password_status_lbl)
    TextView password_status_lbl;
    @BindView(R.id.password_status_seekbar)
    AppCompatSeekBar password_status_seekbar;
    @BindView(R.id.continue_btn)
    Button continue_btn;
    @BindView(R.id.create_acc_lbl)
    TextView create_acc_lbl;
    @BindView(R.id.email_lbl)
    TextView email_lbl;
    @BindView(R.id.email_etext)
    EditText email_etext;
    @BindView(R.id.username_lbl)
    TextView username_lbl;
    @BindView(R.id.username_etext)
    EditText username_etext;
    @BindView(R.id.question_lbl)
    TextView question_lbl;
    @BindView(R.id.question_etext)
    EditText question_etext;
    @BindView(R.id.answer_lbl)
    TextView answer_lbl;
    @BindView(R.id.answer_etext)
    EditText answer_etext;
    @BindView(R.id.fname_lbl)
    TextView fname_lbl;
    @BindView(R.id.fname_etext)
    EditText fname_etext;
    @BindView(R.id.lname_lbl)
    TextView lname_lbl;
    @BindView(R.id.lname_etext)
    EditText lname_etext;
    @BindView(R.id.address1_lbl)
    TextView address1_lbl;
    @BindView(R.id.address1_etext)
    EditText address1_etext;
    @BindView(R.id.address2_lbl)
    TextView address2_lbl;
    @BindView(R.id.address2_etext)
    EditText address2_etext;
    @BindView(R.id.city_lbl)
    TextView city_lbl;
    @BindView(R.id.city_etext)
    EditText city_etext;
    @BindView(R.id.state_lbl)
    TextView state_lbl;
    @BindView(R.id.state_etext)
    EditText state_etext;
    @BindView(R.id.country_lbl)
    TextView country_lbl;
    @BindView(R.id.country_etext)
    EditText country_etext;
    @BindView(R.id.postal_code_lbl)
    TextView postal_code_lbl;
    @BindView(R.id.postal_code_etext)
    EditText postal_code_etext;
    @BindView(R.id.leader_board_lbl)
    TextView leader_board_lbl;
    @BindView(R.id.leaderboard_alias_info_img)
    AppCompatImageView leaderboard_alias_info_img;
    @BindView(R.id.leader_board_etext)
    EditText leader_board_etext;
    @BindView(R.id.accept_terms_checked)
    AppCompatCheckBox accept_terms_checked;
    @BindView(R.id.accept_terms_checked_tview)
    TextView accept_terms_checked_tview;
    @BindView(R.id.accept_personal_info_checked)
    AppCompatCheckBox accept_personal_info_checked;
    @BindView(R.id.accept_personal_info_checked_tview)
    TextView accept_personal_info_checked_tview;
    @BindView(R.id.back_to_login_tview)
    TextView back_to_login_tview;
    @BindView(R.id.password_tooltip_img)
    ImageView password_tooltip_img;

    private CreateAccountParamModel createAccountParamModel;
    private CustomProgressDialog customProgressDialog;
    private String[] countryListBeans, stateListBeans, secretQuestions;
    private String itemName, selectedCountry, selectedState;
    private int indexPos = 0, selectedQuestionId = 0;
    private boolean isEmailExist = true, isUsernameExist = true, isAliasExist = true;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    /**
     * Initialize the Create account Viewmodel
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
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        fragmentManager = getFragmentManager();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                getSecretQuesCountryStateAPICall();
//                getCountryStateResponse();
                initUI();
            }
        });
    }

    /**
     * initialize the UI
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
        setStatusBarGradiant(getActivity());
        setFont();

        disableEdittextInput(country_etext);
        disableEdittextInput(state_etext);
        disableEdittextInput(question_etext);
        email_etext.requestFocus();
        leader_board_etext.setImeOptions(EditorInfo.IME_ACTION_DONE);
        leader_board_etext.setOnEditorActionListener((textView, i, keyEvent) -> {
            if ((keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) || (i == EditorInfo.IME_ACTION_DONE)) {
                continue_btn.performClick();
                return true;
            }
            return false;
        });

        password_status_seekbar.setProgress(0);
        password_status_seekbar.invalidate();
        password_etext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 0) {
                    password_status_lbl.setVisibility(View.VISIBLE);
                    int passwordVal = PasswordStrength.calculateStrength(charSequence.toString());
                    switch (passwordVal) {
                        case 0:
                            password_status_lbl.setText(getString(R.string.weak));
                            password_status_seekbar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_round_corner));
                            password_status_seekbar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
//                            password_status_seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent, null), PorterDuff.Mode.SRC_IN);
                            password_status_seekbar.setProgress(25);
                            break;
                        case 1:
                            password_status_lbl.setText(getString(R.string.fair));
                            password_status_seekbar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_round_corner));
                            password_status_seekbar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.password_fair)));
//                            password_status_seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.password_fair, null), PorterDuff.Mode.SRC_IN);
                            password_status_seekbar.setProgress(50);
                            break;
                        case 2:
                            password_status_lbl.setText(getString(R.string.good));
                            password_status_seekbar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_round_corner));
                            password_status_seekbar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
//                            password_status_seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.green_gradient, null), PorterDuff.Mode.SRC_IN);
                            password_status_seekbar.setProgress(75);
                            break;
                        case 3:
                            password_status_lbl.setText(getString(R.string.strong));
                            password_status_seekbar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_round_corner));
                            password_status_seekbar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow_gradient)));
//                            password_status_seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.yellow_gradient, null), PorterDuff.Mode.SRC_IN);
                            password_status_seekbar.setProgress(100);
                            break;
                    }
                } else {
                    password_status_lbl.setVisibility(View.INVISIBLE);
                    password_status_seekbar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_round_corner));
                    password_status_seekbar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.seekbar_non_progress)));
//                    password_status_seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.seekbar_non_progress, null), PorterDuff.Mode.SRC_IN);
                    password_status_seekbar.setProgress(0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password_status_seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        country_etext.setOnTouchListener((view, motionEvent) ->
        {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                String existingVal = null;
                if (country_etext.getText().toString().trim().length() > 0)
                    existingVal = country_etext.getText().toString();
                if (countryListBeans.length > 0) {
                    Log.i(TAG, "onTouch:countryListBeans list " + countryListBeans.length);
                    pickerDialog(mContext, countryListBeans, indexPos - 2, country_etext,
                            "Select Country", existingVal, null);
                }
            }
            return true;
        });

        state_etext.setOnTouchListener((view, motionEvent) ->
        {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                String existingVal = null;
                if (state_etext.getText().toString().trim().length() > 0)
                    existingVal = state_etext.getText().toString();
                if (stateListBeans.length > 0) {
                    Log.i(TAG, "onTouch:stateListBeans list " + stateListBeans.length);
                    pickerDialog(mContext, stateListBeans, indexPos - 2, state_etext,
                            "Select State", existingVal, null);
                }
            }
            return true;
        });

        question_etext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    String existingVal = null;
                    if (question_etext.getText().toString().trim().length() > 0)
                        existingVal = question_etext.getText().toString();
                    if (questionsBeanArrayList.size() > 0 && secretQuestions.length > 0) {
                        Log.i(TAG, "onTouch:secret ques length " + secretQuestions.length);
                        pickerDialog(mContext, secretQuestions, indexPos - 2,
                                question_etext, "Secret Questions", existingVal, questionsBeanArrayList);
                    }
                }
                return true;
            }
        });

        SpannableString terms_service = new SpannableString("I accept the Terms of Service");
        terms_service.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.hint_color)), 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        terms_service.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colorPrimary)), 13, terms_service.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan terms_service_clickable_span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.TOS_URL));
                startActivity(browserIntent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            }
        };
        terms_service.setSpan(terms_service_clickable_span, 13, terms_service.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        accept_terms_checked_tview.setText(terms_service);
        accept_terms_checked_tview.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString accept_personal_info = new SpannableString("I accept the Use of Personal Information");
        accept_personal_info.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.hint_color)), 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        accept_personal_info.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colorPrimary)), 13, accept_personal_info.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan accept_personal_info_clickable_span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PP_URL));
                startActivity(browserIntent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            }
        };
        accept_personal_info.setSpan(accept_personal_info_clickable_span, 13, accept_personal_info.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        accept_personal_info_checked_tview.setText(accept_personal_info);
        accept_personal_info_checked_tview.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString already_have_acc = new SpannableString("Already have an account? Login");
        already_have_acc.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.hint_color)), 0, 24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        already_have_acc.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colorPrimary)), 25, already_have_acc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan already_have_acc_clickable_span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                setFragment(new LoginFragment());
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            }
        };
        already_have_acc.setSpan(already_have_acc_clickable_span, 25, already_have_acc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        back_to_login_tview.setText(already_have_acc);
        back_to_login_tview.setMovementMethod(LinkMovementMethod.getInstance());

        leaderboard_alias_info_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Utils.showOKCustomAlert(mContext, null, getString(R.string.leaderboard_alias), "alias_tip");
//                Utils.showOKCustomAlert(mContext, null, getString(R.string.alias_tip), "alias_tip");
                return false;
            }
        });
    }

    private void setFont() {
        Utils.setLightFonts(mContext, new TextView[]{create_acc_lbl});
        Utils.setRegularFonts(mContext, new TextView[]{password_etext, address1_etext, address2_etext, answer_etext, city_etext,
                answer_etext, country_etext, email_etext, fname_etext, lname_etext, leader_board_etext, username_etext, postal_code_etext,
                state_etext, question_etext, password_status_lbl});
        Utils.setBoldFonts(mContext, new TextView[]{continue_btn, leader_board_lbl, address1_lbl, address2_lbl, answer_lbl, city_lbl,
                country_lbl, email_lbl, fname_lbl, lname_lbl, password_lbl, postal_code_lbl, username_lbl, state_lbl, question_lbl});
    }

    private void updateFromDB() {
        if (createAccountDao.getCreateAccountDetails() != null) {
            CreateAccountParamModel createAccountParamModel = createAccountDao.getCreateAccountDetails();
            email_etext.setText(createAccountParamModel.getEmail());
            username_etext.setText(createAccountParamModel.getUsername());
            password_etext.setText("");
            if (questionsBeanArrayList.size() > 0) {
                for (int i = 0; i < questionsBeanArrayList.size(); i++) {
                    if (createAccountParamModel.getSecret_question() == questionsBeanArrayList.get(i).getId()) {
                        question_etext.setText(questionsBeanArrayList.get(i).getQuestion());
                    }
                }
            }
            answer_etext.setText(createAccountParamModel.getSecret_answer());
            fname_etext.setText(createAccountParamModel.getFirst_name());
            lname_etext.setText(createAccountParamModel.getLast_name());
            address1_etext.setText(createAccountParamModel.getAddress1());
            address2_etext.setText(createAccountParamModel.getAddress2());
            city_etext.setText(createAccountParamModel.getCity());
            state_etext.setText(createAccountParamModel.getState());
            country_etext.setText(createAccountParamModel.getCountry());
            postal_code_etext.setText(createAccountParamModel.getPostal_code());
            leader_board_etext.setText(createAccountParamModel.getAlias());
            accept_terms_checked.setChecked(true);
            accept_personal_info_checked.setChecked(true);
        }
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.continue_btn, R.id.country_etext, R.id.state_etext, R.id.ca_base_lay, R.id.password_tooltip_img})
    void buttonAction(View view) {
        long mLastClickTime = 0;
        switch (view.getId()) {
            case R.id.continue_btn:
                if (email_etext.getText().toString().length() > 0) {
                    String email = email_etext.getText().toString().trim();
                    if (isValidEmail(email)) {
                        if (username_etext.getText().toString().length() > 0) {
                            if (password_status_lbl.getText().toString().equalsIgnoreCase("Strong")) {
                                if (question_etext.getText().toString().trim().length() > 0) {
                                    if (answer_etext.getText().toString().trim().length() > 0) {
                                        if (accept_terms_checked.isChecked()) {
                                            if (accept_personal_info_checked.isChecked()) {
                                                sharedPreferences.saveUserEmail(Constant.USER_EMAIL, email);
                                                new Handler().post(() -> {
                                                    createAccountParamModel = new CreateAccountParamModel();
                                                    createAccountParamModel.setEmail(email);
                                                    createAccountParamModel.setUsername(username_etext.getText().toString().trim());
                                                    createAccountParamModel.setPassword(password_etext.getText().toString().trim());
                                                    createAccountParamModel.setSecret_question(selectedQuestionId);
                                                    createAccountParamModel.setSecret_answer(answer_etext.getText().toString().trim());
                                                    createAccountParamModel.setFirst_name(fname_etext.getText().toString().trim());
                                                    createAccountParamModel.setLast_name(lname_etext.getText().toString().trim());
                                                    createAccountParamModel.setAddress1(address1_etext.getText().toString().trim());
                                                    createAccountParamModel.setAddress2(address2_etext.getText().toString().trim());
                                                    createAccountParamModel.setCity(city_etext.getText().toString().trim());
                                                    createAccountParamModel.setState(state_etext.getText().toString());
                                                    createAccountParamModel.setCountry(country_etext.getText().toString());
                                                    createAccountParamModel.setAlias(leader_board_etext.getText().toString().trim());
                                                    if (postal_code_etext.getText().length() > 0) {
                                                        if (postal_code_etext.getText().length() == 5) {
                                                            createAccountParamModel.setPostal_code(postal_code_etext.getText().toString());
                                                            isExistingEmailAPI();
                                                        } else
                                                            Utils.showOKCustomAlert(mContext, null,
                                                                    "Postal code must have 5 digits", "postal_code_failed");
                                                    } else {
                                                        createAccountParamModel.setPostal_code(postal_code_etext.getText().toString());
                                                        isExistingEmailAPI();
                                                    }
                                                });
                                            } else
                                                Utils.showCustomAlert(mContext, getString(R.string.alert),
                                                        "Please accept the use of personal information", "pp_unchecked");
                                        } else
                                            Utils.showCustomAlert(mContext, getString(R.string.alert),
                                                    "Please accept the terms of service", "tos_unchecked");
                                    } else
                                        Utils.showOKCustomAlert(mContext, null, "Please answer the secret question",
                                                "secret_question_empty");
                                } else
                                    Utils.showOKCustomAlert(mContext, null, "Please select the secret Question",
                                            "secret_question_empty");
                            } else {
                                String message = "Password should contain min of 8 characters and at least 1 lowercase, 1 uppercase and 1 numeric value";
                                Utils.showOKCustomAlert(mContext, null, message, "password_invalid");
                            }

                        } else
                            Utils.showOKCustomAlert(mContext, null, "Please enter your username", "password_invalid");
                    } else
                        Utils.showOKCustomAlert(mContext, null, "Please enter valid email", "email_invalid");
                } else
                    Utils.showOKCustomAlert(mContext, null, "Please enter your email", "email_empty");
                break;
            case R.id.country_etext:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) { // 1000 = 1second
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                break;
            case R.id.state_etext:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) { // 1000 = 1second
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                break;
            case R.id.ca_base_lay:
                hideKeyboardFrom(mContext, ca_base_lay);
                break;
            case R.id.password_tooltip_img:
                Utils.showOKCustomAlert(mContext, null, getString(R.string.password_tip), "password_unmatched");
                break;
        }

    }

    private void isExistingEmailAPI() {
        String email = email_etext.getText().toString().trim();
        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
        ParamModel paramModel = new ParamModel();
        paramModel.setEmail(email);
        createAccountViewModel.getEmailExistViewModel(paramModel).observe((LifecycleOwner) mContext, commonResponseModelApiResponse ->
                emailUsernameAlaisResponse(commonResponseModelApiResponse));
    }

    private void isExistingUsernameAPI() {
        String username = username_etext.getText().toString().trim();
        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
        ParamModel paramModel = new ParamModel();
        paramModel.setUsername(username);
        createAccountViewModel.getUsernameExistViewModel(paramModel).observe((LifecycleOwner) mContext, commonResponseModelApiResponse ->
                emailUsernameAlaisResponse(commonResponseModelApiResponse));
    }

    private void isExistingAlaisAPI() {
        String alias = leader_board_etext.getText().toString().trim();
        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
        ParamModel paramModel = new ParamModel();
        paramModel.setAlias(alias);
        createAccountViewModel.getAliasExistViewmodel(paramModel).observe((LifecycleOwner) mContext, commonResponseModelApiResponse ->
                emailUsernameAlaisResponse(commonResponseModelApiResponse));
    }

    /**
     * Call the Country and State API
     */
    private void getSecretQuesCountryStateAPICall() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                createAccountViewModel.getSecretQuestionViewModel().observe((LifecycleOwner) mContext, new Observer<ApiResponse<GetQuestionResponseModel>>() {
                    @Override
                    public void onChanged(ApiResponse<GetQuestionResponseModel> getQuestionResponseModelApiResponse) {
                        getSecretQuestionResponse(getQuestionResponseModelApiResponse);
                    }
                });

                createAccountViewModel.getCountryViewModel().observe((LifecycleOwner) mContext, new Observer<ApiResponse<CountryStateResponseModel>>() {
                    @Override
                    public void onChanged(ApiResponse<CountryStateResponseModel> countryStateResponseModelApiResponse) {
                        countryStateResponse(countryStateResponseModelApiResponse);
                    }
                });
                createAccountViewModel.getStateViewModel().observe((LifecycleOwner) mContext, new Observer<ApiResponse<CountryStateResponseModel>>() {
                    @Override
                    public void onChanged(ApiResponse<CountryStateResponseModel> countryStateResponseModelApiResponse) {
                        countryStateResponse(countryStateResponseModelApiResponse);
                    }
                });
            }
        });
    }

    /**
     * Get the Country, State and Secret Question Response List
     */
    private void getCountryStateResponse() {
        new Handler().post(() -> {
//            createAccountViewModel.getCountryResponse().observe((LifecycleOwner) mContext, pickerResponseModelApiResponse -> countryStateResponse(pickerResponseModelApiResponse));

//            createAccountViewModel.getStateResponse().observe((LifecycleOwner) mContext, pickerResponseModelApiResponse -> countryStateResponse(pickerResponseModelApiResponse));


        });
    }

    private void emailUsernameAlaisResponse(ApiResponse<CommonResponseModel> apiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        CommonResponseModel commonResponseModel;
        if (apiResponse.getData() != null) {
            commonResponseModel = apiResponse.getData();
            switch (apiResponse.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (commonResponseModel != null) {
                        String responseMessage = commonResponseModel.getMessage();
                        if (commonResponseModel.getStatus().equals("1")) {
                            switch (responseMessage) {
                                case "Email not registered":
                                    isEmailExist = false;
                                    isExistingUsernameAPI();
                                    break;
                                case "Username not registered":
                                    isUsernameExist = false;
                                    if (leader_board_etext.getText().toString().length() > 0)
                                        isExistingAlaisAPI();
                                    else {
                                        if (createAccountParamModel != null) {
                                            createAccountDao.createAccount1(createAccountParamModel);
                                            redirectToAddVehicleFragment(new AddElectricVehicleFragment());
                                        }
                                    }
                                    break;
                                case "Alias not registered":
                                    isAliasExist = false;
//                                    isEmailUsernameAliasExist(commonResponseModel);
                                    if (createAccountParamModel != null) {
                                        createAccountDao.createAccount1(createAccountParamModel);
                                        redirectToAddVehicleFragment(new AddElectricVehicleFragment());
                                    }
                                    break;
                            }
                        } else
                            Utils.showOKCustomAlert(mContext, null, commonResponseModel.getMessage(), "val_existing");
                    } else if (commonResponseModel.getError().equals(getString(R.string.invalid_token)))
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

    private void isEmailUsernameAliasExist(CommonResponseModel commonResponseModel) {
        Log.i(TAG, "updateUI:commonResponseModel " + commonResponseModel.toString());
        if (!isEmailExist && !isUsernameExist && !isAliasExist) {
            if (createAccountParamModel != null) {
                createAccountDao.createAccount1(createAccountParamModel);
                redirectToAddVehicleFragment(new AddElectricVehicleFragment());
            }
        }
    }

    private void countryStateResponse(ApiResponse<CountryStateResponseModel> apiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        CountryStateResponseModel pickerResponseModel;
        if (apiResponse.getData() != null) {
            pickerResponseModel = apiResponse.getData();
            switch (apiResponse.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (pickerResponseModel != null)
                        updateUI(pickerResponseModel);
                    else if (pickerResponseModel.getError().equals(getString(R.string.invalid_token)))
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

    private void getSecretQuestionResponse(ApiResponse<GetQuestionResponseModel> apiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        GetQuestionResponseModel questionResponseModel;
        if (apiResponse.getData() != null) {
            questionResponseModel = apiResponse.getData();
            switch (apiResponse.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (questionResponseModel != null)
                        getSecretQuestions(questionResponseModel);
                    else if (questionResponseModel.getError().equals(getString(R.string.invalid_token)))
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

    private void updateUI(CountryStateResponseModel countryStateResponseModel) {
        Log.i(TAG, "updateUI:countryStateResponseModel " + countryStateResponseModel.toString());
        if (countryStateResponseModel.getStatus().equals("1")) {
            if (countryStateResponseModel.getSuccess().equalsIgnoreCase("Country list")) {
                if (countryStateResponseModel.getCountry_list().size() > 0) {
                    countryListBeans = new String[countryStateResponseModel.getCountry_list().size()];
                    for (int i = 0; i < countryStateResponseModel.getCountry_list().size(); i++) {
                        countryListBeans[i] = countryStateResponseModel.getCountry_list().get(i).getName();
                    }
                }
            }
            if (countryStateResponseModel.getSuccess().equalsIgnoreCase("States list")) {
                if (countryStateResponseModel.getStates_list().size() > 0) {
                    stateListBeans = new String[countryStateResponseModel.getStates_list().size()];
                    for (int i = 0; i < countryStateResponseModel.getStates_list().size(); i++) {
                        stateListBeans[i] = countryStateResponseModel.getStates_list().get(i).getName();
                    }
                }
            }
        }
    }

    private void getSecretQuestions(GetQuestionResponseModel questionResponseModel) {
        Log.i(TAG, "updateUI:commonResponseModel " + questionResponseModel.toString());
        if (questionResponseModel.getStatus().equals("1")) {
            if (questionResponseModel.getQuestions().size() > 0) {
                secretQuestions = new String[questionResponseModel.getQuestions().size()];
                questionsBeanArrayList.addAll(questionResponseModel.getQuestions());
                for (int i = 0; i < questionResponseModel.getQuestions().size(); i++) {
                    secretQuestions[i] = questionResponseModel.getQuestions().get(i).getQuestion();
                }
                updateFromDB(); // update the values from DB
            }
        }
    }

    //setting launching  fragment
    public void setFragment(Fragment fragment) {
        //For initially add stack for backpress event
        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.login_frame, fragment);
//        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }

    //setting launching  fragment
    public void redirectToAddVehicleFragment(Fragment fragment) {
        //For initially add stack for backpress event
//        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(Constant.BACK_STACK_ROOT_TAG);
//        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.login_frame, fragment);
//        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }

    /**
     * display the dialog which contains secret questions
     *
     * @param existingValue will shown when value is available
     */
    private void pickerDialog(final Context context, String[] list, int index, EditText
            eText, String pickerType, String existingValue, ArrayList<GetQuestionResponseModel.QuestionsBean> questionsBeanArrayList) {
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
                if (pickerType.equals("Secret Questions")) {
                    for (int i = 0; i < questionsBeanArrayList.size(); i++) {
                        if (questionsBeanArrayList.get(i).getQuestion().equals(itemName)) {
                            selectedQuestionId = questionsBeanArrayList.get(i).getId();
                        }
                    }
                }
            } else {
                if(!TextUtils.isEmpty(existingValue))
                    itemName = existingValue;
                else itemName = list[0];
                if (pickerType.equals("Secret Questions"))
                    selectedQuestionId = questionsBeanArrayList.get(0).getId();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
