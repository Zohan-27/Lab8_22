import java.util.Scanner;

interface Connectable {
    void connect();
    void disconnect();
}

interface InformationProvider<T> {
    String getInfo();
    T getDetails();
}

abstract class ElectronicDevice<T> implements InformationProvider<T> {
    private String brand;
    protected double price;
    protected T details;

    public ElectronicDevice(String brand, double price, T details) {
        this.brand = brand;
        this.price = price;
        this.details = details;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public T getDetails() {
        return details;
    }
}

class CableDetails {
    private int length;

    public CableDetails(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

class Cable extends ElectronicDevice<CableDetails> implements Connectable {
    public Cable(String brand, double price, CableDetails details) {
        super(brand, price, details);
    }

    @Override
    public void connect() {
        System.out.println("Кабель подключен.");
    }

    @Override
    public void disconnect() {
        System.out.println("Кабель отключен.");
    }

    @Override
    public String getInfo() {
        return "Кабель [бренд=" + getBrand() + ", цена=" + getPrice() + ", длина=" + getDetails().getLength() + "]";
    }
}

class CaseDetails {
    private String material;

    public CaseDetails(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}

class Case extends ElectronicDevice<CaseDetails> implements InformationProvider<CaseDetails> {
    public Case(String brand, double price, CaseDetails details) {
        super(brand, price, details);
    }

    @Override
    public String getInfo() {
        return "Корпус [бренд=" + getBrand() + ", цена=" + getPrice() + ", материал=" + getDetails().getMaterial() + "]";
    }
}

class CapabilityDetails {
    private String feature;

    public CapabilityDetails(String feature) {
        this.feature = feature;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}

class Capability extends ElectronicDevice<CapabilityDetails> implements Connectable, InformationProvider<CapabilityDetails> {
    public Capability(String brand, double price, CapabilityDetails details) {
        super(brand, price, details);
    }

    @Override
    public void connect() {
        System.out.println("Возможность подключена.");
    }

    @Override
    public void disconnect() {
        System.out.println("Возможность отключена.");
    }

    @Override
    public String getInfo() {
        return "Возможность [бренд=" + getBrand() + ", цена=" + getPrice() + ", особенность=" + getDetails().getFeature() + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        InformationProvider<?>[] infoProviderDevices = new InformationProvider<?>[10];
        int count = 0;
        boolean continueInput = true;

        while (continueInput && count < infoProviderDevices.length) {
            System.out.println("Введите данные для устройства " + (count + 1) + ":");
            System.out.print("Бренд: ");
            String brand = scanner.nextLine();
            System.out.print("Цена: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Выберите тип устройства:");
            System.out.println("1. Кабель");
            System.out.println("2. Корпус");
            System.out.println("3. Возможность");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите длину кабеля: ");
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    CableDetails cableDetails = new CableDetails(length);
                    Cable cable = new Cable(brand, price, cableDetails);
                    infoProviderDevices[count] = cable;
                    break;
                case 2:
                    System.out.print("Введите материал корпуса: ");
                    String material = scanner.nextLine();
                    CaseDetails caseDetails = new CaseDetails(material);
                    Case caseDevice = new Case(brand, price, caseDetails);
                    infoProviderDevices[count] = caseDevice;
                    break;
                case 3:
                    System.out.print("Введите особенность устройства: ");
                    String feature = scanner.nextLine();
                    CapabilityDetails capabilityDetails = new CapabilityDetails(feature);
                    Capability capability = new Capability(brand, price, capabilityDetails);
                    infoProviderDevices[count] = capability;
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите 1, 2 или 3.");
                    continue;
            }

            count++;

            System.out.print("Продолжить ввод данных? (y/n): ");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                continueInput = false;
            }
        }

        System.out.println("Информация об устройствах:");
        for (InformationProvider<?> device : infoProviderDevices) {
            if (device != null) {
                System.out.println(device.getInfo());
            }
        }

        scanner.close();
    }
}
