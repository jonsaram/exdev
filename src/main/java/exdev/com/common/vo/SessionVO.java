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
	private String spCstmCd;
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
	public String getSpCstmCd() {
		return spCstmCd;
	}
	public void setSpCstmCd(String spCstmCd) {
		this.spCstmCd = spCstmCd;
	}
	
}
