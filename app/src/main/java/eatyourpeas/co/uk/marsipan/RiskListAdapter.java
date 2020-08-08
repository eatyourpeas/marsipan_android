package eatyourpeas.co.uk.marsipan;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import eatyourpeas.co.uk.marsipan.risks.Risk;

/**
 * Created by SimonChapman on 15/02/15.
 */
public class RiskListAdapter<T> extends ArrayAdapter<Risk.RiskItem> {

        int mColourId = 5;
        List<Risk.RiskItem> mItems;
        Typeface tf;


        public RiskListAdapter(Context context, List<Risk.RiskItem> items) {

            super(context, android.R.layout.simple_list_item_activated_1, android.R.id.text1, items);
            this.mItems = items;
        }

        public RiskListAdapter(Context context, Risk.RiskItem[] items) {

            super(context, android.R.layout.simple_list_item_activated_1, android.R.id.text1, items);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {



           View view = super.getView(position, convertView, parent);

            TextView textView = (TextView) super.getView(position, convertView, parent);

            tf = Typeface.createFromAsset(getContext().getAssets(),
                    "fonts/BreeSerif-Regular.ttf");
            textView.setTypeface(tf);
            mColourId = mItems.get(position).colour_id;

            matchColourToId(mColourId, view);

            return textView;
        }



    private View matchColourToId(int colourId, View v){

        switch (colourId){



            case 1:
                v.setBackgroundColor(this.getContext().getResources().getColor(R.color.red));
                break;
            case 2:
                v.setBackgroundColor(this.getContext().getResources().getColor(R.color.orange));
                break;
            case 3:
                v.setBackgroundColor(this.getContext().getResources().getColor(R.color.green));
                break;
            case 4:
                v.setBackgroundColor(this.getContext().getResources().getColor(R.color.blue));
                break;
            case 0:
                v.setBackgroundColor(Color.TRANSPARENT);
                break;
            default:
                v.setBackgroundColor(Color.TRANSPARENT);
                break;
        }
        return v;
    }
}

