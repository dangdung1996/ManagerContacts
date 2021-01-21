package controller;

import Exception.InvalidEmailException;
import model.Contacts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerContacts {

    public List<Contacts> list = new ArrayList<>(readFromToFile());

    private static final String FILE_CONTACTS = "CTT.CSV";

    /**
     * Phương Thức Nhập Thông Tin
     * @param input
     */
    public void creatContacts(Scanner input) {
        System.out.print("Họ và tên: ");
        String fullName = input.nextLine();
        System.out.print("Giới tính: ");
        String gender = input.nextLine();
        System.out.print("Số điện thoại: ");
        String phone = input.nextLine();
        System.out.print("Group: ");
        String group = input.nextLine();
        System.out.print("Ngày Sinh: ");
        String dateOfBirth = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Quên quán: ");
        String address = input.nextLine();

        Contacts contacts = new Contacts(phone, group, fullName, gender, address, dateOfBirth, email);
        list.add(contacts);
    }

    public void showContacts(List<Contacts> lists) {
        System.out.printf("%-13s%-13s%-13s%-13s%-13s\n",
                "PhoneNumber " , " NameGroup " , " FullName" , "Gender " , " Address");
        for (Contacts contact : lists) {
            System.out.printf("%-13s%-13s%-13s%-13s%-13s\n",
                    contact.getPhoneNumber(), contact.getGroupContact(),
                    contact.getFullName(), contact.getGender(), contact.getAddress());
        }
    }

    public void upDateContacts(Scanner input) {
        if (list.size() > 0) {
            System.out.println("Nhập số điện thoại bạn muốn cập nhật");
            String phone = input.nextLine().trim();
            for (Contacts c : list) {
                if (c.getPhoneNumber().compareTo(phone) == 0) {
                    System.out.println("Nhập tên group");
                    c.setGroupContact(input.nextLine());
                    c.setFullName(input.nextLine());
                    c.setAddress(input.nextLine());
                    c.setDateOfBirth(input.nextLine());
                    try {
                        c.setEmail(input.nextLine());
                    } catch (InvalidEmailException e) {
                        System.err.println(e.getInvalidEmailException());
                    }
                }
            }
        } else {
            System.out.println("Danh sách rỗng");
        }
    }

    public boolean removeContacts(Scanner input) {
        System.out.println("Nhập sdt để xóa");
        String phone = input.nextLine();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPhoneNumber().compareTo(phone) == 0) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    public void searchNumber(Scanner input) {
        System.out.println("Nhập sdt để tìm");
        String phone = input.nextLine();
        List<Contacts> lists = new ArrayList<>();
        for (Contacts c : list) {
            if (c.getPhoneNumber().compareTo(phone) == 0) {
                lists.add(c);
            }
        }
        showContacts(lists);
    }

    public boolean writerFile() {
        File file = new File(FILE_CONTACTS);
        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter printWriter = new PrintWriter(fileWriter, true)) {
            for (Contacts c : list) {
                printWriter.println(c.getPhoneNumber());
                printWriter.println(c.getGroupContact());
                printWriter.println(c.getFullName());
                printWriter.println(c.getGender());
                printWriter.println(c.getAddress());
                printWriter.println(c.getDateOfBirth());
                printWriter.println(c.getEmail());
                printWriter.close();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Contacts> readFromToFile() {
        List<Contacts> list = new ArrayList<>();
        File file = new File(FILE_CONTACTS);
        try {
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String phone = scanner.nextLine();
                String group = scanner.nextLine();
                String fullName = scanner.nextLine();
                String gender = scanner.nextLine();
                String address = scanner.nextLine();
                String dateOfBirth = scanner.nextLine();
                String email = scanner.nextLine();
                Contacts contacts = new Contacts(phone,group,fullName,gender,address,dateOfBirth,email);
                list.add(contacts);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
