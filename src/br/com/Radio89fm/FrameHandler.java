package br.com.Radio89fm;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class FrameHandler extends Thread {

	private static final int MAX_BANNER = 1;

	protected FrameHandler(Activity activity, long stride) {
		super(new FrameRunnable(activity, stride), "FrameHandler");
		super.setPriority(8);
	}

	static protected class FrameRunnable implements Runnable {
		public FrameRunnable(Activity activity, long stride) {
			this.frameStride = stride;
			this.activity = activity;
		}

		public void run() {
			for (;;) {
				long beginTime = System.currentTimeMillis();
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						changeBanner();
					}
				});

				try {
					long elapsedTime = System.currentTimeMillis() - beginTime;
					long sleepTime = frameStride - elapsedTime;
					if (sleepTime > 1) {
						Thread.sleep(sleepTime);
					}
				} catch (InterruptedException e) {
					Log.e("FrameHandler", e.toString());
				}
			}
		}

		private final Activity activity;
		private final long frameStride;

		private void changeBanner() {
			final int bannerId = new Random().nextInt(MAX_BANNER) + 1;

			ImageView banner = (ImageView) activity.findViewById(R.id.banner_bottom);
			banner.setBackgroundResource(activity.getResources().getIdentifier(String.format("banner%02d", bannerId), "drawable", "br.com.Radio89fm"));
			banner.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					activity.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							String[] banners = activity.getResources().getStringArray(R.array.links);
							String url = banners[bannerId - 1];
							if (url == null || url.equals("")) {
								return;
							}
							Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

							activity.startActivity(intent);
						}
					});
				}
			});
		}
	}
}