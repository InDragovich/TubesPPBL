package ppbl.ass3.tubesppbl.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ppbl.ass3.tubesppbl.R;

public class AdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView data_kosong;

    DataHelper dh;
    ArrayList<String> menu_id, menu_nama, menu_harga, menu_jumlah, menu_deskripsi;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

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

        recyclerView =  findViewById(R.id.recyclerView);
        add_button =  findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        data_kosong = findViewById(R.id.data_kosong);
        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(AdminActivity.this, AddActivity.class);
            startActivity(intent);
        });

        dh = new DataHelper(AdminActivity.this);
        menu_id = new ArrayList<>();
        menu_nama = new ArrayList<>();
        menu_harga = new ArrayList<>();
        menu_jumlah = new ArrayList<>();
        menu_deskripsi = new ArrayList<>();

        storedDataInArrays();

        customAdapter = new CustomAdapter(AdminActivity.this,this, menu_id, menu_nama, menu_harga, menu_jumlah, menu_deskripsi);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storedDataInArrays(){
        Cursor cursor = dh.readAllData();
        if (cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            data_kosong.setVisibility(View.VISIBLE);
        }else {
            while (cursor.moveToNext()){
                menu_id.add(cursor.getString(0));
                menu_nama.add(cursor.getString(1));
                menu_harga.add(cursor.getString(2));
                menu_jumlah.add(cursor.getString(3));
                menu_deskripsi.add(cursor.getString(4));

            }
            empty_imageview.setVisibility(View.GONE);
            data_kosong.setVisibility(View.GONE);
//            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ingin hapus semua data?");
        builder.setMessage("Apa anda yakin ingin menghapus semua data?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataHelper dh = new DataHelper(AdminActivity.this);
                dh.deleteAllData();
                Intent intent = new Intent(AdminActivity.this, AdminActivity.class);
                startActivity(intent);
                recreate();
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