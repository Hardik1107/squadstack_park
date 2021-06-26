package squadstack;

import java.util.PriorityQueue;

import static squadstack.InputFileConstants.*;


public class DataProcessorImpl implements DataProcessor {

    private final DriverInfo[] driverInfos;
    private final PriorityQueue<Integer> nearestSlotQueue;
    private int totalSlots;

    public DataProcessorImpl(int totalSlots) {
        nearestSlotQueue = new PriorityQueue<>();
        this.totalSlots = totalSlots;
        driverInfos = new DriverInfo[totalSlots + 1];
        for (int i = 1; i < totalSlots + 1; i++) {
            nearestSlotQueue.add(i);
        }
    }

    @Override
    public String process(String row) {
        String[] rowValues = row.split(" ");
        try {
            switch (rowValues[0].toLowerCase()) {
                case PARK:
                    return park(rowValues);
                case LEAVE:
                    return leave(rowValues);
                case AGE_QUERY:
                    return ageQuery(rowValues);
                case SLOT_QUERY:
                    return slotNumber(rowValues);
                case VEHICLE_QUERY:
                    return vehicleQuery(rowValues);
                default:
                    return "No Such Query: " + rowValues[0].toLowerCase();

            }
        } catch (Exception e) {
            return "Wrong Query";
        }

    }

    private String park(String[] rowValues) {
        if (rowValues.length != 4) {
            return "Length of query does not match";
        }
        if (nearestSlotQueue.isEmpty()) {
            return "No Slots available";
        }
        int nearestSlot = nearestSlotQueue.poll();
        String vehicleNumber = rowValues[1];
        int age = Integer.parseInt(rowValues[3]);
        DriverInfo driverInfo = new DriverInfo(age, nearestSlot, vehicleNumber);
        driverInfos[nearestSlot] = driverInfo;
        return "Car with vehicle registration number \"" + driverInfo.getVehicleNumber() + "\" has been parked at slot number " + nearestSlot;

    }

    private String leave(String[] rowValues) {
        if (rowValues.length != 2) {
            return "Length of query does not match";
        }
        int leavingSlot = Integer.parseInt(rowValues[1]);
        nearestSlotQueue.add(leavingSlot);
        DriverInfo driverInfo = driverInfos[leavingSlot];
        driverInfos[leavingSlot] = null;
        return "Slot number " + leavingSlot + " vacated, the car with vehicle registration number \"" + driverInfo.getVehicleNumber() + "\" left the space, the driver of the car was of age " + driverInfo.getAge();


    }

    private String ageQuery(String[] rowValues) {
        if (rowValues.length != 2) {
            return "Length of query does not match";
        }
        String toReturn = "";
        for (int i = 1; i < totalSlots + 1; i++) {
            if (driverInfos[i] != null) {
                if (driverInfos[i].getAge() == Integer.parseInt(rowValues[1])) {
                    toReturn = toReturn.concat("" + i + ",");
                }
            }
        }
        if (toReturn.lastIndexOf(",") != -1) {
            toReturn = toReturn.substring(0, toReturn.lastIndexOf(","));
        }
        return toReturn;

    }

    private String vehicleQuery(String[] rowValues) {
        if (rowValues.length != 2) {
            return "Length of query does not match";
        }
        String toReturn = "";
        for (int i = 1; i < totalSlots + 1; i++) {
            if (driverInfos[i] != null) {
                if (driverInfos[i].getAge() == Integer.parseInt(rowValues[1])) {
                    toReturn = toReturn.concat(driverInfos[i].getVehicleNumber() + ",");
                }
            }
        }
        if (toReturn.lastIndexOf(",") != -1) {
            toReturn = toReturn.substring(0, toReturn.lastIndexOf(","));
        }
        return toReturn;
    }

    private String slotNumber(String[] rowValues) {
        if (rowValues.length != 2) {
            return "Length of query does not match";
        }
        for (int i = 1; i < totalSlots + 1; i++) {
            if (driverInfos[i] != null) {
                if (driverInfos[i].getVehicleNumber().equalsIgnoreCase(rowValues[1])) {
                    return String.valueOf(driverInfos[i].getParkingSlot());
                }
            }
        }
        return "No car found for vehicle number " + rowValues[1];
    }
}
