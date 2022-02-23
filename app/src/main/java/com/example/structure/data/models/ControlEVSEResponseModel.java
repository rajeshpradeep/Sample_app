package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 10-03-2020
 */
public class ControlEVSEResponseModel {

    /**
     * status : 1
     * success : Control EVSE
     * control_evse : {"deviceId":"ChargionE8B7","ocpp":{"id":431,"name":"OCPP E8B7","address":"Pacific Crest Trail, Onyx, CA 93255, USA","description":"asd","organization":"Test","latitude":"35.603718941421256","longitude":"-118.12362708398439","iccid":"iccid]","imsi":"ims","ports":[{"id":1173,"name":"Porta","isAdapterAttached":1,"stationId":431,"connectorId":1,"createdAt":"","updatedAt":""},{"id":1174,"name":"Portb","isAdapterAttached":2,"stationId":431,"connectorId":2,"createdAt":"","updatedAt":""},{"id":1175,"name":"Portz","isAdapterAttached":1,"stationId":431,"connectorId":0,"createdAt":"","updatedAt":""},{"id":1176,"name":"Portn","isAdapterAttached":2,"stationId":431,"connectorId":0,"createdAt":"","updatedAt":""}],"chargeboxId":"ChargionE8B7","groupId":92,"stationModelsId":2,"ocppVersion":"1.2","macAddress":"mac","serialNumber":"2123","activationStatus":"Active","networkStatus":"Offline","stationStatus":"Active","isReservationsEnabled":"Enabled","softwareVersion":"Soft","activationDate":"2020-04-01","meterSerialNumber":"w23","meterType":"34","profileId":22,"socketId":0,"createdAt":"","updatedAt":"","stationModel":{"id":2,"manufacturer":"Delta Electronics","model":"EVMU3017MWS","evseType":"AC","maxAmperage":"30","modelDescription":"AC Mini (WiFi, RFID, 25-ft cable)","createdAt":"","updatedAt":""}},"currentPort":{"id":1173,"name":"Porta","isAdapterAttached":1,"stationId":431,"connectorId":1,"createdAt":"","updatedAt":""},"controlEvseData":{"chargeCurrent":"0","activePower":"0","lineVoltage":"0","activeEnergy":"0","evMileage":"0","startDate":"Thu, Apr 30th 2020","sessionStatus":"Available","pluginTime":"09:05:18 AM","chargeDuration":"00:01:25","unPluginTime":"09:07:13 AM","lastSync":"04/30/2020 09:04:48 AM","evseStatus":"Available","networkStatus":"Online","outoforder":"Offline","maxAmperage":"30","evseType":"AC","maxDraw":false,"limitedbyEvse":false,"chargeNearEnd":false,"evseMode":"Enabled","isEvseModeEnabled":"Enabled","timezoneDate":"04/30/2020","tzEvseLastSyncDate":"04/30/2020"},"activeSessionStatus":"stop"}
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private ControlEvseBean control_evse;

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

    public ControlEvseBean getControl_evse() {
        return control_evse;
    }

    public void setControl_evse(ControlEvseBean control_evse) {
        this.control_evse = control_evse;
    }

    public static class ControlEvseBean {
        /**
         * deviceId : ChargionE8B7
         * ocpp : {"id":431,"name":"OCPP E8B7","address":"Pacific Crest Trail, Onyx, CA 93255, USA","description":"asd","organization":"Test","latitude":"35.603718941421256","longitude":"-118.12362708398439","iccid":"iccid]","imsi":"ims","ports":[{"id":1173,"name":"Porta","isAdapterAttached":1,"stationId":431,"connectorId":1,"createdAt":"","updatedAt":""},{"id":1174,"name":"Portb","isAdapterAttached":2,"stationId":431,"connectorId":2,"createdAt":"","updatedAt":""},{"id":1175,"name":"Portz","isAdapterAttached":1,"stationId":431,"connectorId":0,"createdAt":"","updatedAt":""},{"id":1176,"name":"Portn","isAdapterAttached":2,"stationId":431,"connectorId":0,"createdAt":"","updatedAt":""}],"chargeboxId":"ChargionE8B7","groupId":92,"stationModelsId":2,"ocppVersion":"1.2","macAddress":"mac","serialNumber":"2123","activationStatus":"Active","networkStatus":"Offline","stationStatus":"Active","isReservationsEnabled":"Enabled","softwareVersion":"Soft","activationDate":"2020-04-01","meterSerialNumber":"w23","meterType":"34","profileId":22,"socketId":0,"createdAt":"","updatedAt":"","stationModel":{"id":2,"manufacturer":"Delta Electronics","model":"EVMU3017MWS","evseType":"AC","maxAmperage":"30","modelDescription":"AC Mini (WiFi, RFID, 25-ft cable)","createdAt":"","updatedAt":""}}
         * currentPort : {"id":1173,"name":"Porta","isAdapterAttached":1,"stationId":431,"connectorId":1,"createdAt":"","updatedAt":""}
         * controlEvseData : {"chargeCurrent":"0","activePower":"0","lineVoltage":"0","activeEnergy":"0","evMileage":"0","startDate":"Thu, Apr 30th 2020","sessionStatus":"Available","pluginTime":"09:05:18 AM","chargeDuration":"00:01:25","unPluginTime":"09:07:13 AM","lastSync":"04/30/2020 09:04:48 AM","evseStatus":"Available","networkStatus":"Online","outoforder":"Offline","maxAmperage":"30","evseType":"AC","maxDraw":false,"limitedbyEvse":false,"chargeNearEnd":false,"evseMode":"Enabled","isEvseModeEnabled":"Enabled","timezoneDate":"04/30/2020","tzEvseLastSyncDate":"04/30/2020"}
         * activeSessionStatus : stop
         */

