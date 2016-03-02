package com.rockets12.lunchcast.backendless;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;

public class Order implements Serializable {
    private String ownerId;
    private String order_time;
    private java.util.Date updated;
    private String objectId;
    private Integer state;
    private java.util.Date created;
    private Restaurant restaurant;
    private BackendlessUser order_creator;

    public String getOwnerId() {
        return ownerId;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public java.util.Date getUpdated() {
        return updated;
    }

    public String getObjectId() {
        return objectId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public java.util.Date getCreated() {
        return created;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public BackendlessUser getOrder_creator() {
        return order_creator;
    }

    public void setOrder_creator(BackendlessUser order_creator) {
        this.order_creator = order_creator;
    }


    public Order save() {
        return Backendless.Data.of(Order.class).save(this);
    }

    public Future<Order> saveAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Order> future = new Future<Order>();
            Backendless.Data.of(Order.class).save(this, future);

            return future;
        }
    }

    public void saveAsync(AsyncCallback<Order> callback) {
        Backendless.Data.of(Order.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(Order.class).remove(this);
    }

    public Future<Long> removeAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of(Order.class).remove(this, future);

            return future;
        }
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(Order.class).remove(this, callback);
    }

    public static Order findById(String id) {
        return Backendless.Data.of(Order.class).findById(id);
    }

    public static Future<Order> findByIdAsync(String id) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Order> future = new Future<Order>();
            Backendless.Data.of(Order.class).findById(id, future);

            return future;
        }
    }

    public static void findByIdAsync(String id, AsyncCallback<Order> callback) {
        Backendless.Data.of(Order.class).findById(id, callback);
    }

    public static Order findFirst() {
        return Backendless.Data.of(Order.class).findFirst();
    }

    public static Future<Order> findFirstAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Order> future = new Future<Order>();
            Backendless.Data.of(Order.class).findFirst(future);

            return future;
        }
    }

    public static void findFirstAsync(AsyncCallback<Order> callback) {
        Backendless.Data.of(Order.class).findFirst(callback);
    }

    public static Order findLast() {
        return Backendless.Data.of(Order.class).findLast();
    }

    public static Future<Order> findLastAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Order> future = new Future<Order>();
            Backendless.Data.of(Order.class).findLast(future);

            return future;
        }
    }

    public static void findLastAsync(AsyncCallback<Order> callback) {
        Backendless.Data.of(Order.class).findLast(callback);
    }

    public static BackendlessCollection<Order> find(BackendlessDataQuery query) {
        return Backendless.Data.of(Order.class).find(query);
    }

    public static Future<BackendlessCollection<Order>> findAsync(BackendlessDataQuery query) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<BackendlessCollection<Order>> future = new
                    Future<BackendlessCollection<Order>>();
            Backendless.Data.of(Order.class).find(query, future);

            return future;
        }
    }

    public static void findAsync(BackendlessDataQuery query,
                                 AsyncCallback<BackendlessCollection<Order>> callback) {
        Backendless.Data.of(Order.class).find(query, callback);
    }
}