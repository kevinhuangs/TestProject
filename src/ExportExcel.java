import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import jxl.Workbook;
import jxl.*;
import jxl.write.*;
public class ExportExcel {

	public static void main(String[] args) throws IOException, WriteException {
		
		
		FileOutputStream os = new FileOutputStream("c:/Υ��֪ͨ��"+changeDay(new Date())+".xls");
		WritableWorkbook wwb=Workbook.createWorkbook(os);
		try
		{	

			// ������ʽ
			WritableCellFormat title = new WritableCellFormat(new WritableFont(WritableFont.createFont("����"), 16, WritableFont.BOLD, false));//��ͷ
			
			//��ͷ��ʽ	
			WritableCellFormat head = new WritableCellFormat(new WritableFont(WritableFont.createFont("����"), 11, WritableFont.BOLD, false));//��ͷ
			//���ñ߿�
			head.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);
			//����
			head.setBackground(Colour.GRAY_25);
			head.setWrap(true);
			head.setAlignment(Alignment.CENTRE);
			head.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

			//������ʽ	
			WritableCellFormat mainbody = new WritableCellFormat(new WritableFont(WritableFont.createFont("����"), 11));//����
			mainbody.setWrap(true);
			mainbody.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);	
			mainbody.setAlignment(Alignment.CENTRE);
			mainbody.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);


			WritableSheet ws =null;

				//*************************************����sheet*********************
				ws = wwb.createSheet("Υ��֪ͨ��"+changeDay(new Date()), 0);

				//��һ�� ����
				Label labelMain = new Label(1, 0,"");
				ws.addCell(labelMain);
				
				//���� �ڶ���
				Label tLabel = new Label(3,1,"Υ��֪ͨ��",title);
				ws.addCell(tLabel);
				
				//��3�� ���� �ϲ���
				tLabel = new Label(1,2,"");
				ws.addCell(tLabel);
				ws.mergeCells(1,2,8,2);

				//��4��
				tLabel = new Label(1,3,"����",head);
				ws.setColumnView(1,15);
				ws.addCell(tLabel);

				tLabel = new Label(2,3,"������",head);
				ws.setColumnView(2,15);
				ws.addCell(tLabel);
				tLabel = new Label(3,3,"�����˲���",head);
				ws.setColumnView(3,15);
				ws.addCell(tLabel);
				tLabel = new Label(4,3,"�������ʩ",head);
				ws.setColumnView(4,15);
				ws.addCell(tLabel);
				tLabel = new Label(5,3,"������",head);
				ws.setColumnView(5,15);
				ws.addCell(tLabel);
				tLabel = new Label(6,3,"����ʱ��",head);
				ws.setColumnView(6,15);
				ws.addCell(tLabel);
					
		//���� sys_title, ������ cfyg, �����˲��� szbm���������ʩ cflx�������� ChaoSongRule������ʱ�� sys_createtime
		
		
					//������
					String strSQL = "select sys_title,cfyg,szbm,cflx,ChaoSongRule,sys_createtime from DOC_GSJLCL t where sys_recycleflag = 0 and reservedchar4= '8238077' order by sys_createtime desc";
			//		Vector vv = getResultSet(strSQL);
			//		for(int i=0;i<vv.size();i++){
					int i = 0;		
				//	HashMap hmm = (HashMap)(vv.get(i));	
					HashMap hmm = new HashMap();
					String sys_title = (String)hmm.get("SYS_TITLE");
					String cfyg = (String)hmm.get("CFYG");
					String szbm = (String)hmm.get("SZBM");
					String cflx = (String)hmm.get("CFLX");
					String ChaoSongRule = (String)hmm.get("CHAOSONGRULE");
					String sys_createtime = (String)hmm.get("SYS_CREATETIME");
						
						tLabel = new Label(1,4+i,isObjNullAsSpace(sys_title),mainbody);
						ws.addCell(tLabel);
						tLabel = new Label(2,4+i,isObjNullAsSpace(cfyg),mainbody);
						ws.addCell(tLabel);
						tLabel = new Label(3,4+i,isObjNullAsSpace(szbm),mainbody);
						ws.addCell(tLabel);
						tLabel = new Label(4,4+i,isObjNullAsSpace(cflx),mainbody);
						ws.addCell(tLabel);
						tLabel = new Label(5,4+i,isObjNullAsSpace(ChaoSongRule),mainbody);
						ws.addCell(tLabel);
						tLabel = new Label(6,4+i,isObjNullAsSpace(sys_createtime),mainbody);
						ws.addCell(tLabel);
			//		}
			   
		//		response.setHeader("content-disposition", "attachment;filename=WGTZ"+nowDate+".xls");

				

		}catch(Exception e)
		{
		}finally{
				wwb.write();
				wwb.close();
				os.close();
		}

	}
	public static String isNullAsSpace(String s1)
	{
		return isNullAs(s1," ");
	}

	private static String isNullAs(String s1, String string) {
		// TODO Auto-generated method stub
		return null;
	}
	public static String isObjNullAsSpace(Object s1)
	{
		if(s1==null)
		{
			return "";
		}else
		{
			return (String)s1;
		}
		
	}
	/**
	 * ���㵱ǰ����ͳ�Ƶ�ʱ�������
	 * @param currDate
	 * @return ����true ˵����ǰʱ���������ϰ�ʱ�䣬����false˵����ǰ��������Ϣ��
	 */
	private boolean getCurrDateType(String currDate)//ȡ��ͳ���յ����ʣ�true�����ϰ࣬false��Ϣ��
	{
		boolean reValue = true;
		java.util.Date rightnow = null;
		if(currDate==null||currDate=="null"||currDate.length()<1)
		{
			rightnow = new java.util.Date();
			currDate = changeDay(rightnow);
		}else
		{
			rightnow=strToDate(currDate);
		}
		//�Ƿ�����δ
		int dayIndex = rightnow.getDay();
		
		if(dayIndex==0||dayIndex==6)//�Ƿ�����δ
		{
			reValue = false;
		}else
		{
			reValue = true;
		}
		return reValue;
		
	}
	
	/**
	 * ��yyyy-MM-dd��ʽ���ַ�ת��������
	 * @param inputDate
	 * @return
	 */
	private java.util.Date strToDate(String inputDate)
	{
		String reValue = null;
		java.util.Date dt1 = new java.util.Date();
	   SimpleDateFormat sTmp = new SimpleDateFormat("yyyy-MM-dd");
	   ParsePosition pos = new ParsePosition(0);
	   try{
		 dt1=sTmp.parse(inputDate,pos);
	   } catch(Exception e){
		 return null;
	   }
	   return dt1;
	}
	/**
	 * ������ת��Ϊyyyy-MM-dd��ʽ���ַ���
	 * @param date
	 * @return
	 */
	private static String changeDay(java.util.Date date)
	{
	   String revalue = null;
	   try{
		 SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
	     java.util.Calendar now = java.util.Calendar.getInstance();
	     now.setTime(date);
	     revalue = f2.format(now.getTime());
	   }catch(Exception e){
	     }
	     return revalue;
	 }
}
