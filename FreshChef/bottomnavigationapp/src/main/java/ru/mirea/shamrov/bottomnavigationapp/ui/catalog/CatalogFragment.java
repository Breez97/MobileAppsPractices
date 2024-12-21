package ru.mirea.shamrov.bottomnavigationapp.ui.catalog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import ru.mirea.shamrov.bottomnavigationapp.databinding.FragmentCatalogBinding;
import ru.mirea.shamrov.bottomnavigationapp.ui.catalog.adapter.CatalogAdapter;
import ru.mirea.shamrov.bottomnavigationapp.ui.catalog.decorator.SpacingItemDecorator;

public class CatalogFragment extends Fragment implements OnDishClickListener {

	private FragmentCatalogBinding binding;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		CatalogViewModel catalogViewModel =
				new ViewModelProvider(this, new CatalogViewModelFactory(requireContext())).get(CatalogViewModel.class);
		binding = FragmentCatalogBinding.inflate(inflater, container, false);
		View root = binding.getRoot();
		RecyclerView recyclerView = binding.recyclerView;
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
		CatalogAdapter itemAdapter = new CatalogAdapter();
		itemAdapter.setOnDishClickListener(this);
		recyclerView.setAdapter(itemAdapter);
		recyclerView.addItemDecoration(new SpacingItemDecorator(20));
		catalogViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
			itemAdapter.setItems(items, getContext());
		});
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	@Override
	public void onDishClick(Integer dishId) {
		BottomSheetFragment bottomSheetFragment = BottomSheetFragment.createInstance(dishId);
		bottomSheetFragment.show(getChildFragmentManager(), "BottomSheetDialog");
	}

}