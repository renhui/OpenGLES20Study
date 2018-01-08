package com.renhui.opengles20study.camera;

import android.graphics.Point;
import android.graphics.SurfaceTexture;

/**
 * 定义了一些摄像头事件的接口
 */
public interface ICamera {

    /**
     * 打开摄像头
     * @param cameraId 打开的摄像头的ID
     * @return 是否成功打开摄像头
     */
    boolean open(int cameraId);

    void setConfig(Config config);

    boolean preview();

    /**
     * 切换摄像头
     * @param cameraId 要切换的摄像头的ID
     * @return 是否切换成功
     */
    boolean switchTo(int cameraId);

    void takePhoto(TakePhotoCallback callback);

    boolean close();

    void setPreviewTexture(SurfaceTexture texture);

    Point getPreviewSize();

    Point getPictureSize();

    void setOnPreviewFrameCallback(PreviewFrameCallback callback);

    class Config {
        float rate; //宽高比
        int minPreviewWidth;
        int minPictureWidth;
    }

    interface TakePhotoCallback {
        void onTakePhoto(byte[] bytes, int width, int height);
    }

    interface PreviewFrameCallback {
        void onPreviewFrame(byte[] bytes, int width, int height);
    }
}
