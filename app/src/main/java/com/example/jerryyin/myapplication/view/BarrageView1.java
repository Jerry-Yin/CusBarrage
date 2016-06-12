package com.example.jerryyin.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by JerryYin on 6/12/16.
 * 自定义弹幕一
 * http://blog.csdn.net/xieyupeng520/article/details/49232925
 */
public class BarrageView1 extends TextView {

    private static final String TAG = "BarrageView1";
    private Paint mPaint = new Paint(); //画笔

    private int mPoX;   //X坐标
    private int mPoY;   //Y坐标
    private int mWindowWidth;   //屏幕宽度
    private int mWindowHeight;  //屏幕高度

    private RollThread mRollThread;

    public BarrageView1(Context context) {
        super(context);
        init();
    }

    public BarrageView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化方法
     */
    private void init(){
        //获取屏幕尺寸参数
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect); // 获取到当前视图的尺寸

        mWindowHeight = rect.height();
        mWindowWidth = rect.width();

        //设置X坐标为 宽度（因为从右往左滑动，初始化X坐标是最右边）
        mPoX = mWindowWidth;
        mPoY = (int) (getTextSize() + new Random().nextInt((int) (mWindowHeight / 4 - getTextSize())));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        mPaint.setColor(getCurrentTextColor());
        mPaint.setTextSize(30);
        canvas.drawText(String.valueOf(getText()), mPoX, mPoY, mPaint);

        Log.d(TAG, "TextSize = " + getTextSize());
        Log.d(TAG, "mWindowHeight = " + mWindowHeight);



        if (mRollThread == null){
            mRollThread = new RollThread();
            mRollThread.start();
        }
    }


    /**
     * 滚动动画线程
     */
    public class RollThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                animLogic();    //动画逻辑
                postInvalidate();   //重绘，该方法会自动调用onDraw()方法
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //是否需要关闭线程
                if (needStopThread()){
                    Log.i(TAG, getText() + "   -线程停止！");
//                    currentThread().stop();
                    break;
                }
            }
        }
    }

    /**
     * 用paint的measureText(String text)方法来得到文字的宽度，进行判断是否需要退出线程循环。
     * @return
     */
    private boolean needStopThread() {
        if (mPoX <= -mPaint.measureText(getText().toString())){
            return true;
        }
        return false;
    }

    /**
     * X坐标递减－－>左移
     */
    private void animLogic() {
        mPoX -= 2;
    }



}
