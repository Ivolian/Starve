package ivolianer.starve.widget.viewgroup;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;


public class PullLayout extends ViewGroup {

    public PullLayout(Context context) {
        super(context);
    }

    public PullLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 总偏移量
    int offset = 0;

    View backView;

    View frontView;

    @Override
    protected void onFinishInflate() {
        backView = getChildAt(0);
        frontView = getChildAt(1);
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 默认处理
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        // 实现高度 wrap_content
        int width = MeasureSpec.getSize(widthMeasureSpec);
        View child = getChildAt(1);
        int height = child.getMeasuredHeight();
        setMeasuredDimension(width, height + offset);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutBackView();
        layoutFrontView();
    }

    private void layoutBackView() {
        final int left = 0;
        final int top = 0;
        final int right = left + backView.getMeasuredWidth();
        final int bottom = top + backView.getMeasuredHeight();
        backView.layout(left, top, right, bottom);
    }

    private void layoutFrontView() {
        final int left = 0;
        final int top = offset;
        final int right = left + frontView.getMeasuredWidth();
        final int bottom = top + frontView.getMeasuredHeight();
        frontView.layout(left, top, right, bottom);
    }

    //

   public ScrollView scrollView;
    float lastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {

        // 执行动画过程，屏蔽所有事件
        if (animating) {
            return false;
        }
        boolean consumed = false;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 把事件分发下去，如果 ScrollView 不接受到 Down 事件，无法正确处理之后的事件
                consumed = frontView.dispatchTouchEvent(e);
                // 如果没消费，则消费
                if (!consumed) {
                    consumed = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // 滑动距离
                float dy = e.getY() - lastY;
                // 阻力
                dy = dy / 2;
                // 最精彩的地方，谁来处理滑动事件
                if (offset > 0 || dy > 0 && scrollView.getScrollY() == 0){
                    pull(dy);
                    consumed = true;
                }
                else {
                    consumed = frontView.dispatchTouchEvent(e);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (offset > 300) {
                    doYourLoadingAnimation();
                    consumed = true;
                } else if (offset > 0) {
                    clearOffset();
                    consumed = true;
                } else {
                    consumed = frontView.dispatchTouchEvent(e);
                }
                break;
        }
        lastY = e.getY();
        return consumed;
    }

    private void pull(float dy) {
        int newOffset = (int) (offset + dy);
        newOffset = checkOffsetRange(newOffset);
        changeOffset(newOffset);
    }

    private void changeOffset(int offset) {
        this.offset = offset;
        // 会导致 onLayout 的调用
        requestLayout();
    }

    private int checkOffsetRange(int newOffset) {
        newOffset = Math.min(400, newOffset);
        newOffset = Math.max(0, newOffset);
        return newOffset;
    }

    //

    boolean animating = false;

    private void doYourLoadingAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.9f, 1.1f, 0.9f, 1.1f, 0.9f, 1.1f, 0.9f, 1.1f, 1);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                float scale = (Float) animator.getAnimatedValue();
                backView.setScaleX(scale);
                if (1 == animator.getAnimatedFraction()) {
                    animating = false;
                    clearOffset();
                }
            }
        });
        animator.start();
        animating = true;
    }

    private void clearOffset() {
        ValueAnimator animator = ValueAnimator.ofInt(offset, 0);
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int currentOffset = (Integer) animator.getAnimatedValue();
                changeOffset(currentOffset);
                if (1 == animator.getAnimatedFraction()) {
                    animating = false;
                }
            }
        });
        animator.start();
        animating = true;
    }

}

