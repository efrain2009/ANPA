package com.anpa.anpacr.activities;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.anpa.anpacr.R;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.News;

public class DetailNewsActivity extends AnpaAppFraqmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_news);
		
		//Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_NEWS);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			News value = (News) extras.get(Constants.ID_OBJ_DETAIL_NEWS);

			TextView txt_detail_news_title = (TextView) findViewById(R.id.txt_detail_news_title);
			txt_detail_news_title.setText(value.get_stitle());

			TextView txt_detail_news_description = (TextView) findViewById(R.id.txt_detail_news_description);
			txt_detail_news_description.setText(value.get_sdescription());

			SimpleDateFormat formatoFecha = 
				    new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
			
			String fecha = formatoFecha.format(value.get_dDate());
			TextView txt_detail_news_date = (TextView) findViewById(R.id.txt_detail_news_date);
			txt_detail_news_date.setText(fecha);

			if(value.get_bImagen() != null){
				ImageView img_detail_news = (ImageView) findViewById(R.id.img_detail_news);
				Bitmap bmpImage = BitmapFactory.decodeByteArray(
					      value.get_bImagen(), 0, value.get_bImagen().length);
				img_detail_news.setImageBitmap(bmpImage);
			}
		}
	}

}
