package com.gzeinnumer.validatorvalueexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeinnumer.da.constant.DialogType;
import com.gzeinnumer.da.dialog.infoDialog.InfoDialog;
import com.gzeinnumer.vv.ValidatorValue;
import com.gzeinnumer.vv.ValidatorValueMessage;
import com.gzeinnumer.vv.ValidatorValueResult;

public class MainActivity extends AppCompatActivity {

    private EditText edUsername, edPassword;
    private Button btnValidate;
    private Button btnValidateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        btnValidate = findViewById(R.id.btn_validate_toast);
        btnValidateDialog = findViewById(R.id.btn_validate_dialog);

         sample1();
        sample2();

    }

    private void sample1() {
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = edUsername.getText().toString();
                String strPassword = edPassword.getText().toString();

                ValidatorValue.with(getApplicationContext())
                        .addValue(strUsername, "Minimal 5 Character", 5)
                        .addValue(strPassword, "Minimal 8 Character", 8)
                        .validateListener(new ValidatorValueResult() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void sample2() {
        btnValidateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = edUsername.getText().toString();
                String strPassword = edPassword.getText().toString();

                ValidatorValue.with(getApplicationContext())
                        .addValue(strUsername.length() > 0)
                        .addValue(strPassword.length() > 7, "Minimal 8 Character")
                        .validateListener(new ValidatorValueResult() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        }, new ValidatorValueMessage() {
                            @Override
                            public void onFailed(String msg) {
                                new InfoDialog(getSupportFragmentManager())
                                        .setDialogType(DialogType.DialogError)
                                        .setTitle("INFO!!!")
                                        .setContent(msg).show();
                            }
                        });
            }
        });
    }
}