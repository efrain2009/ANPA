package com.anpa.anpacr.activities;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class AnpaAppFraqmentActivity extends SherlockFragmentActivity{

	@Override
	   protected void onCreate(Bundle savedInstanceState)
	   {
	       super.onCreate(savedInstanceState);
	   }
	   
	   @Override
	   public boolean onMenuItemSelected(int featureId, MenuItem p)
	       {
	               if (p.getItemId() == android.R.id.home)
	               {
	                       finish();
	               }
	               return true;
	       }
}
