package eatyourpeas.co.uk.marsipan;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by SimonChapman on 14/02/15.
 */
public class GrowthDateEntryFragment extends Fragment implements TextView.OnEditorActionListener{


    public Calendar dob;
    public Calendar clinic;
    private int dialog_tag;
    private EditText dobET;
    private EditText clinicET;
    private EditText weightET;
    private EditText heightET;
    private EditText systolicET;
    private EditText diastolicET;
    private ImageButton calculateButton;
    private TextView calculatorTitle;
    private TextView risktoollabel;
    private TextView disclaimerlabel;

    private Switch maleFemaleSwitch;
    private ToggleButton maleFemaleToggle;

    private GrowthMethods calculations = new GrowthMethods();
    public double height;
    public double weight;
    public int systolicBP;
    public int diastolicBP;
    public boolean isMale;
    public boolean isBP = false;
    public boolean isBMI = false;

    public HashMap<EditText, Boolean> editTextBooleanMap;
    public Typeface tf;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);

        tf = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/BreeSerif-Regular.ttf");

        if (savedInstanceState != null){

        }

        else {

            dobET = (EditText) rootView.findViewById(R.id.dobET);
            clinicET = (EditText) rootView.findViewById(R.id.clinicET);
            weightET = (EditText) rootView.findViewById(R.id.weightET);
            heightET = (EditText) rootView.findViewById(R.id.heightET);
            systolicET = (EditText) rootView.findViewById(R.id.systolicBPET);
            diastolicET = (EditText) rootView.findViewById(R.id.diastolicBPET);
            calculatorTitle = (TextView) rootView.findViewById(R.id.calculator_title);
            risktoollabel = (TextView) rootView.findViewById(R.id.risktoollabel);
            disclaimerlabel= (TextView) rootView.findViewById(R.id.disclaimertextview);

            calculateButton = (ImageButton) rootView.findViewById(R.id.calculateImageButton);

            dobET.setTypeface(tf);
            clinicET.setTypeface(tf);
            weightET.setTypeface(tf);
            heightET.setTypeface(tf);
            systolicET.setTypeface(tf);
            diastolicET.setTypeface(tf);
            calculatorTitle.setTypeface(tf);
            risktoollabel.setTypeface(tf);
            disclaimerlabel.setTypeface(tf);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                maleFemaleToggle = (ToggleButton) rootView.findViewById(R.id.sexSwitch);
            } else {

                maleFemaleSwitch = (Switch) rootView.findViewById(R.id.sexSwitch);

            }

            maleFemaleSwitch.setTypeface(tf);

            isMale = false;

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                maleFemaleToggle.setChecked(isMale);
                maleFemaleToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        isMale = isChecked;
                    }
                });
            } else {
                maleFemaleSwitch.setChecked(isMale);
                maleFemaleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        isMale = isChecked;
                    }
                });
            }

            editTextBooleanMap = new HashMap<EditText, Boolean>();

            editTextBooleanMap.put(dobET, false);
            editTextBooleanMap.put(clinicET, false);
            editTextBooleanMap.put(weightET, false);
            editTextBooleanMap.put(heightET, false);
            editTextBooleanMap.put(systolicET, false);
            editTextBooleanMap.put(diastolicET, false);


            final Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            dob = myCalendar;
            clinic = myCalendar;

            //calculateButton.setVisibility(View.GONE);
            //  hide(calculateButton,false);
            testAllFields();


            calculateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double decimalAge;
                    String chronologicalAge;
                    DateTime JodaDOB = new DateTime(dob);
                    DateTime JodaClinic = new DateTime(clinic);
                    decimalAge = calculations.decimalAgeFromDates(JodaDOB, JodaClinic);
                    chronologicalAge = calculations.chronologicalAgeFromDates(JodaDOB, JodaClinic);

                    String heightString = "";
                    String weightString = "";
                    String bmiString = "";
                    String pctBMIString = "";
                    String target1Text = "";
                    String target2Text = "";
                    String bpString = "";

                    System.out.println("isBP " + isBP);

                    if (isBMI) {
                        //calculations
                        double bmi = calculations.bmiFromHeightandWeight(height, weight);
                        double weightSDS = calculations.SDS("weight", decimalAge, weight, isMale);
                        double heightSDS = calculations.SDS("height", decimalAge, height, isMale);
                        double bmiSDS = calculations.SDS("BMI", decimalAge, bmi, isMale);
                        double percentBMI = calculations.percentageMedianBMI(bmi, decimalAge, isMale);
                        String weightCentile = calculations.convertZScoreToCentile(weightSDS);
                        String heightCentile = calculations.convertZScoreToCentile(heightSDS);
                        String bmiCentile = calculations.convertZScoreToCentile(bmiSDS);

                        double medianBMI = calculations.measurementFromSDS("BMI", 0, bmi, isMale, decimalAge, false);
                        double ninthCentileBMI = calculations.measurementFromSDS("BMI", -1.341, bmi, isMale, decimalAge, false);

                        double targetWeight100pctmBMI = calculations.weightForBMI(height, medianBMI);
                        double targetWeight95pctmBMI = targetWeight100pctmBMI * 0.95;
                        double targetWeight90pctmBMI = targetWeight100pctmBMI * 0.9;
                        double targetWeight85pctmBMI = targetWeight100pctmBMI * 0.85;
                        double targetWeight9thCentileBMI = calculations.weightForBMI(height, ninthCentileBMI);
                        double BMIat1SD = calculations.measurementFromSDS("BMI", 1, bmi, isMale, decimalAge, false);
                        double BMIat2SD = calculations.measurementFromSDS("BMI", 2, bmi, isMale, decimalAge, false);
                        double weightFor1SDBMI = calculations.weightForBMI(height, BMIat1SD);
                        double weightFor2SDBMI = calculations.weightForBMI(height, BMIat2SD);

                        //markup the results
                        heightString = String.format("</b><br>Height: <b>%.1f cm</><br>Centile: <b>%s &#37;</b><br>SDS: <b>%.2f</b><br>", height, heightCentile, heightSDS);
                        weightString = String.format("Weight: <b>%.1f kg</b><br>Centile: <b>%s &#37;</b><br>SDS: <b>%.2f</b><br>", weight, weightCentile, weightSDS);
                        bmiString = String.format("BMI: <b>%.1f kg/m&sup2;</b><br>Centile: <b>%s &#37;</b><br>SDS: <b>%.2f</b>", bmi, bmiCentile, bmiSDS);
                        pctBMIString = String.format("<H2>&#37;mBMI: <b>%.1f &#37;</b></H2>", percentBMI);
                        target1Text = String.format("<h3>Target Weights</h3>ideal (100&#37;): <b>%.1f kg</b><br>95&#37;: <b>%.1f kg</b><br>90&#37;: <b>%.1f kg</b><br>", targetWeight100pctmBMI, targetWeight95pctmBMI, targetWeight90pctmBMI);
                        target2Text = String.format("85&#37;: <b>%.1f kg</b><br>9th Centile: <b>%.1f kg</b><br>+1SD: <b>%.1f kg</b><br>+2SD: <b>%.1f kg</b><br>", targetWeight85pctmBMI, targetWeight9thCentileBMI, weightFor1SDBMI, weightFor2SDBMI);
                    }
                    if (isBP) {
                        double systolicSDS = calculations.bpSDS(true, isMale, decimalAge, systolicBP);
                        double diastolicSDS = calculations.bpSDS(false, isMale, decimalAge, diastolicBP);
                        String systolicCentile = calculations.convertZScoreToCentile(systolicSDS);
                        String diastolicCentile = calculations.convertZScoreToCentile(diastolicSDS);
                        bpString = String.format("<H3>Blood Pressure Data</H3>Blood Pressure: <b>%d/%d</b><br>Systolic Centile: <b>%s &#37;</b><br>Systolic SDS: <b>%.2f</b><br>Diastolic Centile: <b>%s &#37;</b><br>Diastolic SDS: <b>%.2f</b><br>", systolicBP, diastolicBP, systolicCentile, systolicSDS, diastolicCentile, diastolicSDS);
                    }

                    String ageText = String.format("Decimal age: <b>%.1f years</b><br>Chronological age:  <b>%s</b>", decimalAge, chronologicalAge);
                    String finalText = ageText + heightString + weightString + bmiString + pctBMIString + target1Text + target2Text + bpString;
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Results");
                    alertDialog.setMessage(Html.fromHtml(finalText));
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    // Set the Icon for the Dialog

                    alertDialog.show();
                    TextView tv = (TextView)alertDialog.findViewById(android.R.id.message);

                    tv.setTypeface(tf);



                }
            });

            dobET.setInputType(InputType.TYPE_NULL);
            dobET.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_tag = 0;
                    showDatePicker(dob);
                }
            });
            clinicET.setInputType(InputType.TYPE_NULL);
            clinicET.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_tag = 1;
                    showDatePicker(clinic);
                }
            });


            heightET.setOnEditorActionListener(this);
            weightET.setOnEditorActionListener(this);
            systolicET.setOnEditorActionListener(this);
            diastolicET.setOnEditorActionListener(this);


            setEditTextOnFocusChangedListener(heightET);
            setEditTextOnFocusChangedListener(weightET);
            setEditTextOnFocusChangedListener(systolicET);
            setEditTextOnFocusChangedListener(diastolicET);
        }

        return rootView;
    }

    private void showDatePicker(Calendar dateToChange) {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */

        Bundle args = new Bundle();
        args.putInt("year", dateToChange.get(Calendar.YEAR));
        args.putInt("month", dateToChange.get(Calendar.MONTH));
        args.putInt("day", dateToChange.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */

        date.setCallBack(ondate);
        date.show(getActivity().getSupportFragmentManager(), "datepicker");

    }

        DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                //convert chosen date to date format
                Date dateSet = new Date();
                Calendar c = Calendar.getInstance();
                c.set(year, monthOfYear, dayOfMonth);
                dateSet = c.getTime();


                // convert this to jodatime
                DateTime chosenDate = new DateTime(dateSet);
                DateTimeFormatter df = DateTimeFormat.longDate();

                if (dialog_tag == 0) {
                //this is dob

                    dob = c;
                    dobET.setText(chosenDate.toString(df));
                    editTextBooleanMap.put(dobET, true);

                } else if (dialog_tag == 1) {
                //this is clinic

                    clinic = c;
                    clinicET.setText(chosenDate.toString(df));
                    editTextBooleanMap.put(clinicET, true);
                }

            }
        };

    private void testAllFields() {

        System.out.println("test all the fields");

        if (editTextBooleanMap.get(dobET) == true && editTextBooleanMap.get(clinicET) == true && editTextBooleanMap.get(weightET) == true && editTextBooleanMap.get(heightET) == true) {
            isBMI = true;
        }
        if (editTextBooleanMap.get(dobET) == true && editTextBooleanMap.get(clinicET) == true && editTextBooleanMap.get(systolicET) == true && editTextBooleanMap.get(diastolicET) == true) {
            isBP = true;
        }
        if (isBP || isBMI) {
            calculateButton.setVisibility(View.VISIBLE);
            show(calculateButton);
        } else {
            hide(calculateButton, false);
            calculateButton.setVisibility(View.INVISIBLE);
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
        animSetXY.playTogether(scaleInX, scaleInY);
        if (isTemporary) {
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


    private void setEditTextOnFocusChangedListener(final EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                Bundle growthDataBundle = new Bundle();

                if (!hasFocus){
                    if (v==weightET && weightET.getText().length()>0){
                        //update the ET
                        String myWeight = weightET.getText().toString();
                        weight = Double.parseDouble(myWeight);
                        weightET.setText(myWeight + " kg");
                        editTextBooleanMap.put(weightET, true);
                        growthDataBundle.putDouble("WEIGHT", weight);

                    }
                    else if (v == heightET && heightET.getText().toString().length() > 0) {
                        String myHeight = heightET.getText().toString();
                        height = Double.parseDouble(myHeight);
                        //update the ET
                        heightET.setText(myHeight + " cm");
                        editTextBooleanMap.put(heightET, true);
                        growthDataBundle.putDouble("HEIGHT", height);
                    }
                    else if (v == systolicET && systolicET.getText().toString().length() > 0) {
                        String mySystolic = systolicET.getText().toString();
                        //store the systolic
                        systolicBP = Integer.parseInt(mySystolic);
                        //update the ET
                        systolicET.setText(systolicBP + " mmHg");
                        editTextBooleanMap.put(systolicET, true);
                        growthDataBundle.putInt("SYSTOLIC", systolicBP);
                    }
                    else if (v == diastolicET && diastolicET.getText().toString().length() > 0) {
                        String myDiastolic = diastolicET.getText().toString();
                        //store the diastolic
                        diastolicBP = Integer.parseInt(myDiastolic);
                        //update the ET
                        diastolicET.setText(diastolicBP + " mmHg");
                        editTextBooleanMap.put(diastolicET, true);
                        growthDataBundle.putInt("DIASTOLIC", diastolicBP);
                    }
                } else {
                    if (v == weightET) {
                        weightET.setText("");
                        weight = 0.0;
                        editTextBooleanMap.put(weightET, false);
                    }
                    if (v == heightET) {
                        heightET.setText("");
                        height = 0.0;
                        editTextBooleanMap.put(heightET, false);
                    }
                    if (v == diastolicET) {
                        diastolicET.setText("");
                        diastolicBP = 0;
                        editTextBooleanMap.put(diastolicET, false);
                    }
                    if (v == systolicET) {
                        systolicET.setText("");
                        diastolicBP = 0;
                        editTextBooleanMap.put(systolicET, false);
                    }
                }
                testAllFields();
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            v.clearFocus();
        }
        return false;
    }

}