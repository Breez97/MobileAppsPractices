package ru.mirea.shamrov.data.firebase.authstorage;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.shamrov.data.firebase.utils.AuthCallbackFirebase;
import ru.mirea.shamrov.data.firebase.AuthStorage;

public class FirebaseAuthStorage implements AuthStorage {

	private final FirebaseAuth firebaseAuth;

	public FirebaseAuthStorage() {
		this.firebaseAuth = FirebaseAuth.getInstance();
	}

	@Override
	public void login(String email, String password, AuthCallbackFirebase callback) {
		firebaseAuth.signInWithEmailAndPassword(email, password)
				.addOnSuccessListener(authResult -> callback.onSuccess())
				.addOnFailureListener(e -> callback.onError(e.getMessage()));
	}

	@Override
	public void register(String email, String password, AuthCallbackFirebase callback) {
		firebaseAuth.createUserWithEmailAndPassword(email, password)
				.addOnSuccessListener(authResult -> callback.onSuccess())
				.addOnFailureListener(e -> callback.onError(e.getMessage()));
	}

	@Override
	public boolean isUserAuthorized() {
		FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
		return firebaseUser != null;
	}

	@Override
	public void logout() {
		firebaseAuth.signOut();
	}

}
