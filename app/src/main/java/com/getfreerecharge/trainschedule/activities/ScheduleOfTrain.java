package com.getfreerecharge.trainschedule.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.adapters.ScheduleOfTrainAdapter;
import com.getfreerecharge.trainschedule.models.scheduleoftrains.Day;
import com.getfreerecharge.trainschedule.models.scheduleoftrains.ScheduleOfTrainByNameOrNumber;
import com.getfreerecharge.trainschedule.models.trainroutes.Train;
import com.getfreerecharge.trainschedule.models.trainsugession.AutoCompleteTrain;
import com.getfreerecharge.trainschedule.models.trainsugession.SuggestedTrain;
import com.getfreerecharge.trainschedule.utillss.ConnectionCheck;
import com.getfreerecharge.trainschedule.utillss.RestInterface;
import com.getfreerecharge.trainschedule.utillss.ServiceGenerator;
import com.getfreerecharge.trainschedule.webs.SharedPreference;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.JsonObject;
import com.victor.loading.book.BookLoading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleOfTrain extends AppCompatActivity implements View.OnClickListener {

    AdRequest adRequest;
    SharedPreference myprefs;
    InterstitialAd interstitialAd;
    private Handler handler1 = new Handler();
    LinearLayout bannerAd;

    BookLoading bookLoading;

    String data1;
    LinearLayout ll1, ll2, ll3, ll4;

    RestInterface restInterface;
    Call<ScheduleOfTrainByNameOrNumber> scheduleOfTrainByNameOrNumberCall;
    ArrayList<Day> dayArrayList;
    ScheduleOfTrainAdapter scheduleOfTrainAdapter;

    EditText input_train_name_number;
    TextView get_train_schedule;
    RecyclerView recycler_days;

    TextView homeactionbar, hometitlequote, hometitlequoteone, errortext;

    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    AutoCompleteTextView autoCompleteTextView1;
    Call<AutoCompleteTrain> autoCompleteTrainCall;
    List<String> train= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_of_train);

        myprefs = new SharedPreference(ScheduleOfTrain.this);

        connectionCheck();
        adMobFullPageAd();

        get_train_schedule = (TextView) findViewById(R.id.get_train_schedule);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
        get_train_schedule.setTypeface(tf2);

        recycler_days = (RecyclerView) findViewById(R.id.recycler_days);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll4 = (LinearLayout) findViewById(R.id.ll4);

        restInterface = ServiceGenerator.createService(RestInterface.class);
        dayArrayList = new ArrayList<>();
        scheduleOfTrainAdapter = new ScheduleOfTrainAdapter(this, dayArrayList);

        recycler_days.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        get_train_schedule.setOnClickListener(this);

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

        autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        autoCompleteTextView1.setThreshold(2);
        autoCompleteTextView1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getListOfData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getListOfData();

        bookLoading = (BookLoading) findViewById(R.id.bookloading);
    }



    private void scheduleoftrains() {

        String gettrain = autoCompleteTextView1.getText().toString();
        Pattern pattern = Pattern.compile(", *");
        Matcher matcher = pattern.matcher(gettrain);

        String s1, s2;

        if (matcher.find()) {
            s1 = gettrain.substring(0, matcher.start());
            s2 = gettrain.substring(matcher.end());
            System.out.println("Rock" + gettrain);
            System.out.println("Rock" + s1);
            data1 = s2;
        }

//        String name_num = autoCompleteTextView1.getText().toString();
        JsonObject jsonObject = new JsonObject();
        scheduleOfTrainByNameOrNumberCall=restInterface.getScheduleoftrain(data1, "y98sokxj");
        scheduleOfTrainByNameOrNumberCall.enqueue(new Callback<ScheduleOfTrainByNameOrNumber>() {
            @Override
            public void onResponse(Call<ScheduleOfTrainByNameOrNumber> call, Response<ScheduleOfTrainByNameOrNumber> response) {
                if (response.isSuccessful())
                {
                    ScheduleOfTrainByNameOrNumber sc = response.body();
                    Train train = new Train();
                    System.out.println("go1" + response.body().getResponseCode().toString());
                    System.out.println("go2" + response.body());
                    if (response.body().getResponseCode().toString().equals("200"))
                    {
                        dayArrayList.clear();
                        List<Day> dayDetails = sc.getTrain().getDays();
                        for (int i =0; i<dayDetails.size(); i++)
                        {
                            Day d = new Day();
                            d.setDayCode(dayDetails.get(i).getDayCode());
                            d.setRuns(dayDetails.get(i).getRuns());
                            dayArrayList.add(d);
                        }

                        ll2.setVisibility(View.VISIBLE);
                    }
                    scheduleOfTrainAdapter = new ScheduleOfTrainAdapter(getApplicationContext(), dayArrayList);
                    recycler_days.setAdapter(scheduleOfTrainAdapter);

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
            public void onFailure(Call<ScheduleOfTrainByNameOrNumber> call, Throwable t) {
                ll3.setVisibility(View.VISIBLE);
            }
        });
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
            if (scheduleOfTrainAdapter.getItemCount() == 0) {
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
        if (v==get_train_schedule)
        {
            scheduleoftrains();
            InputMethodManager inputMethodManager = (InputMethodManager) ScheduleOfTrain.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ScheduleOfTrain.this.getCurrentFocus().getWindowToken(), 0);
            ll1.setVisibility(View.GONE);
            new  MyTask().execute();
        }
    }

    private void getListOfData() {
        String gettrain = autoCompleteTextView1.getText().toString();
//        Pattern pattern = Pattern.compile(", *");
//        Matcher matcher = pattern.matcher(gettrain);
//
//        String s1, s2;
//
//        if (matcher.find()) {
//            s1 = gettrain.substring(0, matcher.start());
//            s2 = gettrain.substring(matcher.end());
//            System.out.println( "Rock" + gettrain);
//            System.out.println( "Rock" + s1);
//            data1 = s2;
//        }

        JsonObject jsonObject = new JsonObject();
        autoCompleteTrainCall= restInterface.getData(gettrain, "y98sokxj");
        autoCompleteTrainCall.enqueue(new Callback<AutoCompleteTrain>() {
            @Override
            public void onResponse(Call<AutoCompleteTrain> call, Response<AutoCompleteTrain> response) {
                if (response.isSuccessful())
                {
                    AutoCompleteTrain act = response.body();
                    if (response.body().getResponseCode().toString().equals("200"))
                    {
                        train.clear();
                        List<SuggestedTrain> traindetail = act.getTrains();
                        for (int i = 0; i < traindetail.size(); i++)
                        {
                            SuggestedTrain t = new SuggestedTrain();
                            train.add(traindetail.get(i).getName() + "," + traindetail.get(i).getNumber());
                        }
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ScheduleOfTrain.this,android.R.layout.select_dialog_item,train);
                    autoCompleteTextView1.setAdapter(adapter1);
                }
            }

            @Override
            public void onFailure(Call<AutoCompleteTrain> call, Throwable t) {

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

    @Override
    protected void onResume() {
        super.onResume();
        handler1.postDelayed(mShowFullPageAdTask, 45 * 1000);
    }

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
