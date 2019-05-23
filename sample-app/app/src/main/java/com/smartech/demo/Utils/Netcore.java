package com.smartech.demo.Utils;

import android.app.Application;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import in.netcore.smartechfcm.NetcoreSDK;

public class Netcore {

    private static final String APPID = "<app_id>";

    public static void register(Application context){
        NetcoreSDK.register(context, APPID);
    }
    public static void login(Context context, String identity){
        NetcoreSDK.setIdentity(context,identity);
        NetcoreSDK.login(context);
    }
    public static void logout(Context context){
        NetcoreSDK.logout(context);
        NetcoreSDK.clearIdentity(context);
    }
    public static void track(Context context, String eventName, String payload){
        NetcoreSDK.track(context, eventName, payload);
    }
    public static void track(Context context, int eventID, String payload){
        NetcoreSDK.track(context, eventID, payload);
    }
    public static void profile(Context context, JSONObject payload){
        NetcoreSDK.profile(context,  payload);
    }

    public static String getGUID(Context context){
        return NetcoreSDK.getGUID(context);
    }
    public static void deleteNotification(Context context, List<String> notificationList) {
        NetcoreSDK.deleteNotification(context,notificationList);
    }
    public static JSONArray getNotifications(Context context, int notificationCount) {
        return NetcoreSDK.getNotifications(context,notificationCount);
    }

    public static boolean handleNotification(Context context, Map<String, String> map) {
        return NetcoreSDK.handleNotification(context,map);
    }
    public static int getUnreadNotificationsCount(Context context){
        return NetcoreSDK.getUnreadNotificationsCount(context);
    }
    public static void markNotificationAsRead(Context context, String trID, String deeplink) {
        NetcoreSDK.markNotificationAsRead(context,trID,deeplink);
    }
    public static void optOut(Context context, boolean optOut) {
        NetcoreSDK.optOut(context,optOut);
    }

    public static void setUserLocation(Context context, Double latitude, Double longitude) {
        NetcoreSDK.setUserLocation(context,latitude,longitude);
    }

    public static void setIdentity(Context context, String identity) {
        NetcoreSDK.setIdentity(context,identity);
    }

    public static void clearIdentity(Context context) {
        NetcoreSDK.clearIdentity(context);
    }

    public static void setPushToken(Context context, String token) {
        NetcoreSDK.setPushToken(context,token);
    }

    public static String getPushToken(Context context) {
        return NetcoreSDK.getPushToken(context);
    }

    public static void setPushIconColor(Context context, int argb) {
        NetcoreSDK.setPushIconColor(context,argb);
    }

    public static void resetPushIconColor(Context context) {
        NetcoreSDK.resetPushIconColor(context);
    }
}
