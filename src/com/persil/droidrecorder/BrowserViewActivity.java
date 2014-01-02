package com.persil.droidrecorder;

import java.io.File;
import java.io.IOException;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BrowserViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bowser_view);
		fillSoundList();
	}

	private void fillSoundList() {
		PackageManager m = getPackageManager();
		String s = getPackageName();
		PackageInfo p = null;
		try {
			p = m.getPackageInfo(s, 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s = p.applicationInfo.dataDir;
		File dir = new File(s+"recorded_sounds");
		if (!dir.exists()) {
			try {
				dir.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ListView listView = (ListView)findViewById(R.id.soundList);
		String[] files = dir.list();
		if (files == null) {
			return ;
		}
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bowser_view, menu);
		return true;
	}
	
	@Override
    public void onBackPressed() {
    	super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
	
    @SuppressLint("NewApi") @Override
    public boolean	onNavigateUp() {
    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
	    	boolean ret = super.onNavigateUp();
	        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			return ret;
    	}
       return false;
    }

}
