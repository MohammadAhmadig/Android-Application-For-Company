package ir.mag.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sign_in extends AppCompatActivity {

    private Context controller;
    String strEmail, strPassword;
    public Activity context = this;
    EditText edtEmail;
    Button btnSingIn;
    EditText edtPassword;
    BufferedReader reader;
    ArrayList<String> list;
    public static int ID;
    int welcome=0;
    private SharedPrefence sharedPreference;
    private String text="alakipalaki5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtEmail = findViewById(R.id.editText_email_login_activity);
        edtPassword = findViewById(R.id.editText_password_login_activity);
        btnSingIn = findViewById(R.id.button_login_activity);

        sharedPreference = new SharedPrefence();
        final SplashActivity objectSplash = new SplashActivity();

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = edtEmail.getText().toString();
                strPassword = edtPassword.getText().toString();

                try{
                    final InputStream file = getAssets().open("identification.txt");
                    reader = new BufferedReader(new InputStreamReader(file));
                    list = new ArrayList<String>();
                    String line = reader.readLine();
                    while(line != null){
                        list.add(line);
                        String[] splited_data = line.split("\\t");
                        if(splited_data[0].toString().equals(strEmail) && splited_data[1].toString().equals(strPassword)){
                            ID = Integer.parseInt(splited_data[2].toString());
                            welcome=1;
                            break;
                        }
                        //Log.d("StackOverflow", line);
                        line = reader.readLine();
                    }
                } catch(IOException ioe){
                    ioe.printStackTrace();
                }

                if(welcome==1){
                    //Toast.makeText(Sign_in.this, ID, Toast.LENGTH_LONG).show();
                    Toast.makeText(Sign_in.this, "خوش آمدید", Toast.LENGTH_LONG).show();
                    welcome=0;
                    sharedPreference.save(context, Integer.toString(ID));////////////////////////////
                    Intent intent = new Intent(Sign_in.this, MainActivity.class);
                    startActivity(intent);

                } else{
                    Toast.makeText(Sign_in.this, "نام کاربری یا رمز عبور اشتباه است", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

}
