package com.example.structure.data.models;

/**
 * Created by Rajesh Pradeep G on 04-02-2020
 */
public class PersonalInfoResponseModel {

    /**
     * status : 1
     * success : Personal Info
     * personalInfo : {"email":"q5@test.com","username":"Q5","firstName":"Q5 User","lastName":"Test User","address1":"Chicago","address2":"Illinois","city":"Chicago","state":"Delaware","country":"United States of America","postalCode":"10002","isLeaderboardEnabled":"0","alias":"Testingq","leaderboardId":104}
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private PersonalInfoBean personalInfo;

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

    public PersonalInfoBean getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfoBean personalInfo) {
        this.personalInfo = personalInfo;
    }

    public static class PersonalInfoBean {
        /**
         * email : q5@test.com
         * username : Q5
         * firstName : Q5 User
         * lastName : Test User
         * address1 : Chicago
         * address2 : Illinois
         * city : Chicago
         * state : Delaware
         * country : United States of America
         * postalCode : 10002
         * isLeaderboardEnabled : 0
         * alias : Testingq
         * leaderboardId : 104
         */

        private String email;
        private String username;
        private String firstName;
        private String lastName;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private String country;
        private String postalCode;
        private String isLeaderboardEnabled;
        private String alias;
        private int leaderboardId;

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

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
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

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getIsLeaderboardEnabled() {
            return isLeaderboardEnabled;
        }

        public void setIsLeaderboardEnabled(String isLeaderboardEnabled) {
            this.isLeaderboardEnabled = isLeaderboardEnabled;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getLeaderboardId() {
            return leaderboardId;
        }

        public void setLeaderboardId(int leaderboardId) {
            this.leaderboardId = leaderboardId;
        }
    }
}
