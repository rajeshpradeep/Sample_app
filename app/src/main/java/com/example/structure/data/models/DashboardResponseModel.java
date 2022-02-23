package com.example.structure.data.models;

/**
 * Created by Rajesh Pradeep G on 11-11-2019
 */
public class DashboardResponseModel {

    /**
     * status : 1
     * success : Dashboard
     * station : {"id":263,"address":"","description":"test","organization":"","latitude":"","longitude":"","scaName":"SCA-A4BD-2940 Name","scaId":"SCA-A4BD-2940","registrationKey":"sssss","isLocked":0,"groupId":39,"portId":0,"macAddress":"","serialNumber":"","activationStatus":"Inactive","networkStatus":"Offline","softwareVersion":"","activationDate":"","meterSerialNumber":"","meterType":"","profileId":5,"createdAt":"2020-03-12 10:51:50","updatedAt":"2020-03-12 10:51:50"}
     * deviceId : SCA-A4BD-2940
     * isUserActivated : 1
     * data : {"chargeSession":9,"ghgSavings":"0.51","energyDispensed":"0.96","gasolineSavings":"0.13","monetarySavings":"$0.37","scaSOC":"42.2%","status":"Charging","power":"0 kW","energy":"0 kWh","connectedTimeToday":"0","sessionToday":"0","chargingTimeToday":"0","idleTimeToday":"0","connectedTimeMonth":"0","sessionMonth":"0","chargingTimeMonth":"0","idleTimeMonth":"0","progresToday":"0","progresMonth":"0"}
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private StationBean station;
    private String deviceId;
    private int isUserActivated;
    private DataBean data;

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

    public StationBean getStation() {
        return station;
    }

    public void setStation(StationBean station) {
        this.station = station;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getIsUserActivated() {
        return isUserActivated;
    }

    public void setIsUserActivated(int isUserActivated) {
        this.isUserActivated = isUserActivated;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class StationBean {
        /**
         * id : 263
         * address :
         * description : test
         * organization :
         * latitude :
         * longitude :
         * scaName : SCA-A4BD-2940 Name
         * scaId : SCA-A4BD-2940
         * registrationKey : sssss
         * isLocked : 0
         * groupId : 39
         * portId : 0
         * macAddress :
         * serialNumber :
         * activationStatus : Inactive
         * networkStatus : Offline
         * softwareVersion :
         * activationDate :
         * meterSerialNumber :
         * meterType :
         * profileId : 5
         * createdAt : 2020-03-12 10:51:50
         * updatedAt : 2020-03-12 10:51:50
         */

        private int id;
        private String address;
        private String description;
        private String organization;
        private String latitude;
        private String longitude;
        private String scaName;
        private String scaId;
        private String registrationKey;
        private int isLocked;
        private int groupId;
        private int portId;
        private String macAddress;
        private String serialNumber;
        private String activationStatus;
        private String networkStatus;
        private String softwareVersion;
        private String activationDate;
        private String meterSerialNumber;
        private String meterType;
        private int profileId;
        private String createdAt;
        private String updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getScaName() {
            return scaName;
        }

        public void setScaName(String scaName) {
            this.scaName = scaName;
        }

        public String getScaId() {
            return scaId;
        }

        public void setScaId(String scaId) {
            this.scaId = scaId;
        }

        public String getRegistrationKey() {
            return registrationKey;
        }

        public void setRegistrationKey(String registrationKey) {
            this.registrationKey = registrationKey;
        }

        public int getIsLocked() {
            return isLocked;
        }

