package com.example.splitpay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private retrofitinterface intface;
    private String BaseUrl="https://10.0.2.16:3000";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit=new Retrofit.Builder().baseUrl(BaseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                                .build();
        intface=retrofit.create(retrofitinterface.class);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleloginDialog();
            }
        });

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignupDialog();
            }
        });
    }

    private void handleSignupDialog() {

        View view =getLayoutInflater().inflate(R.layout.signinlayout,null);
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setView(view) .show();
Button signupbtn=view.findViewById(R.id.signup);
EditText name=view.findViewById(R.id.editName);
EditText email=view.findViewById(R.id.email);
EditText password=view.findViewById(R.id.password);
signupbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        HashMap<String,String>map =new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("email",email.getText().toString());
        map.put("password",password.getText().toString());
        Call<Void> call =intface.executeSignup(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
if (response.code()==200){
    Toast.makeText(MainActivity.this, "signedup successfully", Toast.LENGTH_SHORT).show();
}
else if(response.code()==400){
    Toast.makeText(MainActivity.this, "already registered", Toast.LENGTH_SHORT).show();

}
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
});

    }

    private void handleloginDialog() {


        View view=getLayoutInflater().inflate(R.layout.loginlayout,null);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setView(view).show();
        Button loginButton=view.findViewById(R.id.login);
        EditText emailEdit=view.findViewById(R.id.emailEdit);
        EditText password=view.findViewById(R.id.editpassword);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> map =new HashMap<String, String>();
                map.put("email",emailEdit.getText().toString());
                map.put("password",password.getText().toString());
                Call<LoginResult> call =intface.executeLogin(map);
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
if(response.code()==200){
    LoginResult result = response.body();
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    builder.setTitle(result.getName());
    builder.setMessage(result.getEmail());

    builder.show();


}else if( response.code()==404){
    Toast.makeText(MainActivity.this, "wrong credentials", Toast.LENGTH_SHORT).show();
}



                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

Intent intent =new Intent(MainActivity.this,page.class);
startActivity(intent);
    }
}