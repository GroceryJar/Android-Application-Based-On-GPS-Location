
package com.gwalior.connectivity;

import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONObject;
import android.os.AsyncTask;

public class JSONParser {

	private JSONObject jObj;
	
	// constructor
	public JSONParser() {

	}

	public JSONObject getJSONFromUrl(String url, List<NameValuePair> params) {
		jObj = null;
		GetData data = new GetData(url,params);
		AsyncTask<String, Void, String> response = data.execute();
		try {
			if(response.get().toString().equals("true"))
			{
				jObj = data.getjObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jObj;

	}
}
