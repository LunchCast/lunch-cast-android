package com.rockets12.lunchcast;

/**
 * Created by Stevan Kovacevic on 2/23/16.
 */
public interface CallbackInterface {

    public void registerUser(String email, String password, String fullName);

    public void loginUser(String email, String password);

    public void displayOrders();

    public void createNewOrder();

    public void displaySubscriptions();
}
