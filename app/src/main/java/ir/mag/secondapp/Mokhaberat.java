package ir.mag.secondapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ir.mag.secondapp.Adapter.CustomAdapter_Mokhaberat;
import ir.mag.secondapp.Model.Contact;

public class Mokhaberat extends AppCompatActivity {
    SearchView searchView;
    public String name;
    ListView listView;
    String[] splited_data2;
    String[] splited_data;
    ArrayList<String> list;
    CustomAdapter_Mokhaberat adapter;
    BufferedReader reader;
    ArrayList<Contact> contacts = new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villages);

        contacts = getTextFileData("/ICT/mokhaberat.txt");

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);

        adapter = new CustomAdapter_Mokhaberat(this, contacts);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*List<String> result = new ArrayList<>();
                int flag=0;
                result.clear();
                for (String strList : list){
                    if (strList.contains(query)){
                        result.add(strList);
                        flag =1;
                    }
                }
                if(flag==1){
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "یافت نشد",Toast.LENGTH_LONG).show();
                }*/
                //List<String> result = list.stream().filter(x -> x.contains(query)).collect(Collectors.toList());
                int flag=0;
                for (String strList : list){
                    if (strList.contains(query)){
                        flag =1;
                        Toast.makeText(Mokhaberat.this, "یافت شد",Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                if(flag==1){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(Mokhaberat.this, "یافت نشد",Toast.LENGTH_LONG).show();
                }
                /*if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "یافت نشد",Toast.LENGTH_LONG).show();
                }*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id)
            {
                //TextView v=(TextView) view.findViewById(R.id.contact_txtAmariCode);
                TextView v=(TextView) view.findViewById(R.id.contact_txtName);
                name = (String) v.getText();//contacts.get(position).getAbadiCode();
                splited_data2 = getTextFileData2("/ICT/mokhaberat.txt");

                // lat , long , abadi name
                String geoUri = "http://maps.google.com/maps?q=loc:" + splited_data2[2].toString() + "," + splited_data2[3].toString() + " (" + splited_data2[1].toString() + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                startActivity(intent);
                //CharSequence s_temp = view.getText();/////////////////////////////////////////////////////
                //CharSequence code_abadi = s_temp.subSequence(0, 6);


                //Toast.makeText(getApplicationContext(),code_abadi, Toast.LENGTH_SHORT).show();

                //String text = "Item (" + position + ") Is " + contacts.get(position).getName();
                //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();


            }
        });

    }
    public ArrayList<Contact> getTextFileData(String fileName) {

        // Get the dir of SD Card
        File sdCardDir = Environment.getExternalStorageDirectory();

        // Get The Text file
        File txtFile = new File(sdCardDir, fileName);

        // Read the file Contents in a StringBuilder Object
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(txtFile));

            list = new ArrayList<String>();
            String line = reader.readLine();
            while(line != null){
                list.add(line);
                String[] splited_data = line.split("\\t");
                contacts.add(new Contact(splited_data[1].toString(), "anten", splited_data[0].toString(), "",""));
                //Log.d("StackOverflow", line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            Log.e("C2c", "Error occured while reading text file!!");

        }

        return contacts;

    }

    public String[] getTextFileData2(String fileName) {

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
                if(line.contains(name)){
                    splited_data = line.split("\\t");
                    String code_temp = splited_data[0].toString();
                    String name_temp = splited_data[2].toString();
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
