package com.gwalior.promote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gwalior.connectivity.ImageFromUrl;

public class ProductDetailFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.item_detail, container, false);
		ImageView iv = (ImageView)rootView.findViewById(R.id.productImage);
		new ImageFromUrl(iv).execute(new String[]{getActivity().getIntent().getExtras().getString("imageUrl")});
		TextView productDetail = (TextView)rootView.findViewById(R.id.productDetail);
		productDetail.setText(getActivity().getIntent().getExtras().getString("detail"));
		return rootView;
	}
}
