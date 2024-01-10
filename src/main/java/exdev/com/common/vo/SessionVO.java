/**
 *
 */
package exdev.com.common.vo;

import java.util.HashMap;

/**
 * @author ks5011.kim
 *
 */
public class SessionVO {

	private static final long serialVersionUID = 1L;

	private String userId; // empNo를 user id로 사용하고 있다.
	private String userName;
	private String deptCode;
	private String deptName;
	private String compCode;
	private String compName;
	private String gradeCode;
	private String gradeName;
	private String epId;
	private String emailAddress;
	private String phoneNumber;
	private String lastLogin;
	private String ssoKey;
	private String loginId;
	private String loginTime;
	private String loginIp;
	private long managerAuthGrade;
	private long projectAuthGrade;
	private long urlAuthGrade;
	
	
	private String singleId; //userId는 singleId로 사용한다.
	private String teamLeaderAuth;
	private String teamAdminAuth;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getEpId() {
		return epId;
	}
	public void setEpId(String epId) {
		this.epId = epId;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getSsoKey() {
		return ssoKey;
	}
	public void setSsoKey(String ssoKey) {
		this.ssoKey = ssoKey;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public long getManagerAuthGrade() {
		return managerAuthGrade;
	}
	public void setManagerAuthGrade(long managerAuthGrade) {
		this.managerAuthGrade = managerAuthGrade;
	}
	public long getProjectAuthGrade() {
		return projectAuthGrade;
	}
	public void setProjectAuthGrade(long projectAuthGrade) {
		this.projectAuthGrade = projectAuthGrade;
	}
	public long getUrlAuthGrade() {
		return urlAuthGrade;
	}
	public void setUrlAuthGrade(long urlAuthGrade) {
		this.urlAuthGrade = urlAuthGrade;
	}
	public String getSingleId() {
		return singleId;
	}
	public void setSingleId(String singleId) {
		this.singleId = singleId;
	}
	public String getTeamLeaderAuth() {
		return teamLeaderAuth;
	}
	public void setTeamLeaderAuth(String teamLeaderAuth) {
		this.teamLeaderAuth = teamLeaderAuth;
	}
	public String getTeamAdminAuth() {
		return teamAdminAuth;
	}
	public void setTeamAdminAuth(String teamAdminAuth) {
		this.teamAdminAuth = teamAdminAuth;
	}
}
