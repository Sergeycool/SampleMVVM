package net.code.sample.data.repository;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonObject;

import net.code.sample.data.remote.RetrofitServiceHolder;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginRepository {
    private static final String TAG = LoginRepository.class.getSimpleName();

    private static final LoginRepository INSTANCE = new LoginRepository();

    private LoginRepository() {
    }

    public static LoginRepository getInstance() {
        return INSTANCE;
    }

    @NonNull
    public Single<Response<ValidatePhoneNumberResponse>> validatePhoneNumber(
            @NonNull String phoneCountryCode,
            @NonNull String nationalPhoneNumber,
            @NonNull String internationalPhoneNumber,
            @NonNull String ISO2CountryCode) {
        JsonObject data = new JsonObject();
        data.addProperty("phone_local", nationalPhoneNumber);
        data.addProperty("phone_int", internationalPhoneNumber);
        data.addProperty("phone_country", ISO2CountryCode);
        data.addProperty("phone_prefix", phoneCountryCode);

        return RetrofitServiceHolder.getSampleLifeApiService().validatePhoneNumber(data);
    }

    @NonNull
    public Single<Response<NoDataResponse>> validatePhoneCode(@NonNull String userId,
                                                              @NonNull String hash,
                                                              @NonNull String smsCode) {
        JsonObject data = new JsonObject();
        data.addProperty("user_id", userId);
        data.addProperty("hash", hash);
        data.addProperty("phone_code", smsCode);

        return RetrofitServiceHolder.getSampleLifeApiService().validatePhoneCode(data);
    }

    @NonNull
    public Single<Response<NoDataResponse>> signUp(@NonNull String userId,
                                                   @NonNull String hash,
                                                   @NonNull String firstName,
                                                   @NonNull String lastName,
                                                   @NonNull String email,
                                                   @NonNull String password,
                                                   @NonNull String avatarUrl) {
        JsonObject data = new JsonObject();
        data.addProperty("user_id", userId);
        data.addProperty("hash", hash);
        data.addProperty("firstname", firstName);
        data.addProperty("lastname", lastName);
        data.addProperty("email", email);
        data.addProperty("password", password); // min length 6 characters
        data.addProperty("avatar_url", avatarUrl);

        return RetrofitServiceHolder.getSampleLifeApiService().signUp(data);
    }

    @NonNull
    public Single<Response<NoDataResponse>> signUpWithoutAvatar(@NonNull String userId,
                                                               @NonNull String hash,
                                                               @NonNull String firstName,
                                                               @NonNull String lastName,
                                                               @NonNull String email,
                                                               @NonNull String password) {
        JsonObject data = new JsonObject();
        data.addProperty("user_id", userId);
        data.addProperty("hash", hash);
        data.addProperty("firstname", firstName);
        data.addProperty("lastname", lastName);
        data.addProperty("email", email);
        data.addProperty("password", password); // min length 6 characters

        return RetrofitServiceHolder.getSampleLifeApiService().signUp(data);
    }

    @NonNull
    public Single<Response<LoginResponse>> login(@NonNull String email,
                                                 @NonNull String password) {
        JsonObject data = new JsonObject();
        data.addProperty("email", email);
        data.addProperty("password", password); // min length 6 characters

        return RetrofitServiceHolder.getSampleLifeApiService().login(data);
    }

    @NonNull
    public Single<Response<NoDataResponse>> resetPassword(@NonNull String email) {
        JsonObject data = new JsonObject();
        data.addProperty("email", email);

        return RetrofitServiceHolder.getSampleLifeApiService().resetPassword(data);
    }

    @NonNull
    public Single<Response<NoDataResponse>> sendAppInfo(@NonNull String userId,
                                                        @NonNull String hash,
                                                        @NonNull String appVersion,
                                                        @NonNull String androidVersion) {
        JsonObject data = new JsonObject();
        data.addProperty("user_id", userId);
        data.addProperty("hash", hash);
        data.addProperty("version", appVersion);
        data.addProperty("os_version", androidVersion);

        return RetrofitServiceHolder.getSampleLifeApiService().sendAppInfo(data);
    }

    @NonNull
    public Single<Response<NoDataResponse>> sendFcmToken(@NonNull String userId,
                                                         @NonNull String hash,
                                                         @NonNull String fcmToken) {
        JsonObject data = new JsonObject();
        data.addProperty("user_id", userId);
        data.addProperty("hash", hash);
        data.addProperty("push_type", "a");
        data.addProperty("push_token", fcmToken);

        return RetrofitServiceHolder.getSampleLifeApiService().sendFcmToken(data);
    }

    @NonNull
    public Single<String> getFcmToken() {
        return Single.create((SingleOnSubscribe<String>) emitter ->
                FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        @SuppressWarnings("ConstantConditions")
                        String token = task.getResult().getToken();
                        emitter.onSuccess(token);
                    } else {
                        emitter.onError(task.getException());
                    }
                }))
                .observeOn(Schedulers.io());  // because emits to the main thread
    }
}
