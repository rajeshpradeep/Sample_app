package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 22-11-2019
 */
public class LeaderboardResponseModel {

    /**
     * status : 1
     * success : Leaderboard
     * leaderboard_list : [{"alias":"jvojak","points":10549687,"sca_name":"Josip's Test Device","sca_id":"SCA-TEST-1234","position":1,"lb_rank":1},{"alias":"Leaderboard Test MM5","points":1328803,"sca_name":"MM5SCA Name 1","sca_id":"SCA-7906-F4CF","position":2,"lb_rank":2},{"alias":"jharper","points":129639,"sca_name":"Jay's SCA","sca_id":"SCA-BF87-ECF4","position":3,"lb_rank":3},{"alias":"q2","points":43306,"sca_name":"SCA  ED7C SN","sca_id":"SCA-BF87-ED7C","position":4,"lb_rank":4},{"alias":"la","points":7311,"sca_name":"SCA-A4BD-2940 name","sca_id":"SCA-A4BD-2940","position":5,"lb_rank":5},{"alias":"Leader board Test MM3","points":5398,"sca_name":"SCA Name MM3","sca_id":"SCA-MQTT-1111","position":6,"lb_rank":6},{"alias":"LB q4","points":479,"sca_name":"TLS1","sca_id":"SCA-TEST-TLS1","position":7,"lb_rank":7},{"alias":"Test User Leaderboard3","points":279,"sca_name":"sample sca_name","sca_id":"sample sca_id","position":8,"lb_rank":8},{"alias":"Test User Leaderboard3","points":279,"sca_name":"KLKL","sca_id":"SDSD-123-123","position":9,"lb_rank":8},{"alias":"Test User Leaderboard3","points":279,"sca_name":"AWA","sca_id":"asd123123","position":10,"lb_rank":8}]
     * userRank : {"position":0,"points":0}
     * deviceId : SCA-BF87-ED7C- edit
     * recordsCount : 10
     * totalRecordsCount : 19
     */

    private String status;
    private String success;
    private String error;
    private UserRankBean userRank;
    private String deviceId;
    private int recordsCount;
    private int totalRecordsCount;
    private List<LeaderboardListBean> leaderboard_list;

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

    public UserRankBean getUserRank() {
        return userRank;
    }

    public void setUserRank(UserRankBean userRank) {
        this.userRank = userRank;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
    }

    public int getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(int totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public List<LeaderboardListBean> getLeaderboard_list() {
        return leaderboard_list;
    }

    public void setLeaderboard_list(List<LeaderboardListBean> leaderboard_list) {
        this.leaderboard_list = leaderboard_list;
    }

    public static class UserRankBean {
        /**
         * position : 0
         * points : 0
         */

        private int position;
        private int points;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }

    public static class LeaderboardListBean {
        /**
         * alias : jvojak
         * points : 10549687
         * sca_name : Josip's Test Device
         * sca_id : SCA-TEST-1234
         * position : 1
         * lb_rank : 1
         */

        private String alias;
        private int points;
        private String sca_name;
        private String sca_id;
        private int position;
        private int lb_rank;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getSca_name() {
            return sca_name;
        }

        public void setSca_name(String sca_name) {
            this.sca_name = sca_name;
        }

        public String getSca_id() {
            return sca_id;
        }

        public void setSca_id(String sca_id) {
            this.sca_id = sca_id;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getLb_rank() {
            return lb_rank;
        }

        public void setLb_rank(int lb_rank) {
            this.lb_rank = lb_rank;
        }
    }
}
