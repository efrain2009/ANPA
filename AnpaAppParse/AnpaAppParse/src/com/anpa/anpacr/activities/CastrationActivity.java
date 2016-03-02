package com.anpa.anpacr.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import com.anpa.anpacr.domain.Castration;
import com.anpa.anpacr.domain.FreqAnswer;
import com.anpa.anpacr.fragments.FreqAnswerCastrationFragment;
import com.anpa.anpacr.fragments.LastCastrationFragment;
import com.anpa.anpacr.fragments.SuggestionCastrationFragment;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CastrationActivity extends AnpaAppFraqmentActivity implements
		LastCastrationFragment.OnLoadListListener,
		SuggestionCastrationFragment.OnLoadListListenerSuggestionCastration,
		FreqAnswerCastrationFragment.OnLoadListListenerFreqAnswerCastration {
	List<Castration> castrationList;
	List<FreqAnswer> freqAnswerList;
	List<FreqAnswer> suggestionList;
	
	public static final String TAG_CASTRATION = "castraciones";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empty);

		castrationList = new ArrayList<Castration>();
		freqAnswerList = new ArrayList<FreqAnswer>();
		suggestionList = new ArrayList<FreqAnswer>();

		// Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_CASTRATION);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		/* Instacia de los tabs a crear */
		ActionBar.Tab tab_last_castration = actionBar.newTab();
		tab_last_castration.setText(Constants.TITLE_LAST_CASTRATION);

		ActionBar.Tab tab_freq_Answer_castration = actionBar.newTab();
		tab_freq_Answer_castration.setText(Constants.TITLE_FREQ_ANSWER);

		ActionBar.Tab tab_suggestion = actionBar.newTab();
		tab_suggestion.setText(Constants.TITLE_SUGGESTION);

		// Se carga la lista de castraciones
		try {
			new LoadCastrationParse().execute(""); // El ".get()" Hace
															// esperar hasta que
															// el hilo termine.
			new LoadFreqAnswerCastrationParse().execute(""); 
			

		
		} catch (Exception e) {
			showMessage("Ups! Perdimos el rastro de las castraciones. Intenta más tarde.");
			e.printStackTrace();
		}

		/*Asigna a los tabs el listener*/
		tab_last_castration.setTabListener(new CastrationListener());
		tab_freq_Answer_castration.setTabListener(new CastrationListener());
		tab_suggestion.setTabListener(new CastrationListener());
		

		/* Agrega los tabs a creat */
		actionBar.addTab(tab_last_castration);
		actionBar.addTab(tab_freq_Answer_castration);
		actionBar.addTab(tab_suggestion);
	}

	/* Lisenner para cambio de tabs */
	private class CastrationListener implements ActionBar.TabListener {

			
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if (tab.getPosition() == 0) {
				LastCastrationFragment frag = new LastCastrationFragment();
				ft.replace(android.R.id.content, frag, TAG_CASTRATION);
			} else if (tab.getPosition() == 1) {
				FreqAnswerCastrationFragment frag = new FreqAnswerCastrationFragment();
				ft.replace(android.R.id.content, frag, TAG_CASTRATION);
			} else {
				SuggestionCastrationFragment frag = new SuggestionCastrationFragment();
				ft.replace(android.R.id.content, frag, TAG_CASTRATION);
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

	private class LoadCastrationParse extends
			AsyncTask<String, Integer, Boolean> {
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(CastrationActivity.this,
					"Espera un momento", "Olfateando Castraciones....");
		}

		@Override
		protected Boolean doInBackground(String... param) {
			try {
				Calendar currentDate = Calendar.getInstance(); // Get the
																// current date
				ParseQuery<ParseObject> query = ParseQuery
						.getQuery(Constants.TABLE_CASTRACIONES);
				query.addAscendingOrder(Constants.HORARIO_INICIO_CASTRACION);
				query.whereGreaterThan(Constants.HORARIO_INICIO_CASTRACION,
						currentDate.getTime());
				query.selectKeys(Arrays.asList(Constants.NOMBRE_CASTRACION,
						Constants.DESCRIPCION_CASTRACION, Constants.DOCTOR_CASTRACION, Constants.MONTO_CASTRACION,
						Constants.DIRECCION_CASTRACION, Constants.ENCARGADO_CASTRACION,
						Constants.HORARIO_INICIO_CASTRACION, Constants.TIPO_EVENTO_CASTRACION,
						Constants.LATITUD_CASTRACION, Constants.HORARIO_FIN_CASTRACION,
						Constants.LONGITUD_CASTRACION, Constants.IMAGE_CASTRACION));// selecciona las
															// columnas a
															// presentar
				List<ParseObject> results = query.find();

				for (ParseObject CastrationParse : results) {
					final String sIdCastration = CastrationParse.getObjectId();
					final String sNombre = CastrationParse
							.getString(Constants.NOMBRE_CASTRACION);
					final String sDescripcion = CastrationParse
							.getString(Constants.DESCRIPCION_CASTRACION);
					final Date dCreationDate = CastrationParse.getCreatedAt();
					final String sDoctor = CastrationParse
							.getString(Constants.DOCTOR_CASTRACION);
					final Double monto = CastrationParse
							.getDouble(Constants.MONTO_CASTRACION);
					final String direccion = CastrationParse
							.getString(Constants.DIRECCION_CASTRACION);
					final String encargado = CastrationParse
							.getString(Constants.ENCARGADO_CASTRACION);
					final Integer tipo = CastrationParse
							.getInt(Constants.TIPO_EVENTO_CASTRACION);
					final String latitud = CastrationParse
							.getString(Constants.LATITUD_CASTRACION);
					final String longitud = CastrationParse
							.getString(Constants.LONGITUD_CASTRACION);
					final Date dInicioDate = CastrationParse
							.getDate(Constants.HORARIO_INICIO_CASTRACION);
					final Date dFinDate = CastrationParse
							.getDate(Constants.HORARIO_FIN_CASTRACION);

					ParseFile imageFile = CastrationParse
							.getParseFile(Constants.IMAGE_CASTRACION);

					Castration newCastration = new Castration();
					newCastration.set_lId(sIdCastration);
					newCastration.set_bgdMonto(monto);
					newCastration.set_bImagen(imageFile.getData());
					newCastration.set_sDate(dCreationDate.toString());
					SimpleDateFormat dt = new SimpleDateFormat(
							"dd/MM/yyyy hh:mm aaa");
					String inicioDate = dt.format(dInicioDate);
					String dateFinal = dt.format(dFinDate);

					newCastration.set_sDateFin(dateFinal);
					newCastration.set_sDateInicio(inicioDate);
					newCastration.set_sdescription(sDescripcion);
					newCastration.set_sdoctor(sDoctor);
					newCastration.set_sLatitud(latitud);
					newCastration.set_sLongitud(longitud);
					newCastration.set_sEncargado(encargado);
					newCastration.set_sTipo(tipo);
					newCastration.set_sdireccion(direccion);
					newCastration.set_snombre(sNombre);

					castrationList.add(newCastration);
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
				LastCastrationFragment frag = new LastCastrationFragment();
	            FragmentManager fm = getSupportFragmentManager();
	            fm.beginTransaction().replace(android.R.id.content, frag, TAG_CASTRATION).commit();
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
		Toast.makeText(CastrationActivity.this, message, Toast.LENGTH_SHORT)
				.show();
	}

	// Carga lista de preguntas frecuentes
	private class LoadFreqAnswerCastrationParse extends
			AsyncTask<String, Integer, Boolean> {
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(CastrationActivity.this,
					"Espera un momento", "Olfateando Castraciones....");
		}
		

		@Override
		protected Boolean doInBackground(String... param) {
			try {
				ParseQuery<ParseObject> query = ParseQuery
						.getQuery(Constants.TABLE_PREGUNTA_FREC);
				query.addAscendingOrder(Constants.ORDEN_PREGUNTA);
				query.whereEqualTo(Constants.TIPO_PREGUNTA, 0);
				query.selectKeys(Arrays.asList(Constants.DESC_PREGUNTA,
						Constants.ORDEN_PREGUNTA, Constants.RESPESTA_PREGUNTA, Constants.TIPO_PREGUNTA));// selecciona
																			// las
																			// columnas
																			// a
																			// presentar
				List<ParseObject> results = query.find();

				for (ParseObject parse : results) {
					final String sId = parse.getObjectId();
					final String sPregunta = parse.getString(Constants.DESC_PREGUNTA);
					final String sRespuesta = parse
							.getString(Constants.RESPESTA_PREGUNTA);
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

	/*
	 * Implementación del Interface que envía la lista al fragment.
	 */
	@Override
	public List<Castration> loadList() {
		return castrationList;
	}

	@Override
	public List<FreqAnswer> loadFreqAnswerList() {
		return freqAnswerList;
	}
	
	@Override
	public List<FreqAnswer> loadSuggestionList() {
		return suggestionList;
	}

}
