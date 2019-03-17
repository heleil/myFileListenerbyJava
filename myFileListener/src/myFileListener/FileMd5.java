package myFileListener;

import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
import java.io.IOException;

public class FileMd5 {
	public static String fileMd5(String inputFile) throws IOException{
		
		int bufferSize = 1024*1024;  //��������С
		FileInputStream fileInputStream = null;
		DigestInputStream digestInputStream = null;
		
		try {
			//��ȡMD5��ʵ��
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			
			fileInputStream = new FileInputStream(inputFile);
			
			digestInputStream = new DigestInputStream(fileInputStream, messageDigest);   //Creates a digest input stream, using the specified input stream and message digest.
			
			byte[] buffer = new byte[bufferSize];   //���û�������������ȡ�ļ��������ļ����󣬵��µ�IO����
			while(digestInputStream.read(buffer)>0);  //read: updates the message digest    return int
			// ��ȡ���յ�MessageDigest
			messageDigest = digestInputStream.getMessageDigest();
			// �õ���� return�ֽ�����byte[] ����16��Ԫ��
			byte[] resultByteArray = messageDigest.digest();
			
			return byteArrayToHex(resultByteArray);	   //ת��byte Ϊ string ����
			
		}catch(NoSuchAlgorithmException e) {
			return null;
		}finally {
			try {
				digestInputStream.close();
			}catch (Exception e) {
				
			}
		}
	}
	
	public static String byteArrayToHex(byte[] byteArray){
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        //һ���ֽ��ǰ�λ������ Ҳ����2λʮ�������ַ�
        char[] resultCharArray = new char[byteArray.length*2];

        int index = 0;
        for(byte b : byteArray){
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        return new String(resultCharArray);
    }

}