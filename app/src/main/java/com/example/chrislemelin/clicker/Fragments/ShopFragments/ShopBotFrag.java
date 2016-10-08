package com.example.chrislemelin.clicker.Fragments.ShopFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.chrislemelin.clicker.MainActivity;
import com.example.chrislemelin.clicker.R;
import com.example.chrislemelin.clicker.Resources.Upgrade;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link ShopBotFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopBotFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ShopBotFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopBotFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopBotFrag newInstance(String param1, String param2) {
        ShopBotFrag fragment = new ShopBotFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    public void onActivityCreated(Bundle b)
    {
        super.onActivityCreated(b);

        drawButtons();
/*
        Button but = (Button)getView().findViewWithTag("button_0");
        but.setText("first one bitches");*/

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_bot, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);




    }


    public void drawButtons()
    {
        LinearLayout layout = (LinearLayout)getView().findViewById(R.id.ShopLowerTestID);
        layout.removeAllViews();

        ArrayList<Upgrade> ups = (ArrayList<Upgrade>)getArguments().getSerializable("upgrades");

        for(int a = 0 ; a < ups.size(); a ++)
        {
            Button b1 = new Button(getView().getContext());
            b1.setEnabled(!ups.get(a).getActive());
            b1.setTag(ups.get(a).getTag());
            b1.setText(ups.get(a).getName()+"\n Cost: "+ups.get(a).getCost());
            b1.setWidth(0);
            b1.setOnClickListener((MainActivity)getActivity());
            layout.addView(b1);
        }
    }

    public void onResume()
    {
        super.onResume();
/*
        View v = getView();


        LinearLayout layout = (LinearLayout)v.findViewById(R.id.ShopLowerTestID);
        for(int a = 0 ; a < 5; a ++)
        {
            Button b1 = new Button(getView().getContext());
            b1.setHeight(100);
            b1.setWidth(400);
            layout.addView(b1);
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
