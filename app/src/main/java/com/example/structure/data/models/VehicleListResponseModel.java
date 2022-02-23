package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 25-11-2019
 */
public class VehicleListResponseModel {

    /**
     * status : 1
     * success : User Vehicles
     * userVehicles : [{"vehicle_id":4,"make":"Audi","model":"A3 e-tron","year":"2018","combE":"41","range":"0","rangeA":"16"},{"vehicle_id":66,"make":"BMW","model":"530e","year":"2020","combE":"47","range":"0","rangeA":"21"}]
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private List<UserVehiclesBean> userVehicles;
    private int disableVehicleId;

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

    public int getDisableVehicleId() {
        return disableVehicleId;
    }

    public void setDisableVehicleId(int disableVehicleId) {
        this.disableVehicleId = disableVehicleId;
    }

    public List<UserVehiclesBean> getUserVehicles() {
        return userVehicles;
    }

    public void setUserVehicles(List<UserVehiclesBean> userVehicles) {
        this.userVehicles = userVehicles;
    }

    public static class UserVehiclesBean {
        /**
         * vehicle_id : 4
         * make : Audi
         * model : A3 e-tron
         * year : 2018
         * combE : 41
         * range : 0
         * rangeA : 16
         */

        private int vehicle_id;
        private String make;
        private String model;
        private String year;
        private String combE;
        private String range;
        private String rangeA;

        public int getVehicle_id() {
            return vehicle_id;
        }

        public void setVehicle_id(int vehicle_id) {
            this.vehicle_id = vehicle_id;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getCombE() {
            return combE;
        }

        public void setCombE(String combE) {
            this.combE = combE;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public String getRangeA() {
            return rangeA;
        }

        public void setRangeA(String rangeA) {
            this.rangeA = rangeA;
        }
    }
}
