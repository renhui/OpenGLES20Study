package com.renhui.opengles20study.shape.square;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.renhui.opengles20study.base.BaseGLSL;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * 多种矩阵变换的正方体
 */
public class VaryMatrixCube extends BaseGLSL {

    private FloatBuffer vertexBuffer, colorBuffer;
    private ShortBuffer indexBuffer;
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "uniform mat4 vMatrix;" +
                    "varying  vec4 vColor;" +
                    "attribute vec4 aColor;" +
                    "void main() {" +
                    "  gl_Position = vMatrix*vPosition;" +
                    "  vColor=aColor;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "varying vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private int mProgram;

    final float cubePositions[] = {
            -1.0f, 1.0f, 1.0f,    //正面左上0
            -1.0f, -1.0f, 1.0f,   //正面左下1
            1.0f, -1.0f, 1.0f,    //正面右下2
            1.0f, 1.0f, 1.0f,     //正面右上3
            -1.0f, 1.0f, -1.0f,    //反面左上4
            -1.0f, -1.0f, -1.0f,   //反面左下5
            1.0f, -1.0f, -1.0f,    //反面右下6
            1.0f, 1.0f, -1.0f,     //反面右上7
    };
    final short index[] = {
            6, 7, 4, 6, 4, 5,    //后面
            6, 3, 7, 6, 2, 3,    //右面
            6, 5, 1, 6, 1, 2,    //下面
            0, 3, 2, 0, 2, 1,    //正面
            0, 1, 5, 0, 5, 4,    //左面
            0, 7, 3, 0, 4, 7,    //上面
    };

    float color[] = {
            0f, 1f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 1f, 0f, 1f,
            1f, 0f, 0f, 1f,
            1f, 0f, 0f, 1f,
            1f, 0f, 0f, 1f,
            1f, 0f, 0f, 1f,
    };

    private int mPositionHandle;
    private int mColorHandle;

    private float[] mMVPMatrix = new float[16];

    private int mMatrixHandler;

    public VaryMatrixCube() {
        ByteBuffer bb = ByteBuffer.allocateDirect(cubePositions.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(cubePositions);
        vertexBuffer.position(0);

        ByteBuffer dd = ByteBuffer.allocateDirect(
                color.length * 4);
        dd.order(ByteOrder.nativeOrder());
        colorBuffer = dd.asFloatBuffer();
        colorBuffer.put(color);
        colorBuffer.position(0);

        ByteBuffer cc = ByteBuffer.allocateDirect(index.length * 2);
        cc.order(ByteOrder.nativeOrder());
        indexBuffer = cc.asShortBuffer();
        indexBuffer.put(index);
        indexBuffer.position(0);
        mProgram = createOpenGLProgram(vertexShaderCode, fragmentShaderCode);
    }

    public void onSurfaceCreated() {
        //开启深度测试
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
    }


    private float[] mMatrixCamera = new float[16];    //相机矩阵
    private float[] mMatrixProjection = new float[16];    //投影矩阵
    private float[] mMatrixCurrent =     //原始矩阵
            {1, 0, 0, 0,
                    0, 1, 0, 0,
                    0, 0, 1, 0,
                    0, 0, 0, 1};

    //设置相机
    public void setCamera(float ex, float ey, float ez, float cx, float cy, float cz, float ux, float uy, float uz) {
        Matrix.setLookAtM(mMatrixCamera, 0, ex, ey, ez, cx, cy, cz, ux, uy, uz);
    }

    public void ortho(float left, float right, float bottom, float top, float near, float far) {
        Matrix.orthoM(mMatrixProjection, 0, left, right, bottom, top, near, far);
    }

    //平移变换
    public void translate(float x, float y, float z) {
        Matrix.translateM(mMatrixCurrent, 0, x, y, z);
    }

    //旋转变换
    public void rotate(float angle, float x, float y, float z) {
        Matrix.rotateM(mMatrixCurrent, 0, angle, x, y, z);
    }

    //缩放变换
    public void scale(float x, float y, float z) {
        Matrix.scaleM(mMatrixCurrent, 0, x, y, z);
    }

    public float[] getFinalMatrix() {
        float[] ans = new float[16];
        Matrix.multiplyMM(ans, 0, mMatrixCamera, 0, mMatrixCurrent, 0);
        Matrix.multiplyMM(ans, 0, mMatrixProjection, 0, ans, 0);
        return ans;
    }

    public void onSurfaceChanged(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float rate = width / (float) height;
        ortho(-rate * 6, rate * 6, -6, 6, 3, 20);
        setCamera(0, 0, 10, 0, 0, 0, 0, 1, 0);

        // y轴正方向平移
        //translate(0, 3, 0);

        // y轴负方向平移，然后按xyz->(0,0,0)到(1,1,1)旋转30度
        // translate(0, -3, 0);
        // rotate(30f, 1, 1, 1);

        // x轴负方向平移，然后按xyz->(0,0,0)到(1,-1,1)旋转120度，在放大到0.5倍
        // translate(-3, 0, 0);
        // rotate(120f, 1, -1, 1);
        // scale(0.5f, 0.5f, 0.5f);

        translate(1, 0, 0);
        scale(1.0f, 2.0f, 1.0f);
        rotate(30f, 1, 2, 1);

    }

    public void draw() {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        //将程序加入到OpenGLES2.0环境
        GLES20.glUseProgram(mProgram);
        //获取变换矩阵vMatrix成员句柄
        mMatrixHandler = GLES20.glGetUniformLocation(mProgram, "vMatrix");
        //指定vMatrix的值
        mMVPMatrix = getFinalMatrix();
        if (mMVPMatrix != null) {
            GLES20.glUniformMatrix4fv(mMatrixHandler, 1, false, mMVPMatrix, 0);
        }
        //获取顶点着色器的vPosition成员句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角形的坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);
        //获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
        //设置绘制三角形的颜色
//        GLES20.glUniform4fv(mColorHandle, 2, color, 0);
        GLES20.glEnableVertexAttribArray(mColorHandle);
        GLES20.glVertexAttribPointer(mColorHandle, 4, GLES20.GL_FLOAT, false, 0, colorBuffer);
        //索引法绘制正方体
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, index.length, GLES20.GL_UNSIGNED_SHORT, indexBuffer);
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }


}
