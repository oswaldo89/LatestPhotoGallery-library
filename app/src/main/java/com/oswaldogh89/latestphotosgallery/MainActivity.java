package com.oswaldogh89.latestphotosgallery;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.oswaldogh89.library.Image;
import com.oswaldogh89.library.LatestImages;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public final int MY_PERMISSIONS_REQUEST_READ_MEDIA = 123;
    public LatestImages images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_MEDIA);
        } else {
            images = (LatestImages) findViewById(R.id.customControl);
            images.setListSize(10);
        }

        Button BtnObtain = (Button) findViewById(R.id.BtnObtain);
        BtnObtain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Image> im = images.getSelectedImages();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_MEDIA:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    images = (LatestImages) findViewById(R.id.customControl);
                    images.setListSize(10);
                }
                break;
            default:
                break;
        }
    }


}
