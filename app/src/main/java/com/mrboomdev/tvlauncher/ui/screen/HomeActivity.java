package com.mrboomdev.tvlauncher.ui.screen;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mrboomdev.tvlauncher.R;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

		/*findViewById(R.id.account_icon).setOnClickListener(_view ->
				Toast.makeText(this, "Accounts aren't supported yet.", Toast.LENGTH_SHORT).show());

		findViewById(R.id.search_icon).setOnClickListener(_view ->
				Toast.makeText(this, "Search isn't supported yet.", Toast.LENGTH_SHORT).show());

		findViewById(R.id.settings_icon).setOnClickListener(_view ->
				Toast.makeText(this, "Settings aren't supported yet.", Toast.LENGTH_SHORT).show());*/

		//RecyclerView categoriesView = findViewById(R.id.categories);
		//categoriesView.setAdapter(new CategoriesAdapter());
	}

	private boolean isTv() {
		return getPackageManager().hasSystemFeature(PackageManager.FEATURE_LEANBACK);
	}
}