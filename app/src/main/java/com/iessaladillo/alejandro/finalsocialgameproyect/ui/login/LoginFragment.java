package com.iessaladillo.alejandro.finalsocialgameproyect.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.iessaladillo.alejandro.finalsocialgameproyect.R;
import com.iessaladillo.alejandro.finalsocialgameproyect.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    private NavController navController;
    private FragmentLoginBinding b;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentLoginBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        mAuth = FirebaseAuth.getInstance();
        setupViews();
    }

    private void setupViews() {
        b.lblNotRegistered.setOnClickListener(v -> navigateToSignIn());
    }

    private void navigateToSignIn() {
        navController.navigate(R.id.actionLoginToSignin);
    }
}
