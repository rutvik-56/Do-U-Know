package com.example.rutvik_pc.douknow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class search extends AppCompatActivity {
    EditText e;
    Bundle bundle8=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        e=(EditText)findViewById(R.id.editText);
    }


    public void searchmethod(View view)
    {

      String x="+91" + e.getText().toString();
      Intent iolk=new Intent(search.this,result.class);
      bundle8.putString("nomj",x);
       iolk.putExtras(bundle8);
       startActivity(iolk);

    }
}
