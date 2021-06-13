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
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MyConstrainLayout extends ConstraintLayout {

    ArrayList<Pair<PointF, PointF>> listOfLine = new ArrayList<>();

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

        if(listOfLine!=null){
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(8);
            for(Pair<PointF, PointF> p: listOfLine) {
                canvas.drawLine(p.first.x, p.first.y, p.second.x, p.second.y, paint);
            }
        }

    }

    public PointF getCentral(View view){
        return new PointF(view.getX()+view.getWidth()/2.f, view.getY()+view.getHeight()/2.f);
    }

    public int getCentralX(View v){
        return (int) v.getX()+v.getWidth()/2;
    }

    public int getCentralY(View v){
        return (int) v.getY()+v.getHeight()/2;
    }


    public boolean drawLineX(View view){
        boolean drew = false;
        for(int i=0; i<getChildCount(); ++i){
            View v = getChildAt(i);
            if(!v.equals(view)){
                if(getCentralX(view) == getCentralX(v)){
                    drew = true;
                    listOfLine.add(Pair.create(getCentral(view), getCentral(v)));
                }
            }
        }
        invalidate();
        return drew;
    }


    public boolean drawLineY(View view){
        boolean drew = false;
        for(int i=0; i<getChildCount(); ++i){
            View v = getChildAt(i);
            if(!v.equals(view)){
                if(getCentralY(view) == getCentralY(v)){
                    drew = true;
                    listOfLine.add(Pair.create(getCentral(view), getCentral(v)));
                }
            }
        }
        invalidate();
        return drew;
    }

    public void removeLine(){
        listOfLine = new ArrayList<>();
        invalidate();
    }
}
