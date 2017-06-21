package com.getfreerecharge.trainschedule.activities;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.adapters.RescheduledTrainAdapter;
import com.getfreerecharge.trainschedule.models.rescheduledtrains.RescheduledTrainPojo;
import com.getfreerecharge.trainschedule.models.rescheduledtrains.Train;
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


public class RescheduledTrain extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    AdRequest adRequest;
    SharedPreference myprefs;
    InterstitialAd interstitialAd;
    private Handler handler1 = new Handler();
    LinearLayout bannerAd;

    BookLoading bookLoading;

    RecyclerView recycler_rescheduled_train;
    ImageView date_picker;
    EditText input_date;
    TextView get_rescheduled_train;
    RestInterface restInterface;
    LinearLayout ll1, ll2, ll3, ll4;
    Call<RescheduledTrainPojo> rescheduledTrainPojoCall;
    ArrayList<Train> trainArrayList;
    RescheduledTrainAdapter rescheduledTrainAdapter;

    TextView homeactionbar, hometitlequote, hometitlequoteone, errortext;

    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescheduled_train);

        myprefs = new SharedPreference(RescheduledTrain.this);

        connectionCheck();
        adMobFullPageAd();

        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll4 = (LinearLayout) findViewById(R.id.ll4);

        date_picker = (ImageView) findViewById(R.id.date_picker);
        input_date = (EditText) findViewById(R.id.input_date);

        get_rescheduled_train = (TextView) findViewById(R.id.get_rescheduled_train);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
        get_rescheduled_train.setTypeface(tf2);

        recycler_rescheduled_train = (RecyclerView) findViewById(R.id.recycler_rescheduled_train);

        restInterface = ServiceGenerator.createService(RestInterface.class);

        trainArrayList = new ArrayList<>();
        rescheduledTrainAdapter=new RescheduledTrainAdapter(this, trainArrayList);

        recycler_rescheduled_train.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        get_rescheduled_train.setOnClickListener(this);
        date_picker.setOnClickListener(this);

        homeactionbar = (TextView) findViewById(R.id.homeactionbar);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPathone);
        homeactionbar.setTypeface(tf);

        hometitlequoteone = (TextView) findViewById(R.id.hometitlequoteone);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPaththree);
        hometitlequoteone.setTypeface(tf1);

        hometitlequote = (TextView) findViewById(R.id.hometitlequote);
        hometitlequote.setTypeface(tf2);

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
            if (rescheduledTrainAdapter.getItemCount() == 0) {
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
        if (v == get_rescheduled_train) {
            scheduleoftrains();
            ll1.setVisibility(View.GONE);
            new MyTask().execute();
        }
    }

    private void scheduleoftrains() {
        String dates = input_date.getText().toString();
        JsonObject jsonObject = new JsonObject();
        rescheduledTrainPojoCall = restInterface.getRescheduledTrain(dates, "y98sokxj");
        rescheduledTrainPojoCall.enqueue(new Callback<RescheduledTrainPojo>() {
            @Override
            public void onResponse(Call<RescheduledTrainPojo> call, Response<RescheduledTrainPojo> response) {
                if (response.isSuccessful())
                {
                    RescheduledTrainPojo rta = response.body();
                    if (response.body().getResponseCode().toString().equals("200"))
                    {
                        trainArrayList.clear();
                        List<Train> trainsDetail = rta.getTrains();
                        for (int i =0; i< trainsDetail.size(); i++ )
                        {
                            Train train = new Train();
                            train.setName(trainsDetail.get(i).getName());
                            train.setNumber(trainsDetail.get(i).getNumber());
                            train.setFrom(trainsDetail.get(i).getFrom());
                            train.setTo(trainsDetail.get(i).getTo());
                            train.setRescheduledDate(trainsDetail.get(i).getRescheduledDate());
                            train.setRescheduledTime(trainsDetail.get(i).getRescheduledTime());
                            train.setTimeDiff(trainsDetail.get(i).getTimeDiff());
                            trainArrayList.add(train);
                        }

                        ll2.setVisibility(View.VISIBLE);
                    }
                    rescheduledTrainAdapter = new RescheduledTrainAdapter(getApplicationContext(), trainArrayList);
                    recycler_rescheduled_train.setAdapter(rescheduledTrainAdapter);
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
            public void onFailure(Call<RescheduledTrainPojo> call, Throwable t) {
                ll3.setVisibility(View.VISIBLE);
            }
        });

    }

    private void getCallenderDate() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(RescheduledTrain.this,
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
