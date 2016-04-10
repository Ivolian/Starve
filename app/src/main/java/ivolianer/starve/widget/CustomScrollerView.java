package ivolianer.starve.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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

    public PullLayout child;

    //
    float y;
    float dy;
    float distanceY;


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                 dy = ev.getRawY() - y;
                if (getScrollY()==0 && (dy>0 ||child.pull)){
                    Log.e("Result","child holder");

                    return child.onTouchEvent(ev);
                }

            case MotionEvent.ACTION_UP:
                if (getScrollY()==0 && (dy>0 ||child.pull)){
                    Log.e("Result","child holder");

                    return child.onTouchEvent(ev);
                }
                break;


        }


        y = ev.getRawY();

        return super.onTouchEvent(ev);
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
