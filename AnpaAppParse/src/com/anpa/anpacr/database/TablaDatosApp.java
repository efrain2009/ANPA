package com.anpa.anpacr.database;

import com.anpa.anpacr.common.Constants;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Estructura de la tabla "DatosApp", donde se guarda info relacionada
 * con la app.
 * @author Efraín Peraza
 * 11.03.14
 */
public class TablaDatosApp implements IDatabaseTable{

	@Override
	public void onCreate(SQLiteDatabase pDatabase) 
	{
		try 
		{
		   pDatabase.execSQL(SQL_CREATE_TABLE);
	       Log.i(Constants.LOG_TAG, "Create Table DATOSAPP done...");

		}
		catch (Exception e) 
		{
			Log.e(Constants.LOG_TAG, "Error creating table DATOSAPP.");
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
	public static final String NOMBRE_TABLA = "DATOSAPP";
	
	//Columns of the table
	public static final String COL_FECHA_ULT_ACT= "DATOSAPP_FEC_ULT_ACT";

	//Creation of the table 
	private static final String SQL_CREATE_TABLE = "CREATE TABLE " + NOMBRE_TABLA + "( "
													+ COL_FECHA_ULT_ACT + " DATE);";
	
	
	private static final String SQL_UPGRADE_TABLE = "DROP TABLE IF EXISTS " + NOMBRE_TABLA + ";";

}
