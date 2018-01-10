package com.renhui.opengles20study.camera.preview;

import android.graphics.Point;
import android.graphics.SurfaceTexture;

/**
 * 定义了一些摄像头事件的接口
 */
public interface ICamera {

    /**
     * 打开摄像头
     *
     * @param cameraId 打开的摄像头的ID
     * @return 是否成功打开摄像头
     */
    boolean open(int cameraId);

    /**
     * 预览摄像头内容
     */
    boolean preview();

    /**
     * 关闭摄像头
     */
    boolean close();

    /**
     * 设置展示摄像头的图像数据的容器
     * @param texture
     */
    void setPreviewTexture(SurfaceTexture texture);

    /**
     * 获取预览尺寸
     */
    Point getPreviewSize();

    class Config {
        float rate; //宽高比
        int minPreviewWidth;
        int minPictureWidth;
    }
}
