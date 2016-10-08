package com.example.chrislemelin.clicker.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.chrislemelin.clicker.Resources.Upgrade;
import com.example.chrislemelin.clicker.Resources.UpgradePackage;
import com.example.chrislemelin.clicker.Resources.UpgradeType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by chrislemelin on 10/7/16.
 */

public class UpgradeManager implements Serializable
{
    private ArrayList<Upgrade> upgrades;
    private SharedPreferences pref;



    private long clickValue = 1;
    final private long initClickValue = 1;

    public int upgradeCount = 0;


    public UpgradeManager(Context c, SharedPreferences pref)
    {
        this.pref = pref;

        upgrades = new ArrayList<Upgrade>();

        //create all upgrades
        UpgradePackage pack = new UpgradePackage(1);

        makeUpgrade("+1", UpgradeType.Clicker,10,pref,pack,c);
        makeUpgrade("+2", UpgradeType.Clicker,11,pref,new UpgradePackage(2),c);
        makeUpgrade("+3", UpgradeType.Clicker,12,pref,new UpgradePackage(3),c);
        makeUpgrade("+4", UpgradeType.Clicker,13,pref,new UpgradePackage(4),c);
        makeUpgrade("+5", UpgradeType.Clicker,14,pref,new UpgradePackage(5),c);



    }

    private void makeUpgrade(String name, UpgradeType upgradeType, int cost,SharedPreferences pref,
                             UpgradePackage pack,Context c)
    {
        upgrades.add(new Upgrade(name, upgradeType,upgradeCount++,cost,pref,pack,c));
    }

    public ArrayList<Upgrade> getUpgrades()
    {
        return upgrades;
    }

    public boolean TryToActivateUpgrade(String tag)
    {
        for (Upgrade up :upgrades)
        {
            if(tag == up.getTag())
            {
                //its already active
                if(up.getActive())
                {
                    return false;
                }
                else
                {
                    updatePref(up);
                    up.setActive(true);
                    UpdateSelf();
                    return true;
                }
            }
        }
        // couldn't be found
        return false;
    }

    private void updatePref(Upgrade up)
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(up.getTag(),true);
        editor.commit();
    }


    public void UpdateSelf()
    {
        ResetAttributes();
        for (Upgrade up :upgrades)
        {
            if(up.getActive())
            {
                // upgrade if active
                ProcessUpgradePackage(up.getUpgradePackage());
            }
        }
    }

    public Upgrade getUpgrade(String tag)
    {
        for(Upgrade up : upgrades)
        {
            if(up.getTag() == tag)
            {
                return up;
            }
        }
        return null;
    }

    public long getClickValue()
    {
        return clickValue;
    }

    private void ProcessUpgradePackage(UpgradePackage pack)
    {
        clickValue += pack.getClickerUp();
    }



    private void ResetAttributes()
    {
        clickValue = initClickValue;
    }


}
