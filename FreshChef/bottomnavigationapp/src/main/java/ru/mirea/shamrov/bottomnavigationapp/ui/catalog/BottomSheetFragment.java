package ru.mirea.shamrov.bottomnavigationapp.ui.catalog;

import android.os.Bundle;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import ru.mirea.shamrov.bottomnavigationapp.R;
import ru.mirea.shamrov.bottomnavigationapp.databinding.FragmentBottomSheetBinding;

public class BottomSheetFragment extends BottomSheetDialogFragment {

	private FragmentBottomSheetBinding binding;
	private static final String DISH_ID = "dish_id";
	private Integer dishId;
	private ImageView dishDetailImage;
	private TextView dishDetailTitle;
	private TextView dishDetailCategory;
	private TextView dishDetailLocation;
	private TextView dishDetailIngredients;

	public static BottomSheetFragment createInstance(Integer dishId) {
		BottomSheetFragment fragment = new BottomSheetFragment();
		Bundle args = new Bundle();
		args.putInt(DISH_ID, dishId);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			dishId = getArguments().getInt(DISH_ID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = FragmentBottomSheetBinding.inflate(inflater, container, false);
		View view = binding.getRoot();
		dishDetailImage = binding.dishDetailImage;
		dishDetailTitle = binding.dishDetailTitle;
		dishDetailCategory = binding.dishDetailCategory;
		dishDetailLocation = binding.dishDetailLocation;
		dishDetailIngredients = binding.dishDetailIngredients;
		return view;
	}

}