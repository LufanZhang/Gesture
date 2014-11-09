package com.example.test3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyView extends SurfaceView implements SurfaceHolder.Callback{
	MainActivity father;  
	  
    private static final int MAX_TOUCHPOINTS = 5;  
    private static final String START_TEXT = "gesture test!";  
    private Paint textPaint = new Paint();  
    private Paint touchPaints[] = new Paint[MAX_TOUCHPOINTS];  
    private int colors[] = new int[MAX_TOUCHPOINTS];  
    private int width, height;  
  
    // private float scale = 1.0f;  
  
    public MyView(MainActivity father) {  
        super(father);  
        // TODO Auto-generated constructor stub  
        this.father = father;  
        SurfaceHolder holder = getHolder();  
        holder.addCallback(this);  
        setFocusable(true);   
        setFocusableInTouchMode(true); // make sure it can obtain touch event  
        init();  
    }  
  
    private void init() {  
        // initiate 5 paints with different color  
        textPaint.setColor(Color.WHITE);  
        colors[0] = Color.BLUE;  
        colors[1] = Color.RED;  
        colors[2] = Color.GREEN;  
        colors[3] = Color.YELLOW;  
        colors[4] = Color.CYAN;  
        for (int i = 0; i < MAX_TOUCHPOINTS; i++) {  
            touchPaints[i] = new Paint();  
            touchPaints[i].setColor(colors[i]);  
        }  
    }  
  
    /** 
     * operate touch event 
     */  
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
        // obtain the number of fingers on screen
        int pointerCount = event.getPointerCount();  
        System.out.println("pointerCount = "+pointerCount);
        if (pointerCount > MAX_TOUCHPOINTS) {  
            pointerCount = MAX_TOUCHPOINTS;  
        }  
        // lock Canvas, start operate corresponding activity 
        Canvas c = getHolder().lockCanvas();  
        if (c != null) {  
            c.drawColor(Color.BLACK);  
            if (event.getAction() == MotionEvent.ACTION_UP) {  
                // after all of the fingers gone, clear the screen  
            } else {  
                // first draw a crosshair, then draw a circle
                for (int i = 0; i < pointerCount; i++) {  
                    // obtain the coordination of a point and start draw
                    int id = event.getPointerId(i);  
                    int x = (int) event.getX(i);  
                    int y = (int) event.getY(i);  
                    drawCrosshairsAndText(x, y, touchPaints[id], i, id, c);  
                }  
                for (int i = 0; i < pointerCount; i++) {  
                    int id = event.getPointerId(i);  
                    int x = (int) event.getX(i);  
                    int y = (int) event.getY(i);  
                    drawCircle(x, y, touchPaints[id], c);  
                }  
            }  
            // after drawing，unlock  
            getHolder().unlockCanvasAndPost(c);  
        }  
        return true;  
    }  
  

    /**  
     * draw crosshair 
     */
    private void drawCrosshairsAndText(int x, int y, Paint paint, int ptr,  
            int id, Canvas c) {  
        c.drawLine(0, y, width, y, paint);  
        c.drawLine(x, 0, x, height, paint);  
        int textY = (int) (30 + 25 * ptr);  
        c.drawText("x" + ptr + "=" + x, 10, textY, textPaint);  
        c.drawText("y" + ptr + "=" + y, 120, textY, textPaint);  
        c.drawText("id" + ptr + "=" + id, width - 75, textY, textPaint); 
        System.out.println("textY = "+textY+"textPaint = "+textPaint);
    }  
  
    /**  
     * draw circle
     */  
    private void drawCircle(int x, int y, Paint paint, Canvas c) {  
        c.drawCircle(x, y, 80, paint);  
    }  
  
    @Override  
    public void surfaceChanged(SurfaceHolder holder, int format, int width,  
            int height) {  
        // TODO Auto-generated method stub  
        this.width = width;  
        this.height = height;  
        System.out.println("width = "+width+" height = "+height);
        textPaint.setTextSize(30);  
        Canvas c = getHolder().lockCanvas();  
        if (c != null) {  
            // background's color is black 
            c.drawColor(Color.BLACK);  
            float tWidth = textPaint.measureText(START_TEXT);  
            c.drawText(START_TEXT, width / 2 - tWidth / 2, height / 2,  
                    textPaint);  
            getHolder().unlockCanvasAndPost(c);  
        }  
    }  
  
    @Override  
    public void surfaceCreated(SurfaceHolder holder) {  
        // TODO Auto-generated method stub  
  
    }  
  
    @Override  
    public void surfaceDestroyed(SurfaceHolder holder) {  
        // TODO Auto-generated method stub  
  
    } 
}
