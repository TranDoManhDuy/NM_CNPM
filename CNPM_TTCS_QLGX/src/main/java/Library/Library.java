/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import Model.ShiftTypes;
import Model.TimeFrame;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author manhh
 */
public class Library {
    // Ten chi gom a - z, A - Z
    public static boolean isValidString(String str) {
        if (str == null || str.isEmpty()) {
            return false; // Chuỗi rỗng hoặc null thì không hợp lệ
        }
        for (int i = 0; i <= str.length() - 1; ++i) {
            if (!Character.isLetter(str.charAt(i)) && str.charAt(i) != ' ') {
                return false; // Nếu có ký tự không phải chữ cái, trả về false
            }
        }
        return true;
    }
    
    // email validate
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    
    // kiem tra cccd
    public static boolean isValidSSN(String str) {
        return str.matches("\\d{12}");
    }
    
    public static boolean isValidPhoneNumber(String phone_number) {
        return phone_number != null && phone_number.matches("0\\d{9}");
    }
    
    // in dinh dang tien te
    public static String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US); // Định dạng theo US (1,000,000)
        return formatter.format(amount);
    }
    
    // Kiểm tra ngày sinh có đúng định dạng và không lớn hơn ngày hiện tại
    public static boolean isValidDateOfBirth(LocalDate dateOfBirth) {
        return dateOfBirth != null && !dateOfBirth.isAfter(LocalDate.now());
    }

    // Chuyển đổi chuỗi "dd/MM/yyyy" thành LocalDate
    public static LocalDate parseDateOfBirth(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // Check TimeFrame
    public static boolean TimeFrameValidator(ArrayList<TimeFrame> lstTimeFrame){
        lstTimeFrame.sort(Comparator.comparing(t -> t.getTime_start()));
        
        for (int i = 1; i < lstTimeFrame.size(); i++) {
                TimeFrame prev = lstTimeFrame.get(i - 1);
                TimeFrame current = lstTimeFrame.get(i);
                
                if (current.getTime_start().isBefore(prev.getTime_end())) {
                    return false;
                }
            }    
        return true;
    } 
    
    // Check Time of ShiftType
    public static boolean TimeShiftTypeValidator(ArrayList<ShiftTypes> lstShiftType){
        lstShiftType.sort(Comparator.comparing(t -> t.getStart_time()));
        
        for (int i = 1; i < lstShiftType.size(); i++) {
                ShiftTypes prev = lstShiftType.get(i - 1);
                ShiftTypes current = lstShiftType.get(i);
                
                if (current.getStart_time().isBefore(prev.getEnd_time())) {
                    return false;
                }
            }    
        return true;
    } 
    public static boolean StringOnString(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        
        int index = 0; // Chỉ số theo dõi vị trí trong `a`

        for (char c : b.toCharArray()) {
            if (index < a.length() && c == a.charAt(index)) {
                index++; 
            }
            if (index == a.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        
    }
}