package br.com.Radio89fm;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import br.com.Radio89fm.R;

public class Fast89FM extends RadioActivity implements MediaPlayer.OnPreparedListener {
	private static boolean PLAY = false;
	final Activity master = this;
	private Uri uri;
	final MediaPlayer.OnPreparedListener listener = this;
	private Dialog loading;

	public void onPrepared(MediaPlayer mp) {
		loading.dismiss();
		streamPlayer.start();
		PLAY = true;
	}
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		loading = new AlertDialog.Builder(this).setMessage("Conectando à radio").setTitle(R.string.player).show();
		new FrameHandler(this, 10000).start();
		uri = Uri.parse("http://184-107-190-34.webnow.net.br:80/89fm128k.mp3");
		try {
			streamPlayer.reset();
			streamPlayer.setDataSource(master, uri);
			streamPlayer.setOnPreparedListener(listener);
			streamPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			streamPlayer.prepareAsync();
		} catch (Exception e) {
			e.printStackTrace();
		}

		final ImageButton buttonClose = (ImageButton) findViewById(R.id.button_close);
		buttonClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				streamPlayer.release();
				finish();
			}
		});
		final ImageButton buttonPlay = (ImageButton) findViewById(R.id.button_play);

		buttonPlay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!PLAY) {
					streamPlayer.start();
					BitmapDrawable bd = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.button_pause));
					buttonPlay.setBackgroundDrawable(bd);
				} else {
					streamPlayer.pause();
					BitmapDrawable bd = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.button_play));
					buttonPlay.setBackgroundDrawable(bd);
				}
				PLAY = !PLAY;
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		streamPlayer.release();
	}
}