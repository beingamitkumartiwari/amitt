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
import com.getfreerecharge.trainschedule.adapters.SeatAvailabilityAdapter;
import com.getfreerecharge.trainschedule.models.seatavailabilites.Availability;
import com.getfreerecharge.trainschedule.models.seatavailabilites.SeatAvailability;
import com.getfreerecharge.trainschedule.models.stationsuggest.StationSuggList;
import com.getfreerecharge.trainschedule.models.stationsuggest.StationSuggestion;
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

public class SeatAvailabilities extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {


    AdRequest adRequest;
    SharedPreference myprefs;
    InterstitialAd interstitialAd;
    private Handler handler1 = new Handler();
    LinearLayout bannerAd;

    BookLoading bookLoading;

    String data1, data2, data3;
    RestInterface restInterface;
    Call<SeatAvailability> seatAvailabilityCall;
    ArrayList<Availability> availabilityArrayList;
    SeatAvailabilityAdapter seatAvailabilityAdapter;
    LinearLayout ll1, ll2, ll3, ll4;
    EditText input_train_no, input_train_source, input_train_dest, input_train_class, input_train_quota, input_date;
    ImageView date_picker;
    TextView get_seat_availability;
    TextView train_nu, train_name, train_from, train_to, train_class, errortext;
    RecyclerView seat_availability;

