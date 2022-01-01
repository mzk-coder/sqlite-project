package com.mzk.sqliteproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    DBHelper db;
    EditText username;
    EditText password;
    EditText confirmPassword;


    TextView txtlogin;
    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.username_id);
        password = (EditText) findViewById(R.id.password_id);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword_id);



        txtlogin = (TextView)  findViewById(R.id.logIn_id);
        btnSignUp = (Button) findViewById(R.id.signUp);

        db = new DBHelper(this);
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent goToLogIn = new Intent(SignUp.this, LogIn.class);
                startActivity(goToLogIn);





            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String cnfpwd = confirmPassword.getText().toString().trim();


                if(pwd.length() == 8 || pwd.matches("(.*[0-9].*)")  || pwd.matches("(.*[A-Z]*.)")){



                } else{
                    Toast.makeText(SignUp.this, "password should be 8 characters with uppercase letter and number", Toast.LENGTH_SHORT).show();
                }

                if (user.equals("") || pwd.equals("") || cnfpwd.equals("")  ) {
                    Toast.makeText(SignUp.this, "fill all the fields ", Toast.LENGTH_SHORT).show();


                }

                else {
                    if (pwd.equals(cnfpwd)) {

                        Boolean usercheckresult  =db.checkUsername(user);
                        if (usercheckresult) {
                            Toast.makeText(SignUp.this, "user already exist", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Account account = new Account();
                        account.setUsername(user);
                        account.setPassword(pwd);
                        Boolean regResult = db.insertData(account);
                        if (regResult) {

                            Toast.makeText(SignUp.this, "registration successfull", Toast.LENGTH_SHORT).show();
                            username.setText("");
                            password.setText("");
                            confirmPassword.setText("");


                        } else {

                            Toast.makeText(SignUp.this, "registration not successed", Toast.LENGTH_SHORT).show();


                        }

                    } else {

                        Toast.makeText(SignUp.this, " password does not match", Toast.LENGTH_SHORT).show();


                    }


                }


            }
        });


    }


}

