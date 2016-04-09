package ivolianer.starve.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/4/9.
 */
public class MyImageView extends ImageView{

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), R.drawable.big,width,height);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.big);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();
//
////        setImageBitmap(bitmap);




    }



}
