package com.example.rutvik_pc.douknow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {

    Button btnGenerateOTP, btnSignIn;
    String phoneNumber, otp;
    EditText etPhoneNumber, etOTP;
    FirebaseAuth auth;
    private String verificationCode;
    ProgressDialog progressDialog;
    ProgressDialog progressDialog1;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        StartFirebaseLogin();
        btnGenerateOTP = (Button) findViewById(R.id.button3);
        btnSignIn = (Button) findViewById(R.id.button4);
        etOTP = (EditText) findViewById(R.id.editTexttt3);
        etPhoneNumber = (EditText) findViewById(R.id.editTextt2);



        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber="+91"+etPhoneNumber.getText().toString();
                progressDialog = ProgressDialog.show(OTP.this, "Please Wait...", "Processing...", true);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,                     // Phone number to verify
                        60,                           // Timeout duration
                        TimeUnit.SECONDS,                // Unit of timeout
                        OTP.this,        // Activity (for callback binding)
                        mCallback);                      // OnVerificationStateChangedCallbacks
            }
        });



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog1 = ProgressDialog.show(OTP.this, "Please Wait...", "Processing...", true);
                otp=etOTP.getText().toString();

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);

                SigninWithPhone(credential);
            }
        });



    }


    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(OTP.this,"correct OTP",Toast.LENGTH_LONG).show();
                            progressDialog1.dismiss();
                            Intent iuyo=new Intent(OTP.this,Signup.class);
                            bundle.putString("number",phoneNumber);
                            iuyo.putExtras(bundle);
                            startActivity(iuyo);
                        } else {
                            progressDialog1.dismiss();

                            Toast.makeText(OTP.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void StartFirebaseLogin() {
        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                progressDialog.dismiss();
                Toast.makeText(OTP.this, "verification completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                progressDialog.dismiss();
                Toast.makeText(OTP.this, "verification failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                progressDialog.dismiss();
                verificationCode = s;
                Toast.makeText(OTP.this, "Code sent", Toast.LENGTH_SHORT).show();
            }
        };

    }
}
