package com.example.chrislemelin.clicker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.preference.PreferenceManager;
import android.animation.*;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.*;
import android.widget.CheckBox;

import com.example.chrislemelin.clicker.Fragments.*;


import java.net.URI;

public class MainActivity extends FragmentActivity implements BankAccountFrag.OnFragmentInteractionListener,
        MainClickerFrag.OnFragmentInteractionListener, OptionsFrag.OnFragmentInteractionListener{

    //private BankAccount account;// = new BankAccount();
    private SharedPreferences pref;

    private BankAccountFrag bankAccountFrag;
    private MainClickerFrag mainClickerFrag;
    private OptionsFrag optionsFrag;

    public void BankAccountFragInteraction(String uri) {
        return;
    }
    public void MainClickerFragInteraction(String uri) {
        return;
    }
    public void onOptionInteraction(String uri)
    {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences((getString(R.string.pref_name)), 0);
        long mon = pref.getLong((getString(R.string.pref_name)) ,0);

        bankAccountFrag = new BankAccountFrag();
        mainClickerFrag = new MainClickerFrag();
        optionsFrag = new OptionsFrag();

        Bundle bankAccountBundle = new Bundle();
        bankAccountBundle.putLong("money", mon);

        if(savedInstanceState != null)
        {
            //return;
        }
        FragmentManager man = getSupportFragmentManager();
        if( man == null) {
            Log.d("error", "msn is null");
            return;
        }

        if (findViewById(R.id.MainFragID) != null)
        {
            man.beginTransaction()
                    .add(R.id.MainFragID, mainClickerFrag).commit();
        }


        if (findViewById(R.id.OptionsFragID) != null)
        {
            man.beginTransaction()
                    .add(R.id.OptionsFragID, optionsFrag).commit();
        }

        if (findViewById(R.id.BankAcountID) != null)
        {

            Bundle bundle = getIntent().getExtras();
            if(bundle == null)
            {
                bundle = new Bundle();
            }
            bundle.putLong("money",mon);
            bankAccountFrag.setArguments(bundle);
            man.beginTransaction()
                    .add(R.id.BankAcountID, bankAccountFrag).commit();
        }

        Log.d("made", "its lit");


    }

    @Override
    public void onStart()
    {
        super.onStart();
        OptionsFrag opts = (OptionsFrag)getSupportFragmentManager().findFragmentById(R.id.OptionsFragID);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    public void MainButtonClick(View v)
    {
        Log.d("click1","click");
        if(bankAccountFrag != null)
        {
            bankAccountFrag.addToAccount(1);
        }
        Log.d("stored", pref.getLong((getString(R.string.pref_name)), 0)+"");
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong((getString(R.string.pref_name)), bankAccountFrag.getBalance());
        editor.commit();
    }
    public void ToShopClick(View v)
    {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment newFragment = new MainClickerFrag();

        ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        ft.replace(R.id.MainFragID, newFragment, "fragment");
        ft.commit();

        Log.d("shope","toshope");

    }
    public void ToOptionsClick(View v)
    {
        findViewById(R.id.OptionsFragID).setVisibility(View.VISIBLE);
        findViewById(R.id.faderImage).setAlpha(.5f);
    }
    public void ExitOptionsClick(View v)
    {
        findViewById(R.id.OptionsFragID).setVisibility(View.GONE);
        findViewById(R.id.faderImage).setAlpha(0f);
    }

}