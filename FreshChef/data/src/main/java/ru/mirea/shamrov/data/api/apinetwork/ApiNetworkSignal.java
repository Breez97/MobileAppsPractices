package ru.mirea.shamrov.data.api.apinetwork;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ru.mirea.shamrov.data.api.ApiNetwork;
import ru.mirea.shamrov.data.api.model.DishApi;

public class ApiNetworkSignal implements ApiNetwork {

	private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/random.php";
	private ExecutorService executorService = Executors.newSingleThreadExecutor();

	@Override
	public DishApi getRandomDish() {
		Callable<DishApi> task = this::fetchDishFromApi;
		Future<DishApi> future = executorService.submit(task);
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	private DishApi fetchDishFromApi() {
		try {
			URL url = new URL(API_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				return parseJsonToDTO(response.toString());
			} else {
				throw new Exception("Failed to fetch data: " + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private DishApi parseJsonToDTO(String jsonResponse) {
		try {
			JSONObject jsonObject = new JSONObject(jsonResponse);
			JSONArray mealsArray = jsonObject.getJSONArray("meals");
			if (mealsArray.length() > 0) {
				JSONObject mealObject = mealsArray.getJSONObject(0);
				String title = mealObject.getString("strMeal");
				Double price = Math.random() * 100;
				String stringPrice = String.format(Locale.getDefault(), "%.2f", price);
				return new DishApi(title, Double.parseDouble(stringPrice));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}