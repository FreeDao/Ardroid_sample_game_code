package com.baina.tower.impleclass;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface BulletInterface
{
	public void drawSelf(Canvas canvas);
	public void run();
	public boolean isLive();
}
