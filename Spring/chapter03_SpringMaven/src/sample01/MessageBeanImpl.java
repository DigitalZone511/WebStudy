package sample01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//Target
@Component
public class MessageBeanImpl implements MessageBean {
	private @Value("Have a nice day!!")String str;

	@Override
	public void showPrintBefore() {
		System.out.println("showPrintBefore �޽��� = "+str);//�ٽɰ��ɻ���
	}

	@Override
	public void viewPrintBefore() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrintBefore �޽��� = "+str);//�ٽɰ��ɻ���
		
	}
	
	@Override
	public void showPrintAfter() {
		System.out.println("showPrintAfter �޽��� = "+str);//�ٽɰ��ɻ���
	}

	@Override
	public void viewPrintAfter() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrintAfter �޽��� = "+str);//�ٽɰ��ɻ���		
	}

	@Override
	public String showPrint() {
		
		System.out.println("showPrint �޽��� = "+str);//�ٽɰ��ɻ���
		return "KIN";
		
	}

	@Override
	public void viewPrint() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrint �޽��� = "+str);//�ٽɰ��ɻ���		
	}
	
	@Override
	public void display() {
		System.out.println("display �޽��� = "+str);//�ٽɰ��ɻ���		
	}

}

























