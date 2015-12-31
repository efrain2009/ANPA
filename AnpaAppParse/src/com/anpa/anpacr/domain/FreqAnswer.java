package com.anpa.anpacr.domain;

import java.io.Serializable;
import java.util.Date;

public class FreqAnswer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4824006939865349715L;
	
	private String _lId;
	private String _spregunta;
	private String _srespuesta;
	private Integer _iorden;
	private Date _dCreationDate;

	public FreqAnswer() {
		super();
	}
	
	public String get_lId() {
		return _lId;
	}

	public void set_lId(String _lId) {
		this._lId = _lId;
	}

	public String get_spregunta() {
		return _spregunta;
	}

	public void set_spregunta(String _spregunta) {
		this._spregunta = _spregunta;
	}

	public String get_srespuesta() {
		return _srespuesta;
	}

	public void set_srespuesta(String _srespuesta) {
		this._srespuesta = _srespuesta;
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
}
	
