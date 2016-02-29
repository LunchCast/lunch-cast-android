package com.rockets12.lunchcast.backendless;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;

public class Tag implements Serializable {

    private String objectId;
    private java.util.Date updated;
    private String name;
    private String ownerId;
    private java.util.Date created;

    public String getObjectId() {
        return objectId;
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

    public String getOwnerId() {
        return ownerId;
    }

    public java.util.Date getCreated() {
        return created;
    }


    public Tag save() {
        return Backendless.Data.of(Tag.class).save(this);
    }

    public Future<Tag> saveAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Tag> future = new Future<Tag>();
            Backendless.Data.of(Tag.class).save(this, future);

            return future;
        }
    }

    public void saveAsync(AsyncCallback<Tag> callback) {
        Backendless.Data.of(Tag.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(Tag.class).remove(this);
    }

    public Future<Long> removeAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of(Tag.class).remove(this, future);

            return future;
        }
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(Tag.class).remove(this, callback);
    }

    public static Tag findById(String id) {
        return Backendless.Data.of(Tag.class).findById(id);
    }

    public static Future<Tag> findByIdAsync(String id) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Tag> future = new Future<Tag>();
            Backendless.Data.of(Tag.class).findById(id, future);

            return future;
        }
    }

    public static void findByIdAsync(String id, AsyncCallback<Tag> callback) {
        Backendless.Data.of(Tag.class).findById(id, callback);
    }

    public static Tag findFirst() {
        return Backendless.Data.of(Tag.class).findFirst();
    }

    public static Future<Tag> findFirstAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Tag> future = new Future<Tag>();
            Backendless.Data.of(Tag.class).findFirst(future);

            return future;
        }
    }

    public static void findFirstAsync(AsyncCallback<Tag> callback) {
        Backendless.Data.of(Tag.class).findFirst(callback);
    }

    public static Tag findLast() {
        return Backendless.Data.of(Tag.class).findLast();
    }

    public static Future<Tag> findLastAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Tag> future = new Future<Tag>();
            Backendless.Data.of(Tag.class).findLast(future);

            return future;
        }
    }

    public static void findLastAsync(AsyncCallback<Tag> callback) {
        Backendless.Data.of(Tag.class).findLast(callback);
    }

    public static BackendlessCollection<Tag> find(BackendlessDataQuery query) {
        return Backendless.Data.of(Tag.class).find(query);
    }

    public static Future<BackendlessCollection<Tag>> findAsync(BackendlessDataQuery query) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<BackendlessCollection<Tag>> future = new Future<BackendlessCollection<Tag>>();
            Backendless.Data.of(Tag.class).find(query, future);

            return future;
        }
    }

    public static void findAsync(BackendlessDataQuery query,
                                 AsyncCallback<BackendlessCollection<Tag>> callback) {
        Backendless.Data.of(Tag.class).find(query, callback);
    }

    public static void findAllAsync(AsyncCallback<BackendlessCollection<Tag>> callback) {
        Backendless.Data.of(Tag.class).find(callback);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return !(name != null ? !name.equals(tag.name) : tag.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}