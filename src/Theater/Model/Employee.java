package Theater.Model;

public class Employee {
    private int code;
    private String FIO;
    private int specialty;
    private int phone;
    private String category;
    private int direction;

    private final Collective group;

    private Employee(Builder builder) {
        this.code = builder.code;
        this.FIO = builder.FIO;
        this.specialty = builder.specialty;
        this.phone = builder.phone;
        this.category = builder.category;
        this.group = builder.group;
        this.direction = builder.direction;
    }

    public static class Builder {
        private int code;
        private String FIO;
        private int specialty;
        private int phone;
        private String category;
        private int direction = -1;

        private Collective group;

        public Builder(int code, String FIO) {
            this.code = code;
            this.FIO = FIO;
        }

        private Builder code(int code) {
            this.code = code;
            return this;
        }

        private Builder FIO(String FIO) {
            this.FIO = FIO;
            return this;
        }

        public Builder specialty(int specialty) {
            this.specialty = specialty;
            return this;
        }

        public Builder phone(int phone) {
            this.phone = phone;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder group(Collective group) {
            this.group = group;
            this.direction = group.getDirection();
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    public int getCode() {
        return code;
    }

    public String getFIO() {
        return FIO;
    }

    public int getSpecialty() {
        return specialty;
    }

    public int getPhone() {
        return phone;
    }

    public String getCategory() {
        return category;
    }

    public int getDirection() {
        return direction;
    }

    public Collective getGroup() {
        return group;
    }
}
