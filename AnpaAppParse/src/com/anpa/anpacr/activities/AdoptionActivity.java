package com.anpa.anpacr.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.anpa.anpacr.R;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.fragments.FreqAnswerFragment;
import com.anpa.anpacr.fragments.LastNewsFragment;

public class AdoptionActivity extends AnpaAppFraqmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empty);
		
		//Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_ADOPTION);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		/*Instacia de los tabs a crear*/
		ActionBar.Tab tab_last_news = actionBar.newTab();
		tab_last_news.setText(Constants.TITLE_LAST_NEWS);
		
		ActionBar.Tab tab_freq_Answer = actionBar.newTab();
		tab_freq_Answer.setText(Constants.TITLE_FREQ_ANSWER);
		
		/*Asigna a los tabs el listener*/
		tab_last_news.setTabListener(new NewsListener());
		tab_freq_Answer.setTabListener(new NewsListener());
		
		/*Agrega los tabs a creat*/
		actionBar.addTab(tab_last_news);
		actionBar.addTab(tab_freq_Answer);
	}
	
	/*Lisenner para cambio de tabs */
	private class NewsListener implements ActionBar.TabListener{

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if(tab.getPosition() == 0){
				LastNewsFragment frag = new LastNewsFragment();
				ft.replace(android.R.id.content, frag);
			}else{
				FreqAnswerFragment frag = new FreqAnswerFragment();
				ft.replace(android.R.id.content, frag);
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
