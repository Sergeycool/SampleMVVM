package net.code.sample.toolchain;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressWarnings({"unused"})
public class KeyboardUtil {
    public static void showKeyboard(@Nullable Context context, @NonNull EditText editText) {
        if (context != null) {
            editText.requestFocus();
            InputMethodManager imm = getInputMethodManager(context);
            if (imm != null) {
                // imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                imm.showSoftInput(editText, 0);
            }
        }
    }

    public static void hideKeyboard(@Nullable Context context, @NonNull View view) {
        if (context != null) {
            InputMethodManager imm = getInputMethodManager(context);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static void setKeyboardVisible(@Nullable Activity activity, boolean visible) {
        if (activity != null) {
            Window window = activity.getWindow();
            if (window != null) {
                window.setSoftInputMode(visible ?
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE :
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        }
    }

    public static void setKeyboardVisible(@Nullable Dialog dialog, boolean visible) {
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setSoftInputMode(visible ?
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE :
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        }
    }

    public static void setOnlyNumbersAvailable(@NonNull EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        editText.setTransformationMethod(new PasswordTransformationMethod() {
            @Override
            public CharSequence getTransformation(CharSequence source, View view) {
                return source;
            }
        });
    }

    public static void removeFocusOnKeyboardActionDone(@NonNull Context context,
                                                       @NonNull EditText editText,
                                                       @NonNull View viewToSetFocusOn) {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewToSetFocusOn.setFocusable(true);
                viewToSetFocusOn.setFocusableInTouchMode(true);
                viewToSetFocusOn.requestFocus();
                KeyboardUtil.hideKeyboard(context, editText);
            }
            return false;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void setAllViewsToHideKeyboardOnTouch(@Nullable Context context,
                                                        @NonNull View rootView) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(rootView instanceof EditText)) {
            rootView.setOnTouchListener((v, event) -> {
                KeyboardUtil.hideKeyboard(context, rootView);
                return false;
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (rootView instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) rootView).getChildCount(); i++) {
                View innerView = ((ViewGroup) rootView).getChildAt(i);
                setAllViewsToHideKeyboardOnTouch(context, innerView);
            }
        }
    }

    @Nullable
    private static InputMethodManager getInputMethodManager(@NonNull Context context) {
        return (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    }
}
