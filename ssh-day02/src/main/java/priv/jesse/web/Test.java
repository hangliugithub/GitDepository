package priv.jesse.web;

public class Test {
	
	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager() {
			@Override
			public void checkExit(int status) {
				throw new ThreadDeath();
			}
		});
		
		try {
			System.out.println("hello");
			System.exit(0);
		} finally {
			System.out.println("In the finally block");
		}
	}

}
