package com.kh.semi.admin.model.dao;
import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.semi.admin.model.vo.PageInfo;
import com.kh.semi.admin.model.vo.Report;
import com.kh.semi.member.model.vo.Member;

public class AdminDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public AdminDAO() {
		String fileName = AdminDAO.class.getResource("/com/kh/semi/sql/admin/admin-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	
	}
	
	/** 전체 일반 회원 수 계산 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception {
		int listCount = 0;
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
	
		return listCount;
	}

	/**회원 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> selectMemberList(Connection conn, PageInfo pInfo) throws Exception {
		List<Member> mList = null;
		String query = prop.getProperty("selectMemberList");
		
		try {
			int startRow = (pInfo.getCurrentPage() -1) * pInfo.getLimit()+1;
			int endRow = startRow + pInfo.getLimit()-1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			mList = new ArrayList<Member>();
			
			while(rset.next()) {
				Member member = new Member(rset.getInt("MEM_NO"),
											rset.getString("MEM_EMAIL"),
											rset.getString("MEM_NM"),
											rset.getString("MEM_PHONE"));
				mList.add(member);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return mList;
	}

	/** 회원정보에 구독서비스를 합치는 dao 
	 * @param conn
	 * @param mList 
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> addSubList(Connection conn, List<Member> mList) throws Exception {
		String query = prop.getProperty("addSubList");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			for(Member mem : mList) {
				pstmt.setInt(1, mem.getMemNo());
				rset = pstmt.executeQuery();
				if(rset.next()) {
					mem.setMemSub(rset.getString(1));
				}
			}
			
		} finally {
			close(rset);
			close(conn);
		}
		return mList;
	}

	/** 정지회원으로 전환 DAO
	 * @param conn
	 * @param object
	 * @return result
	 * @throws Exception
	 */
	public int updateGradeB(Connection conn, int memNo) throws Exception {
		int result = 0;
		String query = prop.getProperty("updateGradeB");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			result = pstmt.executeUpdate();
		}finally{
			close(pstmt);
		}
		return result;
	}
	
	
	
	/** 신고 내용 입력 dao
	 * @param conn
	 * @param report
	 * @return
	 * @throws Exception
	 */
	public int reportMember(Connection conn, Report report) throws Exception {
		int result = 0;
		String query = prop.getProperty("reportMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, report.getReportReason());
			pstmt.setDate(2, report.getReportDt());
			pstmt.setInt(3, report.getReportBNo());
			pstmt.setString(4, report.getReportBcNo());
			pstmt.setInt(5, report.getReportMemNo());
			pstmt.setString(6, report.getBoardCode());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 전체 불량 회원 수 계산 DAO
	 * @param conn
	 * @return bListCount
	 * @throws Exception
	 */
	public int getbPageInfo(Connection conn) throws Exception {
		int bListCount = 0;
		String query = prop.getProperty("getbPageInfo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				bListCount = rset.getInt(1);
			}
			
		}finally {
			close(rset);
			close(stmt);
		}
		
		return bListCount;
	}
	

	/**불량회원 목록 조회 dao
	 * @param conn
	 * @param pInfo
	 * @return bmList
	 * @throws Exception
	 */
	public List<Report> selectBmemberList(Connection conn, PageInfo pInfo) throws Exception {
		List<Report> bmList = null;
		String query = prop.getProperty("selectBmemberList");
		
		try {
			int startRow = (pInfo.getCurrentPage()-1) * pInfo.getLimit() +1;
			int endRow = startRow + pInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			bmList = new ArrayList<Report>();
			while(rset.next()) {
				Report report = new Report(rset.getInt("REPORT_NO"),
										   rset.getString("REPORT_REASON"),
										   rset.getDate("REPORT_DT"),
										   rset.getInt("REPORT_B_NO"),
										   rset.getString("REPORT_B_C_NO"),
										   rset.getInt("MEM_NO"),
										   rset.getString("BOARD_CODE"),
										   rset.getString("MEM_NM"),
										   rset.getString("MEM_EMAIL"));
				bmList.add(report);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bmList;
	}

	/**일반 회원으로 전환 dao
	 * @param conn
	 * @param memNo
	 * @return result
	 * @throws Exception
	 */
	public int convertMember(Connection conn, int memNo) throws Exception {
		int result = 0;
		String query = prop.getProperty("convertMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}






}
