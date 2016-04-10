package ivolianer.starve.widget;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/4/10.
 */
public class PullLayout extends FrameLayout {


    public View box;
    public View good1;
    public View good2;
    public View good3;
    public View good4;
    public View good5;
    public View good6;
    public PullLayout(Context context) {
        super(context);
    }

    public PullLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    float lastY;

    public  boolean pull =false;



    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                float dy = ev.getRawY() - lastY;
                dy = dy/3;
                float newY = getY() + dy;
                        if (newY<=0){
                            newY = 0;
                            pull = false;
                        }else {
                            pull = true;
                        }

                if (newY > 300){
                    newY = 300;
                }


                    Log.e("result",""+newY);

                setY(newY);

                break;
            case MotionEvent.ACTION_UP:


                if (getY() != 0) {
                    doAnim();
                    pull = false;
                }
                break;
        }
        lastY = ev.getRawY();
        return true;
    }

    //

    private void doAnim(){


        ValueAnimator animator = ValueAnimator.ofFloat(0.9f,1.1f,0.9f,1.1f,0.9f,1.1f,0.9f,1.1f);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                box.setScaleY(value);
                if (1== animation.getAnimatedFraction()){
                    hidePull();
                }
            }

        });

        animator.start();

        doAnimForFruit(good1,1);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                doAnimForFruit(good2,-1);
            }
        },300);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                doAnimForFruit(good3,1);
            }
        },600);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                doAnimForFruit(good4,-1);
            }
        },900);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                doAnimForFruit(good5,1);
            }
        },1200);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                doAnimForFruit(good6,-1);
            }
        },1500);

    }

    private void doAnimForFruit(final View fruit,int direction){
        final float oldX = fruit.getX();
        final float oldY = fruit.getY();

        PropertyValuesHolder propertyValuesHolderX = PropertyValuesHolder.ofFloat("x",oldX,oldX+140*direction);
        PropertyValuesHolder propertyValuesHolderY = PropertyValuesHolder.ofFloat("y",oldY,oldY-120,oldY-40);

        final ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(propertyValuesHolderX,propertyValuesHolderY);
        animator.setDuration(700);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fruit.setVisibility(VISIBLE);
                float x = (Float) animation.getAnimatedValue("x");
                fruit.setX(x);
                float y = (Float) animation.getAnimatedValue("y");
                fruit.setY(y);
                if (1== animator.getAnimatedFraction()){
                    fruit.setVisibility(INVISIBLE);
                    fruit.setX(oldX);
                    fruit.setY(oldY);
                }
            }

        });
        animator.start();
    }

    private void hidePull(){
        ValueAnimator animator = ValueAnimator.ofFloat(getY(),0);
        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                setY(value);
                if (1== animation.getAnimatedFraction()){

            }
            }

        });
        animator.start();
    }

}
