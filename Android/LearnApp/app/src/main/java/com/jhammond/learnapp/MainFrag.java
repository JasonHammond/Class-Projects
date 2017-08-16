package com.jhammond.learnapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainFrag extends ListFragment {
    OnTopicSelectedListener mCallback;

    // Communication interface
    public interface OnTopicSelectedListener {
        void onChoiceMade(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        // Create an array adapter for the list view, using the Ipsum headlines array
        setListAdapter(new ArrayAdapter<>(getActivity(), layout, Topics.Questions));
    }

    @Override
    public void onStart() {
        super.onStart();

        // Large screen? Highlight selected topic
        if(getFragmentManager().findFragmentById(R.id.info_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Verify that callback interface has been implemented
        // Else exception
        try {
            Activity activity = (Activity) context;
            mCallback = (OnTopicSelectedListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTopicSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify parent activity of selection made
        mCallback.onChoiceMade(position);

        // Large screen? Set item for highlighting
        getListView().setItemChecked(position, true);
    }
}