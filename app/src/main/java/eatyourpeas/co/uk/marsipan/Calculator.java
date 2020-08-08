package eatyourpeas.co.uk.marsipan;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.Toast;


public class Calculator extends AppCompatActivity implements ValidationDialogFragment.ValidationDialogListener {

    GrowthDateEntryFragment gdeFragment;
    RiskListFragment rlFragment;
    Boolean isFirstRun = true;

//private ImageButton riskToolButton;
private ImageButton calcRiskButton;
    boolean calculator = true;

    @Override
    public void onDialogPositiveClick(String validationCode) {
        // User touched the dialog's positive button

        if (validationCode.contentEquals("DrFrasierCrane")){
            System.out.println("i pressed dialog ok. Code is "+validationCode);  //2690d7e8c7a9ec03e2cc907514c53617 old
            //update sharedprefs
            final String PREFS_NAME = "MyPrefsFile";
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("PREF_APP_ENABLED", true);
            editor.commit();
            checkFirstRun();

        } else {
            Toast.makeText(this, "Wrong Code", Toast.LENGTH_SHORT).show();
            showDialog();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        checkFirstRun();


        if (savedInstanceState == null) {

        }

        FragmentManager fm = getSupportFragmentManager();
        gdeFragment = (GrowthDateEntryFragment)fm.findFragmentById(R.layout.fragment_calculator);

        if (gdeFragment == null){

            GrowthDateEntryFragment newFragment = new GrowthDateEntryFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, newFragment)
                    .commit();
        } else {
            System.out.println("fragment has been created already");
        }




        calcRiskButton = (ImageButton)findViewById(R.id.calculatorRiskImageButton);
    //    riskToolButton = (ImageButton)findViewById(R.id.risktoolbutton);

        calcRiskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(calculator){

                    calculator = false;
                    hide(v, false);
                    v.setBackgroundResource(R.drawable.oval_ripple_red);
                    ((ImageButton) v).setImageResource(R.drawable.ic_calculator);
                    show(v);
                    Intent intent = new Intent(getApplicationContext(), RiskListActivity.class);
                    hide(v, false);
                    //  show(calcButton);
                    startActivity(intent);
                    finish();
                } else {

                    calculator = true;
                    hide(v, false);
                    v.setBackgroundResource(R.drawable.oval_ripple);
                    ((ImageButton) v).setImageResource(R.drawable.traffic_light);
                    show(v);
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;
        final Boolean PREF_APP_ENABLED = false;


        // Get current version code
        int currentVersionCode = 0;
        try {
            currentVersionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            // handle exception
            e.printStackTrace();
            return;
        }

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);
        boolean appEnabled = prefs.getBoolean("PREF_APP_ENABLED", PREF_APP_ENABLED);


        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {
            isFirstRun = false;
            // This is just a normal run
            return;

        }

        if(savedVersionCode == DOESNT_EXIST && !appEnabled) {
            // TODO This is a new install (or the user cleared the shared preferences)
            showDialog();


        }
        if (currentVersionCode > savedVersionCode) {
            isFirstRun = false;
            // TODO This is an upgrade

        }



    }

    private void showDialog(){
        ValidationDialogFragment alertFragment = new ValidationDialogFragment();
        alertFragment.show(getSupportFragmentManager(),"validation");
        alertFragment.setCancelable(false);

    }
}
