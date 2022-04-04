package Bean;

public class Form {
	public static boolean isCorrectForm(String na, String pw) {
		if (na.length() > 5 && pw.length() > 2) {
			return true;
		}
		return false;
	}

	public static boolean isCorrectFormNa(String name) {
		if (name.length() > 5) {
			return true;
		}
		return false;
	}

}
