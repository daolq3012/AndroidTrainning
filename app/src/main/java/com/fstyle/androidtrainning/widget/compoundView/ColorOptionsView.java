package com.fstyle.androidtrainning.widget.compoundView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fstyle.androidtrainning.R;

/**
 * Created by DaoLQ on 20/04/2017.
 */

public class ColorOptionsView extends LinearLayout {
    public ColorOptionsView(Context context) {
        this(context, null);
    }

    public ColorOptionsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorOptionsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttribute(context, attrs);
    }

    private void setAttribute(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorOptionsView, 0, 0);
        String titleText = typedArray.getString(R.styleable.ColorOptionsView_titleText);
        @SuppressWarnings("ResourceAsColor") int valueColor =
                typedArray.getColor(R.styleable.ColorOptionsView_valueColor,
                        android.R.color.holo_blue_light);
        typedArray.recycle();

        final View view =
                LayoutInflater.from(context).inflate(R.layout.view_color_options, this, true);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(titleText);
        View viewRect = view.findViewById(R.id.view_rect);
        viewRect.setBackgroundColor(valueColor);
    }
}
