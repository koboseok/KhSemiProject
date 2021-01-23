package com.kh.semi.jointReply.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.jointReply.model.dao.JointReplyDAO;
import com.kh.semi.jointReply.model.vo.JointReply;





public class JointReplyService {

	private JointReplyDAO dao = new JointReplyDAO();

	/** 댓글 목록 조회 Service
	 * @param parentBoardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<JointReply> selectList(int parentBoardNo) throws Exception  {
		
		Connection conn = getConnection();
		
		List<JointReply> rList = dao.selectList(conn, parentBoardNo);
		
		close(conn);
		
		return rList;
	}

	/** 댓글 작성 Service
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(JointReply reply)throws Exception {
		Connection conn = getConnection();
		
		// 크로스 사이트 스크립팅 방지 처리
		String replyContent = reply.getReplyContent();
		
		
		replyContent = replaceParameter(replyContent);
		
		
		// 개행 문자 변환 처리 
		// ajax 통신 시 textarea의 개행 뭄ㄴ자가 \n 하나만 넘어옴.
		// \n --> <br> 
		replyContent = replyContent.replaceAll("\n", "<br>");
		
		
		// 변경된 replyContent를 다시 reply에 세팅
		reply.setReplyContent(replyContent);
		
		int result = dao.insertReply(conn, reply);
		
		// 트렌젝션
		
		if(result > 0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		
		
		close(conn);
		
		return result;
		
	}
	// 크로스 사이트 스크립트 방지 메소드
		private String replaceParameter(String param) {
			String result = param;
			if(param != null) {
				result = result.replaceAll("&", "&amp;");
				result = result.replaceAll("<", "&lt;");
				result = result.replaceAll(">", "&gt;");
				result = result.replaceAll("\"", "&quot;");
			}
			
			return result;
		}

		/** 댓글 수정 Service
		 * @param reply
		 * @return result
		 * @throws Exception
		 */
		public int updateReply(JointReply reply)throws Exception {

			Connection conn = getConnection();
			
			
			// 크로스 사이트 스크립팅 방지 처리
			String replyContent = reply.getReplyContent();
			
			
			replyContent = replaceParameter(replyContent);
			
			
			// 개행 문자 변환 처리 
			// ajax 통신 시 textarea의 개행 뭄ㄴ자가 \n 하나만 넘어옴.
			// \n --> <br> 
			replyContent = replyContent.replaceAll("\n", "<br>");
			
			
			// 변경된 replyContent를 다시 reply에 세팅
			reply.setReplyContent(replyContent);
			
			int result = dao.updateReply(conn, reply);
			
			if(result > 0) {
				commit(conn);
			}else {
			rollback(conn);	
			}
			
			close(conn);
			
			return result;
		}

		/** 댓글 삭제(상태 변경 )Service
		 * @param replyNo
		 * @return result
		 * @throws Exception
		 */
		public int updateReplyStatus(int replyNo)throws Exception {
			
			Connection conn = getConnection();
			
			int result = dao.updateReplyStatus(conn, replyNo);
			
			if(result > 0) {
				commit(conn);
			}else{
				rollback(conn);
			}
			
			close(conn);
			
			
			return result;
		}

	
}
