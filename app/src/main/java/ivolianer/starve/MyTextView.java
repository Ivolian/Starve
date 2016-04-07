package ivolianer.starve;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/4/7.
 */
public class MyTextView extends TextView {
    @Override
    protected float getLeftFadingEdgeStrength() {
        return 0;
    }

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
    public boolean isFocused() {
        return true;
    }
}
