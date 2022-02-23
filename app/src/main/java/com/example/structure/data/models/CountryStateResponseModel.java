package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 03-12-2019
 */
public class CountryStateResponseModel {

    /**
     * status : 1
     * success : Country list
     * country_list : [{"id":1,"name":"United States of America"}]
     */

    private String status;
    private String success;
    private String error;
    private List<CountryListBean> country_list;
    private List<CountryListBean> states_list;

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

    public List<CountryListBean> getCountry_list() {
        return country_list;
    }

    public void setCountry_list(List<CountryListBean> country_list) {
        this.country_list = country_list;
    }

    public List<CountryListBean> getStates_list() {
        return states_list;
    }

    public void setStates_list(List<CountryListBean> states_list) {
        this.states_list = states_list;
    }

    public static class CountryListBean {
        /**
         * id : 1
         * name : United States of America
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return "PickerResponseModel{" +
                "status='" + status + '\'' +
                ", success='" + success + '\'' +
                ", error='" + error + '\'' +
                ", country_list=" + country_list +
                '}';
    }
}
