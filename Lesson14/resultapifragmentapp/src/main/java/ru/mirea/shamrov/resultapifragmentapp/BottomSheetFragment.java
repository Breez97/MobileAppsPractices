package ru.mirea.shamrov.resultapifragmentapp;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;

	public BottomSheetFragment() {

	}

	public static BottomSheetFragment newInstance(String param1, String param2) {
		BottomSheetFragment fragment = new BottomSheetFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
		TextView textView = view.findViewById(R.id.textViewBottomSheet);
		getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, result) -> {
			String text = result.getString("key");
			Log.d(BottomSheetFragment.class.getSimpleName(), "Get text: " + text);
			textView.setText(text);
		});
		return view;
	}
}