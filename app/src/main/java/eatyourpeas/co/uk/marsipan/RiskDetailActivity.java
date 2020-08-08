package eatyourpeas.co.uk.marsipan;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;

import eatyourpeas.co.uk.marsipan.risks.Risk;

/**
 * Created by SimonChapman on 14/02/15.
 */

public class RiskDetailActivity extends FragmentActivity implements RiskDetailFragment.RiskColourCallback {

    private int mColourChosen = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_assessment_detail);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }


        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(RiskDetailFragment.ARG_ITEM_ID, getIntent()
                    .getStringExtra(RiskDetailFragment.ARG_ITEM_ID));
            RiskDetailFragment fragment = new RiskDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.riskdetailcontainer, fragment).commit();
        }

        final ImageButton calculateButton = (ImageButton)findViewById(R.id.opencalculatorbutton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calculator.class);
                hide(v, false);
                startActivity(intent);
                finish();
                return;
            }
        });
        show(calculateButton, false);

        final ImageButton addRiskButton = (ImageButton)findViewById(R.id.addRiskButton);
        addRiskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override


    public void onRiskColourSelected(Risk.RiskItem riskItem) {
        //only implemented in single pane view

    }

    public void hide(View v, boolean isTemporary) {

        //this method is to hide the floating action button add/remove button

        v.setAlpha(1.0f);
        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(v, "scaleX", 1, 0);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(v, "scaleY", 1, 0);

        scaleInX.setInterpolator(new AccelerateInterpolator());
        scaleInY.setInterpolator(new AccelerateInterpolator());

        AnimatorSet animSetXY = new AnimatorSet();


        animSetXY.setDuration(200);
        animSetXY.playTogether(scaleInX,scaleInY);
        if (isTemporary){
            animSetXY.setStartDelay(2000);
        }

        animSetXY.start();
    }

    public void show(View v, boolean isTemporary) {

        //this method is to show the floating action button add/remove button

        v.setAlpha(1.0f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 0, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 0, 1);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(scaleX, scaleY);
        animSetXY.setInterpolator(new OvershootInterpolator());
        animSetXY.setDuration(500);

        animSetXY.start();
    }
}
