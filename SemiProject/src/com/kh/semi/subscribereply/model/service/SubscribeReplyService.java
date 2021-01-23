package com.kh.semi.subscribereply.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.subscribereply.model.dao.SubscribeReplyDAO;
import com.kh.semi.subscribereply.model.vo.SubscribeReply;

public class SubscribeReplyService {

	private SubscribeReplyDAO dao = new SubscribeReplyDAO();

	public List<SubscribeReply> selectList(String parentSubscribe) throws Exception {
		Connection conn = getConnection();
		List<SubscribeReply> srList = dao.selectList(conn, parentSubscribe);
		close(conn);
		return srList;

	}

	public int insertReply(SubscribeReply inReply) throws Exception {
		Connection conn = getConnection();

		// 크로스 사이트 스크립팅 방지 처리
		String replyContent = inReply.getReplyContent();

		replyContent = replaceParameter(replyContent);

		// 개행문자 변환 처리
		// ajax 통신 시 textarea의 개행문자가 \n 하나만 넘어옴.
		// \n -> <br>
		replyContent = replyContent.replaceAll("\n", "<br>");

		inReply.setReplyContent(replyContent);

		int result = dao.insertReply(conn, inReply);

		// 트랜잭션 처리
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	private String replaceParameter(String param) {
		String result = param;
		if (param != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}

		return result;
	}

	public int updateReply(SubscribeReply upReply) throws Exception {
		Connection conn = getConnection();
		
		String replyContent = upReply.getReplyContent();
		
		replyContent = replaceParameter(replyContent);
		
		// 개행문자 변환 처리
		// ajax 통신 시 textarea의 개행문자가 \n 하나만 넘어옴.
		// \n -> <br>
		replyContent = replyContent.replaceAll("\n", "<br>");
		
		upReply.setReplyContent(replyContent);
		
		int result = dao.updateReply(conn, upReply);
		
		if(result > 0) {
			commit(conn);			
		}
		else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int updateReplyStatus(int ReplyNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateReplyStatus(conn, ReplyNo);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
