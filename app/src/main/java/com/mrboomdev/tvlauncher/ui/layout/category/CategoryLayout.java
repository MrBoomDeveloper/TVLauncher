package com.mrboomdev.tvlauncher.ui.layout.category;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrboomdev.tvlauncher.R;
import com.mrboomdev.tvlauncher.managers.AppManager;
import com.mrboomdev.tvlauncher.managers.DrawableManager;
import com.mrboomdev.tvlauncher.ui.view.ShadowView;

@SuppressLint("InflateParams")
public class CategoryLayout {
	private static final int WHITE_GLOW_COLOR = Color.parseColor("#55ffffff");
	private final TextView title;
	private final CategoryViewHolder categoryViewHolder;
	private Category category;

	public CategoryLayout() {
		var view = AppManager.getLayoutInflater().inflate(R.layout.category_layout, null);

		if(view instanceof LinearLayout linear) {
			RecyclerView recycler = linear.findViewById(R.id.category_recycler);
			recycler.setAdapter(new Adapter());
		} else {
			throw new IllegalStateException("A root view of a layout is not of type LinearLayout!");
		}

		categoryViewHolder = new CategoryViewHolder(view);
		title = view.findViewById(R.id.category_title);
	}

	public CategoryViewHolder getViewHolder() {
		return categoryViewHolder;
	}

	public class CategoryViewHolder extends RecyclerView.ViewHolder {

		public CategoryViewHolder(@NonNull View view) {
			super(view);
		}

		public void setCategory(@NonNull Category _category) {
			category = _category;
			title.setText(category.getTitle());
		}
	}

	private class Adapter extends RecyclerView.Adapter<ViewHolder<LinearLayout>> {

		@NonNull
		@Override
		public ViewHolder<LinearLayout> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view = AppManager.getLayoutInflater().inflate(R.layout.category_item_movie, null);

			if(view instanceof LinearLayout linear) {
				return new ViewHolder<>(linear);
			}

			throw new IllegalStateException("A root view of a layout is not of type LinearLayout!");
		}

		@Override
		public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
			holder.setItem(category.getItems().get(position));
		}

		@Override
		public int getItemCount() {
			return category.getItems().size();
		}
	}

	private static class ViewHolder<T extends ViewGroup> extends RecyclerView.ViewHolder {
		private final TextView title;
		private final ShadowView shadow;
		private final ImageView image;
		private final ViewGroup view, shadowHolder;

		public ViewHolder(T view) {
			super(view);

			this.view = view;
			this.title = view.findViewById(R.id.category_item_title);
			this.shadow = view.findViewById(R.id.category_item_shadow);
			this.shadowHolder = view.findViewById(R.id.category_item_shadow_holder);
			this.image = view.findViewById(R.id.category_item_image);

			view.setOnFocusChangeListener((_view, isFocused) -> {
				TransitionManager.beginDelayedTransition(shadowHolder);
				var scale = isFocused ? 1 : .5f;
				shadow.setScaleX(scale);
				shadow.setScaleY(scale);
			});
		}

		public void setItem(@NonNull Category.Item item) {
			title.setText(item.getTitle());
			view.setOnClickListener(_view -> item.run());

			var drawable = item.getImage();

			if(drawable != null) {
				var palette = DrawableManager.getDrawablePalette(drawable);
				var glowColor = palette.getDominantColor(WHITE_GLOW_COLOR);

				image.setImageDrawable(drawable);
				image.setBackgroundColor(glowColor);
				shadow.setColor(glowColor);
			}
		}
	}
}