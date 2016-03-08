package forall.utils;


public final class StringUtils {

	public static String cutName(String username) {
		return username.split(" ")[5];
	}

	private StringUtils() {
	}
}
