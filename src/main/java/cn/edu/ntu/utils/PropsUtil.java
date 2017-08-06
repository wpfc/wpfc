package cn.edu.ntu.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropsUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    private static Properties props = null;

    public PropsUtil(String propsPath) {
        this(propsPath, "UTF-8");
    }

    public PropsUtil(String propsPath, String encoding) {
        InputStream is = null;
        try {
            if (StringUtils.isBlank(propsPath)) {
                throw new IllegalArgumentException();
            }
            String suffix = ".properties";
            if (propsPath.lastIndexOf(suffix) == -1) {
                propsPath += suffix;
            }
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propsPath);
            if (is != null) {
                props = new Properties();
                props.load(new InputStreamReader(is, encoding));
            }
        } catch (Exception e) {
            logger.error("加载属性文件出错！", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("释放资源出错！", e);
            }
        }
    }
    
    

    /**
     * 加载属性文件，并转为 Map
     */
    public Map<String, String> loadPropsToMap(String propsPath) {
        Map<String, String> map = new HashMap<String, String>();
        for (String key : props.stringPropertyNames()) {
            map.put(key, props.getProperty(key));
        }
        return map;
    }

    /**
     * 获取字符型属性
     */
    public String getString(String key) {
        return props.getProperty(key);
    }

    /**
     * 获取字符型属性，有默认值
     */
    public static String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    /**
     * 获取字符型属性（有默认值）
     */
//    public static String getString(Properties props, String key, String defalutValue) {
//        String value = defalutValue;
//        if (props.containsKey(key)) {
//            value = props.getProperty(key);
//        }
//        return value;
//    }

    /**
     * 获取数值型属性
     */
    public Integer getInt(String key) {
        return getInt(key, null);
    }
    
    public Integer getInt(String key, Integer defaultValue) {
        String value = props.getProperty(key);
        if (value != null)
            return Integer.parseInt(value.trim());
        return defaultValue;
    }
    
    public static int getInt(Properties props, String key, int defaultValue) {
        String value = props.getProperty(key);
        if (value != null)
            return Integer.valueOf(value.trim());
        return defaultValue;
    }

    /**
     * 获取Long型
     */
    public Long getLong(String key) {
        return getLong(key, null);
    }
    public Long getLong(String key, Long defaultValue) {
        String value = props.getProperty(key);
        if (value != null)
            return Long.parseLong(value.trim());
        return defaultValue;
    }

    /**
     * 获取布尔型属性
     */
    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }
    public Boolean getBoolean(String key, Boolean defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            value = value.toLowerCase().trim();
            if ("true".equals(value))
                return true;
            else if ("false".equals(value))
                return false;
            throw new RuntimeException("The value can not parse to Boolean : " + value);
        }
        return defaultValue;
    }

    public boolean containsKey(String key) {
        return props.containsKey(key);
    }

    public Properties getProperties() {
        return props;
    }
    
    /**
     * 加载配置文件
     *
     * @param fileName 配置文件名
     * @return
     */
    public static Properties loadProps(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + " file is not found");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return props;
    }

    /**
     * 获取字符型属性(默认值为空字符串)
     *
     * @param props
     * @param key
     * @return
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    /**
     * 获取字符型属性(可指定默认值)
     *
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

}