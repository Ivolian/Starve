package ivolianer.starve.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


public class MarqueeView extends TextView {

    public MarqueeView(Context context) {
        super(context);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //

    @Override
    protected float getLeftFadingEdgeStrength() {
        return 0;
    }

    @Override
    protected float getRightFadingEdgeStrength() {
        return 0;
    }

    @Override
    public boolean isFocused() {
        return true;
    }

}
