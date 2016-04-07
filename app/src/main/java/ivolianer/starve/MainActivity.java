package ivolianer.starve;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

         textView = (TextView) findViewById(R.id.textview);



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        doSth();
            }
        });


    }


    private void doSth(){
        ValueAnimator animator =ValueAnimator.ofFloat(0,200);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (Float)animation.getAnimatedValue();
                textView.setScrollX((int )f);
            }
        });
        animator.start();
    }

}
