package org.geilove.requestParam;

/**
 * Created by aihaitao on 11/4/2017.
 */
public class ReportParam {

    private String   token;
    private String   content;
    private String   relation;
    private String   tag;
    private Long    tuiwenid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getTuiwenid() {
        return tuiwenid;
    }

    public void setTuiwenid(Long tuiwenid) {
        this.tuiwenid = tuiwenid;
    }
}
