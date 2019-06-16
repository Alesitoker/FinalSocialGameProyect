package com.iessaladillo.alejandro.finalsocialgameproyect.ui.signUp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.iessaladillo.alejandro.finalsocialgameproyect.R;
import com.iessaladillo.alejandro.finalsocialgameproyect.databinding.FragmentSignupBinding;
import com.iessaladillo.alejandro.finalsocialgameproyect.utils.SnackbarUtils;
import com.iessaladillo.alejandro.finalsocialgameproyect.utils.TextUtils.AfterTextChanged;

import static com.iessaladillo.alejandro.finalsocialgameproyect.utils.ValidationUtils.*;

public class SignUpFragment extends Fragment {

    private FirebaseAuth mAuth;
    private NavController navController;
    private FragmentSignupBinding b;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentSignupBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        mAuth = FirebaseAuth.getInstance();
        setupViews();
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
//        updateUI(user);
    }

    private void setupViews() {
        b.btnSignUp.setOnClickListener(v -> createAccount(b.txtEmail.getText().toString().trim(),
                b.txtPassword.getText().toString().trim()));
        b.lblReturnLogin.setOnClickListener(v -> navigateToLogin());

        b.txtEmail.addTextChangedListener((AfterTextChanged) s -> enableSignUp());
        b.txtPassword.addTextChangedListener((AfterTextChanged) s -> enableSignUp());
        b.txtUser.addTextChangedListener((AfterTextChanged) s -> enableSignUp());

    }

    private void enableSignUp() {
        if (b.txtUser.getText().toString().isEmpty() || b.txtEmail.getText().toString().isEmpty()
                || b.txtPassword.getText().toString().isEmpty()) {
            b.btnSignUp.setEnabled(false);
        } else {
            b.btnSignUp.setEnabled(true);
            validateForm();
        }
    }

    private void createAccount(String email, String password) {
        // TODO: quitar log, mejorar la sensacion de registro
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        Log.d("tag", "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateProfile(user);
//                        updateUI(user);
                    } else {
//                        Log.w("tag", "createUserWithEmail:failure", task.getException());
//                        Toast.makeText(requireContext(), task.getResult().getAdditionalUserInfo().toString(),
//                                Toast.LENGTH_SHORT).show();

                        try {
                            throw task.getException();
                        } catch(FirebaseAuthWeakPasswordException e) {
                            validateForm();
                            b.txtSignupPassword.requestFocus();
                        } catch(FirebaseAuthInvalidCredentialsException e) {
                            validateForm();
                            b.txtSignupEmail.requestFocus();
                        } catch(FirebaseAuthUserCollisionException e) {
                            SnackbarUtils.snackbar(requireView(), getString(R.string.error_user_exists));
                        } catch(Exception e) {
                            Log.e("tag", e.getMessage());
                        }
//                        updateUI(null);
                    }

//                    hideProgressDialog();
                });
    }

    private void validateForm() {
        if (!isValidEmail(b.txtEmail.getText().toString())) {
            b.txtSignupEmail.setError(getString(R.string.error_invalid_email));
        } else {
            b.txtSignupEmail.setErrorEnabled(false);
        }

        if (b.txtPassword.getText().toString().length() < 5) {
            b.txtSignupPassword.setError(getString(R.string.error_weak_password));
        } else {
            b.txtSignupPassword.setErrorEnabled(false);
        }
    }

    private void updateProfile(FirebaseUser user) {
        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(b.txtUser.getText().toString())
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d("tag", "User profile updated.");
                        }
                    });
        }
    }

    private void navigateToLogin() {
        navController.popBackStack();
    }
}
