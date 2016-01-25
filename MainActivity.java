package com.example.valerie.todolist;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ArrayList<String> listItems;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listItems = new ArrayList<String>();
        final ListView todoList = (ListView) findViewById(R.id.todoList);

        myadapter = new ArrayAdapter<String>(
                this,
                R.layout.checkboxlist,
                R.id.list_item_text,
                listItems);


    }

    public void addItem(View view) {
        ListView todoList = (ListView) findViewById(R.id.todoList);
        EditText myEditText = (EditText) findViewById(R.id.newTodo);
        String text = myEditText.getText().toString();
        listItems.add(0, text);
        myEditText.setText("");
        myadapter.notifyDataSetChanged();
        todoList.setAdapter(myadapter);
    }



    public void checkItem(View view) {
        CheckBox clicked = (CheckBox) view;

        TextView listItemText = (TextView)findViewById(R.id.list_item_text);
        Log.d("checkItem:", "listItemText: " + listItemText);
        String text = (String) listItemText.getText();
        Log.d("checkItem: ", "text: " + text);

        if(clicked.isChecked()){
            listItemText.setTextColor(getResources().getColor(R.color.colorOffset));


        }else{

            for(int i = 0; i < listItems.size(); i++){
                if(listItems.get(i).equals(text)){
                    listItems.remove(i);
                    break;
                }
            }
            listItems.add(0, text);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("listItems", listItems);

    }

    @Override
    public void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);
        listItems = inState.getStringArrayList("listItems");
        myadapter = new ArrayAdapter<String>(
                this,
                R.layout.checkboxlist,
                R.id.list_item_text,
                listItems);


    }
}

