package com.iessaladillo.alejandro.finalsocialgameproyect.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public interface AfterTextChanged extends TextWatcher {
        @Override
        default void beforeTextChanged(CharSequence s, int start, int count, int after) {}


        @Override
        default void onTextChanged(CharSequence s, int start, int before, int count) {}
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



}
