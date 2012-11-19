package com.gmail.takashi316.easyguide.ui;

import java.util.ArrayList;

import com.gmail.takashi316.easyguide.content.BitmapLoader;
import com.gmail.takashi316.easyguide.content.ContentUnit;
import com.gmail.takashi316.easyguide.content.TextLoader;
import com.gmail.takashi316.easyguide.lib.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class TocAdapter extends BaseAdapter {

	public class TocItem {
		Bitmap thumbnail;
		String name;
		String description;

		public TocItem(Bitmap thumbnail, String name, String description) {
			this.thumbnail = thumbnail;
			this.name = name;
			this.description = description;
		}
	}// TocItem

	// Organizations organizations = Organizations.getInstance();

	ArrayList<TocItem> tocItems = new ArrayList<TocItem>();

	LayoutInflater layoutInflater;

	/**
	 * the constructor of TocAdapter
	 * 
	 * @param context
	 * @throws Exception
	 */
	public TocAdapter(Context context, ArrayList<ContentUnit> content_units)
			throws Exception {
		super();

		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		for (ContentUnit content_unit : content_units) {
			BitmapLoader bitmap_loader = new BitmapLoader(context);
			Bitmap thumbnail;
			try {
				bitmap_loader.loadBitmapFromFile(content_unit.getImageFile());
				bitmap_loader.resizeBitmap(128, 128);
				thumbnail = bitmap_loader.getBitmap();
			} catch (Exception e) {
				Resources resources = context.getResources();
				thumbnail = BitmapFactory.decodeResource(resources,
						R.drawable.unknown);
			}
			String description;
			try {
				TextLoader text_loader = new TextLoader();
				text_loader.loadTextFromFile(content_unit.getTextFile());
				description = text_loader.getText();
			} catch (Exception e) {
				description = "この項目の説明はありません";
			}
			String name = content_unit.getName();
			TocItem toc_item = new TocItem(thumbnail, name, description);
			this.tocItems.add(toc_item);
		}// for
	}// the constructor

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			// View can be recycled. If the view is not generated yet, it should
			// be inflated here.
			view = this.layoutInflater.inflate(R.layout.tocitem, null);
		}
		try {
			TocItem toc_item = this.tocItems.get(position);
			ImageView imageViewTocItem = (ImageView) view
					.findViewById(R.id.imageViewTocItem);
			imageViewTocItem.setImageBitmap(toc_item.thumbnail);
		} catch (IndexOutOfBoundsException e) {
			Log.e(this.getClass().getSimpleName(),
					"no item in tocArrayList at position" + position);
			return null;
		}// try
		return view;
	}// getView

	@Override
	public int getCount() {
		return this.tocItems.size();
	}

	@Override
	public Object getItem(int position) {
		return this.tocItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}// TocAdapter
