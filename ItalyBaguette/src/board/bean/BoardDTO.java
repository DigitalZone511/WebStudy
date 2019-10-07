package board.bean;
import lombok.Data;

@Data
public class BoardDTO {
	private int seq;
	private String id;
	private String name;
	private String email;
	private String subject;
	private String content;
	
	private int ref; //�׷�
	private int lev; //���� ����
	private int step; //�ۼ���
	private int pseq; //���۹�ȣ
	private int reply; //��ۼ�
	
	private int hit; //��ȸ��
	
	private String logtime;
	
}
