package com.renhui.opengles20study.camera.takepic.filter;

import android.content.res.Resources;
import android.hardware.Camera;

public class CameraFilter extends OesFilter {

    public CameraFilter(Resources mRes) {
        super(mRes);
    }

    @Override
    protected void initBuffer() {
        super.initBuffer();
    }

    @Override
    public void setFlag(int flag) {
        super.setFlag(flag);
        if (getFlag() == Camera.CameraInfo.CAMERA_FACING_FRONT) {    //前置摄像头
            cameraFront();
        } else if (getFlag() == Camera.CameraInfo.CAMERA_FACING_BACK) {   //后置摄像头
            cameraBack();
        }
    }

    private void cameraFront() {
        float[] coord = new float[]{
                1.0f, 0.0f,
                0.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
        };
        mTexBuffer.clear();
        mTexBuffer.put(coord);
        mTexBuffer.position(0);
    }

    private void cameraBack() {
        float[] coord = new float[]{
                1.0f, 0.0f,
                0.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
        };
        mTexBuffer.clear();
        mTexBuffer.put(coord);
        mTexBuffer.position(0);
    }
}
