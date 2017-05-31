package com.saudispark.geotrends;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.core.services.StatusesService;

import retrofit2.Call;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //access Twitter
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        SearchService searchService = twitterApiClient.getSearchService();

        Call<Tweet> call = statusesService.show(524971209851543553L, null, null, null);
        call.enqueue(new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                Toast.makeText(Main2Activity.this, result.data.text, Toast.LENGTH_LONG).show();
            }

            public void failure(TwitterException exception) {
                Toast.makeText(Main2Activity.this, "Error : " + exception.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onLogoutClick(View view) {
        SharedPreferences settings = getSharedPreferences("GEO",MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("logged_in", false);
        editor.apply();
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
       // startActivity(new Intent(Main2Activity.this, MainActivity.class));
        finish();



    }
}
