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

	private String userId; 
	private String userNm;
	private String grade;
	private String email;
	private String spCstmId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSpCstmId() {
		return spCstmId;
	}
	public void setSpCstmId(String spCstmId) {
		this.spCstmId = spCstmId;
	}

	
}
