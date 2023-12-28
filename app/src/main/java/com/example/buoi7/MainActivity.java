package com.example.buoi7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtTitle, edtContent, edtDate, edtType;
    Button btnAdd, btnUpdate , btndelete;
    ListView todolistview;
    private ToDoAdapter toDoAdapter;
    private ToDoDAO toDoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todolistview = findViewById(R.id.todolistview);
        toDoDAO = new ToDoDAO(this); // Initialize the instance variable
        ArrayList<ToDo> list = toDoDAO.getListTOdo();
        toDoAdapter = new ToDoAdapter(this, list); // Initialize the adapter
        todolistview.setAdapter(toDoAdapter);
        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        edtDate = findViewById(R.id.edtDate);
        edtType = findViewById(R.id.edtType);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);

        btndelete = findViewById(R.id.btndelete);

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý xóa dữ liệu khi nhấn nút "Delete"
                deleteToDo();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý thêm mới dữ liệu vào CSDL khi nhấn nút "Add"
                addToDo();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý cập nhật dữ liệu vào CSDL khi nhấn nút "Update"
                updateToDo();
            }
        });
    }

    private void deleteToDo() {
        // Lấy thông tin từ các EditText
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();
        String date = edtDate.getText().toString();
        String type = edtType.getText().toString();

        // Tạo một đối tượng ToDo để thực hiện xóa
        ToDo toDoToDelete = new ToDo(0, title, content, date, type, 0);

        // Gọi phương thức xóa trong ToDoDAO
        toDoDAO.deleteToDo(toDoToDelete);

        // Cập nhật lại danh sách trên ListView
        updateListView();
    }

    private void addToDo() {
        // Lấy thông tin từ các EditText
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();
        String date = edtDate.getText().toString();
        String type = edtType.getText().toString();


        ToDoDAO toDoDAO = new ToDoDAO(this);
        ToDo toDo = new ToDo(0, title, content, date, type, 0);
        toDoDAO.addToDo(toDo);

        // Cập nhật lại danh sách trên ListView
        updateListView();
    }

    private void updateToDo() {
        // Lấy thông tin từ các EditText
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();
        String date = edtDate.getText().toString();
        String type = edtType.getText().toString();

        // Cập nhật ToDo trong CSDL
        ToDoDAO toDoDAO = new ToDoDAO(this);
        ToDo toDo = new ToDo(0, title, content, date, type, 0); // Giả sử status mặc định là 0
        toDoDAO.updateToDo(toDo);

        // Cập nhật lại danh sách trên ListView
        updateListView();
    }

    private void updateListView() {
        // Cập nhật lại danh sách trên ListView
        ArrayList<ToDo> list = toDoDAO.getListTOdo();
        toDoAdapter.updateList(list);
        toDoAdapter.notifyDataSetChanged();
    }




}