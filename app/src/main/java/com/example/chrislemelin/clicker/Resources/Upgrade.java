package com.example.chrislemelin.clicker.Resources;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.chrislemelin.clicker.R;

import java.io.Serializable;

/**
 * Created by chrislemelin on 10/7/16.
 */



public class Upgrade implements Serializable
{
    private UpgradeType type;
    private String tag;
    private boolean active;
    private String name;
    private UpgradePackage upgradePackage;
    private int cost;

    public Upgrade(String name,UpgradeType type, int num, int cost,
                   SharedPreferences pref, UpgradePackage upgradePackage,Context c)
    {
        this.type = type;
        tag = c.getString(R.string.upgrade_tag_prefix)+num;
        this.active = pref.getBoolean(tag,false);
        this.name = name;
        this.upgradePackage = upgradePackage;
        this.cost = cost;
    }

    public String getTag()
    {
        return tag;
    }

    public boolean getActive()
    {
        return  active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public String getName()
    {
        return name;
    }

    public UpgradePackage getUpgradePackage()
    {
        return upgradePackage;
    }

    public UpgradeType getUpgradeType()
    {
        return type;
    }

    public int getCost()
    {
        return cost;
    }


}
