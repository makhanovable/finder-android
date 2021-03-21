package kz.hacknu.findroom.presentation.firebase

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("efeve", remoteMessage.toString())

        val title: String? = remoteMessage.notification?.title.toString()
        val messageBody: String? = remoteMessage.notification?.body.toString()
        val imageUrl: String = remoteMessage.data["imageUrl"] ?: ""

        val action: String = remoteMessage.data["action"] ?: String()
        val type: String = remoteMessage.data["type"] ?: String()
        val value: String = remoteMessage.data["value"] ?: String()

        NotificationUtils.sendNotification(
            this,
            title,
            messageBody,
            imageUrl,
            action,
            type,
            value
        )
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        sendBroadcastMessage(token)
    }

    private fun sendBroadcastMessage(token: String) {
        Intent().also { intent ->
            intent.setAction("kz.kmf.employee.ACTION")
            intent.putExtra("token", token)
            sendBroadcast(intent)
        }
    }

    companion object {
        private const val TAG = "FirebaseService"
    }
}