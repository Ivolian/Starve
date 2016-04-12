package ivolianer.starve.widget.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;


public class CustomScrollerView extends ScrollView {

    public CustomScrollerView(Context context) {
        super(context);
    }

    public CustomScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //

    public PullLayout pullLayout;

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        boolean consume = false;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pullLayout.dispatchTouchEvent(e);
                consume = super.onTouchEvent(e);
                break;
            case MotionEvent.ACTION_MOVE:
                consume = pullLayout.dispatchTouchEvent(e);
                if (!consume) {
                    consume = super.onTouchEvent(e);
                }
                break;
            case MotionEvent.ACTION_UP:
                consume = pullLayout.dispatchTouchEvent(e);
                if (!consume) {
                    consume = super.onTouchEvent(e);
                }
                break;
        }
        return consume;
    }

    //

    private OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.onScroll(t, t - oldt);
        }
    }

//

    public interface OnScrollListener {
        void onScroll(int scrollY, int direction);
    }

}
