package com.renhui.opengles20study.camera.preview;

import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.renhui.opengles20study.camera.filter.BaseFilter;
import com.renhui.opengles20study.camera.filter.OesFilter;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * OpenGL 摄像头绘制器
 */
public class GLCameraDrawer implements GLSurfaceView.Renderer {

    private SurfaceTexture surfaceTexture;

    private BaseFilter mOesFilter;

    private int width, height;
    private int dataWidth, dataHeight;

    private float[] matrix = new float[16];

    public GLCameraDrawer() {
        mOesFilter = new OesFilter();
    }

    public SurfaceTexture getSurfaceTexture() {
        return surfaceTexture;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        int texture = createTextureID();
        surfaceTexture = new SurfaceTexture(texture);
        mOesFilter.create();
        mOesFilter.setTextureId(texture);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        setViewSize(width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        if (surfaceTexture != null) {
            // 更新纹理图像为从图像流中提取的最近一帧
            surfaceTexture.updateTexImage();
        }
        // 绘制纹理
        mOesFilter.draw();
    }

    public void setViewSize(int width, int height) {
        this.width = width;
        this.height = height;
        calculateMatrix();
    }

    public void setDataSize(int dataWidth,int dataHeight){
        this.dataWidth=dataWidth;
        this.dataHeight=dataHeight;
        calculateMatrix();
    }

    private void calculateMatrix() {
        getShowMatrix(matrix, this.dataWidth, this.dataHeight, this.width, this.height);
        if (CameraView.cameraId == 1) {
            flip(matrix, true, false);
            rotate(matrix, 90);
        } else {
            rotate(matrix, 270);
        }
        mOesFilter.setMatrix(matrix);
    }

    public static float[] rotate(float[] m, float angle) {
        Matrix.rotateM(m, 0, angle, 0, 0, 1);
        return m;
    }

    public static float[] flip(float[] m, boolean x, boolean y) {
        if (x || y) {
            Matrix.scaleM(m, 0, x ? -1 : 1, y ? -1 : 1, 1);
        }
        return m;
    }

    // 创建纹理ID
    private int createTextureID() {
        int[] texture = new int[1];
        GLES20.glGenTextures(1, texture, 0);  // 创建纹理，生成你要操作的纹理对象的索引
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture[0]); // 绑定纹理，告诉OpenGL下面代码中对2D纹理的任何设置都是针对索引为0的纹理的

        // glTexParameterf 是设置纹理贴图的参数属性
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        // OpenGL——纹理过滤函数glTexParameteri() 确定如何把纹理象素映射成像素.
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
        return texture[0];
    }

    public static void getShowMatrix(float[] matrix, int imgWidth, int imgHeight, int viewWidth, int viewHeight) {
        if (imgHeight > 0 && imgWidth > 0 && viewWidth > 0 && viewHeight > 0) {
            float sWhView = (float) viewWidth / viewHeight;
            float sWhImg = (float) imgWidth / imgHeight;
            float[] projection = new float[16];
            float[] camera = new float[16];
            if (sWhImg > sWhView) {
                Matrix.orthoM(projection, 0, -sWhView / sWhImg, sWhView / sWhImg, -1, 1, 1, 3);
            } else {
                Matrix.orthoM(projection, 0, -1, 1, -sWhImg / sWhView, sWhImg / sWhView, 1, 3);
            }
            Matrix.setLookAtM(camera, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0);
            Matrix.multiplyMM(matrix, 0, projection, 0, camera, 0);
        }
    }
}
