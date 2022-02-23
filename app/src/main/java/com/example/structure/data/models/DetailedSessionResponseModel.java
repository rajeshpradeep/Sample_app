package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 16-03-2020
 */
public class DetailedSessionResponseModel {

    /**
     * status : 1
     * success : Detailed Session Info
     * detailedSession : [{"time":"2020-05-04 08:45:00 +0000","energy":"0.20","avgPower":"5.63"}]
     * totalEnergy : 0.20
     * peakPower : 6.12
     * avgPower : 5.63
     * sessionDate : 2020-05-04 08:44:56 +0000
     * sessionStartTime : 08:44:56 AM
     * sessionStopTime : 08:46:22 AM
     * sessionDuration : 00:01:26
     * chargeStartTime : 08:45:10 AM
     * chargeStopTime : 08:46:22 AM
     * chargeDuration : 00:01:12
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private String totalEnergy;
    private String peakPower;
    private String avgPower;
    private String sessionDate;
    private String sessionStartTime;
    private String sessionStopTime;
    private String sessionDuration;
    private String chargeStartTime;
    private String chargeStopTime;
    private String chargeDuration;
    private List<DetailedSessionBean> detailedSession;

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

    public String getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(String totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public String getPeakPower() {
        return peakPower;
    }

    public void setPeakPower(String peakPower) {
        this.peakPower = peakPower;
    }

    public String getAvgPower() {
        return avgPower;
    }

    public void setAvgPower(String avgPower) {
        this.avgPower = avgPower;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(String sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public String getSessionStopTime() {
        return sessionStopTime;
    }

    public void setSessionStopTime(String sessionStopTime) {
        this.sessionStopTime = sessionStopTime;
    }

    public String getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(String sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public String getChargeStartTime() {
        return chargeStartTime;
    }

    public void setChargeStartTime(String chargeStartTime) {
        this.chargeStartTime = chargeStartTime;
    }

    public String getChargeStopTime() {
        return chargeStopTime;
    }

    public void setChargeStopTime(String chargeStopTime) {
        this.chargeStopTime = chargeStopTime;
    }

    public String getChargeDuration() {
        return chargeDuration;
    }

    public void setChargeDuration(String chargeDuration) {
        this.chargeDuration = chargeDuration;
    }

    public List<DetailedSessionBean> getDetailedSession() {
        return detailedSession;
    }

    public void setDetailedSession(List<DetailedSessionBean> detailedSession) {
        this.detailedSession = detailedSession;
    }

    public static class DetailedSessionBean {
        /**
         * time : 2020-05-04 08:45:00 +0000
         * energy : 0.20
         * avgPower : 5.63
         */

        private String time;
        private String energy;
        private String avgPower;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getEnergy() {
            return energy;
        }

        public void setEnergy(String energy) {
            this.energy = energy;
        }

        public String getAvgPower() {
            return avgPower;
        }

        public void setAvgPower(String avgPower) {
            this.avgPower = avgPower;
        }
    }
}
