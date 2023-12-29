package com.mrboomdev.tvlauncher.ui.layout.category;

import android.graphics.drawable.Drawable;

import java.util.List;

public class Category {
	private List<Item> items;
	private final String title;
	private final Type type;

	public Category(String title, Type type, List<Item> items) {
		this.title = title;
		this.type = type;
		setItems(items);
	}

	public Category(String title, Type type) {
		this(title, type, List.of());
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public Type getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public static class Item {
		private final Drawable image;
		private final String title;
		private final Runnable runCallback;

		public Item(String title, Drawable image, Runnable runCallback) {
			this.title = title;
			this.image = image;
			this.runCallback = runCallback;
		}

		public Item(String title) {
			this(title, null, null);
		}

		public void run() {
			if(runCallback != null) {
				runCallback.run();
			}
		}

		public String getTitle() {
			return title;
		}

		public Drawable getImage() {
			return image;
		}
	}

	public enum Type {
		APPS,
		MOVIES,
		ACTORS
	}
}