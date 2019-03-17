package myFileListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import myFileListener.FileMd5;

/**
 * @author jyroo
 * myfilelistener
 */
@SuppressWarnings("unused")
public class myFileListener {
	HashMap<String, String> hashmap = new HashMap<>(); //���hash��ֵ��
	List<String> pathName = new ArrayList<String>();  //����ļ���
	
	@SuppressWarnings("static-access")
	public void iteratorPath(String dir) {
		
		File file = new File(dir);
		File[] files = file.listFiles();   //����ĳ��Ŀ¼�������ļ���Ŀ¼�ľ���·��  return file[]
		if(files != null) {
			for(File each_file : files) {
				if(each_file.isFile()) {      // ������ļ�
					pathName.add(each_file.getName());       //�洢�ļ���   
					
					String file_path = each_file.getAbsolutePath();    //��ȡ�ļ��ľ���·��
					
					try {
						FileMd5 mymd5 = new FileMd5();
						String md5_value = mymd5.fileMd5(file_path);    //�����ļ���Ӧ��hashֵ
						hashmap.put(each_file.getName(), md5_value);    //���ļ�����Ϊkey��hashֵ��Ϊvalue�洢��hashmap��
					} catch (Exception e) {
						System.out.println("���� "+e+" �Ĵ��󣡣�");
					}
					
				}else if(each_file.isDirectory()) {       //������ļ���
					iteratorPath(each_file.getAbsolutePath());   //�ݹ����
				}
			}
		}
	}
	
	public static void main(String[] args) {
		myFileListener file_walk = new myFileListener();
		file_walk.iteratorPath("E:\\tmp\\");
		for(String key:file_walk.hashmap.keySet()){
			System.out.println("Key: "+key+" Value: "+file_walk.hashmap.get(key));
        }
	}	
	
}