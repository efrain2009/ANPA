package com.anpa.anpacr.activities;

import java.util.Date;

import android.os.Bundle;
import android.widget.EditText;

import com.anpa.anpacr.R;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.Tip;

public class AddTipActivity extends AnpaAppFraqmentActivity {
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_add_tip);
			
			//Btn de back (anterior)
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_TIPS);
					
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				Tip value = new Tip();

				EditText editxt_description_tip = (EditText) findViewById(R.id.editxt_description_tip);
				value.set_sConsejo(editxt_description_tip.getText().toString());

				EditText editxt_breed_author = (EditText) findViewById(R.id.editxt_breed_author);
				value.set_sAuthor(editxt_breed_author.getText().toString());
				
				value.set_sDate(new Date());
			}
		}
}
