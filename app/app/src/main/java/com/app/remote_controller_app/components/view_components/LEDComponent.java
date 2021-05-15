package com.app.remote_controller_app.components.view_components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.graphics.ColorUtils;

//TODO Dodac mozliwosc zmiany pozycji i rozmiaru

public class LEDComponent extends View {

    int LEDColor;
    int borderColor;
    int defaultColor;

    int diameter = 160;
    float borderWidth = 4.0f;


    public LEDComponent(Context context) {
        super(context);
        LEDColor = Color.RED;
        defaultColor = LEDColor;
        borderColor = Color.BLACK;
    }

    public LEDComponent(Context context, int color) {
        super(context);
        LEDColor = color;
        defaultColor = LEDColor;
        borderColor = Color.BLACK;
    }

    int positionX;
    int positionY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLED(canvas);
    }

    void drawLED(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(LEDColor);
        paint.setStyle(Paint.Style.FILL);

        float radius = diameter / 2f;

        canvas.drawCircle(diameter / 2f, diameter / 2f, radius, paint);

        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);

        canvas.drawCircle(diameter / 2f, diameter / 2f, radius - borderWidth / 2f, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(diameter, diameter);
    }

    public void setLEDColor(String LEDColor) {
        this.defaultColor = Color.parseColor(LEDColor);
        this.LEDColor = defaultColor;
        this.postInvalidate();
    }

    public void darker(float ratio){
        this.LEDColor = ColorUtils.blendARGB(this.defaultColor, Color.BLACK, ratio);
        this.postInvalidate();
    }

    public void lighter(float ratio){
        this.LEDColor = ColorUtils.blendARGB(this.defaultColor, Color.WHITE, ratio);
        this.postInvalidate();
    }


    public void setDiameter(int diameter) {
        this.diameter = diameter;
        this.postInvalidate();
    }

}
