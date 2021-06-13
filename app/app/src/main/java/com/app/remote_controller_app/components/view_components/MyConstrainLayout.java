package com.app.remote_controller_app.components.view_components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MyConstrainLayout extends ConstraintLayout {

    boolean line = false;
    PointF a;
    PointF b;

    public MyConstrainLayout(@NonNull Context context) {
        super(context);
    }

    public MyConstrainLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyConstrainLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyConstrainLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if(line) {
            Log.v("CANVAS", "RYSUJ LINIE");
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(8);
            canvas.drawLine(a.x, a.y, b.x, b.y, paint);
        }


    }

    public PointF getCentral(View view){
        return new PointF(view.getX()+view.getWidth()/2, view.getY()+view.getHeight()/2);
    }


    public boolean drawLine(View view){
        for(int i=0; i<getChildCount(); ++i){
            View v = getChildAt(i);
            if(view.getX() == v.getX() && !v.equals(view)){
                line = true;
                this.a = getCentral(view);
                this.b = getCentral(v);
                invalidate();
                return true;
            }
            line=false;
            removeLine();
        }
        return false;
    }

    public void removeLine(){
        line = false;
        invalidate();
    }
}
