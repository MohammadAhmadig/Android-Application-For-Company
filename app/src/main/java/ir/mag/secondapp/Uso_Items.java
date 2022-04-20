package ir.mag.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import ir.mag.secondapp.Adapter.CustomAdapter_Uso;
import ir.mag.secondapp.Model.Contact;

public class Uso_Items extends AppCompatActivity {
    ListView listView;
    CustomAdapter_Uso adapter;
    BufferedReader reader;
    ArrayList<Contact> contacts = new ArrayList<Contact>();
    private String list[] = {"ایرانسل", "همراه اول", "های وب", "فیبر نوری", "هوشمندسازی مدارس", "توضیحات کلی"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso__items);

        contacts.add(new Contact(list[0], "mtn_item", "", "",""));
        contacts.add(new Contact(list[1], "mci_item", "", "",""));
        contacts.add(new Contact(list[2], "hiweb_item", "", "",""));
        contacts.add(new Contact(list[3], "fibr_item", "", "",""));
        contacts.add(new Contact(list[4], "hooshmand_item", "", "",""));
        contacts.add(new Contact(list[5], "uso_item2", "", "",""));

        listView = (ListView) findViewById(R.id.lv1);

        adapter = new CustomAdapter_Uso(this, contacts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id)
            {
                //CharSequence s_temp = view.getText();/////////////////////////////////////////////////////
                //CharSequence code_abadi = s_temp.subSequence(0, 6);
                TextView v=(TextView) view.findViewById(R.id.contact_txtName);
                //Toast.makeText(getApplicationContext(), "selected Item Name is "+v.getText(), Toast.LENGTH_LONG).show();
                String name = (String) v.getText();//contacts.get(position).getAbadiCode();
                USO_Info_Operator.pos = position;
                if(position == 0 || position == 1 || position == 2){
                    Intent myIntent = new Intent(Uso_Items.this, USO_Info_Operator.class);
                    startActivity(myIntent);
                }
                else if(position == 3){
                    Intent myIntent = new Intent(Uso_Items.this, USO_Info_Fiber.class);
                    startActivity(myIntent);
                }
                else if(position == 4){
                    Intent myIntent = new Intent(Uso_Items.this, USO_Info_Smart.class);
                    startActivity(myIntent);
                }else{
                    Intent myIntent = new Intent(Uso_Items.this, uso_activity.class);
                    startActivity(myIntent);
                }
                //Pishkhaan_Info.pos = position;
                //Pishkhaan_Info.code = (String) name;
                //Pishkhaan_Info.myList = list;

                //((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();


                //Toast.makeText(getApplicationContext(),code_abadi, Toast.LENGTH_SHORT).show();

                //String text = "Item (" + position + ") Is " + contacts.get(position).getName();
                //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();


            }
        });

    }

}
