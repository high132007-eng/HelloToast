package com.example.hellotoast;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hellotoast.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count", 0);
        }

        updateScreen();
    }

    public void showToast(View view) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
    }

    public void showCount(View view) {
        count++;
        updateScreen();
    }

    public void showZero(View view) {
        count = 0;
        updateScreen();
    }

    private void updateScreen() {
        mainBinding.textDisplayCount.setText(String.valueOf(count));

        int countColor;
        if (count % 2 == 0) {
            countColor = R.color.count_even;
        } else {
            countColor = R.color.count_odd;
        }

        mainBinding.buttonCount.setBackgroundTintList(
                ColorStateList.valueOf(ContextCompat.getColor(this, countColor)));

        int zeroColor;
        if (count == 0) {
            zeroColor = R.color.zero_gray;
        } else {
            zeroColor = R.color.zero_active;
        }

        mainBinding.buttonZero.setBackgroundTintList(
                ColorStateList.valueOf(ContextCompat.getColor(this, zeroColor)));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("count", count);
        super.onSaveInstanceState(outState);
    }
}
