package com.example.annoyingalarm;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.annoyingalarm.todo.Task;
import com.example.annoyingalarm.todo.adapter.TodoAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnFocusChangeListener{
    ListView listView;

    ArrayList<Task> taskArrayList;
    TodoAdapter taskArrayAdapter;

    LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        //getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_todo);

        mLayoutInflater = LayoutInflater.from(this);

        listView = (ListView) findViewById(R.id.listView);
        taskArrayList = new ArrayList<Task>();
        taskArrayAdapter = new TodoAdapter(this, taskArrayList);

        listView.setAdapter(taskArrayAdapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todolist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newTaskMenu:
            {
                View view = mLayoutInflater.inflate(R.layout.task_new, null);

                view.findViewById(R.id.txtTitle).setOnFocusChangeListener(this);
                AlertDialog.Builder mBuilder = createAlertDialog(view);
                mBuilder.show();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private AlertDialog.Builder createAlertDialog(final View view) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);

        mBuilder.setView(view);
        mBuilder.setPositiveButton("SAVE", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get the inputs from this and insert it into the DB
                EditText titleTxt = (EditText) view.findViewById(R.id.txtTitle);
                String title = titleTxt.getText().toString().trim();
                EditText descTxt = (EditText) view.findViewById(R.id.txtDescription);
                String description = descTxt.getText().toString();
                DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
                String targetDate = String.format("%02d", datePicker.getDayOfMonth()) +"/" +
                        String.format("%02d", Integer.valueOf(datePicker.getMonth()+1)) + "/" + datePicker.getYear();

                if (title.isEmpty())
                {
                    Toast.makeText(TodoListActivity.this, "Task empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Task addedTask = new Task(1, title,description,targetDate);

                taskArrayList.add(addedTask);
                taskArrayAdapter.notifyDataSetChanged();

                Toast.makeText(TodoListActivity.this, "Title : " + title + "\nDescription : " + description + "\nTargetDate : " + targetDate, Toast.LENGTH_LONG).show();
            }
        });
        mBuilder.setNegativeButton("LOAD", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(TaskActivity.this, "Cancel button clicked", Toast.LENGTH_LONG).show();
            }
        });
        mBuilder.setCancelable(false);
        return mBuilder;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Should be able to edit the data - by launching the dialog

        Task currentTask = (Task)parent.getAdapter().getItem(position);

        View dialogView = mLayoutInflater.inflate(R.layout.task_new, null);

        // set Value for the dialog fields
        EditText txtTitle = (EditText) dialogView.findViewById(R.id.txtTitle);
        EditText txtDescription = (EditText) dialogView.findViewById(R.id.txtDescription);
        DatePicker targetDate = (DatePicker) dialogView.findViewById(R.id.datePicker);

        txtTitle.setOnFocusChangeListener(this);

        txtTitle.setText(currentTask.getTitle());
        txtDescription.setText(currentTask.getTaskDescription());
        String[] dateArray = currentTask.getDate().split("/");
        targetDate.init (Integer.valueOf(dateArray[2]),
                Integer.valueOf(dateArray[1])-1,
                Integer.valueOf(dateArray[0]),
                null);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        return true;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.txtTitle) {
            EditText title = (EditText)v;
            if (title.getText().length() < 1)
            {
                title.setError("Title cant be empty!!");
            }
        }
    }
}
