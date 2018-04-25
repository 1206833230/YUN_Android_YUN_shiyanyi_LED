package com.example.junli.yun_android_yun_shiyanyi_led;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;

public class CameraUtils{
    //获取设配相机id
    public static String getFlashCameraId(CameraManager cameraManager) {
        try {
            if (cameraManager == null) return null;
            String[] cameraList = cameraManager.getCameraIdList();
            for (String id : cameraList) {
                CameraCharacteristics cameraInfo = cameraManager.getCameraCharacteristics(id);
                boolean hasFlash = cameraInfo.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                if (hasFlash) {
                    //id
                    return id;
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return null;
        }


        //启动相机
        public static void setFlashStatus(CameraManager cameraManager, String cameraID, LightStatus lightStatus){
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraID,lightStatus== LightStatus.ON);
            }
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }
}