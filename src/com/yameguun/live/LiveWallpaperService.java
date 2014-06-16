package com.yameguun.live;

import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.extension.ui.livewallpaper.BaseLiveWallpaperService;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;

import android.util.DisplayMetrics;
import android.view.WindowManager;

public class LiveWallpaperService extends BaseLiveWallpaperService implements
		IAccelerationListener {

	// ================================================================================
	// Fields
	// ================================================================================
	private static final int MAX_FRAMES_PER_SECOND = 60;

	private static int CAMERA_WIDTH;
	private static int CAMERA_HEIGHT;

	private Camera mCamera;
	private Scene mScene;

	@Override
	public EngineOptions onCreateEngineOptions() {

		final DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		wm.getDefaultDisplay().getRotation();
		CAMERA_WIDTH = displayMetrics.widthPixels;
		CAMERA_HEIGHT = displayMetrics.heightPixels;
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.mCamera);
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback createResourcesCallback) throws Exception {
		this.enableAccelerationSensor(this);
		createResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public org.andengine.engine.Engine onCreateEngine(final EngineOptions pEngineOptions) {
		return new LimitedFPSEngine(pEngineOptions, MAX_FRAMES_PER_SECOND);
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback createSceneCallback)
			throws Exception {
		
		mScene = new Scene();
		
		//背景色
		mScene.setBackground(new Background(0.0f, 0.0f, 0.0f));
		
		createSceneCallback.onCreateSceneFinished(mScene);

	}

	@Override
	public void onPopulateScene(Scene arg0,OnPopulateSceneCallback populateSceneCallback) throws Exception {
		populateSceneCallback.onPopulateSceneFinished();
	}

	@Override
	public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {
	}

	@Override
	public void onAccelerationChanged(AccelerationData pAccelerationData) {
	}

}
