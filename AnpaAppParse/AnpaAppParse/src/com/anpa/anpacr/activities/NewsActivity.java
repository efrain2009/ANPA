package com.anpa.anpacr.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.anpa.anpacr.R;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.FreqAnswer;
import com.anpa.anpacr.domain.News;
import com.anpa.anpacr.domain.Sponsor;
import com.anpa.anpacr.fragments.FreqAnswerFragment;
import com.anpa.anpacr.fragments.LastNewsFragment;
import com.anpa.anpacr.fragments.SponsorFragment;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class NewsActivity extends AnpaAppFraqmentActivity implements 
FreqAnswerFragment.OnLoadListListenerFreqAnswerNews,
SponsorFragment.OnLoadSponsorListListener,
LastNewsFragment.OnLoadListListener{
	
	private List<News> newsList;
	private List<FreqAnswer> freqAnswerList;
	private List<Sponsor> sponsorList;
	
	public static final String TAG_NEWS = "noticias";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empty);

		newsList = new ArrayList<News>();
		freqAnswerList = new ArrayList<FreqAnswer>();
		sponsorList = new ArrayList<Sponsor>();
		
		//Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_NEWS);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		/*Instacia de los tabs a crear*/
		ActionBar.Tab tab_last_news = actionBar.newTab();
		tab_last_news.setText(Constants.TITLE_LAST_NEWS);
		
		ActionBar.Tab tab_freq_Answer = actionBar.newTab();
		tab_freq_Answer.setText(Constants.TITLE_FREQ_ANSWER);
		
		ActionBar.Tab tab_sponsor = actionBar.newTab();
		tab_sponsor.setText(Constants.TITLE_SPONSOR);
		
		//Se carga la lista de noticias
		try {
			new LoadNewsParse().execute("").get(); //El ".get" Hace esperar hasta que el hilo termine.
			
			new LoadFreqAnswerNewsParse().execute("").get(); 
			
			new LoadSponsorParse().execute("").get();
					
		} catch (Exception e) {
			showMessage("Ups! Perdimos el rastro de las noticias. Intenta más tarde.");
			e.printStackTrace();
		}
				
		/*Asigna a los tabs el listener*/
		tab_last_news.setTabListener(new NewsListener());
		tab_freq_Answer.setTabListener(new NewsListener());
		tab_sponsor.setTabListener(new NewsListener());
		
		/*Agrega los tabs a creat*/
		actionBar.addTab(tab_last_news);
		actionBar.addTab(tab_freq_Answer);
		actionBar.addTab(tab_sponsor);
	}
	
	/*Lisenner para cambio de tabs */
	private class NewsListener implements ActionBar.TabListener{
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if(tab.getPosition() == 0){
				LastNewsFragment frag = new LastNewsFragment();
				ft.replace(android.R.id.content, frag, TAG_NEWS);
			}else if(tab.getPosition() == 1){
				FreqAnswerFragment frag = new FreqAnswerFragment();
				ft.replace(android.R.id.content, frag, TAG_NEWS);
			}else{
				SponsorFragment frag = new SponsorFragment();
				ft.replace(android.R.id.content, frag, TAG_NEWS);				
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
	
	private class LoadNewsParse extends AsyncTask<String, Integer, Boolean> {
		private ProgressDialog progressDialog;
		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(NewsActivity.this,
					"Espera un momento", "Olfateando noticias....");
		}
		@Override
		protected Boolean doInBackground(String... param) {
			try {
				ParseQuery<ParseObject> query = ParseQuery.getQuery("ANPA01_NOTICIAS");
				query.selectKeys(Arrays.asList("ANPA01_TITULO", "ANPA01_CONTENIDO","ANPA01_IMAGE"));// selecciona las columnas a presentar
				List<ParseObject> results = query.find();
				
				for (ParseObject newsParse : results) {
					final String sIdNews = newsParse.getObjectId();
					final String sTitle = newsParse.getString("ANPA01_TITULO");
					final String sContent = newsParse.getString("ANPA01_CONTENIDO");
					final Date dCreationDate = newsParse.getCreatedAt();
					
					ParseFile imageFile = newsParse.getParseFile("ANPA01_IMAGE");
				
					News newNews = new News(sIdNews, sTitle, sContent, dCreationDate.toString(), imageFile.getData());
					newsList.add(newNews);
				}				
			} catch (ParseException e) {
				showMessage(e.getMessage());
				e.printStackTrace();
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				progressDialog.dismiss();
				getSupportActionBar().setSelectedNavigationItem(0);
				LastNewsFragment frag = new LastNewsFragment();
	            FragmentManager fm = getSupportFragmentManager();
	            fm.beginTransaction().replace(android.R.id.content, frag, TAG_NEWS).commit();
	            fm.popBackStackImmediate();
			}
		}
	}
	
	/**
	 * Muestra un mensaje TOAST.
	 * @param message
	 */
	private void showMessage(String message){
		Toast.makeText(NewsActivity.this, message, Toast.LENGTH_SHORT).show();
	}
	
	// Carga lista de preguntas frecuentes
		private class LoadFreqAnswerNewsParse extends
				AsyncTask<String, Integer, Boolean> {
			private ProgressDialog progressDialog;

			@Override
			protected void onPreExecute() {
				progressDialog = ProgressDialog.show(NewsActivity.this,
						"Espera un momento", "Rastreando preguntas frecuentes....");
			}
			

			@Override
			protected Boolean doInBackground(String... param) {
				try {
					ParseQuery<ParseObject> query = ParseQuery
							.getQuery("ANPA05_FAQ");
					query.addAscendingOrder("ANPA05_ORDEN");
					query.whereEqualTo("ANPA05_TIPO", 0);
					query.selectKeys(Arrays.asList("ANPA05_PREGUNTA",
							"ANPA05_ORDEN", "ANPA05_RESPUESTA", "ANPA05_TIPO"));// selecciona
																				// las
																				// columnas
																				// a
																				// presentar
					List<ParseObject> results = query.find();

					for (ParseObject parse : results) {
						final String sId = parse.getObjectId();
						final String sPregunta = parse.getString("ANPA05_PREGUNTA");
						final String sRespuesta = parse
								.getString("ANPA05_RESPUESTA");
						final Date dCreationDate = parse.getCreatedAt();

						FreqAnswer newFreqAnswer = new FreqAnswer();
						newFreqAnswer.set_lId(sId);
						newFreqAnswer.set_spregunta(sPregunta);
						newFreqAnswer.set_srespuesta(sRespuesta);
						newFreqAnswer.set_dCreationDate(dCreationDate);

						freqAnswerList.add(newFreqAnswer);
					}
				} catch (ParseException e) {
					showMessage(e.getMessage());
					e.printStackTrace();
				}
				return true;
			}

			@Override
			protected void onPostExecute(Boolean result) {
				if (result) {
					progressDialog.dismiss();
					// LastCastrationFragment.getInstance().refreshList(CastrationList);
				}
			}
		}
		
		// Carga lista de patrocinios
		private class LoadSponsorParse extends
				AsyncTask<String, Integer, Boolean> {
			private ProgressDialog progressDialog;

			@Override
			protected void onPreExecute() {
				progressDialog = ProgressDialog.show(NewsActivity.this,
						"Espera un momento", "Lamiendo Patrocinadores....");
			}
			

			@Override
			protected Boolean doInBackground(String... param) {
				try {
					ParseQuery<ParseObject> query = ParseQuery
							.getQuery("ANPA06_PATROCINIOS");
					query.addAscendingOrder("ANPA06_ORDEN");
					query.selectKeys(Arrays.asList("ANPA06_NOMBRE",
							"ANPA06_DESCRIPCION", "ANPA06_ORDEN", "ANPA06_URL", "ANPA06_IMAGEN"));// selecciona
																				// las
																				// columnas
																				// a
																				// presentar
					List<ParseObject> results = query.find();

					for (ParseObject parse : results) {
						final String sId = parse.getObjectId();
						final String sNombre = parse.getString("ANPA06_NOMBRE");
						final String sDescripcion = parse
								.getString("ANPA06_DESCRIPCION");
						final Integer sOrden = parse
								.getInt("ANPA06_ORDEN");
						final Date dCreationDate = parse.getCreatedAt();
						final String sURL = parse
								.getString("ANPA06_URL");
						ParseFile imageFile = parse
								.getParseFile("ANPA06_IMAGEN");

						Sponsor newNews = new Sponsor();
						newNews.set_lId(sId);
						newNews.set_iorden(sOrden);
						newNews.set_snombre(sNombre);
						newNews.set_sdescripcion(sDescripcion);
						newNews.set_dCreationDate(dCreationDate);
						newNews.set_bImagen(imageFile.getData());
						newNews.set_sURL(sURL);
						sponsorList.add(newNews);
					}
				} catch (ParseException e) {
					showMessage(e.getMessage());
					e.printStackTrace();
				}
				return true;
			}

			@Override
			protected void onPostExecute(Boolean result) {
				if (result) {
					progressDialog.dismiss();
					// LastCastrationFragment.getInstance().refreshList(CastrationList);
				}
			}
		}

	/*
	 * Implementación del Interface que envía la lista al fragment.
	 */
	@Override
	public List<News> loadList() {
		return newsList;
	}
	
	@Override
	public List<FreqAnswer> loadFreqAnswerList() {
		return freqAnswerList;
	}
	
	@Override
	public List<Sponsor> loadSponsorList() {
		return sponsorList;
	}
	
}
