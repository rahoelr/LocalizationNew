package com.example.localetextstarter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText etHarga;
    TextView tvHasil;
    Button btnHasil;

    int hasil = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHelp();
            }
        });

        Date myDate = new Date();
        long expirationDate = myDate.getTime() + TimeUnit.DAYS.toMillis(3);
        myDate.setTime(expirationDate);

        String formatDate = DateFormat.getDateInstance().format(myDate);
        TextView expiredTextView = findViewById(R.id.date);
        expiredTextView.setText(formatDate);

        etHarga = findViewById(R.id.etHarga);
        tvHasil = findViewById(R.id.tvHasil);
        btnHasil = findViewById(R.id.btnHitung);

        btnHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    String value1 = etHarga.getText().toString();
                    hasil = (int) Integer.parseInt(value1);

                } catch(NumberFormatException e) {
                    hasil = (int) 0;
                }

                int hasil2 = hasil * 100;

                NumberFormat format = NumberFormat.getCurrencyInstance();
                format.setMaximumFractionDigits(0);

                format.setCurrency(Currency.getInstance(Locale.getDefault()));

                String hasil3 = format.format(hasil2);
                tvHasil.setText(hasil3);

            }
        });
    }
    private void showHelp() {
        Intent helpIntent = new Intent(this, HelpActivity.class);
        startActivity(helpIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_language){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}