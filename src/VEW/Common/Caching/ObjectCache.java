package VEW.Common.Caching;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class ObjectCache {
  
  private Constructor constructor;
  private Object[] constructorArgs;
  
  private int allocations = 0;
  private int cacheHits = 0;
  
  private ArrayList cache;
  
  public ObjectCache(Class theClass, Class[] argTypes, Object[] argDefaults) throws SecurityException, NoSuchMethodException {
    this.constructorArgs = argDefaults;
    
    this.constructor = theClass.getConstructor(argTypes);
    constructor.setAccessible(true);
    
    this.cache = new ArrayList();
  }
  
  public ObjectCache(Class theClass) throws SecurityException, NoSuchMethodException {
    this(theClass, null, null);
  }
  
  public Object getInstance() {
    if (cache.isEmpty())
      try {
        allocations++;
        return constructor.newInstance(constructorArgs);
      } catch (IllegalArgumentException e) {
        throw new RuntimeException("Illegal argument in constructor", e);
      } catch (InstantiationException e) {
        throw new RuntimeException("Trying to instantiate abstract class", e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Constructor inaccessible", e);
      } catch (InvocationTargetException e) {
        throw new RuntimeException("Exception in constructor", e.getCause());
      }
    else {
      cacheHits++;
      return cache.remove(cache.size() - 1);
    }
  }
  
  public void finishedWith(Object obj) {
    cache.add(obj);
  }

  public int getAllocations() {
    return allocations;
  }

  public int getCacheHits() {
    return cacheHits;
  }
  
  public void resetCounts() {
    allocations = 0;
    cacheHits = 0;
  }

}
