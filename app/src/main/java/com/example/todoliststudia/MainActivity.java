package com.example.todoliststudia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button button;
    private View snackBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.addTask:
                Toast.makeText(this, "Dodawanie znajduje się u dołu ekranu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.deleteTask:
                Toast.makeText(this, "Aby usunąć zadanie, kliknij i przytrzymaj zadanie które chcesz usunąć", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);
        snackBar = findViewById(R.id.snackBar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        items = new ArrayList<>();
        itemsAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(itemsAdapter);
        itemsAdapter.add("Praca");
        itemsAdapter.add("Sniadanie");
        itemsAdapter.add("Studia");
        itemsAdapter.add("Wow prepatch");


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context,"Zadanie usuniete",Toast.LENGTH_SHORT).show();
                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                if(pos == 3){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=uvsPzuriDdA"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.google.android.youtube");
                    startActivity(intent);
                }
            }
        });

    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.editText2);
        input.setRawInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        String itemtext=input.getText().toString();

        if(!(itemtext.equals(""))){
            itemsAdapter.add(itemtext);
            Snackbar mySnackbar = Snackbar.make(snackBar, "Zadanie dodano pomyslnie", Snackbar.LENGTH_SHORT);
            mySnackbar.show();
            input.setText("");
        }else{
            Toast.makeText(getApplicationContext(),"Nie mozna dodac pustego zadania",Toast.LENGTH_LONG).show();
        }
    }

}