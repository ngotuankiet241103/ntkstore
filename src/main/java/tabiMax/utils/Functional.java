package tabiMax.utils;

import java.util.function.Function;

import org.springframework.util.SerializationUtils;

public class Functional {
	
	public static Function<byte[], String> byteToString = valueByte -> new String(valueByte);
	public static Function<byte[], Object> byteToObject = valueByte -> SerializationUtils.deserialize(valueByte);
}
