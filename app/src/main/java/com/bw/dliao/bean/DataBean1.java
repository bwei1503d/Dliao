package com.bw.dliao.bean;

/**
 * Created by Administrator on 2017/7/14.
 */

public class DataBean1 {

    /**
     * area : 安徽省-安庆市-枞阳县
     * picWidth : 720
     * createtime : 1500016251283
     * picHeight : 720
     * gender : 女
     * lng : 0
     * introduce : 兔兔
     * imagePath : http://qhb.2dyt.com/MyInterface/images/3c878e19-4c5a-4c59-9df4-eba244b6757e.jpg
     * userId : 34
     * yxpassword :
     * relation : 0
     * password :
     * lasttime : 1500016275649
     * phone : 18222222222
     * nickname : 傻傻的
     * age : 25
     * lat : 0
     */

    private Long id;

    private String area;

    private int picWidth;

    private long createtime;

    private int picHeight;

    private String gender;

    private Double lng;

    private String introduce;

    private String imagePath;

    private int userId;

    private String yxpassword;

    private int relation;

    private String password;

    private long lasttime;
    private String phone;

    private String nickname;

    private String age;

    private Double lat;
    private long relationtime;

    public DataBean1(Long id, String area, int picWidth, long createtime, int picHeight, String gender, Double lng, String introduce, String imagePath, int userId, String yxpassword, int relation, String password, long lasttime, String phone, String nickname, String age, Double lat, long relationtime) {
        this.id = id;
        this.area = area;
        this.picWidth = picWidth;
        this.createtime = createtime;
        this.picHeight = picHeight;
        this.gender = gender;
        this.lng = lng;
        this.introduce = introduce;
        this.imagePath = imagePath;
        this.userId = userId;
        this.yxpassword = yxpassword;
        this.relation = relation;
        this.password = password;
        this.lasttime = lasttime;
        this.phone = phone;
        this.nickname = nickname;
        this.age = age;
        this.lat = lat;
        this.relationtime = relationtime;
    }

    public DataBean1() {
    }

    public long getRelationtime() {
        return relationtime;
    }

    public void setRelationtime(long relationtime) {
        this.relationtime = relationtime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getPicWidth() {
        return this.picWidth;
    }

    public void setPicWidth(int picWidth) {
        this.picWidth = picWidth;
    }

    public long getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public int getPicHeight() {
        return this.picHeight;
    }

    public void setPicHeight(int picHeight) {
        this.picHeight = picHeight;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getLng() {
        return this.lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getYxpassword() {
        return this.yxpassword;
    }

    public void setYxpassword(String yxpassword) {
        this.yxpassword = yxpassword;
    }

    public int getRelation() {
        return this.relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getLasttime() {
        return this.lasttime;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }


}
