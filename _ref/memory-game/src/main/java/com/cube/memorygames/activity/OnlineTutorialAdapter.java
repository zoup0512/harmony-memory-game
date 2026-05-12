package com.cube.memorygames.activity;

import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.memory.brain.training.games.R;
import java.util.ArrayList;
import java.util.List;

public class OnlineTutorialAdapter extends PagerAdapter {
    private List<Integer> items = new ArrayList();
    private List<Integer> titles;
    private Typeface typeface;

    public OnlineTutorialAdapter(Typeface typeface) {
        this.typeface = typeface;
        this.items.add(Integer.valueOf(R.layout.online_tutorial_1));
        this.items.add(Integer.valueOf(R.layout.online_tutorial_2));
        this.items.add(Integer.valueOf(R.layout.online_tutorial_3));
        this.items.add(Integer.valueOf(R.layout.online_tutorial_4));
        this.items.add(Integer.valueOf(R.layout.online_tutorial_5));
        this.titles = new ArrayList();
        this.titles.add(Integer.valueOf(R.string.online_tutorial_bets));
        this.titles.add(Integer.valueOf(R.string.online_tutorial_bets));
        this.titles.add(Integer.valueOf(R.string.online_tutorial_bets));
        this.titles.add(Integer.valueOf(R.string.online_tutorial_rating));
        this.titles.add(Integer.valueOf(R.string.online_tutorial_rating));
    }

    public int getTitle(int position) {
        return ((Integer) this.titles.get(position)).intValue();
    }

    public int getCount() {
        return this.items.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View item_view = LayoutInflater.from(container.getContext()).inflate(((Integer) this.items.get(position)).intValue(), container, false);
        TextView textView = (TextView) item_view.findViewById(R.id.text);
        if (textView != null) {
            textView.setTypeface(this.typeface);
            if (position == 0) {
                textView.setText(Html.fromHtml(container.getContext().getString(R.string.online_tutorial_1)));
            }
        }
        TextView rating = (TextView) item_view.findViewById(R.id.rating);
        if (rating != null) {
            rating.setTypeface(this.typeface);
        }
        container.addView(item_view);
        return item_view;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
