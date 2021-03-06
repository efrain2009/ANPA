package com.anpa.anpacr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.actionbarsherlock.app.SherlockActivity;
import com.anpa.anpacr.R;

public class HomeActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		/* Mapeo de los componentes de pantalla */
		
		//Noticias
		ImageButton btn_news = (ImageButton) findViewById(R.id.btn_home_news);
		btn_news.setOnClickListener(onNewsClick);
		
		//Castracion
		ImageButton btn_castration = (ImageButton) findViewById(R.id.btn_home_castration);
		btn_castration.setOnClickListener(onCastrationClick);
		
		//Tips
		ImageButton btn_tips = (ImageButton) findViewById(R.id.btn_home_tips);
		btn_tips.setOnClickListener(onTipsClick);
		
		//Adoption
		ImageButton btn_adoption = (ImageButton) findViewById(R.id.btn_home_adoption);
		btn_adoption.setOnClickListener(onLostClick);
		
		//Donation
		ImageButton btn_donation = (ImageButton) findViewById(R.id.btn_home_donation);
		btn_donation.setOnClickListener(onDonationClick);
		
		//Contactenos
		ImageButton btn_contactus = (ImageButton) findViewById(R.id.btn_home_contactus);
		btn_contactus.setOnClickListener(onContactusClick);
	}

	private OnClickListener onNewsClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Como hacer mensaje System.out
			//Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_LONG).show();
			startNewsActivity();
		}
	};
	
	private void startNewsActivity(){
		startActivity(new Intent(this, NewsActivity.class));
	}
	
	private OnClickListener onCastrationClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Como hacer mensaje System.out
			//Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_LONG).show();
			startCastrationActivity();
		}
	};
	
	private void startCastrationActivity(){
		startActivity(new Intent(this, CastrationActivity.class));
	}
	
	private OnClickListener onTipsClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Como hacer mensaje System.out
			//Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_LONG).show();
			startTipsActivity();
		}
	};
	
	private void startTipsActivity(){
		startActivity(new Intent(this, TipSearchActivity.class));
	}
	
	private OnClickListener onLostClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Como hacer mensaje System.out
			//Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_LONG).show();
			startLostctivity();
		}
	};
	
	private void startLostctivity(){
		startActivity(new Intent(this, LostActivity.class));
	}
	
	private OnClickListener onDonationClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Como hacer mensaje System.out
			//Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_LONG).show();
			startDonationActivity();
		}
	};
	
	private void startDonationActivity(){
		startActivity(new Intent(this, DonationActivity.class));
	}
	
	private OnClickListener onContactusClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Como hacer mensaje System.out
			//Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_LONG).show();
			startContactusActivity();
		}
	};
	
	private void startContactusActivity(){
		startActivity(new Intent(this, ContactusActivity.class));
	}
}
