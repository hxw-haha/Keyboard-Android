package com.hxw.keyboard.keyboard;

import android.content.Context;
import android.widget.LinearLayout;

import com.hxw.keyboard.R;


/**
 * <p>文件描述：数字键盘</p>
 * <p>作者：hanxw</p>
 * <p>创建时间：2021/3/29</p>
 * <p>更改时间：2021/3/29</p>
 * <p>版本号：1</p>
 */
public class NumberKeyboardLayout extends BaseKeyboardLayout {
    public NumberKeyboardLayout(Context context) {
        super(context);
    }

    @Override
    public void initViews() {
        addRowView(mKeyboardNumberList.subList(0, 3), 3);
        addRowView(mKeyboardNumberList.subList(3, 6), 3);
        addRowView(mKeyboardNumberList.subList(6, 9), 3);
        addActionView(mKeyboardNumberList.get(9));
    }

    private void addActionView(String itemValue) {
        final LinearLayout rowLayout = generatedRow(3);
        rowLayout.addView(generatedItemTextView(isMoneyKeyboard ?
                        Constant.INSTANCE.getPointFlag() : Constant.INSTANCE.getClearFlag(),
                R.drawable.selector_keyboard_action));
        rowLayout.addView(generatedItemTextView(itemValue));
        rowLayout.addView(generatedActionView(Constant.INSTANCE.getDeleteFlag(), R.drawable.selector_keyboard_number_del));
        addView(rowLayout);
    }

    @Override
    public void onCall(String value) {
        super.onCall(value);
    }
}
