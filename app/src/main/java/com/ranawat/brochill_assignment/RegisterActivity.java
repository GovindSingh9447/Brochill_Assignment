package com.ranawat.brochill_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.ranawat.brochill_assignment.ModelResponse.RegisterResponse;
import com.ranawat.brochill_assignment.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

    }

    private  String first_name= "",last_name="", email="", password="";
    private void validateData() {


        //get data
        first_name =binding.etFName.getText().toString().trim();
        last_name =binding.etLName.getText().toString().trim();
        email =binding.etEmail.getText().toString().trim();
        password =binding.etPassword.getText().toString().trim();



        //vaildate data
        if(TextUtils.isEmpty(first_name)){
            Toast.makeText(RegisterActivity.this, "Enter First name.....", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(last_name)){
            Toast.makeText(RegisterActivity.this, "Enter last name.....", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(RegisterActivity.this, "Invalid email Pattern.....", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this, "Enter Password....!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Call<RegisterResponse> call=RetrofitClient
                    .getInstance()
                    .getApi()
                    .register(first_name,last_name,email,password);

            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    RegisterResponse registerResponse = response.body();
                    if(response.isSuccessful()){

                        Toast.makeText(RegisterActivity.this , registerResponse.getFirst_name(),Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this , "Its Failed",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}