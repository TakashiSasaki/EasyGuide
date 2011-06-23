package jp.ac.ehime_u.cite.sasaki.YondenNuclearPowerPlantTrainingFacilityNavigator;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FloorArrayAdapter extends ArrayAdapter<FloorContent> {
	private List<FloorContent> floorListItems;
	private LayoutInflater layoutInflater;

	// �R���X�g���N�^
	// ���ɐ[���Ӗ��͖����C������E�E
	public FloorArrayAdapter(Context context, int resourceId,
			List<FloorContent> items) {
		super(context, resourceId, items);
		this.floorListItems = items;
		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// 1�s���̏���
	// �Ƃɂ�����������ɂ��Ă�����getView�Ƃ������\�b�h��1�s���삷�邲�ƂɌĂ΂��̂�
	// �ǉ������Ƃ��������ɉ�������
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// ����Ώۂ�View������
		// ���S�ɐV�K�ɍ��ꍇ��null���킽���Ă���
		// ����ɂ��Ă�View�������ɂƂ��Ă���̂�getView�Ƃ́E�E�E�Ȃ񂾂��ςȘb��
		//View view = convertView;
		if (view == null) {
			// 1�s��layout����View�̉�𐶐�
			view = layoutInflater.inflate(R.layout.floor_list_item, null);
		}
		// items����f�[�^
		// v�����ʂɂ������Ă���View�����o���Ēl���}�b�s���O����
		FloorContent floor_list_item = (FloorContent) floorListItems
				.get(position);
		final FloorContent f_floor_list_item = floor_list_item;

		TextView text_view_floor_title = (TextView) view
				.findViewById(R.id.textViewFloorTitle);
		text_view_floor_title.setText(floor_list_item.getTitle());

		TextView text_view_floor_description = (TextView) view
				.findViewById(R.id.textViewFloorDescription);
		text_view_floor_description.setText(floor_list_item.getDescription());

		ImageView image_view_floor_icon = (ImageView) view
				.findViewById(R.id.imageViewFloorIcon);
		image_view_floor_icon.setImageBitmap(floor_list_item.getImage());

		Button button_go_to_floor = (Button) view
				.findViewById(R.id.buttonGoToFloor);

		button_go_to_floor.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// final�錾�������[�J���ϐ���listenr�������̒l�Ńp�����[�^���Œ艻����
				// �N���[�W�����h�L����
				// doHoge(f_list_item);
			}
		});
		return view;
	}
}