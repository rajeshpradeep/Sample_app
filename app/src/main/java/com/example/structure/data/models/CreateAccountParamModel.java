package com.example.structure.data.models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by Rajesh Pradeep G on 03-12-2019
 */
@Entity(indices = {@Index(value = {"email"}, unique = true)})
public class CreateAccountParamModel {
    @PrimaryKey(autoGenerate = true)
    private int rowId;
    private String email;
    private String username;
    private String password;
    private int secret_question;
    private String secret_answer;
    private String first_name;
    private String last_name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String postal_code;
    private String alias;
    private String year;
    private String make;
    private String model;
    private String is_fully_charged;
    private String is_unexpectedly_interrupted;
    private String is_notify_to_plugin_mobile;
    private String notify_plugin_time;
    private String user_datetime;
    private String user_timezone;

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSecret_question() {
        return secret_question;
    }

    public void setSecret_question(int secret_question) {
        this.secret_question = secret_question;
    }

    public String getSecret_answer() {
        return secret_answer;
    }

    public void setSecret_answer(String secret_answer) {
        this.secret_answer = secret_answer;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getIs_fully_charged() {
        return is_fully_charged;
    }

    public void setIs_fully_charged(String is_fully_charged) {
        this.is_fully_charged = is_fully_charged;
    }

    public String getIs_unexpectedly_interrupted() {
        return is_unexpectedly_interrupted;
    }

    public void setIs_unexpectedly_interrupted(String is_unexpectedly_interrupted) {
        this.is_unexpectedly_interrupted = is_unexpectedly_interrupted;
    }

    public String getIs_notify_to_plugin_mobile() {
        return is_notify_to_plugin_mobile;
    }

    public void setIs_notify_to_plugin_mobile(String is_notify_to_plugin_mobile) {
        this.is_notify_to_plugin_mobile = is_notify_to_plugin_mobile;
    }

    public String getNotify_plugin_time() {
        return notify_plugin_time;
    }

    public void setNotify_plugin_time(String notify_plugin_time) {
        this.notify_plugin_time = notify_plugin_time;
    }

    public String getUser_datetime() {
        return user_datetime;
    }

    public void setUser_datetime(String user_datetime) {
        this.user_datetime = user_datetime;
    }

    public String getUser_timezone() {
        return user_timezone;
    }

    public void setUser_timezone(String user_timezone) {
        this.user_timezone = user_timezone;
    }

    @Override
    public String toString() {
        return "CreateAccountParamModel{" +
                "rowId=" + rowId +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", secret_question=" + secret_question +
                ", secret_answer='" + secret_answer + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", alias='" + alias + '\'' +
                ", year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", is_fully_charged='" + is_fully_charged + '\'' +
                ", is_unexpectedly_interrupted='" + is_unexpectedly_interrupted + '\'' +
                ", is_notify_to_plugin_mobile='" + is_notify_to_plugin_mobile + '\'' +
                ", notify_plugin_time='" + notify_plugin_time + '\'' +
                ", user_datetime='" + user_datetime + '\'' +
                ", user_timezone='" + user_timezone + '\'' +
                '}';
    }
}
