package com.example.seekbarapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView porcentagemTv;
    NumberFormat formatadorPercentual;
    TextView taxaTv;
    TextView totalTv;
    EditText valorEd;
    double valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekbar);
        porcentagemTv = findViewById(R.id.percentTextView);
        formatadorPercentual = NumberFormat.getPercentInstance();

        valorEd = findViewById(R.id.valorEd);
        taxaTv = findViewById(R.id.taxaTotalTv);
        totalTv = findViewById(R.id.totalTv);

        sincronizarTextView();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sincronizarTextView();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sincronizarValores();
            }
        });

    }

    public void sincronizarTextView() {
        valor = seekBar.getProgress();
        this.porcentagemTv.setText(formatadorPercentual.format(valor / 100.0));
    }

    private void sincronizarValores() {
        double porc = valor;
        porc = porc / 100.0;
        double valor = valorEd.getText().toString().equals("") ? 0 : Double.parseDouble(valorEd.getText().toString());
        double taxa = porc * valor;
        taxaTv.setText(Double.toString(taxa));
        totalTv.setText(Double.toString(taxa + valor));
    }
}
