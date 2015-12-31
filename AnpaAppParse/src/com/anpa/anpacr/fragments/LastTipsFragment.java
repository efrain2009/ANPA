package com.anpa.anpacr.fragments;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.anpa.anpacr.R;
import com.anpa.anpacr.activities.DetailTipActivity;
import com.anpa.anpacr.adapter.TipListAdapter;
import com.anpa.anpacr.common.Constants;
import com.anpa.anpacr.domain.Tip;
import com.anpa.anpacr.fragments.LastTipsFragment;

public class LastTipsFragment extends SherlockFragment{
	
	private TipListAdapter tipAdapter;
	private ListView lv_tips;
	
	OnLoadListListener onLoadListListener; 	//Interface para recibir la lista desde el activity.

    //El activity debe tener esta implementación.
    public interface OnLoadListListener {
        public List<Tip> loadList();
    }
	
    /*
	 * Creación del fragment
	 */
		@Override
		public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			View view = inflater.inflate(R.layout.fragment_list_tips, container, false);
			
			lv_tips = (ListView) view.findViewById(R.id.list_tips);
			
			//Carga los datos obtenidos del activity, llamando al interface del activity
			 List<Tip> tiplist = onLoadListListener.loadList();
			
			tipAdapter = new TipListAdapter(getActivity(), tiplist);
			lv_tips.setAdapter(tipAdapter);
			lv_tips.setOnItemClickListener(onclickListTip);			

			return view;
		}
		
		@Override
		public void onAttach(Activity activity){
			super.onAttach(activity);
			/**
			 * Se instancia el interface
			 */
			try {
	            onLoadListListener = (OnLoadListListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnLoadListListener");
	        }
		}
		
	private OnItemClickListener onclickListTip = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Tip tip = (Tip) tipAdapter.getItem(position);
						
			Intent intent = new Intent(getActivity(), DetailTipActivity.class);
			intent.putExtra(Constants.ID_OBJ_DETAIL_TIP, tip);
			startActivity(intent);
		}
	};	
}
