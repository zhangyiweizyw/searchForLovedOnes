package cn.edu.hebtu.software.xunqin;

public class SearchFamilyBean {

    private String l_name;//失踪者姓名
    private String l_sex;//失踪者性别
    private String l_borndate;//失踪者出生日期
    private String l_phone;//联系方式
    private String l_email;//邮箱
    private String lheight;//失踪者失踪时大致身高
    private String l_missdate;//失踪日期
    private String isBlood;//是否采血
    private String isReport;//是否报案
    private String l_native;//失踪人籍贯
    private String l_missaddr;//失踪地点
    private String l_fearture;//失踪人特征描述
    private String l_process;//失踪经过
    private String l_family;//家庭背景及其线索资料
    private String t_familyaddr;//目标家庭地址
    private String t_relationfamily;//与目标家庭联系
    private String t_describefamily;//目标家庭描述


    public SearchFamilyBean(String l_name, String l_sex, String l_borndate, String l_phone, String l_email, String lheight, String l_missdate, String isBlood, String isReport, String l_native, String l_missaddr, String l_fearture, String l_process, String l_family, String t_familyaddr, String t_relationfamily, String t_describefamily) {
        this.l_name = l_name;
        this.l_sex = l_sex;
        this.l_borndate = l_borndate;
        this.l_phone = l_phone;
        this.l_email = l_email;
        this.lheight = lheight;
        this.l_missdate = l_missdate;
        this.isBlood = isBlood;
        this.isReport = isReport;
        this.l_native = l_native;
        this.l_missaddr = l_missaddr;
        this.l_fearture = l_fearture;
        this.l_process = l_process;
        this.l_family = l_family;
        this.t_familyaddr = t_familyaddr;
        this.t_relationfamily = t_relationfamily;
        this.t_describefamily = t_describefamily;
    }

    public String getL_phone() {
        return l_phone;
    }

    public void setL_phone(String l_phone) {
        this.l_phone = l_phone;
    }

    public String getL_email() {
        return l_email;
    }

    public void setL_email(String l_email) {
        this.l_email = l_email;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getL_sex() {
        return l_sex;
    }

    public void setL_sex(String l_sex) {
        this.l_sex = l_sex;
    }

    public String getL_borndate() {
        return l_borndate;
    }

    public void setL_borndate(String l_borndate) {
        this.l_borndate = l_borndate;
    }

    public String getLheight() {
        return lheight;
    }

    public void setLheight(String lheight) {
        this.lheight = lheight;
    }

    public String getL_missdate() {
        return l_missdate;
    }

    public void setL_missdate(String l_missdate) {
        this.l_missdate = l_missdate;
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

    public String getL_native() {
        return l_native;
    }

    public void setL_native(String l_native) {
        this.l_native = l_native;
    }

    public String getL_missaddr() {
        return l_missaddr;
    }

    public void setL_missaddr(String l_missaddr) {
        this.l_missaddr = l_missaddr;
    }

    public String getL_fearture() {
        return l_fearture;
    }

    public void setL_fearture(String l_fearture) {
        this.l_fearture = l_fearture;
    }

    public String getL_process() {
        return l_process;
    }

    public void setL_process(String l_process) {
        this.l_process = l_process;
    }

    public String getL_family() {
        return l_family;
    }

    public void setL_family(String l_family) {
        this.l_family = l_family;
    }

    public String getT_familyaddr() {
        return t_familyaddr;
    }

    public void setT_familyaddr(String t_familyaddr) {
        this.t_familyaddr = t_familyaddr;
    }

    public String getT_relationfamily() {
        return t_relationfamily;
    }

    public void setT_relationfamily(String t_relationfamily) {
        this.t_relationfamily = t_relationfamily;
    }

    public String getT_describefamily() {
        return t_describefamily;
    }

    public void setT_describefamily(String t_describefamily) {
        this.t_describefamily = t_describefamily;
    }
}
