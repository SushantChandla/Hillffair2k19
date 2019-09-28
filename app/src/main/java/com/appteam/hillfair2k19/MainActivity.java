package com.appteam.hillfair2k19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.CircularPropagation;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidnetworking.AndroidNetworking;
import com.appteam.fragments.ClubsFragment;
import com.appteam.fragments.Coupons;
import com.appteam.fragments.SponsersFragment;
import com.cloudinary.android.MediaManager;
import com.appteam.fragments.LeaderboardFragment;
import com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout;
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    IResult mResultCallback;
    IResult mResultCallbackAndroidNeworking;

    ImageButton homeButton;
    ImageButton gamesButton;
    ImageButton scheduleButton;
    ImageButton couponButton;

    TextView homeTextView;
    TextView gamesTextView;
    TextView scheduleTextView;
    TextView couponTextView;

    LinearLayout homeLinearLayout;
    LinearLayout gameLinearLayout;
    LinearLayout scheduleLinearLayout;
    LinearLayout couponLinearLayout;

    ParallaxLayerLayout.TranslationUpdater sensorTranslationUpdater;

    public static int home ;
    public static  int games ;
    public static  int schedule ;
    public static  int coupon;

    public static  int currentSelected;
    Button sponsors , club , coreteam , reward;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();

       FragmentManager fragmentManager=getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
        final FaceSmash faceSmash=new FaceSmash();
        LeaderboardFragment leaderboardFragment =new LeaderboardFragment();
// work here to change Activity fragments (add, remove, etc.).  Example here of adding.
//        fragmentTransaction.add (R.id.fragmentHolder, faceSmash);
//        fragmentTransaction.commit ();
        sponsors = findViewById(R.id.sponsors);
        club = findViewById(R.id.club);
        coreteam = findViewById(R.id.core);
        reward = findViewById(R.id.reward);
        sponsors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SponsersFragment sponsersFragment = new SponsersFragment(MainActivity.this);
                fragmentTransaction.add (R.id.fragmentHolder, sponsersFragment);
                fragmentTransaction.commit();
            }
        });
        club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClubsFragment clubsFragment = new ClubsFragment(MainActivity.this);
                fragmentTransaction.add (R.id.fragmentHolder, clubsFragment);
                fragmentTransaction.commit ();
            }
        });
        coreteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coreteam coreteam = new coreteam(MainActivity.this);
                fragmentTransaction.add (R.id.fragmentHolder, coreteam);
                fragmentTransaction.commit ();
            }
        });
        reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Coupons coupons = new Coupons(MainActivity.this);
                fragmentTransaction.add (R.id.fragmentHolder, coupons);
                fragmentTransaction.commit ();
            }
        });

//        ParallaxLayerLayout parallaxLayout=findViewById(R.id.parallaxLayout);
//
//        sensorTranslationUpdater = new SensorTranslationUpdater(this);
//        parallaxLayout.setTranslationUpdater(sensorTranslationUpdater);



//        gameLinearLayout.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryLight), PorterDuff.Mode.SRC_ATOP);
//
//        gameLinearLayout.getBackground().setColorFilter(Color.parseColor("#00FFFFFF"), PorterDuff.Mode.SRC_ATOP);


        home=homeLinearLayout.getMeasuredWidth();
        games=gameLinearLayout.getMeasuredWidth();
        schedule=scheduleLinearLayout.getMeasuredWidth();
        coupon=couponLinearLayout.getMeasuredWidth();


        animate(gameLinearLayout,gamesTextView);
        animate(scheduleLinearLayout,scheduleTextView);
        animate(couponLinearLayout,couponTextView);





        currentSelected=0;















        CircleImageView profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Profile.class));
            }
        });
//        FaceSmash fragment = new FaceSmash();
//        com.appteam.hillfair2k19.fragments.SponsersFragment fragment = new com.appteam.hillfair2k19.fragments.SponsersFragment(this);
////        coreteam fragment = new coreteam(this);




//        fragmentTransaction.add(R.id.fragmentHolder, fragment);
//        fragmentTransaction.commit();

        // For Volley Api Users

        //Initialising
        initVolleyCallback();
        VolleyService mVolleyService = new VolleyService(mResultCallback,this);

        //GET REQUEST SINGLE JSON OBJECT
//        mVolleyService.getJsonObjectDataVolley("GETCALL","https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");

//        GET REQUEST FOR JSON Array
//        mVolleyService.getJsonArrayDataVolley("GETJSONARRAYLIFESAVER","https://lifesaverapp.herokuapp.com/controlpolice");


        // POST REQUEST JSON BODY
//        JSONObject jsonObject = new JSONObject();
//        try {
//    jsonObject.put("latitude","98.0");
//    jsonObject.put("longitude","98.0");
//    jsonObject.put("station","Austrailia");
//    jsonObject.put("mobile","12345678910");
//
//} catch (JSONException e) {
//            e.printStackTrace();
//        }
//        mVolleyService.postJsonDataVolley("POSTJSONDATALIFESAVER","https://lifesaverapp.herokuapp.com/controlpolice",jsonObject);




        //For Android Networking Users

        //Initialising
//        initAndroidNetworkingCallback();
//        AndroidNetworking.initialize(MainActivity.this);
//        com.appteam.hillfair2k19.AndroidNetworking androidNetworking = new com.appteam.hillfair2k19.AndroidNetworking(mResultCallbackAndroidNeworking,this);

