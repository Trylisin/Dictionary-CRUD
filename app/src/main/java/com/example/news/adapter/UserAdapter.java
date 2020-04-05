package com.example.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.data.entity.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users;

    public UserAdapter(List<User> users, AdapterListener listener) {
        this.users = users;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_user, parent, false
        );
        return new ViewHolder(view);
    }


    public void addMoreItem(List<User> users) {
        int previousSize = this.users.size();
        this.users.addAll(users);
        notifyItemRangeInserted(previousSize, users.size());
    }

    public void reloadItems(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        this.users.remove(position);
        notifyItemRemoved(position);
    }

    public void update(User user, int position) {
        this.users.set(position, user);
        notifyItemChanged(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username, email, password,dateCreate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.txt_username);
            email = itemView.findViewById(R.id.txt_email);
            password = itemView.findViewById(R.id.txt_password);
            dateCreate = itemView.findViewById(R.id.txt_dateCreated);
        }

        void bind(final User user) {
            username.setText(user.username);
            email.setText(user.email);
            dateCreate.setText(user.dateCreated);

        }
    }


    private AdapterListener listener;

    public interface AdapterListener {
        void onButtonMoreClicked(View view, User user, int position);
    }

}
