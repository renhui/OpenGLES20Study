package com.renhui.opengles20study.camera.filter;

import android.opengl.GLES11Ext;
import android.opengl.GLES20;

import java.util.Arrays;

public class OesFilter extends BaseFilter {

    String vertex = "attribute vec4 vPosition;\n" +
            "attribute vec2 vCoord;\n" +
            "uniform mat4 vMatrix;\n" +
            "uniform mat4 vCoordMatrix;\n" +
            "varying vec2 textureCoordinate;\n" +
            "\n" +
            "void main(){\n" +
            "    gl_Position = vMatrix*vPosition;\n" +
            "    textureCoordinate = (vCoordMatrix*vec4(vCoord,0,1)).xy;\n" +
            "}";

    String fragment = "#extension GL_OES_EGL_image_external : require\n" +
            "precision mediump float;\n" +
            "varying vec2 textureCoordinate;\n" +
            "uniform samplerExternalOES vTexture;\n" +
            "void main() {\n" +
            "    gl_FragColor = texture2D( vTexture, textureCoordinate );\n" +
            "}";


    private int mHCoordMatrix;
    private float[] mCoordMatrix = Arrays.copyOf(OM, 16);

    public OesFilter() {
    }

    @Override
    protected void onCreate() {
        createProgram(vertex, fragment);
        mHCoordMatrix = GLES20.glGetUniformLocation(mProgram, "vCoordMatrix");
    }

    public void setCoordMatrix(float[] matrix) {
        this.mCoordMatrix = matrix;
    }

    @Override
    protected void onSetExpandData() {
        super.onSetExpandData();
        GLES20.glUniformMatrix4fv(mHCoordMatrix, 1, false, mCoordMatrix, 0);
    }

    @Override
    protected void onBindTexture() {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + getTextureType());
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, getTextureId());
        GLES20.glUniform1i(mHTexture, getTextureType());
    }

    @Override
    protected void onSizeChanged(int width, int height) {

    }

}
