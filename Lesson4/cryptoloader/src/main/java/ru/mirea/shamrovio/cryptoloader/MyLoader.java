package ru.mirea.shamrovio.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {
//	private String _firstName;
	public static final String ARG_WORD = "word";

	private String _result;

	public MyLoader(@NonNull Context _context, Bundle _args) {
		super(_context);
		if(_args != null) {
//			_firstName = _args.getString(ARG_WORD);

			byte[] _cryptText = _args.getByteArray(ARG_WORD);
			byte[] _key = _args.getByteArray("key");
			SecretKey _originalKey = new SecretKeySpec(_key, 0, _key.length, "AES");

			_result = decryptMsg(_cryptText, _originalKey);
		}
	}

	@Override
	protected void onStartLoading() {
		super.onStartLoading();
		forceLoad();
	}

	@Override
	public String loadInBackground() {
		SystemClock.sleep(5000);
		return _result;
	}

	public static String decryptMsg(byte[] _cipherText, SecretKey _secret) {
		try {
			Cipher _cipher = Cipher.getInstance("AES");
			_cipher.init(Cipher.DECRYPT_MODE, _secret);
			return new String(_cipher.doFinal(_cipherText));
		}
		catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
				BadPaddingException | InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}
}
