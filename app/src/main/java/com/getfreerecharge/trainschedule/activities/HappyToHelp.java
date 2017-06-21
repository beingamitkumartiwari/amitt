package com.getfreerecharge.trainschedule.activities;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.utillss.ConnectionCheck;
import com.getfreerecharge.trainschedule.webs.SharedPreference;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class HappyToHelp extends AppCompatActivity {

    AdRequest adRequest;
    SharedPreference myprefs;
    InterstitialAd interstitialAd;
    private Handler handler1 = new Handler();
    LinearLayout bannerAd;

    TextView homeactionbar, hometitlequote, hometitlequoteone;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    TextView clss, first, second, fc, third, cc, sl, ssl, quota, gn, ld, ho, df, rac,
            oss, rs, ph, pt, dp, hp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_to_help);

        myprefs = new SharedPreference(HappyToHelp.this);

        connectionCheck();
        adMobFullPageAd();

        homeactionbar = (TextView) findViewById(R.id.homeactionbar);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPathone);
        homeactionbar.setTypeface(tf);

        hometitlequoteone = (TextView) findViewById(R.id.hometitlequoteone);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPaththree);
        hometitlequoteone.setTypeface(tf1);

        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
        hometitlequote = (TextView) findViewById(R.id.hometitlequote);
        hometitlequote.setTypeface(tf2);

        initView();
    }


    private void initView() {
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPathfour);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPathtwo);

        clss = (TextView) findViewById(R.id.clss);
        clss.setTypeface(tf);

        quota = (TextView) findViewById(R.id.quota);
        quota.setTypeface(tf);


        first = (TextView) findViewById(R.id.first);
        second = (TextView) findViewById(R.id.second);
        fc = (TextView) findViewById(R.id.fc);
        third = (TextView) findViewById(R.id.third);
        cc = (TextView) findViewById(R.id.cc);
        sl = (TextView) findViewById(R.id.sl);
        ssl = (TextView) findViewById(R.id.ssl);
        gn = (TextView) findViewById(R.id.gn);
        ld = (TextView) findViewById(R.id.ld);
        ho = (TextView) findViewById(R.id.ho);
        df = (TextView) findViewById(R.id.df);
        rac = (TextView) findViewById(R.id.rac);
        oss = (TextView) findViewById(R.id.oss);
        rs = (TextView) findViewById(R.id.rs);
        ph = (TextView) findViewById(R.id.ph);
        pt = (TextView) findViewById(R.id.pt);
        dp = (TextView) findViewById(R.id.dp);
        hp = (TextView) findViewById(R.id.hp);

        first.setTypeface(tf1);
        third.setTypeface(tf1);
        second.setTypeface(tf1);
        fc.setTypeface(tf1);
        cc.setTypeface(tf1);
        sl.setTypeface(tf1);
        ssl.setTypeface(tf1);
        gn.setTypeface(tf1);
        ld.setTypeface(tf1);
        ho.setTypeface(tf1);
        df.setTypeface(tf1);
        rac.setTypeface(tf1);
        oss.setTypeface(tf1);
        rs.setTypeface(tf1);
        ph.setTypeface(tf1);
        pt.setTypeface(tf1);
        dp.setTypeface(tf1);
        hp.setTypeface(tf1);

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
        handler1.postDelayed(mShowFullPageAdTask, 8 * 1000);
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
