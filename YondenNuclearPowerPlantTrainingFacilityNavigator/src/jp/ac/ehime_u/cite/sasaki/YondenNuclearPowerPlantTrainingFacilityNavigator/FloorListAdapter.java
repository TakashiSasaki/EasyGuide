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

public class FloorListAdapter extends ArrayAdapter<FloorListItem> {
	private List<FloorListItem> items;
	private LayoutInflater inflater;

	// �R���X�g���N�^
	// ���ɐ[���Ӗ��͖����C������E�E
	public FloorListAdapter(Context context, int resourceId,
			List<FloorListItem> items) {
		super(context, resourceId, items);
		this.items = items;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// 1�s���̏���
	// �Ƃɂ�����������ɂ��Ă�����getView�Ƃ������\�b�h��1�s���삷�邲�ƂɌĂ΂��̂�
	// �ǉ������Ƃ��������ɉ�������
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ����Ώۂ�View������
		// ���S�ɐV�K�ɍ��ꍇ��null���킽���Ă���
		// ����ɂ��Ă�View�������ɂƂ��Ă���̂�getView�Ƃ́E�E�E�Ȃ񂾂��ςȘb��
		View v = convertView;
		if (v == null) {
			// 1�s��layout����View�̉�𐶐�
			v = inflater.inflate(R.layout.floor_list_item, null);
		}
		// items����f�[�^
		// v�����ʂɂ������Ă���View�����o���Ēl���}�b�s���O����
		FloorListItem floor_list_item = (FloorListItem) items.get(position);
		final FloorListItem f_floor_list_item = floor_list_item;

		TextView text_view_floor_title = (TextView) v
				.findViewById(R.id.textViewFloorTitle);
		text_view_floor_title.setText(floor_list_item.getTitle());

		TextView text_view_floor_description = (TextView) v
				.findViewById(R.id.textViewFloorDescription);
		text_view_floor_description.setText(floor_list_item.getDescription());

		ImageView image_view_floor_icon = (ImageView) v
				.findViewById(R.id.imageViewFloorIcon);
		image_view_floor_icon.setImageBitmap(floor_list_item.getImage());

		Button button_go_to_floor = (Button) v
				.findViewById(R.id.buttonGoToFloor);

		button_go_to_floor.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// final�錾�������[�J���ϐ���listenr�������̒l�Ńp�����[�^���Œ艻����
				// �N���[�W�����h�L����
				// doHoge(f_list_item);
			}
		});
		return v;
	}
}