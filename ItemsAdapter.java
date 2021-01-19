package com.codepath.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

//provide for displaying data from the model into row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{
    // to connect with the main activity for click
    public interface OnLongClickListener{
        void onItemLongClick(int i );
    }
     List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflator to infliate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent , false);

        //wrap it inside a view Holder and return it
        return new ViewHolder(todoView);

    }
// responsible for binding dat to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        // grab the item from the positin
        String item = items.get(i);
        // bind the item to specific view holder// }
        holder.bind(item);

    }
//tells the rv how many item are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //container to provide easy access to views to represent each roe to the list
    class ViewHolder extends RecyclerView.ViewHolder{
            TextView tvItem;
            
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
    //update a view inside of the view holder with this data
        public void bind(String item) {
        tvItem.setText(item);
        tvItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //notify the listener whihc position
                longClickListener.onItemLongClick(getAdapterPosition());
                return true;
            }
        });



        }
    }


}
