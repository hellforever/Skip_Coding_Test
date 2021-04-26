package com.github.hellforever.skiptest1.network;

import com.github.hellforever.skiptest1.data.Envelop;
import com.github.hellforever.skiptest1.data.Restaurant;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface NetworkService {

    @GET("/restaurants/bypostcode/{postcode}")
    Observable<Envelop> getRestaurants(@Path("postcode") String postcode);
}
