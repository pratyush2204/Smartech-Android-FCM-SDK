package com.smartech.demo;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartech.demo.Activity.NotificationCenterActivity;
import com.smartech.demo.Utils.Netcore;
import com.smartech.demo.Utils.SharedPreferencesManager;
import com.smartech.demo.Utils.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String TAG = "LoginActivity";
    private ActionBar actionBar;
    private TextView textNotificationCount;
    private Button btnAddCart, btnRemoveCart, btnCheckout, btnCartExpired, btnPageBrowse, btnProfileUpdate,
            btnOptIn, btnOptOut, btnCustomData, btnOtherFunction, btnGuid, btnGetNotification, btnLogout;
    public static final int OPT_IN = 1;
    public static final int OPT_OUT = 2;
    Switch switchEvent;
    boolean eventflag=false;
    public static final int    PAGE_BROWSE      = 1;
    public static final int    ADD_TO_CART      = 2;
    public static final int    CHECKOUT         = 3;
    public static final int    CART_EXPIRY      = 4;
    public static final int    REMOVE_FROM_CART = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddCart = findViewById(R.id.btn_add_cart);
        btnRemoveCart = findViewById(R.id.btn_remove_cart);
        btnCheckout = findViewById(R.id.btn_checkout);
        btnCartExpired = findViewById(R.id.btn_cart_expired);
        btnPageBrowse = findViewById(R.id.btn_page_browse);
        btnProfileUpdate = findViewById(R.id.btn_profile_update);
        btnOptIn = findViewById(R.id.btn_opt_in);
        btnOptOut = findViewById(R.id.btn_opt_out);
        btnCustomData = findViewById(R.id.btn_custom_data);
        btnOtherFunction = findViewById(R.id.btn_other_function);
        btnGuid = findViewById(R.id.btn_guid);
        btnGetNotification = findViewById(R.id.btn_get_notification);
        btnLogout = findViewById(R.id.btn_logout);
        switchEvent=findViewById(R.id.switch_event);

        btnAddCart.setOnClickListener(onClick);
        btnRemoveCart.setOnClickListener(onClick);
        btnCheckout.setOnClickListener(onClick);
        btnCartExpired.setOnClickListener(onClick);
        btnPageBrowse.setOnClickListener(onClick);
        btnProfileUpdate.setOnClickListener(onClick);
        btnOptIn.setOnClickListener(onClick);
        btnOptOut.setOnClickListener(onClick);
        btnCustomData.setOnClickListener(onClick);
        btnOtherFunction.setOnClickListener(onClick);
        btnGuid.setOnClickListener(onClick);
        btnGetNotification.setOnClickListener(onClick);
        btnLogout.setOnClickListener(onClick);
        textNotificationCount=findViewById(R.id.text_notification_count);

        switchEvent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               eventflag=isChecked;
                Log.d(TAG, "onCheckedChanged: "+eventflag);
            }
        });

        String status = SharedPreferencesManager.getInstance(getApplicationContext()).getLoginValue();
        if (status == null || !status.equals(SharedPreferencesManager.STATUS_LOGIN)) {

               btnLogout.setVisibility(View.GONE);

        }
         actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(getResources().getText(R.string.text_main_title));
        }


        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null)
                for (String key : bundle.keySet()) {
                    Log.e(TAG, key + " : " + bundle.get(key).toString());
                    Toast.makeText(this, key + " : " + bundle.get(key).toString(), Toast.LENGTH_LONG).show();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getText(R.string.text_main_title));
        }
        if(btnGetNotification!=null) {
            if (Build.VERSION.SDK_INT >= 21) {
                textNotificationCount.setText(""+Netcore.getUnreadNotificationsCount(this));
                btnGetNotification.setText("Notification center");
            } else {
                btnGetNotification.setText("Notification center - unread(" + Netcore.getUnreadNotificationsCount(this) + ")");
            }

        }
        }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_cart:
                    if (!eventflag) {
                        trackEventFunc(getResources().getString(R.string.text_add_to_cart));
                    }else {trackEventFunc(ADD_TO_CART);}
                    break;

                case R.id.btn_remove_cart:
                    if (!eventflag) {
                        trackEventFunc(getResources().getString(R.string.text_remove_from_cart));
                    }else {trackEventFunc(REMOVE_FROM_CART);}
                    break;

                case R.id.btn_checkout:
                    if (!eventflag) {
                        trackEventFunc(getResources().getString(R.string.text_checkout));
                    }else {trackEventFunc(CHECKOUT);}
                    break;

                case R.id.btn_cart_expired:
                    if (!eventflag) {
                        trackEventFunc(getResources().getString(R.string.text_cart_expired));
                    }else {trackEventFunc(CART_EXPIRY);}
                    break;

                case R.id.btn_page_browse:
                    if (!eventflag) {
                        trackEventFunc(getResources().getString(R.string.text_page_browse));
                    }else {trackEventFunc(PAGE_BROWSE);}
                    break;

                case R.id.btn_profile_update:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    break;

                case R.id.btn_opt_in:
                        Util.showAlert(getApplicationContext(), OPT_IN, "Smartech Demo", "Do you want to Opt in?");
                    break;

                case R.id.btn_opt_out:
                        Util.showAlert(getApplicationContext(), OPT_OUT, "Smartech Demo", "Do you want to Opt in?");
                    break;

                case R.id.btn_custom_data:
                        startActivity(new Intent(MainActivity.this, CustomActivity.class));
                    break;

                case R.id.btn_other_function:
                        startActivity(new Intent(MainActivity.this, OtherFunctionsActivity.class));
                    break;

                case R.id.btn_guid:
                        Util.showAlertWithMessage(MainActivity.this, "GUID", Netcore.getGUID(getApplicationContext()));
                    break;

                case R.id.btn_get_notification:
                        startActivity(new Intent(MainActivity.this, NotificationCenterActivity.class));
                    break;

                case R.id.btn_logout:
                    Netcore.logout(MainActivity.this);
                    SharedPreferencesManager.getInstance(getApplicationContext()).clearLogin();
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                    break;

            }
        }
    };

    private void trackEventFunc(String eventName) {

        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject payload = new JSONObject();
            payload.put("prid", 1);
            payload.put("name", "Nexus");
            payload.put("itemPrice", 2000);
            payload.put("prqt", 2);
            payload.put("currency", "INR");
            jsonObject.put("payload", payload);
            Netcore.track(MainActivity.this, eventName, jsonObject.toString());
            Toast.makeText(this, eventName+" Succesfully", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void trackEventFunc(int eventId) {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject newPayload = new JSONObject();
        try {

            jsonObject.put( "s^name", "Nexus 5" );
            jsonObject.put( "i^prid", 2 );
            jsonObject.put( "f^price", 15000);
            jsonObject.put( "i^prqt",  1);
            jsonObject.put( "d^dateofpurchase", getCurrentDate() );
            jsonArray.put( jsonObject );
            newPayload.put( "payload", jsonArray );
            Netcore.track( MainActivity.this, eventId, newPayload.toString());
        }
        catch ( JSONException e ) {
            e.printStackTrace();
        }

    }

    private String getCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }


}
