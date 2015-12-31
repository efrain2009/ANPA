package com.anpa.anpacr.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.anpa.anpacr.R;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.Tip;

public class DetailTipActivity extends SherlockFragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_tip);
		
		//Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_TIPS);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Tip value = (Tip) extras.get(Constants.ID_OBJ_DETAIL_TIP);

			TextView txt_detail_raza_title = (TextView) findViewById(R.id.txt_detail_raza);
			txt_detail_raza_title.setText(Constants.RAZA_PRUEBA);
			
			TextView txt_detail_consejo = (TextView) findViewById(R.id.txt_detail_consejo);
			txt_detail_consejo.setText(value.get_sConsejo());

			TextView txt_detail_autor = (TextView) findViewById(R.id.txt_detail_autor);
			txt_detail_autor.setText(value.get_sAuthor());
			
		}
	}

}
