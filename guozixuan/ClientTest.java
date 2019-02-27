package guozixuan;

import java.io.*;
import java.net.*;

public class ClientTest{
  public static void main(String[] args){
    try{
      //链接sever端
      Socket s1 = new Socket("127.0.0.1",8888);
      //实例化输入流
      InputStream is = s1.getInputStream();
      DataInputStream dis = new DataInputStream(is);
      //实例化输出流
      OutputStream os = s1.getOutputStream();
      DataOutputStream dos = new DataOutputStream(os);
      //实例化两个进程
      Thread mcr = new MyClientReader(dis);
      Thread mcw = new MyClientWriter(dos);
      //启动两个进程
      mcr.start();
      mcw.start();
      //捕获异常
    }catch(SocketException e){
      System.out.println(e);
    }catch(IOException e){
      System.out.println(e);
    }  
  }
}
//创建一个进程用来进行接收读取数据
class MyClientReader extends Thread{
  private DataInputStream dis;
  public MyClientReader(DataInputStream dis){
    this.dis = dis;
  }
  @Override
  public void run(){
    String msg;
    try{
      while(true){
      msg = dis.readUTF();
      System.out.println("对方说:"+msg);
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
class MyClientWriter extends Thread{
  private DataOutputStream dos;
  public MyClientWriter(DataOutputStream dos){
    this.dos = dos;
  }
  @Override
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