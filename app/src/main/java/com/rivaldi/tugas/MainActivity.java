package com.rivaldi.tugas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView keterangan;
    EditText namaPegawai, tampilGajiPokok, tampilJumlahAnak, tampilTunjangan;
    RadioButton operator, pengawas, manager;
    Button btnHitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        keterangan = findViewById(R.id.keterangan);
        namaPegawai = findViewById(R.id.inputNama);
        tampilGajiPokok = findViewById(R.id.inputGajiPokok);
        tampilJumlahAnak = findViewById(R.id.inputJumlahAnak);
        tampilTunjangan = findViewById(R.id.inputTunjangan);
        operator = findViewById(R.id.buttonOperator);
        pengawas = findViewById(R.id.buttonPengawas);
        manager = findViewById(R.id.buttonManager);
        btnHitung = findViewById(R.id.btnHitung);
        tampilGajiPokok.setEnabled(false);
        tampilTunjangan.setEnabled(false);

        operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilGajiPokok.setText("Rp. 750.000");
            }
        });

        pengawas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilGajiPokok.setText("Rp. 1.000.000");
            }
        });

        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilGajiPokok.setText("Rp. 5.000.000");
            }
        });

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan jumlah anak dari input
                int jumlahAnak = Integer.parseInt(tampilJumlahAnak.getText().toString());

                // Mendapatkan gaji pokok dari input
                double gajiPokok = Double.parseDouble(tampilGajiPokok.getText().toString().replaceAll("[^0-9]", "")); // Menghilangkan "Rp." dan spasi

                // Mendapatkan tunjangan
                double tunjangan = hitungTunjangan(jumlahAnak, gajiPokok);

                // Total gaji
                double totalGaji = gajiPokok + tunjangan;

                // Menampilkan tunjangan
                tampilTunjangan.setText(String.format("Rp. %.2f", tunjangan));

                // Menampilkan keterangan
                keterangan.setText("Total Gaji " + namaPegawai.getText().toString() + " adalah Rp. " + totalGaji);
            }
        });

    }

    private double hitungTunjangan(int jumlahAnak, double gajiPokok) {
        // Maksimal hanya 3 anak
        jumlahAnak = Math.min(jumlahAnak, 3);

        // Menghitung tunjangan per anak (10% dari gaji pokok)
        double tunjanganPerAnak = 0.1 * gajiPokok;

        // Total tunjangan
        double totalTunjangan = jumlahAnak * tunjanganPerAnak;

        return totalTunjangan;
    }
}