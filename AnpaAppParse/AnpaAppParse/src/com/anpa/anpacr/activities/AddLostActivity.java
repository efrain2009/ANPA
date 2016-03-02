package com.anpa.anpacr.activities;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.anpa.anpacr.R;
import com.anpa.anpacr.common.Constants;
import com.parse.ParseObject;

public class AddLostActivity extends AnpaAppFraqmentActivity {
	
	EditText editxt_nomMascota, editxt_contacto;
	Button saveLost;
	ImageView img_detail_lost;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_add_lost);
			
			//Btn de back (anterior)
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setTitle(Constants.TITLE_DESCRIPTION_LOST);
			
			saveLost = (Button) findViewById(R.id.btn_save_lost);
			saveLost.setOnClickListener(onSave);
			
			Button photoLost = (Button) findViewById(R.id.btn_add_lost_photo);
			photoLost.setOnClickListener(onSetPhoto);
			
			editxt_contacto = (EditText) findViewById(R.id.editxt_contacto);
			
			editxt_nomMascota = (EditText) findViewById(R.id.editxt_nom_mascota);
			
			img_detail_lost = (ImageView) findViewById(R.id.img_detail_lost);
		}
		
		/**
		 * Listener del botón
		 */
		private OnClickListener onSetPhoto= new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pickImage();
			}
		};
		
		/**
		 * Listener del botón
		 */
		private OnClickListener onSave = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ParseObject objLost = new ParseObject(Constants.TABLE_PERDIDOS);
				objLost.put(Constants.NOM_MASCOTA, editxt_nomMascota.getText().toString());
				objLost.put(Constants.NOM_DUENO, editxt_contacto.getText().toString());
				objLost.saveInBackground();	
				alertDialog ();
				saveLost.setEnabled(false);
			}
		};
		
		public void alertDialog (){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setMessage("Dentro de poco se publicara tu aviso")
	               .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // FIRE ZE MISSILES!
	                   }
	               }).create().show();
		}
		
		public void pickImage() {
			  Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			  intent.setType("image/*");
			  startActivityForResult(intent, 1);
			}
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
		    super.onActivityResult(requestCode, resultCode, data);
		    if (requestCode == 1 && resultCode == this.RESULT_OK) {
		        if (data == null) {
		            //Display an error
		            return;
		        }
		        try {
					InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
					
					Bitmap bmp = BitmapFactory.decodeStream(inputStream);
					img_detail_lost.setImageBitmap(bmp);
					
					
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
		    }
		}
}
