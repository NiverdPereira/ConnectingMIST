package e.par.connectingmist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    CardView login;
    TextView register;
    private EditText mail,pass;
    private FirebaseAuth mAuth;
    private ProgressDialog pdialog;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login= (CardView)findViewById(R.id.cardView);
        register=findViewById(R.id.textreg);
        mail=findViewById(R.id.mailtext);
        pass=findViewById(R.id.pass);
        mAuth=FirebaseAuth.getInstance();
        pdialog=new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(LoginActivity.this,Register.class);
                startActivity(j);
            }
        });
    }
    void signIn(){
        email=mail.getText().toString().trim();
        password=pass.getText().toString().trim();
        pdialog.setMessage("Please Wait");
        pdialog.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user=mAuth.getCurrentUser();
                    Intent i= new Intent(LoginActivity.this,Home.class);
                    startActivity(i);

                }
                else{
                    Toast.makeText(LoginActivity.this,"Invalid Info",Toast.LENGTH_LONG).show();;
                }
                pdialog.dismiss();
            }
        });
    }
}
