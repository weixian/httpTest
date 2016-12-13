package Istuary.com.Util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {
	private final Class<?> clazz;
	private Logger logger;

	/**
	 * 
	 * @param clazz
	 */
	public Log(Class<?> clazz) {

		this.clazz = clazz;
		this.logger = LogManager.getLogger(this.clazz);
		DOMConfigurator.configure("config/log4j2.xml");
		
	}

	/**
	 * @author XianWei
	 * @param message
	 * 
	 */
	public void info(String message) {
		logger.info(clazz.getCanonicalName() + ": " + message);
	}

	/**
	 * @author XianWei
	 * @param message
	 */
	public void debug(String message) {
		logger.debug(clazz.getCanonicalName() + ": " + message);
	}

	/**
	 * @author XianWei
	 * @param message
	 */
	public void error(String message) {
		logger.error(clazz.getCanonicalName() + ": " + message);
	}

	/**
	 * @author XianWei
	 * @param message
	 */
	public void trace(String message) {
		logger.trace(clazz.getCanonicalName() + ": " + message);
	}

	/**
	 * @author XianWei
	 * @param message
	 */
	public void warn(String message) {
		logger.warn(clazz.getCanonicalName() + ": " + message);
	}

	/**
	 * @author XianWei
	 * @param message
	 */
	public void fatal(String message) {
		logger.fatal(clazz.getCanonicalName() + ": " + message);
	}
}
