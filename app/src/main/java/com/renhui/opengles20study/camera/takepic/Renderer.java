package com.renhui.opengles20study.camera.takepic;

import android.opengl.GLSurfaceView;


/**
 * @自定义GLSurfaceView.Renderer@
 * @继承GLSurfaceView的渲染器，并增加onDestroy方法定义@
 */
public interface Renderer extends GLSurfaceView.Renderer {

    void onDestroy();

}
