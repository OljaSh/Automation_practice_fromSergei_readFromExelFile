package forall.utils;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

import java.util.Arrays;


public final class PropertiesUtils {

	private static final CompositeConfiguration MIXED_CONFIG;

	public static final class Constants {
		public static final String DEV_URL = "dev.url.arg";
		public static final String QA_URL = "qa.url.arg";
		public static final String MAIN_DATA_SOURCE = "data.file.name.arg";

		private Constants() {
		}
	}

	static {
		try {
			MIXED_CONFIG = new CompositeConfiguration(Arrays.asList(new SystemConfiguration(),
					new PropertiesConfiguration("properties/config.properties")));
		} catch (Exception ex) {
			throw new IllegalArgumentException("Can't load properties");
		}
	}

	public static String getStringValue(final String key) {
		return MIXED_CONFIG.getString(key);
	}

	private PropertiesUtils() {
	}
}
