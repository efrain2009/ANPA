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
import com.anpa.anpacr.domain.Lost;
import com.anpa.anpacr.fragments.LastLostFragment;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class LostActivity extends AnpaAppFraqmentActivity implements 
LastLostFragment.OnLoadListListener{
	
	List<Lost> lostList;
	
	public static final String TAG_LOST = "Perdidos";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empty);

		lostList = new ArrayList<Lost>();
		
		//Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_LOST);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		/*Instacia de los tabs a crear*/
		ActionBar.Tab tab_last_lost = actionBar.newTab();
		tab_last_lost.setText(Constants.TITLE_LAST_LOST);
		
		//Se carga la lista de noticias
		try {
			new LoadLostParse().execute(""); //El ".get" Hace esperar hasta que el hilo termine.
					
		} catch (Exception e) {
			showMessage("Ups! Perdimos el rastro de las noticias. Intenta más tarde.");
			e.printStackTrace();
		}
				
		/*Asigna a los tabs el listener*/
		tab_last_lost.setTabListener(new LostListener());
		
		/*Agrega los tabs a creat*/
		actionBar.addTab(tab_last_lost);
	}
	
	/*Lisenner para cambio de tabs */
	private class LostListener implements ActionBar.TabListener{
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if(tab.getPosition() == 0){
				LastLostFragment frag = new LastLostFragment();
				ft.replace(android.R.id.content, frag, TAG_LOST);
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
	
	private class LoadLostParse extends AsyncTask<String, Integer, Boolean> {
		private ProgressDialog progressDialog;
		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(LostActivity.this,
					"Espera un momento", "Olfateando pérdidos....");
		}
		@Override
		protected Boolean doInBackground(String... param) {
			try {
				ParseQuery<ParseObject> query = ParseQuery.getQuery("ANPA03_PERDIDOS");
				query.selectKeys(Arrays.asList("ANPA03_NOM_MASCOTA", "ANPA03_NOM_DUENO",
						"ANPA03_TELEFONO","ANPA03_PROVINCIA","ANPA03_CANTON",
						"ANPA03_FOTO","ANPA03_DETALLE", "ANPA03_LATITUD","ANPA03_LONGITUD", "ANPA03_RAZA"));// selecciona las columnas a presentar
				List<ParseObject> results = query.find();
				
				for (ParseObject lostParse : results) {
					final String sIdLost = lostParse.getObjectId();
					final String sNomMascota = lostParse.getString("ANPA03_NOM_MASCOTA");
					final String sNomDueno = lostParse.getString("ANPA03_NOM_DUENO");
					final String sTelefono = lostParse.getString("ANPA03_TELEFONO");
					final int iProvincia = lostParse.getInt("ANPA03_PROVINCIA");
					final int iCanton = lostParse.getInt("ANPA03_CANTON");
					final String sDetalle = lostParse.getString("ANPA03_DETALLE");
					final String sLatitud = lostParse.getString("ANPA03_LATITUD");
					final String sLongitud = lostParse.getString("ANPA03_LONGITUD");
					final String sRaza = lostParse.getString("ANPA03_RAZA");
					ParseFile imageFile = lostParse
							.getParseFile("ANPA03_FOTO");
					final Date dCreationDate = lostParse.getCreatedAt();
									
					Lost newLost = new Lost(sIdLost, sNomMascota, sNomDueno,sTelefono, iProvincia, iCanton, sDetalle, sRaza, dCreationDate.toString(), imageFile.getData(), sLatitud, sLongitud);
					lostList.add(newLost);
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
				LastLostFragment frag = new LastLostFragment();
	            FragmentManager fm = getSupportFragmentManager();
	            fm.beginTransaction().replace(android.R.id.content, frag, TAG_LOST).commit();
	            fm.popBackStackImmediate();
			}
		}
	}
	
	/**
	 * Muestra un mensaje TOAST.
	 * @param message
	 */
	private void showMessage(String message){
		Toast.makeText(LostActivity.this, message, Toast.LENGTH_SHORT).show();
	}
	
	/*
	 * Implementación del Interface que envía la lista al fragment.
	 */
	@Override
	public List<Lost> loadList() {
		return lostList;
	}

}
