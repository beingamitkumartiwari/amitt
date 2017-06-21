package com.getfreerecharge.trainschedule.webs;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.victor.loading.book.BookLoading;
import com.victor.loading.rotate.RotateLoading;

import java.util.concurrent.TimeUnit;

public class WebActivity extends AppCompatActivity {

    BookLoading bookLoading;

    LinearLayout ll1, ll2;
    WebView mainWebView;

    TextView homeactionbar, hometitlequote, hometitlequoteone, errortext;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPaththree);
        homeactionbar = (TextView) findViewById(R.id.homeactionbar);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPathone);
        homeactionbar.setTypeface(tf);

        hometitlequoteone = (TextView) findViewById(R.id.hometitlequoteone);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPaththree);
        hometitlequoteone.setTypeface(tf1);

        hometitlequote = (TextView) findViewById(R.id.hometitlequote);
        hometitlequote.setTypeface(tf2);

        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        bookLoading = (BookLoading) findViewById(R.id.bookloading);


        mainWebView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mainWebView.setWebViewClient(new MyCustomWebViewClient());
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        String urll = getIntent().getStringExtra("EXTRA_MESSAGE");
        mainWebView.loadUrl(urll);

        mainWebView.getSettings().setJavaScriptEnabled(true);
        mainWebView.getSettings().setLoadWithOverviewMode(true);
        mainWebView.getSettings().setUseWideViewPort(true);

        mainWebView.getSettings().setSupportZoom(true);
        mainWebView.getSettings().setBuiltInZoomControls(true);
        mainWebView.getSettings().setDisplayZoomControls(false);

        mainWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mainWebView.setScrollbarFadingEnabled(false);
        new MyTask().execute();
    }

    private class MyCustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    private class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ll1.setVisibility(View.VISIBLE);
            ll2.setVisibility(View.GONE);
            bookLoading.start();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ll1.setVisibility(View.GONE);
            ll2.setVisibility(View.VISIBLE);
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


}
