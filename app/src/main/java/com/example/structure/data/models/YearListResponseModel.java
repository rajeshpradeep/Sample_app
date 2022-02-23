package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 02-12-2019
 */
public class YearListResponseModel {

    /**
     * status : 1
     * success : Year list
     * year_list : ["2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998","1997","1996","1995","1994","1993","1992","1991","1990","1989","1988","1987","1986","1985","1984"]
     */

    private String status;
    private String success;
    private String error;
    private List<String> year_list;
    private List<CountryListBean> country_list;

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

    public List<String> getYear_list() {
        return year_list;
    }

    public void setYear_list(List<String> year_list) {
        this.year_list = year_list;
    }

    public List<CountryListBean> getCountry_list() {
        return country_list;
    }

    public void setCountry_list(List<CountryListBean> country_list) {
        this.country_list = country_list;
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
}
