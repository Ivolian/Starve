package ivolianer.starve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhy.android.percent.support.PercentFrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import ivolianer.starve.widget.CustomScrollerView;
import ivolianer.starve.widget.MarqueeView;
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

    private void initScrollerView() {
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
