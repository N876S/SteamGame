
public class Decrypter {
	public String decrypt(String data, int key) {

		// FLIP

		String flipped = "";

		for (int i = data.length() - 1; i >= 0; i--) {
			flipped += data.charAt(i);
		}

		// CONVERT TO DIGITS

		String newLetters = "";

		for (int i = 0; i < flipped.length(); i++) {
			switch (flipped.charAt(i)) {
			case 'a':
				newLetters += '0';
				break;
			case 'b':
				newLetters += '1';
				break;
			case 'c':
				newLetters += '2';
				break;
			case 'd':
				newLetters += '3';
				break;
			case 'e':
				newLetters += '4';
				break;
			case 'f':
				newLetters += '5';
				break;
			case 'g':
				newLetters += '6';
				break;
			case 'h':
				newLetters += '7';
				break;
			case 'i':
				newLetters += '8';
				break;
			case 'j':
				newLetters += '9';
				break;
			}
		}

		// CONVERT TO INT
		
		int undivided;

		try {
			undivided = Integer.parseInt(newLetters);
		} catch (Exception e) {
			System.out.println("Incorrect data, system failed to encrypt");
			return "";
		}
		
		//DIVIDE
		
		undivided /= key;

		return Integer.toString(undivided);
	}
}
