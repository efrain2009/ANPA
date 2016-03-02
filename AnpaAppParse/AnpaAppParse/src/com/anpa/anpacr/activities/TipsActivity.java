package com.anpa.anpacr.activities;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.anpa.anpacr.domain.Tip;
import com.anpa.anpacr.fragments.LastTipsFragment;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TipsActivity extends AnpaAppFraqmentActivity implements
		LastTipsFragment.OnLoadListListener {

	public static final String TAG_TIPS = "consejos";
	List<Tip> tipsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empty);

		tipsList = new ArrayList<Tip>();

		// Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_TIPS);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab tab_freq_tips = actionBar.newTab();
		tab_freq_tips.setText(Constants.TITLE_DESCRIPTION_TIPS);

		// Se carga la lista de tips
		try {
			new LoadTipsParse().execute(""); // El ".get()" Hace
												// esperar hasta que
												// el hilo termine.

		} catch (Exception e) {
			showMessage("Ups! Perdimos el rastro de los consejos. Intenta más tarde.");
			e.printStackTrace();
		}

		/* Asigna a los tabs el listener */
		tab_freq_tips.setTabListener(new TipsListener());

		/* Agrega los tabs a crear */
		actionBar.addTab(tab_freq_tips);

	}

	/* Lisenner para cambio de tabs */
	private class TipsListener implements ActionBar.TabListener {

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if (tab.getPosition() == 0) {
				LastTipsFragment frag = new LastTipsFragment();
				ft.replace(android.R.id.content, frag, TAG_TIPS);
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

	private class LoadTipsParse extends AsyncTask<String, Integer, Boolean> {
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(TipsActivity.this,
					"Espera un momento", "Olfateando Consejos....");
		}

		@Override
		protected Boolean doInBackground(String... param) {
			try {
				ParseQuery<ParseObject> query = ParseQuery
						.getQuery(Constants.TABLE_CONSEJO);
				query.selectKeys(Arrays.asList(Constants.DESCR_CONSEJO,
						Constants.AUTOR_CONSEJO, Constants.RAZA_CONSEJO,
						Constants.ESPECIE_CONSEJO, Constants.ESTRELLA1_CONSEJO,
						Constants.ESTRELLA2_CONSEJO, Constants.ESTRELLA3_CONSEJO,
						Constants.ESTRELLA4_CONSEJO, Constants.ESTRELLA5_CONSEJO,
						Constants.VOTOS_CONSEJO));// selecciona las
				// columnas a
				// presentar
				List<ParseObject> results = query.find();

				for (ParseObject tipParse : results) {
					final String sIdTip = tipParse.getObjectId();
					final String sConsejo = tipParse
							.getString(Constants.DESCR_CONSEJO);
					final String sAutor = tipParse.getString(Constants.AUTOR_CONSEJO);
					final Integer raza = tipParse.getInt(Constants.RAZA_CONSEJO);
					final Integer especie = tipParse.getInt(Constants.ESPECIE_CONSEJO);
					final Integer unaEstrella = tipParse
							.getInt(Constants.ESTRELLA1_CONSEJO);
					final Integer dosEstrella = tipParse
							.getInt(Constants.ESTRELLA2_CONSEJO);
					final Integer tresEstrella = tipParse
							.getInt(Constants.ESTRELLA3_CONSEJO);
					final Integer cuatroEstrella = tipParse
							.getInt(Constants.ESTRELLA4_CONSEJO);
					final Integer cincoEstrella = tipParse
							.getInt(Constants.ESTRELLA5_CONSEJO);
					final Integer totalVotos = tipParse
							.getInt(Constants.VOTOS_CONSEJO);

					Tip newTip = new Tip();
					newTip.set_lId(sIdTip);
					newTip.set_sConsejo(sConsejo);
					newTip.set_sAuthor(sAutor);
					newTip.set_iRaza(raza);
					newTip.set_iEspecie(especie);
					newTip.set_i1Estrella(unaEstrella);
					newTip.set_i2Estrella(dosEstrella);
					newTip.set_i3Estrella(tresEstrella);
					newTip.set_i4Estrella(cuatroEstrella);
					newTip.set_i5Estrella(cincoEstrella);
					newTip.set_iTotalVotos(totalVotos);

					tipsList.add(newTip);
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
				LastTipsFragment frag = new LastTipsFragment();
	            FragmentManager fm = getSupportFragmentManager();
	            fm.beginTransaction().replace(android.R.id.content, frag, TAG_TIPS).commit();
	            fm.popBackStackImmediate();
			}
		}
	}

	/**
	 * Muestra un mensaje TOAST.
	 * 
	 * @param message
	 */
	private void showMessage(String message) {
		Toast.makeText(TipsActivity.this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public List<Tip> loadList() {
		return tipsList;
	}

}
