package net.code.sample.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.io.File;

public class LoginSharedViewModel extends BaseViewModel {
    private static final String TAG = LoginSharedViewModel.class.getSimpleName();

    private final SingleLiveEvent mNavigateToRequests = new SingleLiveEvent<>();
    private final SingleLiveEvent mNavigateToLogin = new SingleLiveEvent();
    private final SingleLiveEvent mNavigateToSignUpValidatePhoneNumber = new SingleLiveEvent();
    private final SingleLiveEvent mNavigateToSignUpSelectCountry = new SingleLiveEvent();
    private final SingleLiveEvent mNavigateToSignUpValidatePhoneCode = new SingleLiveEvent();
    private final SingleLiveEvent mNavigateToSignUpEnterData = new SingleLiveEvent();
    private final SingleLiveEvent mNavigateToTerms = new SingleLiveEvent();
    private final SingleLiveEvent mNavigateToPrivacyPolicy = new SingleLiveEvent();
    private final SingleLiveEvent mNavigateToTakePhoto = new SingleLiveEvent();
    private final SingleLiveEvent mNavigateToSelectGalleryImage = new SingleLiveEvent();

    private final MutableLiveData<CountryCodesInfo> mSelectedCountryCodesInfo =
            new MutableLiveData<>(getPhoneCountryCodeInfoForCurrentLocale());
    private final MutableLiveData<File> mAvatarFile = new MutableLiveData<>();

    public SingleLiveEvent getNavigateToHome() {
        return mNavigateToRequests;
    }

    public void navigateToHome() {
        mNavigateToRequests.call();
    }

    public SingleLiveEvent getNavigateToLogin() {
        return mNavigateToLogin;
    }

    public void navigateToLogin() {
        mNavigateToLogin.call();
    }

    public SingleLiveEvent getNavigateToSignUpValidatePhoneNumber() {
        return mNavigateToSignUpValidatePhoneNumber;
    }

    public void navigateToSignUp() {
        mNavigateToSignUpValidatePhoneNumber.call();
    }

    public SingleLiveEvent getNavigateToSignUpSelectCountry() {
        return mNavigateToSignUpSelectCountry;
    }

    public void navigateToSignUpSelectCountry() {
        mNavigateToSignUpSelectCountry.call();
    }

    public SingleLiveEvent getNavigateToSignUpValidatePhoneCode() {
        return mNavigateToSignUpValidatePhoneCode;
    }

    public void navigateToSignUpValidatePhoneCode() {
        mNavigateToSignUpValidatePhoneCode.call();
    }

    public SingleLiveEvent getNavigateToSignUpEnterData() {
        return mNavigateToSignUpEnterData;
    }

    public void navigateToSignUpEnterData() {
        mNavigateToSignUpEnterData.call();
    }

    public SingleLiveEvent getNavigateToTerms() {
        return mNavigateToTerms;
    }

    public void navigateToTerms() {
        mNavigateToTerms.call();
    }

    public SingleLiveEvent getNavigateToPrivacyPolicy() {
        return mNavigateToPrivacyPolicy;
    }

    public void navigateToPrivacyPolicy() {
        mNavigateToPrivacyPolicy.call();
    }

    public SingleLiveEvent getNavigateToTakePhoto() {
        return mNavigateToTakePhoto;
    }

    public void navigateToTakePhoto() {
        mNavigateToTakePhoto.call();
    }

    public SingleLiveEvent getNavigateToSelectGalleryImage() {
        return mNavigateToSelectGalleryImage;
    }

    public void navigateToSelectGalleryImage() {
        mNavigateToSelectGalleryImage.call();
    }

    public MutableLiveData<CountryCodesInfo> getSelectedCountryCodesInfo() {
        return mSelectedCountryCodesInfo;
    }

    public MutableLiveData<File> getAvatarFile() {
        return mAvatarFile;
    }

    @NonNull
    private CountryCodesInfo getPhoneCountryCodeInfoForCurrentLocale() {
        return new GetPhoneCountryCodeInfoForCurrentLocaleUseCase().execute();
    }
}
