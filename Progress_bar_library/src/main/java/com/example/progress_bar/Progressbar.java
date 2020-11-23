package com.example.progress_bar;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Progressbar extends RelativeLayout {

    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private ImageView imageView;
    private Drawable imageFile;
    private View view, Progressbar;
    private TextView textMsg;
    private TextView loading;
    private int textColor, enlarge;
    private float textSize;



    public Progressbar(Context context) {
        super(context);
        initView();
    }


    public Progressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        this.attrs=attrs;
        initView();
    }

    public Progressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        this.attrs=attrs;
        this.styleAttr=defStyleAttr;
        initView();
    }

    public void initView(){
        this.view = this;
        inflate(mContext, R.layout.progressbar, this);

        TypedArray arr = mContext.obtainStyledAttributes(attrs, R.styleable.Progressbar, styleAttr,0);

        imageFile = arr.getDrawable(R.styleable.Progressbar_barImg);
        textColor = arr.getColor(R.styleable.Progressbar_androidtextColor, Color.BLACK);
        textSize = arr.getDimension(R.styleable.Progressbar_textSize, 16);
        enlarge = arr.getInt(R.styleable.Progressbar_enlarge,4);
        imageView = findViewById(R.id.progressImg);
        Progressbar = findViewById(R.id.progress_bg);
        textMsg = findViewById(R.id.text_msg);
        loading = findViewById(R.id.loading);


        if (imageFile!=null){
            setProgressVector(imageFile);
        }



        setTextColor(textColor);
        setTextSize(textSize);
        enlarge(enlarge);

        arr.recycle();
    }

    public void setScaleType(ImageView.ScaleType scaleType){
        imageView.setScaleType(scaleType);
    }

    public void setProgressVector(Drawable imageFile){
        Glide
                .with(mContext)
                .load(imageFile)
                .into(imageView);
    }

    public void enlarge(int enlarge){
        if (enlarge>=1 && enlarge<=10 )
            imageView.getLayoutParams().height = enlarge*100;
    }


    public void setLoading(String load){
        loading.setText(load);
    }

    public void setTextColor(int color){
        textMsg.setTextColor(color);
        loading.setTextColor(color);
    }

    public void setTextSize(float size){
        textMsg.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        loading.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }



}
