package com.anpa.anpacr.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anpa.anpacr.R;
import com.anpa.anpacr.domain.Tip;

public class TipListAdapter extends BaseAdapter{
	
	protected List<Tip> tipList;
	protected Activity activity;


	public TipListAdapter(Activity activity, List<Tip> tipListFromParse) {
		this.activity = activity;
		this.tipList = tipListFromParse;
	}


	@Override
	public int getCount() {
		return tipList.size();
	}


	@Override
	public Object getItem(int position) {
		return tipList.get(position);
	}


	@Override
	public long getItemId(int position) {
		return new Long(0);
		//return tipList.get(position).get_lId();
	}


	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		View view = contentView;
		
		if(contentView == null){
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.activity_list_item_tip, null);
		}
		
		Tip item = tipList.get(position);
		
		TextView txt_title_tip = (TextView) view.findViewById(R.id.txt_title_consejo);
		txt_title_tip.setText(item.get_sConsejo());		
		String txtPreviewConsejo = item.get_sConsejo();
		if(txtPreviewConsejo.length() > 30)
			txtPreviewConsejo = txtPreviewConsejo.substring(0,30) + "...";
		txt_title_tip.setText(txtPreviewConsejo);
		
		TextView txt_autor = (TextView) view.findViewById(R.id.txt_autor);
		txt_autor.setText("Por: ".concat(item.get_sAuthor()));
		
		return view;
	}
	
	public void add(Tip tip){
		tipList.add(tip);
	}
	
	public void clearAdapter(){
		tipList.clear();
	}	
}
