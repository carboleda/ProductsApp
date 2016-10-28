package co.gdgcali.productosapp.service;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import co.gdgcali.productosapp.domain.interactor.ProductInteractor;
import co.gdgcali.productosapp.domain.interactor.impl.ProductInteractorImpl;
import co.gdgcali.productosapp.presentation.view.fragement.ListProductsFragment;

/**
 * Created by krlosf on 24/10/16.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessaging";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            ProductInteractor productInteractor = new ProductInteractorImpl();
            productInteractor.syncProducts(new ProductInteractor.Callback() {
                @Override
                public void success(Object result) {
                    Intent intent = new Intent(ListProductsFragment.ACTION_REFRESH_PRODUCTS);
                    sendBroadcast(intent);
                }

                @Override
                public void error(Throwable error) {

                }
            });

            /*try {
                String jsonProduct = remoteMessage.getNotification().getBody();
                Gson gson = new Gson();
                Product product = gson.fromJson(jsonProduct, Product.class);
                Log.d(TAG, product.getName());
            } catch (Exception e) {
                Log.e(TAG, "Error gson", e);
            }*/
        }
    }
}
