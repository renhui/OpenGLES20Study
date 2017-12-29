package com.renhui.opengles20study.base;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * GLSurfaceView 基类
 */
public class BaseGLSurfaceView extends GLSurfaceView {

    public BaseGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
    }
}
