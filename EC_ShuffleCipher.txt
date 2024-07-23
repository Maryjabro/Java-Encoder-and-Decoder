
class ShuffleCipher extends Cipher implements MessageEncoder, MessageDecoder {
    //private member n
    private int n;

    //constructor
    public ShuffleCipher(int n) {
        this.n = n;
    }

    @Override
    public String cipherType() {
        return "ShuffleCipher"; // method returns the string “ShuffleCipher”
    }

    //private method that performs one shuffle.
    private String shuffle(String input) {
        int len = input.length();
        String shuffled = "";
        //split the message in half
        String first = input.substring(0, len / 2);
        String second = input.substring(len / 2);

        //then take characters from each half alternately.
        for (int i = 0; i < first.length(); i++) {
            shuffled += first.charAt(i);
            if (i < second.length()) { //makes sure we are still in range
                shuffled += second.charAt(i);
            }
        }
        return shuffled;
    }

    @Override
    public String encode(String plainText) { //the message is shuffled n times
        String encoded = "";

        //shuffle n times by shuffle method
        for (int i = 0; i < n; i++) {
            encoded = shuffle(encoded);
        }

        return encoded;
    }

    @Override
    public String decode(String cipherText) {
        // we need to shuffle the cipherText n times in the opposite direction

        String decoded = cipherText;
        // Iterate n times to reverse the shuffle
        for (int i = 0; i < n; i++) {
            decoded = unshuffle(decoded);
        }

        return decoded;
    }

    // Private method to perform one unshuffle operation
    private String unshuffle(String input) {
        int len = input.length();
        String unshuffled = "";

        // Split the input string into two halves
        String first = input.substring(0, (len + 1) / 2);
        String second = input.substring((len + 1) / 2);

        // Take characters from each half alternately
        for (int i = 0; i < first.length(); i++) {
            unshuffled += first.charAt(i);
            if (i < second.length()) {
                unshuffled += second.charAt(i);
            }
        }

        return unshuffled;
    }
}