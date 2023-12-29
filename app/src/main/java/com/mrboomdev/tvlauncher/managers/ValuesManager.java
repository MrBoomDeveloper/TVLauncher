package com.mrboomdev.tvlauncher.managers;

import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public class ValuesManager {

	@NonNull
	public static String getString(@StringRes int res) {
		return AppManager.getAnyContext().getString(res);
	}

	public static int dpToPx(float dp) {
		return typedValueToPx(TypedValue.COMPLEX_UNIT_DIP, dp);
	}

	public static int spToPx(float sp) {
		return typedValueToPx(TypedValue.COMPLEX_UNIT_SP, sp);
	}

	private static int typedValueToPx(int type, float value) {
		var activity = AppManager.getAnyActivity();

		if(activity == null) {
			throw new IllegalStateException("No activities were launched! Can't convert display metrics.");
		}

		return Math.round(TypedValue.applyDimension(type, value, activity.getResources().getDisplayMetrics()));
	}
}