package com.mrboomdev.tvlauncher.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mrboomdev.tvlauncher.R;

public class ShadowView extends FrameLayout {
	private boolean mForceInvalidateShadow = false;
	private int h = 1, w = 1, color = Color.WHITE;
	private float cornerRadius, width;

	public ShadowView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context, attrs);
	}

	public ShadowView(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ShadowView(@NonNull Context context) {
		this(context, null);
	}

	private void initAttributes(@NonNull Context context, AttributeSet attrs) {
		var attr = context.obtainStyledAttributes(attrs, R.styleable.ShadowView, 0, 0);

		try {
			cornerRadius = attr.getDimension(R.styleable.ShadowView_shadowCornerRadius, 0);
			width = attr.getDimension(R.styleable.ShadowView_shadowWidth, 0);
		} finally {
			attr.recycle();
		}
	}

	private void initView(Context context, AttributeSet attrs) {
		if(attrs != null) {
			initAttributes(context, attrs);
		}

		var padding = (int)width;
		setPadding(padding, padding, padding, padding);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldW, int oldH) {
		super.onSizeChanged(w, h, oldW, oldH);

		if(w > 0 && h > 0) {
			mForceInvalidateShadow = false;
			updateBackground();
		}
	}

	@SuppressWarnings("SuspiciousNameCombination")
	@NonNull
	private Bitmap createShadowBitmap(int shadowWidth, int shadowHeight, float cornerRadius, int shadowColor) {
		var output = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ARGB_4444);
		var shadowRect = new RectF(width, width, shadowWidth - width, shadowHeight - width);

		var shadowPaint = new Paint();
		shadowPaint.setAntiAlias(true);
		shadowPaint.setColor(Color.TRANSPARENT);
		shadowPaint.setStyle(Paint.Style.FILL);
		shadowPaint.setShadowLayer(width, 0, 0, shadowColor);

		var canvas = new Canvas(output);
		canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint);
		return output;
	}

	private void updateBackground(int width, int height) {
		var bitmap = createShadowBitmap(width, height, cornerRadius, color);
		var drawable = new BitmapDrawable(getResources(), bitmap);
		setBackground(drawable);
	}

	private void updateBackground() {
		updateBackground(w, h);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		if(mForceInvalidateShadow) {
			mForceInvalidateShadow = false;
		}

		w = right - left;
		h = bottom - top;

		updateBackground();
	}

	@Override
	protected int getSuggestedMinimumHeight() {
		return 0;
	}

	@Override
	protected int getSuggestedMinimumWidth() {
		return 0;
	}

	public void setWidth(float width) {
		this.width = width;
		updateBackground();
	}

	public void setCornerRadius(float cornerRadius) {
		this.cornerRadius = cornerRadius;
		updateBackground();
	}

	public void setColor(@ColorInt int color) {
		this.color = color;
		updateBackground();
	}
}