package ivolianer.starve;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;


public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (isRunning() && shouldDrawGhost()) {
            // 平移重画
            canvas.translate(mMaxScroll, 0.0f);
            getLayout().draw(canvas, null, null, 0);
        }
        canvas.restore();
    }

    // 跑马中
    boolean running = false;

    public boolean isRunning() {
        return running;
    }

    // 该画幽灵部分了
    boolean shouldDrawGhost() {
        return isRunning() && getScrollX() > mGhostStart;
    }

    private float mMaxScroll;
    private float mGhostStart;

    public void start() {
        running = true;
        final int textWidth = getWidth();
        final float lineWidth = getLayout().getLineWidth(0);
        final float gap = textWidth / 3.0f;
        mGhostStart = lineWidth - textWidth + gap;
        mMaxScroll = lineWidth + gap;
        ValueAnimator animator = ValueAnimator.ofFloat(0, mMaxScroll);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (Float) animation.getAnimatedValue();
                setScrollX((int) f);
                if (1 == animation.getAnimatedFraction()) {
                    running = false;
                }
            }
        });
        animator.start();
    }

}
