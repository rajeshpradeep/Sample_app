package com.example.structure.ui.login.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import retrofit2.Response;

import android.text.TextUtils;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.GetQuestionResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.support.CustomNumberPicker;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.login.asyntask.LoginTask;
import com.example.structure.utils.Constant;
import com.example.structure.utils.ResponseInterface;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends BaseFragment implements ResponseInterface {

    private String TAG = getClass().getSimpleName();
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @Inject
    LoginTask loginTask;
    @Inject
    Executor executor;
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private CustomProgressDialog customProgressDialog;

    @BindView(R.id.img_fp_logo)
    ImageView img_fp_logo;
    @BindView(R.id.fb_lbl)
    TextView fb_lbl;
    @BindView(R.id.email_lbl)
    TextView email_lbl;
    @BindView(R.id.email_layout)
    LinearLayout email_layout;
    @BindView(R.id.email_etext)
    EditText email_etext;
    @BindView(R.id.fp_base_lay)
    ConstraintLayout fp_base_lay;
    @BindView(R.id.question_lbl)
    TextView question_lbl;
    @BindView(R.id.question_etext)
    EditText question_etext;
    @BindView(R.id.answer_layout)
    LinearLayout answer_layout;
    @BindView(R.id.answer_lbl)
    TextView answer_lbl;
    @BindView(R.id.answer_etext)
    EditText answer_etext;
    @BindView(R.id.back_to_tview)
    TextView back_to_tview;
    @BindView(R.id.reset_password_btn)
    Button reset_password_btn;
    @BindView(R.id.fp_scroll)
    NestedScrollView fp_scroll;
    @BindView(R.id.fp_content_clay)
    ConstraintLayout fp_content_clay;

    private String[] secretQuestions;
    private String itemName;
    private int selectedPosition, indexPos = 0, questionID;
    private ArrayList<GetQuestionResponseModel.QuestionsBean> questionsBeanArrays;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureDagger();
//        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
        loginTask.secretQuestionAPI(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        fragmentManager = getFragmentManager();
        initUI();
        return view;
    }

    /**
     * initialize the UI
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
//        removeStatusBar();
        setStatusBarGradiant(getActivity());
        setFont();

        disableEdittextInput(question_etext);

        question_etext.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                String existingVal = null;
                if (question_etext.getText().toString().trim().length() > 0)
                    existingVal = question_etext.getText().toString();

                if (secretQuestions != null) {
                    Log.i(TAG, "onTouch:secret ques length " + secretQuestions.length);
                    pickerDialog(mContext, 2, secretQuestions, indexPos - 2,
                            question_etext, "Secret Questions", existingVal, questionsBeanArrays);
                }
            }
            return true;
        });
        answer_etext.setImeOptions(EditorInfo.IME_ACTION_DONE);
        answer_etext.setOnEditorActionListener((textView, i, keyEvent) -> {
            if ((keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) || (i == EditorInfo.IME_ACTION_DONE)) {
                reset_password_btn.performClick();
                return true;
            }
            return false;
        });

        email_etext.setOnEditorActionListener((textView, i, keyEvent) -> {
            if ((keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) || (i == EditorInfo.IME_ACTION_NEXT)) {
                hideKeyboardFrom(mContext, email_etext);
                return true;
            }
            return false;
        });
    }

    private void setFont() {
        Utils.setLightFonts(mContext, new TextView[]{fb_lbl});
        Utils.setBoldFonts(mContext, new TextView[]{email_lbl, question_lbl, answer_lbl, reset_password_btn});
        Utils.setRegularFonts(mContext, new TextView[]{back_to_tview, question_etext, answer_etext,
                email_etext});
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.reset_password_btn, R.id.fp_base_lay, R.id.back_to_tview, R.id.fp_scroll, R.id.fp_content_clay})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.fp_content_clay:
                hideKeyboardFrom(mContext, fp_content_clay);
                break;
            case R.id.fp_scroll:
                hideKeyboardFrom(mContext, fp_scroll);
                break;
            case R.id.fp_base_lay:
                hideKeyboardFrom(mContext, fp_base_lay);
                break;
            case R.id.back_to_tview:
                setFragment(new LoginFragment());
                break;
            case R.id.reset_password_btn:
                String email = email_etext.getText().toString().trim();
                String secret_ques = question_etext.getText().toString().trim();
                String secret_ans = answer_etext.getText().toString().trim();
                if (!TextUtils.isEmpty(email)) {
                    if (isValidEmail(email)) {
                        if (!TextUtils.isEmpty(secret_ques)) {
                            if (!TextUtils.isEmpty(secret_ans)) {
                                forgotPasswordApiCall(email, String.valueOf(questionID), secret_ans);
                            } else
                                Utils.showOKCustomAlert(mContext, null, "Please answer the secret question.", "answer_empty");
                        } else
                            Utils.showOKCustomAlert(mContext, null, "Please select a secret question.", "question_empty");
                    } else
                        Utils.showOKCustomAlert(mContext, null, "Please enter valid email.", "invalid_email");

                } else
                    Utils.showOKCustomAlert(mContext, null, "Please enter your email.", "email_empty");
                break;
        }
    }

    private void forgotPasswordApiCall(String email, String secretQuestion, String secretAnswer) {
        customProgressDialog = CustomProgressDialog.show(mContext, true, false);
        ParamModel paramModel = new ParamModel();
        paramModel.setSecret_question(secretQuestion);
        paramModel.setSecret_answer(secretAnswer);
        paramModel.setEmail(email);
        loginTask.forgotPasswordAPI(paramModel, this);
    }

    /**
     * setting launching  fragment
     */
    public void setFragment(Fragment fragment) {
        //FOr initially add stack for backpress event
        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.login_frame, fragment);
//        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }

    /**
     * display the dialog which contains secret questions
     *
     * @param existingValue will shown when DB value is available
     */
    private void pickerDialog(final Context context, int offSet, String[] list, int index, EditText eText, String pickerType,
                              String existingValue, ArrayList<GetQuestionResponseModel.QuestionsBean> questionsBeanArrayList) {
        final Dialog numberPickerDialog = new Dialog(context, R.style.custom_dialog);
        numberPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        numberPickerDialog.setCancelable(false);
        numberPickerDialog.setContentView(R.layout.custom_number_picker_dialog);

        CustomNumberPicker np = numberPickerDialog.findViewById(R.id.wheel_view_wv);
        TextView positive_btn = numberPickerDialog.findViewById(R.id.dialogButtonOK);
        TextView negative_btn = numberPickerDialog.findViewById(R.id.dialogButtonNO);
        TextView pickerTitle = numberPickerDialog.findViewById(R.id.dialogTitle);
        Utils.setBoldFonts(context, new TextView[]{positive_btn, negative_btn, pickerTitle});

        np.setOffset(offSet);
        np.setItems(Arrays.asList(list));
        np.setSeletion(index);
        pickerTitle.setText(pickerType);
        np.setOnWheelViewListener(new CustomNumberPicker.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                itemName = item;
                selectedPosition = selectedIndex;
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
                for (int i = 0; i < questionsBeanArrayList.size(); i++) {
                    if (questionsBeanArrayList.get(i).getQuestion().equals(itemName))
                        questionID = questionsBeanArrayList.get(i).getId();
                }
            } else {
                questionID = questionsBeanArrayList.get(0).getId();
                itemName = list[0];
                eText.setText(itemName);
            }
            eText.setError(null);

            itemName = "";
            selectedPosition = 0;

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

        Utils.setBoldFonts(context, new TextView[]{dialog_ok_btn});
        Utils.setRegularFonts(context, new TextView[]{dialog_title, dialog_message});
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

        if (from.equals("fp_success")) {
            dialog_ok_btn.setText(context.getString(R.string.ok));
            dialog_ok_btn.setOnClickListener(v -> {
                setFragment(new LoginFragment());
                dialog.dismiss();
                dialog.cancel();
            });
        } else {
            dialog_ok_btn.setText(context.getString(R.string.ok));
            dialog_ok_btn.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();

            });
        }
    }

    @Override
    public void onSuccess(Object data) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        if (data instanceof GetQuestionResponseModel) {
            GetQuestionResponseModel getQuestionResponseModel = (GetQuestionResponseModel) data;
            questionsBeanArrays = new ArrayList<>();
            questionsBeanArrays.addAll(getQuestionResponseModel.getQuestions());
            secretQuestions = new String[getQuestionResponseModel.getQuestions().size()];
            for (int i = 0; i < getQuestionResponseModel.getQuestions().size(); i++) {
                secretQuestions[i] = getQuestionResponseModel.getQuestions().get(i).getQuestion();
            }
        } else if (data instanceof CommonResponseModel) {
            CommonResponseModel commonResponseModel = (CommonResponseModel) data;
            if (commonResponseModel.getStatus().equals("1")) {
                toastMessage(commonResponseModel.getMessage());
                showOKCustomAlert(mContext, null, commonResponseModel.getMessage(), "fp_success");
            } else if (commonResponseModel.getStatus().equals("0"))
                Utils.showCustomAlert(mContext, getString(R.string.alert), commonResponseModel.getMessage(), "fp_failed");
        }
    }

    @Override
    public void onSuccess(List data) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
    }

    @Override
    public void onError(Throwable throwable) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        Utils.handleErrorResponse(throwable, mContext);
    }

    @Override
    public void onFailure(Response response) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        Utils.showCustomAlert(mContext, getString(R.string.alert), Utils.getConvertedErrorBody(response), "fb_failed");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
