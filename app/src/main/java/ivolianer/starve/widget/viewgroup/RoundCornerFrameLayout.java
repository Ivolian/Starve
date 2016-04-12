package ivolianer.starve.widget.viewgroup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import com.zhy.android.percent.support.PercentFrameLayout;


public class RoundCornerFrameLayout extends PercentFrameLayout {

    public RoundCornerFrameLayout(Context context) {
        super(context);
    }

    public RoundCornerFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundCornerFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //

    private void init(){
        GradientDrawable background = new GradientDrawable();
        background.setCornerRadius(10);
        background.setColor(Color.WHITE);
        setBackground(background);
    }

}
