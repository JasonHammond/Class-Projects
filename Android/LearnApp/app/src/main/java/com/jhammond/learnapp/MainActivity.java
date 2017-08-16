package com.jhammond.learnapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements MainFrag.OnTopicSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_layout);

        // Small screen?
        if(findViewById(R.id.fragment_container) != null) {

            // If already done, move on.
            if(savedInstanceState != null) {
                return;
            }

            // Otherwise create new fragment
            MainFrag mainFrag = new MainFrag();

            // Get/Set arguments
            mainFrag.setArguments(getIntent().getExtras());

            // Add fragment to layout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mainFrag).commit();
        }
    }

    // When a button is pressed, open fragment with desired information
    public void onChoiceMade(int position) {
        InfoFrag infoFrag = (InfoFrag)
                getSupportFragmentManager().findFragmentById(R.id.info_fragment);

        // Large screen? Update fragment
        if (infoFrag != null) {
            infoFrag.updateInfoView(position);

        // If not, swap fragments
        } else {

            // Create desired fragment
            InfoFrag newFrag = new InfoFrag();
            Bundle args = new Bundle();
            args.putInt(InfoFrag.ARG_POSITION, position);
            newFrag.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Put new fragment in old's place, put old on back stack
            transaction.replace(R.id.fragment_container, newFrag);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
