package Lab1;

    public class Customer {
        private String customerId;
        private String fullName;
        private String email;
        private String phoneNumber;

        public Customer(String customerId, String fullName, String email, String phoneNumber) {
            this.customerId = customerId;
            this.fullName = fullName;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        public Customer() {

        }

        public String getCustomerId() { return this.customerId; }
        public String getFullName() { return fullName; }
        public String getEmail() { return this.email; }
        public String getPhoneNumber() { return this.phoneNumber; }

        @Override
        public String toString() {
            return "Customer [ID=" + customerId + ", Name=" + fullName + ", Phone=" + phoneNumber + "]";
        }
        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

    }

