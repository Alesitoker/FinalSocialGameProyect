package com.iessaladillo.alejandro.finalsocialgameproyect.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.iessaladillo.alejandro.finalsocialgameproyect.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.loginFragment, R.id.signInFragment, R.id.listGroupsFragment, R.id.yourListGroupsFragment).build();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
