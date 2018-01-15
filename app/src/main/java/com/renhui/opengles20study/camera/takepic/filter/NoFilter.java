package com.renhui.opengles20study.camera.takepic.filter;

import android.content.res.Resources;


public class NoFilter extends AFilter {

    String vertexCode = "attribute vec4 vPosition;\n" +
            "attribute vec2 vCoord;\n" +
            "uniform mat4 vMatrix;\n" +
            "\n" +
            "varying vec2 textureCoordinate;\n" +
            "\n" +
            "void main(){\n" +
            "    gl_Position = vMatrix*vPosition;\n" +
            "    textureCoordinate = vCoord;\n" +
            "}";

    String fragmentCode = "precision mediump float;\n" +
            "varying vec2 textureCoordinate;\n" +
            "uniform sampler2D vTexture;\n" +
            "void main() {\n" +
            "    gl_FragColor = texture2D( vTexture, textureCoordinate );\n" +
            "}";

    public NoFilter(Resources res) {
        super(res);
    }

    @Override
    protected void onCreate() {

        createProgram(vertexCode, fragmentCode);
    }

    @Override
    protected void onSizeChanged(int width, int height) {

    }
}
