package com.anpa.anpacr.fragments;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.anpa.anpacr.R;
import com.anpa.anpacr.adapter.SponsorListAdapter;
import com.anpa.anpacr.domain.Sponsor;

public class SponsorFragment extends SherlockFragment{
	
	private SponsorListAdapter sponsorAdapter;
	private ListView lv_sponsor;
	
	OnLoadSponsorListListener onLoadSponsorListListener; //Interface para recibir la lista desde el activity.

    //El activity debe tener esta implementaci�n.
    public interface OnLoadSponsorListListener {
        public List<Sponsor> loadSponsorList();
    }

	/*
	 * Creaci�n del fragment
	 */
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_list_sponsor, container, false);
		/**
		 * Se instancia el interface
		 */
		try {
            onLoadSponsorListListener = (OnLoadSponsorListListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnLoadSponsorListListener");
        }
		
		lv_sponsor = (ListView) view.findViewById(R.id.list_sponsor);
		
		//Carga los datos obtenidos del activity, llamando al interface del activity
		 List<Sponsor> sponsorlist = onLoadSponsorListListener.loadSponsorList();
		
		sponsorAdapter = new SponsorListAdapter(getActivity(), sponsorlist);
		lv_sponsor.setAdapter(sponsorAdapter);
		lv_sponsor.setOnItemClickListener(onclickListSponsor);

		return view;
	}
	
	private OnItemClickListener onclickListSponsor = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Sponsor sponsor = (Sponsor) sponsorAdapter.getItem(position);
			/*
			Intent intent = new Intent(getActivity(), DetailSponsorActivity.class);
			intent.putExtra(Constants.ID_OBJ_DETAIL_NEWS, sponsor);
			startActivity(intent);
			*/
		}
	};	
}
