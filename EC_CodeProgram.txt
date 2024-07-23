
class CodeProgram {

    // Method to read file
    private static String readFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            StringBuilder content = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                content.append(fileScanner.nextLine());
            }
            return content.toString();
        } catch (FileNotFoundException e) {
            return e.getMessage();
        }
    }

    // Method to write to file
    private static void writeFile(String fileName, String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(message);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    //driver
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message;

        boolean process = true;

        while (process) {
            // Display menu options
            System.out.println("Welcome to the Secure Cipher program");
            System.out.println("1. Substitution Cipher");
            System.out.println("2. Shuffle Cipher");

            // Read user option
            int option = scanner.nextInt();

            // Declare cipher variable
            Cipher cipher;

            // Switch case for different cipher options
            switch (option) {
                case 1:
                    System.out.println("What is the key (shift amount) for your code?");
                    int shiftAmount = scanner.nextInt();
                    cipher = new SubstitutionCipher(shiftAmount);
                    System.out.println("Substitution Cipher - shift amount: " + shiftAmount);
                    break;
                case 2:
                    System.out.println("What is the key (shuffle amount) for your code?");
                    int shuffleAmount = scanner.nextInt();
                    cipher = new ShuffleCipher(shuffleAmount);
                    System.out.println("Shuffle Cipher - shuffle amount: " + shuffleAmount);
                    break;
                default:
                    System.out.println("Invalid input");
                    continue;
            }

            // Read input and output file names
            System.out.println("Enter input file name:");
            String inputFile = scanner.next();
            System.out.println("Enter output file name:");
            String outputFile = scanner.next();

            // Read operation (Encode or Decode)
            System.out.println("Encode (E) or Decode (D)");
            char operation = scanner.next().charAt(0);

            // Switch case for Encode or Decode operation
            switch (operation) {
                case 'E':
                    message = ((MessageEncoder) cipher).encode(readFile(inputFile));
                    break;
                case 'D':
                    message = ((MessageDecoder) cipher).decode(readFile(inputFile));
                    break;
                default:
                    System.out.println("Invalid input.");
                    continue;
            }

            // Write message to output file
            writeFile(outputFile, message);

            // Display success message based on operation
            if (operation == 'E') {
                System.out.println("Encoded text is saved in " + outputFile);
            } else {
                System.out.println("Decoded text is saved in " + outputFile);
            }

            // Ask user if they want to process another message
            System.out.println("Do you want to process another message (Y/N)");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("Y")) {
                process = false;
            }
        }

        // Close scanner
        scanner.close();
    }

}