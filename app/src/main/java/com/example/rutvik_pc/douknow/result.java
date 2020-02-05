package com.example.rutvik_pc.douknow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class result extends AppCompatActivity {

    String namez,agez,emailz,genderz,pp=null;
    DocumentReference documentReference889;
    TextView tname,tage,tgender,temail,main;
    ProgressDialog progressDialog82;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
         tname=(TextView)findViewById(R.id.textViewt24);
         tage=(TextView)findViewById(R.id.textView7);
         tgender=(TextView)findViewById(R.id.textView11);
         temail=(TextView)findViewById(R.id.textView10);
          main=(TextView)findViewById(R.id.textView9);
        bundle=getIntent().getExtras();
        String u=bundle.getString("nomj");
        pp="Details Of "+u;
        u="Users/"+u;
        documentReference889= FirebaseFirestore.getInstance().document(u);

        }

    public void fetch(View view)
    {
        progressDialog82= ProgressDialog.show(result.this, "Please Wait...", "Processing...", true);

        documentReference889.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists())
                {
                    namez=documentSnapshot.getString("Name");
                    agez=documentSnapshot.getString("Age");
                    emailz=documentSnapshot.getString("Email");
                    genderz=documentSnapshot.getString("Gender");
                    tname.setText(namez);
                    tage.setText(agez);
                    temail.setText(emailz);
                    tgender.setText(genderz);
                    main.setText(pp);
                    progressDialog82.dismiss();

                }
                else
                {
                    progressDialog82.dismiss();
                    Toast.makeText(result.this,"Failed to load Data",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
