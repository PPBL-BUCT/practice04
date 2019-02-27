package guozixuan;

import java.io.*;
import java.net.*;

public class ServerTest{
  public static void main(String[] args){
    try{
      //����sever�˵�����
      ServerSocket s = new ServerSocket(8888);
      Socket s1 = s.accept();//sever�ȴ�����
      
      //ʵ���������
      OutputStream os = s1.getOutputStream();
      DataOutputStream dos = new DataOutputStream(os);
      //ʵ����������
      InputStream is = s1.getInputStream();
      DataInputStream dis = new DataInputStream(is);
      //ʵ���������̵߳Ķ���
      Thread msr = new MyServerReader(dis);
      Thread msw = new MyServerWriter(dos);
      //�����߳�
      msr.start();
      msw.start();
    }
    //�����쳣
    catch(SocketException e){
      System.out.println(e);
    }catch(IOException e){
      System.out.println(e);
    }
  }
}
//����һ�������������н��ն�ȡ����
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
        System.out.println("�Է�˵:" + msg);
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
          System.out.println("�Լ�����,�����˳�");
          System.exit(0);
        }
      }
      }catch(IOException e){
      System.out.println(e);
    }
  }
}