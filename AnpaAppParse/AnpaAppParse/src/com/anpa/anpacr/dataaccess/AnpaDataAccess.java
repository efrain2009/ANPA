package com.anpa.anpacr.dataaccess;

import java.util.ArrayList;
import java.util.List;

import com.anpa.anpacr.AnpacrApplication;
import com.anpa.anpacr.database.DatabaseHelper;
import com.anpa.anpacr.database.TablaParqueo;


import android.content.ContentValues;
import android.database.Cursor;
import android.location.Location;


public class AnpaDataAccess extends AbstractDataAccess {
//	private String _sConsultaParqueos = "SELECT * FROM " + TablaParqueo.NOMBRE_TABLA;
//	private String _sConsultaParqueosMapa = "SELECT "+ 
//			TablaParqueo.COL_PARQUEO_ID + ","+
//			TablaParqueo.COL_PARQUEO_NOMBRE + ","+
//			TablaParqueo.COL_PARQUEO_ESP_DISPON + ","+
//			TablaParqueo.COL_PARQUEO_LATITUD + ","+
//			TablaParqueo.COL_PARQUEO_LONGITUD +
//			" FROM " + TablaParqueo.NOMBRE_TABLA;
//	private String _sConsultaParqueosBusqueda = "SELECT "+ 
//			TablaParqueo.COL_PARQUEO_ID + ","+
//			TablaParqueo.COL_PARQUEO_NOMBRE + ","+
//			TablaParqueo.COL_PARQUEO_DIREC + ","+
//			TablaParqueo.COL_PARQUEO_ESP_DISPON +
//			" FROM " + TablaParqueo.NOMBRE_TABLA + " WHERE "+
//			TablaParqueo.COL_PARQUEO_NOMBRE + " LIKE ";
//	private String _sConsultaInfoParqueo = "SELECT "+
//			TablaParqueo.COL_PARQUEO_NOMBRE + ","+
//			TablaParqueo.COL_PARQUEO_DIREC + ","+
//			TablaParqueo.COL_PARQUEO_TEL1 + ","+
//			TablaParqueo.COL_PARQUEO_TEL2 + ","+
//			TablaParqueo.COL_PARQUEO_EMAIL+ ","+
//			TablaParqueo.COL_PARQUEO_ESP_DISPON + ","+
//			TablaParqueo.COL_PARQUEO_IMAGEN + ", "+
//			TablaParqueo.COL_PARQUEO_LATITUD + ", "+
//			TablaParqueo.COL_PARQUEO_LONGITUD +
//			" FROM " + TablaParqueo.NOMBRE_TABLA +
//			" WHERE " + TablaParqueo.COL_PARQUEO_ID + " = ";
//	
//	public ParqueoDataAccess()
//	{
//		_helper = new DatabaseHelper(AnpascrApplication.getContext());
//	}
//		
//	/**
//	 * Consulta la lista de parqueos almacenadas en SQLite.
//	 * @return cursor: Lista de rutas.
//	 */
//	public List<Parqueo> obtenerInfoParqueos(){
//		List<Parqueo> lParqueos = new ArrayList<Parqueo>();
//		Cursor cursorParqueos = _database.rawQuery(_sConsultaParqueos, null);
//		Parqueo parqueo;
//		if (cursorParqueos.moveToFirst()){
//		     //Se recorre el cursor, registro por registro.
//		     do {
//		          String nId = cursorParqueos.getString(0);
//		          String sNombre = cursorParqueos.getString(1);
//		          String sDireccion = cursorParqueos.getString(2);
//		          String sTelefono1 = cursorParqueos.getString(3);
//		          String sTelefono2 = cursorParqueos.getString(4);
//		          String sEmail = cursorParqueos.getString(5);
//		          String sArrayCriterios = cursorParqueos.getString(6);
//		          byte[] sUrlImagen = cursorParqueos.getBlob(7);
//		          int nEspaciosDisponibles = cursorParqueos.getInt(8);
//		          String sLatitud = cursorParqueos.getString(9);
//		          String sLongitud = cursorParqueos.getString(10);
//		          
//		          parqueo = new Parqueo(nId, sNombre, sDireccion, sTelefono1, sTelefono2, sEmail, sArrayCriterios, 
//		        		  nEspaciosDisponibles, sLatitud, sLongitud, sUrlImagen);
//		          lParqueos.add(parqueo);
//		     } while(cursorParqueos.moveToNext());
//		}
//		return lParqueos;
//	}
//	
//	/**
//	 * Obtiene la info. básica para mostrar los parqueos en el mapa
//	 * @return lista de parqueos
//	 */
//	public List<Parqueo> obtenerInfoParqueosMapa(Double pLatitud, Double pLongitud){
//		Location ubicacionActual = new Location("Actual");
//		ubicacionActual.setLatitude(pLatitud);
//		ubicacionActual.setLongitude(pLongitud);
//		Location ubicacionParqueo = new Location("Parqueo");		
//		List<Parqueo> lParqueos = new ArrayList<Parqueo>();
//		Cursor cursorParqueos = _database.rawQuery(_sConsultaParqueosMapa, null);
//		Parqueo parqueo;
//		if (cursorParqueos.moveToFirst()){
//		     //Se recorre el cursor, registro por registro.
//		     do {
//		          String nId = cursorParqueos.getString(0);
//		          String sNombre = cursorParqueos.getString(1);
//		          int nEspaciosDisponibles = cursorParqueos.getInt(2);
//		          String sLatitud = cursorParqueos.getString(3);
//		          String sLongitud = cursorParqueos.getString(4);
//		          
//		        ubicacionParqueo.setLatitude(Double.parseDouble(sLatitud));
//		      	ubicacionParqueo.setLongitude(Double.parseDouble(sLongitud));
//		      	float distance = ubicacionActual.distanceTo(ubicacionParqueo);
//		      	if(distance <= 2000){
//		          parqueo = new Parqueo(nId, sNombre, nEspaciosDisponibles, sLatitud, sLongitud);
//		          lParqueos.add(parqueo);
//		      	}
//		     } while(cursorParqueos.moveToNext());
//		}
//		return lParqueos;
//	}
//	
//	/** 
//	 * Retorna la información de todos los parqueos en la BD
//	 * @return
//	 */
//	public List<Parqueo> obtenerInfoTodosParqueosMapa() {
//		List<Parqueo> lParqueos = new ArrayList<Parqueo>();
//		Cursor cursorParqueos = _database.rawQuery(_sConsultaParqueosMapa, null);
//		Parqueo parqueo;
//		if (cursorParqueos.moveToFirst()) {
//			// Se recorre el cursor, registro por registro.
//			do {
//				String nId = cursorParqueos.getString(0);
//				String sNombre = cursorParqueos.getString(1);
//				int nEspaciosDisponibles = cursorParqueos.getInt(2);
//				String sLatitud = cursorParqueos.getString(3);
//				String sLongitud = cursorParqueos.getString(4);
//				
//				parqueo = new Parqueo(nId, sNombre, nEspaciosDisponibles, sLatitud, sLongitud);
//				lParqueos.add(parqueo);
//			} while (cursorParqueos.moveToNext());
//		}
//		return lParqueos;
//	}
//	
//	/**
//	 * Obtiene la info. básica para mostrar los parqueos en la lista del buscador.
//	 * @return lista de parqueos
//	 */
//	public List<Parqueo> obtenerInfoParqueosBusqueda(String pParametro){
//		List<Parqueo> lParqueos = new ArrayList<Parqueo>();
//		String sQueryBusqueda = _sConsultaParqueosBusqueda + "'%"+pParametro+"%'";
//		//sQueryBusqueda.replace("P*", pParametro);
//		
//		Cursor cursorParqueos = _database.rawQuery(sQueryBusqueda, null);
//		Parqueo parqueo;
//		if (cursorParqueos.moveToFirst()){
//		     //Se recorre el cursor, registro por registro.
//		     do {
//		          String nId = cursorParqueos.getString(0);
//		          String sNombre = cursorParqueos.getString(1);
//		          String sDireccion = cursorParqueos.getString(2);
//		          int nEspaciosDisponibles = cursorParqueos.getInt(3);
//		          
//		          parqueo = new Parqueo(nId, sNombre, sDireccion, nEspaciosDisponibles);
//		          lParqueos.add(parqueo);
//		     } while(cursorParqueos.moveToNext());
//		}
//		return lParqueos;
//	}
//	
//	/**
//	 * Se insertan valores en la "TablaParqueo".
//	 */
//	public void insertarParqueo(Parqueo parqueo){
//		ContentValues values = new ContentValues();
//		values.put(TablaParqueo.COL_PARQUEO_ID, parqueo.get_sId());
//		values.put(TablaParqueo.COL_PARQUEO_NOMBRE, parqueo.get_sNombre());
//		values.put(TablaParqueo.COL_PARQUEO_DIREC, parqueo.get_sDireccion());
//		values.put(TablaParqueo.COL_PARQUEO_TEL1, parqueo.get_sTelefono1());
//		values.put(TablaParqueo.COL_PARQUEO_TEL2, parqueo.get_sTelefono2());
//		values.put(TablaParqueo.COL_PARQUEO_EMAIL, parqueo.get_sEmail());
//		values.put(TablaParqueo.COL_PARQUEO_CRITERIOS, parqueo.get_arrayCriterios());
//		values.put(TablaParqueo.COL_PARQUEO_IMAGEN, parqueo.get_bImagen());
//		values.put(TablaParqueo.COL_PARQUEO_ESP_DISPON, parqueo.get_nEspaciosDisponibles());
//		values.put(TablaParqueo.COL_PARQUEO_LATITUD, parqueo.get_sLatitud());
//		values.put(TablaParqueo.COL_PARQUEO_LONGITUD, parqueo.get_sLongitud());
//
//		_database.insert(TablaParqueo.NOMBRE_TABLA, null, values);
//	}
//	
//	/**
//	 * Consulta la información de un parqueo mediante su Id.
//	 * @param pIdParqueo
//	 * @return
//	 */
//	public Parqueo obtenerInfoParqueo(String pIdParqueo){
//		String sQueryBusqueda = _sConsultaInfoParqueo + "'" + pIdParqueo + "'";
//		//sQueryBusqueda.replace("P*", pParametro);
//		
//		Cursor cursorParqueos = _database.rawQuery(sQueryBusqueda, null);
//		Parqueo parqueo = null;
//		if (cursorParqueos.moveToFirst()) {
//			String sNombre = cursorParqueos.getString(0);
//			String sDireccion = cursorParqueos.getString(1);
//			String sTel1 = cursorParqueos.getString(2);
//			String sTel2 = cursorParqueos.getString(3);
//			String sEmail = cursorParqueos.getString(4);
//			int nEspaciosDisponibles = cursorParqueos.getInt(5);
//			byte[] bImagen = cursorParqueos.getBlob(6);
//			String sLatitud = cursorParqueos.getString(7);
//			String sLongitud = cursorParqueos.getString(8);
//
//			parqueo = new Parqueo("", sNombre, sDireccion, sTel1, sTel2,
//					sEmail, null, nEspaciosDisponibles, sLatitud, sLongitud, bImagen);
//		}
//		return parqueo;
//	}
//	
//	/**
//	 * Se actualiza el estado del negocio
//	 * @param pNegocioId
//	 * @param pEstado
//	 */
//	public void actualizarEspaciosParqueo(List<Parqueo> lParqueos){
//		ContentValues values = new ContentValues();
//		for(Parqueo parqueo:lParqueos){
//			values.put(TablaParqueo.COL_PARQUEO_ESP_DISPON, parqueo.get_nEspaciosDisponibles());
//			String sCondicion = TablaParqueo.COL_PARQUEO_ID + " = '" + parqueo.get_sId() + "'";
//			_database.update(TablaParqueo.NOMBRE_TABLA, values, sCondicion, null);
//		}
//	}
//	
//	/**
//	 * Se elimina la tabla de parqueos.
//	 */
//	public void borrarParqueos(){
//		_database.delete(TablaParqueo.NOMBRE_TABLA, null, null);
//	}
}
