package com.anpa.anpacr.database;

import com.anpa.anpacr.common.Constants;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Estructura de la tabla "Parqueo"
 * @author Efraín Peraza
 * 10.03.14
 */
public class TablaParqueo implements IDatabaseTable{

	@Override
	public void onCreate(SQLiteDatabase pDatabase) 
	{
		try 
		{
		   pDatabase.execSQL(SQL_CREATE_TABLE);
	       Log.i(Constants.LOG_TAG, "Create Table PARQUEO done...");

		}
		catch (Exception e) 
		{
			Log.e(Constants.LOG_TAG, "Error creating table PARQUEO.");
			e.printStackTrace();
		}	
	}

	@Override
	public void onUpgrade(SQLiteDatabase pDatabase, int pOldVersion, int pNewVersion) 
	{
		pDatabase.execSQL(SQL_UPGRADE_TABLE);
		onCreate(pDatabase);
	}
	
	
	//Table Name
	public static final String NOMBRE_TABLA = "PARQUEO";
	
	//Columns of the table
	public static final String COL_PARQUEO_ID = "PARQUEO_ID";
	public static final String COL_PARQUEO_NOMBRE = "PARQUEO_NOMBRE";
	public static final String COL_PARQUEO_DIREC = "PARQUEO_DIRECCION";
	public static final String COL_PARQUEO_TEL1 = "PARQUEO_TEL1";
	public static final String COL_PARQUEO_TEL2 = "PARQUEO_TEL2";
	public static final String COL_PARQUEO_EMAIL = "PARQUEO_EMAIL";
	public static final String COL_PARQUEO_CRITERIOS = "PARQUEO_CRITERIOS";
	public static final String COL_PARQUEO_IMAGEN = "PARQUEO_IMAGEN";
	public static final String COL_PARQUEO_ESP_DISPON = "PARQUEO_ESP_DISP";
	public static final String COL_PARQUEO_LATITUD = "PARQUEO_LATITUD";
	public static final String COL_PARQUEO_LONGITUD = "PARQUEO_LONGITUD";

	//Creation of the table 
	private static final String SQL_CREATE_TABLE = "CREATE TABLE " + NOMBRE_TABLA + "( "
													+ COL_PARQUEO_ID + " TEXT PRIMARY KEY, "
													+ COL_PARQUEO_NOMBRE + " TEXT NOT NULL, "
													+ COL_PARQUEO_DIREC + " TEXT NOT NULL, "
													+ COL_PARQUEO_TEL1 + " TEXT,"
													+ COL_PARQUEO_TEL2 + " TEXT, "
													+ COL_PARQUEO_EMAIL + " TEXT, "
													+ COL_PARQUEO_CRITERIOS + " TEXT NOT NULL, "
													+ COL_PARQUEO_IMAGEN + " TEXT,"
													+ COL_PARQUEO_ESP_DISPON + " INTEGER, "
													+ COL_PARQUEO_LATITUD + " TEXT,"
													+ COL_PARQUEO_LONGITUD + " TEXT);";
	
	
	private static final String SQL_UPGRADE_TABLE = "DROP TABLE IF EXISTS " + NOMBRE_TABLA + ";";

}
