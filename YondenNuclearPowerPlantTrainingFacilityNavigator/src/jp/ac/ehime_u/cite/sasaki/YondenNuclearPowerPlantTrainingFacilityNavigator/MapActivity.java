package jp.ac.ehime_u.cite.sasaki.YondenNuclearPowerPlantTrainingFacilityNavigator;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.app.AlertDialog;

public class MapActivity extends Activity {
	protected static final float SWIPE_MAX_OFF_PATH = 200;
	protected static final float SWIPE_MIN_DISTANCE = 100;
	protected static final float SWIPE_THRESHOLD_VELOCITY = 10;
	private GestureDetector mGestureDetector;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		mGestureDetector = new GestureDetector(this, mOnGestureListener);

		((ImageView) findViewById(R.id.imageViewBuilding))
				.setOnTouchListener(new OnTouchListener() {

					public boolean onTouch(View v, MotionEvent event) {
						AlertDialog.Builder alert_dialog_builder = new AlertDialog.Builder(
								MapActivity.this);
						alert_dialog_builder.setTitle("�_�C�A���O�{�b�N�X�̃^�C�g��");
						alert_dialog_builder.setPositiveButton("������",
								new OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								});
						alert_dialog_builder.setNegativeButton("�̂�",
								new OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								});
						alert_dialog_builder.setNeutralButton("�����",
								new OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								});
						alert_dialog_builder.setCancelable(true);
						alert_dialog_builder.show();
						return false;
					}
				});
		/*
		((ImageView) findViewById(R.id.imageViewFloor))
				.setOnTouchListener(new OnTouchListener() {

					public boolean onTouch(View v, MotionEvent event) {
						List<FloorListItem> dataList = new ArrayList<FloorListItem>();
						dataList.add(new FloorListItem(MapActivity.this));
						dataList.add(new FloorListItem(MapActivity.this));
						dataList.add(new FloorListItem(MapActivity.this));

						FloorListAdapter floor_list_adapter = new FloorListAdapter(
								MapActivity.this, R.layout.floor_list_item,
								dataList);
						ListView list_view = (ListView) MapActivity.this
								.findViewById(R.id.listViewFloor);
						list_view.setAdapter(floor_list_adapter);
						return false;
					}
				});
				*/
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// return super.onTouchEvent(event);
		Log.d("onTouchEvent", "");
		return mGestureDetector.onTouchEvent(event);
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
					// �J�n�ʒu����I���ʒu�̈ړ��������w��l���傫��
					// X���̈ړ����x���w��l���傫��
					Intent intent = new Intent(getApplicationContext(),
							MediaActivity.class);
					startActivity(intent);
				} else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					// �I���ʒu����J�n�ʒu�̈ړ��������w��l���傫��
					// X���̈ړ����x���w��l���傫��
					Intent intent = new Intent(getApplicationContext(),
							MediaActivity.class);
					startActivity(intent);
				}

			} catch (Exception e) {
				// nothing
			}
			return false;
		}
	};
}