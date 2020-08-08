package eatyourpeas.co.uk.marsipan;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import eatyourpeas.co.uk.marsipan.risks.Risk;

/**
 * Created by SimonChapman on 14/02/15.
 */
public class RiskDetailFragment extends Fragment{
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_COLOUR_ID = "colour_id";

    private Risk.RiskItem mItem;
    private RiskColourCallback mRiskColourCallback = riskColourCallback;
    Typeface tf;

    public RiskDetailFragment() {
    }

    public interface RiskColourCallback{
        public void onRiskColourSelected(Risk.RiskItem riskItem);
    }

    private static RiskColourCallback riskColourCallback = new RiskColourCallback() {
        @Override
        public void onRiskColourSelected(Risk.RiskItem riskItem) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Risk.ITEM_MAP.get(getArguments().getString(
                    ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_risk_assessment_detail,
                container, false);

        tf = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/BreeSerif-Regular.ttf");

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.risktitle)).setText(mItem.content);
            ((TextView) rootView.findViewById(R.id.risktitle)).setTypeface(tf);
            ((TextView) rootView.findViewById(R.id.risktext)).setTypeface(tf);
            String myText = mItem.getColourText(mItem.id, mItem.colour_id);
            ((TextView) rootView.findViewById(R.id.risktext)).setText(Html.fromHtml(myText));
        }



        RadioGroup riskButtonGroup = (RadioGroup)rootView.findViewById(R.id.radiocolour);
        final RadioButton whiteButton = (RadioButton)rootView.findViewById(R.id.radiowhite);
        final RadioButton redButton = (RadioButton)rootView.findViewById(R.id.radiored);
        final RadioButton amberButton = (RadioButton)rootView.findViewById(R.id.radioamber);
        final RadioButton greenButton = (RadioButton)rootView.findViewById(R.id.radiogreen);
        final RadioButton blueButton = (RadioButton)rootView.findViewById(R.id.radioblue);

        if (mItem.colour_id == 0){
            whiteButton.setChecked(true);

        }
        if (mItem.colour_id == 1){
            redButton.setChecked(true);
        }
        if (mItem.colour_id == 2){
            amberButton.setChecked(true);
        }
        if (mItem.colour_id == 3){
            greenButton.setChecked(true);
        }
        if (mItem.colour_id == 4){
            blueButton.setChecked(true);
        }

        //mRiskColourCallback.onRiskColourSelected(mItem);



        riskButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                int position = 0;

                if (checkedId == whiteButton.getId()){
                    System.out.println("You checked white");
                }

                if (checkedId == redButton.getId()){
                    System.out.println("You checked red");
                    position = 1;
                }

                if (checkedId == amberButton.getId()){
                    System.out.println("You checked amber");
                    position = 2;
                }
                if (checkedId == greenButton.getId()){
                    System.out.println("You checked green");
                    position = 3;
                }

                if (checkedId == blueButton.getId()){
                    System.out.println("You checked blue");
                    position = 4;
                }

                String myText = mItem.getColourText(mItem.id, position);
                ((TextView) rootView.findViewById(R.id.risktext)).setText(Html.fromHtml(myText));
                mItem.colour_id = position;
                mRiskColourCallback.onRiskColourSelected(mItem);

            }
        });




    /*
        Spinner mSpinner = (Spinner)rootView.findViewById(R.id.spinner);

        Integer[] items = new Integer[]{R.drawable.whitesquare, R.drawable.redsquare, R.drawable.ambersquare, R.drawable.greensquare, R.drawable.bluesquare};
        SimpleImageArrayAdapter adapter = new SimpleImageArrayAdapter(getActivity(),items);
        mSpinner.setAdapter(adapter);
        mSpinner.setSelection(mItem.colour_id); //update the spinner with the colour if already selected

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String myText = mItem.getColourText(mItem.id, position);
                ((TextView) rootView.findViewById(R.id.risktext)).setText(Html.fromHtml(myText));

                ((TextView) rootView.findViewById(R.id.risktext)).setTypeface(tf);
                mItem.colour_id = position;
                mRiskColourCallback.onRiskColourSelected(mItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    */

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {

        try{
            mRiskColourCallback = (RiskColourCallback) activity;
        }
        catch (ClassCastException e) {
            throw new IllegalStateException(activity.getClass()
                    .getSimpleName()
                    + " does not implement "
                    + getClass().getSimpleName()
                    + "'s details's interface.", e);
        }
        super.onAttach(activity);


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mRiskColourCallback = riskColourCallback;
    }
}
