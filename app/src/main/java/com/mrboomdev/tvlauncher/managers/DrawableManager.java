package com.mrboomdev.tvlauncher.managers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.palette.graphics.Palette;

import java.util.Objects;

public class DrawableManager {

	public static Drawable getDrawable(@DrawableRes int res) {
		var activity = Objects.requireNonNull(AppManager.getAnyActivity());
		return activity.getResources().getDrawable(res, null);
	}

	@NonNull
	public static Drawable getDrawable(@DrawableRes int res, @ColorInt int color) {
		var drawable = copyDrawable(getDrawable(res));
		DrawableCompat.setTint(drawable, color);
		return drawable;
	}

	@NonNull
	public static Drawable getDrawable(@DrawableRes int res, @Size(min = 3) String color) {
		return getDrawable(res, Color.parseColor(color));
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		if(drawable == null) return null;

		var bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
		var canvas = new Canvas(bitmap);

		drawable = copyDrawable(drawable);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);

		return bitmap;
	}

	public static Drawable copyDrawable(Drawable drawable) {
		if(drawable == null) return null;

		var state = drawable.mutate().getConstantState();

		if(state != null) {
			return state.newDrawable();
		}

		return DrawableCompat.wrap(drawable);
	}

	@NonNull
	public static Palette getDrawablePalette(Drawable drawable) {
		var bitmap = drawableToBitmap(drawable);
		return Palette.from(bitmap).generate();
	}
}