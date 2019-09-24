package board.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.bean.BoardDTO;
import member.dao.MemberDAO;

public class BoardDAO {

	private static BoardDAO instance;
	private SqlSessionFactory sqlSessionFactory;

	public static BoardDAO getInstance() {
		if (instance == null) {
			synchronized (MemberDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}

	public BoardDAO() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int boardWrite(BoardDTO boardDTO) {
		int su = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		su = sqlSession.insert("boardSQL.boardWrite", boardDTO);
		sqlSession.commit();
		sqlSession.close();
		return su;
	}

	public List<BoardDTO> boardList(Map<String, Integer> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.boardList", map);
		sqlSession.close();

		return list;
	}

	public List<BoardDTO> boardSearchList(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.boardSearchList", map);
		sqlSession.close();

		return list;
	}

	public int getTotalA() {
		int totalA = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		totalA = sqlSession.selectOne("boardSQL.getTotalA");
		sqlSession.close();
		return totalA;
	}

	public int getSearch(Map<String, String> map) {
		int totalA = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		totalA = sqlSession.selectOne("boardSQL.getSearch", map);
		sqlSession.close();
		return totalA;
	}

	public BoardDTO getboard(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO temp = sqlSession.selectOne("boardSQL.getboard", seq);
		sqlSession.close();
		return temp;
	}

	public int updateMap(Map<String, String> map) {
		int su = 0;

		SqlSession sqlSession = sqlSessionFactory.openSession();
		su = sqlSession.update("boardSQL.boardUpdate", map);
		sqlSession.commit();
		sqlSession.close();

		return su;

	}

	public void boardReply(BoardDTO boardDTO) {

		BoardDTO pDTO = getboard(boardDTO.getPseq()); // ����
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// step update (�ۼ��� ����)
		sqlSession.update("boardSQL.boardReply1", pDTO);

		// insert ����� �־���
		boardDTO.setRef(pDTO.getRef()); // ������ �׷��ȣ
		boardDTO.setLev(pDTO.getLev() + 1); // ����
		boardDTO.setStep(pDTO.getStep() + 1); // �ۼ���

		sqlSession.insert("boardSQL.boardReply2", boardDTO);

		// reply update
		sqlSession.update("boardSQL.boardReply3", boardDTO.getPseq());
		sqlSession.commit();
		sqlSession.close();
	}

	public void boardDelete(BoardDTO boardDTO) {
		BoardDTO pDTO = getboard(boardDTO.getPseq()); // ����
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// BB�� ������ ã�Ƽ� ��ۼ� 1���� update
		if (pDTO != null) {
			int pseq = pDTO.getSeq();
			sqlSession.update("boardSQL.boardDelete1", pseq);
		}
		int seq = boardDTO.getSeq();

		if (boardDTO.getReply() > 0) {
			// BB�� ����� ã�Ƽ� ���� �߰� update
			sqlSession.update("boardSQL.boardDelete2", seq);
		}
		// BB���� delete
		sqlSession.delete("boardSQL.boardDelete3", seq);

		sqlSession.commit();
		sqlSession.close();
	}

	/*
	 * public int update(BoardDTO boardDTO) { int su = 0;
	 * 
	 * String sql = "update board set subject =?, content=? where seq=?";
	 * 
	 * try { conn = ds.getConnection(); pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, boardDTO.getSubject()); pstmt.setString(2,
	 * boardDTO.getContent()); pstmt.setInt(3, boardDTO.getSeq());
	 * 
	 * 
	 * su = pstmt.executeUpdate();
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } finally { try { if (pstmt != null) pstmt.close(); if
	 * (conn != null) conn.close(); if (rs != null) rs.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } }
	 * 
	 * 
	 * 
	 * return su; } public int boardHit(int seq) { int su = 0;
	 * 
	 * SqlSession sqlSession = sqlSessionFactory.openSession(); su =
	 * sqlSession.update("boardSQL.boardHit", seq); sqlSession.commit();
	 * sqlSession.close();
	 * 
	 * return su; }
	 */
}
