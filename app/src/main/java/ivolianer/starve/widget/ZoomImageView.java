package ivolianer.starve.widget;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


public class ZoomImageView extends ImageView {

    public ZoomImageView(Context context) {
        super(context);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //

    final String SCALE = "scale";
    final String ALPHA = "alpha";
    boolean show = true;

    public boolean isShow() {
        return show;
    }

    public void show() {
        PropertyValuesHolder propertyValuesHolderS = PropertyValuesHolder.ofFloat(SCALE, 0, 1.2f, 0.8f, 1);
        PropertyValuesHolder propertyValuesHolderA = PropertyValuesHolder.ofInt(ALPHA, 0, 255);
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(propertyValuesHolderS, propertyValuesHolderA);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                updateScaleAndAlpha(animator);
            }
        });
        animator.start();
        show = true;
    }

    public void hide() {
        PropertyValuesHolder propertyValuesHolderS = PropertyValuesHolder.ofFloat(SCALE, 1, 0);
        PropertyValuesHolder propertyValuesHolderA = PropertyValuesHolder.ofInt(ALPHA, 255, 0);
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(propertyValuesHolderS, propertyValuesHolderA);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                updateScaleAndAlpha(animator);
            }
        });
        animator.start();
        show = false;
    }

    private void updateScaleAndAlpha(ValueAnimator animator) {
        float scale = ((Float) animator.getAnimatedValue(SCALE));
        setScaleX(scale);
        setScaleY(scale);
        int alpha = ((Integer) animator.getAnimatedValue(ALPHA));
        setImageAlpha(alpha);
    }

}
