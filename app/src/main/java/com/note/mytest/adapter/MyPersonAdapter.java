package com.note.mytest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.note.mytest.App;
import com.note.mytest.R;
import com.note.mytest.bean.PersonBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/28 10:32
 * @desc : 测试drawable layer-list单边边框效果
 */
public class MyPersonAdapter extends RecyclerView.Adapter<MyPersonAdapter.MyHolder> {

    private List<PersonBean> list;

    public MyPersonAdapter(List<PersonBean> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=  LayoutInflater.from(App.getContext()).inflate(R.layout.adapter_person, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        PersonBean bean = list.get(position);
        holder.name.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_person_name)
        TextView name;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
