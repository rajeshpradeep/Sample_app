package com.example.structure.data.models;

/**
 * Created by Rajesh Pradeep G on 10-03-2020
 */
public class EVSEModeStatusResponseModel {


    /**
     * status : 1
     * message : Remote started failed
     * apiresponse : {"error":{"message":"Remote start transaction request failed to initiate","description":"Couldn't send a request to OCPP server due to invalid data","reason":"Station OCPP E8B7 not connected"},"data":""}
     * activeSessionStatus : stop
     */

    private String status;
    private String success;
    private String message;
    private String error;
    private String updateEvseMode;
    private ApiresponseBean apiresponse;
    private String activeSessionStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getUpdateEvseMode() {
        return updateEvseMode;
    }

    public void setUpdateEvseMode(String updateEvseMode) {
        this.updateEvseMode = updateEvseMode;
    }

    public ApiresponseBean getApiresponse() {
        return apiresponse;
    }

    public void setApiresponse(ApiresponseBean apiresponse) {
        this.apiresponse = apiresponse;
    }

    public String getActiveSessionStatus() {
        return activeSessionStatus;
    }

    public void setActiveSessionStatus(String activeSessionStatus) {
        this.activeSessionStatus = activeSessionStatus;
    }

    public static class ApiresponseBean {
        /**
         * error : {"message":"Remote start transaction request failed to initiate","description":"Couldn't send a request to OCPP server due to invalid data","reason":"Station OCPP E8B7 not connected"}
         * data :
         */

        private ErrorBean error;
        private DataBean dataBean;

        public ErrorBean getError() {
            return error;
        }

        public void setError(ErrorBean error) {
            this.error = error;
        }

        public DataBean getDataBean() {
            return dataBean;
        }

        public void setDataBean(DataBean dataBean) {
            this.dataBean = dataBean;
        }

        public static class DataBean {
            private String status;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class ErrorBean {
            /**
             * message : Remote start transaction request failed to initiate
             * description : Couldn't send a request to OCPP server due to invalid data
             * reason : Station OCPP E8B7 not connected
             */

            private String message;
            private String description;
            private String reason;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }
        }
    }
}
