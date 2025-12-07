package dataDictionary;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadDataMap {
    private final ThreadLocal<Map<String, Object>> threadDataMap; 
    
    public ThreadDataMap() {
    	threadDataMap = ThreadLocal.withInitial(ConcurrentHashMap::new);
    }
    
    public ThreadLocal<Map<String, Object>> getThreadDataMap() {
		return threadDataMap;
	}
    
    public <T extends Object> void setDataMap(String key, T value) {
        threadDataMap.get().put(key, value);
    }

    @SuppressWarnings("unchecked")
	public <T> T getDataMap(String key) {
        return (T) threadDataMap.get().get(key);
    }
    
    public void removeDataMap() {
		threadDataMap.remove();
	}
}