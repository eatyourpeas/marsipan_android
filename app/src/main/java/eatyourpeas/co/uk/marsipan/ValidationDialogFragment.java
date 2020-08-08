package eatyourpeas.co.uk.marsipan;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by SimonChapman on 27/11/2016.
 */

public class ValidationDialogFragment extends DialogFragment {

    String validationCode;
    Button mButton;
    EditText mEditText;


    public ValidationDialogFragment(){

    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Dialog dialogView = new Dialog(getActivity());

        dialogView.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialogView.setContentView(R.layout.validationlayout);
        dialogView.setTitle(R.string.app_name);
        dialogView.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
        dialogView.show();



        mButton = (Button) dialogView.findViewById(R.id.submitbutton);
        mEditText = (EditText) dialogView.findViewById(R.id.validationCodeEditText);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.onDialogPositiveClick(mEditText.getText().toString());
                dismiss();
            }
        });
        return dialogView;
    }

    // Use this instance of the interface to deliver action events
    ValidationDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ValidationDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface ValidationDialogListener {

        public void onDialogPositiveClick(String validationCode);
    }

}

