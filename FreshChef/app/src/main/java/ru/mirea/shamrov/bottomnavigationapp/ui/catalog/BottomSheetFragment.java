package ru.mirea.shamrov.bottomnavigationapp.ui.catalog;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import ru.mirea.shamrov.bottomnavigationapp.R;
import ru.mirea.shamrov.bottomnavigationapp.databinding.FragmentBottomSheetBinding;
import ru.mirea.shamrov.domain.models.DishRetrofitDTO;

public class BottomSheetFragment extends BottomSheetDialogFragment {
	private FragmentBottomSheetBinding binding;
	private static final String DISH_ID = "dish_id";
	private Integer dishId;
	private ImageView dishDetailImage;
	private TextView dishDetailTitle;
	private TextView dishDetailCategory;
	private TextView dishDetailLocation;
	private TextView dishDetailInstructions;
	private BottomSheetViewModel viewModel;

	public static void show(FragmentManager fragmentManager, Integer dishId) {
		BottomSheetFragment fragment = new BottomSheetFragment();
		Bundle args = new Bundle();
		args.putInt(DISH_ID, dishId);
		fragment.setArguments(args);
		fragment.show(fragmentManager, fragment.getTag());
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			dishId = getArguments().getInt(DISH_ID);
		}
		viewModel = new ViewModelProvider(this,
				new BottomSheetViewModelFactory(requireContext()))
				.get(BottomSheetViewModel.class);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = FragmentBottomSheetBinding.inflate(inflater, container, false);
		View view = binding.getRoot();
		binding.contentLayout.setVisibility(View.GONE);
		binding.progressBar.setVisibility(View.VISIBLE);
		initViews();
		initObservers();
		loadData();
		return view;
	}

	private void initViews() {
		dishDetailImage = binding.dishDetailImage;
		dishDetailTitle = binding.dishDetailTitle;
		dishDetailCategory = binding.dishDetailCategory;
		dishDetailLocation = binding.dishDetailLocation;
		dishDetailInstructions = binding.dishDetailInstructions;
	}

	private void initObservers() {
		viewModel.getCurrentDish().observe(getViewLifecycleOwner(), this::updateUI);
		viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
			if (!isLoading) {
				binding.progressBar.setVisibility(View.GONE);
				binding.contentLayout.setVisibility(View.VISIBLE);
			}
		});
	}

	private void loadData() {
		if (dishId != null) {
			viewModel.loadDishInfo(dishId);
		}
	}

	private void updateUI(DishRetrofitDTO dish) {
		if (dish != null) {
			dishDetailTitle.setText(dish.getTitle());
			dishDetailCategory.setText(dish.getCategory());
			dishDetailLocation.setText(dish.getArea());
			dishDetailInstructions.setText(dish.getInstructions());
			Picasso.get()
					.load(dish.getImageUrl())
					.placeholder(R.drawable.ic_launcher_foreground)
					.error(R.drawable.ic_launcher_background)
					.resize(450, 250)
					.centerCrop()
					.into(dishDetailImage);
		}
	}

}