package com.renhui.opengles20study.camera.preview;

import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 使用后OpenGL预览的摄像头控制逻辑
 */
public class GLCamera implements ICamera {

    private Config mConfig;
    private Camera mCamera;
    private CameraSizeComparator sizeComparator;
    private Point mPreSize;


    private Camera.Size picSize;
    private Camera.Size preSize;


    public GLCamera() {
        this.mConfig = new Config();
        mConfig.minPreviewWidth = 720;
        mConfig.minPictureWidth = 720;
        mConfig.rate = 1.778f;
        sizeComparator = new CameraSizeComparator();
    }

    /**
     * 打开摄像头
     *
     * @param cameraId 打开的摄像头的ID
     * @return 是否成功打开摄像头
     */
    @Override
    public boolean open(int cameraId) {
        mCamera = Camera.open(cameraId);
        if (mCamera != null) {
            Camera.Parameters param = mCamera.getParameters();
            picSize = getPropPictureSize(param.getSupportedPictureSizes(), mConfig.rate, mConfig.minPictureWidth);
            preSize = getPropPreviewSize(param.getSupportedPreviewSizes(), mConfig.rate, mConfig.minPreviewWidth);
            param.setPictureSize(picSize.width, picSize.height);
            param.setPreviewSize(preSize.width, preSize.height);
            mCamera.setParameters(param);
            Camera.Size pre = param.getPreviewSize();
            mPreSize = new Point(pre.height, pre.width);
            return true;
        }
        return false;
    }

    @Override
    public boolean preview() {
        if (mCamera != null) {
            mCamera.startPreview();
        }
        return true;
    }

    @Override
    public boolean close() {
        if (mCamera != null) {
            try {
                mCamera.stopPreview();
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public void setPreviewTexture(SurfaceTexture texture) {
        if (mCamera != null) {
            try {
                mCamera.setPreviewTexture(texture);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Point getPreviewSize() {
        return mPreSize;
    }

    private Camera.Size getPropPreviewSize(List<Camera.Size> list, float th, int minWidth) {
        Collections.sort(list, sizeComparator);

        int i = 0;
        for (Camera.Size s : list) {
            if ((s.height >= minWidth) && equalRate(s, th)) {
                break;
            }
            i++;
        }
        if (i == list.size()) {
            i = 0;
        }
        return list.get(i);
    }

    private Camera.Size getPropPictureSize(List<Camera.Size> list, float th, int minWidth) {
        Collections.sort(list, sizeComparator);

        int i = 0;
        for (Camera.Size s : list) {
            if ((s.height >= minWidth) && equalRate(s, th)) {
                break;
            }
            i++;
        }
        if (i == list.size()) {
            i = 0;
        }
        return list.get(i);
    }

    private boolean equalRate(Camera.Size s, float rate) {
        float r = (float) (s.width) / (float) (s.height);
        if (Math.abs(r - rate) <= 0.03) {
            return true;
        } else {
            return false;
        }
    }


    private class CameraSizeComparator implements Comparator<Camera.Size> {
        public int compare(Camera.Size lhs, Camera.Size rhs) {
            // TODO Auto-generated method stub
            if (lhs.height == rhs.height) {
                return 0;
            } else if (lhs.height > rhs.height) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
