package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 19-03-2020
 */
public class MonitorEVSEResponseModel {

    /**
     * status : 1
     * success : Monitor OCPP
     * monitorOcpp : {"stationId":431,"stationName":"OCPP E8B7","chargeboxId":"ChargionE8B7","latitude":"35.603718941421256","longitude":"-118.12362708398439","stationStatus":"Active","portId":1173,"portName":"Porta","maxAmperage":"30","evseType":"AC","isEvseModeEnabled":"Enabled","connectorId":1,"currentA":"28.7","controlEvseData":{"chargeCurrent":"0","activePower":"0","lineVoltage":"0","activeEnergy":"0","evMileage":"0","startDate":"Thu, Apr 30th 2020","sessionStatus":"Available","pluginTime":"09:05:18 AM","chargeDuration":"00:01:25","unPluginTime":"09:07:13 AM","lastSync":"2020-04-30 09:04:48 +0000","evseStatus":"Available","networkStatus":"Online","outoforder":"Offline","maxAmperage":"30","evseType":"AC","maxDraw":0,"limitedbyEvse":0,"chargeNearEnd":0,"evseMode":"Enabled","isEvseModeEnabled":"Enabled"},"stationPorts":[{"portId":1173,"portName":"Porta","connectorId":1},{"portId":1174,"portName":"Portb","connectorId":2},{"portId":1175,"portName":"Portz","connectorId":0}]}
     */

    private String status;
    private String success;
    private String error;
    private String message;
    private MonitorOcppBean monitorOcpp;

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

    public MonitorOcppBean getMonitorOcpp() {
        return monitorOcpp;
    }

    public void setMonitorOcpp(MonitorOcppBean monitorOcpp) {
        this.monitorOcpp = monitorOcpp;
    }

    public static class MonitorOcppBean {
        /**
         * stationId : 431
         * stationName : OCPP E8B7
         * chargeboxId : ChargionE8B7
         * latitude : 35.603718941421256
         * longitude : -118.12362708398439
         * stationStatus : Active
         * portId : 1173
         * portName : Porta
         * maxAmperage : 30
         * evseType : AC
         * isEvseModeEnabled : Enabled
         * connectorId : 1
         * currentA : 28.7
         * controlEvseData : {"chargeCurrent":"0","activePower":"0","lineVoltage":"0","activeEnergy":"0","evMileage":"0","startDate":"Thu, Apr 30th 2020","sessionStatus":"Available","pluginTime":"09:05:18 AM","chargeDuration":"00:01:25","unPluginTime":"09:07:13 AM","lastSync":"2020-04-30 09:04:48 +0000","evseStatus":"Available","networkStatus":"Online","outoforder":"Offline","maxAmperage":"30","evseType":"AC","maxDraw":0,"limitedbyEvse":0,"chargeNearEnd":0,"evseMode":"Enabled","isEvseModeEnabled":"Enabled"}
         * stationPorts : [{"portId":1173,"portName":"Porta","connectorId":1},{"portId":1174,"portName":"Portb","connectorId":2},{"portId":1175,"portName":"Portz","connectorId":0}]
         */

        private int stationId;
        private String stationName;
        private String chargeboxId;
        private String latitude;
        private String longitude;
        private String stationStatus;
        private int portId;
        private String portName;
        private String maxAmperage;
        private String evseType;
        private String isEvseModeEnabled;
        private int connectorId;
        private String currentA;
        private ControlEvseDataBean controlEvseData;
        private List<StationPortsBean> stationPorts;

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public String getChargeboxId() {
            return chargeboxId;
        }

        public void setChargeboxId(String chargeboxId) {
            this.chargeboxId = chargeboxId;
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

        public String getStationStatus() {
            return stationStatus;
        }

        public void setStationStatus(String stationStatus) {
            this.stationStatus = stationStatus;
        }

        public int getPortId() {
            return portId;
        }

        public void setPortId(int portId) {
            this.portId = portId;
        }

        public String getPortName() {
            return portName;
        }

        public void setPortName(String portName) {
            this.portName = portName;
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

        public String getIsEvseModeEnabled() {
            return isEvseModeEnabled;
        }

        public void setIsEvseModeEnabled(String isEvseModeEnabled) {
            this.isEvseModeEnabled = isEvseModeEnabled;
        }

        public int getConnectorId() {
            return connectorId;
        }

        public void setConnectorId(int connectorId) {
            this.connectorId = connectorId;
        }

        public String getCurrentA() {
            return currentA;
        }

        public void setCurrentA(String currentA) {
            this.currentA = currentA;
        }

        public ControlEvseDataBean getControlEvseData() {
            return controlEvseData;
        }

        public void setControlEvseData(ControlEvseDataBean controlEvseData) {
            this.controlEvseData = controlEvseData;
        }

        public List<StationPortsBean> getStationPorts() {
            return stationPorts;
        }

        public void setStationPorts(List<StationPortsBean> stationPorts) {
            this.stationPorts = stationPorts;
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
             * lastSync : 2020-04-30 09:04:48 +0000
             * evseStatus : Available
             * networkStatus : Online
             * outoforder : Offline
             * maxAmperage : 30
             * evseType : AC
             * maxDraw : 0
             * limitedbyEvse : 0
             * chargeNearEnd : 0
             * evseMode : Enabled
             * isEvseModeEnabled : Enabled
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
            private int maxDraw;
            private int limitedbyEvse;
            private int chargeNearEnd;
            private String evseMode;
            private String isEvseModeEnabled;

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

            public int getMaxDraw() {
                return maxDraw;
            }

            public void setMaxDraw(int maxDraw) {
                this.maxDraw = maxDraw;
            }

            public int getLimitedbyEvse() {
                return limitedbyEvse;
            }

            public void setLimitedbyEvse(int limitedbyEvse) {
                this.limitedbyEvse = limitedbyEvse;
            }

            public int getChargeNearEnd() {
                return chargeNearEnd;
            }

            public void setChargeNearEnd(int chargeNearEnd) {
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
        }

        public static class StationPortsBean {
            /**
             * portId : 1173
             * portName : Porta
             * connectorId : 1
             */

            private int portId;
            private String portName;
            private int connectorId;

            public int getPortId() {
                return portId;
            }

            public void setPortId(int portId) {
                this.portId = portId;
            }

            public String getPortName() {
                return portName;
            }

            public void setPortName(String portName) {
                this.portName = portName;
            }

            public int getConnectorId() {
                return connectorId;
            }

            public void setConnectorId(int connectorId) {
                this.connectorId = connectorId;
            }
        }
    }
}
