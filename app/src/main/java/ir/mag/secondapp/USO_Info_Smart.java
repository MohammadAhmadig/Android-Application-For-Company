package ir.mag.secondapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.util.ArrayList;

public class USO_Info_Smart extends AppCompatActivity {

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
        setContentView(R.layout.activity_uso_info_smart);
        String Show_data="";

        image_direction = findViewById(R.id.image_dire);

        TextView myText = (TextView) findViewById(R.id.text_operator_title);
        myText.setText("هوشمندسازی مدارس");

        myText = (TextView) findViewById(R.id.t1);
        myText.setText("58");//myList.get(pos)
        myText = (TextView) findViewById(R.id.t11);
        myText.setText("");
        myText = (TextView) findViewById(R.id.t2);
        myText.setText("36");
        myText = (TextView) findViewById(R.id.t22);
        myText.setText("");
        myText = (TextView) findViewById(R.id.t3);
        myText.setText("42");
        myText = (TextView) findViewById(R.id.t33);
        myText.setText("");
        myText = (TextView) findViewById(R.id.t4);
        myText.setText("19");
        myText = (TextView) findViewById(R.id.t44);
        myText.setText("");
        myText = (TextView) findViewById(R.id.t5);
        myText.setText("6");
        myText = (TextView) findViewById(R.id.t55);
        myText.setText("");
        myText = (TextView) findViewById(R.id.t6);
        myText.setText("4");
        myText = (TextView) findViewById(R.id.t66);
        myText.setText("");
        myText = (TextView) findViewById(R.id.t7);
        myText.setText("5");
        myText = (TextView) findViewById(R.id.t77);
        myText.setText("");

    }
}
