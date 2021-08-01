package com.example.atry.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atry.MessageModel;
import com.example.atry.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class chatAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModel> messageModels;
    Context context;
    String recId;

    public chatAdapter(ArrayList<MessageModel> messageModels, Context context, String recId) {
        this.messageModels = messageModels;
        this.context = context;
        this.recId = recId;
    }

    int SENDER_VIEW_TYPE=1;
    int RECIEVER_VIEW_TYPE=2;

    public chatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(messageModels.get(position).getUid().equals(FirebaseAuth.getInstance().getUid()))
        return SENDER_VIEW_TYPE;
        else
            return RECIEVER_VIEW_TYPE;

    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == SENDER_VIEW_TYPE){

            View  view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return  new SenderViewHolder(view);
        }
        else{

            View   view = LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);
            return  new RecieverViewHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {


        MessageModel messagemodel =messageModels.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context).setTitle("Delete")
                        .setMessage("Are you sure you want to delete message")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                FirebaseDatabase database=FirebaseDatabase.getInstance();
                                String senderRoom=FirebaseAuth.getInstance().getUid()+recId;
                                database.getReference().child("chats").child(senderRoom)
                                        .child(messagemodel.getMessageId()).setValue(null);


                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

                return false;
            }
        });

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                new AlertDialog.Builder(context).setTitle("Delete")
//                        .setMessage("Are you sure you want to delete message")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        FirebaseDatabase database=FirebaseDatabase.getInstance();
//
//                        String senderRoom=FirebaseAuth.getInstance().getUid()+recId;
//                        database.getReference().child("chat").child(senderRoom)
//                                .child(messagemodel.getMessageId()).setValue(null);
//
//
//                    }
//                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).show();
//
//
//                return false;
//            }
//        });

        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMsg.setText(messagemodel.getMessage());

        }

        else
            ((RecieverViewHolder) holder).recieverMsg.setText(messagemodel.getMessage());



    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder {

        TextView recieverMsg, recieverTime;

        public RecieverViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            recieverMsg = itemView.findViewById(R.id.recieve_text);
            recieverTime = itemView.findViewById(R.id.recieve_time);

        }

    }

        public class SenderViewHolder extends RecyclerView.ViewHolder {

            TextView  senderMsg,  senderTime;

            public SenderViewHolder(@NonNull @NotNull View itemView) {
                super(itemView);
                senderMsg = itemView.findViewById(R.id.send_text);
                senderTime = itemView.findViewById(R.id.sending_time);

            }


        }



}



