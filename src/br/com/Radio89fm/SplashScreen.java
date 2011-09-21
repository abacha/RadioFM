package br.com.Radio89fm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.com.Radio89fm.R;
import br.com.jeramobstats.JeraAgent;

public class SplashScreen extends Activity implements Runnable {

	private final static int SPLASH_COUNT = 2;
	private static int SPLASHED = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		findViewById(R.id.splash)
				.setBackgroundResource(getResources().getIdentifier(String.format("splash%02d", SPLASHED), "drawable", "br.com.Radio89fm"));
		new Handler().postDelayed(this, 3000);
		SPLASHED++;
	}

	@Override
	protected void onStart() {
		super.onStart();
		JeraAgent.onStartSession(this, "4KJMQLGVW6VJ9UWT5AFX");
	}

	@Override
	public void run() {
		this.finish();
		if (SPLASHED <= SPLASH_COUNT) {
			startActivity(new Intent(this, SplashScreen.class));
		} else {
			SPLASHED = 1;
			startActivity(new Intent(this, Fast89FM.class));
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		JeraAgent.onEndSession(this);
	}

}
