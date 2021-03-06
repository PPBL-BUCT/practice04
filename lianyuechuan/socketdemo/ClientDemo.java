package test2;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
 
public class ClientDemo {
	public static void testClient(){
		//创建服务器对象
		System.out.println("正在向服务器请求连接。。。");
		Socket socket = null;
		Scanner keybordscanner = null;
		Scanner inScanner = null;
		PrintWriter pwtoserver = null;
		try {
			//输入链接地址
			Socket socket1 = new Socket(InetAddress.getLocalHost(),6666);
			inScanner = new Scanner(socket1.getInputStream()); 
			System.out.println(inScanner.nextLine());
			pwtoserver = new PrintWriter(socket1.getOutputStream());
			System.out.print("我(客户端)：");
			//先读取键盘录入方可向服务端发送消息
			keybordscanner = new Scanner(System.in);
			while(keybordscanner.hasNextLine()){
				String keyborddata = keybordscanner.nextLine();
				//展示到己方的控制台
				System.out.println("我(客户端)："+keyborddata);
				//写到服务端的的控制台
				pwtoserver.println(keyborddata);
				pwtoserver.flush();
				//阻塞等待接收服务端的消息
				String indata = inScanner.nextLine();
				System.out.println("服务端："+indata);
				System.out.print("我(客户端)：");
			}
		} catch (UnknownHostException e) {
			// 异常处理
			e.printStackTrace();
		} catch (IOException e) {
			// 异常处理
			e.printStackTrace();
		}finally {
			//关闭文件
			keybordscanner.close();
			pwtoserver.close();
			inScanner.close();
			try {
				socket.close();
			} catch (IOException e) {
				// 异常处理
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		testClient();
	}
}
