package edu.jit.utils;
/**
 * 对数据进行空校验：避免出现无效数据入库的情况
 * @author tarena
 *
 */
public class CheckNullUtils {
	public static boolean isNull(String str) {
		return (str == null || "".equals(str));
	}
}
