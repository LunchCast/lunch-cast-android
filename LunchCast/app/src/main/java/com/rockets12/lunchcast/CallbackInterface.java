package com.rockets12.lunchcast;

import com.rockets12.lunchcast.backendless.OrderItem;
import com.rockets12.lunchcast.backendless.Restaurant;
import com.rockets12.lunchcast.backendless.Tag;

import java.util.List;

/**
 * Created by Stevan Kovacevic on 2/23/16.
 */
public interface CallbackInterface {

    public void registerUser(String email, String password, String fullName);

    public void loginUser(String email, String password);

    public void displayOrders();

    public void createNewOrder();

    public void displaySubscriptions();

    public void subscribeToTag(Tag tag);

    public void unsubscribeFromTag(Tag tag);

    public void onRestaurantClicked(Restaurant r);

    public void createOrder(Restaurant res, List<OrderItem> orderItems);
}
