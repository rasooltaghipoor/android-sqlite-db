package com.barana.location.mydatabase1;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    MyDBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myDB = new MyDBHelper(this);
    }

    public void backActivity(View v){
        /*Intent in = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(in);*/
        finish();
    }

    public void updateBtnClick(View v){
        EditText idTxt = (EditText)findViewById(R.id.updateIDTxt);
        EditText nameTxt = (EditText)findViewById(R.id.updateNameTxt);
        boolean res = myDB.updateDB(idTxt.getText().toString(),nameTxt.getText().toString());

        if(res)
            Toast.makeText(this,"Update Successfully",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Error Updating Data",Toast.LENGTH_LONG).show();
    }

    public void onShowClick(View v){
        Cursor result = myDB.showAllData();
        if(result.getCount() <= 0) {
            Toast.makeText(Main2Activity.this , "No data exists" , Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer data = new StringBuffer();

        while(result.moveToNext()){
            data.append("ID: "+result.getString(0)+"\n");
            data.append("Name: "+result.getString(1)+"\n");
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle("All Data");
        alert.setMessage(data.toString());
        alert.show();
    }
}
