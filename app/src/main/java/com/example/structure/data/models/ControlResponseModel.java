package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 21-11-2019
 */
public class ControlResponseModel {

    /**
     * status : 1
     * success : Control
     * deviceId : SCA-NEAR-END1
     * userVehicles : [{"id":46,"year":"2020","make":"BMW","model":"530e","range":"0","rangeA":"21","combE":0.47},{"id":39,"year":"2019","make":"BMW","model":"530e xDrive","range":"0","rangeA":"15","combE":0.49},{"id":53,"year":"2018","make":"BYD","model":"e6","range":"187","rangeA":"0","combE":0.466265}]
     * power : 1920.63
     * maxAmperage : 10.002
     * current : 9.60
     * maxPower : 2000.4
     * lineVoltage : 200
     * activeSession : true
     * accumulatedEvMileage : 1.70
     * evMileageStatus : {"vehicleId":"39","evMileageNeeded":"11","isMileageControlEnabled":"Disabled"}
     * lineCurrent : 26.56
     * maxEvRange : 15
     * timerRemaining : 00:00:00
     * timerControlStatus : {"duration":"3","isTimerEnabled":"Enabled","createdAt":"2020-06-05 08:34:25"}
     * chargeMode : Control
     * adapterAvailabe : true
     * activeEnergy : 832.01
     * noVehicleAdded : Please add a vehicle to your account to access this feature.
     * currentSliderMin : 6
     * powerSliderMin : 1200
     * minEvRange : 1
     * timerMinutesRemaining : 0
     * controlSCAData : {"chargeCurrent":"26.56","activePower":"3.64","lineVoltage":"200.00","activeEnergy":"0.83","evMileage":"2.50","scaBattery":"18.3%","lastSync":"05/20/2020 02:06:01 PM","pluginTime":"02:05:41 PM","sessionStatus":"Charging","scaStatus":"Charging","scaMode":"Control","networkStatus":"Online","evseType":"Level 2","maxAmperage":"10.002","startDate":"Wed, May 20th 2020","chargeDuration":"N/A","unPluginTime":"N/A","maxDraw":true,"limitedbyEvse":false,"chargeNearEnd":false,"timezoneDate":"05/20/2020","tzScaLastSyncDate":"05/20/2020"}
     * roundedLineVoltage : 200
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private String deviceId;
    private String power;
    private String maxAmperage;
    private String current;
    private String maxPower;
    private String lineVoltage;
    private boolean activeSession;
    private String accumulatedEvMileage;
    private EvMileageStatusBean evMileageStatus;
    private String lineCurrent;
    private String maxEvRange;
    private String timerRemaining;
    private TimerControlStatusBean timerControlStatus;
    private String chargeMode;
    private boolean adapterAvailabe;
    private String activeEnergy;
    private String noVehicleAdded;
    private int currentSliderMin;
    private double powerSliderMin;
    private int minEvRange;
    private String timerMinutesRemaining;
    private ControlSCADataBean controlSCAData;
    private int roundedLineVoltage;
    private List<UserVehiclesBean> userVehicles;

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getMaxAmperage() {
        return maxAmperage;
    }

    public void setMaxAmperage(String maxAmperage) {
        this.maxAmperage = maxAmperage;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(String maxPower) {
        this.maxPower = maxPower;
    }

    public String getLineVoltage() {
        return lineVoltage;
    }

    public void setLineVoltage(String lineVoltage) {
        this.lineVoltage = lineVoltage;
    }

    public boolean isActiveSession() {
        return activeSession;
    }

    public void setActiveSession(boolean activeSession) {
        this.activeSession = activeSession;
    }

    public String getAccumulatedEvMileage() {
        return accumulatedEvMileage;
    }

    public void setAccumulatedEvMileage(String accumulatedEvMileage) {
        this.accumulatedEvMileage = accumulatedEvMileage;
    }

    public EvMileageStatusBean getEvMileageStatus() {
        return evMileageStatus;
    }

    public void setEvMileageStatus(EvMileageStatusBean evMileageStatus) {
        this.evMileageStatus = evMileageStatus;
    }

    public String getLineCurrent() {
        return lineCurrent;
    }

    public void setLineCurrent(String lineCurrent) {
        this.lineCurrent = lineCurrent;
    }

    public String getMaxEvRange() {
        return maxEvRange;
    }

    public void setMaxEvRange(String maxEvRange) {
        this.maxEvRange = maxEvRange;
    }

    public String getTimerRemaining() {
        return timerRemaining;
    }

    public void setTimerRemaining(String timerRemaining) {
        this.timerRemaining = timerRemaining;
    }

    public TimerControlStatusBean getTimerControlStatus() {
        return timerControlStatus;
    }

    public void setTimerControlStatus(TimerControlStatusBean timerControlStatus) {
        this.timerControlStatus = timerControlStatus;
    }

    public String getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(String chargeMode) {
        this.chargeMode = chargeMode;
    }

    public boolean isAdapterAvailabe() {
        return adapterAvailabe;
    }

    public void setAdapterAvailabe(boolean adapterAvailabe) {
        this.adapterAvailabe = adapterAvailabe;
    }

    public String getActiveEnergy() {
        return activeEnergy;
    }

    public void setActiveEnergy(String activeEnergy) {
        this.activeEnergy = activeEnergy;
    }

    public String getNoVehicleAdded() {
        return noVehicleAdded;
    }

    public void setNoVehicleAdded(String noVehicleAdded) {
        this.noVehicleAdded = noVehicleAdded;
    }

    public int getCurrentSliderMin() {
        return currentSliderMin;
    }

    public void setCurrentSliderMin(int currentSliderMin) {
        this.currentSliderMin = currentSliderMin;
    }

    public double getPowerSliderMin() {
        return powerSliderMin;
    }

    public void setPowerSliderMin(double powerSliderMin) {
        this.powerSliderMin = powerSliderMin;
    }

    public int getMinEvRange() {
        return minEvRange;
    }

    public void setMinEvRange(int minEvRange) {
        this.minEvRange = minEvRange;
    }

    public String getTimerMinutesRemaining() {
        return timerMinutesRemaining;
    }

    public void setTimerMinutesRemaining(String timerMinutesRemaining) {
        this.timerMinutesRemaining = timerMinutesRemaining;
    }

    public ControlSCADataBean getControlSCAData() {
        return controlSCAData;
    }

    public void setControlSCAData(ControlSCADataBean controlSCAData) {
        this.controlSCAData = controlSCAData;
    }

    public int getRoundedLineVoltage() {
        return roundedLineVoltage;
    }

    public void setRoundedLineVoltage(int roundedLineVoltage) {
        this.roundedLineVoltage = roundedLineVoltage;
    }

    public List<UserVehiclesBean> getUserVehicles() {
        return userVehicles;
    }

    public void setUserVehicles(List<UserVehiclesBean> userVehicles) {
        this.userVehicles = userVehicles;
    }

    public static class EvMileageStatusBean {
        /**
         * vehicleId : 39
         * evMileageNeeded : 11
         * isMileageControlEnabled : Disabled
         */

