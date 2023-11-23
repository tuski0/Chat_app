package net.flow9.dcjt.chat_app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    Button login_btn;
    Button register_btn;
    EditText etId;
    EditText etPw;
    String password, email;
    private FirebaseAuth mfirebaseAuth;
    private DatabaseReference mDatabaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mfirebaseAuth = FirebaseAuth.getInstance();

        etId = findViewById(R.id.etid);
        etPw = findViewById(R.id.etpw);
        register_btn = findViewById(R.id.register_btn);


        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = etId.getText().toString();
                String strPassword = etPw.getText().toString();

                mfirebaseAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}