package com.sourabhkarkal.movieapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import com.sourabhkarkal.movieapp.ApplicationController;
import com.sourabhkarkal.movieapp.R;

/**
 * Created by sourabhkarkal on 24/02/17.
 */

public class Utils {

    public enum MessageType {
        INFO,
        ERROR,
        SUCCESS
    }

    /**
     * Checks and returns whether there is an Internet connectivity or not. This
     * would be useful to check the network connectivity before making a network
     * call.
     *
     * @return "True" -> is Connected , "False" -> if not.
     */
    public static boolean isNetworkAvailable() {
        boolean isConnected = false;
        final ConnectivityManager connectivityService = (ConnectivityManager) ApplicationController.getInstance().getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityService != null) {
            final NetworkInfo networkInfo = connectivityService
                    .getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                isConnected = true;
            }
        }
        return isConnected;
    }

    /***
     * show snack bar
     *
     * @param view
     * @param message
     * @param messageType
     */

    public static void showSnackBar(View view, String message, MessageType messageType) {
        Context context = ApplicationController.getInstance().getContext();
        Snackbar bar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        TextView textView = (TextView) bar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextSize(15);
        bar.getView().setBackgroundColor(Utils.getColor(context, R.color.light_black));
        if (messageType == MessageType.ERROR) {
            textView.setTextColor(Utils.getColor(context, R.color.snack_error));
        } else if (messageType == MessageType.INFO) {
            textView.setTextColor(Utils.getColor(context, R.color.snack_info));
        } else if (messageType == MessageType.SUCCESS) {
            textView.setTextColor(Utils.getColor(context, R.color.snack_success));
        }
        bar.show();
    }

    /**
     * Get color from resource according to api level
     *
     * @param context
     * @param id
     * @return
     */

    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }

    }

    /*
    * Replace fragment
    *
    * @param context
    * @param fragment
    */
    public static void replaceFragment(FragmentActivity context, Fragment fragment, boolean doAnimate) {
        String TAG = fragment.getClass().toString();
        String backStackName = fragment.getClass().getName();
        FragmentManager manager = context.getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped && context.getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            final android.support.v4.app.FragmentTransaction ft = context.getSupportFragmentManager().beginTransaction();
            if(doAnimate)
                ft.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.left_to_right,R.anim.right_to_left);
            else
                ft.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
            ft.replace(R.id.mainfragment, fragment, TAG);
            ft.addToBackStack(backStackName);
            ft.commit();
        }
    }


}
