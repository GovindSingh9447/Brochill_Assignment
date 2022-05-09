package com.ranawat.brochill_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ranawat.brochill_assignment.databinding.ActivityTweetScreenBinding;

public class TweetScreenActivity extends AppCompatActivity {

    private ActivityTweetScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTweetScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
    }
}