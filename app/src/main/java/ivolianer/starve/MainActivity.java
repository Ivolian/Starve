package ivolianer.starve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import ivolianer.starve.widget.ElasticSearchIconView;
import ivolianer.starve.widget.MarqueeView;
import ivolianer.starve.widget.MyScrollerView;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.marquee)
    MarqueeView marqueeView;

    @Bind(R.id.search_view)
    ElasticSearchIconView searchView;

    @Bind(R.id.scrollView)
    MyScrollerView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();


        scrollView.setOnScrollListener(new MyScrollerView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY, int direction) {

                if (scrollY < 200 && direction < 0 && !searchView.isShow()) {
                    searchView.show();
                }
                if (scrollY > 200 && direction > 0 && searchView.isShow()) {

                    searchView.hide();

                }
//                Log.e("result", "" + scrollY);
            }
        });
    }




    private void init() {
        marqueeView.setText("叶马德是我儿子 叶马德是我儿子 叶马德是我儿子 叶马德是我儿子 叶马德是我儿子 叶马德是我儿子");


        //

    }


}
