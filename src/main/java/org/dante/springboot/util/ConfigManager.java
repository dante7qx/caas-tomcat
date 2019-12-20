package org.dante.springboot.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Properties;

/**
 * 属性文件管理器
 * 
 **/
public class ConfigManager {

	private String filePath = null; // 属性文件的路径

	private File file = null; // 属性文件的文件对象变量

	private long lastModifiedTime = 0; // 属性文件的最后修改日期

	private Properties pros = null; // 属性对像变量

	private static ConfigManager cm = null; // 单例类

	/**
	 * 本工具类为单例
	 */
	private ConfigManager(String path) {
		filePath = this.getFilePath(path);
		file = new File(filePath);
		lastModifiedTime = file.lastModified();

		if (lastModifiedTime == 0) { // 该属性文件不存在
			System.err.println("该属性文件不存在！");
		}

		pros = new Properties();
		try {
			pros.load(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取属性文件管理工具类实例
	 */
	public static ConfigManager getInstance(String path) {
		if (null == cm) {
			cm = new ConfigManager(path);
		} else { // 通过判断前后属性文件的名称来确定是否实例化
			try {
				Field field = cm.getClass().getDeclaredField("filePath");
				String val = (String) field.get(cm);
				int index = path.lastIndexOf("/");
				int ind = val.lastIndexOf("/");
				int postfix = val.lastIndexOf(".");
				if (!(path.substring(index + 1, path.length())).equals(val.substring(ind + 1, postfix))) {
					cm = new ConfigManager(path);
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return cm;
	}

	/**
	 * 获取属性文件路径
	 */
	private String getFilePath(String path) {
		String filePath = null;
		String inPath = path + ".properties";
		try {
			URL url = getClass().getClassLoader().getResource(inPath);
			if (null != url) {
				filePath = url.getFile();
			} else {
				throw new Exception("属性文件" + inPath + "不存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filePath;
	}

	/**
	 * 读取一个特定的属型项
	 * 
	 * @param key 属性项的键
	 * @return 属性项的值
	 */
	public String getPropertiesConfig(String key) {
		long newTime = file.lastModified();
		// 检查属性文件是否被修改过
		// 若是，则重新读取此文件
		if (newTime == 0) {
			// 属性文件不存在
			try {
				if (lastModifiedTime == 0) {
					throw new Exception("属性文件不存在！");
				} else {
					throw new Exception("属性文件已经被删除了！");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (newTime > lastModifiedTime) { // 属性文件被修改过
			pros.clear();
			try {
				pros.load(new FileInputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		lastModifiedTime = newTime;
		String value = pros.getProperty(key);
		return value;
	}

}