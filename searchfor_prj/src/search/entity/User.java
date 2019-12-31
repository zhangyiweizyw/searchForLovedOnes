package search.entity;


public class User {
	private int id;
    private String userName;
    private String userPwd;
    private String userType;
    private String userEmail;
    private String userTel;
    private String user_uri;
    public User(String userName, String userPwd, String userType, String userEmail, String userTel) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userType = userType;
        this.userEmail = userEmail;
        this.userTel = userTel;
    }
    
    public User(String userName, String userPwd, String userType, String userEmail, String userTel,String user_uri) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userType = userType;
        this.userEmail = userEmail;
        this.userTel = userTel;
        this.user_uri=user_uri;
    }

    public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
    public String getUser_uri() {
        return user_uri;
    }

    public void setUser_uri(String user_uri) {
        this.user_uri = user_uri;
    }
}
