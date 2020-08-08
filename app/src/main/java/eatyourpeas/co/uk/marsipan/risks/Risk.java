package eatyourpeas.co.uk.marsipan.risks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class Risk {


    /**
     * An array of sample (dummy) items.
     */
    public static List<RiskItem> ITEMS = new ArrayList<RiskItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, RiskItem> ITEM_MAP = new HashMap<String, RiskItem>();

    private static String[] red = {"<h3>Percentage Median BMI &lt;70%</h3><p>[Approximates to below 0.4th BMI centile]</p><p><b>OR</b></p><h3>Recent loss of weight of 1kg or more/week for two consecutive weeks", "</h3><h3>Heart rate (awake) &lt;40 bpm</h3><h3>History of Recurrent Syncope</h3><h3>Marked orthostatic changes</h3><p>(fall in systolic blood pressure of 20mmHg or more, or below 0.4th-2nd centiles for age, or increase in heart rate up to 30bpm)</p>", "<H3>Irregular heart rhythm (does not include sinus arrhythmia)</H3><h3>QTc &gt; 450 ms with evidence of bradyarrhythmia or tachyarrhythmia</H3>(excludes sinus bradycardia and sinus arrhythmia)<H3>ECG evidence of biochemical abnormality</H3>", "<h3>Severe dehydration (10%)</h3><p>Reduced urine output<br>Dry mouth<br>Decreased skin turgor<br>sunken eyes<br>Tachypnoea<br>Tachycardia</p><p>", "<H3>below 35.5 degrees Celsius (tympanic)</H3><p>OR</p><h3>below 35.0 degrees Celsius axillary</H3>", "<h3>Hypophosphataemia</h3><h3>Hypokalaemia</h3><h3>Hyponatraemia</h3><h3>Hypocalcaemia</h3>", "<h3>Acute food refusal</h3><p>OR</p><h3>estimated calorie intake 400-600kcal per day</h3>", "<h3>Violent when parents try to limit behaviour or encourage food/fluid intake</h3><h3>Parental violence in relation to feeding (hitting, force feeding)</h3>", "<h3>High levels of uncontrolled exercise (2hrs per day)</h3>", "<H3>Self poisoning.</H3><H3>Suicidal ideas with moderate-high risk of completed suicide</H3>", "<h3>Sit Up, Squat, Stand test:</h3><p>Unable to get up at all from squatting (score 0)</p><p><b>OR</b></p><p>Unable to sit up at all from lying flat (score 0)</p>", "<h3>Confusion and delirium</h3>", "<h3>Acute Pancreatitis</h3><h3>Gastric or oesophageal rupture.</h3>"};
    private static String[] amber = {"<H3>Percentage Median BMI 70-80%</H3>[Approximates to between 2nd and 0.4th BMI centile]<p><b>OR</b></p><H3>Recent loss of weight of 500g-999g/week for two consecutive weeks</H3>", "<H3>Heart rate (awake) 40-50bpm</H3><H3>Sitting Blood Pressure</H3><p><b>Systolic</b><br>below 0.4th centile (84-98mmHg depending on age and sex)</p><p><b>Diastolic</b><br>below 0.4th centile (35-40 mmHg depending on age and sex)<p>Moderate orthostatic cardiovascular changes (fall in systolic blood pressure of 15mmHg or more, or diastolic blood pressure fall of 10mmHg or more within 3 mins standing, or increase in heart rate up to 30bpm)</p><H3>Occasional syncope</H3>", "<H3>QTc >450ms</H3>", "<H3>Moderate dehydration (5-10%)</H3>Reduced urine output<br>Dry mouth<br>Normal skin turgor<br>Some tachypnoea<br>Some tachycardia<br>Peripheral oedema", "<H3>below 36 degrees Celsius</H3>", "<H3>Hypophosphataemia</H3><H3>Hypokalaemia</H3><H3>Hyponatraemia</H3><H3>Hypocalcaemia</H3>", "<H3>Severe restriction</H3>(less than 50% of required intake).<p>Vomiting.</p><p>Purging with laxatives</p>", "<H3>Poor insight into eating problems</H3><p>lacks motivation to tackle eating problems</p><p>resistance to changes required to gain weight.</p><p>Parents unable to implement meal plan advice given by health care providers</p>", "<H3>Moderate levels of uncontrolled exercise</H3>(>1 hr per day)", "<H3>Cutting or similar behaviours.</H3><H3>Suicidal ideas with low risk of completed suicide</H3>", "<H3>Sit Up, Squat, Stand test:</H3>Unable to get up from squatting without using upper limbs (score 1)<p><b>OR</b><p><p>Unable to sit up from lying flat without using upper limbs (score 1)</p>", "<H3>Other major psychiatric co- diagnosis</H3>eg OCD, psychosis, depression", "<H3>Mallory Weiss Tear</H3><H3>Gastro-oesophageal reflux</H3><H3>Gastritis</H3><H3>Pressure sores</H3>"};
    private static String[] green = {"<H3>Percentage Median BMI 80-85%</H3>[Approximates to between 9th and 2nd BMI centile]<p><b>OR</b></p><H3>Recent weight loss of up to 500g/week for two consecutive weeks</H3>", "<H3>Heart rate (awake) 50-60bpm</H3><H3>Sitting Blood Pressure</H3><b>Systolic</b><br>below 2nd centile (88 - 105mmHg depending on age and sex)<br><b>Diastolic</b><br>below 2nd centile (40 - 45mmHg depending on age and sex)<p>Pre-syncopal symptoms but no orthostatic cardiovascular changes</p><H3>Cool peripheries.<br>Prolonged peripheral capillary refill time (normal central capillary refill time)</H3>", "<H3>QTc < 450ms</H3><b>AND</b><p>taking medication known to prolong QTc interval<br><b>OR</b><br>Family history of prolonged QTc or deafness</p>", "<H3>Mild (less than 5%)</H3>May have dry mouth or not clinically dehydrated but with concerns about risk of dehydration with negative fluid balance.", "<H3>Normal</H3>", "<H3>Normal Results</H3>", "<H3>Moderate restriction<p>Bingeing</p></H3>", "<H3>Some insight into eating problems</H3>some motivation to tackle eating problems, ambivalent towards changes required to gain weight but not actively resisting", "<H3>Mild levels of uncontrolled exercise</H3>(less than 1 hr per day)", "<H3>No self harm/suicide</H3>", "<H3>Sit Up, Squat, Stand test</H3>Unable to get up without noticeable difficulty (score 2)<p><b>OR</b></p>Unable to sit up from lying flat without noticeable difficulty (score 2)", "<H3>Poor attention and concentration<H3>", "<H3>No other issues</H3"};
    private static String[] blue = {"<H3>Percentage Median BMI>85%</H3>[Approximates to above 9th BMI centile]<P><b>OR</b></P>No weight loss over past two weeks", "<H3>Heart rate (awake) >60bpm</H3><H3>Normal sitting blood pressure for age and sex with reference to centile charts</H3><H3>Normal orthostatic cardiovascular changes</H3><H3>Normal heart rhythm</H3>", "<H3>QTc < 450ms</H3>", "<H3>Not clinically dehydrated</H3>", "<H3>Normal</H3>", "<H3>Normal Results</H3>", "<H3>Mild Restriction<p>No Bingeing</p></H3>", "<H3>Some insight into eating problems</H3>motivated to tackle eating problems,<br>ambivalence towards changes required to gain weight not apparent in behaviour", "<H3>No uncontrolled exercise</H3>", "<H3>No self harm/suicide</H3>", "<H3>Sit Up, Squat, Stand test</H3>Stands up from squat without any difficulty (score 3)<p>OR</p>Sits up from lying flat without any difficulty (score 3)", "<H3>No other issues</H3", "<H3>No other issues</H3"};
    private static String[] colours = {"red", "amber", "green", "blue", "transparent"};


    static {
        String[] marsipanCategories = {"Body Mass", "Cardiovascular Health", "ECG Abnormalities", "Hydration Status", "Temperature", "Biochemical Abnormalities", "Calorie Intake", "Engagement With Management Plan", "Activity and Exercise", "Self Harm and Suicide", "Muscular Weakness", "Other Mental Health Diagnoses", "Other"};



        for (int i = 0; i < marsipanCategories.length; i++) {
            //load the risks into the array, load transparent colour in
            RiskItem mItem = new RiskItem(""+i, marsipanCategories[i], 0);
            addItem(mItem);

        }

    }

    private static void addItem(RiskItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * Risk Items
     */
    public static class RiskItem {
        public String id;
        public String content;
        public int colour_id;

        public RiskItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        public RiskItem(String id, String content, int colour_id){
            this.id = id;
            this.content = content;
            this.colour_id = colour_id;
        }

        @Override
        public String toString() {
            return content;
        }


        public String getColourText(String id, int colour_id){
            String colourText;
            int index = Integer.valueOf(id);
            switch (colour_id){
                case 1:
                    colourText = red[index];
                    break;
                case 2:
                    colourText = amber[index];
                    break;
                case 3:
                    colourText = green[index];
                    break;
                case 0:
                    colourText = "By tapping on the box below, select the colour which best describes the level of risk for this category"; //white
                    break;
                case 4:
                    colourText = blue[index];
                    break;
                default:
                    colourText="";
                    break;
            }
            return colourText;

        }
    }
}
