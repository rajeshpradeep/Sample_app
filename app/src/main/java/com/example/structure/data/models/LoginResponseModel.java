package com.example.structure.data.models;

/**
 * Created by Rajesh Pradeep G on 29-10-2019
 */
public class LoginResponseModel {

    /**
     * status : 1
     * success : Login Successfull
     * user_data : {"email":"testuser@gmail.com","user_id":1,"username":"testuser","userType":"SCA"}
     * auth_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvZGFzaGJvYXJkLnFtdWx1c2V2LmNvbS5mYXJzaG9yZS5uZXRcL2FwaVwvbG9naW4iLCJpYXQiOjE1ODM4NDM5NDQsIm5iZiI6MTU4Mzg0Mzk0NCwianRpIjoiNkg2MXhKeWo1UTZ3MHd2aSIsInN1YiI6MSwicHJ2IjoiODdlMGFmMWVmOWZkMTU4MTJmZGVjOTcxNTNhMTRlMGIwNDc1NDZhYSJ9.dofTGgFjvsM9DY_lAhRLeQ0ChqH853naYr6MFHh4hLw
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private UserDataBean user_data;
    private String auth_token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDataBean getUser_data() {
        return user_data;
    }

    public void setUser_data(UserDataBean user_data) {
        this.user_data = user_data;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public static class UserDataBean {
        /**
         * email : testuser@gmail.com
         * user_id : 1
         * username : testuser
         * userType : SCA
         */

        private String email;
        private int user_id;
        private String username;
        private String userType;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
