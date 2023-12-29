package com.mrboomdev.tvlauncher.ui.screen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.mrboomdev.tvlauncher.BuildConfig;
import com.mrboomdev.tvlauncher.R;
import com.mrboomdev.tvlauncher.managers.AppManager;
import com.mrboomdev.tvlauncher.ui.layout.category.CategoriesLayout;
import com.mrboomdev.tvlauncher.ui.layout.category.Category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HomeActivity extends Activity {

	@SuppressLint("PrivateApi")
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		AppManager.setActivity(this);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home_activity);
		CategoriesLayout categoriesLayout = findViewById(R.id.categories_layout);

		var pm = getPackageManager();
		var apps = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES | PackageManager.GET_META_DATA);

		var installedApps = new ArrayList<Category.Item>();
		var appDetails = new HashSet<String>();

		for(var app : apps) {
			if(app.activities == null || app.packageName.equals(BuildConfig.APPLICATION_ID)) {
				continue;
			}

			Intent intentToResolve = new Intent(Intent.ACTION_MAIN);
			intentToResolve.addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER);
			intentToResolve.setPackage(app.packageName);

			List<ResolveInfo> resolved = pm.queryIntentActivities(intentToResolve, 0);

			for(var resolvedActivity : resolved) {
				appDetails.add(resolvedActivity.activityInfo.name);
			}

			for(var activity : app.activities) {
				if(!activity.exported || !activity.enabled || !appDetails.contains(activity.name)) continue;

				var banner = activity.loadBanner(pm);
				var label = activity.loadLabel(pm);

				if(banner == null) {
					continue;
				}

				installedApps.add(new Category.Item(label.toString(), banner, () -> {
					var intent = new Intent(intentToResolve);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setClassName(app.packageName, activity.name);
					startActivity(intent);
				}));
			}
		}

		categoriesLayout.setData(List.of(
				new Category("Your Apps", Category.Type.APPS, installedApps)
		));

		findViewById(R.id.account_icon).setOnClickListener(_view ->
				Toast.makeText(this, "Accounts aren't supported yet.", Toast.LENGTH_SHORT).show());

		findViewById(R.id.search_icon).setOnClickListener(_view ->
				Toast.makeText(this, "Search isn't supported yet.", Toast.LENGTH_SHORT).show());

		findViewById(R.id.settings_icon).setOnClickListener(_view ->
				Toast.makeText(this, "Settings aren't supported yet.", Toast.LENGTH_SHORT).show());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.removeActivity(getClass());
	}

	private boolean isTv() {
		return getPackageManager().hasSystemFeature(PackageManager.FEATURE_LEANBACK);
	}
}