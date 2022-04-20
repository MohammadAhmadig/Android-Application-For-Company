package ir.mag.secondapp.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.res.ResourcesCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import ir.mag.secondapp.BuildConfig;
import ir.mag.secondapp.Model.Contact;
import ir.mag.secondapp.R;
import ir.mag.secondapp.Roads;

public class CustomAdapter_Roads extends BaseAdapter implements Filterable
{

    private Context context;
    private ArrayList<Contact> filteredData;
    private ArrayList<Contact> data;
    private LayoutInflater inflater;
    private ItemFilter mFilter = new ItemFilter();
    ListView listView;
    public static String KML_Name;
    public static int kmlID;
    private final String KML_PATH_NAME = Environment.getExternalStorageDirectory().toString() + "/ICT/"+ KML_Name +".kml";

    public CustomAdapter_Roads(Context context, ArrayList<Contact> data)
    {
        this.context = context;
        this.filteredData = data ;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount () { return filteredData.size(); }

    @Override
    public Object getItem (int position)
    {
        return filteredData.get(position);
    }

    @Override
    public long getItemId (int position)
    {
        return position;
    }


    public static class viewHolder
    {
        public TextView txtName;
        public TextView txtParent;
        //public TextView txtAmariCode;

        //public ImageView imgProfile;

    }


    @Override
    public View getView (final int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;


        viewHolder holder = new viewHolder();
        final int position2 = position;
        if (vi == null)
        {
            vi = inflater.inflate(R.layout.contact_item_roads, null);
            holder.txtName = (TextView) vi.findViewById(R.id.contact_txtName);
            //holder.txtAmariCode = (TextView) vi.findViewById(R.id.contact_txtAmariCode);
            holder.txtParent = (TextView) vi.findViewById(R.id.contact_txtParent);
            //holder.imgProfile = (ImageView) vi.findViewById(R.id.contact_imgProfile);

            vi.setTag(holder);

        }
        else
            holder = (viewHolder) vi.getTag();


        if (data.size() > 0)
        {
            Contact tempValue = filteredData.get(position);

            holder.txtName.setText(tempValue.getName());
            holder.txtParent.setText(tempValue.getParent());
            //holder.txtAmariCode.setText(tempValue.getAbadiCode());

            String uri = "@drawable/" + tempValue.getImage();
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            //holder.imgProfile.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), imageResource, null));


        }

        Button btn1 = (Button) vi.findViewById(R.id.button1);
        final Roads object1 = new Roads();

        final viewHolder finalHolder = holder;
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //Toast.makeText(context, "click shod .....", Toast.LENGTH_LONG).show();
                object1.setController(context);
                TextView v1= finalHolder.txtName;
                TextView v2= finalHolder.txtParent;

                object1.ShowImage(object1.FindRoad(v1,v2),arg0);
                //object1.showpopup(arg0);
            }
        });

        Button btn2 = (Button) vi.findViewById(R.id.button2);
        final View finalVi = vi;
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                object1.setController(context);

                TextView v1= finalHolder.txtName;
                TextView v2= finalHolder.txtParent;
                object1.Earth(object1.FindRoad(v1,v2));

            }
        });

        /*
        if(object1.isGoogleEarthInstalled()){
                    TextView v1= finalHolder.txtName;
                    TextView v2= finalHolder.txtParent;
                    object1.Earth(object1.FindRoad(v1,v2));
                } else{
                    Toast.makeText(object1, "برای استفاده از این قابلیت ابتدا نرم افزار Google Earth را نصب کنبد.",Toast.LENGTH_LONG).show();
                }
         */
        return vi;
    }

/*
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List filtList = new ArrayList<>();
                if (list == null) {
                    list = new ArrayList(disp_ls);
                }


                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = disp_ls.size();
                    results.values = disp_ls;
                } else {
                    constraint = constraint.toString();
                    for(int i=0; i < disp_ls.size() ; i++) {
                        Contact data = (Contact) disp_ls.get(i);
                        String da = data.getAbadiCode() + " " + data.getName() + " " + data.getParent();
                        if (da.contains(constraint)) {
                            filtList.add(disp_ls.get(i));
                        }
                    }

                    results.count = filtList.size();
                    results.values = filtList;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }*/

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final ArrayList<Contact> list = data;

            int count = list.size();
            final ArrayList<Contact> filtList = new ArrayList<Contact>();

            Contact filterableString ;

            constraint = constraint.toString().toLowerCase();
            for(int i=0; i < list.size() ; i++) {
                Contact data = (Contact) list.get(i);
                String da = data.getAbadiCode() + " " + data.getName() + " " + data.getParent();
                if (da.contains(constraint)) {
                    filtList.add(list.get(i));
                }
            }

            results.values = filtList;
            results.count = filtList.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Contact>) results.values;
            notifyDataSetChanged();
        }

    }

}
