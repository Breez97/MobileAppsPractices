package ru.mirea.shamrov.data.repository;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.shamrov.domain.models.UserAuthDTO;
import ru.mirea.shamrov.domain.repository.AuthCallback;
import ru.mirea.shamrov.domain.repository.AuthRepository;

public class AuthRepositoryImpl implements AuthRepository {

	private final FirebaseAuth firebaseAuth;

	public AuthRepositoryImpl(FirebaseAuth firebaseAuth) {
		this.firebaseAuth = firebaseAuth;
	}

	@Override
	public void login(String email, String password, AuthCallback callback) {
		firebaseAuth.signInWithEmailAndPassword(email, password)
				.addOnSuccessListener(authResult -> {
					UserAuthDTO user = new UserAuthDTO(authResult.getUser().getUid(), email);
					callback.onSuccess(user);
				})
				.addOnFailureListener(e -> callback.onError(e.getMessage()));
	}

	@Override
	public void register(String email, String password, AuthCallback callback) {
		firebaseAuth.createUserWithEmailAndPassword(email, password)
				.addOnSuccessListener(authResult -> {
					UserAuthDTO user = new UserAuthDTO(authResult.getUser().getUid(), email);
					callback.onSuccess(user);
				})
				.addOnFailureListener(e -> callback.onError(e.getMessage()));
	}

}
