package com.mzk.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Account account = getIntent().getParcelableExtra("account");

        Bundle bundle = new Bundle();
        bundle.putParcelable("account", account);

        FragmentUser fragmentUser = new FragmentUser();
        fragmentUser.setArguments(bundle);


        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragmentUser).commit();

    }
}