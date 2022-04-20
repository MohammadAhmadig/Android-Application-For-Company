package ir.mag.secondapp;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.util.ArrayList;

public class USO_Info_Operator extends AppCompatActivity {

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
        setContentView(R.layout.activity_info_operator);
        String Show_data="";

        image_direction = findViewById(R.id.image_dire);

        if(pos == 0){
            TextView myText = (TextView) findViewById(R.id.text_operator_title);
            myText.setText("ایرانسل");
            myText = (TextView) findViewById(R.id.t1);
            myText.setText("721");//myList.get(pos)
            myText = (TextView) findViewById(R.id.t11);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t2);
            myText.setText("238");
            myText = (TextView) findViewById(R.id.t22);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t3);
            myText.setText("299");
            myText = (TextView) findViewById(R.id.t33);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t4);
            myText.setText("40");
            myText = (TextView) findViewById(R.id.t44);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t5);
            myText.setText("19");
            myText = (TextView) findViewById(R.id.t55);
            myText.setText("47.50%");
            myText = (TextView) findViewById(R.id.t6);
            myText.setText("157");
            myText = (TextView) findViewById(R.id.t66);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t7);
            myText.setText("82");
            myText = (TextView) findViewById(R.id.t77);
            myText.setText("52.23%");
            ImageView imageView = (ImageView) findViewById(R.id.image_map);;
            imageView.setImageResource(R.drawable.mtn_info);
        }
        else if(pos == 1){
            TextView myText = (TextView) findViewById(R.id.text_operator_title);
            myText.setText("همراه اول");
            myText = (TextView) findViewById(R.id.t1);
            myText.setText("721");//myList.get(pos)
            myText = (TextView) findViewById(R.id.t11);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t2);
            myText.setText("396");
            myText = (TextView) findViewById(R.id.t22);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t3);
            myText.setText("319");
            myText = (TextView) findViewById(R.id.t33);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t4);
            myText.setText("31");
            myText = (TextView) findViewById(R.id.t44);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t5);
            myText.setText("5");
            myText = (TextView) findViewById(R.id.t55);
            myText.setText("16.13%");
            myText = (TextView) findViewById(R.id.t6);
            myText.setText("81");
            myText = (TextView) findViewById(R.id.t66);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t7);
            myText.setText("12");
            myText = (TextView) findViewById(R.id.t77);
            myText.setText("14.81%");
            ImageView imageView = (ImageView) findViewById(R.id.image_map);;
            imageView.setImageResource(R.drawable.mci_info);
        }
        else if(pos == 2){
            TextView myText = (TextView) findViewById(R.id.text_operator_title);
            myText.setText("های وب");
            myText = (TextView) findViewById(R.id.t1);
            myText.setText("721");//myList.get(pos)
            myText = (TextView) findViewById(R.id.t11);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t2);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t22);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t3);
            myText.setText("301");
            myText = (TextView) findViewById(R.id.t33);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t4);
            myText.setText("64");
            myText = (TextView) findViewById(R.id.t44);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t5);
            myText.setText("37");
            myText = (TextView) findViewById(R.id.t55);
            myText.setText("57.81%");
            myText = (TextView) findViewById(R.id.t6);
            myText.setText("776");
            myText = (TextView) findViewById(R.id.t66);
            myText.setText("");
            myText = (TextView) findViewById(R.id.t7);
            myText.setText("475");
            myText = (TextView) findViewById(R.id.t77);
            myText.setText("61.21%");
            ImageView imageView = (ImageView) findViewById(R.id.image_map);;
            imageView.setImageResource(R.drawable.hiweb_info);
        }

    }
}
