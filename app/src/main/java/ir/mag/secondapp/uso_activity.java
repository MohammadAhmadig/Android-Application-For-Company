package ir.mag.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class uso_activity extends AppCompatActivity {

    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso);

        TextView aboutTextView = (TextView) findViewById(R.id.textView_uso);

        CharSequence aboutText = Html.fromHtml("<h1> " + ""+ "</h1>"
                + getString(R.string.detail_uso1));
        aboutTextView.setText(aboutText);

        ImageView btn1 = findViewById(R.id.image_uso1);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showpopup(R.drawable.uso1,arg0);
                //ForToast();
            }
        });
    }
    private void ForToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.costum_toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.image1);
        image.setImageResource(R.drawable.uso1);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }
    private void showpopup(int pic , View view)
    {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
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
}
