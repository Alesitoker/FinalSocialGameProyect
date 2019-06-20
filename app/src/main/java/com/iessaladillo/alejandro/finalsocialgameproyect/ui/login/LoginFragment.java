package com.iessaladillo.alejandro.finalsocialgameproyect.ui.login;

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

import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.iessaladillo.alejandro.finalsocialgameproyect.R;
import com.iessaladillo.alejandro.finalsocialgameproyect.databinding.FragmentLoginBinding;
import com.iessaladillo.alejandro.finalsocialgameproyect.utils.SnackbarUtils;
import com.iessaladillo.alejandro.finalsocialgameproyect.utils.TextUtils.AfterTextChanged;

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

    @Override
    public void onStart() {
        super.onStart();
        //TODO: Comprobar si la sesion esta iniciada.
        FirebaseUser user = mAuth.getCurrentUser();
    }

    private void setupViews() {
        b.btnLogin.setOnClickListener(v -> logIn(b.txtEmail.getText().toString().trim(),
                b.txtPassword.getText().toString().trim()));
        b.lblNotRegistered.setOnClickListener(v -> navigateToSignIn());

        b.txtEmail.addTextChangedListener((AfterTextChanged) s -> enableLogIn());
        b.txtPassword.addTextChangedListener((AfterTextChanged) s -> enableLogIn());
    }

    private void enableLogIn() {
        if (b.txtEmail.getText().toString().isEmpty() || b.txtPassword.getText().toString().isEmpty()) {
            b.btnLogin.setEnabled(false);
        } else {
            b.btnLogin.setEnabled(true);
        }
    }
    //TODO: No poder ir atras hacia el log in al iniciar sesión.
    private void logIn(String email, String password) {
        // TODO: quitar log, hacer el login mas intuitivo
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        Log.d("tagL", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        navigateToListGroups();
                    } else {
                        Log.w("tagL", "signInWithEmail:failure", task.getException());
//                        Toast.makeText(requireContext(), "Authentication failed.",
//                                Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            SnackbarUtils.snackbar(requireView(), getString(R.string.LoginFragment_AuthInvalidCredentials));
                        } catch (FirebaseNetworkException e) {
                            SnackbarUtils.snackbar(requireView(), getString(R.string.LoginFragment_NetworkError));
                        } catch (Exception e) {
                            SnackbarUtils.snackbar(requireView(), e.getMessage());
                        }
                    }

//                   hideProgressDialog();
                });
    }

    private void navigateToListGroups() {
        navController.navigate(R.id.actionLoginToListGroups);
    }

    private void navigateToSignIn() {
        navController.navigate(R.id.actionLoginToSignin);
    }
}
