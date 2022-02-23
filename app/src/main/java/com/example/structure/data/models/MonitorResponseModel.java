package com.example.structure.data.models;

/**
 * Created by Rajesh Pradeep G on 07-02-2020
 */
public class MonitorResponseModel {

    /**
     * status : 1
     * success : Monitor SCA
     * monitorSca : {"monitorActiveStatus":"Charging","stationName":"END1","scaId":"SCA-NEAR-END1","scaBattery":"18.3%","lastSync":"2020-05-20 14:06:01 +0000","mode":"Control","activeEnergy":"0.83","activePower":"3.64","lineVoltage":"200.00","chargeCurrent":"26.56","evseType":"Level 2","maxAmperage":"10.002","maxDrawStatus":1,"limitedbyEVSEStatus":0,"adapterStatus":0,"networkStatus":"Online","sessionStatus":"Charging"}
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private MonitorScaBean monitorSca;

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

    public MonitorScaBean getMonitorSca() {
        return monitorSca;
    }

    public void setMonitorSca(MonitorScaBean monitorSca) {
        this.monitorSca = monitorSca;
    }

    public static class MonitorScaBean {
        /**
         * monitorActiveStatus : Charging
         * stationName : END1
         * scaId : SCA-NEAR-END1
         * scaBattery : 18.3%
         * lastSync : 2020-05-20 14:06:01 +0000
         * mode : Control
         * activeEnergy : 0.83
         * activePower : 3.64
         * lineVoltage : 200.00
         * chargeCurrent : 26.56
         * evseType : Level 2
         * maxAmperage : 10.002
         * maxDrawStatus : 1
         * limitedbyEVSEStatus : 0
         * adapterStatus : 0
         * networkStatus : Online
         * sessionStatus : Charging
         */

        private String monitorActiveStatus;
        private String stationName;
        private String scaId;
        private String scaBattery;
        private String lastSync;
        private String mode;
        private String activeEnergy;
        private String activePower;
        private String lineVoltage;
        private String chargeCurrent;
        private String evseType;
        private String maxAmperage;
        private int maxDrawStatus;
        private int limitedbyEVSEStatus;
        private int adapterStatus;
        private String networkStatus;
        private String sessionStatus;

        public String getMonitorActiveStatus() {
            return monitorActiveStatus;
        }

        public void setMonitorActiveStatus(String monitorActiveStatus) {
            this.monitorActiveStatus = monitorActiveStatus;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public String getScaId() {
            return scaId;
        }

        public void setScaId(String scaId) {
            this.scaId = scaId;
        }

        public String getScaBattery() {
            return scaBattery;
        }

        public void setScaBattery(String scaBattery) {
            this.scaBattery = scaBattery;
        }

        public String getLastSync() {
            return lastSync;
        }

        public void setLastSync(String lastSync) {
            this.lastSync = lastSync;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getActiveEnergy() {
            return activeEnergy;
        }

        public void setActiveEnergy(String activeEnergy) {
            this.activeEnergy = activeEnergy;
        }

        public String getActivePower() {
            return activePower;
        }

        public void setActivePower(String activePower) {
            this.activePower = activePower;
        }

        public String getLineVoltage() {
            return lineVoltage;
        }

        public void setLineVoltage(String lineVoltage) {
            this.lineVoltage = lineVoltage;
        }

        public String getChargeCurrent() {
            return chargeCurrent;
        }

        public void setChargeCurrent(String chargeCurrent) {
            this.chargeCurrent = chargeCurrent;
        }

        public String getEvseType() {
            return evseType;
        }

        public void setEvseType(String evseType) {
            this.evseType = evseType;
        }

        public String getMaxAmperage() {
            return maxAmperage;
        }

        public void setMaxAmperage(String maxAmperage) {
            this.maxAmperage = maxAmperage;
        }

        public int getMaxDrawStatus() {
            return maxDrawStatus;
        }

        public void setMaxDrawStatus(int maxDrawStatus) {
            this.maxDrawStatus = maxDrawStatus;
        }

        public int getLimitedbyEVSEStatus() {
            return limitedbyEVSEStatus;
        }

        public void setLimitedbyEVSEStatus(int limitedbyEVSEStatus) {
            this.limitedbyEVSEStatus = limitedbyEVSEStatus;
        }

        public int getAdapterStatus() {
            return adapterStatus;
        }

        public void setAdapterStatus(int adapterStatus) {
            this.adapterStatus = adapterStatus;
        }

        public String getNetworkStatus() {
            return networkStatus;
        }

        public void setNetworkStatus(String networkStatus) {
            this.networkStatus = networkStatus;
        }

        public String getSessionStatus() {
            return sessionStatus;
        }

        public void setSessionStatus(String sessionStatus) {
            this.sessionStatus = sessionStatus;
        }
    }
}
