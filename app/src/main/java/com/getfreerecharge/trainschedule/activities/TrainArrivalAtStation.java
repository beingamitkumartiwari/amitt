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
import com.getfreerecharge.trainschedule.adapters.TrainArrivalStationAdapter;
import com.getfreerecharge.trainschedule.models.stationsuggest.StationSuggList;
import com.getfreerecharge.trainschedule.models.stationsuggest.StationSuggestion;
import com.getfreerecharge.trainschedule.models.trainarrival.Train;
import com.getfreerecharge.trainschedule.models.trainarrival.TrainArrivalStationPojo;
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

public class TrainArrivalAtStation extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    AdRequest adRequest;
    SharedPreference myprefs;
    InterstitialAd interstitialAd;
    private Handler handler1 = new Handler();
    LinearLayout bannerAd;

    BookLoading bookLoading;

    String data1;

    RestInterface restInterface;
    Call<TrainArrivalStationPojo> trainArrivalStationPojoCall;
    ArrayList<Train> trainArrayList;
    TrainArrivalStationAdapter trainArrivalStationAdapter;
    LinearLayout ll1, ll2, ll3, ll4;
    EditText input_name, input_hour;
    TextView get_available_train;
    RecyclerView recycler_arrival_at_station;

    TextView homeactionbar, hometitlequote, hometitlequoteone, errortext;

    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    AutoCompleteTextView autoCompleteTextView1;
    Call<StationSuggestion> stationSuggestionCall;
    List<String> srclist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_arrival_at_station);

        myprefs = new SharedPreference(TrainArrivalAtStation.this);

        connectionCheck();
        adMobFullPageAd();

        restInterface = ServiceGenerator.createService(RestInterface.class);
        trainArrayList = new ArrayList<>();
        trainArrivalStationAdapter = new TrainArrivalStationAdapter(this, trainArrayList);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll4 = (LinearLayout) findViewById(R.id.ll4);

        input_hour = (EditText) findViewById(R.id.input_hour);

        get_available_train = (TextView) findViewById(R.id.get_available_train);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
        get_available_train.setTypeface(tf2);

        recycler_arrival_at_station = (RecyclerView) findViewById(R.id.recycler_arrival_at_station);
        recycler_arrival_at_station.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        get_available_train.setOnClickListener(this);

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
        autoCompleteTextView1.setThreshold(1);
        autoCompleteTextView1.addTextChangedListener(this);
//        getSourceOfData();

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
            if (trainArrivalStationAdapter.getItemCount() == 0) {
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
        if (v == get_available_train) {
            scheduleoftrains();
            InputMethodManager inputMethodManager = (InputMethodManager) TrainArrivalAtStation.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(TrainArrivalAtStation.this.getCurrentFocus().getWindowToken(), 0);
            ll1.setVisibility(View.GONE);
            new MyTask().execute();
        }
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
            System.out.println("Rock" + data1);
        }

        String s3 = input_hour.getText().toString();

        JsonObject jsonObject = new JsonObject();
        trainArrivalStationPojoCall = restInterface.getListOfTrains(data1, s3, "y98sokxj");
        trainArrivalStationPojoCall.enqueue(new Callback<TrainArrivalStationPojo>() {
            @Override
            public void onResponse(Call<TrainArrivalStationPojo> call, Response<TrainArrivalStationPojo> response) {
                if (response.isSuccessful()) {
                    TrainArrivalStationPojo trainArrivalStationPojo = response.body();
                    if (response.body().getResponseCode().toString().equals("200")) {
                        System.out.println("Rock 1" + response.body().getResponseCode().toString());

                        trainArrayList.clear();

                        List<Train> trainDetail = trainArrivalStationPojo.getTrains();

                        System.out.println("Rock 1" + trainDetail.size());

                        for (int i = 0; i < trainDetail.size(); i++) {
                            Train t = new Train();
                            t.setName(trainDetail.get(i).getName());
                            t.setNumber(trainDetail.get(i).getNumber());
                            t.setActarr(trainDetail.get(i).getActarr());
                            t.setActdep(trainDetail.get(i).getActdep());
                            t.setScharr(trainDetail.get(i).getScharr());
                            t.setSchdep(trainDetail.get(i).getSchdep());
                            t.setDelayarr(trainDetail.get(i).getDelayarr());
                            t.setDelaydep(trainDetail.get(i).getDelaydep());
                            trainArrayList.add(t);
                        }
                        ll2.setVisibility(View.VISIBLE);
                    }
                    trainArrivalStationAdapter = new TrainArrivalStationAdapter(getApplicationContext(), trainArrayList);
                    recycler_arrival_at_station.setAdapter(trainArrivalStationAdapter);

                    if (response.body().getResponseCode().toString().equals("204")) {
                        ll3.setVisibility(View.VISIBLE);
                    }
                } else {
                    ll3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<TrainArrivalStationPojo> call, Throwable t) {
                ll3.setVisibility(View.VISIBLE);
            }
        });
    }


    private void getSourceOfData() {

        String gettrain = autoCompleteTextView1.getText().toString();

        JsonObject jsonObject = new JsonObject();
        stationSuggestionCall = restInterface.getStation(gettrain, "y98sokxj");
        stationSuggestionCall.enqueue(new Callback<StationSuggestion>() {
            @Override
            public void onResponse(Call<StationSuggestion> call, Response<StationSuggestion> response) {
                if (response.isSuccessful()) {
                    StationSuggestion act = response.body();
                    if (response.body().getResponseCode().toString().equals("200")) {
                        srclist.clear();
                        List<StationSuggList> traindetail = act.getStation();
                        for (int i = 0; i < traindetail.size(); i++) {
                            SuggestedTrain t = new SuggestedTrain();
                            srclist.add(traindetail.get(i).getFullname() + "," + traindetail.get(i).getCode());
                        }
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(TrainArrivalAtStation.this, android.R.layout.select_dialog_item, srclist);
                    autoCompleteTextView1.setAdapter(adapter1);
                }
            }

            @Override
            public void onFailure(Call<StationSuggestion> call, Throwable t) {

            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        getSourceOfData();
    }

    @Override
    public void afterTextChanged(Editable s) {

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
