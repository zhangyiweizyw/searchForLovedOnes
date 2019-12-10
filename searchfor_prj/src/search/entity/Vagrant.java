package search.entity;

public class Vagrant {
	
	private String name;//����������
    private String sex;//�������Ա�
    private String age;//�����ߴ�Լ����
    private String findaddress;//�����߷��ֵ�ַ

    private String begintime;//��ʼ����ʱ��
    private String targetfamily;//Ŀ���ͥ��Ϣ
    private String describe;//��������������
    private String phonenumber;//��������ϵ��ʽ

    public Vagrant(String name, String sex, String age, String findaddress, String begintime, String targetfamily, String describe, String phonenumber) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.findaddress = findaddress;

        this.begintime = begintime;
        this.targetfamily = targetfamily;
        this.describe = describe;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFindaddress() {
        return findaddress;
    }

    public void setFindaddress(String findaddress) {
        this.findaddress = findaddress;
    }


    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getTargetfamily() {
        return targetfamily;
    }

    public void setTargetfamily(String targetfamily) {
        this.targetfamily = targetfamily;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

}
