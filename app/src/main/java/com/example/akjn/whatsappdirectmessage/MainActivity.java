package com.example.akjn.whatsappdirectmessage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;

import android.widget.ArrayAdapter;
import android.view.Menu;
import android.view.MenuInflater;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    private EditText mPhoneNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sp = getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        if (sp.getString("theme", "light").equals("light"))
            setTheme(R.style.AppTheme);
        else
            setTheme(R.style.DarkTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    public void contactOnWhatsApp(View v) {

        EditText phoneNumberField = (EditText) findViewById(R.id.inputField);
        if (phoneNumberField.getText().toString().isEmpty()) {
            Alert_Dialog_Blank_Input();
        } else {
            //  CODE FOR COUNTRY CODE SPINNER
            CountryCodePicker cpp = (CountryCodePicker) findViewById(R.id.cpp);
            cpp.registerCarrierNumberEditText(phoneNumberField);
            String phoneNumber = cpp.getFullNumber();

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber));
            startActivity(browserIntent);
        }
    }

    //Function to save contact
    public void SaveContact(View v) {

        EditText phoneNumberField = (EditText) findViewById(R.id.inputField);
        if (phoneNumberField.getText().toString().isEmpty()) {
            Alert_Dialog_Blank_Input();
        } else {
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            mPhoneNumber = (EditText) findViewById(R.id.inputField);

            CountryCodePicker cpp = (CountryCodePicker) findViewById(R.id.cpp);
            String mNo = cpp.getFullNumberWithPlus() + mPhoneNumber.getText();
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, mNo);

            startActivity(intent);
        }

    }


    //Function to clear numbers
    public void clearNumber(View view) {
        EditText hello = (EditText) findViewById(R.id.inputField);
        hello.setText("");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences sp = getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        switch (item.getItemId()) {

            case R.id.dark_mode:
                //add code here
                if (!item.isChecked()) {
                    editor.putString("theme", "dark");
                    editor.commit();
                    Intent i = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    break;
                } else {
                    editor.putString("theme", "light");
                    editor.commit();
                    Intent ins = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(ins);
                    finish();
                    break;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void Alert_Dialog_Blank_Input() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Error");
        builder.setMessage("Please Enter A Number");
        AlertDialog alert = builder.create();
        alert.getWindow().setGravity(Gravity.CENTER);
        alert.show();
    }
}

