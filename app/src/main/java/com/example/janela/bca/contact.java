package com.example.janela.bca;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Scanner;

public class contact extends AppCompatActivity
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
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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
        getMenuInflater().inflate(R.menu.contact, menu);
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
        }else if (id == R.id.nav_share) {
            Intent ninth = new Intent(this, share.class);
            startActivity(ninth);
            selectedClick(7);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
