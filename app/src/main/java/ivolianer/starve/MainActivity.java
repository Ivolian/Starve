package ivolianer.starve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhy.android.percent.support.PercentFrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import ivolianer.starve.widget.CustomScrollerView;
import ivolianer.starve.widget.MarqueeView;
import ivolianer.starve.widget.PullLayout;
import ivolianer.starve.widget.ZoomImageView;

public class MainActivity extends AppCompatActivity {


    // ======================== marquee ========================

    @Bind(R.id.marquee)
    MarqueeView marqueeView;

    private void initMarquee(String text) {
        marqueeView.setText(text);
    }


    // ======================== scrollView ========================

    @Bind(R.id.searchIcon)
    ZoomImageView searchIcon;

    @Bind(R.id.searchView)
    PercentFrameLayout searchView;

    @Bind(R.id.scrollView)
    CustomScrollerView scrollView;

@Bind(R.id.child)
PullLayout pullLayout;



    private void initScrollerView() {
       scrollView.child = pullLayout;
        pullLayout.box = findViewById(R.id.box);
        pullLayout.good1 = findViewById(R.id.good1);
        pullLayout.good2 = findViewById(R.id.good2);
        pullLayout.good3 = findViewById(R.id.good3);
        pullLayout.good4 = findViewById(R.id.good4);
        pullLayout.good5 = findViewById(R.id.good5);
        pullLayout.good6 = findViewById(R.id.good6);

        scrollView.setOnScrollListener(new CustomScrollerView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY, int direction) {
                if (!searchIcon.isShow() && direction > 0 && scrollY > searchView.getHeight()) {
                    searchIcon.show();
                }
                if (searchIcon.isShow() && direction < 0 && scrollY < searchView.getHeight()) {
                    searchIcon.hide();
                }
            }
        });
    }


    // ======================== onCreate ========================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initMarquee("北京市东花市北里20号楼6单元501室");
        initScrollerView();
    }


}
