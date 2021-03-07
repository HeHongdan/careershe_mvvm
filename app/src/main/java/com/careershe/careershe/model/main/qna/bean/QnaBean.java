package com.careershe.careershe.model.main.qna.bean;



import com.careershe.rxhttp.request.base.BaseBean;

import java.util.Date;

/**
 * 类描述：问答(主页-问答)。
 *
 * @author HeHongdan
 * @date 2021/1/21
 * @since v2021/1/21
 *
 * "data": {
 *         "_id": "Reqwkvdttp", //问答id
 *         "askingName": "中山大学有什么热门的专业？", //问答名称
 *         "username": "法外狂徒张三", //问答用户
 *         "image": "https://www.careershe.com/images/bc3ca354b71448d981fd9a160deb354d.jpeg", //问答用户图片
 *         "askingCheckOfficial": 3, // 验证是否是官方 1. 官方 2. vip 3. 普通用户
 *         "answerCount": 1, //评论数
 *         "answerList": [ //评论列表
 *             {
 *                 "_id": "JiuxXhtGQE", //评论id
 *                 "answerCheckOfficial": 1, //评论用户是否官方
 *                 "answerName": "千职鹤—李老师", //评论用户
 *                 "answerImage":  "https://www.careershe.com/images/20201217185414.png", //评论用户图片
 *                 "answerText": "中山大学最好的学院是管理学院和医学院，所以管院下面的工商管理、公共管理，医学院下面的临床医学、基础医学、药学都是非常不错的专业", //评论内容
 *                 "answerDate": "17小时前", //评论发布时间
 *                 "answerCount": null
 *             }
 *         ],
 *         "checkQuestion": 2, //1. 未审核 2.审核通过  4.已删除
 *         "askingDate": "2021-01-20 15:52:26", //问答发布时间
 *         "answerVo": null,
 *         "sort": null,
 *         "selectedOrTop": null,
 *         "keywordName": "院校" //关键词
 *     }
 *
 */
public class QnaBean extends BaseBean {

    /** 验证问答用户是否是官方 1.官方 2.vip 3.普通用户。  */
    public static final int USER_OFFICIAL = 1;
    public static final int USER_VIP = 2;

    private String _id;

    /** 问答名称(问题)。  */
    private String askingName;
    /** 提问用户名。  */
    private String username;
    /** 提问用户头像。  */
    private String image;
    /** 验证问答用户是否是官方 1.官方 2.vip 3.普通用户。  */
    private int askingCheckOfficial;
    /** 评论数。  */
    private int answerCount;
    /** 问答状态 审核状态 1. 未审核 2.审核通过 4.已删除。  */
    private int checkQuestion;
    /** 问答时间。  */
    private Date askingDate;
    /** 提问分类(标签)。  */
    private String keywordName;
    /** 评论。  */
    private AnswerBean answerVo;
    private int newAnswerCount;


    public String getAskingName() {
        return askingName;
    }

    public String getUsername() {
        return username;
    }

    public String getImage() {
        return image;
    }

    public int getAskingCheckOfficial() {
        return askingCheckOfficial;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public int getCheckQuestion() {
        return checkQuestion;
    }

    public Date getAskingDate() {
        return askingDate;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public AnswerBean getAnswerVo() {
        return answerVo;
    }

    public int getNewAnswerCount() {
        return newAnswerCount;
    }

    @Override
    public String toString() {
        return "问答{" +
                "askingName='" + askingName + '\'' +
                ", username='" + username + '\'' +
                ", image='" + image + '\'' +
                ", answerCount=" + answerCount +
                ", checkQuestion=" + checkQuestion +
                ", askingDate=" + askingDate +
                ", keywordName='" + keywordName + '\'' +
                ", answerVo=" + answerVo +
                ", newAnswerCount=" + newAnswerCount +
                '}';
    }
}
