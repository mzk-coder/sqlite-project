package com.mzk.sqliteproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class LogIn extends AppCompatActivity {
    private EditText Username, Password;
    private Button btnlogin;
    private TextView viewRegister;


    DBHelper dariDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Username = (EditText) findViewById(R.id.username_ID);
        Password = (EditText) findViewById(R.id.password_id);
        btnlogin = (Button) findViewById(R.id.btn_logIn);
        viewRegister = (TextView) findViewById(R.id.register_id);
        dariDB = new DBHelper(this);

        viewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signup = new Intent(LogIn.this, SignUp.class);
                startActivity(signup);


            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = Username.getText().toString();
                String pwd = Password.getText().toString();

                if (user.equals("") || pwd.equals("")) {
                    Toast.makeText(LogIn.this, "not empty", Toast.LENGTH_SHORT).show();


                } else {

                    Account account = dariDB.checkUsernamePassword(user, pwd); //DariDB.checkUsernamePassword(user,pwd);
                    if (account == null) {

                        Toast.makeText(LogIn.this, "invalid username or password unsuccessfully", Toast.LENGTH_SHORT).show();

                    } else {


                        Toast.makeText(LogIn.this, "login successfully", Toast.LENGTH_SHORT).show();
                        Intent home1 = new Intent(getApplicationContext(), MainActivity.class);
                        home1.putExtra("account", account);
                        startActivity(home1);
                    }


                }


            }
        });
    }

}


