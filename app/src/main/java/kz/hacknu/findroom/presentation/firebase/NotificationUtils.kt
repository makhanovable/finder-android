package kz.hacknu.findroom.presentation.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import kz.hacknu.findroom.R
import org.koin.core.KoinComponent


class NotificationUtils : KoinComponent {
    companion object {

        fun sendNotification(
            context: Context,
            title: String?,
            body: String?,
            imageUrl: String,
            action: String,
            type: String,
            value: String
        ) {


            val resultIntent: Intent
//            resultIntent = Intent(context, OldApplicationActivity::class.java)
//            resultIntent.putExtra("open", value)


            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notificationBuilder = NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(NotificationCompat.BigTextStyle())
//                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)

//            try {
//                if (imageUrl.isNotEmpty()) {
//                    val bmp: Bitmap = PicassoTrustAll.getInstance(context).load(imageUrl).get()
//                    notificationBuilder.setLargeIcon(bmp)
//                    notificationBuilder
//                        .setStyle(
//                            NotificationCompat.BigPictureStyle()
//                                .bigPicture(bmp)
//                                .bigLargeIcon(null)
//                        )
//                } else {
//                    notificationBuilder.setStyle(NotificationCompat.BigTextStyle().bigText(body))
//                }
//            } catch (e: Exception) {
//            }

            if (title != null) notificationBuilder.setContentTitle(title)
            if (body != null) notificationBuilder.setContentText(body)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "МПС"
                val importance = NotificationManager.IMPORTANCE_HIGH

                val channel = NotificationChannel("default", name, importance)
                channel.canShowBadge()
                channel.setShowBadge(true)
                nm.createNotificationChannel(channel)
            }

            val notificationId = (System.currentTimeMillis()).hashCode()
            nm.notify(notificationId, notificationBuilder.build())
        }
    }
}