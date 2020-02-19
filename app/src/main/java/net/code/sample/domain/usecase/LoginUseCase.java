package net.code.sample.domain.usecase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Single;

public class LoginUseCase {
    @NonNull
    public Observable<RxMessage<LoginResponse.Data>> execute(@NonNull String email, @NonNull String password) {
        Single<RxMessage<LoginResponse.Data>> startSingle = Single.just(RxMessage.onStart());

        Single<RxMessage<LoginResponse.Data>> requestSingle = LoginRepository.getInstance().login(email, password)
                .map(response -> {
                    if (response.isSuccessful() && response.body() != null) {
                        LoginResponse.Data data = response.body().getData();
                        if (response.body().isSuccessful() && data != null) {

                            // Log to Firebase Analytics
                            Bundle bundle = new Bundle();
                            bundle.putString("email", email);
                            Analytics.logEvent(Analytics.LOGIN, bundle);

                            UserDataRepository userDataRepository = UserDataRepository.getInstance();
                            userDataRepository.saveUserId(data.getUserId());
                            userDataRepository.saveHash(data.getHash());
                            userDataRepository.saveFirstName(data.getFirstName());
                            userDataRepository.saveLastName(data.getLastName());
                            userDataRepository.saveAvatarUrl(data.getAvatarUrl());
                            userDataRepository.saveLoggedIn(true);
                            return RxMessage.onNextLast(response.body().getData());
                        } else {
                            throw new RxResponseException(response.code(), response.body().getMessage());
                        }
                    } else {
                        throw new RxResponseException(response);
                    }
                })
                .onErrorReturn(RxMessage::onError);

        // startSingle.concatWith(requestSingle);
        return Observable.concat(startSingle.toObservable(), requestSingle.toObservable());
    }
}
