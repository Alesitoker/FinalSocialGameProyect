package com.iessaladillo.alejandro.finalsocialgameproyect.ui.Chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iessaladillo.alejandro.finalsocialgameproyect.databinding.FragmentChatBinding;

public class ChatFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FragmentChatBinding b;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentChatBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setupViews();
    }

    private void setupViews() {
        FirebaseUser user = mAuth.getCurrentUser();

    }
}
