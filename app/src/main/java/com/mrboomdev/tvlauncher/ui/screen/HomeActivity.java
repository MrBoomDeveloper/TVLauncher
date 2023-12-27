package com.mrboomdev.tvlauncher.ui.screen;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mrboomdev.tvlauncher.R;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tv_home);

		findViewById(R.id.account_icon).setOnClickListener(_view ->
				Toast.makeText(this, "Accounts aren't supported yet.", Toast.LENGTH_SHORT).show());

		findViewById(R.id.search_icon).setOnClickListener(_view ->
				Toast.makeText(this, "Search isn't supported yet.", Toast.LENGTH_SHORT).show());

		findViewById(R.id.settings_icon).setOnClickListener(_view ->
				Toast.makeText(this, "Settings aren't supported yet.", Toast.LENGTH_SHORT).show());

		//RecyclerView categoriesView = findViewById(R.id.categories);
		//categoriesView.setAdapter(new CategoriesAdapter());
	}

	private boolean isTv() {
		return getPackageManager().hasSystemFeature(PackageManager.FEATURE_LEANBACK);
	}

	private class CategoriesAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

		@NonNull
		@Override
		public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			var recycler = new RecyclerView(HomeActivity.this);
			return new CategoryViewHolder(recycler);
		}

		@Override
		public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

		}

		@Override
		public int getItemCount() {
			return 0;
		}
	}

	private class CategoryViewHolder extends RecyclerView.ViewHolder {
		private final RecyclerView row;

		public CategoryViewHolder(@NonNull RecyclerView row) {
			super(row);
			this.row = row;
		}

		public void setCategory(Category category) {

		}
	}

	private record Category(boolean isApps) {}
}