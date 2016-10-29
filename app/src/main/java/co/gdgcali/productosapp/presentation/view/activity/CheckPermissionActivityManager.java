package co.gdgcali.productosapp.presentation.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by carboleda@mobilelab.com.co on 15/03/16.
 */
public class CheckPermissionActivityManager extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static CheckingPermissionListener checkingPermissionListener;

    public interface CheckingPermissionListener {
        void onResult(boolean isGranted);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> lstPermissions = getIntent().getStringArrayListExtra("permissions");

        // Should we show an explanation?
        //if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
        // Show an expanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.
        //} else {
        // No explanation needed, we can request the permission.
        String[] arrPermissions = new String[lstPermissions.size()];
        lstPermissions.toArray(arrPermissions);
        ActivityCompat.requestPermissions(this, arrPermissions, PERMISSION_REQUEST_CODE);
        //}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        try {
            CheckPermissionActivityManager.checkingPermissionListener.onResult(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        } finally {
            CheckPermissionActivityManager.checkingPermissionListener = null;
        }
    }

    public static boolean checkPermission(Context context, String permission,
                                          CheckingPermissionListener checkingPermissionListener) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            if(checkingPermissionListener != null) {
                CheckPermissionActivityManager.checkingPermissionListener = checkingPermissionListener;
                Intent intent = new Intent(context, CheckPermissionActivityManager.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("permissions", new String[]{permission});
                context.startActivity(intent);
            }

            return false;
        } else {
            return true;
        }
    }

    public static boolean checkPermissions(Context context,
                                           CheckingPermissionListener checkingPermissionListener,
                                           String ...permissions) {
        ArrayList<String> lstPermisionsToRequest = null;

        if(checkingPermissionListener != null) {
            lstPermisionsToRequest = new ArrayList<>(0);
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                    lstPermisionsToRequest.add(permission);
                }
            }
        }

        if(lstPermisionsToRequest != null && !lstPermisionsToRequest.isEmpty()) {
            CheckPermissionActivityManager.checkingPermissionListener = checkingPermissionListener;
            Intent intent = new Intent(context, CheckPermissionActivityManager.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("permissions", lstPermisionsToRequest);
            context.startActivity(intent);

            return false;
        } else {
            return true;
        }
    }
}
