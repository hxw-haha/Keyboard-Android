package com.hxw.keyboard.keyboard;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hxw.keyboard.R;
import com.hxw.keyboard.utils.ResUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>文件描述：键盘base</p>
 * <p>作者：hanxw</p>
 * <p>创建时间：2021/3/29</p>
 * <p>更改时间：2021/3/29</p>
 * <p>版本号：1</p>
 */
public abstract class BaseKeyboardLayout extends LinearLayout {
    /**
     * 回调监听器
     */
    protected IKeyBoardCall mKeyBoardCall;
    /**
     * 是否实时回调
     * 默认：false
     */
    protected boolean isThenCall = false;
    /**
     * 是否数字随机，是否字母随机
     */
    protected boolean isNumberRandom = false, isLetterRandom = false;
    /**
     * 最大长度
     */
    protected int mMaxLength = Integer.MAX_VALUE;
    /**
     * 是否金额键盘、是否证件键盘、是否手机号键盘
     */
    protected boolean isMoneyKeyboard = false, isIdNoKeyboard = false, isPhoneKeyboard = false;
    /**
     * 金额小数据后几位
     */
    private int mMoneyPointLength = Constant.INSTANCE.getDefaultMoneyPointMaxLength();

    private LayoutParams mRowParams;

    protected final List<String> mKeyboardNumberList, mKeyboardLetterList, mKeyboardSignList;

    protected String mValue = "";

    /**
     * 设置是否金额键盘
     *
     * @param moneyPointSize 设置金额小数据后几位
     */
    public void setMoneyPointLength(int moneyPointSize) {
        this.mMoneyPointLength = moneyPointSize;
        isIdNoKeyboard = false;
        isPhoneKeyboard = false;

        isMoneyKeyboard = true;
    }

    /**
     * 设置是否证件键盘
     */
    public void setIdNoKeyboard() {
        isMoneyKeyboard = false;
        isPhoneKeyboard = false;

        isIdNoKeyboard = true;
        setMaxLength(18);
    }

    /**
     * 设置是否手机号键盘
     *
     * @return
     */
    public void setPhoneKeyboard() {
        isMoneyKeyboard = false;
        isIdNoKeyboard = false;

        isPhoneKeyboard = true;
        setMaxLength(11);
    }

    public void setDefaultValue(String mValue) {
        this.mValue = mValue;
    }

    /**
     * 设置是否实时回调
     *
     * @param thenCall
     */
    public void setThenCall(boolean thenCall) {
        isThenCall = thenCall;
    }

    /**
     * 是否回调事件
     *
     * @param mKeyBoardCall
     */
    public void setKeyBoardCall(IKeyBoardCall mKeyBoardCall) {
        this.mKeyBoardCall = mKeyBoardCall;
    }

    /**
     * 设置数字是否随机
     *
     * @param numberRandom
     */
    public void setNumberRandom(boolean numberRandom) {
        isNumberRandom = numberRandom;
    }

    /**
     * 设置字母是否随机
     *
     * @param letterRandom
     */
    public void setLetterRandom(boolean letterRandom) {
        isLetterRandom = letterRandom;
    }

    /**
     * 设置输入最大值
     *
     * @param mMaxLength
     */
    public void setMaxLength(int mMaxLength) {
        this.mMaxLength = mMaxLength;
    }

    /**
     * 获取输入值
     *
     * @return
     */
    public String getValue() {
        return mValue;
    }

    public BaseKeyboardLayout(Context context) {
        this(context, null);
    }

