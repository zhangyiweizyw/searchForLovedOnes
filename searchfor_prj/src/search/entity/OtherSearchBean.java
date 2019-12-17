package search.entity;

public class OtherSearchBean {
	
	private String s_name;//��Ѱ������
    private String s_sex;//��Ѱ���Ա�
    private String s_reason;//Ѱ��ԭ��������������
    private String relation;//�뱻Ѱ����ϵ
    private String y_name;//Ѱ��������
    private String y_sex;//Ѱ�����Ա�
    private int y_age;//Ѱ��������
    private String y_email;//Ѱ��������
    private String y_phone;//Ѱ���ߵ绰
    private String y_address;//Ѱ����סַ
    private int user_id;
    public OtherSearchBean(String s_name, String s_sex, String s_reason, String relation, String y_name, String y_sex, int y_age, String y_email, String y_phone, String y_address,int user_id) {
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
        this.user_id=user_id;
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
    public int getUser_id(){
        return this.user_id;
    }
    public void setUser_id(int user_id){this.user_id=user_id;}
}
