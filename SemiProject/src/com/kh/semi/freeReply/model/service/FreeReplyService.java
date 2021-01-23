package com.kh.semi.reply.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.reply.model.dao.FreeReplyDAO;
import com.kh.semi.reply.model.vo.FreeReply;



public class FreeReplyService {

	private FreeReplyDAO dao = new FreeReplyDAO();

	/** 댓글 목록 조회 Service
	 * @param parentBoardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<FreeReply> selectFList(int fBoardNo) throws Exception  {
		
		Connection conn = getConnection();
		
		List<FreeReply> fReplyList = dao.selectFList(conn, fBoardNo);
		
		close(conn);
		
		return fReplyList;
	}

	/** 댓글 작성 Service
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertFReply(FreeReply fReply)throws Exception {
		
		Connection conn = getConnection();
		
		// 크로스 사이트 스크립팅 방지 처리
		String fReplyContent = fReply.getfReplyContent();
		
		
		fReplyContent = replaceParameter(fReplyContent);
		
		
		// 개행 문자 변환 처리 
		// ajax 통신 시 textarea의 개행 뭄ㄴ자가 \n 하나만 넘어옴.
		// \n --> <br> 
		fReplyContent = fReplyContent.replaceAll("\n", "<br>");
		
		
		// 변경된 replyContent를 다시 reply에 세팅
		fReply.setfReplyContent(fReplyContent);
		
		int result = dao.insertFReply(conn, fReply);
		
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
		public int updateFReply(FreeReply fReply)throws Exception {

			Connection conn = getConnection();
			
			
			// 크로스 사이트 스크립팅 방지 처리
			String fReplyContent = fReply.getfReplyContent();
			
			
			fReplyContent = replaceParameter(fReplyContent);
			
			
			// 개행 문자 변환 처리 
			// ajax 통신 시 textarea의 개행 뭄ㄴ자가 \n 하나만 넘어옴.
			// \n --> <br> 
			fReplyContent = fReplyContent.replaceAll("\n", "<br>");
			
			
			// 변경된 replyContent를 다시 reply에 세팅
			fReply.setfReplyContent(fReplyContent);
			
			int result = dao.updateFReply(conn, fReply);

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
		public int updateReplyStatus(int fReplyNo)throws Exception {
			
			Connection conn = getConnection();
			
			int result = dao.updateReplyStatus(conn, fReplyNo);
			
			if(result > 0) {
				commit(conn);
			}else{
				rollback(conn);
			}
			
			close(conn);
			
			
			return result;
		}

	
}
