import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector ResponsibleLB = new Vector();
		//String[] ResponsibleLBArray = {"���","Υ����˾�ƶ�","Υ�����ɷ���"};
		String[] ResponsibleLBArray = {"��΢/��ͷ��ʾ","�е�/������ʾ","����/�����˲�����չ���Լ̸","����/���쳤̸��","����"};
		for(int i=0;i<ResponsibleLBArray.length;i++)
		{
			Map temp = new HashMap();
			temp.put("value",i+"");
			temp.put("text",ResponsibleLBArray[i]);
			ResponsibleLB.add(temp);
		}
	}

}
