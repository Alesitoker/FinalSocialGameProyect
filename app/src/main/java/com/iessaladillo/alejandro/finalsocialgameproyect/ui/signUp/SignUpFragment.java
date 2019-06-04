package com.iessaladillo.alejandro.finalsocialgameproyect.ui.signUp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.iessaladillo.alejandro.finalsocialgameproyect.databinding.FragmentSignupBinding;

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
        b.btnSignUp.setOnClickListener(v -> createAccount(b.txtEmail.getText().toString().trim(), b.txtPassword.getText().toString().trim()));
        b.lblReturnLogin.setOnClickListener(v -> returnToLogin());
    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        Log.d("tag", "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateProfile(user);
//                        updateUI(user);
                    } else {
                        Log.w("tag", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(requireActivity(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
//                        updateUI(null);
                    }

//                    hideProgressDialog();
                });
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

    private void returnToLogin() {
        navController.popBackStack();
    }
}
