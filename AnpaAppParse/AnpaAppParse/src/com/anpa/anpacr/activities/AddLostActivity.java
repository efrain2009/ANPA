package com.anpa.anpacr.activities;

import android.os.Bundle;
import android.widget.EditText;

import com.anpa.anpacr.R;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.Lost;

public class AddLostActivity extends AnpaAppFraqmentActivity {
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_add_lost);
			
			//Btn de back (anterior)
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_LOST);
					
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				Lost value = new Lost();

				EditText editxt_description_lost = (EditText) findViewById(R.id.editxt_nom_mascota);
				value.set_sdetalle(editxt_description_lost.getText().toString());

				EditText editxt_contacto = (EditText) findViewById(R.id.editxt_contacto);
				value.set_snombreDueno(editxt_contacto.getText().toString());
				
			}
		}
}
