package com.example.android.learninguserinputs;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Toggle Button variblaes
    private ToggleButton toggleButton1, toggleButton2;
    private Button btnDisplay;
    //Autocomplete global variables
    EditText etText;
    List<String> list;
    AutoCompleteTextView txtAuto3;
    private Button btnAddItem;
    //Spinner global variables
    Spinner spnr;
    final String[] celebrities = {"Select an Actor",
            "Chris Hemsworth",
            "Jennifer Lawrence",
            "Jessica Alba",
            "Robert Downey Jr.",
            "Brad Pitt",
            "Angelina Jolie",
            "Brad Pitt"};
    //Radiogroup global variables
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toggle Buttons
        addListenerOnButton();

        //Edit Text
        etText = (EditText) findViewById(R.id.editText);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etText.getText().toString();
                Toast msg = Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG);
                msg.show();
            }
        });

        //Arrays

        int[] myArray = new int[5];
        myArray[0] = 10; myArray[1] = 20; myArray[2] = 30; myArray[3] = 40; myArray[4] = 50;
        int [] myArray2 = {10, 20, 30, 40, 50};
        String array ="Array 1: ";
        for(int i=0; i<5; i++){
            array += myArray[i]+" | ";
        }
        String array2 ="\nArray 2: ";
        for (int k : myArray2){
            array2 += String.valueOf(k) + " | ";
        }

        TextView txtArray = (TextView) findViewById(R.id.txtArray);
        if (txtArray != null) {
            txtArray.setText(array);

        }
        txtArray.setText(txtArray.getText() + array2);

        //Static Autocomplete TextView
        String str[] = {"Dios", "Jesus", "Espiritu", "Santo"};
        AutoCompleteTextView txtAuto = (AutoCompleteTextView) findViewById(R.id.autoCompleteTXT);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, str);

        if (txtAuto != null) {
            txtAuto.setThreshold(1);
        }
        if (txtAuto != null) {
            txtAuto.setAdapter(adp);
        }

        //Dynamic AutoCompleteTextView
        LinearLayout lnLayout = (LinearLayout) findViewById(R.id.lnLayout1);
        AutoCompleteTextView txtAuto2 = new AutoCompleteTextView(MainActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) ActionBar.LayoutParams.WRAP_CONTENT, (int) ActionBar.LayoutParams.WRAP_CONTENT);

        params.leftMargin = 20;
        params.topMargin = 25;

        txtAuto2.setLayoutParams(params);

        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, str);
        txtAuto2.setThreshold(1);
        txtAuto2.setAdapter(adp2);

        if (lnLayout != null) {
            lnLayout.addView(txtAuto2);
        }

        //RunTime Add Item to AutoCompleteTextView

        list = new ArrayList<String>();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");

        txtAuto3 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        final EditText et1 = (EditText) findViewById(R.id.editText1);
        Button btn1 = (Button) findViewById(R.id.button1);

        add();

        if (btn1 != null) {
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et1 != null) {
                        list.add(et1.getText().toString());
                    }
                    if (et1 != null) {
                        et1.setText(null);
                    }

                    add();

                    Toast.makeText(MainActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
                }
            });
        }

        //Spinner

        spnr = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, celebrities);

        if (spnr != null) {
            spnr.setAdapter(adapterSpinner);
        }
        if (spnr != null) {
            spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int position1 = spnr.getSelectedItemPosition();
                    if (position1 > 0)
                        Toast.makeText(MainActivity.this, "You have selected "+celebrities[+position1], Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        //Radiogroups and Radiobuttons
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        if (radioGroup != null) {
            radioGroup.clearCheck();
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if(null!=rb && checkedId > -1){
                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    //Add item for autocomplete Method
    private void add(){
        ArrayAdapter<String> adp3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        adp3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        txtAuto3.setThreshold(1);
        txtAuto3.setAdapter(adp3);
    }

    //Toggle Buttons method.
    public void addListenerOnButton(){

        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("toggleButton1 : ").append(toggleButton1.getText());
                result.append("\ntoggleButton2 : ").append(toggleButton2.getText());

                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Methods for Radiogroup and Radiobuttons
    public void onClear(View v) {
        /* Clears all selected radio buttons to default */
        radioGroup.clearCheck();
    }

    public void onSubmit(View v) {
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
    }

}
