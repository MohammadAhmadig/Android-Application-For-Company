package ir.mag.secondapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    public Activity context = this;
    private SharedPrefence sharedPreference2;
    private String text2="";
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreference2 = new SharedPrefence();

        text2 = sharedPreference2.getValue(context);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if( text2 == null ){
                    Intent intent=new Intent(SplashActivity.this,Sign_in.class);
                    startActivity(intent);
                } else{
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }

                finish();
            }
        },2000);

    }
}