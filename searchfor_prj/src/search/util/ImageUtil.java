package search.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageUtil {


	public byte[] imageToByte(File file) {
		byte[] buffer = new byte[1024];
		byte[] imgdata = null;
		int len = -1;
		try {
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			imgdata = baos.toByteArray();
			// bytes.add(imgdata);
			baos.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgdata;
	}

	
	public List<byte[]> imagesToByte(List<File> files) {
		int i = 0;
		List<byte[]> bytes = new ArrayList<>();
		for (i = 0; i < files.size(); i++) {
			byte[] buffer = new byte[1024];
			int len = -1;
			try {
				InputStream is = new FileInputStream(files.get(i));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((len = is.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				byte[] imgdata = baos.toByteArray();
				bytes.add(imgdata);
				baos.close();
				is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}

	
	public void byteToImage(byte[]ibyte,String path) {
		File file = new File(path);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write(ibyte, 0, ibyte.length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
