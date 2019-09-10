package com.example.myprofilebuilder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {
    public static String User_Key = "USER";
    TextView fn_display, gender_display;
    ImageView imageView_display;
    Button editButton;
    private int REQ_CODE = 5;
    String fname;
    String lname;
    String flagGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("My Profile");
        setContentView(R.layout.activity_second);
        fn_display = findViewById(R.id.fn_display);
        gender_display = findViewById(R.id.ln_display);
        imageView_display = findViewById(R.id.imageView_display);
        editButton = (Button) findViewById(R.id.editButton);

        if (getIntent() != null && getIntent().getExtras() != null) {
            final User user = (User) getIntent().getExtras().getSerializable(User_Key);
            display(user);
        }
    }

    private void display(final User user) {
        flagGender = user.GenderFlag;
        fname = user.firstName;
        lname = user.lastName;
        fn_display.setText(fname + " " + lname);
        gender_display.setText(flagGender);

        if (user != null) {
            if (user.GenderFlag.equals("Male")) {
                imageView_display.setImageDrawable(getDrawable(R.drawable.male));
            } else {
                imageView_display.setImageDrawable(getDrawable(R.drawable.female));
            }
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEditIntent = new Intent(DisplayActivity.this, EditActivity.class);
                toEditIntent.putExtra(User_Key, user);
                startActivityForResult(toEditIntent, REQ_CODE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                User user = (User) data.getExtras().getSerializable(EditActivity.User_Key);
                display(user);
            }
        }


    }

}
