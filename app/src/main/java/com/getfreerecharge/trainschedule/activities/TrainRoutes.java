package com.getfreerecharge.trainschedule.activities;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.adapters.TrainRouteAdapter;
import com.getfreerecharge.trainschedule.adapters.TrainRouteClassAdapter;
import com.getfreerecharge.trainschedule.adapters.TrainRouteDayAdapter;
import com.getfreerecharge.trainschedule.models.trainroutes.Class;
import com.getfreerecharge.trainschedule.models.trainroutes.Day;
import com.getfreerecharge.trainschedule.models.trainroutes.Route;
import com.getfreerecharge.trainschedule.models.trainroutes.Train;
import com.getfreerecharge.trainschedule.models.trainroutes.TrainRoute;
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
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainRoutes extends AppCompatActivity implements View.OnClickListener {

    AdRequest adRequest;
    SharedPreference myprefs;
    InterstitialAd interstitialAd;
    private Handler handler1 = new Handler();
    LinearLayout bannerAd;

    TextView get_train_route;
    BookLoading bookLoading;

    String data1;

    RestInterface restInterface;
    Call<TrainRoute> trainRouteCall;
    ArrayList<Route> routeList;
    ArrayList<Day> dayList;
    ArrayList<Class> classList;

    AutoCompleteTextView autoCompleteTextView1;
    Call<AutoCompleteTrain> autoCompleteTrainCall;
    List<String> train = new ArrayList<>();

    TrainRouteAdapter trainRouteAdapter;
    TrainRouteClassAdapter trainRouteClassAdapter;
    TrainRouteDayAdapter trainRouteDayAdapter;

    RecyclerView recycler_days, recycler_class, recycler_route;
    LinearLayout ll1, ll2, ll3, ll4;

    TextView homeactionbar, hometitlequote, hometitlequoteone, errortext;

    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_route);

        myprefs = new SharedPreference(TrainRoutes.this);

        connectionCheck();
        adMobFullPageAd();

        restInterface = ServiceGenerator.createService(RestInterface.class);
        routeList = new ArrayList<>();
        dayList = new ArrayList<>();
        classList = new ArrayList<>();

        trainRouteAdapter = new TrainRouteAdapter(this, routeList);
        trainRouteClassAdapter = new TrainRouteClassAdapter(this, classList);
        trainRouteDayAdapter = new TrainRouteDayAdapter(this, dayList);
        get_train_route = (TextView) findViewById(R.id.get_train_route);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
        get_train_route.setTypeface(tf2);
        get_train_route.setOnClickListener(this);
        bookLoading = (BookLoading) findViewById(R.id.bookloading);
        ininViews();
        recycler_route.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler_class.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycler_days.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

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


    }



    private void ininViews() {

        recycler_days = (RecyclerView) findViewById(R.id.recycler_days);
        recycler_class = (RecyclerView) findViewById(R.id.recycler_class);
        recycler_route = (RecyclerView) findViewById(R.id.recycler_route);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll4 = (LinearLayout) findViewById(R.id.ll4);

        homeactionbar = (TextView) findViewById(R.id.homeactionbar);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPathone);
        homeactionbar.setTypeface(tf);

//        hometitlequoteone = (TextView) findViewById(R.id.hometitlequoteone);
//        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPaththree);
//        hometitlequoteone.setTypeface(tf1);

        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
