package jp.ac.ehime_u.cite.sasaki.YondenNuclearPowerPlantTrainingFacilityNavigator;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

//�N���b�J�u���}�b�v
public class RoomView extends ImageView {

	public RoomView(Context context) {
		super(context);
		this.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

}
