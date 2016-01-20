package com.anpa.anpacr.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anpa.anpacr.AnpacrApplication;

/**
 * Custom implementation of the SQLOpenHelper to handle the data access
 * @author mgomez
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	/**
	 * Tables of the database
	 */
	private List<IDatabaseTable> _tables;
	/**
	 * Unique instance of the class (Singleton)
	 */
	private static DatabaseHelper _instance;
	
	/***
	 * Private Class constructor (Singleton)
	 * @param pContext Context of the application
	 */
	public DatabaseHelper(Context pContext) 
	{
		super(pContext, DataConstants.DATABASE_NAME, null, DataConstants.DATABASE_VERSION);
		
		_tables = new ArrayList<IDatabaseTable>();
		
		//Add the required tables to the list
		_tables.add(new TablaParqueo());
		_tables.add(new TablaDatosApp());
	}
	
	/**
	 * Called when the database is created. 
	 */
	@Override
	public void onCreate(SQLiteDatabase pDatabase) 
	{
		
		//Create the database tables
		for(IDatabaseTable table : _tables)
		{
			table.onCreate(pDatabase);
		}
		
		//Create additional structures via SQL
	}

	/**
	 * Called when the database is upgraded. 
	 */
	@Override
	public void onUpgrade(SQLiteDatabase pDatabase, int pOldVersion, int pNewVersion) 
	{
		//Update each of the tables
		for(IDatabaseTable table : _tables)
		{
			table.onUpgrade(pDatabase, pOldVersion, pNewVersion);
		}
	}
	
	/**
	 * Returns the unique instance of the class
	 * @param pContext Context to be used to create the instance in case it doesnt exits
	 * @return Unique instance of DatabaseHelper
	 */
	public static DatabaseHelper getInstance()
	{
		if (_instance == null)
			_instance = new DatabaseHelper(AnpacrApplication.getContext());
		return _instance;
	}
}
