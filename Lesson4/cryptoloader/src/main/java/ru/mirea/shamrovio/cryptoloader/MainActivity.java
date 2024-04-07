package ru.mirea.shamrovio.cryptoloader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {
	public final String TAG = this.getClass().getSimpleName();
	private final int LoaderID = 1234;

	private EditText _editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		_editText = findViewById(R.id.editTextText);
	}

	public void onClickButton(View view) {
//		Bundle _bundle = new Bundle();
//		_bundle.putString(MyLoader.ARG_WORD, "mirea");
//		LoaderManager.getInstance(this).initLoader(LoaderID, _bundle, this);

		Bundle _bundle = new Bundle();
		SecretKey _key = generateKey();
		_bundle.putByteArray(MyLoader.ARG_WORD, encryptMsg(_editText.getText().toString(), _key));
		_bundle.putByteArray("key", _key.getEncoded());
		LoaderManager.getInstance(this).initLoader(LoaderID, _bundle, this);
	}

	@Override
	public void onLoaderReset(@NonNull Loader _loader) {
		Log.d(TAG, "onLoaderReset");
	}

	@NonNull
	@Override
	public Loader onCreateLoader(int _id, @Nullable Bundle _bundle) {
		if(_id == LoaderID) {
			Toast.makeText(this, "onCreateLoader: " + _id, Toast.LENGTH_SHORT).show();
			return new MyLoader(this, _bundle);
		}
		throw new InvalidParameterException("Invalid loader id");
	}

	@Override
	public void onLoadFinished(@NonNull Loader _loader, Object _data) {
		if(_loader.getId() == LoaderID) {
			Log.d(TAG, String.format("Decoded: %s", _data));
			Toast.makeText(this,	"Result:	" + _data,	Toast.LENGTH_SHORT).show();
		}
	}

	public static SecretKey generateKey() {
		try {
			SecureRandom _sr = SecureRandom.getInstance("SHA1PRNG");
			_sr.setSeed("any data used as random seed".getBytes());
			KeyGenerator _kg = KeyGenerator.getInstance("AES");
			_kg.init(256, _sr);
			return new SecretKeySpec((_kg.generateKey()).getEncoded(), "AES");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] encryptMsg(String _message, SecretKey _secret) {
		Cipher _cipher = null;
		try {
			_cipher = Cipher.getInstance("AES");
			_cipher.init(Cipher.ENCRYPT_MODE, _secret);
			return _cipher.doFinal(_message.getBytes());
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
				BadPaddingException | IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		}
	}
}