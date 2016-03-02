package com.rockets12.lunchcast.backendless;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;

public class Meal implements Serializable {
    private String ownerId;
    private java.util.Date created;
    private String price;
    private String description;
    private java.util.Date updated;
    private String objectId;
    private String name;

    public String getOwnerId() {
        return ownerId;
    }

    public java.util.Date getCreated() {
        return created;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getUpdated() {
        return updated;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Meal save() {
        return Backendless.Data.of(Meal.class).save(this);
    }

    public Future<Meal> saveAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Meal> future = new Future<Meal>();
            Backendless.Data.of(Meal.class).save(this, future);

            return future;
        }
    }

    public void saveAsync(AsyncCallback<Meal> callback) {
        Backendless.Data.of(Meal.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(Meal.class).remove(this);
    }

    public Future<Long> removeAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of(Meal.class).remove(this, future);

            return future;
        }
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(Meal.class).remove(this, callback);
    }

    public static Meal findById(String id) {
        return Backendless.Data.of(Meal.class).findById(id);
    }

    public static Future<Meal> findByIdAsync(String id) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Meal> future = new Future<Meal>();
            Backendless.Data.of(Meal.class).findById(id, future);

            return future;
        }
    }

    public static void findByIdAsync(String id, AsyncCallback<Meal> callback) {
        Backendless.Data.of(Meal.class).findById(id, callback);
    }

    public static Meal findFirst() {
        return Backendless.Data.of(Meal.class).findFirst();
    }

    public static Future<Meal> findFirstAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Meal> future = new Future<Meal>();
            Backendless.Data.of(Meal.class).findFirst(future);

            return future;
        }
    }

    public static void findFirstAsync(AsyncCallback<Meal> callback) {
        Backendless.Data.of(Meal.class).findFirst(callback);
    }

    public static Meal findLast() {
        return Backendless.Data.of(Meal.class).findLast();
    }

    public static Future<Meal> findLastAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Meal> future = new Future<Meal>();
            Backendless.Data.of(Meal.class).findLast(future);

            return future;
        }
    }

    public static void findLastAsync(AsyncCallback<Meal> callback) {
        Backendless.Data.of(Meal.class).findLast(callback);
    }

    public static BackendlessCollection<Meal> find(BackendlessDataQuery query) {
        return Backendless.Data.of(Meal.class).find(query);
    }

    public static Future<BackendlessCollection<Meal>> findAsync(BackendlessDataQuery query) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<BackendlessCollection<Meal>> future = new Future<BackendlessCollection<Meal>>();
            Backendless.Data.of(Meal.class).find(query, future);

            return future;
        }
    }

    public static void findAsync(BackendlessDataQuery query,
                                 AsyncCallback<BackendlessCollection<Meal>> callback) {
        Backendless.Data.of(Meal.class).find(query, callback);
    }

}