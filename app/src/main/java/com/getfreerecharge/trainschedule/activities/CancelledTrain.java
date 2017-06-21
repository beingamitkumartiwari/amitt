package com.getfreerecharge.trainschedule.activities;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.adapters.CancelTrainAdapter;
import com.getfreerecharge.trainschedule.models.cancelledtrains.CancelledTrainPojo;
import com.getfreerecharge.trainschedule.models.cancelledtrains.Dest;
import com.getfreerecharge.trainschedule.models.cancelledtrains.Source;
import com.getfreerecharge.trainschedule.models.cancelledtrains.Train;
import com.getfreerecharge.trainschedule.models.cancelledtrains.Train_;
import com.getfreerecharge.trainschedule.utillss.ConnectionCheck;
import com.getfreerecharge.trainschedule.utillss.RestInterface;
import com.getfreerecharge.trainschedule.utillss.ServiceGenerator;
import com.getfreerecharge.trainschedule.webs.SharedPreference;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.JsonObject;
import com.victor.loading.book.BookLoading;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelledTrain extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    AdRequest adRequest;
    SharedPreference myprefs;
    InterstitialAd interstitialAd;
    private Handler handler1 = new Handler();
    LinearLayout bannerAd;

    BookLoading bookLoading;

    ImageView date_picker;
    EditText input_date;
    TextView get_cancelled_train;
    RecyclerView recycler_cancelled_train;
    RestInterface restInterface;
    Call<CancelledTrainPojo> cancelledTrainPojoCall;
    ArrayList<Train> trainArrayList;
    CancelTrainAdapter cancelTrainAdapter;
    LinearLayout ll1, ll2, ll3, ll4;

    TextView homeactionbar, hometitlequote, hometitlequoteone, errortext;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelled_train);

        myprefs = new SharedPreference(CancelledTrain.this);

        connectionCheck();
        adMobFullPageAd();
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll4 = (LinearLayout) findViewById(R.id.ll4);



        date_picker = (ImageView) findViewById(R.id.date_picker);
        input_date = (EditText) findViewById(R.id.input_date);
        get_cancelled_train = (TextView) findViewById(R.id.get_cancelled_train);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
        get_cancelled_train.setTypeface(tf2);

        homeactionbar = (TextView) findViewById(R.id.homeactionbar);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPathone);
        homeactionbar.setTypeface(tf);

        hometitlequoteone = (TextView) findViewById(R.id.hometitlequoteone);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPaththree);
        hometitlequoteone.setTypeface(tf1);

        hometitlequote = (TextView) findViewById(R.id.hometitlequote);
        hometitlequote.setTypeface(tf2);

        recycler_cancelled_train = (RecyclerView) findViewById(R.id.recycler_cancelled_train);

        restInterface = ServiceGenerator.createService(RestInterface.class);
        trainArrayList = new ArrayList<>();

        cancelTrainAdapter = new CancelTrainAdapter(this, trainArrayList);
        recycler_cancelled_train.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        get_cancelled_train.setOnClickListener(this);
        date_picker.setOnClickListener(this);

        errortext = (TextView) findViewById(R.id.errortext);
        errortext.setTypeface(tf2);
        bookLoading = (BookLoading) findViewById(R.id.bookloading);
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ll1.setVisibility(View.GONE);
            ll2.setVisibility(View.GONE);
            ll3.setVisibility(View.GONE);
            ll4.setVisibility(View.VISIBLE);
            bookLoading.start();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (cancelTrainAdapter.getItemCount() == 0) {
                ll1.setVisibility(View.GONE);
                ll2.setVisibility(View.GONE);
                ll3.setVisibility(View.VISIBLE);
                ll4.setVisibility(View.GONE);
            } else {
                ll1.setVisibility(View.GONE);
                ll2.setVisibility(View.VISIBLE);
                ll3.setVisibility(View.GONE);
                ll4.setVisibility(View.GONE);
            }

            bookLoading.stop();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == date_picker) {
            getCallenderDate();
        }
        if (v == get_cancelled_train) {
            scheduleoftrains();
            ll1.setVisibility(View.GONE);
            new MyTask().execute();
        }
    }


    private void getCallenderDate() {
        Calendar now = Calendar.getInstance();
        @SuppressLint("WrongConstant") DatePickerDialog dpd = DatePickerDialog.newInstance(CancelledTrain.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onResume() {
        super.onResume();
        handler1.postDelayed(mShowFullPageAdTask, 45 * 1000);
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "-" + (++monthOfYear) + "-" + year;
        input_date.setText(date);
    }

//f2igrqx8, fefkhr3u
    private void scheduleoftrains() {
        String getdate = input_date.getText().toString();
        JsonObject jsonObject = new JsonObject();
        cancelledTrainPojoCall = restInterface.getCancelledTrain(getdate, "y98sokxj");
        cancelledTrainPojoCall.enqueue(new Callback<CancelledTrainPojo>() {
            @Override
            public void onResponse(Call<CancelledTrainPojo> call, Response<CancelledTrainPojo> response) {
                if (response.isSuccessful()) {
                    CancelledTrainPojo ctp = response.body();
                    Train train = new Train();
                    if (response.body().getResponseCode().toString().equals("200")) {
                        trainArrayList.clear();
                        List<Train> trainDetail = ctp.getTrains();
                        System.out.println("go2" + trainDetail);
                        for (int i = 0; i < trainDetail.size(); i++) {
                            Train t1 = new Train();
                            t1.setDest(trainDetail.get(i).getDest());
                            t1.setSource(trainDetail.get(i).getSource());
                            t1.setTrain(trainDetail.get(i).getTrain());
                            trainArrayList.add(t1);
                            System.out.println("go2" + t1.toString());
                        }
                        ll2.setVisibility(View.VISIBLE);
                    }
                    cancelTrainAdapter = new CancelTrainAdapter(getApplicationContext(), trainArrayList);
                    recycler_cancelled_train.setAdapter(cancelTrainAdapter);

                    if (response.body().getResponseCode().toString().equals("204"))
                    {
                        ll3.setVisibility(View.VISIBLE);
                    }
                }

                else
                {
                    ll3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<CancelledTrainPojo> call, Throwable t) {
                ll3.setVisibility(View.VISIBLE);
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////


    private void connectionCheck() {
        ConnectionCheck connectionCheck = new ConnectionCheck(getApplicationContext());
        interstitialAd = new InterstitialAd(getApplicationContext());
        if (connectionCheck.isConnectionAvailable(this)) {
            interstitialAd.setAdUnitId(myprefs.getAddmob());
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

//    @Override
//    protected void onResume() {
//        super.onResume();
//        handler1.postDelayed(mShowFullPageAdTask, 8 * 1000);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        handler1.removeCallbacks(mShowFullPageAdTask);

    }

    private void adMobFullPageAd() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId((myprefs.getAddmob()));
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
