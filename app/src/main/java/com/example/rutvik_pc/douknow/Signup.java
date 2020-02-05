package com.example.rutvik_pc.douknow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {


    Bundle bundle=new Bundle();
    String pnpn=null;
    String path;
    private EditText name, email, age, password;
    DocumentReference documentReference;
    ProgressDialog progressDialog5;
    public static final String na="Name";
    public static final String pass="Password";
    public static final String ag="Age";
    public static final String em="Email";
    public static final String ge="Gender";
    boolean p= Boolean.parseBoolean(null);
    String name1, email1, password1, age1,gender1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bundle= getIntent().getExtras();
        pnpn= bundle.getString("number");
        path="Users/"+pnpn;
        documentReference= FirebaseFirestore.getInstance().document(path);
        name = (EditText) findViewById(R.id.editText115);
        email = (EditText) findViewById(R.id.editText137);
        age = (EditText) findViewById(R.id.editText42);
        password = (EditText) findViewById(R.id.editText1186);


    }


    public void onRadioButtonClicked(View view5) {

        boolean checked = ((RadioButton) view5).isChecked();
        RadioButton button = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton button1 = (RadioButton) findViewById(R.id.radioButton);


        switch (view5.getId()) {
            case R.id.radioButton:
                if (checked) {
                    p=true;
                    button.setChecked(false);
                }
                break;
            case R.id.radioButton2:
                if (checked) {
                    p=false;
                    button1.setChecked(false);
                }
                break;
        }

    }



    public  void registerheremethod123(View vbn)
    {


        progressDialog5 = ProgressDialog.show(Signup.this, "Please Wait...", "Processing...", true);
        password1 = password.getText().toString();
        age1 = age.getText().toString();
        email1 = email.getText().toString();
        name1 = name.getText().toString();

        if(p==true)
        {
            gender1="Male";
        }
        else
        {
            gender1="Female";
        }

        Map<String, Object> datas=new HashMap<String, Object>();
        datas.put(na,name1);
        datas.put(pass,password1);
        datas.put(ag,age1);
        datas.put(em,email1);
        datas.put(ge,gender1);



        documentReference.set(datas).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog5.dismiss();
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                Intent iop=new Intent(Signup.this,Login.class);
                startActivity(iop);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {

                progressDialog5.dismiss();
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();

            }
        });


    }

}
