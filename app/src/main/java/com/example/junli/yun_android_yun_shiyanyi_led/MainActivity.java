package com.example.junli.yun_android_yun_shiyanyi_led;

import android.app.Activity;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private CameraManager cameraManager;
    private String cameraID;
    private int num = 9;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraManager = (CameraManager)getSystemService(CAMERA_SERVICE);
        cameraID = CameraUtils.getFlashCameraId(cameraManager);

        CameraUtils.setFlashStatus(cameraManager,cameraID,LightStatus.ON);
        LightTurnOffTask turnOffTask = new LightTurnOffTask(10000,cameraManager,cameraID,this);
        turnOffTask.execute();

        final TextView textView = findViewById(R.id.text);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(num+"s");
                num--;
                handler.postDelayed(this,1000);

            }
        },1000);
    }
    protected void onPause() {
        super.onPause();
        finish();
    }
}
