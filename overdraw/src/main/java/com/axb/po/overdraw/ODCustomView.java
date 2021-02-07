package com.axb.po.overdraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 自定义控件过度绘制
 */
public class ODCustomView extends FrameLayout {
    private int[] ids = new int[]{R.drawable.puke1,R.drawable.puke2,R.drawable.puke3,R.drawable.puke4,R.drawable.puke5,R.drawable.puke6};
    private Bitmap[] images = new Bitmap[6];
    private Paint paint;
    public ODCustomView(@NonNull Context context) {
        this(context,null);
    }

    public ODCustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ODCustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        for(int i=0;i<ids.length;i++){
            images[i]= BitmapFactory.decodeResource(getResources(),ids[i]);
        }

        paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //这样绘制，会导致中间几张扑克叠加的地方会有过度绘制
        //相邻两张牌间距40像素
//        for(int i=0;i<images.length;i++){
//            canvas.drawBitmap(images[i],i*40,0,paint);
//        }

        //解决过度绘制
        for(int i=0;i<images.length;i++){
            canvas.save();
            //裁剪左边40个像素的宽度绘制就可以了，这样就不存在叠加了
            if(i != images.length -1) {
                //最后一张不裁切
                canvas.clipRect(i * 40, 0, (i + 1) * 40, images[i].getHeight());
            }
            canvas.drawBitmap(images[i],i*40,0,paint);
            canvas.restore();
        }
    }
}
