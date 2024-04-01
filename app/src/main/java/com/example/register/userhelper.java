package com.example.register;

public class userhelper {
    String fullname;
    String email;
    String password;
    String course;
    String uri;
    public userhelper() {

    }
    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        this.Uid = uid;
    }

    String Uid;
    int Usertype;
    String phone;
    String id;
    String fee;
    String exam;
    String cat;
    String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public userhelper(String uid, String fullname, String email, String password, String phone, String course, String id, int Usertype, String uri,String fee,String exam,String cat,String unit) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.course = course;
        this.id=id;
        this.Usertype=Usertype;
        this.uri=uri;
        this.fee = fee;
        this.cat = cat;
        this.exam = exam;
        this.Uid = uid;
        this.unit = unit;

    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getUsertype() {
        return Usertype;
    }

    public void setUsertype(int usertype) {
        this.Usertype = usertype;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

