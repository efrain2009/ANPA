package com.anpa.anpacr.domain;

import java.io.Serializable;
import java.util.Date;

public class Tip implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4824006939865349715L;
	
	private String _lId;
	private String _sAuthor;
	private String _sConsejo;
	private Date _sDate;
	private Integer _i1Estrella;
	private Integer _i2Estrella;
	private Integer _i3Estrella;
	private Integer _i4Estrella;
	private Integer _i5Estrella;
	private Integer _iTotalVotos;
	private Integer _iRaza;
	private Integer _iEspecie;
	
	public Tip() {
		super();
	}
	
	
	public String get_lId() {
		return _lId;
	}


	public void set_lId(String _lId) {
		this._lId = _lId;
	}


	public String get_sAuthor() {
		return _sAuthor;
	}
	public void set_sAuthor(String _sAuthor) {
		this._sAuthor = _sAuthor;
	}
	public String get_sConsejo() {
		return _sConsejo;
	}
	public void set_sConsejo(String _sConsejo) {
		this._sConsejo = _sConsejo;
	}
	public Date get_sDate() {
		return _sDate;
	}
	public void set_sDate(Date _sDate) {
		this._sDate = _sDate;
	}
	public Integer get_i1Estrella() {
		return _i1Estrella;
	}
	public void set_i1Estrella(Integer _i1Estrella) {
		this._i1Estrella = _i1Estrella;
	}
	public Integer get_i2Estrella() {
		return _i2Estrella;
	}
	public void set_i2Estrella(Integer _i2Estrella) {
		this._i2Estrella = _i2Estrella;
	}
	public Integer get_i3Estrella() {
		return _i3Estrella;
	}
	public void set_i3Estrella(Integer _i3Estrella) {
		this._i3Estrella = _i3Estrella;
	}
	public Integer get_i4Estrella() {
		return _i4Estrella;
	}
	public void set_i4Estrella(Integer _i4Estrella) {
		this._i4Estrella = _i4Estrella;
	}
	public Integer get_i5Estrella() {
		return _i5Estrella;
	}
	public void set_i5Estrella(Integer _i5Estrella) {
		this._i5Estrella = _i5Estrella;
	}
	public Integer get_iRaza() {
		return _iRaza;
	}
	public void set_iRaza(Integer _iRaza) {
		this._iRaza = _iRaza;
	}
	public Integer get_iEspecie() {
		return _iEspecie;
	}
	public void set_iEspecie(Integer _iEspecie) {
		this._iEspecie = _iEspecie;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer get_iTotalVotos() {
		return _iTotalVotos;
	}
	public void set_iTotalVotos(Integer _iTotalVotos) {
		this._iTotalVotos = _iTotalVotos;
	}	
	
}

