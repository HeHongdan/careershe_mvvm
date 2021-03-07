package com.careershe.careershe.model.main.qna.bean;


import com.careershe.rxhttp.request.base.BaseBean;

/**
 * 类描述：问答-回答。
 *
 * @author HeHongdan
 * @date 2021/1/17
 * @since v2021/1/17
 */
public class AnswerBean extends BaseBean {

    private String _id;
    /** 回答用户名(第一个)。  */
    private int answerCheckOfficial;
    /** 验证问答用户是否是官方 1.官方 2.vip 3.普通用户。  */
    private int askingCheckOfficial;
    /** 评论昵称。  */
    private String answerName;
    /** 评论头像。  */
    private String answerImage;
    /** 评论内容。  */
    private String answerText;
    /** 评论日期。  */
    private String answerDate;
    /** 评论数。  */
    private String answerCount;


    public int getAnswerCheckOfficial() {
        return answerCheckOfficial;
    }

    public int getAskingCheckOfficial() {
        return askingCheckOfficial;
    }

    public String getAnswerName() {
        return answerName;
    }

    public String getAnswerImage() {
        return answerImage;
    }

    public String getAnswerText() {
        return answerText;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public String getAnswerCount() {
        return answerCount;
    }
}
