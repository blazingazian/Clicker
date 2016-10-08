package com.example.chrislemelin.clicker.Resources;

import java.io.Serializable;

/**
 * Created by chrislemelin on 10/7/16.
 */

public class UpgradePackage implements Serializable
{
    private int clickerUp = 0;

    public UpgradePackage(int clickerUp)
    {
        this.clickerUp = clickerUp;
    }

    public int getClickerUp()
    {
        return clickerUp;
    }

}
