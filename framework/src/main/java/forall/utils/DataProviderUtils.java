package forall.utils;

import forall.annotations.DataSource;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static forall.utils.ExcelUtils.getData;

/**
 * Author: Serhii Korol.
 */
public final class DataProviderUtils {

	public static final String GENERIC_DP = "genericDP";

	@DataProvider(name = GENERIC_DP)
	public static Iterator<Object[]> provideExcelData(final Method method) {
		return Optional.ofNullable(method.getDeclaredAnnotation(DataSource.class))
				.map(source -> getData(source.fileName().toString(), source.workSheetName(), method))
				.filter(output -> output.stream().allMatch(row -> row.length == method.getParameterCount()))
				.map(List::iterator)
				.orElse(new ArrayList<Object[]>().iterator());
	}
}
