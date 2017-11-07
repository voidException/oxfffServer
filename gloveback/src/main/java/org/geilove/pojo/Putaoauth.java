package org.geilove.pojo;

public class Putaoauth {
    private Long id;

    private String renzhenguuid;

    private String name;

    private String numberid;

    private String phone;

    private String address;

    private String email;

    private String confirmif;

    private String comment;

    private String imgone;

    private String imgtwo;

    private String imgthree;

    private String authtype;

    private String legalperson;

    private String useruuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRenzhenguuid() {
        return renzhenguuid;
    }

    public void setRenzhenguuid(String renzhenguuid) {
        this.renzhenguuid = renzhenguuid == null ? null : renzhenguuid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNumberid() {
        return numberid;
    }

    public void setNumberid(String numberid) {
        this.numberid = numberid == null ? null : numberid.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getConfirmif() {
        return confirmif;
    }

    public void setConfirmif(String confirmif) {
        this.confirmif = confirmif == null ? null : confirmif.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getImgone() {
        return imgone;
    }

    public void setImgone(String imgone) {
        this.imgone = imgone == null ? null : imgone.trim();
    }

    public String getImgtwo() {
        return imgtwo;
    }

    public void setImgtwo(String imgtwo) {
        this.imgtwo = imgtwo == null ? null : imgtwo.trim();
    }

    public String getImgthree() {
        return imgthree;
    }

    public void setImgthree(String imgthree) {
        this.imgthree = imgthree == null ? null : imgthree.trim();
    }

    public String getAuthtype() {
        return authtype;
    }

    public void setAuthtype(String authtype) {
        this.authtype = authtype == null ? null : authtype.trim();
    }

    public String getLegalperson() {
        return legalperson;
    }

    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson == null ? null : legalperson.trim();
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }
}