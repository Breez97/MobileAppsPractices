package ru.mirea.shamrov.resultapifragmentapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BlankFragment extends Fragment {

	private FragmentListener listener;

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		try {
			listener = (FragmentListener) context;
		} catch (ClassCastException e) {
			throw new ClassCastException(context + " must implement onSomeEventListener");
		}
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_blank, container, false);
		ImageView imageView = view.findViewById(R.id.imageView);
		imageView.setOnClickListener(view1 -> listener.sendResult("image pushed"));
		return view;
	}
}