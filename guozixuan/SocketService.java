package guozixuan;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
 
public class SocketService {
    //���������
    public static void main(String[] args) throws IOException{
        //����������
        ServerSocket server=new ServerSocket(5209);
        System.out.println("�����������ɹ�");
        //�ȴ��ͻ������Ӻ󣬽��տͻ���socket
        Socket socket=server.accept();
        //��ȡ�ͻ���socket��������
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while(true){
            //�ȴ��ͻ���socket�Ĳ�Ϊ��������
            String str = in.readLine();
            if (str == null) {
                break;
            }
            System.out.println("�ͻ���˵��" + str);
        }
        in.close(); //�ر�Socket������
        socket.close(); //�ر�Socket
        server.close(); //�ر�ServerSocket
    }
}
