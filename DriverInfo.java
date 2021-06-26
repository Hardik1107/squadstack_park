package squadstack;

public class DriverInfo {

    private Integer age;
    private Integer parkingSlot;
    private String vehicleNumber;

    public DriverInfo(Integer age, Integer parkingSlot, String vehicleNumber) {
        this.age = age;
        this.parkingSlot = parkingSlot;
        this.vehicleNumber = vehicleNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(Integer parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}