//        hometitlequote = (TextView) findViewById(R.id.hometitlequote);
//        hometitlequote.setTypeface(tf2);

        errortext = (TextView) findViewById(R.id.errortext);
        errortext.setTypeface(tf2);
    }

    @Override
    public void onClick(View v) {
        if (v == get_train_route) {
            tainRoutes();
            InputMethodManager inputMethodManager = (InputMethodManager) TrainRoutes.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(TrainRoutes.this.getCurrentFocus().getWindowToken(), 0);
            ll1.setVisibility(View.GONE);
            new MyTask().execute();
        }
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
            if (trainRouteDayAdapter.getItemCount() == 0) {
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


    private void tainRoutes() {

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


        String train_no = autoCompleteTextView1.getText().toString();

        JsonObject jsonObject = new JsonObject();
        trainRouteCall = restInterface.getTrainRoute(data1, "y98sokxj");
        trainRouteCall.enqueue(new Callback<TrainRoute>() {
            @Override
            public void onResponse(Call<TrainRoute> call, Response<TrainRoute> response) {

                if (response.isSuccessful()) {
                    TrainRoute trainRoute = response.body();
//                    Train train = new Train();
                    System.out.println("go1" + response.body().getResponseCode().toString());
                    System.out.println("go2" + response.body().getTrain());
                    if (response.body().getResponseCode().toString().equals("200")) {
                        routeList.clear();
                        List<Route> routeDetail = trainRoute.getRoute();
                        List<Class> classDetail = trainRoute.getTrain().getClasses();
                        List<Day> dayDetail = trainRoute.getTrain().getDays();
                        for (int i = 0; i < routeDetail.size(); i++) {
                            Route route = new Route();
                            route.setFullname(routeDetail.get(i).getFullname());
                            route.setCode(routeDetail.get(i).getCode());
                            route.setScharr(routeDetail.get(i).getScharr());
                            route.setSchdep(routeDetail.get(i).getSchdep());
                            route.setHalt(routeDetail.get(i).getHalt());
                            route.setNo(routeDetail.get(i).getNo());
                            route.setDistance(routeDetail.get(i).getDistance());

                            routeList.add(route);
                        }

                        for (int i = 0; i < classDetail.size(); i++) {
                            Class cl = new Class();
                            cl.setClassCode(classDetail.get(i).getClassCode());
                            cl.setAvailable(classDetail.get(i).getAvailable());
                            classList.add(cl);
                        }

                        for (int i = 0; i < dayDetail.size(); i++) {
                            Day d = new Day();
                            d.setDayCode(dayDetail.get(i).getDayCode());
                            d.setRuns(dayDetail.get(i).getRuns());
                            dayList.add(d);
                        }

                        ll2.setVisibility(View.VISIBLE);


                    }

                    trainRouteAdapter = new TrainRouteAdapter(getApplicationContext(), routeList);
                    recycler_route.setAdapter(trainRouteAdapter);

                    trainRouteClassAdapter = new TrainRouteClassAdapter(getApplicationContext(), classList);
                    recycler_class.setAdapter(trainRouteClassAdapter);

                    trainRouteDayAdapter = new TrainRouteDayAdapter(getApplicationContext(), dayList);
                    recycler_days.setAdapter(trainRouteDayAdapter);

                    if (response.body().getResponseCode().toString().equals("204")) {
                        ll3.setVisibility(View.VISIBLE);
                    }
                } else {
                    ll3.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<TrainRoute> call, Throwable t) {
                ll3.setVisibility(View.VISIBLE);
            }
        });

    }

    private void getListOfData() {
        String text = autoCompleteTextView1.getText().toString();
        JsonObject jsonObject = new JsonObject();
        autoCompleteTrainCall = restInterface.getData(text, "y98sokxj");
        autoCompleteTrainCall.enqueue(new Callback<AutoCompleteTrain>() {
            @Override
            public void onResponse(Call<AutoCompleteTrain> call, Response<AutoCompleteTrain> response) {
                if (response.isSuccessful()) {
                    AutoCompleteTrain act = response.body();
                    if (response.body().getResponseCode().toString().equals("200")) {
                        train.clear();
                        List<SuggestedTrain> traindetail = act.getTrains();
                        for (int i = 0; i < traindetail.size(); i++) {
                            SuggestedTrain t = new SuggestedTrain();
                            train.add(traindetail.get(i).getName() + "," + traindetail.get(i).getNumber());
                        }
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(TrainRoutes.this, android.R.layout.select_dialog_item, train);
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
