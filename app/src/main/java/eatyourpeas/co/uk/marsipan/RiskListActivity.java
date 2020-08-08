package eatyourpeas.co.uk.marsipan;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
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
public class RiskListActivity extends FragmentActivity implements RiskListFragment.Callbacks, RiskDetailFragment.RiskColourCallback{

    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_risk_assessment_list);


        if (findViewById(R.id.riskdetailcontainer) != null) {
            mTwoPane = true;
            ((RiskListFragment) getSupportFragmentManager().findFragmentById(
                    R.id.risklistcontainer)).setActivateOnItemClick(true);
        }
        ImageButton calculateButton = (ImageButton)findViewById(R.id.opencalculatorbutton);
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
        show(calculateButton);
    }

    @Override
    public void onItemSelected(String id) {

        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(RiskDetailFragment.ARG_ITEM_ID, id);
            RiskDetailFragment fragment = new RiskDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.riskdetailcontainer, fragment).commit();
        } else {
            Intent detailIntent = new Intent(this, RiskDetailActivity.class);
            detailIntent.putExtra(RiskDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    @Override
    public void onRiskColourSelected(Risk.RiskItem riskItem) {
        //only implemented in dual pane view

        RiskListAdapter adapter = (RiskListAdapter)((RiskListFragment) getSupportFragmentManager().findFragmentById(
                R.id.risklistcontainer)).getListAdapter();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected  void  onResume(){
        super.onResume();

       //update the listview with the selection from the detail view
        if (!mTwoPane){

            RiskListAdapter adapter = (RiskListAdapter)((RiskListFragment) getSupportFragmentManager().findFragmentById(
                    R.id.risklistcontainer)).getListAdapter();
            adapter.notifyDataSetChanged();
        }
    }

    public void hide(View v, Boolean isTemporary) {

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

    public void show(View v) {

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
