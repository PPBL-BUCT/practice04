package guozixuan;

import java.io.*;
import java.net.*;

public class ClientTest{
  public static void main(String[] args){
    try{
      //����sever��
      Socket s1 = new Socket("127.0.0.1",8888);
      //ʵ����������
      InputStream is = s1.getInputStream();
      DataInputStream dis = new DataInputStream(is);
      //ʵ���������
      OutputStream os = s1.getOutputStream();
      DataOutputStream dos = new DataOutputStream(os);
      //ʵ������������
      Thread mcr = new MyClientReader(dis);
      Thread mcw = new MyClientWriter(dos);
      //������������
      mcr.start();
      mcw.start();
      //�����쳣
    }catch(SocketException e){
      System.out.println(e);
    }catch(IOException e){
      System.out.println(e);
    }  
  }
}
//����һ�������������н��ն�ȡ����
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
      System.out.println("�Է�˵:"+msg);
      if(msg.equals("bye")){
        System.out.println("�Է�����,�����˳�");
        System.exit(0);
      }
    }
    }catch(IOException e){
      System.out.println(e);
    }
  }
}
//����һ����������д�벢��������
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
        System.out.println("�Լ�����,�����˳�");
        System.exit(0);
      }
    }
    }catch(IOException e){
      System.out.println(e);
    }
  }
}