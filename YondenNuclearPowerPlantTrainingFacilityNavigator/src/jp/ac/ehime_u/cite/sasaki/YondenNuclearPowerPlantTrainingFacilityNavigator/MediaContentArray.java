package jp.ac.ehime_u.cite.sasaki.YondenNuclearPowerPlantTrainingFacilityNavigator;

import java.util.ArrayList;

public class MediaContentArray extends ArrayList<MediaContent> {
	//SD�J�[�h����R���e���c��ǂݍ���
	MediaContentArray(String facility_name, String media_root) {
		
	}

	//�f�o�b�O���Ɏg��
	MediaContentArray(int n_media_content) {
		super();
		for (int i = 0; i < n_media_content; ++i) {
			this.add(new MediaContent());
		}
	}
}
