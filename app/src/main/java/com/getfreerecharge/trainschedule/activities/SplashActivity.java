package com.getfreerecharge.trainschedule.activities;

import java.util.ArrayList;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.skyfishjy.library.RippleBackground;


public class SplashActivity extends AppCompatActivity {
    private ImageView foundDevice;
    TextView quote, intro, name;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        Typeface tf = Typeface.createFromAsset(getAssets(), fontPathone);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPaththree);

        quote = (TextView) findViewById(R.id.quote);
        intro = (TextView) findViewById(R.id.intro);
        name = (TextView) findViewById(R.id.name);
        quote.setTypeface(tf1);
        intro.setTypeface(tf1);
        name.setTypeface(tf1);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, HomeScreen.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

//        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
//
//        final Handler handler=new Handler();
//
////        foundDevice=(ImageView)findViewById(R.id.foundDevice);
//        ImageView button=(ImageView)findViewById(R.id.centerImage);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rippleBackground.startRippleAnimation();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        foundDevice();
//                    }
//                },3000);
//            }
//        });
//    }
//
//    private void foundDevice(){
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setDuration(400);
//        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
//        ArrayList<Animator> animatorList=new ArrayList<Animator>();
//        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(foundDevice, "ScaleX", 0f, 1.2f, 1f);
//        animatorList.add(scaleXAnimator);
//        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(foundDevice, "ScaleY", 0f, 1.2f, 1f);
//        animatorList.add(scaleYAnimator);
//        animatorSet.playTogether(animatorList);
////        foundDevice.setVisibility(View.VISIBLE);
//        animatorSet.start();
//    }
    }
}
