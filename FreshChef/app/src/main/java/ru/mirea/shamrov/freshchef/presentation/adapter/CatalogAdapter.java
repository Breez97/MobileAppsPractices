package ru.mirea.shamrov.freshchef.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.mirea.shamrov.domain.models.DishRetrofitDTO;
import ru.mirea.shamrov.freshchef.R;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.SimpleCatalogViewHolder> {

	private List<DishRetrofitDTO> itemList = new ArrayList<>();
	private Context context;

	public void setItems(List<DishRetrofitDTO> itemList, Context context) {
		this.itemList = itemList;
		this.context = context;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public SimpleCatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View recyclerViewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item_view, parent, false);
		return new SimpleCatalogViewHolder(recyclerViewItem);
	}

	@Override
	public void onBindViewHolder(@NonNull SimpleCatalogViewHolder holder, int position) {
		holder.bind(itemList.get(position));
	}

	@Override
	public int getItemCount() {
		return itemList.size();
	}

	public class SimpleCatalogViewHolder extends RecyclerView.ViewHolder {

		private final TextView dishTitle;
		private final TextView dishDescription;
		private final ImageView dishImage;
		private final TextView dishPrice;
		private final TextView dishGrams;

		public SimpleCatalogViewHolder(@NonNull View itemView) {
			super(itemView);
			this.dishTitle = itemView.findViewById(R.id.dishTitle);
			this.dishDescription = itemView.findViewById(R.id.dishDescription);
			this.dishImage = itemView.findViewById(R.id.dishImage);
			this.dishPrice = itemView.findViewById(R.id.dishPrice);
			this.dishGrams = itemView.findViewById(R.id.dishGram);
		}

		public void bind(DishRetrofitDTO item) {
			dishTitle.setText(item.getTitle());
			dishDescription.setText(item.getCategory());
			dishPrice.setText(String.format(Locale.getDefault(), "%.0f ₽", item.getPrice()));
			dishGrams.setText(String.format(Locale.getDefault(), "%d г", item.getAmount()));
			Picasso.get()
					.load(item.getImageUrl())
					.placeholder(R.drawable.ic_launcher_foreground)
					.error(R.drawable.ic_launcher_background)
					.resize(450, 250)
					.centerCrop()
					.into(dishImage);
		}

	}

}
