package com.note.testlibrary.tool;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 说明：监听EditTextView 是否输入了值，改变状态
 * 作者：王贵才
 * 邮箱：786722510@qq.com
 */
public class WorksSizeCheckUtil {
    private static IEditTextChangeListener mChangeListener;
    public static void setChangeListener(IEditTextChangeListener changeListener) {
        mChangeListener = changeListener;
    }
    public static class textChangeListener {
        private TextView button;
        private EditText[] editTexts;
        public textChangeListener(TextView button) {
            this.button = button;
        }

        public void addAllEditText(EditText... editTexts) {
            this.editTexts = editTexts;
            initEditListener();
        }
        private void initEditListener() {
            for (EditText editText : editTexts) {
                editText.addTextChangedListener(new textChange());
            }
        }

        private class textChange implements TextWatcher {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkAllEdit()) {
                    mChangeListener.textChange(true);
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                    mChangeListener.textChange(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        }

        /**
         * 检查所有的edit是否输入了数据
         */
        private boolean checkAllEdit() {
            for (EditText editText : editTexts) {
                if (!TextUtils.isEmpty(editText.getText() + "")) {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public interface IEditTextChangeListener {
        void textChange(boolean isHasContent);
    }



/******************************* 使用方法*******************************/

//     WorksSizeCheckUtil  textChangeListener = new WorksSizeCheckUtil.textChangeListener(btnLogin);  btnLogin 就是监听有值才可以点击
//        textChangeListener.addAllEditText(editPhone, editPass); //添加edit需要执行监听 账号、密码
//        WorksSizeCheckUtil.setChangeListener(isHasContent -> {
//        if (isHasContent) {
//            btnLogin.setEnabled(true);     btnLogin 监听有值才可以点击
//            btnLogin.setBackgroundResource(R.drawable.login_selector); 可点击显示Button背景颜色
//        } else {
//            btnLogin.setEnabled(false);
//            btnLogin.setBackgroundResource(R.drawable.login_shape);
//        }
//    });
}
