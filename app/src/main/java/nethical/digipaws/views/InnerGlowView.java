package nethical.digipaws.views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class InnerGlowView extends GlowView {

    public InnerGlowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.bitmap = BitmapFactory.decodeResource(getResources(), nethical.digipaws.R.drawable.inner_glow);
        init();
    }
}
