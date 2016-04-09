package ivolianer.starve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import ivolianer.starve.widget.MarqueeView;
import ivolianer.starve.widget.MyScrollerView;
import ivolianer.starve.widget.ZoomImageView;

public class MainActivity extends AppCompatActivity {


    // ======================== marquee ========================

    @Bind(R.id.marquee)
    MarqueeView marqueeView;

    private void initMarquee(String text){
        marqueeView.setText(text);
    }


    // ======================== marquee ========================



    @Bind(R.id.search_view)
    ZoomImageView searchView;

    @Bind(R.id.scrollView)
    MyScrollerView scrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        init();


        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });






        scrollView.setOnScrollListener(new MyScrollerView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY, int direction) {

                if (scrollY < 200 && direction < 0 && !searchView.isShow()
                        ) {searchView.show();
                }
                if (scrollY > 200 && direction > 0 && searchView.isShow()) {

                  searchView . hide();

                }
//                Log.e("result", "" + scrollY);
            }
        });
    }




    private void init() {
        initMarquee("北京市东花市北里20号楼6单元501室");

        //

    }


}
