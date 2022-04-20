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

public class CustomAdapter_Bakhshdari extends BaseAdapter implements Filterable
{

    private Context context;
    private ArrayList<Contact> filteredData;
    private ArrayList<Contact> data;
    private LayoutInflater inflater;
    private ItemFilter mFilter = new ItemFilter();

    public CustomAdapter_Bakhshdari(Context context, ArrayList<Contact> data)
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
            vi = inflater.inflate(R.layout.contact_item, null);
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
