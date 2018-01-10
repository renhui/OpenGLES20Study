package com.renhui.opengles20study.camera.preview;

import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.renhui.opengles20study.base.BaseGLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 摄像头展示界面
 */
public class CameraView extends BaseGLSurfaceView implements GLSurfaceView.Renderer {

    public static int cameraId = 1;  // 要打开的摄像头的ID

    private GLCamera mCamera;
    private GLCameraDrawer mCameraDrawer;

    public CameraView(Context context) {
        super(context);

        setRenderer(this);  // 设置渲染器

        mCamera = new GLCamera(); // 创建摄像头资源
        mCameraDrawer = new GLCameraDrawer();
    }

    public CameraView(Context context, AttributeSet attr) {
        super(context, attr);

        setRenderer(this);  // 设置渲染器

        mCamera = new GLCamera(); // 创建摄像头资源
        mCameraDrawer = new GLCameraDrawer();
    }

    // 切换摄像头
    public void switchCamera() {
        cameraId = cameraId == 1 ? 0 : 1;
        onPause();
        onResume();
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mCameraDrawer.onSurfaceCreated(gl, config);
        mCamera.open(cameraId); // 打开指定的摄像头
        Point point = mCamera.getPreviewSize();
        mCameraDrawer.setDataSize(point.x, point.y);
        mCamera.setPreviewTexture(mCameraDrawer.getSurfaceTexture()); // 将SurfaceTexture和摄像头预览绑定
        mCameraDrawer.getSurfaceTexture().setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
            @Override
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                requestRender();
            }
        });
        mCamera.preview();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        mCameraDrawer.setViewSize(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        mCameraDrawer.onDrawFrame(gl);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCamera.close();
    }
}