    TextView homeactionbar, hometitlequote, hometitlequoteone;

    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    AutoCompleteTextView autoCompleteTextView1, autoCompleteTextView2, autoCompleteTextView3 ;
    Call<StationSuggestion> stationSuggestionCall;
    Call<AutoCompleteTrain> autoCompleteTrainCall;
    List<String> train= new ArrayList<>();
    List<String> srclist= new ArrayList<>();
    List<String> dstlist= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_availability);

        myprefs = new SharedPreference(SeatAvailabilities.this);

        connectionCheck();
        adMobFullPageAd();

        initViews();
        restInterface = ServiceGenerator.createService(RestInterface.class);
        availabilityArrayList = new ArrayList<>();
        seatAvailabilityAdapter = new SeatAvailabilityAdapter(this, availabilityArrayList);

        autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        autoCompleteTextView1.setThreshold(4);
        autoCompleteTextView1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getTrainOfData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getTrainOfData();

        autoCompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        autoCompleteTextView2.setThreshold(2);
        autoCompleteTextView2.addTextChangedListener(new TextWatcher() {
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

        autoCompleteTextView3 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView3);
        autoCompleteTextView3.setThreshold(2);
        autoCompleteTextView3.addTextChangedListener(new TextWatcher() {
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

//        autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
//        autoCompleteTextView1.setThreshold(1);
//        autoCompleteTextView1.addTextChangedListener(this);
//        getTrainOfData();
//
//        autoCompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
//        autoCompleteTextView2.setThreshold(1);
//        autoCompleteTextView2.addTextChangedListener(this);
//        getSourceOfData();
//
//        autoCompleteTextView3 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView3);
//        autoCompleteTextView3.setThreshold(1);
//        autoCompleteTextView3.addTextChangedListener(this);
//        getDestOfData();

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
            if (seatAvailabilityAdapter.getItemCount() == 0) {
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


    private void initViews() {
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll4 = (LinearLayout) findViewById(R.id.ll4);

        input_train_class = (EditText) findViewById(R.id.input_train_class);
        input_train_quota = (EditText) findViewById(R.id.input_train_quota);
        input_date = (EditText) findViewById(R.id.input_date);
        date_picker = (ImageView) findViewById(R.id.date_picker);

        get_seat_availability = (TextView) findViewById(R.id.get_seat_availability);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
        get_seat_availability.setTypeface(tf2);

        train_nu = (TextView) findViewById(R.id.train_nu);
        train_name = (TextView) findViewById(R.id.train_name);
        train_from = (TextView) findViewById(R.id.train_from);
        train_to = (TextView) findViewById(R.id.train_to);
        train_class = (TextView) findViewById(R.id.train_class);
        seat_availability = (RecyclerView) findViewById(R.id.seat_availability);
        seat_availability.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        get_seat_availability.setOnClickListener(this);
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

    private void getSeatAvailability() {
        String gettrain = autoCompleteTextView1.getText().toString();
        String getsource = autoCompleteTextView2.getText().toString();
        String getdest = autoCompleteTextView3.getText().toString();
        String getclass = input_train_class.getText().toString();
        String getquota = input_train_quota.getText().toString();
        String getdate = input_date.getText().toString();


        Pattern pattern = Pattern.compile(", *");
        Matcher matcher = pattern.matcher(gettrain);

        String s1, s2, s3, s4, s5, s6;

        if (matcher.find()) {
            s1 = gettrain.substring(0, matcher.start());
            s2 = gettrain.substring(matcher.end());
            System.out.println( "Rock" + gettrain);
            System.out.println( "Rock" + s2);
            data1 = s2;
        }


        Pattern pattern1 = Pattern.compile(", *");
        Matcher matcher1 = pattern1.matcher(getsource);

        if (matcher1.find()) {
            s3 = getsource.substring(0, matcher1.start());
            s4 = getsource.substring(matcher1.end());
            System.out.println( "Rock" + getsource);
            System.out.println( "Rock" + s4);
            data2 = s4;
        }

        Pattern pattern2 = Pattern.compile(", *");
        Matcher matcher2 = pattern2.matcher(getdest);

        if (matcher2.find()) {
            s5 = getdest.substring(0, matcher2.start());
            s6 = getdest.substring(matcher2.end());
            System.out.println( "Rock" + getdest);
            System.out.println( "Rock" + s6);
            data3 = s6;
        }
        System.out.println( "Rock" + getdate);
        System.out.println( "Rock" + getclass);
        System.out.println( "Rock" + getquota);
        JsonObject jsonObject = new JsonObject();
        seatAvailabilityCall = restInterface.getSeatAvailability(data1,
                data2, data3, getdate, getclass, getquota, "y98sokxj");
        seatAvailabilityCall.enqueue(new Callback<SeatAvailability>() {
            @Override
            public void onResponse(Call<SeatAvailability> call, Response<SeatAvailability> response) {
                if (response.isSuccessful())
                {
                    SeatAvailability sa = response.body();
                    if (response.body().getResponseCode().toString().equals("200"))
                    {

                        train_nu.setText(response.body().getTrainNumber().toString());
                        train_name.setText(response.body().getTrainName().toString());
                        train_from.setText(response.body().getFrom().getName().toString());
                        train_to.setText(response.body().getTo().getName().toString());
                        train_class.setText(response.body().getClass_().getClassName().toString());
                        availabilityArrayList.clear();
                        List<Availability> availabilities = sa.getAvailability();
                        for (int i = 0; i < availabilities.size(); i++)
                        {
                            Availability a = new Availability();
                            a.setDate(availabilities.get(i).getDate());
                            a.setStatus(availabilities.get(i).getStatus());
                            availabilityArrayList.add(a);
                        }
                        ll2.setVisibility(View.VISIBLE);
                    }
                    seatAvailabilityAdapter = new SeatAvailabilityAdapter(getApplicationContext(), availabilityArrayList);
                    seat_availability.setAdapter(seatAvailabilityAdapter);

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
            public void onFailure(Call<SeatAvailability> call, Throwable t) {
                ll3.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == get_seat_availability)
        {
            getSeatAvailability();
            ll1.setVisibility(View.GONE);
            new MyTask().execute();
        }

        if (v == date_picker)
        {
            getCallenderDate();
        }
    }

    private void getCallenderDate() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(SeatAvailabilities.this,
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

    private void getTrainOfData() {
        String text = autoCompleteTextView1.getText().toString();
        JsonObject jsonObject = new JsonObject();
        autoCompleteTrainCall= restInterface.getData(text, "y98sokxj");
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
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SeatAvailabilities.this,android.R.layout.select_dialog_item,train);
                    autoCompleteTextView1.setAdapter(adapter1);
                }
            }

            @Override
            public void onFailure(Call<AutoCompleteTrain> call, Throwable t) {

            }
        });
    }


    private void getSourceOfData() {
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
                        srclist.clear();
                        List<StationSuggList> traindetail = act.getStation();
                        for (int i = 0; i < traindetail.size(); i++)
                        {
                            SuggestedTrain t = new SuggestedTrain();
                            srclist.add(traindetail.get(i).getFullname() + "," + traindetail.get(i).getCode());
                        }
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SeatAvailabilities.this,android.R.layout.select_dialog_item,srclist);
                    autoCompleteTextView2.setAdapter(adapter1);
                }
            }

            @Override
            public void onFailure(Call<StationSuggestion> call, Throwable t) {

            }
        });
    }

    private void getDestOfData() {
        String text = autoCompleteTextView3.getText().toString();
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
                        dstlist.clear();
                        List<StationSuggList> traindetail = act.getStation();
                        for (int i = 0; i < traindetail.size(); i++)
                        {
                            SuggestedTrain t = new SuggestedTrain();
                            dstlist.add(traindetail.get(i).getFullname() + "," + traindetail.get(i).getCode());
                        }
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SeatAvailabilities.this,android.R.layout.select_dialog_item,dstlist);
                    autoCompleteTextView3.setAdapter(adapter1);
                }
            }

            @Override
            public void onFailure(Call<StationSuggestion> call, Throwable t) {

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



//
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        getSourceOfData();
//        getTrainOfData();
//        getDestOfData();
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//
//    }
}