        public void setIsLocked(int isLocked) {
            this.isLocked = isLocked;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getPortId() {
            return portId;
        }

        public void setPortId(int portId) {
            this.portId = portId;
        }

        public String getMacAddress() {
            return macAddress;
        }

        public void setMacAddress(String macAddress) {
            this.macAddress = macAddress;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public String getActivationStatus() {
            return activationStatus;
        }

        public void setActivationStatus(String activationStatus) {
            this.activationStatus = activationStatus;
        }

        public String getNetworkStatus() {
            return networkStatus;
        }

        public void setNetworkStatus(String networkStatus) {
            this.networkStatus = networkStatus;
        }

        public String getSoftwareVersion() {
            return softwareVersion;
        }

        public void setSoftwareVersion(String softwareVersion) {
            this.softwareVersion = softwareVersion;
        }

        public String getActivationDate() {
            return activationDate;
        }

        public void setActivationDate(String activationDate) {
            this.activationDate = activationDate;
        }

        public String getMeterSerialNumber() {
            return meterSerialNumber;
        }

        public void setMeterSerialNumber(String meterSerialNumber) {
            this.meterSerialNumber = meterSerialNumber;
        }

        public String getMeterType() {
            return meterType;
        }

        public void setMeterType(String meterType) {
            this.meterType = meterType;
        }

        public int getProfileId() {
            return profileId;
        }

        public void setProfileId(int profileId) {
            this.profileId = profileId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    public static class DataBean {
        /**
         * chargeSession : 9
         * ghgSavings : 0.51
         * energyDispensed : 0.96
         * gasolineSavings : 0.13
         * monetarySavings : $0.37
         * scaSOC : 42.2%
         * status : Charging
         * power : 0 kW
         * energy : 0 kWh
         * connectedTimeToday : 0
         * sessionToday : 0
         * chargingTimeToday : 0
         * idleTimeToday : 0
         * connectedTimeMonth : 0
         * sessionMonth : 0
         * chargingTimeMonth : 0
         * idleTimeMonth : 0
         * progresToday : 0
         * progresMonth : 0
         */

        private String chargeSession;
        private String ghgSavings;
        private String energyDispensed;
        private String gasolineSavings;
        private String monetarySavings;
        private String scaSOC;
        private String status;
        private String power;
        private String energy;
        private String connectedTimeToday;
        private String sessionToday;
        private String chargingTimeToday;
        private String idleTimeToday;
        private String connectedTimeMonth;
        private String sessionMonth;
        private String chargingTimeMonth;
        private String idleTimeMonth;
        private String progresToday;
        private String progresMonth;

        public String getChargeSession() {
            return chargeSession;
        }

        public void setChargeSession(String chargeSession) {
            this.chargeSession = chargeSession;
        }

        public String getGhgSavings() {
            return ghgSavings;
        }

        public void setGhgSavings(String ghgSavings) {
            this.ghgSavings = ghgSavings;
        }

        public String getEnergyDispensed() {
            return energyDispensed;
        }

        public void setEnergyDispensed(String energyDispensed) {
            this.energyDispensed = energyDispensed;
        }

        public String getGasolineSavings() {
            return gasolineSavings;
        }

        public void setGasolineSavings(String gasolineSavings) {
            this.gasolineSavings = gasolineSavings;
        }

        public String getMonetarySavings() {
            return monetarySavings;
        }

        public void setMonetarySavings(String monetarySavings) {
            this.monetarySavings = monetarySavings;
        }

        public String getScaSOC() {
            return scaSOC;
        }

        public void setScaSOC(String scaSOC) {
            this.scaSOC = scaSOC;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getEnergy() {
            return energy;
        }

        public void setEnergy(String energy) {
            this.energy = energy;
        }

        public String getConnectedTimeToday() {
            return connectedTimeToday;
        }

        public void setConnectedTimeToday(String connectedTimeToday) {
            this.connectedTimeToday = connectedTimeToday;
        }

        public String getSessionToday() {
            return sessionToday;
        }

        public void setSessionToday(String sessionToday) {
            this.sessionToday = sessionToday;
        }

        public String getChargingTimeToday() {
            return chargingTimeToday;
        }

        public void setChargingTimeToday(String chargingTimeToday) {
            this.chargingTimeToday = chargingTimeToday;
        }

        public String getIdleTimeToday() {
            return idleTimeToday;
        }

        public void setIdleTimeToday(String idleTimeToday) {
            this.idleTimeToday = idleTimeToday;
        }

        public String getConnectedTimeMonth() {
            return connectedTimeMonth;
        }

        public void setConnectedTimeMonth(String connectedTimeMonth) {
            this.connectedTimeMonth = connectedTimeMonth;
        }

        public String getSessionMonth() {
            return sessionMonth;
        }

        public void setSessionMonth(String sessionMonth) {
            this.sessionMonth = sessionMonth;
        }

        public String getChargingTimeMonth() {
            return chargingTimeMonth;
        }

        public void setChargingTimeMonth(String chargingTimeMonth) {
            this.chargingTimeMonth = chargingTimeMonth;
        }

        public String getIdleTimeMonth() {
            return idleTimeMonth;
        }

        public void setIdleTimeMonth(String idleTimeMonth) {
            this.idleTimeMonth = idleTimeMonth;
        }

        public String getProgresToday() {
            return progresToday;
        }

        public void setProgresToday(String progresToday) {
            this.progresToday = progresToday;
        }

        public String getProgresMonth() {
            return progresMonth;
        }

        public void setProgresMonth(String progresMonth) {
            this.progresMonth = progresMonth;
        }
    }
}
