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

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton rb_male, rb_female;
    ImageView profileImage;
    EditText editText_Fn, editText_ln;
    Button saveButton;
    public static String User_Key = "USER";
    String flag_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Profile");
        radioGroup = findViewById(R.id.radioGroup_edit);
        rb_male = findViewById(R.id.rb_male_edit);
        rb_female = findViewById(R.id.rb_female_edit);
        profileImage = findViewById(R.id.profileImage_edit);
        saveButton = (Button) findViewById(R.id.editButton);
        editText_Fn = (EditText) findViewById(R.id.editText_Fn_edit);
        editText_ln = (EditText) findViewById(R.id.editText_Ln_edit);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_female_edit:
                        profileImage.setImageDrawable(getDrawable(R.drawable.female));
                        flag_image = "Female";
                        break;

                    case R.id.rb_male_edit:
                        profileImage.setImageDrawable(getDrawable(R.drawable.male));
                        flag_image = "Male";
                        break;

                    default:
                        break;
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (editText_Fn.getText().toString().equals("")) {
                    editText_Fn.setError("Hey! Please enter with First Name");
                } else if (editText_ln.getText().toString().equals("")) {
                    editText_ln.setError("Hey! Please enter with First Name");
                } else if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Please select a gender", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    User user = new User(editText_Fn.getText().toString(), editText_ln.getText().toString(), flag_image);
                    intent.putExtra(User_Key, user);
                    startActivity(intent);
                    finish();
                }
            }

        });

    }
}
