package com.example.uts_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText username, passwordEdit;
    private Button masuk;
    private AlertDialog alertDialog;
    private String user_name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        passwordEdit = findViewById(R.id.password);
        masuk = findViewById(R.id.masuk_btn);

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name = username.getText().toString();
                password = passwordEdit.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Login Successfully...");
                builder.setTitle("Login");
                builder.setCancelable(false);

                if (user_name.toString().equals("pakjoko") && password.toString().equals("yangpentingcuan")) {
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(view.getContext(), Details.class);
                            startActivity(intent);
                            dialogInterface.cancel();
                        }
                    });
                } else {
                    builder.setMessage("Login Failed!");
                    builder.setTitle("Login");
                    builder.setCancelable(false);

                    builder.setPositiveButton("try again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                }
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}