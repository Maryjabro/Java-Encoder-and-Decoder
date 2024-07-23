
class SubstitutionCipher extends Cipher implements MessageEncoder, MessageDecoder { //subclass of cipher, implementing 2 interfaces to encode and decode
    private int shift;

    public SubstitutionCipher(int shift) {
        this.shift = shift;
    }

    @Override //override from abstract class Cipher
    public String cipherType() {
        return "SubstitutionCipher"; //returns the string “SubstitutionCipher”.
    }

    @Override
    public String encode(String plainText) {
        String encoded = ""; // Initialize an empty string to store the encoded message

        // Iterate over each character in the plainText
        for (int i = 0; i < plainText.length(); i++) {
            char letter = plainText.charAt(i); // Get the character at index i

            // Check if the character is a letter
            if (Character.isLetter(letter)) {
                char convert;
                // Determine the conversion character based on whether c is lowercase or uppercase
                if (Character.isLowerCase(letter)) {
                    convert = 'a'; // If lowercase
                } else {
                    convert = 'A'; // If uppercase
                }
                // Shift the character by adding the shift value. we have to make sure
                // we dont go over ASCII values for alphabet so we have to  wrap around  by %26 +convert
                char shifted = (char) ((letter - convert + shift) % 26 + convert);
                // Append the shifted character to the encoded message
                encoded += shifted;
            } else {
                // If the character is not a letter, keep it unchanged and append to the encoded message
                encoded += letter;
            }
        }

        return encoded; // Return the encoded message
    }

    @Override
    public String decode(String cipherText) {
        String decoded = ""; // Initialize an empty string to store the decoded message

        for (int i = 0; i < cipherText.length(); i++) {
            char letter = cipherText.charAt(i); // Get the character at index i

            // Check if the character is a letter
            if (Character.isLetter(letter)) {
                char convert;
                // Determine the conversion character based on whether c is lowercase or uppercase
                if (Character.isLowerCase(letter)) {
                    convert = 'a'; // If lowercase
                } else {
                    convert = 'A'; // If uppercase
                }
                // Reverse the shift by subtracting the shift value.
                char shifted = (char) ((letter - convert - shift + 26) % 26 + convert);
                // Append the shifted character to the decoded message
                decoded += shifted;
            } else {
                // If the character is not a letter
                decoded += letter;
            }
        }

        return decoded;
    }
}
