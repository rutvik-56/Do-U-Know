package com.example.rutvik_pc.douknow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    DocumentReference documentReference12;
    EditText ph,pa;
    TextView t;
    String phone,passw,checkp;
    ProgressDialog progressDialog58;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        boolean Registered;

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Login.this);
        Registered = sharedPref.getBoolean("Registered", false);
        if (!Registered)
        {
            setContentView(R.layout.activity_login);
        }else {

            Intent i=new Intent(Login.this,search.class);
            startActivity(i);
            finish();
        }

        t=(TextView)findViewById(R.id.textView4);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i123 = new Intent(Login.this,OTP.class);
                startActivity(i123);
            }
        });
        ph=(EditText)findViewById(R.id.edittext1);
        pa=(EditText)findViewById(R.id.edittext2);



    }


    public void logi(View v)
    {
        progressDialog58= ProgressDialog.show(Login.this, "Please Wait...", "Processing...", true);
        phone=ph.getText().toString();
        passw=pa.getText().toString();
        phone="Users/+91" + phone;
        documentReference12= FirebaseFirestore.getInstance().document(phone);

        documentReference12.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists())
                {

                    checkp=documentSnapshot.getString("Password");

                        if (passw.equals(checkp) )
                        {
                            Intent iko=new Intent(Login.this,search.class);
                            final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Login.this);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putBoolean("Registered", true);
                            editor.apply();
                            progressDialog58.dismiss();
                            startActivity(iko);
                            finish();
                    }
                    else
                    {
                        progressDialog58.dismiss();
                        Toast.makeText(Login.this,"Password Not Match",Toast.LENGTH_LONG).show();
                    }

                }
                else
                {

                    progressDialog58.dismiss();
                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();
                }
            }
        });


    }



}
