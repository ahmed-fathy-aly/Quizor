package web.gcm;

import ui.activities.LoginRegisterActivity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.team_egor.quizor.R;

public class GcmIntentService extends IntentService
{
	public static final int NOTIFICATION_ID = 4261942;
	private static final String TAG = "Game";
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;

	public GcmIntentService()
	{
		super("GcmIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		// The getMessageType() intent parameter must be the intent you received
		// in your BroadcastReceiver.
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty())
		{ // has effect of unparcelling Bundle
			/*
			 * Filter messages based on message type. Since it is likely that
			 * GCM will be extended in the future with new message types, just
			 * ignore any message types you're not interested in, or that you
			 * don't recognize.
			 */
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType))
			{
				sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType))
			{
				sendNotification("Deleted messages on server: " + extras.toString());
				// If it's a regular GCM message, do some work.
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType))
			{
				Log.i("Game", "noification coming " + extras.toString());

				// Post notification of received message.
				String title = extras.getString("title");
				String message = extras.getString("message");
				sendCustomNotification(message, title);
			}
		}
		// Release the wake lock provided by the WakefulBroadcastReceiver.
		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}

	// Put the message into a notification and post it.
	// This is just one simple example of what you might choose to do with
	// a GCM message.
	private void sendNotification(String msg)
	{
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				LoginRegisterActivity.class), 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(
				R.drawable.ic_notification).setContentTitle("GCM Notification").setStyle(
				new NotificationCompat.BigTextStyle().bigText(msg)).setContentText(msg);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}

	private void sendCustomNotification(String msg, String title)
	{
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				LoginRegisterActivity.class), 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(
				R.drawable.ic_notification).setContentTitle(title).setStyle(
				new NotificationCompat.BigTextStyle().bigText(msg)).setContentText(msg);
		Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		mBuilder.setSound(alarmSound);

		// post the notification and increment the notification id(hope no one
		// sees i'm incrementing a constant
		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}
}