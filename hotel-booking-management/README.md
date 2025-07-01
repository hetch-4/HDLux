# Hotel Booking Management System

## Overview
The Hotel Booking Management System is a Java-based application designed to facilitate the management of hotel bookings. It allows users to manage rooms, customers, and bookings efficiently.

## Features
- Room management: Add, remove, and list available rooms.
- Booking management: Create, cancel, and retrieve bookings.
- Customer management: Manage customer information for bookings.
- Date utilities: Handle date formatting and validation.

## Project Structure
```
hotel-booking-management
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── App.java
│   │   │   ├── models
│   │   │   │   ├── Room.java
│   │   │   │   ├── Booking.java
│   │   │   │   └── Customer.java
│   │   │   ├── services
│   │   │   │   ├── BookingService.java
│   │   │   │   └── RoomService.java
│   │   │   └── utils
│   │   │       └── DateUtils.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           ├── models
│           │   └── RoomTest.java
│           ├── services
│           │   └── BookingServiceTest.java
│           └── AppTest.java
├── .gitignore
├── pom.xml
└── README.md
```

## Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd hotel-booking-management
   ```
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Run the application:
   ```
   mvn exec:java -Dexec.mainClass="App"
   ```

## Usage
- Follow the prompts in the console to manage rooms, bookings, and customers.
- Ensure to input valid dates for check-in and check-out.

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.