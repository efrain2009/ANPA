package com.anpa.anpacr.domain;

import java.io.Serializable;
import java.util.Date;

public class Sponsor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4824006939865349715L;
	
	private String _lId;
	private String _snombre;
	private String _sdescripcion;
	private String _sURL;
	private Integer _iorden;
	private byte[] _bImagen;	
	private Date _dCreationDate;

	public Sponsor() {
		super();
	}
	
	public String get_lId() {
		return _lId;
	}

	public void set_lId(String _lId) {
		this._lId = _lId;
	}

	public Integer get_iorden() {
		return _iorden;
	}

	public void set_iorden(Integer _iorden) {
		this._iorden = _iorden;
	}

	public Date get_dCreationDate() {
		return _dCreationDate;
	}

	public void set_dCreationDate(Date _dCreationDate) {
		this._dCreationDate = _dCreationDate;
	}

	public String get_snombre() {
		return _snombre;
	}

	public void set_snombre(String _snombre) {
		this._snombre = _snombre;
	}

	public String get_sdescripcion() {
		return _sdescripcion;
	}

	public void set_sdescripcion(String _sdescripcion) {
		this._sdescripcion = _sdescripcion;
	}

	public byte[] get_bImagen() {
		return _bImagen;
	}

	public void set_bImagen(byte[] _bImagen) {
		this._bImagen = _bImagen;
	}

	public String get_sURL() {
		return _sURL;
	}

	public void set_sURL(String _sURL) {
		this._sURL = _sURL;
	}
	
}
	
