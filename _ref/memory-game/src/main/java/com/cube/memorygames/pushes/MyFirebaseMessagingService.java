package com.cube.memorygames.pushes;

import com.cube.memorygames.MemoryApplicationModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        MemoryApplicationModel.getInstance().onlineEventReceived(PushPayloadProcessor.processPayload(remoteMessage.getData()));
    }
}
