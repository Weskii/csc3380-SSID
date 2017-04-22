package csc3380.lsu.rmettig.ssid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); //binds the xml file to this class

        //gets content from the elements in the form
        final EditText edtNewUsername = (EditText) findViewById(R.id.edtNewUser);
        final EditText edtVerifyPw = (EditText) findViewById(R.id.edtVerifyPw);
        final EditText edtNewPassword = (EditText) findViewById(R.id.edtPassword);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(RegisterActivity.this, UserAreaActivity.class);
                RegisterActivity.this.startActivity(registerIntent);
            }
        });
    }
}
