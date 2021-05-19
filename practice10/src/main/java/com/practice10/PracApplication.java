package com.practice10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PracApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		Lighter lighter = context.getBean(args[0], Lighter.class);
		System.out.println(lighter.doLight());
		Lighter lighter1 = context.getBean(Lamp.class);
		Lighter lighter2 = context.getBean(Flashlight.class);
		Lighter lighter3 = context.getBean(Firefly.class);
		System.out.println(lighter1.doLight());
		System.out.println(lighter2.doLight());
		System.out.println(lighter3.doLight());
		((AnnotationConfigApplicationContext) context).close();
	}
}
