package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by ${farhanarnob} on ${06-Oct-16}.
 * For learning fragment
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {
    private static final String HEAD_INDEX = String.valueOf(R.string.head_index);
    private static final String BODY_INDEX = String.valueOf(R.string.body_index);
    private static final String LEG_INDEX = String.valueOf(R.string.leg_index);
    private int headIndex;
    private int bodyIndex;
    private int legIndex;
    private boolean twoPane = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.android_me_linear_layout) != null) {
            twoPane = true;
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_fab);
            fab.setVisibility(View.GONE);
            GridView gridView = (GridView) findViewById(R.id.grid_all_part);
            gridView.setNumColumns(2);
            if (savedInstanceState == null) {
                // Use a FragmentManager and transaction to add the fragment to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                // For head
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                int headIndex = getIntent().getIntExtra(HEAD_INDEX, 0);
                headFragment.setListIndex(headIndex);

                // Fragment transaction for head
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();


                // For body
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                int bodyIndex = getIntent().getIntExtra(BODY_INDEX, 0);
                bodyFragment.setListIndex(bodyIndex);

                // Fragment transaction for body
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                // For Leg
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                int legIndex = getIntent().getIntExtra(LEG_INDEX, 0);
                legFragment.setListIndex(legIndex);

                // Fragment transaction for leg
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(getApplicationContext(), "List item clicked. Position:" + position,
                Toast.LENGTH_SHORT)
                .show();
        int bodyPartNumber = position / 12;

        int listIndex = position - 12 * bodyPartNumber;

        if (twoPane) {

            BodyPartFragment newFragment = new BodyPartFragment();
            switch (bodyPartNumber) {
                case 0:
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();

                    break;
                case 1:
                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }

            Bundle bundle = new Bundle();
            bundle.putInt(HEAD_INDEX, headIndex);
            bundle.putInt(BODY_INDEX, bodyIndex);
            bundle.putInt(LEG_INDEX, legIndex);

            final Intent intentMeActivity = new Intent(this, AndroidMeActivity.class);
            intentMeActivity.putExtras(bundle);


            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intentMeActivity);
                }
            });
        }


    }
}
