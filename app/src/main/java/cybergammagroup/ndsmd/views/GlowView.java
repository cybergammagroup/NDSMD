package cybergammagroup.ndsmd.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GlowView extends View {

    Bitmap bitmap;
    int width, height;
    float percentage;
    int bitHeight, bitWidth;
    int color;


    public GlowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    protected void init(){
        bitHeight = bitmap.getHeight();
        bitWidth = bitmap.getWidth();
        percentage = 0.0f;
        color = 0;
    }

    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect srcRect = new Rect(0, bitHeight - (int) (bitHeight * percentage), bitWidth, bitHeight);
        Rect dstRect = new Rect(0, 0, width, height);

        Paint paint = new Paint();

        paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));

        canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
    }

    public void setColour(int color){
        this.color = color;
    }

    public void setHeight(float percentage, int height){
        this.percentage = percentage;
        this.getLayoutParams().height = (int) (height * percentage);
        this.setLayoutParams(this.getLayoutParams());
    }
}
