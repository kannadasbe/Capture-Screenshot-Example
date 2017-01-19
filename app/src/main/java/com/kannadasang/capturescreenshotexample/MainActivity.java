package com.kannadasang.capturescreenshotexample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imgScreenShot;
    Button btnCaptureScreenshot;
TextView tvImagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvImagePath= (TextView) findViewById(R.id.tvImagePath);
        imgScreenShot = (ImageView) findViewById(R.id.imgScreenShot);
        btnCaptureScreenshot = (Button) findViewById(R.id.btnCaptureScreeshot);
        // Button onclick listener
        btnCaptureScreenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating screenshot bitmap image
                View rootView = view.getRootView();
                rootView.setDrawingCacheEnabled(true);
                Bitmap bitmapScreenshot = rootView.getDrawingCache();



                // Create image from bitmap and store it in memory
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapScreenshot.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
                Random rand = new Random();
                int randomValue = rand.nextInt(9999);
                File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/"+String.valueOf(randomValue) + "capturedimage.jpg");
                try {
                    if (file.createNewFile()) {
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    fileOutputStream.close();

                    // Setting screenshot image to image view
                    imgScreenShot.setImageBitmap(bitmapScreenshot);
                    tvImagePath.setText("Screenshot saved to :" + file.getAbsolutePath());


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
