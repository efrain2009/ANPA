package com.anpa.anpacr.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anpa.anpacr.R;
import com.anpa.anpacr.domain.Lost;

public class LostListAdapter extends BaseAdapter{
	
	protected Activity activity;
	protected List<Lost> lostList;


	public LostListAdapter(Activity activity, List<Lost> lostListFromParse) {
		this.activity = activity;
		this.lostList = lostListFromParse;
	}


	@Override
	public int getCount() {
		return lostList.size();
	}


	@Override
	public Object getItem(int position) {
		return lostList.get(position);
	}


	@Override
	public long getItemId(int position) {
		return new Long(0);
		//return lostList.get(position).get_lId();
	}


	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		View view = contentView;
		
		if(contentView == null){
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.activity_list_item_lost, null);
		}
		
		Lost item = lostList.get(position);
		
		TextView txt_title_lost = (TextView) view.findViewById(R.id.txt_title_lost);
		txt_title_lost.setText(item.get_snombreDueno());
				
		TextView txt_descrip_lost = (TextView) view.findViewById(R.id.txt_descrip_lost);
		txt_descrip_lost.setText(item.get_sdetalle());
		
		String txtPreviewDescrip = item.get_sdetalle();
		if(txtPreviewDescrip.length() > 30)
			txtPreviewDescrip = txtPreviewDescrip.substring(0,30) + "...";
		txt_descrip_lost.setText(txtPreviewDescrip);
		
		ImageView img_lost = (ImageView) view.findViewById(R.id.img_lost);
		Bitmap bmpImage = BitmapFactory.decodeByteArray(
			      item.get_bImagen(), 0, item.get_bImagen().length);
		img_lost.setImageBitmap(bmpImage);
		
		return view;
	}
	
	public void add(Lost lost){
		lostList.add(lost);
	}
	
	public void clearAdapter(){
		lostList.clear();
	}	
}