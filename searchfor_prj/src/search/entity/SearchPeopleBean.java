package search.entity;

public class SearchPeopleBean {
	
	private String m_name;//失踪者姓名
    private String m_sex;//失踪者性别
    private String m_borndate;//失踪者出生日期
    private String height;//失踪者失踪时大致身高
    private String m_missdate;//失踪日期
    private String isBlood;//是否采血
    private String isReport;//是否报案
    private String m_native;//失踪人籍贯
    private String m_missadd;//失踪地点
    private String m_fearture;//失踪人特征描述
    private String m_process;//失踪经过
    private String m_family;//家庭背景及其线索资料
    private String y_name;//联系人姓名
    private String y_phone;//联系人手机
    private String y_email;//联系人邮箱
    private String y_address;//联系人现住址
    private String y_relation;//联系人与失踪者联系

  

    public SearchPeopleBean(String m_name, String m_sex, String m_borndate, String height, String m_missdate, String isBlood, String isReport, String m_native, String m_missadd, String m_fearture, String m_process, String m_family, String y_name, String y_phone, String y_email, String y_address, String y_relation) {
        this.m_name = m_name;
        this.m_sex = m_sex;
        this.m_borndate = m_borndate;
        this.height = height;
        this.m_missdate = m_missdate;
        this.isBlood = isBlood;
        this.isReport = isReport;
        this.m_native = m_native;
        this.m_missadd = m_missadd;
        this.m_fearture = m_fearture;
        this.m_process = m_process;
        this.m_family = m_family;
        this.y_name = y_name;
        this.y_phone = y_phone;
        this.y_email = y_email;
        this.y_address = y_address;
        this.y_relation = y_relation;

    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_sex() {
        return m_sex;
    }

    public void setM_sex(String m_sex) {
        this.m_sex = m_sex;
    }

    public String getM_borndate() {
        return m_borndate;
    }

    public void setM_borndate(String m_borndate) {
        this.m_borndate = m_borndate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getM_missdate() {
        return m_missdate;
    }

    public void setM_missdate(String m_missdate) {
        this.m_missdate = m_missdate;
    }

    public String getIsBlood() {
        return isBlood;
    }

    public void setIsBlood(String isBlood) {
        this.isBlood = isBlood;
    }

    public String getIsReport() {
        return isReport;
    }

    public void setIsReport(String isReport) {
        this.isReport = isReport;
    }

    public String getM_native() {
        return m_native;
    }

    public void setM_native(String m_native) {
        this.m_native = m_native;
    }

    public String getM_missadd() {
        return m_missadd;
    }

    public void setM_missadd(String m_missadd) {
        this.m_missadd = m_missadd;
    }

    public String getM_fearture() {
        return m_fearture;
    }

    public void setM_fearture(String m_fearture) {
        this.m_fearture = m_fearture;
    }

    public String getM_process() {
        return m_process;
    }

    public void setM_process(String m_process) {
        this.m_process = m_process;
    }

    public String getM_family() {
        return m_family;
    }

    public void setM_family(String m_family) {
        this.m_family = m_family;
    }

    public String getY_name() {
        return y_name;
    }

    public void setY_name(String y_name) {
        this.y_name = y_name;
    }

    public String getY_phone() {
        return y_phone;
    }

    public void setY_phone(String y_phone) {
        this.y_phone = y_phone;
    }

    public String getY_email() {
        return y_email;
    }

    public void setY_email(String y_email) {
        this.y_email = y_email;
    }

    public String getY_address() {
        return y_address;
    }

    public void setY_address(String y_address) {
        this.y_address = y_address;
    }

    public String getY_relation() {
        return y_relation;
    }

    public void setY_relation(String y_relation) {
        this.y_relation = y_relation;
    }

}
