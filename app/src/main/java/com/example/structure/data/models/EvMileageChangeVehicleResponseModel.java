package com.example.structure.data.models;

/**
 * Created by Rajesh Pradeep G on 09-03-2020
 */
public class EvMileageChangeVehicleResponseModel {

    /**
     * status : 1
     * success : Range Available
     * maxEvRange : {"maxRange":"22","acc_ev_val":0}
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private MaxEvRangeBean maxEvRange;

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

    public MaxEvRangeBean getMaxEvRange() {
        return maxEvRange;
    }

    public void setMaxEvRange(MaxEvRangeBean maxEvRange) {
        this.maxEvRange = maxEvRange;
    }

    public static class MaxEvRangeBean {
        /**
         * maxRange : 22
         * acc_ev_val : 0
         */

        private String maxRange;
        private double accEvVal;

        public String getMaxRange() {
            return maxRange;
        }

        public void setMaxRange(String maxRange) {
            this.maxRange = maxRange;
        }

        public double getAccEvVal() {
            return accEvVal;
        }

        public void setAccEvVal(double accEvVal) {
            this.accEvVal = accEvVal;
        }
    }
}
