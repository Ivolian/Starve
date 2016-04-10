package ivolianer.starve.widget;

import android.content.Context;
import android.util.AttributeSet;
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
