package ppbl.ass3.tubesppbl.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ppbl.ass3.tubesppbl.R;

public class DetailActivity extends AppCompatActivity {

    TextView addToCardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String judul ="Pesanan Masuk";
        String pesan ="Pesanan dari pelanggan diterima, silahkan diproses";
        String appName ="Notifikasi";
        Notif notif = new Notif();
        notif.sendNotif(judul,pesan,appName,this
        );

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

        addToCardBtn = findViewById(R.id.addToCardBtn);
        addToCardBtn.setOnClickListener(new View.OnClickListener() {

//            Context context;

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
//              Toast.makeText(context, "Pesananmu sudah ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
            }

        });


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//    }
    }
}