//                 GET REQUEST SINGLE JSON OBJECT
//        androidNetworking.getJsonObjectAndroidNetworking("GETCALL","https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");


        //GET REQUEST FOR JSON ARRAY
//        androidNetworking.getJsonArrayAndroidNetworking("GETJSONARRAYFROMLIIFESAVER","https://lifesaverapp.herokuapp.com/controlpolice");


        //POST REQUEST A JSON OBJECT OR BODY
//        JSONObject jsonObject = new JSONObject();
//        try {
//    jsonObject.put("latitude","98.0");
//    jsonObject.put("longitude","98.0");
//    jsonObject.put("station","Hamirpur");
//    jsonObject.put("mobile","1234567890");
//
//} catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        androidNetworking.postJsondataAndroidNetworking("POSTINGJOSNBODYTOLIFESAVER","https://lifesaverapp.herokuapp.com/controlpolice",jsonObject);
    }

    void initVolleyCallback(){
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response , JSONArray jsonArray) {
                if (response != null)
                {
                    //JsonObject
//                    Toast.makeText(MainActivity.this, String.valueOf(response), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //JsonArray
//                    Toast.makeText(MainActivity.this, String.valueOf(jsonArray), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
}
        };

    }
    void initAndroidNetworkingCallback(){
        mResultCallbackAndroidNeworking = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response , JSONArray jsonArray) {
                if (response != null)
                {
                    //JsonObject
//                    Toast.makeText(MainActivity.this, String.valueOf(response), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //JsonArray
//                    Toast.makeText(MainActivity.this, String.valueOf(jsonArray), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {

            }
        };
    }

    public void linkViews(){

        homeButton=findViewById(R.id.homeButton);
        gamesButton=findViewById(R.id.gamesButton);
        scheduleButton=findViewById(R.id.scheduleButton);
        couponButton=findViewById(R.id.couponButton);

        homeTextView=findViewById(R.id.homeTextView);
        gamesTextView=findViewById(R.id.gameTextView);
        scheduleTextView=findViewById(R.id.scheduleTextView);
        couponTextView=findViewById(R.id.couponTextView);

         homeLinearLayout=findViewById(R.id.homeLayout);
       gameLinearLayout=findViewById(R.id.gamesLayout);
        scheduleLinearLayout=findViewById(R.id.scheduleLayout);
       couponLinearLayout=findViewById(R.id.couponsLayout);


    }


    public void handleClick(View view){
        int id=view.getId();
        LinearLayout l;
        switch (id){
            case R.id.homeButton:
            case R.id.homeTextView:

                currentSelected=0;
               l=findViewById(R.id.homeLayout);
                //TODO: load homepage

                animateReverse(l,homeTextView);

                animate(gameLinearLayout,gamesTextView);
                animate(scheduleLinearLayout,scheduleTextView);
                animate(couponLinearLayout,couponTextView);

                Toast.makeText(this,"" +currentSelected,Toast.LENGTH_SHORT).show();


                break;
            case R.id.gamesButton:
            case R.id.gameTextView:
                currentSelected=1;

               l=findViewById(R.id.gamesLayout);
                //TODO: load gamepage

                animateReverse(l,gamesTextView);

                animate(homeLinearLayout,homeTextView);
                animate(scheduleLinearLayout,scheduleTextView);
                animate(couponLinearLayout,couponTextView);


                Toast.makeText(this,"" +currentSelected,Toast.LENGTH_SHORT).show();


                break;
            case R.id.scheduleButton:
            case R.id.scheduleTextView:
                currentSelected=2;

             l=findViewById(R.id.scheduleLayout);
                //TODO: load schedulepage

                animate(gameLinearLayout,gamesTextView);
                animate(homeLinearLayout,homeTextView);
                animate(couponLinearLayout,couponTextView);
                animateReverse(l,scheduleTextView);
                Toast.makeText(this,"" +currentSelected,Toast.LENGTH_SHORT).show();


                break;
            case R.id.couponButton:
            case R.id.couponTextView:
                currentSelected=3;

              l=findViewById(R.id.couponsLayout);
                //TODO: load couponpage

                animate(gameLinearLayout,gamesTextView);
                animate(scheduleLinearLayout,scheduleTextView);
                animate(homeLinearLayout,homeTextView);

                animateReverse(l,couponTextView);
                Toast.makeText(this,"" +currentSelected,Toast.LENGTH_SHORT).show();


                break;

        }


    }


    public void animate(final LinearLayout l,final View txtv){
        txtv.setVisibility(View.GONE);
        ValueAnimator anim = ValueAnimator.ofInt(l.getMeasuredWidth(), 56);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = l.getLayoutParams();
                layoutParams.width = val;
                l.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(200);
        anim.start();

        l.getBackground().setColorFilter(Color.parseColor("#00FFFFFF"), PorterDuff.Mode.SRC_ATOP);




    }

    public void animateReverse(final LinearLayout l,final View txtv){
        txtv.setVisibility(View.VISIBLE);
        int x=l.getId();
        int w;
        if(x==R.id.homeLayout)w=home;
        else if(x==R.id.gamesLayout)w=games;
        else if(x==R.id.scheduleLayout)w=schedule;
        if(x==R.id.couponsLayout)w=coupon;
        else w=schedule;

        l.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryLight), PorterDuff.Mode.SRC_ATOP);
        ValueAnimator anim = ValueAnimator.ofInt(56,300);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = l.getLayoutParams();
                layoutParams.width = val;
                l.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(150);
        anim.start();




    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
