package com.example.gerasimov.graphtheory;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import java.lang.Math;

public class GraphView extends android.support.v7.widget.AppCompatImageView {
    private boolean drawCustomCanvas = false;
    private Paint mPaint;
    public GraphView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public GraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }
    public void setDrawCustomCanvas(boolean drawCustomCanvas)
    {
        this.drawCustomCanvas = drawCustomCanvas;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(40);
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        double width, height, angle, radius, x,y;
        int countNodes;
        Paint paint = new Paint();
        width = getWidth()/2;
        height = getHeight()/2 - 150;
        radius = width - width/5 + 20;
        int[][] matrix = Main.getMatrix();
        countNodes = matrix.length;
        float[][] nodes =new float[countNodes][2];

        if(!drawCustomCanvas)
        {super.onDraw(canvas);}
        else{
            for(int i = 0; i < countNodes; i ++){
                angle = 2*Math.PI*(i+1)/countNodes;
                x = radius*Math.cos(angle) + width;
                y = radius*Math.sin(angle) + height;
                nodes[i][0] = (float)x;
                nodes[i][1] = (float)y;
            }
            paint.setColor(Color.parseColor("#000000"));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);

            for(int i = 0; i < countNodes; i++){
                for(int j = 0; j < countNodes; j++){
                    if(matrix[i][j] != 0 ) {
                        if (Main.digraph) {
                            float beta = (float) Math.atan2(nodes[j][1] - nodes[i][1], nodes[j][0] - nodes[i][0]);
                            float alfa = (float) Math.PI / 6;
                            float a = (float) (55 * Math.sin(beta));
                            float b = (float) (55 * Math.cos(beta));
                            int lengthArrow = 50;
                            nodes[i][0] += b;
                            nodes[i][1] += a;
                            nodes[j][0] -= b;
                            nodes[j][1] -= a;
                            float x2 = (float) (nodes[j][0] - lengthArrow * Math.cos(alfa + beta));//draw arrows
                            float y2 = (float) (nodes[j][1] - lengthArrow * Math.sin(alfa + beta));
                            canvas.drawLine(x2, y2, nodes[j][0], nodes[j][1], paint);
                            x2 = (float) (nodes[j][0] - lengthArrow * Math.cos(alfa - beta));
                            y2 = (float) (nodes[j][1] + lengthArrow * Math.sin(alfa - beta));
                            canvas.drawLine(x2, y2, nodes[j][0], nodes[j][1], paint);
                            canvas.drawLine(nodes[i][0], nodes[i][1], nodes[j][0], nodes[j][1], paint);
                            nodes[i][0] -= b;
                            nodes[i][1] -= a;
                            nodes[j][0] += b;
                            nodes[j][1] += a;
                        }
                        else canvas.drawLine(nodes[i][0], nodes[i][1], nodes[j][0], nodes[j][1], paint);
                    }
                }
            }

            for(int i = 0; i< countNodes; i++) {
                paint.setColor(Color.parseColor("#2563ba"));
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(nodes[i][0], nodes[i][1], 55, paint);
                paint.setColor(Color.parseColor("#000000"));
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(3);
                canvas.drawCircle(nodes[i][0], nodes[i][1], 55, paint);

                paint.setAntiAlias(true);
                paint.setTextSize(70);
                if(i+1 <= 9) {
                    canvas.drawText(Integer.toString(i + 1), nodes[i][0] + paint.ascent() / 3.0F, nodes[i][1] - paint.ascent() / 2.0F, paint);
                }
                else{
                    canvas.drawText(Integer.toString(i + 1), nodes[i][0] + paint.ascent()/1.5F  , nodes[i][1] - paint.ascent() / 2.0F, paint);
                }
            }
        }
    }
}