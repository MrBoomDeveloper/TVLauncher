package com.mrboomdev.tvlauncher.ui.layout.category;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryLayout extends RecyclerView {

	public CategoryLayout(@NonNull Context context) {
		super(context);
	}

	public static class CategoryViewHolder extends RecyclerView.ViewHolder {
		private final RecyclerView row;

		public CategoryViewHolder(@NonNull RecyclerView row) {
			super(row);
			this.row = row;
		}

		public void setCategory(Category category) {

		}
	}
}