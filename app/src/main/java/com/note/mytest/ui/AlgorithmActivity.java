package com.note.mytest.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.note.mytest.R;
import com.note.mytest.tool.Algorithm;
import com.note.testlibrary.tool.BaseActivity;
import com.note.testlibrary.tool.WGCLogUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 实现十大算法
 */
public class AlgorithmActivity extends BaseActivity {

    @BindView(R.id.btn_dichotomy)
    Button btnDichotomy;
    @BindView(R.id.btn_Hanoi)
    Button btnHanoi;
    @BindView(R.id.btn_bubble)
    Button btnBubble;
    @BindView(R.id.btn_selection)
    Button btnSelection;
    @BindView(R.id.tv_arr)
    TextView tvArr;
    @BindView(R.id.tv_result)
    TextView tvResult;

    int[] arr = {1, 3, 8, 10, 11, 67, 100, 2, 9, 4, 59, 88, 32, 78};

    @Override
    protected int initLayout() {
        return R.layout.activity_algorithm;
    }

    @Override
    protected void initData() {
        String ct = "";
        for (int i = 0; i < arr.length; i++) {
            //数组拼接成字符串
            ct = ct + "," + arr[i];
        }
        tvArr.setText(ct);

    }

    @OnClick({R.id.btn_dichotomy, R.id.btn_Hanoi, R.id.btn_bubble, R.id.btn_selection,R.id.btn_insert})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 二分法
            case R.id.btn_dichotomy:
                /**
                 * arr 数组  target 要查找的数据（目标）
                 */
                int index = Algorithm.binarySearch(arr, 3);
                if (index != -1) {
                    WGCLogUtils.i("找到了，下标为：" + index);
                } else {
                    WGCLogUtils.d("没有找到");
                }
                break;
            case R.id.btn_Hanoi:
                break;

            /**
             *
             * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
             * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经
             * 由交换慢慢“浮”到数列的顶端。
             */
            case R.id.btn_bubble:
                int bubble[] = Algorithm.bubbleSort(arr);
                WGCLogUtils.i("冒泡算法：" + bubble.length);
                String result ="";
                for (int i =0; i < bubble.length; i ++) {
                    result = result + bubble[i]+ ",";
                }
                tvResult.setText("冒泡结果：" + result);
                break;

            /**
             *选择排序
             */
            case R.id.btn_selection:
                int[] selection = Algorithm.selectionSort(arr);
                WGCLogUtils.i("选择排序：" + selection.length);
                String selectionResult ="";
                for (int i =0; i < selection.length; i ++) {
                    selectionResult = selectionResult + selection[i]+ ",";
                }
                tvResult.setText("选择排序结果：" + selectionResult);
                break;

            case R.id.btn_insert:
                int[] insert = Algorithm.insertionSort(arr);
                WGCLogUtils.i("插入排序：" + insert.length);
                String insertResult ="";
                for (int i =0; i < insert.length; i ++) {
                    selectionResult = insertResult + insert[i]+ ",";
                }
                tvResult.setText("插入排序结果：" + insertResult);
                break;
            default:
                break;
        }
    }
}
