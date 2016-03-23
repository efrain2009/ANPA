package com.anpa.anpacr.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.anpa.anpacr.R;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.GenericNameValue;
import com.anpa.anpacr.domain.Tip;

public class DetailTipActivity extends SherlockFragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_tip);
		
		//Btn de back (anterior)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_TIPS);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Tip value = (Tip) extras.get(Constants.ID_OBJ_DETAIL_TIP);

			TextView txt_raza = (TextView) findViewById(R.id.txt_raza_consejo);
			
			String _sraza =  "";
			for (String raza : Constants.RACES) {
				String[] razaSplit = raza.split(",");
				if(razaSplit[0].contains(value.get_iRaza().toString())){
					_sraza = razaSplit[1];
					 break;
				}
			}
			
			String especie = readSpecies(value.get_iEspecie(), value.get_iRaza());
						
			txt_raza.setText(Constants.CONSEJOS_PARA + _sraza + " , " + especie);
			
			TextView txt_detail_consejo = (TextView) findViewById(R.id.txt_detail_consejo);
			txt_detail_consejo.setText(value.get_sConsejo());

			TextView txt_detail_autor = (TextView) findViewById(R.id.txt_detail_autor);
			txt_detail_autor.setText(value.get_sAuthor());
			
		}
		Button btnAddTip = (Button)findViewById(R.id.btn_add_tip);
		btnAddTip.setOnClickListener(onAddTip);
	}
	
	/**
	 * Listener del botón
	 */
	
	private OnClickListener onAddTip = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startActivity(new Intent(DetailTipActivity.this, AddTipActivity.class));
		}
	};
	
	/* carga la lista de razas de una especie */
    private String readSpecies(int specieId, int raza)
    {
    	ArrayList<GenericNameValue> speciesList = new ArrayList<GenericNameValue>();

        String selectedFile = "";
        switch (specieId) {
		case 2:
			selectedFile = "razas_gatos";
			break;
		case 3:
			selectedFile = "razas_aves";
			break;
		case 4: 
			selectedFile = "razas_peces";
			break;
		case 5:
			selectedFile = "razas_roedores";
			break;
		default:
			selectedFile = "razas_perros";
			break;
		}

        BufferedReader in = null;
        StringBuilder buf = new StringBuilder();
        try{
            InputStream is = getApplicationContext().getAssets().open(selectedFile + ".txt");
            in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            
            String races;
            boolean isFirst = true;
            while ((races = in.readLine()) != null ){
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(races);
            }

            String[] specieRacesArray = buf.toString().split("#");
            
            for (String race : specieRacesArray)
            {
                String[] values = race.split(",");
                if(raza == Integer.parseInt(values[0]))
                	return values[1];
            }
        }
        catch(IOException e) {
            Log.e("OJO", "Error opening asset ");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e("OJO", "Error closing asset ");
                }
            }
        }
		return "";
    }	
}
