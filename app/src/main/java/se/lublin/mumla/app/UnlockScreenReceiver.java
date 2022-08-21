package se.lublin.mumla.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class UnlockScreenReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("mumla","hi");
        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            Intent dialogIntent = new Intent(context, MumlaActivity.class);
            dialogIntent.putExtra(MumlaActivity.EXTRA_DRAWER_FRAGMENT, DrawerAdapter.ITEM_PINNED_CHANNELS);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(dialogIntent);
        }

    }
}
