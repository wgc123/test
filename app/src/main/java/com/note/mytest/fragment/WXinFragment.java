package com.note.mytest.fragment;

import android.view.View;

import com.google.gson.Gson;
import com.note.mytest.R;
import com.note.mytest.gson.GsonBean;
import com.note.testlibrary.tool.WGCLogUtils;

import java.util.List;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/10 8:50
 * @desc : 微信
 */
public class WXinFragment extends BaseFragment {
    String result = "{\"from\":1,\"status\":200,\"detail\":\"OK\",\"meetingid\":364431423,\n" +
            "\"members\":[{\"userid\":1,\"nickname\":\"测试用户2\"}]}";

    @Override
    public int initLayout() {
        return R.layout.fragment_wxin;
    }

    @Override
    public void initData(View view) {
        Gson gson = new Gson();
        GsonBean bean = gson.fromJson(result, GsonBean.class);
        List<GsonBean.MembersBean> membersBeans = bean.getMembers();
        int userid = 0;
        String nickname = "";
        for (GsonBean.MembersBean gb : membersBeans){
            userid = gb.getUserid();
            nickname = gb.getNickname();
        }
        WGCLogUtils.i("id:" + userid);
        WGCLogUtils.i("nickname:" + nickname);
    }


}
