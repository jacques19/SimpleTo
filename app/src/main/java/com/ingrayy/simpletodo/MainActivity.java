package com.ingrayy.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> todoItems;
    ArrayAdapter<String> aTodoAdapter;
    ListView LvItems;
    EditText etEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popuLateArrayItems();
        LvItems = (ListView) findViewById(R.id.lvItems);
        LvItems.setAdapter(aTodoAdapter);
        etEditText = (EditText)findViewById(R.id.etEditText);
        LvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView <?> parent, View view, int position, long id) {
                todoItems.remove(position);
                aTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
        }
        public void popuLateArrayItems(){
        todoItems = new ArrayList <String>();
        readItems();
            aTodoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
        }

        private void readItems(){
        File fiLeDir= getFilesDir();
        File fiLe = new File(fiLeDir,"todo.txt");
        try{
            todoItems = new ArrayList <String>(FileUtils.readLines(fiLe));
        }catch ( IOException e){};
    }

            private void writeItems(){
            File fiLeDir= getFilesDir();
            File fiLe = new File(fiLeDir,"todo.txt");
            try{
             FileUtils.writeLines(fiLe,todoItems);
            }catch ( IOException e){
            }
        };



    public void onAddItem(View view) {
        aTodoAdapter.add(etEditText.getText().toString());
        etEditText.setText("");
        writeItems();
    }
}
