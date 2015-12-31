package com.anpa.anpacr.activities;

import android.os.Bundle;

import com.anpa.anpacr.R;
import com.anpa.anpacr.common.Constants;

public class ContactusActivity extends AnpaAppFraqmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		//Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_CONTACTUS);
	}

}

