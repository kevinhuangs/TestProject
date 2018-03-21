import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DataSendClient {

	public static void main(String[] args) throws Exception{
		
		
		String urlPath = new String("http://localhost:8080/TestProject/DataReceiveServlet"); 
	    String param=URLEncoder.encode("Hello World!","UTF-8");
	    URL url=new URL(urlPath);
	    HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();//������������������
	    //���ò���
	    httpConn.setDoOutput(true);   //��Ҫ���
	    httpConn.setDoInput(true);   //��Ҫ����
	    httpConn.setUseCaches(false);  //��������
	    httpConn.setRequestMethod("POST");   //����POST��ʽ����
	    //������������
	    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    httpConn.setRequestProperty("Connection", "Keep-Alive");// ά�ֳ�����
	    httpConn.setRequestProperty("Charset", "UTF-8");
	    //����,Ҳ���Բ�������connect��ʹ�������httpConn.getOutputStream()���Զ�connect
	    httpConn.connect();
	    //��������������ָ���URL�������
	    DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
	    dos.writeBytes(param);
	    dos.flush();
	    dos.close();
	    //�����Ӧ״̬
	    int resultCode=httpConn.getResponseCode();
	    if(HttpURLConnection.HTTP_OK==resultCode){
	      StringBuffer sb=new StringBuffer();
	      String readLine=new String();
	      BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
	      while((readLine=responseReader.readLine())!=null){
	        sb.append(readLine).append("\n");
	      }
	      responseReader.close();
	      System.out.println("ClientReceive----------"+sb.toString());
	    } 

		

	}

}
