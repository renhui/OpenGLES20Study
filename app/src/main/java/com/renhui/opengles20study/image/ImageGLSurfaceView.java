package com.renhui.opengles20study.image;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;

import java.io.IOException;

/**
 * 展示图片的 GLSurfaceView
 */
public class ImageGLSurfaceView extends GLSurfaceView {

    ImageRenderer imageRenderer;

    public ImageGLSurfaceView(Context context) throws IOException {
        super(context);
        setEGLContextClientVersion(2);
        imageRenderer = new ImageRenderer(context, BitmapFactory.decodeStream(getResources().getAssets().open("texture/fengj.png")));
        setRenderer(imageRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        requestRender();
    }
}
