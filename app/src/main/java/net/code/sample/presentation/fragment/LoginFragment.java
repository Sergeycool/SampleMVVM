package net.code.sample.presentation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class LoginFragment extends BaseFragment<LoginViewModel, LoginSharedViewModel,
        FragmentLoginBinding> {
    private static final String TAG = LoginFragment.class.getSimpleName();

    private CommonDialogListenerRestorer mListenerRestorer = new CommonDialogListenerRestorer(this)
            .put(TAG_DIALOG_FORGOTTEN_PASSWORD, new CommonDialog.OnClickListener() {
                @Override
                public void onButton2Clicked(@Nullable Intent data) {
                    String email = data != null ?
                            data.getStringExtra(EXTRA_DIALOG_EDIT_VIEW_1_TEXT) : null;
                    mViewModel.resetPassword(email);
                }
            });

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    protected void setViewModelVariableInBinding() {
        mBinding.setViewModel(mViewModel);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //noinspection ConstantConditions
        KeyboardUtil.removeFocusOnKeyboardActionDone(getContext(),
                mBinding.editPassword, mBinding.getRoot());

        mViewModel.getOnLoginClicked().observe(getViewLifecycleOwner(), o ->
                KeyboardUtil.hideKeyboard(getContext(), mBinding.getRoot()));

        mViewModel.getOnForgottenPasswordClicked().observe(getViewLifecycleOwner(), o ->
                CommonDialogFactory.showForgotPasswordDialog(LoginFragment.this,
                        TAG_DIALOG_FORGOTTEN_PASSWORD,
                        mListenerRestorer.getOnClickListener(TAG_DIALOG_FORGOTTEN_PASSWORD)));

        mViewModel.getLoginMessage().observe(getViewLifecycleOwner(), rxMessage -> {
            if (rxMessage.isStart()) {
                mBinding.progressButtonLogin.showProgressBar(true);
            } else if (rxMessage.isComplete()) {
                mBinding.progressButtonLogin.showProgressBar(false);
                if (rxMessage.getError() == null) {
                    if (rxMessage.getResult() != null)
                        mSharedViewModel.navigateToHome();
                    else
                        mSharedViewModel.navigateToHome();
                } else {
                    if (rxMessage.getError() instanceof RxResponseException) {
                        RxResponseException rxException = (RxResponseException) rxMessage.getError();
                        if (rxException.getCode() == 200) {
                            // Looks like this is not the correct password. Or the correct email.
                            CommonDialogFactory.showErrorDialog(LoginFragment.this,
                                    null, null, rxException.getMessage());
                            // CommonDialogFactory.showWrongEmailOrPasswordDialog(LoginFragment.this, null, null);
                        } else {
                            CommonDialogFactory.showServerErrorDialog(LoginFragment.this);
                        }
                    } else {
                        CommonDialogFactory.showNetworkErrorDialog(LoginFragment.this);
                    }
                }
            }
        });

        mViewModel.getResetPasswordMessage().observe(getViewLifecycleOwner(), rxMessage -> {
            if (rxMessage.isStart()) {
                mBinding.progressBar.setVisibility(View.VISIBLE);
            } else if (rxMessage.isComplete()) {
                mBinding.progressBar.setVisibility(View.GONE);
                if (rxMessage.getError() == null) {
                    CommonDialogFactory.showNewPasswordDialog(LoginFragment.this, null, null);
                } else {
                    if (rxMessage.getError() instanceof RxResponseException) {
                        RxResponseException rxException = (RxResponseException) rxMessage.getError();
                        if (rxException.getCode() == 200) {
                            CommonDialogFactory.showWrongEmailFormatDialog(LoginFragment.this, null, null);
                        } else {
                            CommonDialogFactory.showServerErrorDialog(LoginFragment.this);
                        }
                    } else {
                        CommonDialogFactory.showNetworkErrorDialog(LoginFragment.this);
                    }
                }
            }
        });

        mViewModel.getIsValidEmail().observe(getViewLifecycleOwner(), isValid ->
                mBinding.editEmail.setError(!isValid));

        mViewModel.getIsValidPassword().observe(getViewLifecycleOwner(), isValid ->
                mBinding.editPassword.setError(!isValid));

        mViewModel.getIsValidResetEmail().observe(getViewLifecycleOwner(), isValid -> {
            if (!isValid) {
                CommonDialogFactory.showWrongEmailFormatDialog(LoginFragment.this, null, null);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardUtil.showKeyboard(getContext(), mBinding.editEmail);
    }

    @Override
    public void onPause() {
        super.onPause();
        KeyboardUtil.hideKeyboard(getContext(), mBinding.getRoot());
    }
}
