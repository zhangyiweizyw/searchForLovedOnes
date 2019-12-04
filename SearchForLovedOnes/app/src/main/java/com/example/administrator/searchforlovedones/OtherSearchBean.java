package cn.edu.hebtu.software.xunqin;

public class OtherSearchBean {
    private String s_name;//被寻者姓名
    private String s_sex;//被寻者性别
    private String s_reason;//寻人原因及其他线索资料
    private String relation;//与被寻者联系
    private String y_name;//寻人者姓名
    private String y_sex;//寻人者性别
    private int y_age;//寻人者年龄
    private String y_email;//寻人者邮箱
    private String y_phone;//寻人者电话
    private String y_address;//寻人者住址

    public OtherSearchBean(String s_name, String s_sex, String s_reason, String relation, String y_name, String y_sex, int y_age, String y_email, String y_phone, String y_address) {
        this.s_name = s_name;
        this.s_sex = s_sex;
        this.s_reason = s_reason;
        this.relation = relation;
        this.y_name = y_name;
        this.y_sex = y_sex;
        this.y_age = y_age;
        this.y_email = y_email;
        this.y_phone = y_phone;
        this.y_address = y_address;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_sex() {
        return s_sex;
    }

    public void setS_sex(String s_sex) {
        this.s_sex = s_sex;
    }

    public String getS_reason() {
        return s_reason;
    }

    public void setS_reason(String s_reason) {
        this.s_reason = s_reason;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getY_name() {
        return y_name;
    }

    public void setY_name(String y_name) {
        this.y_name = y_name;
    }

    public String getY_sex() {
        return y_sex;
    }

    public void setY_sex(String y_sex) {
        this.y_sex = y_sex;
    }

    public int getY_age() {
        return y_age;
    }

    public void setY_age(int y_age) {
        this.y_age = y_age;
    }

    public String getY_email() {
        return y_email;
    }

    public void setY_email(String y_email) {
        this.y_email = y_email;
    }

    public String getY_phone() {
        return y_phone;
    }

    public void setY_phone(String y_phone) {
        this.y_phone = y_phone;
    }

    public String getY_address() {
        return y_address;
    }

    public void setY_address(String y_address) {
        this.y_address = y_address;
    }
}
