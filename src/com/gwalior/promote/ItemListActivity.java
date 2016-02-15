package com.gwalior.promote;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gwalior.connectivity.UserFunctions;

public class ItemListActivity extends Activity {
	
	ArrayList<String> items;
	ArrayList<Long> itemsId;
	ArrayAdapter<String> adapter;
	JSONObject products;
	JSONObject product;
	String category;
	UserFunctions user;
	boolean moreProduct=true;
	int total;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);
		ListView listview = (ListView) findViewById(R.id.itemList);
		
		category = getIntent().getExtras().getString("cat");

		items = new ArrayList<String>();
		itemsId = new ArrayList<Long>();
		
		user = new UserFunctions();
		products = user.getProductList(category, 0, 10);
		
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
		
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
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
						products = user.getProductList(category, totalItemCount, totalItemCount+10);
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
				i = new Intent(getApplicationContext(), ItemActivity.class);
				i.putExtra("pid", String.valueOf(itemsId.get(arg2)));
				startActivity(i);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logged, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	   try{
	    switch (item.getItemId()) {
	    case R.id.menuLogout:            
	    	UserFunctions userFunctions = new UserFunctions();
	    	Services.loc = null;
		    if(userFunctions.isUserLoggedIn(getApplicationContext())){
		    	userFunctions.logoutUser(getApplicationContext());
		    }
		    Intent login = new Intent(getApplicationContext(), MainActivity.class);
		    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    startActivity(login);
	        return true;        
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	   }catch(Exception e){
	      e.printStackTrace();
	   }
	return false;
	}

}
