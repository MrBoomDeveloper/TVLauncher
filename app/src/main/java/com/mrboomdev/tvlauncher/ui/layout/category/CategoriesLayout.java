package com.mrboomdev.tvlauncher.ui.layout.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoriesLayout extends RecyclerView {
	private final CategoriesAdapter adapter;
	private List<Category> categories;

	public CategoriesLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		var layoutManager = new LinearLayoutManager(context);
		adapter = new CategoriesAdapter();

		setLayoutManager(layoutManager);
		setAdapter(adapter);
	}

	@SuppressLint("NotifyDataSetChanged")
	public void setData(List<Category> categories) {
		this.categories = categories;
		adapter.notifyDataSetChanged();
	}

	public CategoriesLayout(@NonNull Context context) {
		this(context, null);
	}

	private class CategoriesAdapter extends RecyclerView.Adapter<CategoryLayout.CategoryViewHolder> {

		@NonNull
		@Override
		public CategoryLayout.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			var categoryLayout = new CategoryLayout();
			return categoryLayout.getViewHolder();
		}

		@Override
		public void onBindViewHolder(@NonNull CategoryLayout.CategoryViewHolder holder, int position) {
			holder.setCategory(categories.get(position));
		}

		@Override
		public int getItemCount() {
			return categories.size();
		}
	}
}