package com.example.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.data.entity.DictionaryEntity;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {

    private List<DictionaryEntity> dictionaryEntityList;

    public DictionaryAdapter(List<DictionaryEntity> dictionaryEntityList,AdapterListener listener) {
        this.dictionaryEntityList = dictionaryEntityList;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return dictionaryEntityList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dictionaryEntityList.get(position));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_dictionary, parent, false
        );
        return new ViewHolder(view);
    }


    public void addMoreItem(List<DictionaryEntity> dictionaryEntityList) {
        int previousSize = this.dictionaryEntityList.size();
        this.dictionaryEntityList.addAll(dictionaryEntityList);
        notifyItemRangeInserted(previousSize, dictionaryEntityList.size());
    }

    public void reloadItems(List<DictionaryEntity> dictionaryEntityList) {
        this.dictionaryEntityList.clear();
        this.dictionaryEntityList.addAll(dictionaryEntityList);
        notifyDataSetChanged();
    }
    public void  remove(int position){
        this.dictionaryEntityList.remove(position);
        notifyItemChanged(position);
    }
    public void  update(DictionaryEntity dictionaryEntity,int position){
        this.dictionaryEntityList.set(position,dictionaryEntity);
        notifyItemChanged(position);
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView word, partofspeech, meaning;
        ImageView btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            word = itemView.findViewById(R.id.txt_word);
            partofspeech = itemView.findViewById(R.id.txt_partofspeech);
            meaning = itemView.findViewById(R.id.txt_meaning);
            btnMore = itemView.findViewById(R.id.btnMore);
        }

        void bind( final DictionaryEntity dictionaryEntity) {
            word.setText(dictionaryEntity.word);
            partofspeech.setText(dictionaryEntity.partofspeech);
            meaning.setText(dictionaryEntity.meaning);

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onButtonClickMore(v,dictionaryEntity,getAdapterPosition());
                }
            });
        }
    }
    private AdapterListener listener;

    public interface  AdapterListener{
        void onButtonClickMore(View view,DictionaryEntity dictionaryEntity,int position);
    }
}

