package com.example.autoclassscheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.autoclassscheduler.Utility.Constants
import com.example.autoclassscheduler.Utility.SharedPrefForLink
import com.example.autoclassscheduler.Utility.timerUtility


class AlertReceiver : BroadcastReceiver(){

    private var timerUtility: timerUtility? = null
    private lateinit var prefProvider: SharedPrefForLink

    override fun onReceive(p0: Context?, p1: Intent?) {

        //waking up the screen
        //p0?.let { timerUtility?.wakelocker(it) }

        //opening the link in browser when we hit the scheduled time
        var link = prefProvider.getString(Constants.link_key)

        link?.let { Log.i("linktoAR", it) }
        if (link != null) {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            //Get the package name of the default browser to launch it
            val resolveInfo: ResolveInfo = p0?.getPackageManager()?.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)!!
            val packageNameofBrowser = resolveInfo.activityInfo.packageName

            intent.setPackage(packageNameofBrowser)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            p0.startActivity(intent)
        }else{
            Log.i("linktoAR","invalid link")
        }


        timerUtility?.release()
    }
}
//Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//browserIntent.setPackage("com.android.chrome");
//context.startActivity(browserIntent);

//start activity
//Intent i = new Intent();
//i.setClassName("com.test", "com.test.MainActivity");
//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//context.startActivity(i);
