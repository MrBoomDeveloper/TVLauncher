package com.mrboomdev.tvlauncher.ui.layout.category;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesLayout extends RecyclerView {

	public CategoriesLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		setAdapter(new CategoriesAdapter());
	}

	public CategoriesLayout(@NonNull Context context) {
		this(context, null);
	}

	private class CategoriesAdapter extends RecyclerView.Adapter<CategoryLayout.CategoryViewHolder> {

		@NonNull
		@Override
		public CategoryLayout.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			var recycler = new RecyclerView(getContext());
			return new CategoryLayout.CategoryViewHolder(recycler);
		}

		@Override
		public void onBindViewHolder(@NonNull CategoryLayout.CategoryViewHolder holder, int position) {

		}

		@Override
		public int getItemCount() {
			return 0;
		}
	}
}