        private String deviceId;
        private OcppBean ocpp;
        private CurrentPortBean currentPort;
        private ControlEvseDataBean controlEvseData;
        private String activeSessionStatus;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public OcppBean getOcpp() {
            return ocpp;
        }

        public void setOcpp(OcppBean ocpp) {
            this.ocpp = ocpp;
        }

        public CurrentPortBean getCurrentPort() {
            return currentPort;
        }

        public void setCurrentPort(CurrentPortBean currentPort) {
            this.currentPort = currentPort;
        }

        public ControlEvseDataBean getControlEvseData() {
            return controlEvseData;
        }

        public void setControlEvseData(ControlEvseDataBean controlEvseData) {
            this.controlEvseData = controlEvseData;
        }

        public String getActiveSessionStatus() {
            return activeSessionStatus;
        }

        public void setActiveSessionStatus(String activeSessionStatus) {
            this.activeSessionStatus = activeSessionStatus;
        }

        public static class OcppBean {
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
             * ports : [{"id":1173,"name":"Porta","isAdapterAttached":1,"stationId":431,"connectorId":1,"createdAt":"","updatedAt":""},{"id":1174,"name":"Portb","isAdapterAttached":2,"stationId":431,"connectorId":2,"createdAt":"","updatedAt":""},{"id":1175,"name":"Portz","isAdapterAttached":1,"stationId":431,"connectorId":0,"createdAt":"","updatedAt":""},{"id":1176,"name":"Portn","isAdapterAttached":2,"stationId":431,"connectorId":0,"createdAt":"","updatedAt":""}]
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
             * stationModel : {"id":2,"manufacturer":"Delta Electronics","model":"EVMU3017MWS","evseType":"AC","maxAmperage":"30","modelDescription":"AC Mini (WiFi, RFID, 25-ft cable)","createdAt":"","updatedAt":""}
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
            private StationModelBean stationModel;
            private List<PortsBean> ports;

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

            public StationModelBean getStationModel() {
                return stationModel;
            }

            public void setStationModel(StationModelBean stationModel) {
                this.stationModel = stationModel;
            }

            public List<PortsBean> getPorts() {
                return ports;
            }

            public void setPorts(List<PortsBean> ports) {
                this.ports = ports;
            }

            public static class StationModelBean {
                /**
                 * id : 2
                 * manufacturer : Delta Electronics
                 * model : EVMU3017MWS
                 * evseType : AC
                 * maxAmperage : 30
                 * modelDescription : AC Mini (WiFi, RFID, 25-ft cable)
                 * createdAt :
                 * updatedAt :
                 */

