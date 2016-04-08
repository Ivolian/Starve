package ivolianer.starve;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.Bind;
import butterknife.ButterKnife;
import ivolianer.starve.widget.MyScrollerView;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.search_view)
    View searchView;

    ScaleDrawable searchViewBackground;

    @Bind(R.id.scrollView)
    MyScrollerView scrollView;

    boolean running = false;
    boolean show = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();



        scrollView.setOnScrollListener(new MyScrollerView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY,int direction) {

            if (scrollY < 200 &&direction<0 && !show) {
                startAnim();
            }
            if (scrollY > 200 && direction>0 && show) {

                stopAnim();

            }
//                Log.e("result", "" + scrollY);
            }
        });
    }

    private void startAnim(){

        ValueAnimator animator = ValueAnimator.ofInt(0,3500,2500,3000);
        animator.setDuration(800);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int level = (int)((Integer)animation.getAnimatedValue());

                searchViewBackground.setLevel(level);
                if (animation.getAnimatedFraction()==1){
                    Log.e("result", "" + false);
                    running =false;
                }
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                running =false;


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
        running = true;
        show = true;
        Log.e("result", "" + true);

    }

    private void stopAnim(){
        running = true;

        ValueAnimator animator = ValueAnimator.ofInt(3000,0);
        animator.setDuration(800);

        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                running =false;

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int level = (int)((Integer)animation.getAnimatedValue());
                searchViewBackground.setLevel(level);


            }
        });
        animator.start();
        Log.e("result", "" + true);
        show = false;

    }


    private void init() {
        Drawable drawable = new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_search)
                .color(Color.WHITE)
                .sizePx(100);
        searchViewBackground = new ScaleDrawable(drawable, Gravity.CENTER, 1, 1);
        searchViewBackground.setLevel(3000);
        searchView.setBackground(searchViewBackground);

        //


    }


}
