package com.note.testlibrary.tool;

/**
 * 说明：数字格式化分隔 如：123 456 789
 * 作者：王贵才
 * 邮箱：786722510@qq.com
 */
public class FormatNumber {

    private static FormatNumber formatNumber;

    public static FormatNumber getInstance(){
        if (formatNumber == null){
            formatNumber = new FormatNumber();
        }
        return formatNumber;
    }
    // 自定义隔开符操作
    public String formatNumberWithCommaSplit(double number) {
        String firstStr = "";//第一个字符
        String middleStr = "";//中间字符
        String endStr = "";//小数后两位
        if (number < 0) {
            firstStr = "-";
        } else if (number != 0 && number < 0.1) {
            return number + "";
        }
        String tempNumberStr = number + "00000000000000000";
        int endIndex = tempNumberStr.lastIndexOf(".");
        endStr = tempNumberStr.substring(endIndex+1, endIndex + 3);

        String numberStr = Math.abs((long) number) + "";//取正

        int firstIndex = numberStr.length() % 3;
        int bitCount = numberStr.length() / 3;

        if (firstIndex > 0) {
            middleStr += numberStr.substring(0, firstIndex) + " ";
        }
        // 这儿设置隔开符
        for (int i = 0; i < bitCount; i++) {
            middleStr += numberStr.substring(firstIndex + i * 3, firstIndex + i * 3 + 3) + " ";
        }
        if (middleStr.length() > 1) {
            middleStr = middleStr.substring(0, middleStr.length() - 1);
        }
        return firstStr + middleStr ;
    }
}
