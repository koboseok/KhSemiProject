package com.kh.semi.reply.model.dao;

import static com.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.freeBoard.model.dao.FreeBoardDAO;
import com.kh.semi.reply.model.vo.FreeReply;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

public class FreeReplyDAO {
   private Statement stmt = null;
   private PreparedStatement pstmt = null;
   private ResultSet rset = null;
   
   private Properties prop;
   
   public FreeReplyDAO() {
       String fileName = FreeBoardDAO.class.getResource("/com/kh/semi/sql/reply/reply-query.xml").getPath();
         try {
            prop = new Properties();
            prop.loadFromXML(new FileInputStream(fileName)); 
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

   /** 댓글 목록 조회 DAO
    * @param conn
    * @param parentBoardNo
    * @return rList
    * @throws Exception
    */
   public List<FreeReply> selectFList(Connection conn, int fBoardNo) throws Exception {
      List<FreeReply> fReplyList = null;
      String query = prop.getProperty("selectFList");
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setInt(1, fBoardNo);
         
         rset = pstmt.executeQuery();
         
         fReplyList = new ArrayList<FreeReply>();
         
         while(rset.next()) {
            FreeReply reply = new FreeReply();
            reply.setfReplyNo(rset.getInt("FR_RE_NO"));
            reply.setfReplyContent(rset.getString("FR_RE_CONTENT"));
            reply.setfReplyCreateDate(rset.getTimestamp("FR_RE_DT"));
            reply.setMemName(rset.getString("MEM_NM"));
            
            fReplyList.add(reply);
         }
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return fReplyList;
   }

   
   /** 댓글 작성 
 * @param conn
 * @param fReply
 * @return
 * @throws Exception
 */
public int insertFReply(Connection conn, FreeReply fReply) throws Exception{
     
	   int result = 0;
      
      String query = prop.getProperty("insertFReply");
      
      try {
         pstmt = conn.prepareStatement(query);
         
         pstmt.setString(1, fReply.getfReplyContent());
         pstmt.setString(2, fReply.getMemName());
         pstmt.setInt(3, fReply.getfBoardNo());
         
         result = pstmt.executeUpdate();
      } finally {
         close(pstmt);
      }
      return result;
   }

/** 댓글 수정 DAO*/
public int updateFReply(Connection conn, FreeReply fReply)throws Exception {
	
	int result = 0;
	
	String query = prop.getProperty("updateFReply");
	
	try {
		
		pstmt =conn.prepareStatement(query);
		
		pstmt.setString(1, fReply.getfReplyContent());
		pstmt.setInt(2, fReply.getfReplyNo());
		
		result = pstmt.executeUpdate();
		
		
		
	}finally {
		close(pstmt);
	}
	return result;
}

/** 댓글 삭제(상태 변경) DAO*/
public int updateReplyStatus(Connection conn, int fReplyNo)throws Exception {

	int result = 0;
	
	String query = prop.getProperty("updateReplyStatus");
	
	try {
		
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, fReplyNo);
		
		result = pstmt.executeUpdate();
		
		
		
		
	}finally {
		close(pstmt);
	}
	
	return result;
}  
   }

