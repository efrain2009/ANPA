package com.anpa.anpacr.dataaccess;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

import com.anpa.anpacr.AnpacrApplication;
import com.anpa.anpacr.database.DatabaseHelper;
import com.anpa.anpacr.database.TablaDatosApp;

public class DatosAppDataAccess extends AbstractDataAccess {
	private String _sConsultaFechaActualizacion = "SELECT "	+ TablaDatosApp.COL_FECHA_ULT_ACT + " FROM "
			+ TablaDatosApp.NOMBRE_TABLA;

	public DatosAppDataAccess() {
		_helper = new DatabaseHelper(AnpacrApplication.getContext());
	}

	/**
	 * Obtiene la última fecha de actualización de los parqueos
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public Date obtenerFechaActualizacion() {
		Cursor cFechaAct = _database.rawQuery(_sConsultaFechaActualizacion,	null);
		if (cFechaAct.moveToFirst()) {
			String sDate = cFechaAct.getString(0);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dFechaActualizacion = new Date();
			try {
				dFechaActualizacion = dateFormat.parse(sDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dFechaActualizacion;
		}
		else{
			//Si no existe registro en la BD, se crea uno nuevo
			ContentValues values = new ContentValues();
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String sNuevaFecha = formatter.format(new Date());
			values.put(TablaDatosApp.COL_FECHA_ULT_ACT, sNuevaFecha);
			_database.insert(TablaDatosApp.NOMBRE_TABLA, null, values);
			return null;
		}
	}
	
	/**
	 * Guarda la nueva fecha de actualización.
	 * @param fecha
	 */
	@SuppressLint("SimpleDateFormat")
	public void establecerNuevaFecha(Date fecha){
		ContentValues values = new ContentValues();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sNuevaFecha = formatter.format(fecha);
		values.put(TablaDatosApp.COL_FECHA_ULT_ACT, sNuevaFecha);
		_database.update(TablaDatosApp.NOMBRE_TABLA, values, null, null);
	}
}
