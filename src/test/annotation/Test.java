package test.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType; 
import java.lang.annotation.RetentionPolicy;

//��ע�����ڷ�������
@Target(ElementType.METHOD)
//VM����������Ҳ����ע�ͣ���˿���ͨ��������ƶ�ȡע�����Ϣ
@Retention(RetentionPolicy.RUNTIME)
//����ע�������javadoc��
@Documented
//��������̳и����е�ע��
@Inherited

public @interface Test { 
	public int idint=011;
    public int id();
    public String description() default "no description";
}

