package com.hxw.keyboard.keyboard;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.hxw.keyboard.R;
import com.hxw.keyboard.base.BaseDialog;
import com.hxw.keyboard.utils.VerifyUtil;


/**
 * <p>文件描述：键盘弹窗</p>
 * <p>作者：hanxw</p>
 * <p>创建时间：2021/3/29</p>
 * <p>更改时间：2021/3/29</p>
 * <p>版本号：1</p>
 */
public class KeyboardDialog extends BaseDialog {
    private Builder mBuilder;

    private KeyboardDialog(@NonNull Builder builder) {
        super(builder.context, true);
        this.mBuilder = builder;
        initViews();
        mDialog.setCanceledOnTouchOutside(builder.canceledOnTouchOutside);
        mDialog.setCancelable(builder.cancelable);
    }

    private void initViews() {
        FrameLayout content = findViewId(R.id.keyboard_content);
        final BaseKeyboardLayout keyboardLayout = generatedKeyboardLayout();
        content.addView(keyboardLayout);
        findViewId(R.id.keyboard_finish).setOnClickListener(v -> {
            final String inputValue = keyboardLayout.getValue();
            if (mBuilder.isPhoneKeyboard) {
                if (!VerifyUtil.isPhone(inputValue)) {
                    showToast("手机号码有误。");
                    return;
                }
            }
            if (mBuilder.isIdNoKeyboard) {
                if (!VerifyUtil.isIDNo(inputValue)) {
                    showToast("证件号码有误。");
                    return;
                }
            }
            if (mBuilder.call != null) {
                mBuilder.call.onCall(inputValue);
            }
            dismiss();
        });
    }

    private BaseKeyboardLayout generatedKeyboardLayout() {
        BaseKeyboardLayout keyboardLayout;
        if (mBuilder.isNumberKeyboard) {
            keyboardLayout = new NumberKeyboardLayout(mRootView.getContext());
        } else {
            keyboardLayout = new LetterKeyboardLayout(mRootView.getContext());
        }
        keyboardLayout.setNumberRandom(mBuilder.isNumberRandom);
        keyboardLayout.setLetterRandom(mBuilder.isLetterRandom);
        keyboardLayout.setKeyBoardCall(mBuilder.call);
        keyboardLayout.setThenCall(mBuilder.isThenCall);
        keyboardLayout.setMaxLength(mBuilder.maxLength);
        keyboardLayout.setDefaultValue(mBuilder.defaultValue);

        if (mBuilder.isIdNoKeyboard) {
            keyboardLayout.setIdNoKeyboard();
        }
        if (mBuilder.isMoneyKeyboard) {
            keyboardLayout.setMoneyPointLength(mBuilder.moneyPointLength);
        }
        if (mBuilder.isPhoneKeyboard) {
            keyboardLayout.setPhoneKeyboard();
        }
        keyboardLayout.build();
        return keyboardLayout;
    }

    @Override
    public int getGravity() {
        return Gravity.LEFT | Gravity.BOTTOM;
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_keyboard;
    }

    public static class Builder {
        private Context context;
        private boolean cancelable = true, canceledOnTouchOutside = true;
        private IKeyBoardCall call;

        /**
         * 设置默认值
         */
        private String defaultValue = "";
        /**
         * 最大长度
         */
        private int maxLength = Integer.MAX_VALUE;
        /**
         * 是否数字随机，是否字母随机
         */
        private boolean isNumberRandom = false, isLetterRandom = false;
        /**
         * 是否数字键盘、是否金额键盘、是否证件键盘、是否手机号键盘
         */
        private boolean isNumberKeyboard = false, isMoneyKeyboard = false, isIdNoKeyboard = false, isPhoneKeyboard = false;
        /**
         * 金额小数据后几位
         */
        private int moneyPointLength = Constant.INSTANCE.getDefaultMoneyPointMaxLength();
        /**
         * 是否实时回调
         * 默认：true
         */
        protected boolean isThenCall = true;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        /**
         * 是否金额键盘
         */
        public Builder setMoneyKeyboard() {
            setNumberKeyboard(true);
            isMoneyKeyboard = true;
            return this;
        }

        /**
         * 设置金额小数据后几位
         */
        public Builder setMoneyPointLength(int moneyPointLength) {
            setNumberKeyboard(true);
            this.moneyPointLength = moneyPointLength;
            isMoneyKeyboard = true;
            return this;
        }

        /**
         * 是否证件键盘
         */
        public Builder setIdNoKeyboard() {
            setNumberKeyboard(true);
            isIdNoKeyboard = true;
            setMaxLength(18);
            return this;
        }

        /**
         * 设置是否手机号键盘
         *
         * @return
         */
        public Builder setPhoneKeyboard() {
            setNumberKeyboard(true);
            isPhoneKeyboard = true;
            setMaxLength(11);
            return this;
        }

        /**
         * 设置默认值
         */
        public Builder setDefaultValue(@NonNull String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * 设置是否属于数字键盘
         *
         * @param numberKeyboard
         * @return
         */
        public Builder setNumberKeyboard(boolean numberKeyboard) {
            isNumberKeyboard = numberKeyboard;
            return this;
        }

        /**
         * 设置字母是否随机
         *
         * @param letterRandom
         */
        public Builder setLetterRandom(boolean letterRandom) {
            isLetterRandom = letterRandom;
            return this;
        }

        /**
         * 设置数字是否随机
         *
         * @param numberRandom
         * @return
         */
        public Builder setNumberRandom(boolean numberRandom) {
            isNumberRandom = numberRandom;
            return this;
        }

        /**
         * 设置最大长度
         *
         * @param maxLength
         * @return
         */
        public Builder setMaxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        /**
         * 设置是否实时回调
         *
         * @param thenCall
         * @return
         */
        public Builder setThenCall(boolean thenCall) {
            isThenCall = thenCall;
            return this;
        }

        /**
         * 设置回调方法
         *
         * @param call
         * @return
         */
        public Builder setCall(IKeyBoardCall call) {
            this.call = call;
            return this;
        }

        public Builder setCancelable(boolean flag) {
            this.cancelable = flag;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean cancel) {
            if (cancel && !cancelable) {
                cancelable = true;
            }
            canceledOnTouchOutside = cancel;
            return this;
        }

        public KeyboardDialog build() {
            final KeyboardDialog dialog = create();
            dialog.show();
            return dialog;
        }

        public KeyboardDialog create() {
            return new KeyboardDialog(this);
        }
    }
}
