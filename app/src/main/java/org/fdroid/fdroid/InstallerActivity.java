package org.fdroid.fdroid;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.ApkProvider;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.installer.InstallManagerService;

public class InstallerActivity extends AppCompatActivity {
    private static final String TAG = "InstallerActivity";
    public static final String EXTRA_PACKAGE = "EXTRA_PACKAGE";
    public static final String EXTRA_VERSION_CODE = "EXTRA_VERSION_CODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            String appPackage = getIntent().getStringExtra(EXTRA_PACKAGE);
            Integer appVersionCode = getIntent().getIntExtra(EXTRA_VERSION_CODE, 0);

            Log.d(TAG,"Installing "+appPackage + " versionCode: "+appVersionCode);

            App suggestedApp = new App();
            suggestedApp.packageName = appPackage;
            suggestedApp.suggestedVersionCode = appVersionCode;

            Apk apk = ApkProvider.Helper.findSuggestedApk(getApplicationContext(), suggestedApp);
            InstallManagerService.queue(this, suggestedApp, apk);

            Log.d(TAG,"Package "+ appPackage +" enqeued ");

            setResult(Activity.RESULT_OK);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
            setResult(Activity.RESULT_CANCELED);
        }finally {
            finish();
        }
    }
}
