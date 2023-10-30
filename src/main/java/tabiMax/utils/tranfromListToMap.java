package tabiMax.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tranfromListToMap {
	public static Map<String, Integer> toMap(List<String> size, List<String> amount){
		System.out.println(size +" "+ amount);
		Map<String, Integer> sizeQuantityMap = new HashMap<>();
			int length = amount.size();
			for(int i =0; i <  amount.size(); i++ ) {
				if(amount.get(i).equals("")) {
					amount.remove(i);
				}
			}
			for(int i = 0; i < size.size(); i++) {
				sizeQuantityMap.put(size.get(i), Integer.parseInt(amount.get(i)));
			}
		
		return sizeQuantityMap;
	}
}
