package ppbl.ass3.tubesppbl.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ppbl.ass3.tubesppbl.MainActivity;
import ppbl.ass3.tubesppbl.R;

public class IntroActivity extends AppCompatActivity {
    private ConstraintLayout buttonplg;
    private ConstraintLayout buttonadm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        buttonplg=findViewById(R.id.buttonplg);
        buttonadm=findViewById(R.id.buttonadm);
        buttonplg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(IntroActivity.this, MainActivity.class)));
//                startActivity(new Intent(IntroActivity.this,MainActivity.class));
            }
        });

        buttonadm.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(IntroActivity.this, AdminActivity.class)));
            }
        }));
    }
}