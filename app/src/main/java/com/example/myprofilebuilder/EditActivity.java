package com.example.myprofilebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.view.View.GONE;

public class EditActivity extends AppCompatActivity {
    public static String User_Key = "USER1";
    EditText editText_Fn_edit, editText_Ln_edit;
    String flagGender;
    RadioGroup radioGroup_Edit;
    RadioButton rb_male_edit, rb_female_edit;
    ImageView profileImage_edit;
    Button editButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("My Profile");
        editText_Fn_edit = (EditText) findViewById(R.id.editText_Fn_edit);
        editText_Ln_edit = (EditText) findViewById(R.id.editText_Ln_edit);
        rb_female_edit = (RadioButton) findViewById(R.id.rb_female_edit);
        rb_male_edit = (RadioButton) findViewById(R.id.rb_male_edit);
        radioGroup_Edit = findViewById(R.id.radioGroup_edit);
        profileImage_edit = (ImageView) findViewById(R.id.profileImage_edit);
        editButton = (Button) findViewById(R.id.editButton);


        if (getIntent() != null && getIntent().getExtras() != null) {
            final User users = (User) getIntent().getExtras().getSerializable(MainActivity.User_Key);

            editText_Fn_edit.setText(users.firstName);
            editText_Ln_edit.setText(users.lastName);
            if (users.GenderFlag.equals("Male")) {
                profileImage_edit.setImageDrawable(getDrawable(R.drawable.male));
                rb_male_edit.setChecked(true);
            } else {
                profileImage_edit.setImageDrawable(getDrawable(R.drawable.female));
                rb_female_edit.setChecked(true);
            }


            radioGroup_Edit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (radioGroup_Edit.getCheckedRadioButtonId()) {
                        case R.id.rb_female_edit:
                            profileImage_edit.setImageDrawable(getDrawable(R.drawable.female));
                            flagGender = "Female";

                            break;
                        case R.id.rb_male_edit:
                            profileImage_edit.setImageDrawable(getDrawable(R.drawable.male));
                            flagGender = "Male";
                            break;
                        default:
                            break;
                    }

                }
            });

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (editText_Fn_edit.getText().toString().equals("")) {
                        editText_Fn_edit.setError("Hey! Please enter with First Name");
                    } else if (editText_Ln_edit.getText().toString().equals("")) {
                        editText_Ln_edit.setError("Hey! Please enter with First Name");
                    } else if (radioGroup_Edit.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(EditActivity.this, "Please select a gender", Toast.LENGTH_LONG).show();
                    } else {

                        users.firstName = editText_Fn_edit.getText().toString();
                        users.lastName = editText_Ln_edit.getText().toString();
                        if (rb_female_edit.isChecked() == true) {
                            users.GenderFlag = "Female";
                        } else if (rb_male_edit.isChecked() == true) {
                            users.GenderFlag = "Male";
                        }
                        User sentData = new User(users.firstName, users.lastName, users.GenderFlag);
                        Intent finalIntent = new Intent(EditActivity.this, DisplayActivity.class);
                        finalIntent.putExtra(User_Key, sentData);
                        setResult(EditActivity.RESULT_OK, finalIntent);
                        finish();
                    }


                }
            });


        }


    }
}
