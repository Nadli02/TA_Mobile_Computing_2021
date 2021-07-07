package com.example.ta_mobile_computing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ItemModel> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        data = new ArrayList<>();
        for (int i = 0; i < MyItem.Headline.length; i++) {
            data.add(new ItemModel(
                    MyItem.Headline[i],
                    MyItem.Subhead[i],
                    MyItem.iconList[i]
            ));
        }

        adapter = new RecyclerViewAdapter(data, this );
        recyclerView.setAdapter(adapter);
    }
}