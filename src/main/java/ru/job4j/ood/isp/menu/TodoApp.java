package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {
    private final Menu menu = new SimpleMenu();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("Введите команду (add, select, print, exit):");
            String command = scanner.nextLine();
            switch (command) {
                case "add" -> addItem();
                case "select" -> selectItem();
                case "print" -> printMenu();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Неизвестная команда.");
            }
        }
    }

    private void addItem() {
        System.out.println("Введите имя родительского элемента (или 'null' для корня):");
        String parentName = scanner.nextLine();
        if (parentName.equals("null")) {
            parentName = null;
        }
        System.out.println("Введите имя элемента:");
        String childName = scanner.nextLine();
        menu.add(parentName, childName, () -> System.out.println("Действие для " + childName));
    }

    private void selectItem() {
        System.out.println("Введите имя элемента для выбора:");
        String itemName = scanner.nextLine();
        Optional<Menu.MenuItemInfo> itemInfo = menu.select(itemName);
        itemInfo.ifPresentOrElse(
                info -> System.out.println("Выбрано: " + info.getName()),
                () -> System.out.println("Элемент не найден.")
        );
    }

    private void printMenu() {
        MenuPrinter printer = new Printer();
        printer.print(menu);
    }

}