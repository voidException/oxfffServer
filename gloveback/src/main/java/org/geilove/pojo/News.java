package org.geilove.pojo;

import java.util.Date;

public class News {
    private Long id;

    private String newsuuid;

    private String author;

    private Date publishdate;

    private String title;

    private String vicetitle;

    private String source;

    private String newscontent;

    private String imageone;

    private String imagetwo;

    private String imagethree;

    private String newsurl;

    private String newstype;

    private String lable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsuuid() {
        return newsuuid;
    }

    public void setNewsuuid(String newsuuid) {
        this.newsuuid = newsuuid == null ? null : newsuuid.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getVicetitle() {
        return vicetitle;
    }

    public void setVicetitle(String vicetitle) {
        this.vicetitle = vicetitle == null ? null : vicetitle.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent == null ? null : newscontent.trim();
    }

    public String getImageone() {
        return imageone;
    }

    public void setImageone(String imageone) {
        this.imageone = imageone == null ? null : imageone.trim();
    }

    public String getImagetwo() {
        return imagetwo;
    }

    public void setImagetwo(String imagetwo) {
        this.imagetwo = imagetwo == null ? null : imagetwo.trim();
    }

    public String getImagethree() {
        return imagethree;
    }

    public void setImagethree(String imagethree) {
        this.imagethree = imagethree == null ? null : imagethree.trim();
    }

    public String getNewsurl() {
        return newsurl;
    }

    public void setNewsurl(String newsurl) {
        this.newsurl = newsurl == null ? null : newsurl.trim();
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype == null ? null : newstype.trim();
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable == null ? null : lable.trim();
    }
}