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
	HashMap<String, String> hashmap = new HashMap<>(); //存放hash键值对
	List<String> pathName = new ArrayList<String>();  //存放文件名
	
	@SuppressWarnings("static-access")
	public void iteratorPath(String dir) {
		
		File file = new File(dir);
		File[] files = file.listFiles();   //返回某个目录下所有文件和目录的绝对路径  return file[]
		if(files != null) {
			for(File each_file : files) {
				if(each_file.isFile()) {      // 如果是文件
					pathName.add(each_file.getName());       //存储文件名   
					
					String file_path = each_file.getAbsolutePath();    //获取文件的绝对路径
					
					try {
						FileMd5 mymd5 = new FileMd5();
						String md5_value = mymd5.fileMd5(file_path);    //生成文件对应的hash值
						hashmap.put(each_file.getName(), md5_value);    //以文件名作为key，hash值作为value存储到hashmap中
					} catch (Exception e) {
						System.out.println("发生 "+e+" 的错误！！");
					}
					
				}else if(each_file.isDirectory()) {       //如果是文件夹
					iteratorPath(each_file.getAbsolutePath());   //递归遍历
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
