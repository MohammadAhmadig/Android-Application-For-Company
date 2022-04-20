package ir.mag.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class InfoActivity extends AppCompatActivity {

    public static int pos;
    public static String code;
    public static ArrayList<String> myList;
    String[] splited_data;
    ArrayList<String> list1;
    BufferedReader reader;
    ImageView image_direction, image_share;
    private String titles[] =
            {"کد آبادی", "شهرستان", "بخش", "دهستان", "آبادی", "خانوار", "جمعیت", "های وب فاز یک",
                    "همت در حال اجرا", "WLL", "کوه نور1032", "تعهدات همراه اول مطابق فایل دریافتی از اپراتور", "تعهدات تلفن ثابت", "ADSL",
                    "MCI_Coverage", "MTN_Coverage", "lat", "long"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        String Show_data="";

        image_share = findViewById(R.id.image_share);
        image_direction = findViewById(R.id.image_dire);

        splited_data = getTextFileData("/ICT/village_info.txt");


        image_direction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // lat , long , abadi name
                    String geoUri = "http://maps.google.com/maps?q=loc:" + splited_data[16].toString() + "," + splited_data[17].toString() + " (" + splited_data[4].toString() + ")";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                    startActivity(intent);
                }
        });

            /*image_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, Arrays.toString(titles) + Arrays.toString(splited_data));
                    //shareIntent.putExtra(Intent.EXTRA_SUBJECT, "The title");
                    startActivity(Intent.createChooser(shareIntent, "اشتراک گذاری با ..."));
                }
            });*/


        TextView myText = (TextView) findViewById(R.id.code);
        myText.setText(splited_data[0].toString());//myList.get(pos)
        myText = (TextView) findViewById(R.id.shahr);
        myText.setText(splited_data[1].toString());
        myText = (TextView) findViewById(R.id.bakhsh);
        myText.setText(splited_data[2].toString());
        myText = (TextView) findViewById(R.id.dehestan);
        myText.setText(splited_data[3].toString());
        myText = (TextView) findViewById(R.id.abadi);
        myText.setText(splited_data[4].toString());
        myText = (TextView) findViewById(R.id.khanevar);
        myText.setText(splited_data[5].toString());
        myText = (TextView) findViewById(R.id.jamiat);
        myText.setText(splited_data[6].toString());
        myText = (TextView) findViewById(R.id.hiweb1);
        myText.setText(splited_data[7].toString());
        myText = (TextView) findViewById(R.id.hemat);
        myText.setText(splited_data[8].toString());
        myText = (TextView) findViewById(R.id.wll);
        myText.setText(splited_data[9].toString());
        myText = (TextView) findViewById(R.id.koohnoor);
        myText.setText(splited_data[10].toString());
        myText = (TextView) findViewById(R.id.taahodhamrah);
        myText.setText(splited_data[11].toString());
        myText = (TextView) findViewById(R.id.taahodsabet);
        myText.setText(splited_data[12].toString());
        myText = (TextView) findViewById(R.id.adsl);
        myText.setText(splited_data[13].toString());
        myText = (TextView) findViewById(R.id.poosheshhamrah);
        myText.setText(splited_data[14].toString());
        myText = (TextView) findViewById(R.id.poosheshirancell);
        myText.setText(splited_data[15].toString());

        myText = (TextView) findViewById(R.id.text_place_title);
        myText.setText(splited_data[4].toString());//abadi name
    }

    public String[] getTextFileData(String fileName) {

        // Get the dir of SD Card
        File sdCardDir = Environment.getExternalStorageDirectory();

        // Get The Text file
        File txtFile = new File(sdCardDir, fileName);

        // Read the file Contents in a StringBuilder Object
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(txtFile));

            String line = reader.readLine();
            while(line != null){
                if(line.contains(code)){
                    splited_data = line.split("\\t");
                    String code_temp = splited_data[0].toString();
                    String name_temp = splited_data[4].toString();
                    break;
                }
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            Log.e("C2c", "Error occured while reading text file!!");

        }

        return splited_data;

    }
}
