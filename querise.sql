CREATE TABLE doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    contact_number VARCHAR(20) NOT NULL
);
CREATE TABLE Chanel (
    chanelId INT PRIMARY KEY AUTO_INCREMENT,
    doctorId INT,
    date DATE NOT NULL,
    reason VARCHAR(255) NOT NULL,
    FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId)
);