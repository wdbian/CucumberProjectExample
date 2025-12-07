package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConfigUtil {
	private static final String configFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
	private static final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
	private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	static {
		loadProperties();
	}

	private static void loadProperties() {
		readWriteLock.writeLock().lock();
		try(FileInputStream in = new FileInputStream(configFilePath)) {
			Properties property = new Properties();
			property.load(in);
			for (String key : property.stringPropertyNames()) {
				concurrentHashMap.put(key, property.getProperty(key));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();
		}	
	}
	
	public static String getProperty(String key) {
		readWriteLock.readLock().lock();
		String value = concurrentHashMap.get(key);
		readWriteLock.readLock().unlock();
		return value;
	}
	
	public static void setProperty(String key, String value) {
		readWriteLock.writeLock().lock();
		concurrentHashMap.put(key, value);
		persistProperties();
		readWriteLock.writeLock().unlock();
	}

	private static void persistProperties() {
		Properties property = new Properties();
		property.putAll(concurrentHashMap);
		try (FileOutputStream out = new FileOutputStream(configFilePath)) {
			property.store(out, null);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
}
