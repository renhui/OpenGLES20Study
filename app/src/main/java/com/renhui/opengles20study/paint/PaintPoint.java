package com.renhui.opengles20study.paint;

import android.opengl.GLES20;

/**
 * 画笔点
 */
public class PaintPoint implements PPgles {

    private static final String TAG = "CZPoint";

    private static final String VERTEX_SHADER
            = "attribute vec4 a_Position;"
            + "void main() {"
            + "    gl_Position = a_Position;"
            + "    gl_PointSize = 50.0;"
            + "}";
    private static final String FRAGMENT_SHADER
            = "precision mediump float;"
            + "uniform vec4 u_FragmentColor;"
            + "void main() {"
            + "    gl_FragColor = u_FragmentColor;"
            + "}";

    private int mAPosition;
    private int mUFragmentColor;
    private float[] mPosition = {0.0f, 0.0f};
    private float[] mColor = {1.0f, 0.0f, 0.0f, 1.0f};

    @Override
    public void init(int program, int vertexShader, int fragmentShader) {
        mAPosition = GLES20.glGetAttribLocation(program, "a_Position");
        mUFragmentColor = GLES20.glGetUniformLocation(program, "u_FragmentColor");
    }

    @Override
    public String getVertexShader() {
        return VERTEX_SHADER;
    }

    @Override
    public String getFragmentShader() {
        return FRAGMENT_SHADER;
    }

    @Override
    public void draw() {
        GLES20.glVertexAttrib3f(mAPosition, mPosition[0], mPosition[1], 0.0f);
        GLES20.glUniform4f(mUFragmentColor, mColor[0], mColor[1], mColor[2], mColor[3]);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 1);
    }

    public void setPosition(float x, float y) {
        mPosition[0] = x;
        mPosition[1] = y;
    }

    public void setColor(float r, float g, float b, float a) {
        mColor[0] = r;
        mColor[1] = g;
        mColor[2] = b;
        mColor[3] = a;
    }
}
