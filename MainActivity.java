package com.codepath.simpletodo;

import android.os.FileUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList items;
    Button btnAdd;
    EditText etItem ;
    RecyclerView rvItems;
    ItemsAdapter itemsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvItems);


        loadItem();

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener() {

            @Override
            public void onItemLongClick(int i) {
        //delete the item from the model
        items.remove(i);
                //notify the adapter
            itemsAdapter.notifyItemRemoved(i);
            Toast.makeText(getApplicationContext(),"item has removed ", Toast.LENGTH_SHORT).show();
            saveItems();
            }
        };
        final ItemsAdapter itemsAdapter = new ItemsAdapter(items,onLongClickListener);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String todoItem = etItem.getText().toString();
            // add an item to model
                items.add(todoItem);
                // notify that we add an item
                itemsAdapter.notifyItemInserted(items.size()-1);
                etItem.setText(" ");
                Toast.makeText(getApplicationContext(),"item has added ", Toast.LENGTH_SHORT).show();
                saveItems();
            }

        } );
    }
    private File getDataFile() {
        return new File(getFilesDir(), "data.txt");

    }
    // this function would load items by reading every line of the data
    private void loadItem() {
        try{
        items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
    } catch (IOException e) {
        Log.e( "melActivity", "error reading this line",e );
        items= new ArrayList<>();
        }
    }
    // this functio would be save items by writing  into the data file
    private void saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), items);
        }catch (IOException e) {
            Log.e( "melActivity", "error Writing this line",e );

        }
    }
}