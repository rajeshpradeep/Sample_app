package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 09-04-2020
 */
public class DashboardEvseResponseModel {

    /**
     * status : 1
     * success : Dashboard
     * station : {"id":431,"name":"OCPP E8B7","address":"Pacific Crest Trail, Onyx, CA 93255, USA","description":"asd","organization":"Test","latitude":"35.603718941421256","longitude":"-118.12362708398439","iccid":"iccid]","imsi":"ims","chargeboxId":"ChargionE8B7","groupId":92,"stationModelsId":2,"ocppVersion":"1.2","macAddress":"mac","serialNumber":"2123","activationStatus":"Active","networkStatus":"Offline","stationStatus":"Active","isReservationsEnabled":"Enabled","softwareVersion":"Soft","activationDate":"2020-04-01","meterSerialNumber":"w23","meterType":"34","profileId":22,"socketId":0,"createdAt":"","updatedAt":""}
     * deviceId : ChargionE8B7
     * isUserActivated : 1
     * data : {"chargeSession":8,"ghgSavings":"4.96","energyDispensed":"9.32","gasolineSavings":"1.28","monetarySavings":"$3.55","status":"Available","power":"0 kW","energy":"0 kWh","connectedTimeToday":"0","sessionToday":0,"chargingTimeToday":"0","idleTimeToday":"0","connectedTimeMonth":"0","sessionMonth":0,"chargingTimeMonth":"0","idleTimeMonth":"0","progresToday":0,"progresMonth":0}
     * ports : [{"id":1173,"name":"Porta","isAdapterAttached":1,"stationId":431,"connectorId":1,"createdAt":"","updatedAt":""},{"id":1174,"name":"Portb","isAdapterAttached":2,"stationId":431,"connectorId":2,"createdAt":"","updatedAt":""},{"id":1175,"name":"Portz","isAdapterAttached":1,"stationId":431,"connectorId":0,"createdAt":"","updatedAt":""}]
     * currentConnectorId : 1
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private StationBean station;
    private String deviceId;
    private int isUserActivated;
    private DataBean data;
    private int currentConnectorId;
    private List<PortsBean> ports;

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

    public int getCurrentConnectorId() {
        return currentConnectorId;
    }

    public void setCurrentConnectorId(int currentConnectorId) {
        this.currentConnectorId = currentConnectorId;
    }

    public List<PortsBean> getPorts() {
        return ports;
    }

    public void setPorts(List<PortsBean> ports) {
        this.ports = ports;
    }

    public static class StationBean {
        /**
         * id : 431
         * name : OCPP E8B7
         * address : Pacific Crest Trail, Onyx, CA 93255, USA
         * description : asd
         * organization : Test
         * latitude : 35.603718941421256
         * longitude : -118.12362708398439
         * iccid : iccid]
         * imsi : ims
         * chargeboxId : ChargionE8B7
         * groupId : 92
         * stationModelsId : 2
         * ocppVersion : 1.2
         * macAddress : mac
         * serialNumber : 2123
         * activationStatus : Active
         * networkStatus : Offline
         * stationStatus : Active
         * isReservationsEnabled : Enabled
         * softwareVersion : Soft
         * activationDate : 2020-04-01
         * meterSerialNumber : w23
         * meterType : 34
         * profileId : 22
         * socketId : 0
         * createdAt :
         * updatedAt :
         */

        private int id;
        private String name;
        private String address;
        private String description;
        private String organization;
        private String latitude;
        private String longitude;
        private String iccid;
        private String imsi;
        private String chargeboxId;
        private int groupId;
        private int stationModelsId;
        private String ocppVersion;
        private String macAddress;
        private String serialNumber;
        private String activationStatus;
        private String networkStatus;
        private String stationStatus;
        private String isReservationsEnabled;
        private String softwareVersion;
        private String activationDate;
        private String meterSerialNumber;
        private String meterType;
        private int profileId;
        private String socketId;
        private String createdAt;
        private String updatedAt;

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

        public String getIccid() {
            return iccid;
        }

        public void setIccid(String iccid) {
            this.iccid = iccid;
        }

        public String getImsi() {
            return imsi;
        }

        public void setImsi(String imsi) {
            this.imsi = imsi;
        }

        public String getChargeboxId() {
            return chargeboxId;
        }

        public void setChargeboxId(String chargeboxId) {
            this.chargeboxId = chargeboxId;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getStationModelsId() {
            return stationModelsId;
        }

        public void setStationModelsId(int stationModelsId) {
            this.stationModelsId = stationModelsId;
        }

        public String getOcppVersion() {
            return ocppVersion;
        }

        public void setOcppVersion(String ocppVersion) {
            this.ocppVersion = ocppVersion;
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

        public String getStationStatus() {
            return stationStatus;
        }

        public void setStationStatus(String stationStatus) {
            this.stationStatus = stationStatus;
        }

        public String getIsReservationsEnabled() {
            return isReservationsEnabled;
        }

        public void setIsReservationsEnabled(String isReservationsEnabled) {
            this.isReservationsEnabled = isReservationsEnabled;
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

        public String getSocketId() {
            return socketId;
        }

        public void setSocketId(String socketId) {
            this.socketId = socketId;
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
         * chargeSession : 8
         * ghgSavings : 4.96
         * energyDispensed : 9.32
         * gasolineSavings : 1.28
         * monetarySavings : $3.55
         * status : Available
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

    public static class PortsBean {
        /**
         * id : 1173
         * name : Porta
         * isAdapterAttached : 1
         * stationId : 431
         * connectorId : 1
         * createdAt :
         * updatedAt :
         */

        private int id;
        private String name;
        private int isAdapterAttached;
        private int stationId;
        private int connectorId;
        private String createdAt;
        private String updatedAt;

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

        public int getIsAdapterAttached() {
            return isAdapterAttached;
        }

        public void setIsAdapterAttached(int isAdapterAttached) {
            this.isAdapterAttached = isAdapterAttached;
        }

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public int getConnectorId() {
            return connectorId;
        }

        public void setConnectorId(int connectorId) {
            this.connectorId = connectorId;
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
}
