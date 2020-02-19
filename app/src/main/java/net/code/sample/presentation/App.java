package net.code.sample.presentation;

import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import im.crisp.sdk.Crisp;
import io.fabric.sdk.android.Fabric;
import io.reactivex.plugins.RxJavaPlugins;

public class App extends MultiDexApplication {
    private static final String TAG = App.class.getSimpleName();
    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        RxJavaPlugins.setErrorHandler(throwable -> WLog.e(TAG, throwable));
        if (BuildConfig.IS_PRODUCTION) Analytics.initialize(this);
        Fabric.with(this, new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(!BuildConfig.IS_PRODUCTION).build())
                .build());

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        // getApplicationContext().startService(new Intent(getAppContext(), TransferService.class));
        TransferNetworkLossHandler.getInstance(getAppContext());

        Crisp.initialize(getAppContext());
        Crisp.getInstance().setWebsiteId(CRISP_CHAT_WEBSITE_ID);
        // Crisp.getInstance().setLocale("en");

        CacheImagesUtil.deleteAvatarCacheDir();
        new RemoveUnusedPicturesUseCase().execute();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    /**
     * Every Android app is guaranteed to have exactly one {@link android.app.Application} instance
     * for the lifetime of the app.
     * But there is no guarantee that the non-static onCreate() will have been called before
     * some static initialization code tries to fetch your Context object.
     * (in some cases null check is required)
     */
    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
