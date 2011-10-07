package jp.ac.ehime_u.cite.sasaki.easyguide.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

/**
 * @author Takashi SASAKI {@link "http://twitter.com/TakashiSasaki"}
 * 
 */
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

		ImageView image_view_building = (ImageView) findViewById(R.id.imageViewBuilding);
		image_view_building.setImageBitmap(OpeningActivity.getChosenBuilding()
				.getBuildingImage());
		image_view_building.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				AlertDialog.Builder alert_dialog_builder = new AlertDialog.Builder(
						MapActivity.this);
				alert_dialog_builder.setTitle("�_�C�A���O�{�b�N�X�̃^�C�g��");
				alert_dialog_builder.setPositiveButton("YES �f�o�b�O�p",
						new OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				alert_dialog_builder.setNegativeButton("NO �f�o�b�O�p",
						new OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				alert_dialog_builder.setNeutralButton("CANCEL �f�o�b�O�p",
						new OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				alert_dialog_builder.setCancelable(true);
				alert_dialog_builder.show();
				return true;
			}
		});

		ImageView image_view_floor = (ImageView) findViewById(R.id.imageViewFloor);
		// TODO: �N���b�J�u���}�b�v������
		image_view_floor.setImageBitmap(OpeningActivity.getChosenBuilding()
				.get(1).getFloorImage());
		image_view_floor.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				/*
				 * List<FloorContent> dataList = new ArrayList<FloorContent>();
				 * dataList.add(new FloorContent(MapActivity.this));
				 * dataList.add(new FloorContent(MapActivity.this));
				 * dataList.add(new FloorContent(MapActivity.this));
				 * 
				 * FloorArrayAdapter floor_list_adapter = new FloorArrayAdapter(
				 * MapActivity.this, R.layout.floor_list_item, dataList);
				 * ListView list_view = (ListView) MapActivity.this
				 * .findViewById(R.id.listViewFloor); assert (list_view !=
				 * null); list_view.setAdapter(floor_list_adapter);
				 * 
				 * // �R���e�L�X�g����C���t���[�^���擾 LayoutInflater layout_infrater =
				 * LayoutInflater .from(MapActivity.this); //
				 * ���C�A�E�g���C���t���Ƃ��ăr���[���擾 View view = layout_infrater.inflate(
				 * R.layout.floor_list, null);
				 * 
				 * new AlertDialog.Builder(MapActivity.this).setView(view)
				 * .show();
				 */
				return false;
			}
		});

		ImageView image_view_room = (ImageView) findViewById(R.id.imageViewRoom);
		image_view_room.setImageBitmap(OpeningActivity.getChosenBuilding()
				.get(1).get(1).getRoomImage());
		image_view_room.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				new Handler().post(new Runnable() {
					@Override
					public void run() {
						InvokeMediaActivity();
					}
				});
				return false;
			}// onTouch
		}// OnTouchListener
				);// setOnTouchListener
	}// onCreate

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
					InvokeMediaActivity();
				} else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					// �I���ʒu����J�n�ʒu�̈ړ��������w��l���傫��
					// X���̈ړ����x���w��l���傫��
					InvokeMediaActivity();
				}

			} catch (Exception e) {
				// nothing
			}// try
			return false;
		}// onFling
	};// SimpleOnGestureListener

	private void InvokeMediaActivity() {
		Intent intent = new Intent(getApplicationContext(), MediaActivity.class);
		startActivity(intent);
	}
}