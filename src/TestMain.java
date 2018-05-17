import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector ResponsibleLB = new Vector();
		//String[] ResponsibleLBArray = {"差错","违反公司制度","违反法律法规"};
		String[] ResponsibleLBArray = {"轻微/口头提示","中等/书面提示","较重/监察稽核部或风险管理部约谈","严重/督察长谈话","其他"};
		for(int i=0;i<ResponsibleLBArray.length;i++)
		{
			Map temp = new HashMap();
			temp.put("value",i+"");
			temp.put("text",ResponsibleLBArray[i]);
			ResponsibleLB.add(temp);
		}
	}

}
