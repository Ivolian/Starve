package ivolianer.starve.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class RoundCornerFrameLayout extends FrameLayout {

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
