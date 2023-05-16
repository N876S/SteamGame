
public class Encrypter {
	public String encrypt(String data, int key) {

		// MAKE SURE THE KEY IS VALID

		if (key > 99999 || key < 0) {
			System.out.println("Invalid Key. Must input integer from 0-99999.");
			return "";
		}

		int added;

		// PARSE TO INT TO MULTIPLY

		try {
			added = Integer.parseInt(data) * key;
		} catch (Exception e) {
			System.out.println("Incorrect data, system failed to encrypt");
			return "";
		}

		// CONVERT BACK TO STRING

		String addedString = Integer.toString(added);

		// CONVERT TO LETTERS

		String newLetters = "";

		for (int i = 0; i < addedString.length(); i++) {
			switch (addedString.charAt(i)) {
			case '0':
				newLetters += 'a';
				break;
			case '1':
				newLetters += 'b';
				break;
			case '2':
				newLetters += 'c';
				break;
			case '3':
				newLetters += 'd';
				break;
			case '4':
				newLetters += 'e';
				break;
			case '5':
				newLetters += 'f';
				break;
			case '6':
				newLetters += 'g';
				break;
			case '7':
				newLetters += 'h';
				break;
			case '8':
				newLetters += 'i';
				break;
			case '9':
				newLetters += 'j';
				break;
			}
		}
		
		//FLIP STRING
		
		String flipped = "";
		
		for(int i = newLetters.length() - 1; i >= 0; i--) {
			flipped += newLetters.charAt(i);
		}

		return flipped;
	}
}
