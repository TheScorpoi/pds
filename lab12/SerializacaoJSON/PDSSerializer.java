package SerializacaoJSON;

import java.lang.reflect.*;
import java.util.Arrays;

public class PDSSerializer {

	public static String fromObject(Object o) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<?> c1 = o.getClass();
				
		Field name = c1.getDeclaredField("name");
		name.setAccessible(true);
		String fieldName = (String)name.get(o);
		
		Field price = c1.getDeclaredField("size");
		price.setAccessible(true);
		Integer fieldPrice = (Integer)price.get(o);
		
		Field owner = c1.getDeclaredField("owner");
		owner.setAccessible(true);
		Owner fieldOwner = (Owner) owner.get(o);
		
		Field passageiros = c1.getDeclaredField("passageiros");
		passageiros.setAccessible(true);
		String[] fieldPassageiros = (String[])passageiros.get(o);
		
		System.out.println("Name: " + fieldName);
		System.out.println("Price: " + fieldPrice);
		System.out.println("Owner: " + fieldOwner);
		System.out.println("Passageiros: " + Arrays.toString(fieldPassageiros));
		
		return "";
	}
	
	
	
}