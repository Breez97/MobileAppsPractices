package ru.mirea.shamrovio.mireaproject.ui.lesson7;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import ru.mirea.shamrovio.mireaproject.databinding.FragmentApiBinding;

public class ApiFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();
	private FragmentApiBinding binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = FragmentApiBinding.inflate(inflater, container, false);

		binding.buttonGenerate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ConnectivityManager connectivityManager = (ConnectivityManager)
						getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = null;
				if(connectivityManager != null) {
					networkInfo = connectivityManager.getActiveNetworkInfo();
				}

				if(networkInfo != null && networkInfo.isConnected()) {
					new DownloadPageTask().execute("https://www.boredapi.com/api/activity");
				} else {
					Toast.makeText(getActivity(), "Нет интернета", Toast.LENGTH_SHORT).show();
				}
			}
		});

		return binding.getRoot();
	}

	private class DownloadPageTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... urls) {
			try {
				return downloadIpInfo(urls[0]);
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			try {
				JSONObject responseJson = new JSONObject(result);
				Log.d(TAG, "Response" + responseJson);
				String activity = responseJson.getString("activity");
				String type = responseJson.getString("type");
				String participants = responseJson.getString("participants");
				String price = responseJson.getString("price");
				String accessibility = responseJson.getString("accessibility");
				Log.d(TAG, activity);

				binding.textViewActivityTitle.setText(String.format(Locale.getDefault(),
						"Название: %s", activity));
				binding.textViewActivityType.setText(String.format(Locale.getDefault(),
						"Тип: %s", type));
				binding.textViewActivityParticipants.setText(String.format(Locale.getDefault(),
						"Количество участников: %s", participants));
				binding.textViewActivityPrice.setText(String.format(Locale.getDefault(),
						"Цена: %s", price));
				binding.textViewActivityAccesibility.setText(String.format(Locale.getDefault(),
						"Доступность: %s", accessibility));
			} catch(JSONException e) {
				e.printStackTrace();
			}
			super.onPostExecute(result);
		}

		private String downloadIpInfo(String address) throws IOException {
			InputStream inputStream = null;
			String data = "";
			try {
				URL url = new URL(address);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setReadTimeout(100000);
				connection.setConnectTimeout(100000);
				connection.setRequestMethod("GET");
				connection.setInstanceFollowRedirects(true);
				connection.setUseCaches(false);
				connection.setDoInput(true);
				int responseCode = connection.getResponseCode();

				if(responseCode == HttpURLConnection.HTTP_OK) {
					inputStream = connection.getInputStream();
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					int read = 0;
					while((read = inputStream.read()) != -1) {
						bos.write(read);
					}
					bos.close();
					data = bos.toString();
				} else {
					data = connection.getResponseMessage() + ". Error code: " + responseCode;
				}
				connection.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(inputStream != null) {
					inputStream.close();
				}
			}
			return data;
		}
	}
}