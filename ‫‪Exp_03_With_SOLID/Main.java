import PaymentServices.*;

import java.util.Scanner;

public class Main {

    static OrderRegisterService registerService;
    static OrderPaymentService paymentService;


    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String customerName;
        Order order;
        int customerAnswerForOrder = 0;
        int customerAnswerForPaymentMethod;

        System.out.println("Enter Customer Name : ");
        customerName = scanner.nextLine();
        order = new Order(customerName);

        //Step 1 : Select Order Items
        while (customerAnswerForOrder!=3) {
            System.out.println("For Ordering Sandwich enter 1.");
            System.out.println("For Ordering Pizza enter 2.");
            System.out.println("For submit your order enter 3");
            customerAnswerForOrder = scanner.nextInt();

            if (customerAnswerForOrder == 1) {
                order.addItem(new Food("sandwich",1000));
            } else if (customerAnswerForOrder == 2) {
                order.addItem(new Food("pizza",2000));
            }
        }

        //Step2 : Select Payment Method
        System.out.println("Enter Your Payment Method (1 for online, 2 for on-site, 3 for phone):");
        customerAnswerForPaymentMethod = scanner.nextInt();

        registerService = OrderRegisterFactory.getOrderRegisterService(customerAnswerForPaymentMethod);
        paymentService = OrderPaymentFactory.getOrderPaymentService(customerAnswerForPaymentMethod);  // initialize the services

        registerService.orderRegister(customerName); // register the new customer

        //Step3 : pay price
        System.out.println("Pay Price:");
        paymentService.orderPayment(order.getTotalPrice());  // payment for customer

        //Finally Print Bill
        System.out.println(order);
    }

}
