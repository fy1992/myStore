package cn.dahe.dto;

/**
 * Created by fy on 2017/2/17.
 */
public class RegisterDto {
    private String loginName;
    private String password;
    private String checkPassword;
    private String mobile;
    private String email;
    private String storeName;
    private String addr;
    private int industryId;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", checkPassword='" + checkPassword + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", storeName='" + storeName + '\'' +
                ", addr='" + addr + '\'' +
                ", industryId=" + industryId +
                '}';
    }
}
