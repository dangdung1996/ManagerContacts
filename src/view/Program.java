package view;

import controller.ManagerContacts;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ManagerContacts manager = new ManagerContacts();
        int choice;
        do {
            System.out.println("----CHƯƠNG TRÌNH QUẢN LÍ DANH BẠ----");
            System.out.println("Chọn chức năng theo số để tiếp tục");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc File");
            System.out.println("7. Ghi vào File");
            System.out.println("0. Exit");
            System.out.println("-----------------------");
            choice = input.nextInt();
            input.nextLine();
            switch (choice){
                case 0:
                    System.exit(0);
                case 1:
                    manager.showContacts(manager.list);
                    break;
                case 2:
                    manager.creatContacts(input);
                    break;
                case 3:
                    manager.upDateContacts(input);
                    break;
                case 4:
                    boolean result = manager.removeContacts(input);
                    if (result)
                        System.out.println("Xoa Thanh Cong");
                    else System.out.println("Xoa That Bai");
                    break;
                case 5:
                    manager.searchNumber(input);
                    break;
                case 6:
                    manager.readFromToFile();
                    break;
                case 7:
                    boolean result1 = manager.writerFile();
                    if (result1) System.out.println("Ghi File Thanh Cong");
                    else System.out.println("Ghi File That Bai");
                    break;
            }
        }while (true);
    }
}