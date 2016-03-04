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
import com.rockets12.lunchcast.backendless.Order;
import com.rockets12.lunchcast.backendless.OrderItem;
import com.rockets12.lunchcast.backendless.Restaurant;
import com.rockets12.lunchcast.backendless.Tag;
import com.rockets12.lunchcast.backendless.UserSubscription;
import com.rockets12.lunchcast.fragment.HomeFragment;
import com.rockets12.lunchcast.fragment.LoginFragment;
import com.rockets12.lunchcast.fragment.MealsFragment;
import com.rockets12.lunchcast.fragment.OrdersFragment;
import com.rockets12.lunchcast.fragment.RestaurantsFragment;
import com.rockets12.lunchcast.fragment.SubscriptionsFragment;

import java.util.ArrayList;
import java.util.List;

public class LunchCastActivity extends AppCompatActivity implements CallbackInterface {

    private static final String TAG = LunchCastActivity.class.getSimpleName();

    public static final String FRAGMENT_LOGIN = "login";
    public static final String FRAGMENT_HOME = "home";
    public static final String FRAGMENT_SUBSCRIPTIONS = "subscriptions";
    public static final String FRAGMENT_RESTAURANTS = "restaurants";
    public static final String FRAGMENT_MEALS = "meals";
    public static final String FRAGMENT_ORDERS = "orders";

    public static final int ORDER_STATE_OPEN = 0;
    public static final int ORDER_STATE_CLOSED = 1;
    public static final int ORDER_STATE_EATEN = 2;

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

    private void displayRestaurantsFragment(ArrayList<Restaurant> restaurants) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                RestaurantsFragment.newInstance(restaurants), FRAGMENT_RESTAURANTS).commit();
    }

    private void displayMealsFragment(Order order, Restaurant restaurant) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                MealsFragment.newInstance(order, restaurant), FRAGMENT_MEALS)
                .commit();
    }

    private void displayOrdersFragment(ArrayList<Order> orders) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                OrdersFragment.newInstance(orders), FRAGMENT_ORDERS)
                .commit();
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
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause("state in (0,1)");
        Order.findAsync(query, new AsyncCallback<BackendlessCollection<Order>>() {
            @Override
            public void handleResponse(BackendlessCollection<Order> orderBackendlessCollection) {
                ArrayList<Order> orders = (ArrayList<Order>) orderBackendlessCollection.getData();
                displayOrdersFragment(orders);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });
    }

    @Override
    public void createNewOrder() {
        Restaurant.findAllAsync(new AsyncCallback<BackendlessCollection<Restaurant>>() {
            @Override
            public void handleResponse(BackendlessCollection<Restaurant> tagBackendlessCollection) {
                ArrayList<Restaurant> tags = (ArrayList<Restaurant>) tagBackendlessCollection
                        .getData();
                displayRestaurantsFragment(tags);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });
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

    @Override
    public void onRestaurantClicked(Restaurant r) {
        displayMealsFragment(null, r);
    }

    @Override
    public void onOrderClicked(Order o) {

    }

    @Override
    public void createOrder(final Restaurant res, final List<OrderItem> orderItems) {
        Log.d(TAG, "creating order for res:" + res.getName());
        Order newOrder = new Order();
        newOrder.setRestaurant(res);
        newOrder.setOrder_creator(mUser);
        newOrder.setState(ORDER_STATE_OPEN);
        newOrder.saveAsync(new AsyncCallback<Order>() {
            @Override
            public void handleResponse(Order order) {
                for (OrderItem item : orderItems) {
                    item.setOrder_id(order);
                    item.setOrderer(mUser);
                    item.saveAsync(new AsyncCallback<OrderItem>() {
                        @Override
                        public void handleResponse(OrderItem orderItem) {

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
                Log.d(TAG, "error creating order: " + backendlessFault.toString());
            }
        });
    }
}
