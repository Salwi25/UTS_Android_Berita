package com.example.uts_login;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class Details extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText tanggalLahir;
    Spinner spinner;
    Button btnSelanjutnya;
    String mSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tanggalLahir = findViewById(R.id.tanggal_lahir);
        spinner = findViewById(R.id.label_spinner);
        btnSelanjutnya = findViewById(R.id.selanjutnya_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        tanggalLahir.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    tanggalLahir.setEnabled(false);
                    DialogFragment dateFragment = new DatePickerFragment();
                    dateFragment.show(getSupportFragmentManager(), "date-picker");
                } else {
                    tanggalLahir.setEnabled(true);
                }
            }
        });

        btnSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kategori = String.valueOf(spinner.getSelectedItem());
                String tanggal_lahir = String.valueOf(tanggalLahir.getText());
                Intent intent = new Intent(Details.this, ContentRV.class);
                intent.putExtra("basedKategori", kategori);
                intent.putExtra("basedTanggalLahir", tanggal_lahir);
                startActivity(intent);
            }
        });
    }

        public void processDatePickerResult(int day, int month, int year) {
            String day_string = Integer.toString(day);
            String month_string = Integer.toString(month+1);
            String year_string = Integer.toString(year);

            String dateMessage = day_string + "-" + month_string + "-" + year_string;
            tanggalLahir.setText(dateMessage);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSpinner = adapterView.getItemAtPosition(i).toString();
        showSpinnerText();
    }

    private void showSpinnerText() {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}