package com.example.sahip.realtimechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity {


    private Socket mSocket;
    EditText editText;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.txt);
        button = (Button) findViewById(R.id.btn);

        try

        {
            mSocket = IO.socket("http://192.168.1.20:3000");
        } catch (URISyntaxException e)

        {
            throw new RuntimeException(e);

        }
        mSocket.connect();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSocket.emit("new message", editText.getText().toString());
            }
        });
        mSocket.emit("new message", "hello there, I am android client:)");


    }


}
