package com.pingxun.library.guomei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pingxun.library.guomeilibrary.meijie.DemoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPanel = (Button) findViewById(R.id.buttonPanel);
        buttonPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DemoActivity.class).putExtra("url", "https://static-sit.gomemyf.com/traffic-access/#/testTool?fromChannel=miaobd&fromChannelId=miaobd-a"));
//                startActivity(new Intent(MainActivity.this, DemoActivity.class).putExtra("url","https://static-sit.gomemyf.com/flow-h5/#/index?fromChannel=miaobd&fromChannelId=miaobd1"));
            }
        });
    }
}
