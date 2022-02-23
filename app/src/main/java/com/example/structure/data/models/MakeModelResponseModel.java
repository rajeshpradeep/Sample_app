package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 13-12-2019
 */
public class MakeModelResponseModel {


    /**
     * status : 1
     * success : Year list
     * make_list : ["Ford","Nissan","Toyota"]
     */

    private String status;
    private String success;
    private String error;
    private List<String> make_list;
    private List<String> model_list;

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

    public List<String> getMake_list() {
        return make_list;
    }

    public void setMake_list(List<String> make_list) {
        this.make_list = make_list;
    }

    public List<String> getModel_list() {
        return model_list;
    }

    public void setModel_list(List<String> model_list) {
        this.model_list = model_list;
    }
}
