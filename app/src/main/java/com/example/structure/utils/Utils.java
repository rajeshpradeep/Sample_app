package com.example.structure.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.structure.R;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.ui.control.ControlFragment;
import com.example.structure.ui.control_evse.ControlEVSEFragment;
import com.example.structure.ui.dashboard.DashboardFragment;
import com.example.structure.ui.login.LoginActivity;
import com.example.structure.ui.monitor.MonitorFragment;

import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 15-10-2019
 */
public class Utils {
    private static Typeface type;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;

    public static void setBlackFonts(Context context, TextView[] textViews) {
        type = Typeface.createFromAsset(context.getAssets(), "fonts/NunitoSans-Black.ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(type);
        }
    }

    public static void setBlackItalicFonts(Context context, TextView[] textViews) {
        type = Typeface.createFromAsset(context.getAssets(), "fonts/NunitoSans-BlackItalic.ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(type);
        }
    }

    public static void setBoldFonts(Context context, TextView[] textViews) {
        type = Typeface.createFromAsset(context.getAssets(), "fonts/NunitoSans-Bold.ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(type);
        }
    }

    public static void setBoldItalicFonts(Context context, TextView[] textViews) {
        type = Typeface.createFromAsset(context.getAssets(), "fonts/NunitoSans-BoldItalic.ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(type);
        }
    }

    public static void setRegularFonts(Context context, TextView[] textViews) {
        type = Typeface.createFromAsset(context.getAssets(), "fonts/NunitoSans-Regular.ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(type);
        }
    }

    public static void setItalicFonts(Context context, TextView[] textViews) {
        type = Typeface.createFromAsset(context.getAssets(), "fonts/NunitoSans-Italic.ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(type);
        }
    }

    public static void setLightFonts(Context context, TextView[] textViews) {
        type = Typeface.createFromAsset(context.getAssets(), "fonts/NunitoSans-Light.ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(type);
        }
    }

    public static void setSemiBoldFonts(Context context, TextView[] textViews) {
        type = Typeface.createFromAsset(context.getAssets(), "fonts/NunitoSans-SemiBold.ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(type);
        }
    }

    public static void setSemiItalicFonts(Context context, TextView[] textViews) {
        type = Typeface.createFromAsset(context.getAssets(), "fonts/NunitoSans-SemiBoldItalic.ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(type);
        }
    }

    public static String getConvertedErrorBody(Response response) {
        String value = null;
        try {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            value = jObjError.getString("error");
            Log.e("getConvertedErrorBody ", jObjError.toString());
        } catch (Exception e) {
            e.printStackTrace();
            value = "Internal Server Error";
        }
        return value;
    }

    public static void handleErrorResponse(Throwable throwable, Context context) {
        if (throwable instanceof IOException) {
            showCustomAlert(context, context.getResources().getString(R.string.alert), context.getString(R.string.no_internet_connection), "Login");
        } else {
            showCustomAlert(context, context.getResources().getString(R.string.alert), context.getResources().getString(R.string.something_wrong), "Login");
        }
    }

    /*
     * shown edittext error
     * */
    public static void setError(EditText editText, String errorMsg) {
        editText.requestFocus();
        editText.setError(errorMsg);
    }

