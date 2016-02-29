package com.rockets12.lunchcast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.rockets12.lunchcast.backendless.Tag;
import com.rockets12.lunchcast.backendless.UserSubscription;
import com.rockets12.lunchcast.fragment.HomeFragment;
import com.rockets12.lunchcast.fragment.LoginFragment;
import com.rockets12.lunchcast.fragment.SubscriptionsFragment;

import java.util.ArrayList;

public class LunchCastActivity extends AppCompatActivity implements CallbackInterface {

    private static final String TAG = LunchCastActivity.class.getSimpleName();

    public static final String FRAGMENT_LOGIN = "login";
    public static final String FRAGMENT_HOME = "home";
    public static final String FRAGMENT_SUBSCRIPTIONS = "subscriptions";

    private BackendlessUser mUser;
    private UserSubscription mUserSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_cast);
        Backendless.initApp(this, Constants.BACKENDLESS_APP_ID, Constants.BACKENDLESS_SECRET_KEY,
                Constants.BACKENDLESS_VERSION);
        Backendless.Data.mapTableToClass("Users", BackendlessUser.class);
        displayLoginFragment();
    }

    private void displayLoginFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, LoginFragment
                .newInstance(), FRAGMENT_LOGIN).commit();
    }

    private void displayHomeFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, HomeFragment
                .newInstance(), FRAGMENT_HOME).commit();
    }

    private void displaySubscriptionsFragment(ArrayList<Tag> tags) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                SubscriptionsFragment.newInstance(tags, (ArrayList<Tag>) mUserSubscription
                        .getTags()), FRAGMENT_SUBSCRIPTIONS).commit();
    }

    @Override
    public void registerUser(String email, String password, String fullName) {
        BackendlessUser user = new BackendlessUser();
        user.setEmail(email);
        user.setPassword(password);
        user.setProperty("name", fullName);
        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser backendlessUser) {
                Log.d(TAG, "user created: " + backendlessUser.getEmail());
                //create empty user subscription on login
                mUserSubscription = new UserSubscription();
                mUserSubscription.setTags(new ArrayList<Tag>());
                mUserSubscription.setUserId(backendlessUser.getUserId());
                mUserSubscription.saveAsync(new AsyncCallback<UserSubscription>() {
                    @Override
                    public void handleResponse(UserSubscription userSubscription) {
                        mUserSubscription = userSubscription;
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {

                    }
                });
                loginUser(backendlessUser.getEmail(), backendlessUser.getPassword());
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
                Log.d(TAG, "user logged in: " + backendlessUser.getEmail());
                mUser = backendlessUser;
                if (mUserSubscription == null) {
                    // load user subscription object
                    BackendlessDataQuery query = new BackendlessDataQuery();
                    query.setWhereClause("userId = '" + mUser.getObjectId() + "'");
                    UserSubscription
                            .findAsync(
                                    query,
                                    new AsyncCallback<BackendlessCollection<UserSubscription>>() {
                                        @Override
                                        public void handleResponse
                                                (BackendlessCollection<UserSubscription>
                                                         userSubscriptionBackendlessCollection) {
                                            mUserSubscription =
                                                    userSubscriptionBackendlessCollection.getData
                                                            ().get(0);
                                        }

                                        @Override
                                        public void handleFault(BackendlessFault backendlessFault) {

                                        }
                                    });
                }
                displayHomeFragment();
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
        Tag.findAllAsync(new AsyncCallback<BackendlessCollection<Tag>>() {
            @Override
            public void handleResponse(BackendlessCollection<Tag> tagBackendlessCollection) {
                ArrayList<Tag> tags = (ArrayList<Tag>) tagBackendlessCollection.getData();
                displaySubscriptionsFragment(tags);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });
    }

    @Override
    public void subscribeToTag(Tag tag) {
        mUserSubscription.getTags().add(tag);
        mUserSubscription.saveAsync(new AsyncCallback<UserSubscription>() {
            @Override
            public void handleResponse(UserSubscription userSubscription) {
                mUserSubscription = userSubscription;
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e(TAG, backendlessFault.toString());
            }
        });
    }

    @Override
    public void unsubscribeFromTag(Tag tag) {
        mUserSubscription.getTags().remove(tag);
        mUserSubscription.saveAsync(new AsyncCallback<UserSubscription>() {
            @Override
            public void handleResponse(UserSubscription userSubscription) {
                mUserSubscription = userSubscription;
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e(TAG, backendlessFault.toString());
            }
        });
    }
}