        private String vehicleId;
        private String evMileageNeeded;
        private String isMileageControlEnabled;

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getEvMileageNeeded() {
            return evMileageNeeded;
        }

        public void setEvMileageNeeded(String evMileageNeeded) {
            this.evMileageNeeded = evMileageNeeded;
        }

        public String getIsMileageControlEnabled() {
            return isMileageControlEnabled;
        }

        public void setIsMileageControlEnabled(String isMileageControlEnabled) {
            this.isMileageControlEnabled = isMileageControlEnabled;
        }
    }

    public static class TimerControlStatusBean {
        /**
         * duration : 3
         * isTimerEnabled : Enabled
         * createdAt : 2020-06-05 08:34:25
         */

        private String duration;
        private String isTimerEnabled;
        private String createdAt;

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getIsTimerEnabled() {
            return isTimerEnabled;
        }

        public void setIsTimerEnabled(String isTimerEnabled) {
            this.isTimerEnabled = isTimerEnabled;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class ControlSCADataBean {
        /**
         * chargeCurrent : 26.56
         * activePower : 3.64
         * lineVoltage : 200.00
         * activeEnergy : 0.83
         * evMileage : 2.50
         * scaBattery : 18.3%
         * lastSync : 05/20/2020 02:06:01 PM
         * pluginTime : 02:05:41 PM
         * sessionStatus : Charging
         * scaStatus : Charging
         * scaMode : Control
         * networkStatus : Online
         * evseType : Level 2
         * maxAmperage : 10.002
         * startDate : Wed, May 20th 2020
         * chargeDuration : N/A
         * unPluginTime : N/A
         * maxDraw : true
         * limitedbyEvse : false
         * chargeNearEnd : false
         * timezoneDate : 05/20/2020
         * tzScaLastSyncDate : 05/20/2020
         */

        private String chargeCurrent;
        private String activePower;
        private String lineVoltage;
        private String activeEnergy;
        private String evMileage;
        private String scaBattery;
        private String lastSync;
        private String pluginTime;
        private String sessionStatus;
        private String scaStatus;
        private String scaMode;
        private String networkStatus;
        private String evseType;
        private String maxAmperage;
        private String startDate;
        private String chargeDuration;
        private String unPluginTime;
        private boolean maxDraw;
        private boolean limitedbyEvse;
        private boolean chargeNearEnd;
        private String timezoneDate;
        private String tzScaLastSyncDate;

        public String getChargeCurrent() {
            return chargeCurrent;
        }

        public void setChargeCurrent(String chargeCurrent) {
            this.chargeCurrent = chargeCurrent;
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

        public String getActiveEnergy() {
            return activeEnergy;
        }

        public void setActiveEnergy(String activeEnergy) {
            this.activeEnergy = activeEnergy;
        }

        public String getEvMileage() {
            return evMileage;
        }

        public void setEvMileage(String evMileage) {
            this.evMileage = evMileage;
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

        public String getPluginTime() {
            return pluginTime;
        }

        public void setPluginTime(String pluginTime) {
            this.pluginTime = pluginTime;
        }

        public String getSessionStatus() {
            return sessionStatus;
        }

        public void setSessionStatus(String sessionStatus) {
            this.sessionStatus = sessionStatus;
        }

        public String getScaStatus() {
            return scaStatus;
        }

        public void setScaStatus(String scaStatus) {
            this.scaStatus = scaStatus;
        }

        public String getScaMode() {
            return scaMode;
        }

        public void setScaMode(String scaMode) {
            this.scaMode = scaMode;
        }

        public String getNetworkStatus() {
            return networkStatus;
        }

        public void setNetworkStatus(String networkStatus) {
            this.networkStatus = networkStatus;
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

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getChargeDuration() {
            return chargeDuration;
        }

        public void setChargeDuration(String chargeDuration) {
            this.chargeDuration = chargeDuration;
        }

        public String getUnPluginTime() {
            return unPluginTime;
        }

        public void setUnPluginTime(String unPluginTime) {
            this.unPluginTime = unPluginTime;
        }

        public boolean isMaxDraw() {
            return maxDraw;
        }

        public void setMaxDraw(boolean maxDraw) {
            this.maxDraw = maxDraw;
        }

        public boolean isLimitedbyEvse() {
            return limitedbyEvse;
        }

        public void setLimitedbyEvse(boolean limitedbyEvse) {
            this.limitedbyEvse = limitedbyEvse;
        }

        public boolean isChargeNearEnd() {
            return chargeNearEnd;
        }

        public void setChargeNearEnd(boolean chargeNearEnd) {
            this.chargeNearEnd = chargeNearEnd;
        }

        public String getTimezoneDate() {
            return timezoneDate;
        }

        public void setTimezoneDate(String timezoneDate) {
            this.timezoneDate = timezoneDate;
        }

        public String getTzScaLastSyncDate() {
            return tzScaLastSyncDate;
        }

        public void setTzScaLastSyncDate(String tzScaLastSyncDate) {
            this.tzScaLastSyncDate = tzScaLastSyncDate;
        }
    }

    public static class UserVehiclesBean {
        /**
         * id : 46
         * year : 2020
         * make : BMW
         * model : 530e
         * range : 0
         * rangeA : 21
         * combE : 0.47
         */

        private int id;
        private String year;
        private String make;
        private String model;
        private String range;
        private String rangeA;
        private double combE;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public double getCombE() {
            return combE;
        }

        public void setCombE(double combE) {
            this.combE = combE;
        }
    }
}
