package com.rockets12.lunchcast.backendless;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class UserSubscription {
    private java.util.Date created;
    private String objectId;
    private String ownerId;
    private java.util.Date updated;
    private java.util.List<Tag> tags;
    private String userId;

    public java.util.Date getCreated() {
        return created;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public java.util.Date getUpdated() {
        return updated;
    }

    public java.util.List<Tag> getTags() {
        return tags;
    }

    public void setTags(java.util.List<Tag> tags) {
        this.tags = tags;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public UserSubscription save() {
        return Backendless.Data.of(UserSubscription.class).save(this);
    }

    public Future<UserSubscription> saveAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<UserSubscription> future = new Future<UserSubscription>();
            Backendless.Data.of(UserSubscription.class).save(this, future);

            return future;
        }
    }

    public void saveAsync(AsyncCallback<UserSubscription> callback) {
        Backendless.Data.of(UserSubscription.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(UserSubscription.class).remove(this);
    }

    public Future<Long> removeAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of(UserSubscription.class).remove(this, future);

            return future;
        }
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(UserSubscription.class).remove(this, callback);
    }

    public static UserSubscription findById(String id) {
        return Backendless.Data.of(UserSubscription.class).findById(id);
    }

    public static Future<UserSubscription> findByIdAsync(String id) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<UserSubscription> future = new Future<UserSubscription>();
            Backendless.Data.of(UserSubscription.class).findById(id, future);

            return future;
        }
    }

    public static void findByIdAsync(String id, AsyncCallback<UserSubscription> callback) {
        Backendless.Data.of(UserSubscription.class).findById(id, callback);
    }

    public static UserSubscription findFirst() {
        return Backendless.Data.of(UserSubscription.class).findFirst();
    }

    public static Future<UserSubscription> findFirstAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<UserSubscription> future = new Future<UserSubscription>();
            Backendless.Data.of(UserSubscription.class).findFirst(future);

            return future;
        }
    }

    public static void findFirstAsync(AsyncCallback<UserSubscription> callback) {
        Backendless.Data.of(UserSubscription.class).findFirst(callback);
    }

    public static UserSubscription findLast() {
        return Backendless.Data.of(UserSubscription.class).findLast();
    }

    public static Future<UserSubscription> findLastAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<UserSubscription> future = new Future<UserSubscription>();
            Backendless.Data.of(UserSubscription.class).findLast(future);

            return future;
        }
    }

    public static void findLastAsync(AsyncCallback<UserSubscription> callback) {
        Backendless.Data.of(UserSubscription.class).findLast(callback);
    }

    public static BackendlessCollection<UserSubscription> find(BackendlessDataQuery query) {
        return Backendless.Data.of(UserSubscription.class).find(query);
    }

    public static Future<BackendlessCollection<UserSubscription>> findAsync(BackendlessDataQuery
                                                                                    query) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<BackendlessCollection<UserSubscription>> future = new
                    Future<BackendlessCollection<UserSubscription>>();
            Backendless.Data.of(UserSubscription.class).find(query, future);

            return future;
        }
    }

    public static void findAsync(BackendlessDataQuery query,
                                 AsyncCallback<BackendlessCollection<UserSubscription>> callback) {
        Backendless.Data.of(UserSubscription.class).find(query, callback);
    }
}