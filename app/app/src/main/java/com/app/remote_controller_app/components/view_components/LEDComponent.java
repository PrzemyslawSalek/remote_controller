package com.app.remote_controller_app.components.view_components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.graphics.ColorUtils;

//TODO Dodac mozliwosc zmiany pozycji i rozmiaru

public class LEDComponent extends View {

    int LEDColor;
    int defaultColor;


    public LEDComponent(Context context) {
        super(context);
        LEDColor = Color.RED;
        defaultColor = LEDColor;
    }

    public LEDComponent(Context context, int color) {
        super(context);
        LEDColor = color;
        defaultColor = LEDColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLED(canvas);
    }

    void drawLED(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(LEDColor);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawOval(canvas.getClipBounds().left, canvas.getClipBounds().top, canvas.getClipBounds().right, canvas.getClipBounds().bottom,paint);
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


}
