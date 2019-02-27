package guozixuan;

import java.io.*;
import java.net.*;

public class ServerTest{
  public static void main(String[] args){
    try{
      //设置sever端的链接
      ServerSocket s = new ServerSocket(8888);
      Socket s1 = s.accept();//sever等待链接
      
      //实例化输出流
      OutputStream os = s1.getOutputStream();
      DataOutputStream dos = new DataOutputStream(os);
      //实例化输入流
      InputStream is = s1.getInputStream();
      DataInputStream dis = new DataInputStream(is);
      //实例化两个线程的对象
      Thread msr = new MyServerReader(dis);
      Thread msw = new MyServerWriter(dos);
      //启动线程
      msr.start();
      msw.start();
    }
    //捕获异常
    catch(SocketException e){
      System.out.println(e);
    }catch(IOException e){
      System.out.println(e);
    }
  }
}
//创建一个进程用来进行接收读取数据
class MyServerReader extends Thread{
  private DataInputStream dis;
  public MyServerReader (DataInputStream dis){
    this.dis = dis;
  }
  public void run(){
    String msg;
    try{
      while(true){
        msg = dis.readUTF();
        System.out.println("对方说:" + msg);
        if(msg.equals("bye")){
          System.out.println("对方下线,程序退出");
          System.exit(0);
        }
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }
}
//创建一个进程用来写入并发送数据
class MyServerWriter extends Thread{
  private DataOutputStream dos;
  public MyServerWriter(DataOutputStream dos){
    this.dos = dos;
  }
  public void run(){
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String msg;
    try{
      while(true){
        msg = br.readLine();
        dos.writeUTF(msg);
        if(msg.equals("bye")){
          System.out.println("自己下线,程序退出");
          System.exit(0);
        }
      }
      }catch(IOException e){
      System.out.println(e);
    }
  }
}