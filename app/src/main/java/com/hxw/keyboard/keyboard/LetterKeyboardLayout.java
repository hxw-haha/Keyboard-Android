package com.hxw.keyboard.keyboard;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.hxw.keyboard.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>文件描述：字母键盘</p>
 * <p>作者：hanxw</p>
 * <p>创建时间：2021/3/29</p>
 * <p>更改时间：2021/3/29</p>
 * <p>版本号：1</p>
 */
public class LetterKeyboardLayout extends BaseKeyboardLayout {
    private boolean isCaseFlag;

    private List<String> letterBigList = new ArrayList<>();
    private List<String> letterSmallList = new ArrayList<>();

    public LetterKeyboardLayout(Context context) {
        super(context);
    }

    @Override
    public void initViews() {
        isCaseFlag = true;

        final List<String> keyboardLetterBigList = Constant.INSTANCE.getKeyboardLetterBigList();
        if (isLetterRandom) {
            Collections.shuffle(keyboardLetterBigList);
        }

        letterBigList.clear();
        letterBigList.addAll(keyboardLetterBigList);

        letterSmallList.clear();
        for (String letter : keyboardLetterBigList) {
            letterSmallList.add(letter.toLowerCase());
        }

        loadLetterKeyboard(true);
    }


    /*****************************字母键盘 start***************************************/
    /**
     * 显示字符串键盘
     *
     * @param isCaseFlag true：大小，false：小写
     */
    private void loadLetterKeyboard(boolean isCaseFlag) {
        removeAllViews();
        mKeyboardLetterList.clear();
        if (isCaseFlag) {
            mKeyboardLetterList.addAll(letterBigList);
        } else {
            mKeyboardLetterList.addAll(letterSmallList);
        }
        addRowView(mKeyboardLetterList.subList(0, 10), 10);
        addRowView(mKeyboardLetterList.subList(10, 19), 10);
        addLetterActionUpdate(isCaseFlag);
        addLetterActionChange();
    }

    /**
     * 更新操作
     */
    private void addLetterActionUpdate(boolean isCaseFlag) {
        final LinearLayout rowLayout = generatedRow(10);
        final ImageView caseView = generatedActionView(Constant.INSTANCE.getCaseFlag(), R.drawable.selector_keyboard_case, 1.5f);
        caseView.setSelected(isCaseFlag);
        rowLayout.addView(caseView);
        final List<String> list = mKeyboardLetterList.subList(19, mKeyboardLetterList.size());
        for (String value : list) {
            rowLayout.addView(generatedItemTextView(value));
        }
        rowLayout.addView(generatedActionView(Constant.INSTANCE.getDeleteFlag(), R.drawable.selector_keyboard_letter_del, 1.5f));
        addView(rowLayout);
    }

    /**
     * 切换显示
     */
    private void addLetterActionChange() {
        final LinearLayout rowLayout = generatedRow(10);
        rowLayout.addView(generatedItemTextView(Constant.INSTANCE.getNumberFlag(), R.drawable.selector_keyboard_action, 2));
        final TextView blankView = generatedItemTextView(Constant.INSTANCE.getBlankFlag(), R.drawable.selector_keyboard_action, 6);
        blankView.setEnabled(false);
        rowLayout.addView(blankView);
        rowLayout.addView(generatedItemTextView(Constant.INSTANCE.getSignFlag(), R.drawable.selector_keyboard_action, 2));
        addView(rowLayout);
    }

    /*****************************字母键盘 end***************************************/


    /*****************************数字键盘  start***************************************/
    private void loadNumberKeyboard() {
        removeAllViews();
        addRowView(mKeyboardNumberList.subList(0, 3), 3);
        addRowView(mKeyboardNumberList.subList(3, 6), 3);
        addRowView(mKeyboardNumberList.subList(6, 9), 3);
        addNumberActionView(mKeyboardNumberList.get(9));
    }

    private void addNumberActionView(String itemValue) {
        final LinearLayout rowLayout = generatedRow(3);
        rowLayout.addView(generatedItemTextView(Constant.INSTANCE.getLetterFlag(), R.drawable.selector_keyboard_action));
        rowLayout.addView(generatedItemTextView(itemValue));
        rowLayout.addView(generatedActionView(Constant.INSTANCE.getDeleteFlag(), R.drawable.selector_keyboard_number_del));
        addView(rowLayout);
    }
    /*****************************数字键盘  edn***************************************/


    /*****************************符号键盘  start***************************************/
    private void loadSignKeyboard() {
        removeAllViews();
        addRowView(mKeyboardSignList.subList(0, 10), 10);
        addRowView(mKeyboardSignList.subList(10, 19), 10);
        addSignKeyActionUpdate();
        addSignActionChange();
    }

    /**
     * 更新操作
     */
    private void addSignKeyActionUpdate() {
        final LinearLayout rowLayout = generatedRow(10);
        final TextView blankView = generatedItemTextView(Constant.INSTANCE.getBlankFlag(), R.drawable.transparent, 0.5f);
        blankView.setEnabled(false);
        rowLayout.addView(blankView);
        final List<String> list = mKeyboardSignList.subList(19, 26);
        for (String value : list) {
            rowLayout.addView(generatedItemTextView(value));
        }
        rowLayout.addView(generatedActionView(Constant.INSTANCE.getDeleteFlag(), R.drawable.selector_keyboard_letter_del, 1.5f));
        addView(rowLayout);
    }

    /**
     * 切换显示
     */
    private void addSignActionChange() {
        final LinearLayout rowLayout = generatedRow(10);
        rowLayout.addView(generatedItemTextView(Constant.INSTANCE.getNumberFlag(), R.drawable.selector_keyboard_action, 2));
        final List<String> list = mKeyboardSignList.subList(26, mKeyboardSignList.size());
        for (String value : list) {
            rowLayout.addView(generatedItemTextView(value));
        }
        rowLayout.addView(generatedItemTextView(Constant.INSTANCE.getLetterFlag(), R.drawable.selector_keyboard_action, 2));
        addView(rowLayout);
    }

    /*****************************符号键盘  end***************************************/

    @Override
    public void onCall(String value) {
        if (Constant.INSTANCE.getCaseFlag().equalsIgnoreCase(value)) {
            isCaseFlag = !isCaseFlag;
            loadLetterKeyboard(isCaseFlag);
            return;
        } else if (Constant.INSTANCE.getLetterFlag().equalsIgnoreCase(value)) {
            loadLetterKeyboard(isCaseFlag);
            return;
        } else if (Constant.INSTANCE.getNumberFlag().equalsIgnoreCase(value)) {
            loadNumberKeyboard();
            return;
        } else if (Constant.INSTANCE.getSignFlag().equalsIgnoreCase(value)) {
            loadSignKeyboard();
            return;
        }
        super.onCall(value);
    }
}
