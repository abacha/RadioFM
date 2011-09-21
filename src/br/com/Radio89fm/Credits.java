package br.com.Radio89fm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


public class Credits extends RadioActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credits);
		findViewById(R.id.jera).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jera.com.br"));
				startActivity(intent);
			}
		});
		findViewById(R.id.quatromobi).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://quatro.mobi"));
				startActivity(intent);
			}
		});
	}
}
