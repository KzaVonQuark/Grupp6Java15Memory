package application;

import java.io.File;

public class Test {

	String pathName = "/images/";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test = new Test();
		test.loadImages();
	}
	
	public void loadImages() {
	File imgdir = new File(pathName);
	System.out.println(imgdir.list());
	
	//for (File file : images) {
		//System.out.println(file);
	//}
	}		

}
