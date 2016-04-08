package ivolianer.starve.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;


public class MyScrollerView extends ScrollView {

    public MyScrollerView(Context context) {
        super(context);
    }

    public MyScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //

    public interface OnScrollListener {
        void onScroll(int scrollY, int direction);
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

}
