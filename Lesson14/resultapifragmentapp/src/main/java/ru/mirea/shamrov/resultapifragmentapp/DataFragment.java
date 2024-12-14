package ru.mirea.shamrov.resultapifragmentapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DataFragment extends Fragment {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;

	public DataFragment() {

	}

	public static DataFragment newInstance(String param1, String param2) {
		DataFragment fragment = new DataFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_data, container, false);
		Button button = view.findViewById(R.id.buttonOpenBottomSheet);
		button.setOnClickListener(click -> {
			String text = ((EditText) view.findViewById(R.id.editTextInfo)).getText().toString();
			Bundle bundle = new Bundle();
			bundle.putString("key", text);
			getChildFragmentManager().setFragmentResult("requestKey", bundle);
			BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
			bottomSheetFragment.show(getChildFragmentManager(), "ModalBottomSheet");
		});
		return view;
	}
}