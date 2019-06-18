package com.example.hungdang.smsreciver;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdapterRCSMS extends RecyclerView.Adapter<AdapterRCSMS.ViewHolder> {

    LayoutInflater layoutInflater;
    View contactView;
    Animation animation;

    private List<ContactsSMS> mContacts;

    public AdapterRCSMS (List<ContactsSMS> mContacts){
        this.mContacts = mContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        layoutInflater = LayoutInflater.from(context);
        contactView = layoutInflater.inflate(R.layout.rc_sms,viewGroup,false);

        ViewHolder viewHolder =new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ContactsSMS contactsSMS = mContacts.get(i);

        viewHolder.noidung.setText(contactsSMS.getNoiDung().toString());
        viewHolder.tinnhan.setText(contactsSMS.getTinNhan().toString());

//        Log.d("3213213123zxcxzc123",contactsSMS.getNoiDung()+contactsSMS.getTinNhan()+"");
        int position = i;
        int lastPosition = -1;


         animation = AnimationUtils.loadAnimation(viewHolder.itemView.getContext(),
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        viewHolder.itemView.startAnimation(animation);

        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView noidung,tinnhan;
//        Context context;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            noidung = itemView.findViewById(R.id.noidung);
            tinnhan = itemView.findViewById(R.id.tinnhan);

            noidung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), IteamRC.class);
                    i.putExtra("noidung",noidung.getText().toString());
                    i.putExtra("tinnhan",tinnhan.getText().toString());
                    v.getContext().startActivity(i);
//                    Toast.makeText(itemView.getContext(), "dsadasdasdasd", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}
