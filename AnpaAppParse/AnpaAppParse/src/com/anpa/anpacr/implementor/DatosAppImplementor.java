package com.anpa.anpacr.implementor;

import java.util.Date;

import com.anpa.anpacr.dataaccess.DatosAppDataAccess;

public class DatosAppImplementor {
private DatosAppDataAccess _dataAccess;
private static DatosAppImplementor _instancia;
	
	private DatosAppImplementor()
	{
		_dataAccess = new DatosAppDataAccess();
	}
	
	public static DatosAppImplementor getInstance(){
		if(_instancia == null)
			_instancia = new DatosAppImplementor();
		return _instancia;
	}
	
	/**
	 * Obtiene la fecha de actualizacion.
	 * @return
	 */
	public Date obtenerFechaActualizacion(){
		_dataAccess.openForWriting();
		Date fechaActualizacion = _dataAccess.obtenerFechaActualizacion();
		_dataAccess.close();
		return fechaActualizacion;
	}
	
	public void guardarNuevaFechaActualizacion(Date pNuevaFecha){
		_dataAccess.openForWriting();
		_dataAccess.establecerNuevaFecha(pNuevaFecha);
		_dataAccess.close();
	}
}
