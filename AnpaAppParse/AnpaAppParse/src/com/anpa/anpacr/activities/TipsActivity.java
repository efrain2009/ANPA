package com.anpa.anpacr.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.anpa.anpacr.R;
import com.anpa.anpacr.adapter.TipListAdapter;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.Tip;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TipsActivity extends AnpaAppFraqmentActivity{

	List<Tip> tipsList;
	private TipListAdapter tipsAdapter;
	private ListView lv_tips;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_tip);

		tipsList = new ArrayList<Tip>();
		
		// Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_TIPS);

		Button btnAddLost = (Button) findViewById(R.id.btn_add_tip);
		btnAddLost.setOnClickListener(onAddTip);

		lv_tips = (ListView) findViewById(R.id.list_tips);
		tipsAdapter = new TipListAdapter(this, tipsList);
		lv_tips.setOnItemClickListener(onclickListTips);

		// Se carga la lista de tips
		try {
			new LoadTipsParse().execute(""); // El ".get()" Hace
												// esperar hasta que
												// el hilo termine.

		} catch (Exception e) {
			showMessage("Ups! Perdimos el rastro de los consejos. Intenta más tarde.");
			e.printStackTrace();
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
						Constants.ESTRELLA2_CONSEJO,
						Constants.ESTRELLA3_CONSEJO,
						Constants.ESTRELLA4_CONSEJO,
						Constants.ESTRELLA5_CONSEJO, Constants.VOTOS_CONSEJO));// selecciona
																				// las
				// columnas a
				// presentar
				List<ParseObject> results = query.find();

				for (ParseObject tipParse : results) {
					final String sIdTip = tipParse.getObjectId();
					final String sConsejo = tipParse
							.getString(Constants.DESCR_CONSEJO);
					final String sAutor = tipParse
							.getString(Constants.AUTOR_CONSEJO);
					final Integer raza = tipParse
							.getInt(Constants.RAZA_CONSEJO);
					final Integer especie = tipParse
							.getInt(Constants.ESPECIE_CONSEJO);
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
				tipsAdapter.notifyDataSetChanged();
				lv_tips.setAdapter(tipsAdapter);
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

	private OnItemClickListener onclickListTips = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Tip tip = (Tip) tipsAdapter.getItem(position);

			Intent intent = new Intent(TipsActivity.this,
					DetailTipActivity.class);
			intent.putExtra(Constants.ID_OBJ_DETAIL_TIP, tip);
			startActivity(intent);
		}
	};

	/**
	 * Listener del botón
	 */
	private OnClickListener onAddTip = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent(TipsActivity.this, AddTipActivity.class));
		}
	};

	/*
	 * Implementación del Interface que envía la lista al fragment.
	 */
	public List<Tip> loadList() {
		return tipsList;
	}
}
