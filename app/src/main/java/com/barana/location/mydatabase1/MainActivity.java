package com.barana.location.mydatabase1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new MyDBHelper(this);
    }

    public void saveClick(View v){
        EditText idTxt = (EditText)findViewById(R.id.idTxt);
        EditText nameTxt = (EditText)findViewById(R.id.nameTxt);
        boolean res = myDB.insertToDB(idTxt.getText().toString(),nameTxt.getText().toString());

        if(res)
            Toast.makeText(this,"Done Successfully",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Error Inesrting Data",Toast.LENGTH_LONG).show();
    }

    public void onDelete(View v){
        EditText idTxt = (EditText)findViewById(R.id.delIDTxt);
        boolean res = myDB.deleteDB(idTxt.getText().toString());
        if(res)
            Toast.makeText(this,"Delete Successfully",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Error Deleting Data",Toast.LENGTH_LONG).show();
    }

    public void openActivity(View v){
        Intent in = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(in);
    }
}
