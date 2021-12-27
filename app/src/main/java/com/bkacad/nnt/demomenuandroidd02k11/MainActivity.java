package com.bkacad.nnt.demomenuandroidd02k11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvTodo;
    private List<String> data;
    private ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTodo = findViewById(R.id.lv_main_todo);
        data = new ArrayList<>();
        data.add("1. Đi chợ");
        data.add("2. Học bài");
        data.add("3. Giải trí");
        myAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        lvTodo.setAdapter(myAdapter);

        registerForContextMenu(lvTodo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Link đến layout menu
        getMenuInflater().inflate(R.menu.main_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // Xử lý các sự kiện khi click vào option menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_option_menu_settings:
                Toast.makeText(MainActivity.this, "Vào cài đặt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_option_menu_abouts:
                startActivity(new Intent(MainActivity.this, AboutsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.main_context_menu_details:
                // Hiển thị chi tiết
                Toast.makeText(MainActivity.this, data.get(contextMenuInfo.position), Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_context_menu_edit:
                // Hiển thị 1 dialog - Tạo dialog
                break;
            case R.id.main_context_menu_delete:
                // Xoá -> render listview
                data.remove(contextMenuInfo.position);
                myAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
}