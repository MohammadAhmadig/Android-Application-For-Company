package ir.mag.secondapp;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import ir.mag.secondapp.Adapter.CustomAdapter_Uso;
import ir.mag.secondapp.Model.Contact;

public class Setting extends AppCompatActivity {

    public static android.content.res.Resources res;
    int i=0;
    int person = Sign_in.ID;
    boolean permission = false;
    public String txt_Name;
    public static int txtID;
    private String list1[] = { "village", "village_info", "roads", "pishkhaan_info", "pishkhaan","bakhshdari", "dehyari", "mokhaberat"};
    ListView listView;
    CustomAdapter_Uso adapter;
    BufferedReader reader;
    ArrayList<Contact> contacts = new ArrayList<Contact>();
    private String list[] = {"به روز رسانی اطلاعات","نرم افزار Google Earth"};
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    //private String file_url = "https://8upload.ir/uploads/";
    private String file_url = "https://aghazerah.ir/app/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso__items);

        contacts.add(new Contact(list[0], "update", "", "",""));
        contacts.add(new Contact(list[1], "google_earth", "", "",""));
        listView = (ListView) findViewById(R.id.lv1);

        adapter = new CustomAdapter_Uso(this, contacts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id)
            {
                if(position == 0){
                    if(!isConnectingToInternet(getApplicationContext())) {
                        Toast.makeText(Setting.this, "مشکل در اتصال به اینترنت",Toast.LENGTH_LONG).show();
                    }else {
                        new DownloadFileFromURL().execute(list1);
                    }
                }
                else if(position == 1){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafebazaar.ir/app/com.google.earth"));
                    startActivity(browserIntent);
                }

            }
        });

    }
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type:
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setTitle("In progress...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }


    class DownloadFileFromURL extends AsyncTask<String, Integer, String> {
        /**
         * Before starting background thread Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            String list1[] = { "village", "village_info", "roads", "pishkhaan_info", "pishkhaan","bakhshdari", "dehyari", "mokhaberat"};
            try {
                pDialog.setMax(f_url.length);
                File filepath = Environment.getExternalStorageDirectory();
                for (int i = 0; i < f_url.length; i++) {
                    permission = Check_Permission_Files(person, i);
                    if (permission) {
                        URL url = new URL(file_url + f_url[i] + ".txt");
                        URLConnection conection = url.openConnection();
                        conection.connect();
                        // getting file length
                        int lenghtOfFile = conection.getContentLength();

                        // input stream to read file - with 8k buffer
                        InputStream input = new BufferedInputStream(
                                url.openStream(), 8192);
                        System.out.println("Data::" + file_url + f_url[i] + ".txt");
                        // Output stream to write file
                        OutputStream output = new FileOutputStream(filepath.getAbsolutePath() + "/ICT/" + list1[i] + ".txt");
                        //OutputStream output = new FileOutputStream(
                        //        "/sdcard/Images/" + i + ".jpg");

                        byte data[] = new byte[1024];

                        long total = 0;

                        while ((count = input.read(data)) != -1) {
                            total += count;
                            // publishing the progress....
                            // After this onProgressUpdate will be called
                            publishProgress((int) ((total * 100) / lenghtOfFile));

                            // writing data to file
                            output.write(data, 0, count);
                        }

                        // flushing output
                        output.flush();

                        // closing streams
                        output.close();
                        input.close();
                        //cc++;
                    }
                }
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            publishProgress();
            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(Integer... progress) {
            pDialog.incrementProgressBy(1);
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

            // Displaying downloaded image into image view
            // Reading image path from sdcard
            //String imagePath = Environment.getExternalStorageDirectory()
            //      .toString() + "/downloaded.jpg";
            // setting downloaded into image view
            // my_image.setImageDrawable(Drawable.createFromPath(imagePath));
        }

    }
    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    private boolean Check_Permission_Files(int iid,int section){
        boolean perm;
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        matrix.add(0, new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7)));//ertebati va raees
        matrix.add(1, new ArrayList<>(Arrays.asList(5,6,7)));//fanni
        matrix.add(2, new ArrayList<>(Arrays.asList(100,101)));//maali omoomi
        matrix.add(3, new ArrayList<>(Arrays.asList(3,4)));// pishkhaan
        matrix.add(4, new ArrayList<>(Arrays.asList(100,101)));//harasat
        matrix.add(5, new ArrayList<>(Arrays.asList(5,6)));//daftar

        perm = matrix.get(iid).contains(section);
        return perm;
    }

    private void myDownload(int i){
        //permission = Check_Permission_Files(person,i);
        //if(permission) {

            txt_Name = list1[i];
            //txtID = res.getIdentifier(list1[i], "raw", getPackageName());
            //makeFile(txtID);
            //new DownloadFileFromURL().execute(file_url+txt_Name+".txt");//txt_Name+".txt" // "f141573586.pdf"
            new DownloadFileFromURL().execute(list1);
        //}
    }
}
