package com.wl.tableball.model;

import org.jbox2d.dynamics.Body;
import android.graphics.Bitmap;
import com.wl.tableball.game.GameView;

public class Ball extends MyBody{
	
	Ball(Body body,Bitmap bitmap,GameView gameview)
	{
		super(body,bitmap,gameview);
	}
}

