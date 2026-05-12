package com.my.target.core.ui.views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.my.target.core.engines.b.a;
import com.my.target.core.ui.views.controls.IconButton;

public class AdTitle extends RelativeLayout implements OnClickListener {
    private TextView a;
    private String b = "";
    private final View c;
    private a d;

    public void setLabel(String str) {
        this.b = str;
        this.a.setText(str);
    }

    public AdTitle(Context context) {
        super(context);
        this.a = new TextView(context);
        this.a.setTextColor(-1);
        this.a.setTypeface(null, 1);
        this.a.setTextSize(2, CloseButton.TEXT_SIZE_SP);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = (int) TypedValue.applyDimension(1, CloseButton.STROKE_WIDTH, getContext().getResources().getDisplayMetrics());
        layoutParams.addRule(15);
        layoutParams.addRule(1, 256);
        addView(this.a, layoutParams);
        setBackgroundColor(-7829368);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (context.getResources().getDisplayMetrics().density + 0.5f));
        layoutParams2.addRule(12);
        this.c = new View(context);
        this.c.setBackgroundColor(-10066330);
        addView(this.c, layoutParams2);
        View iconButton = new IconButton(context);
        byte[] decode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAARNJREFUeNrs2osNgCAMhGFK3H/lGhcwKC30jmOD/1PkEc3d28mjt8OHAAQgAAEIQAACEMCO4ScDeBWEXuDJ+0kAXm069EJz3pkBRuKMFaBs/AqA0vHZAOXjMwEg4rMAYOIzAKDiowHg4iMBIOOjAGDjIwCg42cB4ONnACji/wLQxD/jSojfer7/+iB6QjzU6CfHr7oPoAAwvQGkCF9XAWNbBv98A0binBmACmFmFaBAmF0G4REi9gHQCFEbIViEyJ0gJEL0VhgOIeMsAIWQdRiCQcg8DUIgZB+HyyOsuA8ojbDqQqQswsobIQtAggZ4izT2KfAWS/uLzAgC7S8yUR9GaoAmAAEIQAACEIAABLBz3AIMAMj9LYGJKTxCAAAAAElFTkSuQmCC", 0);
        Options options = new Options();
        options.inDensity = 420;
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        iconButton.setBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length, options), Boolean.valueOf(false));
        iconButton.setId(256);
        iconButton.setOnClickListener(this);
        layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = (int) TypedValue.applyDimension(1, 5.0f, getContext().getResources().getDisplayMetrics());
        layoutParams2.addRule(15);
        layoutParams2.addRule(9);
        iconButton.setLayoutParams(layoutParams2);
        addView(iconButton);
    }

    public void setCloseClickListener(a aVar) {
        this.d = aVar;
    }

    public void setStripeColor(int i) {
        this.c.setBackgroundColor(i);
    }

    public void setMainColor(int i) {
        setBackgroundColor(i);
    }

    public void setTitleColor(int i) {
        this.a.setTextColor(i);
    }

    public void onClick(View view) {
        if (view.getId() == 256 && this.d != null) {
            this.d.onCloseClick();
        }
    }
}
