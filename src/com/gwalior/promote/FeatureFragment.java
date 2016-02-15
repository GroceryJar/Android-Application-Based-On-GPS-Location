package com.gwalior.promote;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.gwalior.connectivity.UserFunctions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class FeatureFragment extends Fragment {
	
	ArrayList<String> items;
	ArrayList<Long> itemsId;
	ArrayAdapter<String> adapter;
	JSONObject products;
	JSONObject product;
	UserFunctions user;
	boolean moreProduct=true;
	int total;
	int pType=2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.featured_list, container, false);
		ListView listview = (ListView) rootView.findViewById(R.id.featured_list);
		
		items = new ArrayList<String>();
		itemsId = new ArrayList<Long>();
		
		user = new UserFunctions();
		products = user.getSpecialList(pType,0,10);
		
		try {
			if(products.getInt("success")==1)
			{
				total = products.getInt("total");
				for(int i=1; i<=total; i++)
				{
					product = products.getJSONObject("p"+i);
					items.add(product.getString("name"));
					itemsId.add(Long.parseLong(product.getString("id")));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, items);
		listview.setAdapter(adapter);
		
		listview.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view,int firstVisibleItem, int visibleItemCount,int totalItemCount) {
				int lastItem = firstVisibleItem + visibleItemCount;
				if(lastItem == totalItemCount){
					if(moreProduct)
					{
						products = user.getSpecialList(pType,totalItemCount,totalItemCount+10);
						try {
							if(products.getInt("success")==1)
							{
								total = products.getInt("total");
								for(int i=1; i<=total; i++)
								{
									product = products.getJSONObject("p"+i);
									items.add(product.getString("name"));
									itemsId.add(Long.parseLong(product.getString("id")));
								}
							}
							else
								moreProduct=false;
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						adapter.notifyDataSetChanged();
					}
		        }
			}
		});
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				Intent i;
				i = new Intent(getActivity(), ItemActivity.class);
				i.putExtra("pid", String.valueOf(itemsId.get(arg2)));
				startActivity(i);
			}
		});
		return rootView;

	}
}
