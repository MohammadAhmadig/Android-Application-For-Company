package ir.mag.secondapp;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.util.ArrayList;

public class USO_Info_Fiber extends AppCompatActivity {

    public static int pos;
    public static String code;
    public static ArrayList<String> myList;
    String[] splited_data;
    ArrayList<String> list1;
    BufferedReader reader;
    ImageView image_direction, image_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso_info_fiber);
        String Show_data="";

        image_direction = findViewById(R.id.image_dire);

        TextView myText = (TextView) findViewById(R.id.text_operator_title);
        myText.setText("فیبر نوری");
        myText = (TextView) findViewById(R.id.t1);
        myText.setText("49");//myList.get(pos)
        myText = (TextView) findViewById(R.id.t11);
        myText.setText("");
        myText = (TextView) findViewById(R.id.t2);
        myText.setText("4.6");
        myText = (TextView) findViewById(R.id.t22);
        myText.setText("9.38 %");
        myText = (TextView) findViewById(R.id.t3);
        myText.setText("514.9");
        myText = (TextView) findViewById(R.id.t33);
        myText.setText("");
        myText = (TextView) findViewById(R.id.t4);
        myText.setText("185.4");
        myText = (TextView) findViewById(R.id.t44);
        myText.setText("36 %");

    }
}
