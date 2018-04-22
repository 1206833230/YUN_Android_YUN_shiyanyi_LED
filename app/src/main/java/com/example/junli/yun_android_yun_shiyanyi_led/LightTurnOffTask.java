package com.example.junli.yun_android_yun_shiyanyi_led;

import android.app.Activity;
import android.hardware.camera2.CameraManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class LightTurnOffTask extends AsyncTask<String,String,String>{

    private  long time;
    private CameraManager cameraManager;
    private String cameraId;
    private Activity ui;

    /**
     * @param time
     * @param cameraManager
     * @param cameraId
     * @param ui
     */
    public LightTurnOffTask(long time, CameraManager cameraManager, String cameraId, Activity ui) {
        this.time = time;
        this.cameraManager = cameraManager;
        this.cameraId = cameraId;
        this.ui = ui;
    }

    //主要负责耗时的后台处理
    @Override
    protected String doInBackground(String... strings) {
        try{
            Thread.sleep(time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    //在doInBackground执行完之后，onPostExecute方法将被UI调用，后台的计算结果将通过该方法传递到UI线程，并在界面上展示给用户。
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onPostExecute(String s) {
        CameraUtils.setFlashStatus(cameraManager, cameraId,LightStatus.OFF);
        ui.finish();
    }

}
