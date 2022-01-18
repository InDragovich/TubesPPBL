package ppbl.ass3.tubesppbl.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ppbl.ass3.tubesppbl.R;

public class AddActivity extends AppCompatActivity {

    EditText nama_input, harga_input, jumlah_input, deskripsi_input;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#ff5e00"));

        // Set BackgroundDrawable
        ((ActionBar) actionBar).setBackgroundDrawable(colorDrawable);

        nama_input = findViewById(R.id.nama_input);
        harga_input = findViewById(R.id.harga_input);
        jumlah_input = findViewById(R.id.jumlah_input);
        deskripsi_input = findViewById(R.id.deskripsi_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataHelper dh = new DataHelper(AddActivity.this);
                dh.addMenu(nama_input.getText().toString().trim(),
                        Integer.valueOf(harga_input.getText().toString().trim()),
                        Integer.valueOf(jumlah_input.getText().toString().trim()),
                        deskripsi_input.getText().toString().trim());
                finish();
            }

        });
    }
}