package ua.yandex.shad;

public class A extends B {

	static int i = 10;

	public A(){
		System.out.println("A");
	}

	public A(int i){
		this.k = 10;
		System.out.println("A" + super.k);
	}

	public static void main(String... args){
		new A(4);
	}

}