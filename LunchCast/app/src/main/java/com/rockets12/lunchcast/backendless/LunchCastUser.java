package com.rockets12.lunchcast.backendless;

import com.backendless.BackendlessUser;

public class LunchCastUser extends BackendlessUser {
    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getPassword() {
        return super.getPassword();
    }

    public String getDeviceId() {
        return (String) super.getProperty("deviceId");
    }

    public void setDeviceId(String deviceId) {
        super.setProperty("deviceId", deviceId);
    }

    public String getName() {
        return (String) super.getProperty("name");
    }

    public void setName(String name) {
        super.setProperty("name", name);
    }

    public java.util.List<Tag> getSubscriptions() {
        return (java.util.List<Tag>) super.getProperty("subscriptions");
    }

    public void setSubscriptions(java.util.List<Tag> subscriptions) {
        super.setProperty("subscriptions", subscriptions);
    }
}