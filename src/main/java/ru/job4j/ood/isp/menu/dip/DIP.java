package ru.job4j.ood.isp.menu.dip;

public class DIP {

    /**
     * Нарушение DIP: UserService напрямую зависит от конкретного класса Database,
     * что делает его трудным для тестирования и изменения. Изменение базы данных потребует
     * изменения UserService.
     */

    static class Database {
        public void connect() {
        }
    }

    static class UserService {
        private final Database database;

        public UserService() {
            this.database = new Database();
        }

        public void saveUser(String username) {
            database.connect();
        }
    }

    /**
     * Нарушение DIP: NotificationService зависит от конкретного класса EmailService,
     * что затрудняет замену на другой способ уведомления (например, SMS) без изменения кода NotificationService.
     */

    static class EmailService {
        public void sendEmail(String message) {
        }
    }

    static class NotificationService {
        private final EmailService emailService;

        public NotificationService() {
            this.emailService = new EmailService();
        }

        public void notifyUser(String message) {
            emailService.sendEmail(message);
        }
    }

    /**
     * Нарушение DIP: OrderService зависит от конкретного класса PayPalProcessor,
     * а не от абстракции PaymentProcessor. Это делает OrderService менее гибким и трудным для тестирования.
     */

    interface PaymentProcessor {
        void processPayment(double amount);
    }

    static class PayPalProcessor implements PaymentProcessor {
        public void processPayment(double amount) {
        }
    }

    static class OrderService {
        private final PayPalProcessor paymentProcessor;

        public OrderService() {
            this.paymentProcessor = new PayPalProcessor();
        }

        public void placeOrder(double amount) {
            paymentProcessor.processPayment(amount);
        }
    }
}
