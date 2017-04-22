package csc3380.lsu.rmettig.ssid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {

    Shark newShark = null;
    String sharkType = "";
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); //binds the xml file to this class

        //gets content from the elements in the form
        final EditText edtNewUsername = (EditText) findViewById(R.id.edtNewUser);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        final RadioButton rbtGreatWhite = (RadioButton) findViewById(R.id.rbtGreatWhite);
        final RadioButton rbtHammerHead = (RadioButton) findViewById(R.id.rbtHammerHead);
        final RadioButton rbtWhale = (RadioButton) findViewById(R.id.rbtWhale);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(RegisterActivity.this, UserAreaActivity.class);

                if(rbtGreatWhite.isChecked())
                    sharkType = "greatwhite";
                if(rbtHammerHead.isChecked())
                    sharkType = "hammerhead";
                if(rbtWhale.isChecked())
                    sharkType = "whale";

                if (!sharkType.equals("")&&!username.equals("")) {
                    newShark = new Shark(sharkType);
                    //RegisterActivity.this.startActivity(registerIntent);
                }
                else {
                    new AlertDialog.Builder(RegisterActivity.this).setMessage("You must select an egg!!");
                }
            }
        });

        rbtGreatWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtHammerHead.setChecked(false);
                rbtWhale.setChecked(false);
            }
        });

        rbtHammerHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtGreatWhite.setChecked(false);
                rbtWhale.setChecked(false);
            }
        });

        rbtWhale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtGreatWhite.setChecked(false);
                rbtHammerHead.setChecked(false);
            }
        });
    }
}
