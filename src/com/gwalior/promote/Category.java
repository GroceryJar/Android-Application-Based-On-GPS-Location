package com.gwalior.promote;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Category extends Fragment {
	ArrayList<String> items;
	ArrayList<String> itemsId;
	ArrayAdapter<String> adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.category, container, false);
		ListView listview = (ListView) rootView.findViewById(R.id.your_listview);

		// EDITED Code
		//String[] items = new String[] {"Electronics Offer","Fashin Offers","Food Offers","Books Offers","More Offers","Electronics Offer","Fashin Offers","Food Offers","Books Offers","More Offers","Electronics Offer","Fashin Offers","Food Offers","Books Offers","More Offers" };
		items = new ArrayList<String>();
		itemsId = new ArrayList<String>();
		
		items.add("Electronics Offer");
		itemsId.add("Electronics");
		items.add("Clothes");
		itemsId.add("Clothes");
		items.add("Personal Care");
		itemsId.add("Personal Care");
		items.add("More Offers");
		itemsId.add("More Offers");
		
		
		adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, items);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				Intent i;
				i = new Intent(getActivity(), ItemListActivity.class);
				i.putExtra("cat", itemsId.get(arg2));
				startActivity(i);
			}
		});
		
		return rootView;

	}

}
