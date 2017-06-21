package com.getfreerecharge.trainschedule.activities;

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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.adapters.TrainBetweenStationAdapter;
import com.getfreerecharge.trainschedule.models.stationsuggest.StationSuggList;
import com.getfreerecharge.trainschedule.models.stationsuggest.StationSuggestion;
import com.getfreerecharge.trainschedule.models.trainbetweenstation.From;
import com.getfreerecharge.trainschedule.models.trainbetweenstation.Train;
import com.getfreerecharge.trainschedule.models.trainbetweenstation.TrainBetweenStation;
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
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainsBetweenStation extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    AdRequest adRequest;
    SharedPreference myprefs;
    InterstitialAd interstitialAd;
    private Handler handler1 = new Handler();
    LinearLayout bannerAd;

    BookLoading bookLoading;

    String data1, data2;

    RestInterface restInterface;
    Call<TrainBetweenStation> trainBetweenStationCall;
    ArrayList<Train> trainArrayList;
    TrainBetweenStationAdapter trainBetweenStationAdapter;
    LinearLayout ll1, ll2, ll3, ll4;
    RecyclerView train_between_station;
    ImageView date_picker;
    EditText input_date, input_train_dest, input_train_source;
    TextView get_seat_availability;

    TextView homeactionbar, hometitlequote, hometitlequoteone, errortext;

    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    AutoCompleteTextView autoCompleteTextView1, autoCompleteTextView2;
    Call<StationSuggestion> stationSuggestionCall;
    List<String> train= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains_between_station);

        myprefs = new SharedPreference(TrainsBetweenStation.this);

        connectionCheck();
        adMobFullPageAd();

        initView();
        restInterface = ServiceGenerator.createService(RestInterface.class);
        trainArrayList = new ArrayList<>();
        trainBetweenStationAdapter = new TrainBetweenStationAdapter(this, trainArrayList);

        autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        autoCompleteTextView1.setThreshold(2);
        autoCompleteTextView1.addTextChangedListener(new TextWatcher() {
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
        });
        getSourceOfData();

        autoCompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        autoCompleteTextView2.setThreshold(2);
        autoCompleteTextView2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getDestOfData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getDestOfData();

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
            if (trainBetweenStationAdapter.getItemCount() == 0) {
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



    private void initView() {
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll4 = (LinearLayout) findViewById(R.id.ll4);

        train_between_station = (RecyclerView) findViewById(R.id.train_between_station);
        train_between_station.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        date_picker = (ImageView) findViewById(R.id.date_picker);
        input_date = (EditText) findViewById(R.id.input_date);
        get_seat_availability = (TextView) findViewById(R.id.get_seat_availability);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
        get_seat_availability.setTypeface(tf2);

        date_picker.setOnClickListener(this);
        get_seat_availability.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if (v == get_seat_availability)
        {
            gettrainBetweenStation();
            ll1.setVisibility(View.GONE);
            new MyTask().execute();
        }

        if (v == date_picker)
        {
            getCallenderDate();
        }
    }

    private void getSourceOfData() {
        String text = autoCompleteTextView1.getText().toString();
        JsonObject jsonObject = new JsonObject();
        stationSuggestionCall= restInterface.getStation(text, "y98sokxj");
        stationSuggestionCall.enqueue(new Callback<StationSuggestion>() {
            @Override
            public void onResponse(Call<StationSuggestion> call, Response<StationSuggestion> response) {
                if (response.isSuccessful())
                {
                    StationSuggestion act = response.body();
                    if (response.body().getResponseCode().toString().equals("200"))
                    {
                        train.clear();
                        List<StationSuggList> traindetail = act.getStation();
                        for (int i = 0; i < traindetail.size(); i++)
                        {
                            SuggestedTrain t = new SuggestedTrain();
                            train.add(traindetail.get(i).getFullname() + "," + traindetail.get(i).getCode());
                        }
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(TrainsBetweenStation.this,android.R.layout.select_dialog_item,train);
                    autoCompleteTextView1.setAdapter(adapter1);
                }
            }

            @Override
            public void onFailure(Call<StationSuggestion> call, Throwable t) {

            }
        });
    }

    private void getDestOfData() {
        String text = autoCompleteTextView2.getText().toString();
        JsonObject jsonObject = new JsonObject();
        stationSuggestionCall= restInterface.getStation(text, "y98sokxj");
        stationSuggestionCall.enqueue(new Callback<StationSuggestion>() {
            @Override
            public void onResponse(Call<StationSuggestion> call, Response<StationSuggestion> response) {
                if (response.isSuccessful())
                {
                    StationSuggestion act = response.body();
                    if (response.body().getResponseCode().toString().equals("200"))
                    {
                        train.clear();
                        List<StationSuggList> traindetail = act.getStation();
                        for (int i = 0; i < traindetail.size(); i++)
                        {
                            SuggestedTrain t = new SuggestedTrain();
                            train.add(traindetail.get(i).getFullname() + "," + traindetail.get(i).getCode());
                        }
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(TrainsBetweenStation.this,android.R.layout.select_dialog_item,train);
                    autoCompleteTextView2.setAdapter(adapter1);
                }
            }

            @Override
            public void onFailure(Call<StationSuggestion> call, Throwable t) {

            }
        });
    }



    private void gettrainBetweenStation() {

        String src = autoCompleteTextView1.getText().toString();
        String dest = autoCompleteTextView2.getText().toString();
        String date = input_date.getText().toString();

        String  s3, s4, s5, s6;


        Pattern pattern1 = Pattern.compile(", *");
        Matcher matcher1 = pattern1.matcher(src);

        if (matcher1.find()) {
            s3 = src.substring(0, matcher1.start());
            s4 = src.substring(matcher1.end());
            System.out.println( "Rock" + src);
            System.out.println( "Rock" + s3);
            data1 = s4;
        }

        Pattern pattern2 = Pattern.compile(", *");
        Matcher matcher2 = pattern2.matcher(dest);

        if (matcher2.find()) {
            s5 = dest.substring(0, matcher2.start());
            s6 = dest.substring(matcher2.end());
            System.out.println( "Rock" + dest);
            System.out.println( "Rock" + s5);
            data2 = s6;
        }

        JsonObject jsonObject = new JsonObject();
        trainBetweenStationCall = restInterface.getTrainBetweenStation(data1, data2, date,"y98sokxj");
        trainBetweenStationCall.enqueue(new Callback<TrainBetweenStation>() {
            @Override
            public void onResponse(Call<TrainBetweenStation> call, Response<TrainBetweenStation> response) {
                if (response.isSuccessful())
                {
                    TrainBetweenStation tbs = response.body();
                    if (response.body().getResponseCode().toString().equals("200"))
                    {
                        trainArrayList.clear();;
                        List<Train> trains = tbs.getTrain();
                        for (int i = 0; i < trains.size(); i++)
                        {
                            Train t = new Train();
                            From f = new From();
                            t.setNumber(trains.get(i).getNumber());
                            t.setName(trains.get(i).getName());
                            t.setFrom(trains.get(i).getFrom());
                            t.setSrcDepartureTime(trains.get(i).getSrcDepartureTime());
                            trainArrayList.add(t);
                        }
                        ll2.setVisibility(View.VISIBLE);
                    }
                    trainBetweenStationAdapter = new TrainBetweenStationAdapter(getApplicationContext(), trainArrayList);
                    train_between_station.setAdapter(trainBetweenStationAdapter);

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
            public void onFailure(Call<TrainBetweenStation> call, Throwable t) {
                ll3.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getCallenderDate() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(TrainsBetweenStation.this,
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
        String date = dayOfMonth + "-" + (++monthOfYear);
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
