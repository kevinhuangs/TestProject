package test.annotation;

import java.lang.reflect.Method;
/**
*   ʹ��ע��ͽ���ע��
*  
*   @creator            xiaoyu.wang   
*   @create-time     2011-8-12   ����03:49:17   
*   @revision          $Id
*/
public class Test_1{
    /**
     * ��ע�͵���������
     */
    @Test(id = 1, description = "hello method1")
    public void method1() {
    }

    @Test(id = 2)
    public void method2() { 
    }
 
    @Test(id = 3, description = "last method3")
 
    /**
     * ����ע�ͣ���Test_1�����б�ע�ⷽ������Ϣ��ӡ����
     * @param args
     */
    public static void main(String[] args) {
        Method[] methods = Test_1.class.getDeclaredMethods();
        for (Method method : methods) {
            //�жϷ������Ƿ���ָ��ע�����͵�ע��
            boolean hasAnnotation = method.isAnnotationPresent(Test.class);
            if (hasAnnotation) {
                //����ע�����ͷ��ط�����ָ������ע��
                Test annotation = method.getAnnotation(Test.class);
                System.out.println("Test(method=" + method.getName() + ",id=" + annotation.id()
                                   + ",description=" + annotation.description() + ")"+annotation.idint);
            }
        }
    }
}