                private int id;
                private String manufacturer;
                private String model;
                private String evseType;
                private String maxAmperage;
                private String modelDescription;
                private String createdAt;
                private String updatedAt;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getManufacturer() {
                    return manufacturer;
                }

                public void setManufacturer(String manufacturer) {
                    this.manufacturer = manufacturer;
                }

                public String getModel() {
                    return model;
                }

                public void setModel(String model) {
                    this.model = model;
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

                public String getModelDescription() {
                    return modelDescription;
                }

                public void setModelDescription(String modelDescription) {
                    this.modelDescription = modelDescription;
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

        public static class CurrentPortBean {
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

        public static class ControlEvseDataBean {
            /**
             * chargeCurrent : 0
             * activePower : 0
             * lineVoltage : 0
             * activeEnergy : 0
             * evMileage : 0
             * startDate : Thu, Apr 30th 2020
             * sessionStatus : Available
             * pluginTime : 09:05:18 AM
             * chargeDuration : 00:01:25
             * unPluginTime : 09:07:13 AM
             * lastSync : 04/30/2020 09:04:48 AM
             * evseStatus : Available
             * networkStatus : Online
             * outoforder : Offline
             * maxAmperage : 30
             * evseType : AC
             * maxDraw : false
             * limitedbyEvse : false
             * chargeNearEnd : false
             * evseMode : Enabled
             * isEvseModeEnabled : Enabled
             * timezoneDate : 04/30/2020
             * tzEvseLastSyncDate : 04/30/2020
             */

            private String chargeCurrent;
            private String activePower;
            private String lineVoltage;
            private String activeEnergy;
            private String evMileage;
            private String startDate;
            private String sessionStatus;
            private String pluginTime;
            private String chargeDuration;
            private String unPluginTime;
            private String lastSync;
            private String evseStatus;
            private String networkStatus;
            private String outoforder;
            private String maxAmperage;
            private String evseType;
            private boolean maxDraw;
            private boolean limitedbyEvse;
            private boolean chargeNearEnd;
            private String evseMode;
            private String isEvseModeEnabled;
            private String timezoneDate;
            private String tzEvseLastSyncDate;

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

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getSessionStatus() {
                return sessionStatus;
            }

            public void setSessionStatus(String sessionStatus) {
                this.sessionStatus = sessionStatus;
            }

            public String getPluginTime() {
                return pluginTime;
            }

            public void setPluginTime(String pluginTime) {
                this.pluginTime = pluginTime;
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

            public String getLastSync() {
                return lastSync;
            }

            public void setLastSync(String lastSync) {
                this.lastSync = lastSync;
            }

            public String getEvseStatus() {
                return evseStatus;
            }

            public void setEvseStatus(String evseStatus) {
                this.evseStatus = evseStatus;
            }

            public String getNetworkStatus() {
                return networkStatus;
            }

            public void setNetworkStatus(String networkStatus) {
                this.networkStatus = networkStatus;
            }

            public String getOutoforder() {
                return outoforder;
            }

            public void setOutoforder(String outoforder) {
                this.outoforder = outoforder;
            }

            public String getMaxAmperage() {
                return maxAmperage;
            }

            public void setMaxAmperage(String maxAmperage) {
                this.maxAmperage = maxAmperage;
            }

            public String getEvseType() {
                return evseType;
            }

            public void setEvseType(String evseType) {
                this.evseType = evseType;
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

            public String getEvseMode() {
                return evseMode;
            }

            public void setEvseMode(String evseMode) {
                this.evseMode = evseMode;
            }

            public String getIsEvseModeEnabled() {
                return isEvseModeEnabled;
            }

            public void setIsEvseModeEnabled(String isEvseModeEnabled) {
                this.isEvseModeEnabled = isEvseModeEnabled;
            }

            public String getTimezoneDate() {
                return timezoneDate;
            }

            public void setTimezoneDate(String timezoneDate) {
                this.timezoneDate = timezoneDate;
            }

            public String getTzEvseLastSyncDate() {
                return tzEvseLastSyncDate;
            }

            public void setTzEvseLastSyncDate(String tzEvseLastSyncDate) {
                this.tzEvseLastSyncDate = tzEvseLastSyncDate;
            }
        }
    }
}
