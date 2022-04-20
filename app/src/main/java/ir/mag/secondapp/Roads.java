package ir.mag.secondapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.gms.maps.GoogleMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import ir.mag.secondapp.Adapter.CustomAdapter_Roads;
import ir.mag.secondapp.Model.Contact;


public class Roads extends AppCompatActivity {
    public static android.content.res.Resources res;
    private Context controller;
    public String KML_Name;
    public static int kmlID;
    private String KML_PATH_NAME = Environment.getExternalStorageDirectory().toString() + "/ICT/"+ KML_Name +".kml";
    GoogleMap mMap;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    CustomAdapter_Roads adapter;
    String[] splited_data;
    ArrayList<String> list1;
    BufferedReader reader;
    ArrayList<Contact> contacts = new ArrayList<Contact>();
    String wantPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private Context context;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Context baseContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roads);

        contacts = getTextFileData("/ICT/roads.txt");

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);

        adapter = new CustomAdapter_Roads(this, contacts);
        listView.setAdapter(adapter);

        res = getResources();

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
                        Toast.makeText(Roads.this, "یافت شد",Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                if(flag==1){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(Roads.this, "یافت نشد",Toast.LENGTH_LONG).show();
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

    }

    /*public void open_file(File file) {
        //File path = new File(getFilesDir(), "dl");
        //File file = new File(path, filename);

        // Get URI and MIME type of file
        //Uri uri = FileProvider.getUriForFile(this,getPackageName() + ".kmz", file);
        //String mime = getContentResolver().getType(uri);

        // Open file with user selected app
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/kmz");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }*/
    /*private void initShareIntent(String type) throws IOException {
        // Find the SD Card path
        File file = getAssets().open("99.kmz");
        Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + R..myPdfName);

        //Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_STREAM, outputFileUri);
        startActivity(Intent.createChooser(intent, (String) getText(R.string.share)));
    }*/



    /*private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }

    }*/


    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
    private boolean checkPermission(String permission){
        if (Build.VERSION.SDK_INT >= 23) {
            int result = ContextCompat.checkSelfPermission(context, permission);
            if (result == PackageManager.PERMISSION_GRANTED){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private void requestPermission(String permission){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)){
            Toast.makeText(context, "Write external storage permission allows us to write data. Please allow in App Settings for additional functionality.",Toast.LENGTH_LONG).show();
        }
        ActivityCompat.requestPermissions(activity, new String[]{permission},PERMISSION_REQUEST_CODE);
    }


    public void makeFile(int s){
        byte[] buffer=null;
        int size=0;

        String filename= KML_Name +".kml";

        File filepath = Environment.getExternalStorageDirectory();

        // Create a new folder in SD Card
        File dir = new File(filepath.getAbsolutePath()
                + "/ICT/");
        boolean exists = dir.exists();
        if (!exists){dir.mkdirs();}

        /*File dir2 = new File(filepath.getAbsolutePath()
                + "/Dubsmash_IRANI/Temp_Files/");
        boolean exists2 = dir2.exists();
        if (!exists2){dir2.mkdirs();}*/

        File file2 = new File(dir , filename);


        InputStream fIn = res.openRawResource(s);

        try {
            size = fIn.available();
            buffer = new byte[size];
            fIn.read(buffer);
            fIn.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

        if (!file2.exists()){
            FileOutputStream save;
            try {
                save = new FileOutputStream(filepath.getAbsolutePath() + "/ICT/" + filename);
                save.write(buffer);
                save.flush();
                save.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }
    }

    public int FindRoad(TextView v1, TextView v2) {
        String Show_data="";
        int ID=-1;
        try {
            InputStream file = controller.getAssets().open("roads.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            list1 = new ArrayList<String>();
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(v1.getText()) && line.contains(v2.getText())) {
                    splited_data = line.split("\\t");
                    ID = Integer.parseInt(splited_data[0]);
                    ID = ID - 1;//index from 0
                    return ID;
                }
                Log.d("StackOverflow", line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ID;
    }

    public void ShowImage( int position, View view) {
        int pic;
        if(position==0){
            pic = R.drawable.j1;
        }else if(position==1){
            pic = R.drawable.j1;
        }else if(position==2){
            pic = R.drawable.j2;
        }else if(position==3){
            pic = R.drawable.j2;
        }else if(position==4){
            pic = R.drawable.j3;
        }else if(position==5){
            pic = R.drawable.j3;
        }else if(position==6){
            pic = R.drawable.j4;
        }else if(position==7){
            pic = R.drawable.j4;
        }else if(position==8){
            pic = R.drawable.j5;
        }else if(position==9){
            pic = R.drawable.j5;
        }else if(position==10){
            pic = R.drawable.j6;
        }else if(position==11){
            pic = R.drawable.j6;
        }else if(position==12){
            pic = R.drawable.j7;
        }else if(position==13){
            pic = R.drawable.j7;
        }else if(position==14){
            pic = R.drawable.j8;
        }else if(position==15){
            pic = R.drawable.j8;
        }else if(position==16){
            pic = R.drawable.j9;
        }else if(position==17){
            pic = R.drawable.j9;
        }else if(position==18){
            pic = R.drawable.j10;
        }else if(position==19){
            pic = R.drawable.j10;
        }else if(position==20){
            pic = R.drawable.j11;
        }else if(position==21){
            pic = R.drawable.j11;
        }else if(position==22){
            pic = R.drawable.j12;
        }else if(position==23){
            pic = R.drawable.j12;
        }else if(position==24){
            pic = R.drawable.j13;
        }else if(position==25){
            pic = R.drawable.j13;
        }else if(position==26){
            pic = R.drawable.j14;
        }else if(position==27){
            pic = R.drawable.j14;
        }else if(position==28){
            pic = R.drawable.j15;
        }else if(position==29){
            pic = R.drawable.j15;
        }else if(position==30){
            pic = R.drawable.j16;
        }else if(position==31){
            pic = R.drawable.j16;
        }else{
            pic = R.drawable.j17;
        }
        //ForToast(pic);
        showpopup(pic , view);
    }

    public void Earth( int position) {
        //TextView v = (TextView) view.findViewById(R.id.contact_txtName);
        //Toast.makeText(getApplicationContext(), "selected Item Name is "+v.getText(), Toast.LENGTH_LONG).show();
        //String jadeh = (String) v.getText();//contacts.get(position).getAbadiCode();
        //Intent myIntent = new Intent(Villages.this, InfoActivity.class);
        //String pos = Integer.toString(position);
        //((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        if(position==0){
            kmlID = R.raw.e1;
            KML_Name = "e1";
        }else if(position==1){
            kmlID = R.raw.e2;
            KML_Name = "e2";
        }else if(position==2){
            kmlID = R.raw.e3;
            KML_Name = "e3";
        }else if(position==3){
            kmlID = R.raw.e4;
            KML_Name = "e4";
        }else if(position==4){
            kmlID = R.raw.e5;
            KML_Name = "e5";
        }else if(position==5){
            kmlID = R.raw.e6;
            KML_Name = "e6";
        }else if(position==6){
            kmlID = R.raw.e7;
            KML_Name = "e7";
        }else if(position==7){
            kmlID = R.raw.e8;
            KML_Name = "e8";
        }else if(position==8){
            kmlID = R.raw.e9;
            KML_Name = "e9";
        }else if(position==9){
            kmlID = R.raw.e10;
            KML_Name = "e10";
        }else if(position==10){
            kmlID = R.raw.e11;
            KML_Name = "e11";
        }else if(position==11){
            kmlID = R.raw.e12;
            KML_Name = "e12";
        }else if(position==12){
            kmlID = R.raw.e13;
            KML_Name = "e13";
        }else if(position==13){
            kmlID = R.raw.e14;
            KML_Name = "e14";
        }else if(position==14){
            kmlID = R.raw.e15;
            KML_Name = "e15";
        }else if(position==15){
            kmlID = R.raw.e16;
            KML_Name = "e16";
        }else if(position==16){
            kmlID = R.raw.e17;
            KML_Name = "e17";
        }else if(position==17){
            kmlID = R.raw.e18;
            KML_Name = "e18";
        }else if(position==18){
            kmlID = R.raw.e19;
            KML_Name = "e19";
        }else if(position==19){
            kmlID = R.raw.e20;
            KML_Name = "e20";
        }else if(position==20){
            kmlID = R.raw.e21;
            KML_Name = "e21";
        }else if(position==21){
            kmlID = R.raw.e22;
            KML_Name = "e22";
        }else if(position==22){
            kmlID = R.raw.e23;
            KML_Name = "e23";
        }else if(position==23){
            kmlID = R.raw.e24;
            KML_Name = "e24";
        }else if(position==24){
            kmlID = R.raw.e25;
            KML_Name = "e25";
        }else if(position==25){
            kmlID = R.raw.e26;
            KML_Name = "e26";
        }else if(position==26){
            kmlID = R.raw.e27;
            KML_Name = "e27";
        }else if(position==27){
            kmlID = R.raw.e28;
            KML_Name = "e28";
        }else if(position==28){
            kmlID = R.raw.e29;
            KML_Name = "e29";
        }else if(position==29){
            kmlID = R.raw.e30;
            KML_Name = "e30";
        }else if(position==30){
            kmlID = R.raw.e31;
            KML_Name = "e31";
        }else if(position==31){
            kmlID = R.raw.e32;
            KML_Name = "e32";
        }else if(position==32){
            kmlID = R.raw.e33;
            KML_Name = "e33";
        }else{
            kmlID = R.raw.e34;
            KML_Name = "e34";
        }

        KML_PATH_NAME = Environment.getExternalStorageDirectory().toString() + "/ICT/"+ KML_Name +".kml";
        //KML_Name = String.valueOf(kmlID);
        makeFile(kmlID);
        File file = new File(KML_PATH_NAME);
        Uri uri;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            // Use file Uri before Android Nougat ( 7.0 )
            uri = Uri.fromFile(file);
        } else {
            // Android Nougat ( 7.0 ) and later,
            // use a FileProvider, AndroidManifest provider, and /res/xml/provider_paths.xml
            uri = FileProvider.getUriForFile(Roads.this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file);
        }
        //MimeTypeMap mime = MimeTypeMap.getSingleton();
        //String mimeType = mime.getMimeTypeFromExtension(ext);
        intent.setDataAndType(uri, "application/vnd.google-earth.kml+xml");
        intent.putExtra("com.google.earth.EXTRA.tour_feature_id", "my_track");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        controller.startActivity(intent);

    }
    public void setController(Context cnts){ this.controller = cnts;}

    private void ForToast(int pic){
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        LayoutInflater inflater = LayoutInflater.from(controller);
        //LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.costum_toast, null);

        ImageView image = (ImageView) layout.findViewById(R.id.image1);
        image.setImageResource(pic);

        Toast toast = new Toast(controller.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }
    public boolean isGoogleEarthInstalled()
    {
        try
        {
            ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.earth", 0 );
            return true;
        }
        catch(PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }

    private void showpopup(int pic , View view)
    {
        LayoutInflater inflater = LayoutInflater.from(controller);
        View mView= inflater.inflate(R.layout.popup,null);
        //PopupWindow mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, false);
        //mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        //mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        final PopupWindow mPopupWindow = new PopupWindow(
                mView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        ImageView ImView = (ImageView) mView.findViewById(R.id.widget45);
        ImView.setImageResource(pic);

        // Get a reference for the custom view close button
        ImageButton closeButton = (ImageButton) mView.findViewById(R.id.ib_close);

        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.showAtLocation(view, Gravity.CENTER,0,0);
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
                contacts.add(new Contact(splited_data[2].toString(), "dandelion2", splited_data[1].toString(), splited_data[0].toString(),""));
                //Log.d("StackOverflow", line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            Log.e("C2c", "Error occured while reading text file!!");

        }

        return contacts;

    }
}