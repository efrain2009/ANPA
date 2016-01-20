package com.anpa.anpacr.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.anpa.anpacr.R;
import com.anpa.anpacr.adapter.SpinnerAdapter;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.GenericNameValue;

public class TipSearchActivity extends AnpaAppFraqmentActivity {
	private Spinner specieSpinner;
	private SpinnerAdapter adapter;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_tip_search);
			
			//Btn de back (anterior)
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_TIPS);
			
			//Crea la lista de razas:
			ArrayList<GenericNameValue> racesItems = new ArrayList<GenericNameValue>();
			for (String race : Constants.RACES) {
				String[] raceSplit = race.split(",");
				racesItems.add(new GenericNameValue(raceSplit[1], Integer.parseInt(raceSplit[0])));
			}
			
			adapter = new SpinnerAdapter(TipSearchActivity.this,
		            android.R.layout.simple_spinner_item,
		            racesItems);
			specieSpinner = (Spinner) findViewById(R.id.spn_specie_selector);
			specieSpinner.setAdapter(adapter); // Set the custom adapter to the spinner
			specieSpinner.setOnItemSelectedListener(onSelectItem);
			
			Button btnSearchTip = (Button)findViewById(R.id.btn_tip_search);
			btnSearchTip.setOnClickListener(onSearch);
		}
		
		/**
		 * Listener del spinner
		 */
		private OnItemSelectedListener onSelectItem = new OnItemSelectedListener() {
			@Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                    int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                GenericNameValue selectedItem = adapter.getItem(position);
                // Here you can do the action you want to...
                Toast.makeText(TipSearchActivity.this, "Seleccionado: " + selectedItem.getName() + "\n ID: " + selectedItem.getValue(),
                    Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
		};
		
		/**
		 * Listener del botón
		 */
		private OnClickListener onSearch = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TipSearchActivity.this, TipsActivity.class));
			}
		};
}
