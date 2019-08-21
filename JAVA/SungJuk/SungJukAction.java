package SungJuk;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SungJukAction {

	static Scanner input = new Scanner(System.in);
	ArrayList<SungJukDTO> list = new ArrayList<SungJukDTO>();
	String line;
	int num;

	public void SunjuckRun() {

		while (true) {
			meuePrint();
			num = input.nextInt();
			switch (num) {
			case 1:
				insertArticle();
				break;
			case 2:
				printArticle();
				break;
			case 3:
				searchArticle();
				break;
			case 4:
				deleteArticle();
				break;
			case 5:
				sortArticle();
				break;
			case 6:
				System.exit(0);
			default:
				System.out.println("�߸��Է��߽��ϴ�");
			}
		}

	}

	public void insertArticle() {
		boolean con = true;

		System.out.print("��ȣ�Է� : ");
		int num = input.nextInt();

		for (SungJukDTO id : list) {
			if (id.getId() == num)
				con = false;
		}

		if (con) {
			SungJukDTO tempSung = new SungJukDTO();

			tempSung.setId(num);
			System.out.print("�̸��Է� : ");
			tempSung.setName(input.next());
			System.out.print("���� �Է� :");
			tempSung.setKor(input.nextInt());
			System.out.print("���� �Է� : ");
			tempSung.setEng(input.nextInt());
			System.out.print("�����Է� :");
			tempSung.setMath(input.nextInt());

			int totalScore = tempSung.getKor() + tempSung.getEng() + tempSung.getMath();

			tempSung.setSumScore(totalScore);
			tempSung.setAvgScore((double)totalScore / 3);

			list.add(tempSung);
		} else {
			System.out.println("ID�� �ߺ��˴ϴ�.");
		}

	}

	public void printArticle() {
		System.out.println("��ȣ\t�̸�\t����\t����\t����\t����\t���");
		for (SungJukDTO sungJukDTO : list) {
			System.out.println(sungJukDTO.getId() + "\t" + sungJukDTO.getName() + "\t" + sungJukDTO.getKor() + "\t"
					+ sungJukDTO.getEng() + "\t" + sungJukDTO.getMath() + "\t" + sungJukDTO.getSumScore() + "\t"+new DecimalFormat("#.###").format(sungJukDTO.getAvgScore()));


		}
	}

	public void searchArticle() {
		boolean con = false;

		System.out.print("�˻� �� �̸� �Է� :");
		String searchName = input.next();
		System.out.println("��ȣ\t�̸�\t����\t����\t����\t����\t���");
		for (SungJukDTO sungJukDTO : list) {
			if (sungJukDTO.getName().equals(searchName)) {
				con = true;
				System.out.print(sungJukDTO.getId() + "\t" + sungJukDTO.getName() + "\t" + sungJukDTO.getKor() + "\t"
						+ sungJukDTO.getEng() + "\t" + sungJukDTO.getMath() + "\t" + sungJukDTO.getSumScore() + "\t"+
						new DecimalFormat("#.###").format(sungJukDTO.getAvgScore()));
			}
		}
		if (!con) {
			System.out.println("ã���� �ϴ� �̸��� �����ϴ�.");
		}

	}

	public void deleteArticle() {
		boolean con = false;

		System.out.print("���� �� �̸� �Է� :");
		String deleteName = input.next();

		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().compareTo(deleteName) == 0) {
				con = true;
				list.remove(i);
			}
		}
		
		if (!con) {
			System.out.println("�����ϰ��� �ϴ� �̸��� �����ϴ�.");
		}
		
	}

	public void sortArticle() {

		System.out.println("1. �̸����� ��������");
		System.out.println("2. �������� ��������");
		System.out.println("3. �����޴�");
		
		num = input.nextInt();
		
		switch(num)
		{
		case 1:
			Collections.sort(list, (s1,s2) -> s1.getName().compareTo(s2.getName()));
			break;
		case 2:
			Collections.sort(list, (s1,s2) -> s2.getSumScore()- s1.getSumScore() );
			break;
		case 3:
			break;
		default : 
			System.out.println("�߸��Է��߽��ϴ�.");
		}
		
	}

	public void meuePrint() {
		System.out.println("*************\n1. �Է�\n2. ���\n3. �˻�\n4. ����\n5. ����\n6. ��\n" + "*************\n  ��ȣ : ");
	}

}
