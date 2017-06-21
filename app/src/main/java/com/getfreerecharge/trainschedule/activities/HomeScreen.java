package com.getfreerecharge.trainschedule.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.utillss.ConnectionCheck;
import com.getfreerecharge.trainschedule.webs.SharedPreference;
import com.getfreerecharge.trainschedule.webs.WebActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    AdRequest adRequest;
    SharedPreference myprefs;
    InterstitialAd interstitialAd;
    private Handler handler1 = new Handler();
    LinearLayout bannerAd;

    LinearLayout pnrstatus, trainroute, scheduleoftrain, cancelledtrain, rescheduledtrain,
            trainarrivalatstation, seatavailability, fairenquiry, trainbwstation, helpyou;

    TextView homeactionbar, hometitlequote, textroute, textpnr, helptext, textbetween, textfair, textseat, textarrival,
            textrescheduled, textcancle, textschedule, hometitlequoteone;

    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        myprefs = new SharedPreference(HomeScreen.this);

        connectionCheck();
        adMobFullPageAd();

        pnrstatus = (LinearLayout) findViewById(R.id.pnrstatus);
        trainroute = (LinearLayout) findViewById(R.id.trainroute);
        scheduleoftrain = (LinearLayout) findViewById(R.id.scheduleoftrain);
        cancelledtrain = (LinearLayout) findViewById(R.id.cancelledtrain);
        rescheduledtrain = (LinearLayout) findViewById(R.id.rescheduledtrain);
        trainarrivalatstation = (LinearLayout) findViewById(R.id.trainarrivalatstation);
        seatavailability = (LinearLayout) findViewById(R.id.seatavailability);
        fairenquiry = (LinearLayout) findViewById(R.id.fairenquiry);
        trainbwstation = (LinearLayout) findViewById(R.id.trainbwstation);
        helpyou = (LinearLayout) findViewById(R.id.helpyou);
        homeactionbar = (TextView) findViewById(R.id.homeactionbar);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPathone);
        homeactionbar.setTypeface(tf);

        textroute = (TextView) findViewById(R.id.textroute);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPaththree);
        textroute.setTypeface(tf1);

        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
        hometitlequote = (TextView) findViewById(R.id.hometitlequote);
        hometitlequote.setTypeface(tf2);

        textpnr = (TextView) findViewById(R.id.textpnr);
        textpnr.setTypeface(tf2);

        helptext = (TextView) findViewById(R.id.helptext);
        helptext.setTypeface(tf2);

        textbetween = (TextView) findViewById(R.id.textbetween);
        textbetween.setTypeface(tf2);

        textfair = (TextView) findViewById(R.id.textfair);
        textfair.setTypeface(tf2);

        textseat = (TextView) findViewById(R.id.textseat);
        textseat.setTypeface(tf2);

        textarrival = (TextView) findViewById(R.id.textarrival);
        textarrival.setTypeface(tf2);

        textrescheduled = (TextView) findViewById(R.id.textrescheduled);
        textrescheduled.setTypeface(tf2);

        textcancle = (TextView) findViewById(R.id.textcancle);
        textcancle.setTypeface(tf2);

        textschedule = (TextView) findViewById(R.id.textschedule);
        textschedule.setTypeface(tf2);

        hometitlequoteone = (TextView) findViewById(R.id.hometitlequoteone);
        hometitlequoteone.setTypeface(tf2);

        pnrstatus.setOnClickListener(this);
        trainroute.setOnClickListener(this);
        scheduleoftrain.setOnClickListener(this);
        cancelledtrain.setOnClickListener(this);
        rescheduledtrain.setOnClickListener(this);
        trainarrivalatstation.setOnClickListener(this);
        seatavailability.setOnClickListener(this);
        fairenquiry.setOnClickListener(this);
        trainbwstation.setOnClickListener(this);
        helpyou.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v == pnrstatus) {
            Intent intent_pnrstatus= new Intent(HomeScreen.this, PnrStatus.class);
            startActivity(intent_pnrstatus);
        }

        if (v == trainroute) {
            Intent intent_pnrstatus= new Intent(HomeScreen.this, TrainRoutes.class);
            startActivity(intent_pnrstatus);
        }

        if (v == scheduleoftrain) {
            String url = "https://www.irctc.co.in/eticketing/loginHome.jsf";
            Intent intent = new Intent(HomeScreen.this, WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);

//            Intent intent_pnrstatus= new Intent(HomeScreen.this, WebActivity.class);
//            startActivity(intent_pnrstatus);
        }

        if (v == cancelledtrain)
        {
            Intent intent_pnrstatus= new Intent(HomeScreen.this, CancelledTrain.class);
            startActivity(intent_pnrstatus);
        }

        if (v == rescheduledtrain)
        {
            Intent intent_pnrstatus= new Intent(HomeScreen.this, RescheduledTrain.class);
            startActivity(intent_pnrstatus);
        }

        if (v == trainarrivalatstation)
        {
            Intent intent_pnrstatus= new Intent(HomeScreen.this, TrainArrivalAtStation.class);
            startActivity(intent_pnrstatus);
        }

        if (v == seatavailability)
        {
            Intent intent_pnrstatus= new Intent(HomeScreen.this, SeatAvailabilities.class);
            startActivity(intent_pnrstatus);
        }

        if (v == fairenquiry)
        {
            Intent intent_pnrstatus= new Intent(HomeScreen.this, FairEnquirey.class);
            startActivity(intent_pnrstatus);
        }

        if (v == trainbwstation)
        {
            Intent intent_pnrstatus= new Intent(HomeScreen.this, TrainsBetweenStation.class);
            startActivity(intent_pnrstatus);
        }

        if (v == helpyou)
        {
            Intent intent_pnrstatus= new Intent(HomeScreen.this, HappyToHelp.class);
            startActivity(intent_pnrstatus);
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////


    private void connectionCheck() {
        ConnectionCheck connectionCheck = new ConnectionCheck(getApplicationContext());
        interstitialAd = new InterstitialAd(getApplicationContext());
        if (connectionCheck.isConnectionAvailable(this)) {
            interstitialAd.setAdUnitId("ca-app-pub-1001302816606190/3839020469");
            adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
            addAdmobAdListner();
            //adMobBannerAd();
        }
    }

    private void addAdmobAdListner() {
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();

            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });
    }

//    private void adMobBannerAd() {
//        bannerAd = (LinearLayout) findViewById(R.id.myAdd);
//        final AdView adView = new AdView(this);
//        adView.setAdUnitId(myprefs.getAddbanner());
//        adView.setAdSize(AdSize.BANNER);
//        bannerAd.addView(adView);
//        final AdListener listener = new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                adView.setVisibility(View.VISIBLE);
//                super.onAdLoaded();
//            }
//        };
//        adView.setAdListener(listener);
//        AdRequest adRequest1 = new AdRequest.Builder().build();
//        adView.loadAd(adRequest1);
//    }

    Runnable mShowFullPageAdTask = new Runnable() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (interstitialAd.isLoaded())
                        interstitialAd.show();
                }
            });
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        handler1.postDelayed(mShowFullPageAdTask, 30 * 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler1.removeCallbacks(mShowFullPageAdTask);

    }

    private void adMobFullPageAd() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-1001302816606190/3839020469");
        requestNewInterstitial();
        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
    }

    private void requestNewInterstitial() {
        adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////


}

