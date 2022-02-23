package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 27-02-2020
 */
public class SessionInfoResponseModel {

    /**
     * status : 1
     * success : Session Info
     * sessionInfo : [{"portName":"Porta","filterSessionDate":"04/30/2020 09:05:18","sessionDate":"04-30-2020 09:05:18 +0000","transactionId":"1234534","deviceName":"OCPP E8B7","groupName":"g1ocpp","stationType":"AC","totalEnergy":"1.38","peakPower":"6.00","avgPower":"5.60","idTag":"RFID1","sessionStartTime":"09:05:18","sessionEndTime":"09:07:13","sessionDuration":"00:01:55","chargeStartTime":"09:05:28","chargeEndTime":"09:07:03","chargeDuration":"00:01:25","endReason":"Local","ghgSavingsKg":0.73349898,"ghgSavingsGal":0.18818232000000001,"transactionType":"session","startSoc":"","endSoc":""}]
     * totalRecordsCount : 7
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private int totalRecordsCount;
    private List<SessionInfoBean> sessionInfo;

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

    public int getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(int totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public List<SessionInfoBean> getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(List<SessionInfoBean> sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public static class SessionInfoBean {
        /**
         * portName : Porta
         * filterSessionDate : 04/30/2020 09:05:18
         * sessionDate : 04-30-2020 09:05:18 +0000
         * transactionId : 1234534
         * deviceName : OCPP E8B7
         * groupName : g1ocpp
         * stationType : AC
         * totalEnergy : 1.38
         * peakPower : 6.00
         * avgPower : 5.60
         * idTag : RFID1
         * sessionStartTime : 09:05:18
         * sessionEndTime : 09:07:13
         * sessionDuration : 00:01:55
         * chargeStartTime : 09:05:28
         * chargeEndTime : 09:07:03
         * chargeDuration : 00:01:25
         * endReason : Local
         * ghgSavingsKg : 0.73349898
         * ghgSavingsGal : 0.18818232000000001
         * transactionType : session
         * startSoc :
         * endSoc :
         */

        private String portName;
        private String filterSessionDate;
        private String sessionDate;
        private String transactionId;
        private String deviceName;
        private String groupName;
        private String stationType;
        private String totalEnergy;
        private String peakPower;
        private String avgPower;
        private String idTag;
        private String sessionStartTime;
        private String sessionEndTime;
        private String sessionDuration;
        private String chargeStartTime;
        private String chargeEndTime;
        private String chargeDuration;
        private String endReason;
        private double ghgSavingsKg;
        private double ghgSavingsGal;
        private String transactionType;
        private String startSoc;
        private String endSoc;

        public String getPortName() {
            return portName;
        }

        public void setPortName(String portName) {
            this.portName = portName;
        }

        public String getFilterSessionDate() {
            return filterSessionDate;
        }

        public void setFilterSessionDate(String filterSessionDate) {
            this.filterSessionDate = filterSessionDate;
        }

        public String getSessionDate() {
            return sessionDate;
        }

        public void setSessionDate(String sessionDate) {
            this.sessionDate = sessionDate;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getStationType() {
            return stationType;
        }

        public void setStationType(String stationType) {
            this.stationType = stationType;
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

        public String getIdTag() {
            return idTag;
        }

        public void setIdTag(String idTag) {
            this.idTag = idTag;
        }

        public String getSessionStartTime() {
            return sessionStartTime;
        }

        public void setSessionStartTime(String sessionStartTime) {
            this.sessionStartTime = sessionStartTime;
        }

        public String getSessionEndTime() {
            return sessionEndTime;
        }

        public void setSessionEndTime(String sessionEndTime) {
            this.sessionEndTime = sessionEndTime;
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

        public String getChargeEndTime() {
            return chargeEndTime;
        }

        public void setChargeEndTime(String chargeEndTime) {
            this.chargeEndTime = chargeEndTime;
        }

        public String getChargeDuration() {
            return chargeDuration;
        }

        public void setChargeDuration(String chargeDuration) {
            this.chargeDuration = chargeDuration;
        }

        public String getEndReason() {
            return endReason;
        }

        public void setEndReason(String endReason) {
            this.endReason = endReason;
        }

        public double getGhgSavingsKg() {
            return ghgSavingsKg;
        }

        public void setGhgSavingsKg(double ghgSavingsKg) {
            this.ghgSavingsKg = ghgSavingsKg;
        }

        public double getGhgSavingsGal() {
            return ghgSavingsGal;
        }

        public void setGhgSavingsGal(double ghgSavingsGal) {
            this.ghgSavingsGal = ghgSavingsGal;
        }

        public String getTransactionType() {
            return transactionType;
        }

        public void setTransactionType(String transactionType) {
            this.transactionType = transactionType;
        }

        public String getStartSoc() {
            return startSoc;
        }

        public void setStartSoc(String startSoc) {
            this.startSoc = startSoc;
        }

        public String getEndSoc() {
            return endSoc;
        }

        public void setEndSoc(String endSoc) {
            this.endSoc = endSoc;
        }
    }
}
