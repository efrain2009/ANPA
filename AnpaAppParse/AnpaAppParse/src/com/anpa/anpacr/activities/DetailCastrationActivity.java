package com.anpa.anpacr.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.anpa.anpacr.R;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.Castration;

public class DetailCastrationActivity extends SherlockFragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_castration);
		
		//Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_CASTRATION);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Castration value = (Castration) extras.get(Constants.ID_OBJ_DETAIL_CASTRATION);

			TextView txt_detail_castration_title = (TextView) findViewById(R.id.txt_detail_castration_title);
			txt_detail_castration_title.setText(value.get_snombre());
			
			TextView txt_detail_castration_direction = (TextView) findViewById(R.id.txt_direction_castration);
			txt_detail_castration_direction.setText(value.get_sdireccion());

			TextView txt_detail_castration_description = (TextView) findViewById(R.id.txt_detail_castration_description);
			txt_detail_castration_description.setText(value.get_sdescription());

			TextView txt_detail_castration_schedule = (TextView) findViewById(R.id.txt_detail_castration_schedule);
			String horario = value.get_sDateInicio().substring(10, 19) +" a " + value.get_sDateFin().substring(10, 19);
			txt_detail_castration_schedule.setText(horario);
			
			TextView txt_detail_castration_doctor = (TextView) findViewById(R.id.txt_detail_castration_doctor);
			txt_detail_castration_doctor.setText(value.get_sdoctor());

			TextView txt_detail_castration_attendant = (TextView) findViewById(R.id.txt_detail_castration_attendant);
			txt_detail_castration_attendant.setText(value.get_sEncargado());
			
			TextView txt_detail_castration_amount = (TextView) findViewById(R.id.txt_detail_castration_amount);
			String monto =  "¢ " + value.get_bgdMonto().toString();
			txt_detail_castration_amount.setText(monto);
			
			TextView txt_detail_castration_date = (TextView) findViewById(R.id.txt_detail_castration_date);
			String fecha = value.get_sDateInicio().substring(0, 10);
			txt_detail_castration_date.setText(fecha);

			if (value.get_bImagen() != null) {
				Bitmap bmpNewsDetail = BitmapFactory.decodeByteArray(
						value.get_bImagen(), 0, value.get_bImagen().length);
				ImageView img_detail_castration = (ImageView) findViewById(R.id.img_detail_castration);
				img_detail_castration.setImageBitmap(bmpNewsDetail);
			}
		}
	}

}
