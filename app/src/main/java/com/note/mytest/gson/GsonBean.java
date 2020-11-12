package com.note.mytest.gson;

import java.util.List;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/11/4 10:29
 * @desc : 测试gson解析
 */
public class GsonBean {

    /**
     * from : 1
     * status : 200
     * detail : OK
     * meetingid : 364431423
     * members : [{"userid":1,"nickname":"测试用户2"}]
     */
    private int from;
    private int status;
    private String detail;
    private int meetingid;
    private List<MembersBean> members;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(int meetingid) {
        this.meetingid = meetingid;
    }

    public List<MembersBean> getMembers() {
        return members;
    }

    public void setMembers(List<MembersBean> members) {
        this.members = members;
    }

    public static class MembersBean {
        /**
         * userid : 1
         * nickname : 测试用户2
         */

        private int userid;
        private String nickname;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
