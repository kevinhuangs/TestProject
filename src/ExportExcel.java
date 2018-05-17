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
		
		
		FileOutputStream os = new FileOutputStream("c:/违规通知单"+changeDay(new Date())+".xls");
		WritableWorkbook wwb=Workbook.createWorkbook(os);
		try
		{	

			// 标题样式
			WritableCellFormat title = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 16, WritableFont.BOLD, false));//表头
			
			//表头样式	
			WritableCellFormat head = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD, false));//表头
			//设置边框
			head.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);
			//背景
			head.setBackground(Colour.GRAY_25);
			head.setWrap(true);
			head.setAlignment(Alignment.CENTRE);
			head.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

			//正文样式	
			WritableCellFormat mainbody = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 11));//正文
			mainbody.setWrap(true);
			mainbody.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);	
			mainbody.setAlignment(Alignment.CENTRE);
			mainbody.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);


			WritableSheet ws =null;

				//*************************************创建sheet*********************
				ws = wwb.createSheet("违规通知单"+changeDay(new Date()), 0);

				//第一行 空行
				Label labelMain = new Label(1, 0,"");
				ws.addCell(labelMain);
				
				//标题 第二行
				Label tLabel = new Label(3,1,"违规通知单",title);
				ws.addCell(tLabel);
				
				//第3行 空行 合并行
				tLabel = new Label(1,2,"");
				ws.addCell(tLabel);
				ws.mergeCells(1,2,8,2);

				//第4行
				tLabel = new Label(1,3,"标题",head);
				ws.setColumnView(1,15);
				ws.addCell(tLabel);

				tLabel = new Label(2,3,"责任人",head);
				ws.setColumnView(2,15);
				ws.addCell(tLabel);
				tLabel = new Label(3,3,"责任人部门",head);
				ws.setColumnView(3,15);
				ws.addCell(tLabel);
				tLabel = new Label(4,3,"监察管理措施",head);
				ws.setColumnView(4,15);
				ws.addCell(tLabel);
				tLabel = new Label(5,3,"监察类别",head);
				ws.setColumnView(5,15);
				ws.addCell(tLabel);
				tLabel = new Label(6,3,"创建时间",head);
				ws.setColumnView(6,15);
				ws.addCell(tLabel);
					
		//标题 sys_title, 责任人 cfyg, 责任人部门 szbm，监察管理措施 cflx，监察类别 ChaoSongRule，创建时间 sys_createtime
		
		
					//第六行
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
	 * 计算当前用于统计的时间的性质
	 * @param currDate
	 * @return 返回true 说明当前时间是正常上班时间，返回false说明当前日期是休息日
	 */
	private boolean getCurrDateType(String currDate)//取得统计日的性质，true正常上班，false休息日
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
		//是否是周未
		int dayIndex = rightnow.getDay();
		
		if(dayIndex==0||dayIndex==6)//是否是周未
		{
			reValue = false;
		}else
		{
			reValue = true;
		}
		return reValue;
		
	}
	
	/**
	 * 把yyyy-MM-dd格式的字符转换成日期
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
	 * 把日期转换为yyyy-MM-dd格式的字符串
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
