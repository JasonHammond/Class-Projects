package com.jhammond.learnapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InfoFrag extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If recreating activity, restore instance state
        if(savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate fragment layout
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Any arguments incoming?
        Bundle args = getArguments();
        if(args != null) {
            // Use arguments
            updateInfoView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Use saved instance state from onCreateView()
            updateInfoView(mCurrentPosition);
        }
    }

    public void updateInfoView(int position) {
        TextView information = (TextView) getActivity().findViewById(R.id.info_fragment);
        information.setText(Topics.Info[position]);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save info selection in case fragment needs to be recreated
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}