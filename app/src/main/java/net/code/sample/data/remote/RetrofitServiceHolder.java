package net.code.sample.data.remote;

import androidx.annotation.NonNull;

import net.code.sample.data.remote.api.AppApiService;
import net.code.sample.toolchain.RetrofitUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RetrofitServiceHolder {
    private static final String TAG = RetrofitServiceHolder.class.getSimpleName();

    @NonNull
    private static final Retrofit APP_LIFE_RETROFIT = RetrofitUtil.createRetrofit(
            DataConstants.APPLIFE_BASE_URL, getAppLifeOkHttpClient());

    @NonNull
    private static final AppApiService APP_LIFE_API_SERVICE = APP_LIFE_RETROFIT
            .create(AppLifeApiService.class);

    @NonNull
    public static Retrofit getAppLifeRetrofit() {
        return APP_LIFE_RETROFIT;
    }

    @NonNull
    public static AppApiService getAppLifeApiService() {
        return APP_LIFE_API_SERVICE;
    }

    @NonNull
    private static OkHttpClient getAppLifeOkHttpClient() {
        return RetrofitUtil.getSharedOkHttpClientBuilder()
                .connectTimeout(DataConstants.OK_HTTP_CLIENT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DataConstants.OK_HTTP_CLIENT_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DataConstants.OK_HTTP_CLIENT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
