package guozixuan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
 
public class SocketClient {
    // ��ͻ���
    public static void main(String[] args) throws IOException {
        //�����ͻ��ˣ������ӷ�����
        Socket socket = new Socket("192.168.10.2", 5209);
        System.out.println("�ͻ��������ɹ�");
        //��ȡ����̨������
        BufferedReader out = new BufferedReader(new InputStreamReader(System.in));
        //ͨ��socket���������write���͹��ܶ���
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        while (true) {
            //�ȴ�����̨��Ϊ�յ�������
            String str = out.readLine();
            if ("".equals(str)) {
                break;
            }
            //ͨ��socket�����ַ������͵�������
            pw.println(str);
            //����ˢ�����͹��ܶ���
            pw.flush();
        } // ����ѭ��
        pw.close(); // �ر�Socket�����
        socket.close(); // �ر�Socket
    }
 
}
