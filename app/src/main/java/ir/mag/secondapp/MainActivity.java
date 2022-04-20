package ir.mag.secondapp;

import com.google.android.gms.common.internal.FallbackServiceBroker;
import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
//import android.support.v7.app.ActionBarActivity;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	public static android.content.res.Resources res;
	private SharedPrefence sharedPreference2;
	private String text2="";
	public String txt_Name;
	public static int txtID;
	private String txt_PATH_NAME = Environment.getExternalStorageDirectory().toString() + "/ICT/"+ txt_Name +".txt";
	int pos = -1;
	int i=0;
	GridView gridView;
	public Activity context = this;
	boolean permission = false;
	boolean permission2 = false;
	int person = Sign_in.ID;

	static final Integer[] MOBILE_OS = new Integer[] { 
		0 ,1,2, 3,4,5,6,7,8};
	private String list1[] = { "village", "village_info", "roads", "pishkhaan_info", "pishkhaan","bakhshdari", "dehyari", "mokhaberat"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sharedPreference2 = new SharedPrefence();
		text2 = sharedPreference2.getValue(context);

		res = getResources();
		gridView = (GridView) findViewById(R.id.gridView0);
 
		gridView.setAdapter(new MainAdapter(this));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				permission = Check_Permission(person,position);
				if(permission) {

					//Toast.makeText(Sign_in.this, "خوش آمدید", Toast.LENGTH_LONG).show();
					if( text2 == null || Integer.toString(person).equals(text2)){
						for(i=0;i!=list1.length;i++){
							permission2 = Check_Permission_Files(person,i);
							if(permission2) {
								txt_Name = list1[i];
								txtID = res.getIdentifier(list1[i], "raw", getPackageName());
								makeFile(txtID);
							}
						}
						sharedPreference2.save(context, "ICT added");////////////////////////////
					}

					pos = position;
					if (position == 0) {
						Intent intent = new Intent(MainActivity.this, Villages.class);
						startActivity(intent);
					} else if (position == 1) {
						Intent intent = new Intent(MainActivity.this, Roads.class);
						startActivity(intent);
					} else if (position == 2) {
						Intent intent = new Intent(MainActivity.this, Uso_Items.class);
						startActivity(intent);
					} else if (position == 3) {
						Intent intent = new Intent(MainActivity.this, Pishkhaan.class);
						startActivity(intent);
					} else if (position == 4) {
						Intent intent = new Intent(MainActivity.this, Bakhshdari.class);
						startActivity(intent);
					} else if (position == 5) {
						Intent intent = new Intent(MainActivity.this, Dehyari.class);
						startActivity(intent);
					} else if (position == 6) {
						Intent intent = new Intent(MainActivity.this, Mokhaberat.class);
						startActivity(intent);
					} else if (position == 7) {
						Intent intent = new Intent(MainActivity.this, Setting.class);
						startActivity(intent);
					} else {
						AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
								MainActivity.this);
						// Setting Dialog Title
						alertDialog2.setTitle(R.string.darbare);

						// Setting Dialog Message
						alertDialog2.setMessage(R.string.email);
						// Setting Icon to Dialog
						alertDialog2.setIcon(R.drawable.ic_launcher);
						// Setting Positive "Yes" Button
						alertDialog2.show();
					}/* else {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								MainActivity.this);
						// Setting Dialog Title
						alertDialog.setTitle(R.string.guide);

						// Setting Dialog Message
						alertDialog.setMessage(R.string.guide2);
						// Setting Icon to Dialog
						alertDialog.setIcon(R.drawable.ic_launcher);
						// Setting Positive "Yes" Button
						alertDialog.show();

					}*/
				}
				else{
					Toast.makeText(MainActivity.this, "شما به این بخش دسترسی ندارید", Toast.LENGTH_SHORT).show();
				}

			}
		});

		//Permissions
		if (ContextCompat.checkSelfPermission(MainActivity.this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {
			//Toast.makeText(Roads.this, "nadarad",Toast.LENGTH_LONG).show();
			// Permission is not granted
			// Should we show an explanation?
			if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
					Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
				// Show an explanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.
			} else {
				// No explanation needed; request the permission
				ActivityCompat.requestPermissions(MainActivity.this,
						new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);

				// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
				// app-defined int constant. The callback method gets the
				// result of the request.
			}
		} else {
			// Permission has already been granted
		}

		if (ContextCompat.checkSelfPermission(MainActivity.this,
				Manifest.permission.READ_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {
			//Toast.makeText(Roads.this, "nadarad",Toast.LENGTH_LONG).show();
			// Permission is not granted
			// Should we show an explanation?
			if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
					Manifest.permission.READ_EXTERNAL_STORAGE)) {
				// Show an explanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.
			} else {
				// No explanation needed; request the permission
				ActivityCompat.requestPermissions(MainActivity.this,
						new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 102);

				// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
				// app-defined int constant. The callback method gets the
				// result of the request.
			}
		} else {
			// Permission has already been granted
		}



			
	}
	
	@Override
    public void onBackPressed() {
        finish();
    }
	
	
	public class MainAdapter extends BaseAdapter {
		private Context context;
	 
		public MainAdapter(Context context) {
			this.context = context;
		}
	 
		public View getView(int position, View convertView, ViewGroup parent) {

			View gridView;
	 
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				gridView = new View(context);
	 
				// get layout from mobile.xml
				gridView = inflater.inflate(R.layout.main_adapter, parent , false);
	 
				// set value into textview
				
	 
			} else {
				gridView = (View) convertView;
			}
	
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image0);

			Integer mobile = MOBILE_OS[position];
			
			if (mobile.equals(0)) {
				Picasso.with(context).load(R.drawable.z0).noFade().resize(400, 400).into(imageView);
			} else if (mobile.equals(1)) {
				Picasso.with(context).load(R.drawable.z1).noFade().resize(400, 400).into(imageView);
			} else if (mobile.equals(2)) {
				Picasso.with(context).load(R.drawable.z2).noFade().resize(400, 400).into(imageView);
			} else if (mobile.equals(3)) {
				Picasso.with(context).load(R.drawable.z3).noFade().resize(400, 400).into(imageView);
			} else if (mobile.equals(4)) {
				Picasso.with(context).load(R.drawable.z4).noFade().resize(400, 400).into(imageView);
			} else if (mobile.equals(5)) {
				Picasso.with(context).load(R.drawable.z5).noFade().resize(400, 400).into(imageView);
			} else if (mobile.equals(6)) {
				Picasso.with(context).load(R.drawable.z6).noFade().resize(400, 400).into(imageView);
			} else if (mobile.equals(7)) {
				Picasso.with(context).load(R.drawable.z7).noFade().resize(400, 400).into(imageView);
			} else {
				Picasso.with(context).load(R.drawable.z8).noFade().resize(400, 400).into(imageView);
			} /*else {
				Picasso.with(context).load(R.drawable.z9).noFade().resize(400, 400).into(imageView);
			}*/
			
			return gridView;
		}
	 
		@Override
		public int getCount() {
			return MOBILE_OS.length;
		}
	 
		@Override
		public Object getItem(int position) {
			return null;
		}
	 
		@Override
		public long getItemId(int position) {
			return 0;
		}
	 
	}

	public void makeFile(int s){
		byte[] buffer=null;
		int size=0;

		String filename= txt_Name +".txt";

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

	private boolean Check_Permission(int iid,int position1){
		boolean perm;
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		matrix.add(0, new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8)));//ertebati va raees
		matrix.add(1, new ArrayList<>(Arrays.asList(2,4,5,6,7,8)));//fanni
		matrix.add(2, new ArrayList<>(Arrays.asList(8)));//maali omoomi
		matrix.add(3, new ArrayList<>(Arrays.asList(3,7,8)));// pishkhaan
		matrix.add(4, new ArrayList<>(Arrays.asList(8)));//harasat
		matrix.add(5, new ArrayList<>(Arrays.asList(4,5,7,8)));//daftar

		perm = matrix.get(iid).contains(position1);
		return perm;
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

}
