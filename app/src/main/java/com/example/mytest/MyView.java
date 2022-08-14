package com.example.mytest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    private Paint mPaint;
    private Paint mPaintBorder;

    public MyView(Context context) {
        super(context);
        init();
    }


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(getResources().getColor(R.color.purple_200));//画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//画笔风格

        mPaintBorder = new Paint();
        mPaintBorder.setAntiAlias(true);//抗锯齿
        mPaintBorder.setColor(getResources().getColor(R.color.teal_700));//画笔颜色
        mPaintBorder.setStyle(Paint.Style.STROKE);//画笔风格
        mPaintBorder.setStrokeWidth(20);//画笔粗细

    }

    /*重写该方法，在这里绘图*/
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#ff00ff"));
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);

        mPaintBorder.setColor(Color.parseColor("#00ff00"));
        canvas.drawCircle(0,0,100,mPaint);
        canvas.drawCircle(0,getBottom(),100,mPaint);
        canvas.drawCircle(getRight(),0,100,mPaint);
        canvas.drawCircle(getRight(),getBottom(),100,mPaint);
//        canvas.drawCircle(40,40,40,mPaint);
        drawBorder(canvas);
    }

    public void drawBorder(Canvas canvas){
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaintBorder);

    }
}
