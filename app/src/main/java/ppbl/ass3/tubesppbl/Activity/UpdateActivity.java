package ppbl.ass3.tubesppbl.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ppbl.ass3.tubesppbl.R;

public class UpdateActivity extends AppCompatActivity {

    EditText id_input, nama_input, harga_input, jumlah_input, deskripsi_input;
    Button update_button, delete_button;
    String id, nama, harga, jumlah, deskripsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

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

        id_input = findViewById(R.id.id_input);
        nama_input = findViewById(R.id.nama_input2);
        harga_input = findViewById(R.id.harga_input2);
        jumlah_input = findViewById(R.id.jumlah_input2);
        deskripsi_input = findViewById(R.id.deskripsi_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nama);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataHelper dh = new DataHelper(UpdateActivity.this);
                dh.updateData(id, nama, harga, jumlah, deskripsi);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nama") &&
                getIntent().hasExtra("harga") && getIntent().hasExtra("jumlah") &&
                getIntent().hasExtra("deskripsi")){
            //getting datA form intent
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama");
            harga = getIntent().getStringExtra("harga");
            jumlah = getIntent().getStringExtra("jumlah");
            deskripsi = getIntent().getStringExtra("deskripsi");

            //setting intent data
            nama_input.setText(nama);
            harga_input.setText(harga);
            jumlah_input.setText(jumlah);
            deskripsi_input.setText(deskripsi);

        }else{
            Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ingin hapus " + nama + " ?");
        builder.setMessage("Apa anda yakin ingin menghapus " + nama + " ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataHelper dh = new DataHelper(UpdateActivity.this);
                dh.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}