package ivolianer.starve;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/4/7.
 */
public class MyTextView2 extends TextView {

    public MyTextView2(Context context) {
        super(context);
    }

    public MyTextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //

    boolean running = false;

    public boolean isRunning() {
        return running;
    }


    Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        if (isRunning() && shouldDrawGhost()) {
            final float dx = getGhostOffset();
            canvas.translate(0.0f,dx );
            getLayout().draw(canvas, null, paint, 0);
        }

        canvas.restore();
    }


    float getGhostOffset() {
        return mGhostOffset;
    }


    boolean shouldDrawGhost() {
        return isRunning() && getScrollY() > mGhostStart;
    }

    private float mGhostOffset;
    private float mGhostStart;
    private float mMaxScroll;

    public void start() {
        running = true;
        final int textHeight = getHeight();

        final float lineWidth = getLayout().getLineWidth(0);

        final float gap = textHeight / 3.0f;
        mGhostStart = lineWidth - textHeight + gap;
        mMaxScroll = mGhostStart + textHeight;
        mGhostOffset = lineWidth + gap;


        ValueAnimator animator = ValueAnimator.ofFloat(0, mMaxScroll);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (Float) animation.getAnimatedValue();
                setScrollX((int) f);
            }
        });
        animator.start();
    }


}
