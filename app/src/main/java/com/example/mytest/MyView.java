package com.yizhixionga.test_2.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


/**
 * Author:lizy
 * time:2022/8/26 13:45
 * Description : MyView正方形view
 **/
public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);

        if (width < height) {
            height=width;
        } else {
            width = height;
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
        // 基本的而绘制功能，比如绘制背景颜色、背景图片等
        super.onDraw(canvas);
        //也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了
        int r = getMeasuredWidth()/2;
        //圆心的横坐标为当前的View的左边起始位置+半径
        int centerX = getLeft()+r;
        //圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = getTop()+r;

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        //开始绘制
        canvas.drawCircle(centerX,centerY,r,paint);
    }

    private int getMySize(int defauilSize, int measureSpec) {
        int mySize = defauilSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED://如果没有指定大小，就设置为默认大小
                mySize = defauilSize;
                break;
            case MeasureSpec.AT_MOST://如果测量模式是最大取值为size,我们将大小取最大值,你也可以取其他值
            case MeasureSpec.EXACTLY://如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
        }

        return mySize;
    }


}
