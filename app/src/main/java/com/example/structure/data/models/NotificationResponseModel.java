package com.example.structure.data.models;

/**
 * Created by Rajesh Pradeep G on 05-02-2020
 */
public class NotificationResponseModel {

    /**
     * status : 1
     * success : Notification Info
     * notification : {"isFullyCharged":1,"isUnexpectedlyInterrupted":1,"isNotifyToPluginMobile":1,"notifyPluginTime":"11:41","userDatetime":"2020-05-22 11:41:10","userTimezone":"Asia/Kolkata"}
     */

    private String status;
    private String success;
    private String error;
    private NotificationBean notification;

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

    public NotificationBean getNotification() {
        return notification;
    }

    public void setNotification(NotificationBean notification) {
        this.notification = notification;
    }

    public static class NotificationBean {
        /**
         * isFullyCharged : 1
         * isUnexpectedlyInterrupted : 1
         * isNotifyToPluginMobile : 1
         * notifyPluginTime : 11:41
         * userDatetime : 2020-05-22 11:41:10
         * userTimezone : Asia/Kolkata
         */

        private int isFullyCharged;
        private int isUnexpectedlyInterrupted;
        private int isNotifyToPluginMobile;
        private String notifyPluginTime;
        private String userDatetime;
        private String userTimezone;

        public int getIsFullyCharged() {
            return isFullyCharged;
        }

        public void setIsFullyCharged(int isFullyCharged) {
            this.isFullyCharged = isFullyCharged;
        }

        public int getIsUnexpectedlyInterrupted() {
            return isUnexpectedlyInterrupted;
        }

        public void setIsUnexpectedlyInterrupted(int isUnexpectedlyInterrupted) {
            this.isUnexpectedlyInterrupted = isUnexpectedlyInterrupted;
        }

        public int getIsNotifyToPluginMobile() {
            return isNotifyToPluginMobile;
        }

        public void setIsNotifyToPluginMobile(int isNotifyToPluginMobile) {
            this.isNotifyToPluginMobile = isNotifyToPluginMobile;
        }

        public String getNotifyPluginTime() {
            return notifyPluginTime;
        }

        public void setNotifyPluginTime(String notifyPluginTime) {
            this.notifyPluginTime = notifyPluginTime;
        }

        public String getUserDatetime() {
            return userDatetime;
        }

        public void setUserDatetime(String userDatetime) {
            this.userDatetime = userDatetime;
        }

        public String getUserTimezone() {
            return userTimezone;
        }

        public void setUserTimezone(String userTimezone) {
            this.userTimezone = userTimezone;
        }
    }
}
