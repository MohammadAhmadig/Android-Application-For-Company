package ir.mag.secondapp.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

import ir.mag.secondapp.Model.Contact;
import ir.mag.secondapp.R;

public class CustomAdapter_Uso extends BaseAdapter
{

    private Context context;
    private ArrayList<Contact> filteredData;
    private ArrayList<Contact> data;
    private LayoutInflater inflater;

    public CustomAdapter_Uso(Context context, ArrayList<Contact> data)
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
        public TextView txtAmariCode;

        public ImageView imgProfile;

    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        viewHolder holder = new viewHolder();

        if (vi == null)
        {
            vi = inflater.inflate(R.layout.uso_item, null);
            holder.txtName = (TextView) vi.findViewById(R.id.contact_txtName);
            holder.txtAmariCode = (TextView) vi.findViewById(R.id.contact_txtAmariCode);
            holder.txtParent = (TextView) vi.findViewById(R.id.contact_txtParent);
            holder.imgProfile = (ImageView) vi.findViewById(R.id.contact_imgProfile);

            vi.setTag(holder);

        }
        else
            holder = (viewHolder) vi.getTag();


        if (data.size() > 0)
        {
            Contact tempValue = filteredData.get(position);

            holder.txtName.setText(tempValue.getName());
            holder.txtParent.setText(tempValue.getParent());
            holder.txtAmariCode.setText(tempValue.getAbadiCode());

            String uri = "@drawable/" + tempValue.getImage();
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            holder.imgProfile.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), imageResource, null));


        }

        return vi;
    }


}
