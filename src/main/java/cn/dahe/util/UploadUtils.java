package cn.dahe.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadUtils {
	public static void upload(MultipartFile file, String path){
		try {
			byte[] bytes = file.getBytes();
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
			bos.write(bytes);
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
