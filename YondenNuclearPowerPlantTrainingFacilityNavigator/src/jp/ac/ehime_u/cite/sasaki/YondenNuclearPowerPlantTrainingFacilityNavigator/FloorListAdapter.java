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

	// コンストラクタ
	// 特に深い意味は無い気がする・・
	public FloorListAdapter(Context context, int resourceId,
			List<FloorListItem> items) {
		super(context, resourceId, items);
		this.items = items;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// 1行分の処理
	// とにかく何をするにしてもこのgetViewというメソッドが1行操作するごとに呼ばれるので
	// 追加処理とかもここに押し込む
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 操作対象のViewを見る
		// 完全に新規に作る場合はnullがわたってくる
		// それにしてもViewを引数にとっているのにgetViewとは・・・なんだか変な話だ
		View v = convertView;
		if (v == null) {
			// 1行分layoutからViewの塊を生成
			v = inflater.inflate(R.layout.floor_list_item, null);
		}
		// itemsからデータ
		// vから画面にくっついているViewを取り出して値をマッピングする
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
				// final宣言したローカル変数でlistenr生成時の値でパラメータを固定化する
				// クロージャモドキ実装
				// doHoge(f_list_item);
			}
		});
		return v;
	}
}