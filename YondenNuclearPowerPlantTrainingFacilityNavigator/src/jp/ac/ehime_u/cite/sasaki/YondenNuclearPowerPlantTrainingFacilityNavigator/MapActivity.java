package jp.ac.ehime_u.cite.sasaki.YondenNuclearPowerPlantTrainingFacilityNavigator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

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
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//return super.onTouchEvent(event);
		Log.d("onTouchEvent", "");
		return mGestureDetector.onTouchEvent(event);
	}

	private final SimpleOnGestureListener mOnGestureListener = new SimpleOnGestureListener() {
		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2,
				float velocityX, float velocityY) {

			Log.d("onFling", "X1="+event1.getX()+",Y1="+event1.getY()+",X2="+event2.getX()+",Y2="+event2.getY());
			try {
				if (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					// 開始位置から終了位置の移動距離が指定値より大きい
					// X軸の移動速度が指定値より大きい
					Intent intent = new Intent(getApplicationContext(),
							MediaActivity.class);
					startActivity(intent);
				} else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					// 終了位置から開始位置の移動距離が指定値より大きい
					// X軸の移動速度が指定値より大きい
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