package ivolianer.starve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;

import butterknife.Bind;
import butterknife.ButterKnife;
import ivolianer.starve.widget.MarqueeView;
import ivolianer.starve.widget.ZoomImageView;

public class MainActivity extends AppCompatActivity {


    // ======================== marquee ========================

    @Bind(R.id.marquee)
    MarqueeView marqueeView;

    private void initMarquee(String text) {
        marqueeView.setText(text);
    }


    // ======================== marquee ========================

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.search_view)
    ZoomImageView searchView;

    int y  = 0;
    RecyclerViewHeader header;
    private void initRecycleView() {


         header = (RecyclerViewHeader) findViewById(R.id.header);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
       header.attachTo(recyclerView);
        recyclerView.setAdapter(new CustomAdapter(new String[]{"123", "FDS", "123", "FDS"}));

    }

    // todo more better method to do this
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        final int distance = header.getHeight();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                y += dy         ;



                if (y < distance && dy < 0 && !searchView.isShow()
                        ) {searchView.show();
                }
                if (y > distance && dy > 0 && searchView.isShow()) {

                    searchView . hide();

                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        init();


    }


    private void init() {
        initMarquee("北京市东花市北里20号楼6单元501室");
        initRecycleView();
        //

    }


}
