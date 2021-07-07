package com.myfirst.complexpermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mBtOne;
    private int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtOne = findViewById(R.id.btOne);
        mBtOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(MainActivity.this,permissions,REQUEST_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            Toast.makeText(MainActivity.this,"Camera and Storage permissions granted",Toast.LENGTH_SHORT).show();
        else if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[1]))
                Toast.makeText(MainActivity.this,"Camera granted , Storage denied",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this,"Camera granted , storage denied \n by selecting do not show again",Toast.LENGTH_SHORT).show();
        }
        else if(grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[0]))
                Toast.makeText(MainActivity.this,"Storage granted , camera denied",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this,"Storage granted, camera denied \n by selecting do not show again.",Toast.LENGTH_SHORT).show();
        }
        else if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[0])
                && ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[1]))
            Toast.makeText(MainActivity.this,"Both the permissions denied",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this,"Storage denied by selecting do not show \n" +
                    "again and camera denied by selecting do not show again",Toast.LENGTH_SHORT).show();
    }
}