package com.renhui.opengles20study.glsv;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.renhui.opengles20study.base.BaseGLSurfaceView;
import com.renhui.opengles20study.image.ImageRenderer;

import java.io.IOException;

/**
 * 展示图片的 GLSurfaceView
 */
public class ImageGLSurfaceView extends BaseGLSurfaceView {

    ImageRenderer imageRenderer;

    public ImageGLSurfaceView(Context context) throws IOException {
        super(context);
        imageRenderer = new ImageRenderer(context);
        setRenderer(imageRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        requestRender();
    }
}
