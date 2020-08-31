package br.com.bv.library.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LibraryUtil {

	public static boolean isNull(Object o) {
		if (o == null) return true;
		return false;
	}

	public static boolean isNullOrEmpty(Object o) {
		if (!isNull(o)) {
			if (o instanceof String) return ((String) o).isEmpty();
		}
		return isNull(o);
	}

	public static byte[] readBytesFromFile(String filePath) throws IOException {
		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;

		try {
			File file = new File(filePath);
			bytesArray = new byte[(int) file.length()];
			// read file into bytes[]
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesArray);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return bytesArray;
	}

}
