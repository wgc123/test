package com.note.mytest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.note.mytest.R;
import com.note.mytest.bean.Fruit;
import com.note.mytest.ui.FruitActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/11/18 16:23
 * @desc :
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitHolder> {

    private List<Fruit> list;
    private Context context;

    public FruitAdapter(Context context,List<Fruit> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FruitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.material_card, parent, false);
        return new FruitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitHolder holder, int position) {
        Fruit fruit = list.get(position);
        holder.fruitName.setText(fruit.name);
        Glide.with(context).load(fruit.imageId).into(holder.fruitImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FruitActivity.class);
                intent.putExtra("fruitName", fruit.name);
                intent.putExtra("fruitImageId", fruit.imageId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FruitHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.fruitImage)
        ImageView fruitImage;
        @BindView(R.id.fruitName)
        TextView fruitName;

        public FruitHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
