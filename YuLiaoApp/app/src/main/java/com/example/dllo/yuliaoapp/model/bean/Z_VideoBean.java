package com.example.dllo.yuliaoapp.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/19.
 * 视频页实体类
 */
public class Z_VideoBean  {


    /**
     * cover : http://vimg1.ws.126.net/image/snapshot/2016/10/S/H/VC2QBLESH.jpg
     * description : 毛骗---骗子的又一套路，可不要相信陌生人说的话
     * length : 148
     * m3u8_url : http://flv2.bn.netease.com/videolib3/1610/19/PSDJM7596/SD/movie_index.m3u8
     * mp4_url : http://flv2.bn.netease.com/videolib3/1610/19/PSDJM7596/SD/PSDJM7596-mobile.mp4
     * playCount : 0
     * playersize : 1
     * prompt : 成功为您推荐10条新视频
     * ptime : 2016-10-19 14:01:24.0
     * replyBoard : video_bbs
     * replyCount : 0
     * replyid : C2QAD9G4008535RB
     * sectiontitle : http://vimg3.ws.126.net/image/snapshot/2016/10/S/R/VC2O3UTSR.jpg
     * title : 骗子的又一套路，可不要相信陌生人说的话
     * topicDesc : 每天播放  视频   搞笑   模仿  希望老大给予通过  谢谢
     * topicImg : http://vimg3.ws.126.net/image/snapshot/2016/10/S/R/VC2O3UTSR.jpg
     * topicName : 丛林蚂蚁
     * topicSid : VC2O3UTSJ
     * vid : VC2QAD9G4
     * videoTopic : {"alias":"视频直播","ename":"T1476515576558","tid":"T1476515576558","tname":"丛林蚂蚁"}
     * videosource : 新媒体
     */

    private List<视频Bean> 视频;

    public List<视频Bean> get视频() {
        return 视频;
    }

    public void set视频(List<视频Bean> 视频) {
        this.视频 = 视频;
    }

    public static class 视频Bean {
        private String cover;
        private String description;
        private int length;
        private String m3u8_url;
        private String mp4_url;
        private int playCount;
        private int playersize;
        private String prompt;
        private String ptime;
        private String replyBoard;
        private int replyCount;
        private String replyid;
        private String sectiontitle;
        private String title;
        private String topicDesc;
        private String topicImg;
        private String topicName;
        private String topicSid;
        private String vid;
        /**
         * alias : 视频直播
         * ename : T1476515576558
         * tid : T1476515576558
         * tname : 丛林蚂蚁
         */

        private VideoTopicBean videoTopic;
        private String videosource;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getM3u8_url() {
            return m3u8_url;
        }

        public void setM3u8_url(String m3u8_url) {
            this.m3u8_url = m3u8_url;
        }

        public String getMp4_url() {
            return mp4_url;
        }

        public void setMp4_url(String mp4_url) {
            this.mp4_url = mp4_url;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public int getPlayersize() {
            return playersize;
        }

        public void setPlayersize(int playersize) {
            this.playersize = playersize;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getReplyBoard() {
            return replyBoard;
        }

        public void setReplyBoard(String replyBoard) {
            this.replyBoard = replyBoard;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getReplyid() {
            return replyid;
        }

        public void setReplyid(String replyid) {
            this.replyid = replyid;
        }

        public String getSectiontitle() {
            return sectiontitle;
        }

        public void setSectiontitle(String sectiontitle) {
            this.sectiontitle = sectiontitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTopicDesc() {
            return topicDesc;
        }

        public void setTopicDesc(String topicDesc) {
            this.topicDesc = topicDesc;
        }

        public String getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(String topicImg) {
            this.topicImg = topicImg;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getTopicSid() {
            return topicSid;
        }

        public void setTopicSid(String topicSid) {
            this.topicSid = topicSid;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public VideoTopicBean getVideoTopic() {
            return videoTopic;
        }

        public void setVideoTopic(VideoTopicBean videoTopic) {
            this.videoTopic = videoTopic;
        }

        public String getVideosource() {
            return videosource;
        }

        public void setVideosource(String videosource) {
            this.videosource = videosource;
        }

        public static class VideoTopicBean {
            private String alias;
            private String ename;
            private String tid;
            private String tname;

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getTname() {
                return tname;
            }

            public void setTname(String tname) {
                this.tname = tname;
            }
        }
    }
}
