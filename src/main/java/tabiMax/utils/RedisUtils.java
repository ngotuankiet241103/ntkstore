package tabiMax.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.util.SerializationUtils;

public class RedisUtils {
	public static <T, O> Map<T, O> getObject(Map<byte[], byte[]> data, Function<byte[], T> keyMapper,
			Function<byte[], O> valueMapper) {
		Map<T, O> response = new HashMap<>();
		for (Map.Entry<byte[], byte[]> entry : data.entrySet()) {
			T key = keyMapper.apply(entry.getKey());
			O value = valueMapper.apply(entry.getValue());
			response.put(key, value);
			
		}
		return response;
	}

	public static <T, O> T getObject(O data) {
		T response = (T) SerializationUtils.deserialize((byte[]) data);

		return response;
	}

}
