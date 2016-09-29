package com.example.chrislemelin.clicker;

import java.io.Serializable;

/**
 * Created by chrislemelin on 9/29/16.
 */
public class BankAccount implements Serializable
{
    static private long money;

    public BankAccount()
    {
        money = 0;
    }

    public BankAccount(long amount)
    {
        money = amount;
    }

    //public void setMoney(long amount){money = amount;}
    public void deposit(long amount)
    {
        money += amount;
    }
    public long getBalance()
    {
        return money;
    }
    public boolean withdraw(long amount)
    {
        if(money >= amount)
        {
            money-= amount;
            return true;
        }
        else
        {
            return false;
        }
    }
}
