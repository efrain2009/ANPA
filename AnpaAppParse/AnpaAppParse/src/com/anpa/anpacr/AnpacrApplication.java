package com.anpa.anpacr;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;
import android.content.Context;

public class AnpacrApplication extends Application {

	private static AnpacrApplication _instancia;
	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "d4TSBDUrzXvj0MZaKWeo8OfJflWJAXS5BenBdJrL", "4CkjGy8T98wth9lB9ifGqEoJyRiX3RZPtYkmJEjZ"); //AnpaCRApp

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
	}
	
	public AnpacrApplication(){
		_instancia = this;
	}
	/**
	 * Gets the application context
	 * @return
	 */
	public static Context getContext() {
        return _instancia;
    }
}
