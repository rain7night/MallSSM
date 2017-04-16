import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import common.utils.FtpUtil;
import org.junit.Test;

public class FTPTest {
	
	@Test
	public void test1() throws Exception{

		try {
			FileInputStream in=new FileInputStream(new File("D:\\Books\\yh.jpg"));
			boolean flag = FtpUtil.uploadFile("192.168.186.128", 21, "kevin", "handsome", "/home/kevin/images","/2017-04-13", "yh.jpg", in);
			System.out.println(flag);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

}
