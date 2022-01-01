package com.mzk.sqliteproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragmentUser extends Fragment {


    Account account;
    TextView userprofile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);


        userprofile = (TextView) v.findViewById(R.id.userprofileID);

        loadData();
        return v;
    }


    private void loadData() {


        //Bundle args = new Bundle();

        assert getArguments() != null;
        account = (Account) getArguments().getParcelable("account");

         userprofile.setText(account.getUsername());

    }

}
