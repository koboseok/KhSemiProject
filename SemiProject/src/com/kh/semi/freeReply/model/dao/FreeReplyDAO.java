package com.kh.semi.freeReply.model.dao;

import static com.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.freeBoard.model.vo.Board;
import com.kh.semi.freeReply.model.vo.FreeReply;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

public class FreeReplyDAO {
   private Statement stmt = null;
   private PreparedStatement pstmt = null;
   private ResultSet rset = null;
   
   private Properties prop;
   
   public FreeReplyDAO() {
       String fileName = Board.class.getResource("/com/kh/semi/sql/reply/FreeReply-query.xml").getPath();
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
   public List<FreeReply> selectList(Connection conn, int parentBoardNo) throws Exception {
      List<FreeReply> rList = null;
      String query = prop.getProperty("selectList");
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setInt(1, parentBoardNo);
         
         rset = pstmt.executeQuery();
         
         rList = new ArrayList<FreeReply>();
         
         while(rset.next()) {
            FreeReply reply = new FreeReply();
            reply.setReplyNo(rset.getInt("REPLY_NO"));
            reply.setReplyContent(rset.getString("REPLY_CONTENT"));
            reply.setReplyCreateDate(rset.getTimestamp("REPLY_CREATE_DT"));
            reply.setMemName(rset.getString("MEM_NM"));
            
            rList.add(reply);
         }
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return rList;
   }

   /** 
    * @param conn
    * @param reply
    * @return result
    * @throws Exception
    */
   public int insertReply(Connection conn, FreeReply reply) throws Exception{
      int result = 0;
      
      String query = prop.getProperty("insertReply");
      
      try {
         pstmt = conn.prepareStatement(query);
         
         pstmt.setString(1, reply.getReplyContent());
         pstmt.setString(2, reply.getMemName());
         pstmt.setInt(3, reply.getParentBoardNo());
         
         result = pstmt.executeUpdate();
      } finally {
         close(pstmt);
      }
      return result;
   }

/** 댓글 수정 DAO
 * @param conn
 * @param reply
 * @return result
 * @throws Exception
 */
public int updateReply(Connection conn, FreeReply reply)throws Exception {
	
	int result = 0;
	
	String query = prop.getProperty("updateReply");
	
	try {
		
		pstmt =conn.prepareStatement(query);
		
		pstmt.setString(1, reply.getReplyContent());
		pstmt.setInt(2, reply.getReplyNo());
		
		result = pstmt.executeUpdate();
		
		
		
	}finally {
		close(pstmt);
	}
	return result;
}

/** 댓글 삭제(상태 변경) DAO
 * @param conn
 * @param replyNo
 * @return result
 * @throws Exception
 */
public int updateReplyStatus(Connection conn, int replyNo)throws Exception {

	int result = 0;
	
	String query = prop.getProperty("updateReplyStatus");
	
	try {
		
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, replyNo);
		
		result = pstmt.executeUpdate();
		
		
		
		
	}finally {
		close(pstmt);
	}
	
	return result;
}  
   }

