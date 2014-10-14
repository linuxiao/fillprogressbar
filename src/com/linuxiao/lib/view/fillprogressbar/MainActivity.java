package com.linuxiao.lib.view.fillprogressbar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final FillProgressBar iv = (FillProgressBar)findViewById(R.id.fillProgressBar1);
		new Thread(new Runnable() {
			float p = 0.0f;
			@Override
			public void run() {
				while (true) {
					p= p+0.01f;
					if (p<=1.0f) {
						iv.setProgress(p);
					}else{
						p = 0.0f;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		ImageView contentView = (ImageView)iv.getContentView();
		contentView.setImageResource(R.drawable.image);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
