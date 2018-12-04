package com.example.akjn.whatsappdirectmessage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void contactOnWhatsApp(View v) {
        EditText phoneNumberField = (EditText) findViewById(R.id.inputField);
        String phoneNumber = phoneNumberField.getText().toString();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber));
        startActivity(browserIntent);
    }
}
