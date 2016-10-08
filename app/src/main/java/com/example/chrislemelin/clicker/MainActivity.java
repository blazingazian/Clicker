package com.example.chrislemelin.clicker;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.CheckBox;

import com.example.chrislemelin.clicker.Fragments.*;
import com.example.chrislemelin.clicker.Managers.SoundManager;
import com.example.chrislemelin.clicker.Managers.UpgradeManager;
import com.example.chrislemelin.clicker.Resources.Upgrade;

public class MainActivity extends FragmentActivity implements BankAccountFrag.OnFragmentInteractionListener,
        MainClickerFrag.OnFragmentInteractionListener, OptionsFrag.OnFragmentInteractionListener,
        ShopFrag.OnFragmentInteractionListener, View.OnClickListener{

    //private BankAccount account;// = new BankAccount();
    private SharedPreferences pref;

    private BankAccountFrag bankAccountFrag;
    private MainClickerFrag mainClickerFrag;
    private OptionsFrag optionsFrag;
    private SoundManager soundManager;
    private ShopFrag shopFrag;

    private UpgradeManager upgradeManager;

    public void onFragmentInteraction(Uri uri){return;}
    public void BankAccountFragInteraction(String uri) {
        return;
    }
    public void MainClickerFragInteraction(String uri) {
        return;
    }
    public void onOptionInteraction(String uri) {return;}
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences((getString(R.string.pref_name)), 0);

        //fragments init
        bankAccountFrag = new BankAccountFrag();
        mainClickerFrag = new MainClickerFrag();
        optionsFrag = new OptionsFrag();
        shopFrag = new ShopFrag();

        //managers init
        soundManager = new SoundManager(this,pref);
        upgradeManager = new UpgradeManager(this,pref);

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
            man.beginTransaction()
                    .add(R.id.BankAcountID, bankAccountFrag).commit();
        }


    }

    @Override
    public void onStart()
    {
        super.onStart();
        soundManager.startMusic();

        ((CheckBox)findViewById(R.id.OptionsMusicID)).setChecked(pref.getBoolean(getString(R.string.pref_music),true));
        ((CheckBox)findViewById(R.id.OptionsEffectsID)).setChecked(pref.getBoolean(getString(R.string.pref_effects),true));
    }


    //Clicking responsibility

    public void MainButtonClick(View v)
    {
        if(bankAccountFrag != null)
        {
            long increaseValue = upgradeManager.getClickValue();
            bankAccountFrag.addToAccount(increaseValue);
        }
    }

    //Shop Stuff

    public void ToShopClick(View v)
    {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment newFragment = shopFrag;

        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            bundle = new Bundle();
        }
        bundle.putSerializable("upgrades",upgradeManager);
        newFragment.setArguments(bundle);

        ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        ft.replace(R.id.MainFragID, newFragment, "fragment");
        ft.commit();
    }
    public void ExitShopClick(View v)
    {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment newFragment = mainClickerFrag;

        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        ft.replace(R.id.MainFragID, newFragment, "fragment");
        ft.commit();
    }

    public void onClick(View v)
    {
        Log.d("upgrade click", ""+v.getTag().toString());
        Upgrade up = upgradeManager.getUpgrade(v.getTag().toString());

        if(bankAccountFrag.withdraw((long)up.getCost()))
        {
            if (upgradeManager.TryToActivateUpgrade(v.getTag().toString()))
            {
                shopFrag.drawButtons();
            }
        }
    }

    // Options Menu Stuff

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

    // Sound Manager Stuff

    public void ToggleEffects(View v)
    {
        CheckBox chex = (CheckBox)v;
        soundManager.setEffects(chex.isChecked());
    }
    public void ToggleMusic(View v)
    {
        CheckBox chex = (CheckBox)v;
        soundManager.setMusic(chex.isChecked());
    }

    public void onPause()
    {
        super.onPause();
        soundManager.pauseMusic();
    }
    public void onStop()
    {
        super.onStop();
        soundManager.pauseMusic();
    }

}