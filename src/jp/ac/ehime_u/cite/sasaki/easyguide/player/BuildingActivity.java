package jp.ac.ehime_u.cite.sasaki.easyguide.player;

import jp.ac.ehime_u.cite.sasaki.easyguide.model.Building;
import jp.ac.ehime_u.cite.sasaki.easyguide.model.Facility;
import jp.ac.ehime_u.cite.sasaki.easyguide.model.Floor;
import jp.ac.ehime_u.cite.sasaki.easyguide.model.Organization;
import jp.ac.ehime_u.cite.sasaki.easyguide.model.Organizations;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * @author Takashi SASAKI {@link "http://twitter.com/TakashiSasaki"}
 * 
 */
public class BuildingActivity extends Activity {
	protected static final float SWIPE_MAX_OFF_PATH = 200;
	protected static final float SWIPE_MIN_DISTANCE = 100;
	protected static final float SWIPE_THRESHOLD_VELOCITY = 10;
	private GestureDetector mGestureDetector;
	private Building building;
	int organizationIndex;
	int facilityIndex;
	int buildingIndex;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.building);
		this.mGestureDetector = new GestureDetector(this,
				this.mOnGestureListener);

		Intent intent = this.getIntent();
		this.organizationIndex = intent.getIntExtra("organizationIndex", 0);
		this.facilityIndex = intent.getIntExtra("facilityIndex", 0);
		this.buildingIndex = intent.getIntExtra("buildingIndex", 0);

		Organizations organizations = Organizations.getInstance();
		Organization organization;
		try {
			organization = organizations.getOrganization(this.organizationIndex);
			Facility facility = organization.getFacility(this.facilityIndex);
			this.building = facility.getBuilding(this.buildingIndex);
		} catch (Exception e) {
			this.building = Building.getDummy();
		}

		ImageView image_view_building = (ImageView) findViewById(R.id.imageViewBuilding);
		image_view_building.setImageBitmap(this.building.getImage());

		// image_view_building.setOnTouchListener(new OnTouchListener() {
		//
		// public boolean onTouch(View v, MotionEvent event) {
		// AlertDialog.Builder alert_dialog_builder = new AlertDialog.Builder(
		// BuildingActivity.this);
		// alert_dialog_builder.setTitle("ダイアログボックスのタイトル");
		// alert_dialog_builder.setPositiveButton("YES デバッグ用",
		// new OnClickListener() {
		//
		// public void onClick(DialogInterface dialog,
		// int which) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// alert_dialog_builder.setNegativeButton("NO デバッグ用",
		// new OnClickListener() {
		//
		// public void onClick(DialogInterface dialog,
		// int which) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// alert_dialog_builder.setNeutralButton("CANCEL デバッグ用",
		// new OnClickListener() {
		//
		// public void onClick(DialogInterface dialog,
		// int which) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// alert_dialog_builder.setCancelable(true);
		// alert_dialog_builder.show();
		// return true;
		// }
		// });

		SetSpinnerFloors();
	}//

	void SetSpinnerFloors() {
		Spinner s = (Spinner) findViewById(R.id.spinnerFloors);
		ArrayAdapter<Floor> floor_array_adapter = new ArrayAdapter<Floor>(this,
				android.R.layout.simple_spinner_dropdown_item);
		// floor_array_adapter.add(Floor.getEmptyFloor());
		for (Floor f : this.building) {
			floor_array_adapter.add(f);
		}
		s.setAdapter(floor_array_adapter);
		OnItemSelectedListener l = new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Floor selected_floor = (Floor) arg0.getItemAtPosition(arg2);
				if (selected_floor.isEmpty()) {
					return;
				}
				InvokeFloorActivity(selected_floor.getIndex());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		};// OnItemSelectedListener
		s.setOnItemSelectedListener(l);

		s.setPrompt(this.getString(R.string.spinner_prompt_floors));
	}// SetSpinnerFloors

	void InvokeFloorActivity(int fi) {
		Intent intent = new Intent();
		intent.setClass(this, FloorActivity.class);
		intent.putExtra("organizationIndex", this.organizationIndex);
		intent.putExtra("facilityIndex", this.facilityIndex);
		intent.putExtra("buildingIndex", this.buildingIndex);
		intent.putExtra("floorIndex", fi);
		startActivity(intent);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// return super.onTouchEvent(event);
		Log.d("onTouchEvent", "");
		return this.mGestureDetector.onTouchEvent(event);
	}

	private final SimpleOnGestureListener mOnGestureListener = new SimpleOnGestureListener() {
		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2,
				float velocityX, float velocityY) {

			Log.d("onFling", "X1=" + event1.getX() + ",Y1=" + event1.getY()
					+ ",X2=" + event2.getX() + ",Y2=" + event2.getY());
			try {
				if (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					// 開始位置から終了位置の移動距離が指定値より大きい
					// X軸の移動速度が指定値より大きい
					InvokeMediaActivity();
				} else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					// 終了位置から開始位置の移動距離が指定値より大きい
					// X軸の移動速度が指定値より大きい
					InvokeMediaActivity();
				}

			} catch (Exception e) {
				// nothing
			}// try
			return false;
		}// onFling
	};// SimpleOnGestureListener

	private void InvokeMediaActivity() {
		Intent intent = new Intent(getApplicationContext(),
				EquipmentActivity.class);
		startActivity(intent);
	}
}