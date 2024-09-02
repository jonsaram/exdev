package exdev.com.common.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import exdev.com.ExdevCommonAPI;
import exdev.com.common.vo.SessionVO;

@Repository("ExdevCommonDao")
public class ExdevCommonDao {
	
	@Autowired(required=false)
	@Qualifier("sqlSession")
	private SqlSessionTemplate sqlMainSession;
	
	
	private void printLog(String queryId, Object obj) {
		Map map = (Map)obj;
		String userId = "";
		SessionVO sessionVo = (SessionVO)map.get("sessionVo");
		if ( ExdevCommonAPI.isValid(sessionVo) ) {
			userId = (String)sessionVo.getUserId();
		}
		String today = ExdevCommonAPI.getToday("yyyy-MM-dd HH:mm:ss");
		System.out.println("=================================================");
		System.out.println(today + " / " + userId + " / Request Query Id: " + queryId);
		System.out.println("=================================================");
	}
	
	@SuppressWarnings("rawtypes")
	public List<Map> getList(String queryId, Object obj) throws Exception {
		printLog(queryId, obj);
		return sqlMainSession.selectList(queryId, obj);
	}
	public Object getObject(String queryId, Object obj) throws Exception {
		printLog(queryId, obj);
		return sqlMainSession.selectOne(queryId, obj);
	}
	public int update(String queryId, Object obj) throws Exception {
		printLog(queryId, obj);
		return sqlMainSession.update(queryId, obj);
	}
	public int insert(String queryId, Object obj) throws Exception {
		printLog(queryId, obj);
		return sqlMainSession.insert(queryId, obj);
	}
    public int delete(String queryId, Object obj) throws Exception {
		printLog(queryId, obj);
        return sqlMainSession.delete(queryId, obj);
    }
}