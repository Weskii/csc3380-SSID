package csc3380.lsu.rmettig.ssid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnRegister = (Button) findViewById(R.id.btnRegisterNew);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, RegisterActivity.class);
                Login.this.startActivity(registerIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Login.this, UserAreaActivity.class);
                Login.this.startActivity(loginIntent);
            }
        });
    }
}
