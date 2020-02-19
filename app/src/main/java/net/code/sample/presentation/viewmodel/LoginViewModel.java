package net.code.sample.presentation.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();

    private final SingleLiveEvent mOnLoginClicked = new SingleLiveEvent();
    private final SingleLiveEvent mOnForgottenPasswordClicked = new SingleLiveEvent();

    private final SingleLiveEvent<RxMessage<LoginResponse.Data>> mLoginMessage = new SingleLiveEvent<>();
    private final SingleLiveEvent<RxMessage> mResetPasswordMessage = new SingleLiveEvent<>();

    private final MutableLiveData<Boolean> mIsValidEmail = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsValidPassword = new MutableLiveData<>();
    private final SingleLiveEvent<Boolean> mIsValidResetEmail = new SingleLiveEvent<>();

    public SingleLiveEvent getOnLoginClicked() {
        return mOnLoginClicked;
    }

    public void onLoginClicked(@Nullable String email, @Nullable String password) {
        mOnLoginClicked.call();

        boolean isValidEmail = new IsValidEmailUseCase().execute(email);
        mIsValidEmail.setValue(isValidEmail);
        boolean isValidPassword = new IsValidPasswordUseCase().execute(password);
        mIsValidPassword.setValue(isValidPassword);
        if (isValidEmail && isValidPassword) {
            //noinspection ConstantConditions
            getDisposables().add(new LoginUseCase().execute(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mLoginMessage::setValue));
        }
    }

    public SingleLiveEvent getOnForgottenPasswordClicked() {
        return mOnForgottenPasswordClicked;
    }

    public void onForgottenPasswordClicked() {
        mOnForgottenPasswordClicked.call();
    }

    public SingleLiveEvent<RxMessage<LoginResponse.Data>> getLoginMessage() {
        return mLoginMessage;
    }

    public SingleLiveEvent<RxMessage> getResetPasswordMessage() {
        return mResetPasswordMessage;
    }

    public MutableLiveData<Boolean> getIsValidEmail() {
        return mIsValidEmail;
    }

    public MutableLiveData<Boolean> getIsValidPassword() {
        return mIsValidPassword;
    }

    public SingleLiveEvent<Boolean> getIsValidResetEmail() {
        return mIsValidResetEmail;
    }

    public void resetPassword(@Nullable String email) {
        boolean isValidResetEmail = new IsValidEmailUseCase().execute(email);
        mIsValidResetEmail.setValue(isValidResetEmail);
        if (isValidResetEmail) {
            //noinspection ConstantConditions
            getDisposables().add(new ResetPasswordUseCase().execute(email)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mResetPasswordMessage::setValue));
        }
    }
}
