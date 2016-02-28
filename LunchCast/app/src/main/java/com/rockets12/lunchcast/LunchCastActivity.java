package com.rockets12.lunchcast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.rockets12.lunchcast.backendless.LunchCastUser;
import com.rockets12.lunchcast.backendless.Tag;
import com.rockets12.lunchcast.fragment.HomeFragment;
import com.rockets12.lunchcast.fragment.LoginFragment;
import com.rockets12.lunchcast.fragment.SubscriptionsFragment;

import java.util.ArrayList;

public class LunchCastActivity extends AppCompatActivity implements CallbackInterface {

    private static final String TAG = LunchCastActivity.class.getSimpleName();

    public static final String FRAGMENT_LOGIN = "login";
    public static final String FRAGMENT_HOME = "home";
    public static final String FRAGMENT_SUBSCRIPTIONS = "subscriptions";

    private LunchCastUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_cast);
        Backendless.initApp(this, Constants.BACKENDLESS_APP_ID, Constants.BACKENDLESS_SECRET_KEY,
                Constants.BACKENDLESS_VERSION);
        displayLoginFragment();
    }

    private void displayLoginFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, LoginFragment
                .newInstance(), FRAGMENT_LOGIN).commit();
    }

    private void displayHomeFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, HomeFragment
                .newInstance(), FRAGMENT_HOME).commit();
    }

    private void displaySubscriptionsFragment(ArrayList<Tag> tags) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                SubscriptionsFragment.newInstance(tags, (ArrayList<Tag>) mUser.getSubscriptions()),
                FRAGMENT_SUBSCRIPTIONS).commit();
    }

    @Override
    public void registerUser(String email, String password, String fullName) {
        LunchCastUser user = new LunchCastUser();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(fullName);
        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser backendlessUser) {
                Log.d(TAG, "user created: " + backendlessUser.getEmail());
                mUser = (LunchCastUser) backendlessUser;
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e(TAG, backendlessFault.toString());
            }
        });
    }

    @Override
    public void loginUser(String email, String password) {
        Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser backendlessUser) {
                Log.d(TAG, "user created: " + backendlessUser.getEmail());
                mUser = (LunchCastUser) backendlessUser;
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e(TAG, backendlessFault.toString());
            }
        }, true);
    }

    @Override
    public void displayOrders() {

    }

    @Override
    public void createNewOrder() {

    }

    @Override
    public void displaySubscriptions() {

    }
}
