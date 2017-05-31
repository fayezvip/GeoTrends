package com.saudispark.geotrends;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends Activity {


    public static final String REQUEST_RESULT="REQUEST_RESULT";
   // public final SharedPreferences settings = getPreferences(MODE_PRIVATE);
    public  boolean isUserLoggedin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        String TwitterKey = getString(R.string.TWITTER_KEY);
        String TwitterSecret = getString(R.string.TWITTER_SECRET);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(TwitterKey,TwitterSecret))
                .debug(true)
                .build();
        Twitter.initialize(config);

        SharedPreferences settings = getSharedPreferences("GEO",MODE_PRIVATE);

        isUserLoggedin =  settings.getBoolean("logged_in",false);

        if (isUserLoggedin)
        {
            Toast.makeText(this, "You Logged in", Toast.LENGTH_LONG).show();
            //startActivity(new Intent(MainActivity.this,Main2Activity.class));

        }
        else
        {

            Toast.makeText(this, "You NOT Logged in: ", Toast.LENGTH_LONG).show();

            Intent loginIntent = new Intent(MainActivity.this, loginActivity.class);
            startActivityForResult(loginIntent,1);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences settings = getSharedPreferences("GEO",MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        if (resultCode==RESULT_OK) {
            Toast.makeText(this, "the result is OK", Toast.LENGTH_LONG).show();
            editor.putBoolean("logged_in", true);
            editor.apply();
        }
        else if (resultCode==RESULT_CANCELED)
        {
            Toast.makeText(this, "the result is Canceled", Toast.LENGTH_LONG).show();
            editor.putBoolean("logged_in", false);
            editor.apply();

        }
        else
        {
            Toast.makeText(this, "ERRRRRRRRR", Toast.LENGTH_LONG).show();
            editor.putBoolean("logged_in", false);
            editor.apply();


        }
    }


    public void changeL (View view) {
   /*     SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("logged_in", !isUserLoggedin);
        editor.apply();
        isUserLoggedin = !isUserLoggedin;
        if (isUserLoggedin)
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
*/
        Intent loginIntent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(loginIntent);

    }





}
