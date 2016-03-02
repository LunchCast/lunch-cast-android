package com.rockets12.lunchcast.backendless;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String ownerId;
    private String objectId;
    private String address;
    private java.util.Date created;
    private java.util.Date updated;
    private String name;
    private String telefon;
    private Integer minAmount;
    private Integer eta;
    private java.util.List<Tag> tags;
    private java.util.List<Meal> meals;

    public String getOwnerId() {
        return ownerId;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.util.Date getCreated() {
        return created;
    }

    public java.util.Date getUpdated() {
        return updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public java.util.List<Tag> getTags() {
        return tags;
    }

    public void setTags(java.util.List<Tag> tags) {
        this.tags = tags;
    }

    public java.util.List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(java.util.List<Meal> meals) {
        this.meals = meals;
    }


    public Restaurant save() {
        return Backendless.Data.of(Restaurant.class).save(this);
    }

    public Future<Restaurant> saveAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Restaurant> future = new Future<Restaurant>();
            Backendless.Data.of(Restaurant.class).save(this, future);

            return future;
        }
    }

    public void saveAsync(AsyncCallback<Restaurant> callback) {
        Backendless.Data.of(Restaurant.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(Restaurant.class).remove(this);
    }

    public Future<Long> removeAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of(Restaurant.class).remove(this, future);

            return future;
        }
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(Restaurant.class).remove(this, callback);
    }

    public static Restaurant findById(String id) {
        return Backendless.Data.of(Restaurant.class).findById(id);
    }

    public static Future<Restaurant> findByIdAsync(String id) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Restaurant> future = new Future<Restaurant>();
            Backendless.Data.of(Restaurant.class).findById(id, future);

            return future;
        }
    }

    public static void findByIdAsync(String id, AsyncCallback<Restaurant> callback) {
        Backendless.Data.of(Restaurant.class).findById(id, callback);
    }

    public static Restaurant findFirst() {
        return Backendless.Data.of(Restaurant.class).findFirst();
    }

    public static Future<Restaurant> findFirstAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Restaurant> future = new Future<Restaurant>();
            Backendless.Data.of(Restaurant.class).findFirst(future);

            return future;
        }
    }

    public static void findFirstAsync(AsyncCallback<Restaurant> callback) {
        Backendless.Data.of(Restaurant.class).findFirst(callback);
    }

    public static Restaurant findLast() {
        return Backendless.Data.of(Restaurant.class).findLast();
    }

    public static Future<Restaurant> findLastAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Restaurant> future = new Future<Restaurant>();
            Backendless.Data.of(Restaurant.class).findLast(future);

            return future;
        }
    }

    public static void findLastAsync(AsyncCallback<Restaurant> callback) {
        Backendless.Data.of(Restaurant.class).findLast(callback);
    }

    public static BackendlessCollection<Restaurant> find(BackendlessDataQuery query) {
        return Backendless.Data.of(Restaurant.class).find(query);
    }

    public static Future<BackendlessCollection<Restaurant>> findAsync(BackendlessDataQuery query) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<BackendlessCollection<Restaurant>> future = new
                    Future<BackendlessCollection<Restaurant>>();
            Backendless.Data.of(Restaurant.class).find(query, future);

            return future;
        }
    }

    public static void findAsync(BackendlessDataQuery query,
                                 AsyncCallback<BackendlessCollection<Restaurant>> callback) {
        Backendless.Data.of(Restaurant.class).find(query, callback);
    }

    public static void findAllAsync(AsyncCallback<BackendlessCollection<Restaurant>> callback) {
        Backendless.Data.of(Restaurant.class).find(callback);
    }
}