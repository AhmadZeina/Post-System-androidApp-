package com.example.postsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etPost;
    private Button btnSave, btnEdit, btnDelete;
    private ListView lvPosts;
    private ArrayList<String> posts;
    private ArrayAdapter<String> adapter;
    private int currentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPost = findViewById(R.id.etPost);
        btnSave = findViewById(R.id.btnSave);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        lvPosts = findViewById(R.id.lvPosts);

        posts = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, posts);
        lvPosts.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePost();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPost();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePost();
            }
        });

        lvPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
                etPost.setText(posts.get(position));
            }
        });
    }

    private void savePost() {
        String post = etPost.getText().toString().trim();
        if (!post.isEmpty()) {
            posts.add(post);
            adapter.notifyDataSetChanged();
            etPost.setText("");
            Toast.makeText(this, "Post saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a post", Toast.LENGTH_SHORT).show();
        }
    }

    private void editPost() {
        String post = etPost.getText().toString().trim();
        if (!post.isEmpty() && currentPosition != -1) {
            posts.set(currentPosition, post);
            adapter.notifyDataSetChanged();
            etPost.setText("");
            currentPosition = -1;
            Toast.makeText(this, "Post edited", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select a post to edit", Toast.LENGTH_SHORT).show();
        }
    }

    private void deletePost() {
        if (currentPosition != -1) {
            posts.remove(currentPosition);
            adapter.notifyDataSetChanged();
            etPost.setText("");
            currentPosition = -1;
            Toast.makeText(this, "Post deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select a post to delete", Toast.LENGTH_SHORT).show();
        }
    }
}
