package ru.mirea.shamrovio.securesharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ru.mirea.shamrovio.securesharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;

		try {
			String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);

			SharedPreferences secureSharedPreferences = EncryptedSharedPreferences.create(
					"secret_shared_prefs",
					mainKeyAlias,
					getBaseContext(),
					EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
					EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
			);

			secureSharedPreferences.edit().putString("secure", "Маяковский В.В.").apply();

			String result = secureSharedPreferences.getString("secure", "unknown");
			binding.textView.setText(result);

		} catch (GeneralSecurityException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}