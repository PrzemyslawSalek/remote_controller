package com.app.remote_controller_app.components.view_components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

//TODO Dodac mozliwosc zmiany pozycji i rozmiaru

public class LEDComponent extends View {

    public LEDComponent(Context context) {
        super(context);
    }

    public LEDComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LEDComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LEDComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    int LEDColor = Color.RED;
    int borderColor = Color.BLACK;

    float borderWidth = 4.0f;
    int size = 160;



    int positionX;
    int positionY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawLED(canvas);
    }

    void drawLED(Canvas canvas) {
        paint.setColor(LEDColor);
        paint.setStyle(Paint.Style.FILL);

        float radius = size / 2f;

        canvas.drawCircle(size / 2f, size / 2f, radius, paint);

        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);

        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(size, size);
    }

    public void setLEDColor(int LEDColor) {
        this.LEDColor = LEDColor;
        this.postInvalidate();
    }

    public void setSize(int size) {
        this.size = size;
        this.postInvalidate();
    }
}
