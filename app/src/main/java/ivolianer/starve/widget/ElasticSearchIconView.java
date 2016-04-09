package ivolianer.starve.widget;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import ivolianer.starve.R;


public class ElasticSearchIconView extends View {

    public ElasticSearchIconView(Context context) {
        super(context);
        init();
    }

    public ElasticSearchIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ElasticSearchIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //

    boolean show;

    public boolean isShow() {
        return show;
    }

    //

    private final int DURATION = 800;

    private final int DEFAULT_LEVEL = 3500;

    private final int LEVEL_DIFF = 500;

    //

    private ScaleDrawable scaleDrawable;

    private void init() {
        scaleDrawable = new ScaleDrawable(createSearchDrawable(), Gravity.CENTER, 1, 1);
        scaleDrawable.setLevel(DEFAULT_LEVEL);
        setBackground(scaleDrawable);
        show = true;
    }

    private Drawable createSearchDrawable() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.search_icon);
        return new BitmapDrawable(getResources(), bitmap);
    }

    //

    private final String LEVEL = "level";

    private final String ALPHA = "alpha";

    public void show() {
        PropertyValuesHolder propertyValuesHolderL = PropertyValuesHolder.ofInt(LEVEL, 0, DEFAULT_LEVEL + LEVEL_DIFF, DEFAULT_LEVEL - LEVEL_DIFF, DEFAULT_LEVEL);
        PropertyValuesHolder propertyValuesHolderA = PropertyValuesHolder.ofInt(ALPHA, 0, 255);
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(propertyValuesHolderL, propertyValuesHolderA);
        animator.setDuration(DURATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int level = ((Integer) animation.getAnimatedValue(LEVEL));
                scaleDrawable.setLevel(level);
                int alpha = ((Integer) animation.getAnimatedValue(ALPHA));
                scaleDrawable.setAlpha(alpha);
            }
        });
        animator.start();
        show = true;
    }

    public void hide() {
        PropertyValuesHolder propertyValuesHolderL = PropertyValuesHolder.ofInt(LEVEL, DEFAULT_LEVEL, 0);
        PropertyValuesHolder propertyValuesHolderA = PropertyValuesHolder.ofInt(ALPHA, 255, 0);
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(propertyValuesHolderL, propertyValuesHolderA);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int level = ((Integer) animation.getAnimatedValue(LEVEL));
                scaleDrawable.setLevel(level);
                int alpha = ((Integer) animation.getAnimatedValue(ALPHA));
                scaleDrawable.setAlpha(alpha);
            }
        });
        animator.start();
        show = false;
    }

}
