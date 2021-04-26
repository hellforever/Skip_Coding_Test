package com.github.hellforever.skiptest1;

import com.github.hellforever.skiptest1.data.Envelop;
import com.github.hellforever.skiptest1.data.Restaurant;
import com.github.hellforever.skiptest1.network.NetworkService;
import com.github.hellforever.skiptest1.network.RetrofitSingleton;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantModel {
    private RestaurantModel() {
    }

    private static volatile RestaurantModel instance;

    public static RestaurantModel getInstance() {
        if (instance == null) {
            synchronized (RestaurantModel.class) {
                if (instance == null) {
                    instance = new RestaurantModel();
                }
            }
        }
        return instance;
    }

    public Observable<List<Restaurant>> getListRestaurant(String postCode) {
        return RetrofitSingleton.getRetrofit().create(NetworkService.class)
                .getRestaurants(postCode).map(Envelop::getRestaurants)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
