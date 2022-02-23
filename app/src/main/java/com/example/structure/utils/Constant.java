package com.example.structure.utils;

import com.example.structure.BuildConfig;

/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
public class Constant {
    public static String BASE_URL = BuildConfig.BASE_URL; //"https://dev:vjetWcoh@dashboard.qmulusev.com.farshore.net/api/v1";
    public static String TOS_URL= BuildConfig.TOS_URL;
    public static String PP_URL= BuildConfig.PP_URL;
    public static String ARGONNE_DEVICES_URL= BuildConfig.ARGONNE_DEVICES_URL;

    public static String ARGONNE_DASHBOARD_URL= "https://dashboard.qmulusev.com.farshore.net/dashboard";
    public static String BASE_URL_AUTH= "dev";
    public static String BASE_URL_VALUE= "mr1JB33D";
    public static int SPLASH_TIME_OUT = 2000;

    // ----------------------------------------------------------co-----------------------------------
    /*Endpoints*/
    public static final String LOGIN_ENDPOINT = "login";
    public static final String FORGOT_PASSWORD_ENDPOINT = "forgotpassword";
    public static final String GET_USERTYPE_ENDPOINT = "getUserType";
    public static final String LOGOUT_ENDPOINT = "logout";

    public static final String DASHBOARD_ENDPOINT = "dashboard";
    public static final String RESEND_EMAIL_ENDPOINT = "resendMail";
    public static final String GET_YEAR_LIST_ENDPOINT = "getYearList";
    public static final String GET_MAKE_LIST_ENDPOINT = "getMakeList";
    public static final String GET_MODEL_LIST_ENDPOINT = "getModelList";
    public static final String CHANGE_PASSWORD_ENDPOINT = "updatePassword";

    public static final String MONITOR_ENDPOINT = "monitor";

    // Control end point
    public static final String CONTROL_ENDPOINT = "control";
    public static final String UPDATE_EVSE_MODE_STATUS_ENDPOINT = "updateEvseModeStatus";
    public static final String UPDATE_REMOTE_OPERATION_ENDPOINT = "updateRemoteOperation";

    public static final String EV_MILEAGE_CHANGE_VEHICLE_ENDPOINT = "evMileageChangeVehicle";
    public static final String PUBLISH_CHARGE_MODE_ENDPOINT = "publishChargeMode";
    public static final String UPDATE_EV_MILEAGE_ENDPOINT = "updateEvMileage";
    public static final String UPDATE_TIMER_CONTROL_ENDPOINT = "updateTimerControl";

    //Create acc endpoint
    public static final String CHECK_EMAIL_EXISTS_ENDPOINTS = "checkEmailExists";
    public static final String CHECK_USER_NAME_EXISTS_ENDPOINTS = "checkUserNameExists";
    public static final String GET_COUNTRY_ENDPOINTS = "getCountry";
    public static final String GET_STATES_ENDPOINTS = "getStates";
    public static final String GET_QUESTION_ENDPOINTS = "getQuestion";
    public static final String SIGNUP_ENDPOINTS = "signup";
    public static final String LEADER_BOARD_ALIAS_CHECK_ENDPOINTS = "checkLeaderboardAlias";

    public static final String LEADERBOARD_ENDPOINTS = "leaderboard";

    public static final String NOTIFICATION_ENDPOINTS = "notification";
    public static final String UPDATE_NOTIFICATION_ENDPOINTS = "updateNotification";

    public static final String PERSONAL_INFO_ENDPOINTS = "personalInfo";
    public static final String UPDATE_PERSONAL_INFO_ENDPOINTS = "updatePersonalInfo";

    public static final String SESSION_LIST_ENDPOINTS = "sessionList";
    public static final String SESSION_DETAILS_ENDPOINTS = "sessionDetails";

    // Vehicle
    public static final String USER_VEHICLES_ENDPOINT = "userVehicles";
    public static final String ADD_VEHICLE_ENDPOINT = "addVehicle";
    public static final String UPDATE_VEHICLE_ENDPOINT = "updateVehicle";
    public static final String DELETE_VEHICLE_ENDPOINT = "deleteVehicle";

    //----------------------------------------------------------------------------------------------

    // URL path
    public static final String YEAR_PARAM = "year";
    public static final String MAKE_PARAM = "make";
    public static final String MODEL_PARAM = "model";
    public static final String OFFSET_PARAM = "offset";
    public static final String LIMIT_PARAM = "limit";
    public static final String CONNECTOR_ID_PARAM = "connectorId";
    public static final String CHARGEBOX_ID_PARAM = "chargeboxId";
    public static final String PORT_ID_PARAM = "portId";
    public static final String DEVICE_ID_PARAM = "deviceId";
    public static final String ACTIVE_SESSION_ID_PARAM = "activeSession";
    public static final String VEHICLE_ID_PARAM = "vehicleId";
    public static final String TRANSACTION_ID_PARAM = "transactionId";
    public static final String TRANSACTION_TYPE_PARAM = "transactionType";


    // session keys
    public static final String BACK_STACK_ROOT_TAG = "root_fragment";
    public static final String FINISHED = "finished";
    public static final String BACK_STACK_ZERO = "0";
    public static final String SESSION_NAME = "qmulus_session";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_ID = "user_id";
    public static final String USER_TOKEN = "user_token";
    public static final String USER_TYPE = "user_type";
    public static final String DEVICE_ID = "device_id";
    public static final String FIREBASE_TOKEN = "firebase_token";

    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

    public static final String GRAFANA_DASHBOARD_URL = "https://web1.qmulusev.com.farshore.net:3000/d-solo/oGF9xk0Zz/argonne?orgId=1&refresh=1m&var-device_id=";
    public static final String GRAFANA_CONTROL_URL = "https://web1.qmulusev.com.farshore.net:3000/d-solo/sCZ8PiJZk/argonne-control?orgId=1&refresh=1m&var-device_id=";
}
