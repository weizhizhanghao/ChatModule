package com.example.think.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.think.activity.R;

/**
 * Created by HuangMei on 2016/10/26.
 */
public class SpinnerView extends FrameLayout{

    private TextView mTitleTextView;
    private ImageView mIndicatorImageView;

    private String[] mLabels;

    private PopupWindow mPopWin;
    private RadioGroup mRadioGroup;
    private RadioButton[] mRadioButton;

    private OnValueChangedListener mValueChangedListener;


    public SpinnerView(Context context) {
        super(context);
        init();
    }

    public SpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.spinner_xml, this, true);
        mTitleTextView = (TextView) findViewById(R.id.title);
        mIndicatorImageView = (ImageView) findViewById(R.id.indicator);
    }

    public void setItems(String[] labels){
        if (labels == null || labels.length == 0){
            return;
        }
        if(mPopWin != null){
            return;
        }

        mRadioGroup = new RadioGroup(getContext());
        mPopWin = new PopupWindow(mRadioGroup, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        mPopWin.setFocusable(true);
        mPopWin.setOutsideTouchable(true);
        mPopWin.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#20000000")));
        LayoutInflater inflater = LayoutInflater.from(getContext());
        mLabels = labels;
        mRadioButton = new RadioButton[labels.length];
        for(int i= 0; i < labels.length; i ++){
            RadioButton view = (RadioButton) inflater.inflate(R.layout.item_m_spinner, mRadioGroup, false);
            view.setText(labels[i]);
            view.setId(i);

            if(i == 0){
                mTitleTextView.setText(labels[i]);
                view.setChecked(true);
            } else {
                view.setChecked(false);
            }

            mRadioGroup.addView(view);
            mRadioButton[i] = view;
        }

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mTitleTextView.setText(mLabels[i]);
                mPopWin.dismiss();
                if(mValueChangedListener != null){
                    mValueChangedListener.onValueChanged(mLabels[i], i);
                }
            }
        });

        mRadioGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWin.dismiss();
            }
        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopWin == null)
                    return;
                if (mPopWin.isShowing()) {
                    mPopWin.dismiss();
                } else {
                    mPopWin.showAsDropDown(SpinnerView.this);
                }
            }
        });
    }

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener){
        mValueChangedListener = onValueChangedListener;
    }

    public void next(){
        int checkId = mRadioGroup.getCheckedRadioButtonId();
        if(checkId < mRadioButton.length - 1){
            mRadioButton[checkId + 1].setChecked(true);
        }
    }

    public boolean isLastedChecked(){
        return mRadioGroup.getCheckedRadioButtonId() == mRadioButton.length - 1;
    }

    public interface OnValueChangedListener{
        void onValueChanged(String value, int index);
    }
}