    public BaseKeyboardLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseKeyboardLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
        setOrientation(LinearLayout.VERTICAL);
        mKeyboardNumberList = new ArrayList<>(Constant.INSTANCE.getKeyboardNumberList());
        mKeyboardLetterList = new ArrayList<>();
        mKeyboardSignList = new ArrayList<>(Constant.INSTANCE.getKeyboardSignList());
    }

    protected abstract void initViews();

    /**
     * 构建显示页面-必须调用
     */
    public void build() {
        if (isNumberRandom) {
            Collections.shuffle(mKeyboardNumberList);
        }
        initViews();
    }

    /**
     * 添加一行 view
     *
     * @param values 一行显示的数据
     */
    public void addRowView(@NonNull List<String> values, float weightSum) {
        final LinearLayout rowLayout = generatedRow(weightSum);
        for (String value : values) {
            rowLayout.addView(generatedItemTextView(value));
        }
        addView(rowLayout);
    }


    public LinearLayout generatedRow(float weightSum) {
        return generatedRow(weightSum, Gravity.CENTER);
    }

    public LinearLayout generatedRow(float weightSum, int gravity) {
        final LinearLayout rowLayout = new LinearLayout(getContext());
        rowLayout.setOrientation(LinearLayout.HORIZONTAL);
        rowLayout.setGravity(gravity);
        rowLayout.setWeightSum(weightSum);
        rowLayout.setLayoutParams(generatedRowParams());
        return rowLayout;
    }

    public LayoutParams generatedRowParams() {
        return generatedRowParams(Gravity.CENTER);
    }

    public LayoutParams generatedRowParams(int gravity) {
        if (mRowParams == null) {
            mRowParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
            );
            mRowParams.gravity = gravity;
        }
        return mRowParams;
    }


    public TextView generatedItemTextView(String value) {
        return generatedItemTextView(value, R.drawable.selector_keyboard);
    }

    public TextView generatedItemTextView(String value, @DrawableRes int backgroundResource) {
        return generatedItemTextView(value, backgroundResource, 1);
    }


    public TextView generatedItemTextView(String value, @DrawableRes int backgroundResource, float weight) {
        final TextView item = new TextView(getContext());
        item.setTextSize(18);
        item.setTextColor(ResUtil.INSTANCE.getColor(R.color.color_000));
        item.setText(value);
        item.setGravity(Gravity.CENTER);
        item.setBackgroundResource(backgroundResource);
        item.setLayoutParams(generatedItemParams(weight));
        item.setOnClickListener(v -> this.onCall(value));
        return item;
    }

    public LayoutParams generatedItemParams() {
        return generatedItemParams(1);
    }

    public LayoutParams generatedItemParams(float weight) {
        final LayoutParams params = new LayoutParams(0,
                Constant.INSTANCE.getItemHeight());
        params.setMargins(Constant.INSTANCE.getItemMarginSize(), Constant.INSTANCE.getItemMarginSize(),
                Constant.INSTANCE.getItemMarginSize(), Constant.INSTANCE.getItemMarginSize());
        params.gravity = Gravity.CENTER;
        params.weight = weight;
        return params;
    }

    public ImageView generatedActionView(String value, @DrawableRes int backgroundResource) {
        return generatedActionView(value, backgroundResource, 1);
    }

    public ImageView generatedActionView(String value, @DrawableRes int backgroundResource, float weight) {
        final ImageView action = new ImageView(getContext());
        action.setBackgroundResource(backgroundResource);
        action.setLayoutParams(generatedItemParams(weight));
        action.setOnClickListener(v -> onCall(value));
        return action;
    }

    @CallSuper
    protected void onCall(String value) {
        if (Constant.INSTANCE.getBlankFlag().equalsIgnoreCase(value)) {
            return;
        }
        if (Constant.INSTANCE.getClearFlag().equalsIgnoreCase(value)) {
            if (TextUtils.isEmpty(mValue) || mValue.length() == 0) {
                return;
            }
            mValue = "";
        } else if (Constant.INSTANCE.getDeleteFlag().equalsIgnoreCase(value)) {
            if (TextUtils.isEmpty(mValue) || mValue.length() == 0) {
                return;
            }
            mValue = mValue.substring(0, mValue.length() - 1);
        } else {
            if (!TextUtils.isEmpty(mValue)
                    && mValue.length() >= mMaxLength) {
                return;
            }
            if (Constant.INSTANCE.getPointFlag().equalsIgnoreCase(value)) {
                if (TextUtils.isEmpty(mValue)
                        || mValue.contains(Constant.INSTANCE.getPointInterval())
                        || mValue.length() == mMaxLength - 1) {
                    //当前数据为空、数据中包含"."、最后一位不能为"."
                    return;
                }
                mValue += Constant.INSTANCE.getPointInterval();
            } else {
                if (mValue.contains(Constant.INSTANCE.getPointInterval())) {
                    final int pointLength = mValue.substring(
                            mValue.indexOf(Constant.INSTANCE.getPointInterval()) + 1).length();
                    if (pointLength >= mMoneyPointLength) {
                        return;
                    }
                }
                mValue += value;
            }
        }
        if (isThenCall && mKeyBoardCall != null) {
            mKeyBoardCall.onCall(mValue);
        }
    }
}
