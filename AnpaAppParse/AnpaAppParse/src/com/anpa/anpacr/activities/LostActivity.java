package com.anpa.anpacr.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.anpa.anpacr.R;
import com.anpa.anpacr.adapter.LostListAdapter;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.Lost;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class LostActivity extends AnpaAppFraqmentActivity{
	
	List<Lost> lostList;
	private LostListAdapter lostAdapter;
	private ListView lv_lost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_lost);

		lostList = new ArrayList<Lost>();
		
		//Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_LOST);
		
		Button btnAddLost = (Button) findViewById(R.id.btn_add_lost);
		btnAddLost.setOnClickListener(onAddLost);
		
		lv_lost = (ListView) findViewById(R.id.list_lost);
		lostAdapter = new LostListAdapter(this, lostList);
		lv_lost.setOnItemClickListener(onclickListLost);
		
		//Se carga la lista de perdidos
		try {
			new LoadLostParse().execute(""); //El ".get" Hace esperar hasta que el hilo termine.
					
		} catch (Exception e) {
			showMessage("Ups! Perdimos el rastro de las mascotas perdidas. Intenta más tarde.");
			e.printStackTrace();
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
				ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.TABLE_PERDIDOS);
				query.selectKeys(Arrays.asList(Constants.NOM_MASCOTA, Constants.NOM_DUENO,
						Constants.TELEFONO_PERDIDO,Constants.PROVINCIA_PERDIDO,Constants.CANTON_PERDIDO,
						Constants.FOTO_PERDIDO,Constants.DETALLE_PERDIDO, Constants.LATITUD_PERDIDO,Constants.LONGITUD_PERDIDO, Constants.RAZA_PERDIDO));// selecciona las columnas a presentar
				List<ParseObject> results = query.find();
				
				for (ParseObject lostParse : results) {
					final String sIdLost = lostParse.getObjectId();
					final String sNomMascota = lostParse.getString(Constants.NOM_MASCOTA);
					final String sNomDueno = lostParse.getString(Constants.NOM_DUENO);
					final String sTelefono = lostParse.getString(Constants.TELEFONO_PERDIDO);
					final int iProvincia = lostParse.getInt(Constants.PROVINCIA_PERDIDO);
					final int iCanton = lostParse.getInt(Constants.CANTON_PERDIDO);
					final String sDetalle = lostParse.getString(Constants.DETALLE_PERDIDO);
					final String sLatitud = lostParse.getString(Constants.LATITUD_PERDIDO);
					final String sLongitud = lostParse.getString(Constants.LONGITUD_PERDIDO);
					final String sRaza = lostParse.getString(Constants.RAZA_PERDIDO);
					ParseFile imageFile = lostParse
							.getParseFile(Constants.FOTO_PERDIDO);
					final Date dateCreated  = lostParse.getCreatedAt();
					
					SimpleDateFormat dt = new SimpleDateFormat(
							"dd/MM/yyyy hh:mm aaa");
					String date = dt.format(dateCreated);
									
					Lost newLost = new Lost(sIdLost, sNomMascota, sNomDueno,sTelefono, iProvincia, iCanton, sDetalle, sRaza, date, imageFile.getData(), sLatitud, sLongitud);
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
				lostAdapter.notifyDataSetChanged();
				lv_lost.setAdapter(lostAdapter);
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
	
	private OnItemClickListener onclickListLost = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Lost lost = (Lost) lostAdapter.getItem(position);
						
			Intent intent = new Intent(LostActivity.this, DetailLostActivity.class);
			intent.putExtra(Constants.ID_OBJ_DETAIL_LOST, lost);
			startActivity(intent);
		}
	};
	
	/**
	 * Listener del botón
	 */
	private OnClickListener onAddLost = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startActivity(new Intent(LostActivity.this, AddLostActivity.class));
		}
	};
}