    /*
     * check valid or invalid password
     * */
    public static void validPassword(EditText editText, String errorMsg) {
        if ((!android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString().trim()).matches())) {
            editText.requestFocus();
            editText.setError(errorMsg);
        }
    }

    /**
     * Get the screen width
     */
    public static int getWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * Custom Alert
     */
    public static void showCustomAlert(final Context context, String title, String message, final String from) {
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
        if (title != null)
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
        if (from.equals("app_close")) {
            dialog_negative.setVisibility(View.VISIBLE);
            dialog_separator.setVisibility(View.VISIBLE);
            dialog_positive.setText(context.getString(R.string.ok));
            dialog_negative.setText(context.getString(R.string.cancel));
            dialog_positive.setOnClickListener(v -> {
                ((Activity) context).finishAffinity();
                dialog.cancel();
                dialog.dismiss();
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

    /**
     * Okay Custom Alert
     */
    public static void showOKCustomAlert(final Context context, String title, String message, final String from) {
        final Dialog dialog = new Dialog(context, R.style.custom_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.common_custom_ok_dialog);
        dialog.setCancelable(false);
        dialog.show();
        TextView dialog_title = dialog.findViewById(R.id.title_tview);
        TextView dialog_message = dialog.findViewById(R.id.message_tview);
        Button dialog_ok_btn = dialog.findViewById(R.id.ok_btn);
        LinearLayout parent = dialog.findViewById(R.id.ok_dialog_layout);
        Utils.setBoldFonts(context, new TextView[]{dialog_ok_btn});
        Utils.setRegularFonts(context, new TextView[]{dialog_title, dialog_message});
        if (title != null) {
            dialog_title.setVisibility(View.VISIBLE);
            dialog_title.setText(title);
        } else {
            dialog_title.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(message))
            dialog_message.setText(message);
        else
            dialog_message.setVisibility(View.GONE);
        if (from.equals("app_close")) {
            dialog_ok_btn.setText(context.getString(R.string.ok));
            dialog_ok_btn.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();
                ((Activity) context).finishAffinity();
            });
        } else if (from.equals("session_expired")) {
            dialog_ok_btn.setOnClickListener(v -> {
//                sharedPreferencesUtil.deleteAllSession();
                dialog.cancel();
                dialog.dismiss();
                Intent intent = new Intent(context, LoginActivity.class);
                ((Activity) context).startActivity(intent);
                ((Activity) context).finish();
            });
        } else if (from.equals("email_not_verified")) { // after changing the email from Profile info redirect to dashboard
            NavController navController = Navigation.findNavController((Activity) context, R.id.my_nav_host_fragment);
            dialog_ok_btn.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();
                navController.navigate(R.id.action_personalInfoFragment_to_bottom_menu_dashboard);
            });
        } else if (from.equals("cp_success")) { // after changing the email from Profile info redirect to dashboard
            NavController navController = Navigation.findNavController((Activity) context, R.id.my_nav_host_fragment);
            dialog_ok_btn.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();
                navController.popBackStack();
            });
        } else {
            if (from.equals("control_tip")) {
                dialog_message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                dialog_message.setText(HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_LEGACY));
                dialog_message.setMovementMethod(new ScrollingMovementMethod());
            }
            dialog_ok_btn.setText(context.getString(R.string.ok));
            dialog_ok_btn.setOnClickListener(v -> {
                dialog.cancel();
                dialog.dismiss();
            });
        }
    }

    /**
     * Add device Custom Alert
     */
    public static Dialog addDeviceCustomAlert(final Context context, String title, String message, final String from, int navId) {
        NavController navController = Navigation.findNavController((Activity) context, R.id.my_nav_host_fragment);
        final Dialog dialog = new Dialog(context, R.style.custom_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.common_custom_ok_dialog);
        dialog.setCancelable(true);
        if (!dialog.isShowing())
            dialog.show();

        TextView dialog_title = dialog.findViewById(R.id.title_tview);
        TextView dialog_message = dialog.findViewById(R.id.message_tview);
        Button dialog_ok_btn = dialog.findViewById(R.id.ok_btn);
        LinearLayout parent = dialog.findViewById(R.id.ok_dialog_layout);
        dialog_ok_btn.setText(context.getResources().getString(R.string.add_device));
        dialog_ok_btn.setAllCaps(false);
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
        dialog_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.getFunctionalFragment(((Activity) context).getParent(), ((BottomMenuActivity) context).getSupportFragmentManager(), "MonitorFragment")) {
                    navController.navigate(navId);
                }
                dialog.cancel();
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public static void usPhoneCode(EditText phone_etext) {
        phone_etext.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {
            private boolean backspacingFlag = false;
            private boolean editedFlag = false;
            private int cursorComplement;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                cursorComplement = s.length() - phone_etext.getSelectionStart();
                if (count > after) {
                    backspacingFlag = true;
                } else {
                    backspacingFlag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // nothing to do here =D
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                String phone = string.replaceAll("[^\\d]", "");
                if (!editedFlag) {
                    if (phone.length() >= 6 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6);
                        phone_etext.setText(ans);
                        phone_etext.setSelection(phone_etext.getText().length() - cursorComplement);
                    } else if (phone.length() >= 3 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3);
                        phone_etext.setText(ans);
                        phone_etext.setSelection(phone_etext.getText().length() - cursorComplement);
                    }
                } else {
                    editedFlag = false;
                }
            }
        });
    }

    /**
     * Round off double
     */
    public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    /**
     * Date Comparision
     **/
    public static String checkDates(String startDate, String endDate) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dfDate = new SimpleDateFormat("MM/dd/yyyy");
        boolean b = false;
        String status = null;
        try {
            if (dfDate.parse(startDate).before(dfDate.parse(endDate))) {
                status = "before"; // If start date is before end date.
            } else if (dfDate.parse(startDate).equals(dfDate.parse(endDate))) {
                status = "equal";  // If two dates are equal.
            } else {
                status = "after";// If start date is after the end date.
            }
        } catch (ParseException e) {
            status = "default";
            e.printStackTrace();
        }
        return status;
    }

    public static Dialog setMargins(Dialog dialog, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        Window window = dialog.getWindow();
        if (window == null) {
            // dialog window is not available, cannot apply margins
            return dialog;
        }
        Context context = dialog.getContext();
        // set dialog to fullscreen
        RelativeLayout root = new RelativeLayout(context);
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        // set background to get rid of additional margins
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        // apply left and top margin directly
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        Point size = new Point();
        Display display = dialog.getWindow().getWindowManager().getDefaultDisplay();
        display.getSize(size);
        attributes.height = (int) (size.x * 0.9);
        attributes.x = marginLeft;
        attributes.y = marginTop;
        window.setAttributes(attributes);
        // set right and bottom margin implicitly by calculating width and height of dialog
        Point displaySize = getDisplayDimensions(context);
        int width = displaySize.x - marginLeft - marginRight;
        int height = displaySize.y - marginTop - marginBottom;
        window.setLayout(width, height);
        return dialog;
    }

    @NonNull
    public static Point getDisplayDimensions(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;
        // find out if status bar has already been subtracted from screenHeight
        display.getRealMetrics(metrics);
        int physicalHeight = metrics.heightPixels;
        int statusBarHeight = getStatusBarHeight(context);
        int navigationBarHeight = getNavigationBarHeight(context);
        int heightDelta = physicalHeight - screenHeight;
        if (heightDelta == 0 || heightDelta == navigationBarHeight) {
            screenHeight -= statusBarHeight;
        }
        return new Point(screenWidth, screenHeight);
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return (resourceId > 0) ? resources.getDimensionPixelSize(resourceId) : 0;
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return (resourceId > 0) ? resources.getDimensionPixelSize(resourceId) : 0;
    }

    public static int dpToPx(Context context, int dp) {
        float density = context.getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }

    public static void slideDown(final View view) {
        view.animate()
                .translationY(view.getHeight())
                .alpha(0.f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // superfluous restoration
                        view.setVisibility(View.GONE);
                        view.setAlpha(1.f);
                        view.setTranslationY(0.f);
                    }
                });
    }

    public static void slideUp(final View view) {
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0.f);
        if (view.getHeight() > 0) {
            slideUpNow(view);
        } else {
            // wait till height is measured
            view.post(new Runnable() {
                @Override
                public void run() {
                    slideUpNow(view);
                }
            });
        }
    }

    private static void slideUpNow(final View view) {
        view.setTranslationY(view.getHeight());
        view.animate()
                .translationY(0)
                .alpha(1.f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                        view.setAlpha(1.f);
                    }
                });
    }

    public static boolean getFunctionalFragment(Activity context, FragmentManager fragmentManager, String fragmentName) {
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.my_nav_host_fragment);
        FragmentManager navHostManager = Objects.requireNonNull(navHostFragment).getChildFragmentManager();
        List fragment_list = navHostManager.getFragments();
        for (int i = 0; i < fragment_list.size(); i++) {
            if (fragment_list.get(i) instanceof DashboardFragment ||
                    fragment_list.get(i) instanceof MonitorFragment || fragment_list.get(i) instanceof ControlFragment || fragment_list.get(i) instanceof ControlEVSEFragment) {
                return true;
            }
        }
        return false;
    }

    /**
     * Convert UTC time to local time
     *
     * @param time UTC time
     **/
    public static String getLocalTimeFromUTC(String time) {
        Date fromDate = null;
        String output = time;
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        DateFormat outPutFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        try {
            fromDate = dateFormat.parse(time);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat date = new SimpleDateFormat("dd", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat month = new SimpleDateFormat("MM", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat hr = new SimpleDateFormat("HH", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat min = new SimpleDateFormat("mm", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sec = new SimpleDateFormat("ss", Locale.getDefault());

            Calendar calendar = new GregorianCalendar(Integer.parseInt(year.format(fromDate)),
                    Integer.parseInt(month.format(fromDate)),
                    Integer.parseInt(date.format(fromDate)),
                    Integer.parseInt(hr.format(fromDate)),
                    Integer.parseInt(min.format(fromDate)),
                    Integer.parseInt(sec.format(fromDate)));

            TimeZone tz = TimeZone.getDefault();
            boolean inDs = tz.inDaylightTime(new Date());
            int offset = 0;
            if (inDs) {
                offset = TimeZone.getDefault().getRawOffset() + TimeZone.getDefault().getDSTSavings();
            } else {
                offset = TimeZone.getDefault().getRawOffset();
            }
            long now = calendar.getTimeInMillis() + offset;
            Date epochDate = new Date(now);
            output = outPutFormat.format(epochDate.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }


    /**
     * Returns the  device name in capital
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }


    /**
     * Convert UTC time to local time by given format
     *
     * @param time UTC time
     **/
    public static String getLocalTimeFromUTCByFormat(String time, String inputFormat, String outputFormat) {
        Date fromDate = null;
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat dateFormat = new SimpleDateFormat(inputFormat, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        DateFormat outPutFormat = new SimpleDateFormat(outputFormat, Locale.getDefault());
//        outPutFormat.setTimeZone(TimeZone.getTimeZone("CST6CDT"));
        outPutFormat.setTimeZone(TimeZone.getDefault());
        try {
            fromDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outPutFormat.format(fromDate);
    }

    /**
     * Convert UTC time to local time by given format
     *
     * @param time UTC time
     **/
    public static String getUTCTimeFromLocalTimeFormat(String time, String inputFormat, String outputFormat) {
        Date fromDate = null;
        String output = outputFormat;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(inputFormat, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getDefault());
        try {
            fromDate = dateFormat.parse(time);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat date = new SimpleDateFormat("dd", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat month = new SimpleDateFormat("MM", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat hr = new SimpleDateFormat("HH", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat min = new SimpleDateFormat("mm", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sec = new SimpleDateFormat("ss", Locale.getDefault());


            Calendar calendar = new GregorianCalendar(Integer.parseInt(year.format(fromDate)),
                        Integer.parseInt(month.format(fromDate)) - 1,
                    Integer.parseInt(date.format(fromDate)),
                    Integer.parseInt(hr.format(fromDate)),
                    Integer.parseInt(min.format(fromDate)),
                    Integer.parseInt(sec.format(fromDate)));
//            int offset = TimeZone.getDefault().getRawOffset() + TimeZone.getDefault().getDSTSavings();
            long utcTimeStamp = TimeZoneUtils.toUTC(calendar.getTimeInMillis(), calendar.getTimeZone());
            Calendar outputCalendar = Calendar.getInstance();
            outputCalendar.setTimeInMillis(utcTimeStamp);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outPutFormat = new SimpleDateFormat(outputFormat);
            output = outPutFormat.format(outputCalendar.getTime()) + " +0000";
//            output = outPutFormat.format(outputCalendar.getTime());

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("Utils", "getUTCTimeFromLocalTimeFormat: " + output);
        return output;
    }
   /* public static Date dateFromUTC(Date date){
        return new Date(date.getTime() + Calendar.getInstance().getTimeZone().getOffset(date.getTime()));
    }*/

    public static Date dateToUTC(Date date) {
        return new Date(date.getTime() - Calendar.getInstance().getTimeZone().getOffset(date.getTime()));
    }

    public static long toLocalTime(long time, TimeZone to) {
        return time + to.getOffset(time);
    }

    public static long toUTC(long time, TimeZone from) {
        return time - from.getOffset(time);
    }

    public static String getLocalTimeFromUTCWithoutAMPM(String time) {
        Date fromDate = null;
        String output = time;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        DateFormat outPutFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
//        outPutFormat.setTimeZone(TimeZone.getDefault());
        try {
            fromDate = dateFormat.parse(time);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat date = new SimpleDateFormat("dd", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat month = new SimpleDateFormat("MM", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat hr = new SimpleDateFormat("HH", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat min = new SimpleDateFormat("mm", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sec = new SimpleDateFormat("ss", Locale.getDefault());


            Calendar calendar = new GregorianCalendar(Integer.parseInt(year.format(fromDate)),
                    Integer.parseInt(month.format(fromDate)),
                    Integer.parseInt(date.format(fromDate)),
                    Integer.parseInt(hr.format(fromDate)),
                    Integer.parseInt(min.format(fromDate)),
                    Integer.parseInt(sec.format(fromDate)));
            long localTimeStamp = 0;
            DateTimeFormatter shortTimeZoneFormatter = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
               /* int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion >= 26) {
                    shortTimeZoneFormatter = DateTimeFormatter.ofPattern("zzz", Locale.getDefault());
                String tZ = ZonedDateTime.now(ZoneId.systemDefault()).format(shortTimeZoneFormatter);
                    if (currentapiVersion == 28) {
//                    if (getDeviceName().toUpperCase().contains("GOOGLE"))
                        if(getDeviceName().toUpperCase().contains("REDMI") || getDeviceName().toUpperCase().contains("SAMSUNG")
                                || getDeviceName().toUpperCase().contains("ONEPLUS")  || getDeviceName().toUpperCase().contains("VIVO") )
                            localTimeStamp = TimeZoneUtils.toLocalTime(calendar.getTimeInMillis(),calendar.getTimeZone());
                        else
                            localTimeStamp = TimeZoneUtils.toLocalTime(calendar.getTimeInMillis(),  TimeZone.getTimeZone(tZ));
                    }
                    else
                        localTimeStamp = TimeZoneUtils.toLocalTime(calendar.getTimeInMillis(),  TimeZone.getTimeZone(tZ));
                }else {
                TimeZone tz = TimeZone.getDefault();
                String timezone = tz.getDisplayName(tz.inDaylightTime(new Date()), TimeZone.SHORT);
                localTimeStamp = TimeZoneUtils.toLocalTime(calendar.getTimeInMillis(), TimeZone.getTimeZone(timezone));
            }
          *//*  Calendar outputCalendar = Calendar.getInstance();
            if(localTimeStamp!=0)
                outputCalendar.setTimeInMillis(localTimeStamp);
            output = outPutFormat.format(outputCalendar.getTime());*//*
//            Log.e("timezone", "getLocalTimeFromUTCWithoutAMPM: " + calendar.getTimeZone() + " "+ time);
            if(localTimeStamp!=0) {
                Date epochDate = new Date(localTimeStamp);
                output = outPutFormat.format(epochDate.getTime());
            }
*/

            TimeZone tz = TimeZone.getDefault();
            boolean inDs = tz.inDaylightTime(new Date());
            int offset = 0;
            if (inDs) {
                offset = TimeZone.getDefault().getRawOffset() + TimeZone.getDefault().getDSTSavings();
            } else {
                offset = TimeZone.getDefault().getRawOffset();
            }
            long now = calendar.getTimeInMillis() + offset;
            Date epochDate = new Date(now);
            output = outPutFormat.format(epochDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;

    }

    public static String getUTCTimeFromLocal(String time) {
        Date fromDate = null;
        String output = time;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getDefault());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outPutFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
//        outPutFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            fromDate = dateFormat.parse(time);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat date = new SimpleDateFormat("dd", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat month = new SimpleDateFormat("MM", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat hr = new SimpleDateFormat("HH", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat min = new SimpleDateFormat("mm", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sec = new SimpleDateFormat("ss", Locale.getDefault());


            Calendar calendar = new GregorianCalendar(Integer.parseInt(year.format(fromDate)),
                    Integer.parseInt(month.format(fromDate)) - 1,
                    Integer.parseInt(date.format(fromDate)),
                    Integer.parseInt(hr.format(fromDate)),
                    Integer.parseInt(min.format(fromDate)),
                    Integer.parseInt(sec.format(fromDate)));
//            int offset = TimeZone.getDefault().getRawOffset() + TimeZone.getDefault().getDSTSavings();
            long utcTimeStamp = TimeZoneUtils.toUTC(calendar.getTimeInMillis(), calendar.getTimeZone());
            Calendar outputCalendar = Calendar.getInstance();
            outputCalendar.setTimeInMillis(utcTimeStamp);
            output = outPutFormat.format(outputCalendar.getTime());
            Log.i("Utils", "getUTCTimeFromLocal: " + output);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * get the default time zone
     * ex: Indian Timezone is : +05:30
     **/
    public static String getDefaultTimeZone() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        Date currentLocalTime = calendar.getTime();
        DateFormat date = new SimpleDateFormat("ZZZZZ", Locale.getDefault());
        String timeZone = date.format(currentLocalTime);
        System.out.println(timeZone + "  TimeZone   ");
        return timeZone;
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = null;
        Date date = null;
        try {
            date = new Date();
            dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(date);
    }

    /**
     * Check the internet connection
     **/
    public static boolean isConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isOnline = (networkInfo != null && networkInfo.isConnected());
        if (!isOnline)
            Toast.makeText(context, " Please Check Your Internet Connection ", Toast.LENGTH_SHORT).show();
        return isOnline;
    }

    /**
     * Get Local time from UTC time
     **/
    public Date getUTCToLocalDate(String date) {
        Date inputDate = new Date();
        if (date != null && !date.isEmpty()) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                inputDate = simpleDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return inputDate;
    }

    /**
     * Get UTC time from Local time
     *
     * @param date local date time
     **/
    public String getLocalToUTCDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date time = calendar.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        outputFmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return outputFmt.format(time);
    }
}
