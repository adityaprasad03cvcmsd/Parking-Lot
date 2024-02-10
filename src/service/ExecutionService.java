package service;

import java.util.Arrays;
import java.util.Scanner;

public class ExecutionService {

    private final ParkingService parkingService;

    public ExecutionService() {
        this.parkingService = new ParkingService(6); // no of slots is assigned here
    }

    public void execute(){
        Scanner scanner = new Scanner(System.in);

        String command;
        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                break;
            }

            String[] commandParts = command.split(" ");
            String commandName = commandParts[0].toLowerCase();
            String[] arguments = commandParts.length > 1 ? Arrays.copyOfRange(commandParts, 1, commandParts.length) : new String[0];

            try {
                executeCommand(commandName, arguments);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command or arguments: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void executeCommand(String commandName, String[] arguments) {
        switch (commandName) {
            case "create_parking_lot":
                if (arguments.length != 1) {
                    throw new IllegalArgumentException("Expected 1 argument: total number of slots");
                }
                parkingService.creatParkingLot(Integer.parseInt(arguments[0]));
                break;

            case "park":
                if (arguments.length != 2) {
                    throw new IllegalArgumentException("Expected 2 arguments: registration number and color");
                }
                parkingService.park(arguments[0], arguments[1]);
                break;

            case "leave":
                if (arguments.length != 1) {
                    throw new IllegalArgumentException("Expected 1 argument: slot number");
                }
                parkingService.leave(Integer.parseInt(arguments[0]));
                break;

            case "status":
                if (arguments.length != 0) {
                    throw new IllegalArgumentException("Expected 0 arguments");
                }
                parkingService.status();
                break;

            case "registration_numbers_for_cars_with_colour":
                if (arguments.length != 1) {
                    throw new IllegalArgumentException("Expected 1 argument: color");
                }
                System.out.println(parkingService.registrationNumberOfCarsWithColor(arguments[0]));
                break;

            case "slot_number_for_registration_number":
                if (arguments.length != 1) {
                    throw new IllegalArgumentException("Expected 1 argument: registration number");
                }
                int slotNumber = parkingService.slotNumberFromRegistrationNumber(arguments[0]);
                System.out.println(slotNumber != -1 ? "Slot number: " + slotNumber : "Car not found");
                break;

            case "slot_numbers_for_car_colour":
                if (arguments.length != 1) {
                    throw new IllegalArgumentException("Expected 1 argument: color");
                }
                System.out.println(parkingService.slotNumbersFromCarColor(arguments[0]));
                break;

            default:
                throw new IllegalArgumentException("Invalid command: " + commandName);
        }
    }
}
