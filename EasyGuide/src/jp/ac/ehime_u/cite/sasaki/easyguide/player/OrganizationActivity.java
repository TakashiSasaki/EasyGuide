package jp.ac.ehime_u.cite.sasaki.easyguide.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Takashi SASAKI {@link "http://twitter.com/TakashiSasaki"}
 * 
 */
public class OrganizationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String selected_organization = intent
				.getStringExtra("jp.ac.ehime_u.cite.sasaki.easyguide.organization");
		Log.v("OrganizationActivity", selected_organization + " was selected");
	}
}