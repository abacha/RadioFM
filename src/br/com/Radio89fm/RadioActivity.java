package br.com.Radio89fm;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import br.com.Radio89fm.R;

public class RadioActivity extends Activity {

	protected MediaPlayer streamPlayer = new MediaPlayer();

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.quit: {
			streamPlayer.release();
			finish();
			return true;
		}
		case R.id.credit: {
			finish();
			Intent i = new Intent(this, Credits.class);
			startActivity(i);
			return true;
		}
		case R.id.player: {
			finish();
			Intent i = new Intent(this, Fast89FM.class);
			startActivity(i);
			return true;
		}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}
}