import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FileReadWrite textFile = new FileReadWrite();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите данные одной строкой, разделенные пробелом: ");
            String userData = scanner.nextLine();

            if (!userData.isEmpty()) {
                ValidateData validate = new ValidateData();
                if (validate.checkUserData(userData)) {
                    String[] userDataArray = userData.trim().split("\\s+");
                    String filename = userDataArray[0];
                    String preformatted = validate.preformatDataForWriting(userData);
                    textFile.writeToFile(filename + ".txt", preformatted);
                    break;
                }
            }
        }
    }
}
