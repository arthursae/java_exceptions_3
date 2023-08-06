import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.regex.Pattern;

public class ValidateData {
    private boolean isValid;
    private static final int FIELDS = 6;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public ValidateData() {
        this.isValid = false;
    }

    public String preformatDataForWriting(String userData) {
        String[] userDataArray = userData.trim().split("\\s+");
        StringBuilder outputData = new StringBuilder();

        if (isValid) {
            for (String item : userDataArray) {
                outputData.append("<").append(item).append(">");
            }
        }
        return outputData.toString();
    }

    public boolean checkUserData(String userData) {

        String[] userDataArray = userData.trim().split("\\s+");

        if (userDataArray.length == FIELDS) {
            String surname = userDataArray[0];
            String firstname = userDataArray[1];
            String patronymic = userDataArray[2];
            String dob = userDataArray[3];
            String phone = userDataArray[4];
            String sex = userDataArray[5];
            String prefix = ANSI_RED + "Неверный формат данных – ";

            if (isNameInvalid(surname) || isNameInvalid(firstname) || isNameInvalid(patronymic)) {
                System.out.println(prefix + "ФИО:\nВведите фамилию, имя и отчество через пробел" + ANSI_RESET);
            } else if (!isDateValid(dob)) {
                System.out.println(prefix + "дата рождения:\nВведите ДД.ММ.ГГГГ" + ANSI_RESET);
            } else if (!Pattern.compile("[0-9]{4,15}").matcher(phone).matches()) {
                System.out.println(prefix + "номер телефона:\nВведите только цифры (мин 4, макс 15)" + ANSI_RESET);
            } else if (!Pattern.compile("[fm]?").matcher(sex).matches()) {
                System.out.println(prefix + "пол:\nВведите m или f\nm = мужской, f = женский" + ANSI_RESET);
            } else {
                isValid = true;
            }
        } else {
            System.out.println(ANSI_RED + "Неверное количество данных!\nВведите строку через пробел: ФИО дата_рождения номер_телефона пол" + ANSI_RESET);
        }
        return isValid;
    }

    private boolean isNameInvalid(String name) {
        String regex = "[абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯa-zA-Z]+";
        return !Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS).matcher(name).matches();
    }

    private boolean isDateValid(String dob) {
        if (Pattern.compile("\\d{2}[.]\\d{2}[.]\\d{4}").matcher(dob).matches()) {
            String[] dobArray = dob.split("\\.");
            int dd = Integer.parseInt(dobArray[0]);
            int mm = Integer.parseInt(dobArray[1]);
            int yyyy = Integer.parseInt(dobArray[2]);

            if (yyyy < 1900 && yyyy > Year.now().getValue()) {
                return false;
            }

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                format.setLenient(false);
                format.parse(dd + "/" + mm + "/" + yyyy);
            } catch (ParseException | IllegalArgumentException e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
