package ppbl.ass3.tubesppbl.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ppbl.ass3.tubesppbl.R;

public class CustomAdapterCust extends RecyclerView.Adapter<CustomAdapterCust.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList menu_id, menu_nama, menu_harga, menu_jumlah, menu_deskripsi;
    int position;
    Animation translate_anim;

    CustomAdapterCust(Activity activity, Context context, ArrayList menu_id, ArrayList menu_nama, ArrayList menu_harga, ArrayList menu_jumlah, ArrayList menu_deskripsi){
    this.activity = activity;
    this.context = context;
    this.menu_id = menu_id;
    this.menu_nama = menu_nama;
    this.menu_harga = menu_harga;
    this.menu_jumlah = menu_jumlah;
    this.menu_deskripsi = menu_deskripsi;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_cust, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;
        holder.id_txt.setText(String.valueOf(menu_id.get(position)));
        holder.menu_nama_txt.setText(String.valueOf(menu_nama.get(position)));
        holder.menu_harga_txt.setText(String.valueOf(menu_harga.get(position)));
        holder.menu_jumlah_txt.setText(String.valueOf(menu_jumlah.get(position)));
        holder.menu_deskripsi_txt.setText(String.valueOf(menu_deskripsi.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id",String.valueOf(menu_id.get(position)));
                intent.putExtra("nama",String.valueOf(menu_nama.get(position)));
                intent.putExtra("harga",String.valueOf(menu_harga.get(position)));
                intent.putExtra("jumlah",String.valueOf(menu_jumlah.get(position)));
                intent.putExtra("deskripsi",String.valueOf(menu_deskripsi.get(position)));
                activity.startActivityForResult(intent, 1);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_txt, menu_nama_txt, menu_harga_txt, menu_jumlah_txt, menu_deskripsi_txt;
        LinearLayout  mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            menu_nama_txt = itemView.findViewById(R.id.menu_nama_txt);
            menu_harga_txt = itemView.findViewById(R.id.menu_harga_txt);
            menu_jumlah_txt = itemView.findViewById(R.id.menu_jumlah_txt);
            menu_deskripsi_txt = itemView.findViewById(R.id.menu_deskripsi_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
