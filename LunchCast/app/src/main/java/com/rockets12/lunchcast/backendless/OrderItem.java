package com.rockets12.lunchcast.backendless;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private java.util.Date created;
    private String objectId;
    private Integer quantity;
    private String ownerId;
    private java.util.Date updated;
    private Meal meal;
    private Order order_id;
    private BackendlessUser orderer;

    public java.util.Date getCreated() {
        return created;
    }

    public String getObjectId() {
        return objectId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public java.util.Date getUpdated() {
        return updated;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Order order_id) {
        this.order_id = order_id;
    }

    public BackendlessUser getOrderer() {
        return orderer;
    }

    public void setOrderer(BackendlessUser orderer) {
        this.orderer = orderer;
    }


    public OrderItem save() {
        return Backendless.Data.of(OrderItem.class).save(this);
    }

    public Future<OrderItem> saveAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<OrderItem> future = new Future<OrderItem>();
            Backendless.Data.of(OrderItem.class).save(this, future);

            return future;
        }
    }

    public void saveAsync(AsyncCallback<OrderItem> callback) {
        Backendless.Data.of(OrderItem.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(OrderItem.class).remove(this);
    }

    public Future<Long> removeAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of(OrderItem.class).remove(this, future);

            return future;
        }
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(OrderItem.class).remove(this, callback);
    }

    public static OrderItem findById(String id) {
        return Backendless.Data.of(OrderItem.class).findById(id);
    }

    public static Future<OrderItem> findByIdAsync(String id) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<OrderItem> future = new Future<OrderItem>();
            Backendless.Data.of(OrderItem.class).findById(id, future);

            return future;
        }
    }

    public static void findByIdAsync(String id, AsyncCallback<OrderItem> callback) {
        Backendless.Data.of(OrderItem.class).findById(id, callback);
    }

    public static OrderItem findFirst() {
        return Backendless.Data.of(OrderItem.class).findFirst();
    }

    public static Future<OrderItem> findFirstAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<OrderItem> future = new Future<OrderItem>();
            Backendless.Data.of(OrderItem.class).findFirst(future);

            return future;
        }
    }

    public static void findFirstAsync(AsyncCallback<OrderItem> callback) {
        Backendless.Data.of(OrderItem.class).findFirst(callback);
    }

    public static OrderItem findLast() {
        return Backendless.Data.of(OrderItem.class).findLast();
    }

    public static Future<OrderItem> findLastAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<OrderItem> future = new Future<OrderItem>();
            Backendless.Data.of(OrderItem.class).findLast(future);

            return future;
        }
    }

    public static void findLastAsync(AsyncCallback<OrderItem> callback) {
        Backendless.Data.of(OrderItem.class).findLast(callback);
    }

    public static BackendlessCollection<OrderItem> find(BackendlessDataQuery query) {
        return Backendless.Data.of(OrderItem.class).find(query);
    }

    public static Future<BackendlessCollection<OrderItem>> findAsync(BackendlessDataQuery query) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<BackendlessCollection<OrderItem>> future = new
                    Future<BackendlessCollection<OrderItem>>();
            Backendless.Data.of(OrderItem.class).find(query, future);

            return future;
        }
    }

    public static void findAsync(BackendlessDataQuery query,
                                 AsyncCallback<BackendlessCollection<OrderItem>> callback) {
        Backendless.Data.of(OrderItem.class).find(query, callback);
    }
}