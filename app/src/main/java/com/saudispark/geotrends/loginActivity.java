package com.saudispark.geotrends;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.http.Query;

import static com.saudispark.geotrends.MainActivity.REQUEST_RESULT;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.core.services.params.Geocode;


public class loginActivity extends Activity {
    public TwitterApiClient twitterApiClient;
    TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_login);

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);

        loginButton.setCallback(new Callback<TwitterSession>() {
            SharedPreferences settings = getSharedPreferences("GEO",MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                editor.putBoolean("logged_in", true);
                editor.putString("access_token",result.data.getAuthToken().token.toString());
                editor.putString("access_secret",result.data.getAuthToken().secret.toString());
                editor.apply();

                Intent returnIntent = new Intent();
                returnIntent.putExtra(MainActivity.REQUEST_RESULT,42);
                setResult(RESULT_OK, returnIntent);
                finish();
                //onClickClose(findViewById(R.id.login_button));

            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure

                editor.putBoolean("logged_in", false);
                editor.apply();
                Intent returnIntent = new Intent();
                returnIntent.putExtra(MainActivity.REQUEST_RESULT,42);
                setResult(RESULT_CANCELED, returnIntent);
                finish();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

public void onClickClose(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.REQUEST_RESULT,42);
        setResult(RESULT_OK, returnIntent);
        finish();
    }


}
