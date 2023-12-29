package com.mrboomdev.tvlauncher.managers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressLint("StaticFieldLeak")
public class AppManager {
	private static final Map<Class<? extends Activity>, Activity> activities = new HashMap<>();

	public static void setActivity(Class<? extends Activity> clazz, Activity activity) {
		if(activity == null) {
			activities.remove(clazz);
			return;
		}

		activities.put(clazz, activity);
	}

	@Nullable
	@SuppressWarnings("unchecked")
	public static <T extends Activity> T getActivity(Class<T> clazz) {
		var activity = activities.get(clazz);
		if(activity == null) return null;

		return (T) activity;
	}

	@NonNull
	public static LayoutInflater getLayoutInflater() {
		var activity = AppManager.getAnyActivity();
		return Objects.requireNonNull(activity).getLayoutInflater();
	}

	@Nullable
	public static Activity getAnyActivity() {
		for(var activity : activities.values()) {
			return activity;
		}

		return null;
	}

	public static Context getAnyContext() {
		var activity = getAnyActivity();
		if(activity != null) return activity;

		return getAppContext();
	}

	@SuppressLint("PrivateApi")
	public static Context getAppContext() {
		try {
			var clazz = Class.forName("android.app.AppGlobals");
			var method = clazz.getMethod("getInitialApplication");
			return (Context) method.invoke(null);
		} catch(Exception e) {
			throw new RuntimeException("Failed to gain a context via reflection!", e);
		}
	}

	public static void setActivity(Activity activity) {
		setActivity(activity.getClass(), activity);
	}

	public static void removeActivity(Class<? extends Activity> clazz) {
		setActivity(clazz, null);
	}
}