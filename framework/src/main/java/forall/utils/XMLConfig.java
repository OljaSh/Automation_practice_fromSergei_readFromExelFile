package forall.utils;

import java.util.Map;

/**
 * Author: Sergey Korol.
 */
public class XMLConfig {

	private final Map<String, String> parameters;

	public XMLConfig(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getParameter(String key) {
		return parameters.get(key);
	}
}
