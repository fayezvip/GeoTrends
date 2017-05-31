package com.saudispark.geotrends;

import com.twitter.sdk.android.core.TwitterApiClient;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.HashtagEntity;
import retrofit2.http.Query;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Fayez on 5/30/17.
 */

public class TwitterTrendsApi extends TwitterApiClient {

    public TwitterTrendsApi(TwitterSession session) {
            super(session);
        }

        /**
         * Provide CustomService with defined endpoints
         */
        public TrendsService getTrendsService() {
            return getService(TrendsService.class);
        }
    }

    // example users/show service endpoint
    interface TrendsService {
        @GET("/1.1/trends/place.json")
        Call<HashtagEntity> void show(@Query("user_id") long id);
    }



}

