package com.example.janela.bca;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Scanner;

public class share extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextToSpeech tts;
    private ArrayList<String> ESLPhrases;
    private boolean ttsLoaded = false;
    private void initializeTTS(){
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status){
                ttsLoaded = true;
            }
        });
    }

    private void loadPhrases(){
        ESLPhrases = new ArrayList<String>();
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.phrases));
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            ESLPhrases.add(line);
        }
    }

    private void selectedClick(int index){
        String text = ESLPhrases.get(index);
        if(ttsLoaded){
            tts.setSpeechRate(1f);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH,null);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharing = new Intent(Intent.ACTION_SEND);
                sharing.setType("text/plain");
                sharing.putExtra(Intent.EXTRA_TEXT, "I'm using Think Pink to learn more about Breast Cancer");
                startActivity(Intent.createChooser(sharing, "Share using"));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initializeTTS();
        loadPhrases();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Intent secondscreen = new Intent(this, about.class);
            startActivity(secondscreen);
            selectedClick(0);
        } else if (id == R.id.nav_cause) {
            Intent thirdscreen = new Intent(this, causes.class);
            startActivity(thirdscreen);
            selectedClick(1);
        } else if (id == R.id.nav_symptoms) {
            Intent fourthscreen = new Intent(this, symptoms.class);
            startActivity(fourthscreen);
            selectedClick(2);
        } else if (id == R.id.nav_prevention) {
            Intent fifthscreen = new Intent(this, prevention.class);
            startActivity(fifthscreen);
            selectedClick(3);
        } else if (id == R.id.nav_charities) {
            Intent sixth = new Intent(this, charity.class);
            startActivity(sixth);
            selectedClick(4);
        } else if (id == R.id.nav_help) {
            Intent seventh = new Intent(this, help.class);
            startActivity(seventh);
            selectedClick(5);
        } else if (id == R.id.nav_contact){
            Intent eighth = new Intent(this, contact.class);
            startActivity(eighth);
            selectedClick(6);
        }else if (id == R.id.nav_share){
            Intent ninth = new Intent(this, share.class);
            startActivity(ninth);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void sendSMSMessage(View view) {
        Button sendBtn = (Button) findViewById(R.id.sendBtn);
        EditText txtphoneNo = (EditText) findViewById(R.id.editText);
        Log.i("Send SMS", "");
        String phoneNo = txtphoneNo.getText().toString();
        String message = "I'm using Think Pink to learn more about Breast Cancer ";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
