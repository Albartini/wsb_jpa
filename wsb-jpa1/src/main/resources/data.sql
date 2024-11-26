-- insert into address (id, address_line1, address_line2, city, postal_code)
-- values (1, 'xx', 'yy', 'city', '62-030');
-- insert into address (id, address_line1, address_line2, city, postal_code)
--             values (2, 'Taka', 'Owaka', 'Wroclaw', '51-030');
-- insert into address (id, address_line1, address_line2, city, postal_code)
--             values (3, 'Tam', 'Tutaj', 'Warszawa', '12-030');
-- insert into address (id, address_line1, address_line2, city, postal_code)
--             values (4, 'Arbuzowa', '13', 'Poznan', '23-030');
-- insert into address (id, address_line1, address_line2, city, postal_code)
--             values (5, 'Zimowa', '7', 'Krakow', '78-030');
--
-- Insert sample data into Address table

INSERT INTO Address (id, city, addressLine1, addressLine2, postalCode)
VALUES (1, 'New York', '123 Main St', 'Apt 4B', '10001'),
       (2, 'Los Angeles', '456 Elm St', 'Suite 5', '90001'),
       (3, 'Chicago', '789 Oak St', 'Apt 2A', '60601'),
       (4, 'Houston', '101 Pine St', 'Suite 10', '77001'),
       (5, 'Phoenix', '202 Maple St', 'Apt 3C', '85001'),
       (6, 'Philadelphia', '303 Cedar St', 'Suite 20', '19101'),
       (7, 'San Antonio', '404 Birch St', 'Apt 4D', '78201'),
       (8, 'San Diego', '505 Walnut St', 'Suite 30', '92101'),
       (9, 'Dallas', '606 Chestnut St', 'Apt 5E', '75201'),
       (10, 'San Jose', '707 Spruce St', 'Suite 40', '95101'),
       (11, 'Austin', '808 Fir St', 'Apt 6F', '73301'),
       (12, 'Jacksonville', '909 Redwood St', 'Suite 50', '32099'),
       (13, 'San Francisco', '1010 Cedar St', 'Apt 7G', '94101'),
       (14, 'Columbus', '1111 Pine St', 'Suite 60', '43201'),
       (15, 'Fort Worth', '1212 Elm St', 'Apt 8H', '76101'),
       (16, 'Indianapolis', '1313 Maple St', 'Suite 70', '46201'),
       (17, 'Charlotte', '1414 Oak St', 'Apt 9I', '28201'),
       (18, 'Seattle', '1515 Birch St', 'Suite 80', '98101'),
       (19, 'Denver', '1616 Walnut St', 'Apt 10J', '80201'),
       (20, 'Washington', '1717 Chestnut St', 'Suite 90', '20001');

-- Insert sample data into Doctor table
INSERT INTO Doctor (id, firstName, lastName, telephoneNumber, email, doctorNumber, specialization, Address_id)
VALUES (1, 'John', 'Doe', '555-1234', 'johndoe@example.com', 'D001', 'Cardiology', 1),
       (2, 'Jane', 'Smith', '555-5678', 'janesmith@example.com', 'D002', 'Neurology', 2),
       (3, 'Emily', 'Davis', '555-2345', 'emilydavis@example.com', 'D003', 'Pediatrics', 3),
       (4, 'Michael', 'Wilson', '555-3456', 'michaelwilson@example.com', 'D004', 'Orthopedics', 4),
       (5, 'Sarah', 'Taylor', '555-4567', 'sarahtaylor@example.com', 'D005', 'Dermatology', 5),
       (6, 'David', 'Anderson', '555-5678', 'davidanderson@example.com', 'D006', 'Ophthalmology', 6),
       (7, 'Laura', 'Thomas', '555-6789', 'laurathomas@example.com', 'D007', 'Gynecology', 7),
       (8, 'James', 'Martinez', '555-7890', 'jamesmartinez@example.com', 'D008', 'Oncology', 8),
       (9, 'Linda', 'Hernandez', '555-8901', 'lindahernandez@example.com', 'D009', 'Psychiatry', 9),
       (10, 'Robert', 'Moore', '555-9012', 'robertmoore@example.com', 'D010', 'Radiology', 10);

-- Insert sample data into Patient table
INSERT INTO Patient (id, firstName, lastName, telephoneNumber, email, patientNumber, dateOfBirth, Address_id)
VALUES (1, 'Alice', 'Brown', '555-8765', 'alicebrown@example.com', 'P001', '1980-05-15', 11),
       (2, 'Bob', 'Johnson', '555-4321', 'bobjohnson@example.com', 'P002', '1975-09-20', 12),
       (3, 'Charlie', 'Green', '555-5432', 'charliegreen@example.com', 'P003', '1990-01-10', 13),
       (4, 'Diana', 'King', '555-6543', 'dianaking@example.com', 'P004', '1985-02-20', 14),
       (5, 'Edward', 'Scott', '555-7654', 'edwardscott@example.com', 'P005', '1970-03-30', 15),
       (6, 'Fiona', 'Adams', '555-8765', 'fionaadams@example.com', 'P006', '1995-04-15', 16),
       (7, 'George', 'Baker', '555-9876', 'georgebaker@example.com', 'P007', '1988-05-25', 17),
       (8, 'Hannah', 'Carter', '555-0987', 'hannahcarter@example.com', 'P008', '1992-06-05', 18),
       (9, 'Ian', 'Evans', '555-1098', 'ianevans@example.com', 'P009', '1983-07-15', 19),
       (10, 'Julia', 'Foster', '555-2109', 'juliafoster@example.com', 'P010', '1977-08-25', 20);

-- Insert sample data into Visit table
INSERT INTO Visit (id, description, time, Doctor_id, Patient_id)
VALUES (1, 'Routine Checkup', '2023-10-01 10:00:00', 1, 1),
       (2, 'Follow-up', '2023-10-02 11:00:00', 2, 2),
       (3, 'Consultation', '2023-10-03 12:00:00', 3, 3),
       (4, 'Therapy Session', '2023-10-04 13:00:00', 4, 4),
       (5, 'Surgery Follow-up', '2023-10-05 14:00:00', 5, 5),
       (6, 'Vaccination', '2023-10-06 15:00:00', 6, 6),
       (7, 'Physical Exam', '2023-10-07 16:00:00', 7, 7),
       (8, 'Lab Test', '2023-10-08 17:00:00', 8, 8),
       (9, 'Emergency Visit', '2023-10-09 18:00:00', 9, 9),
       (10, 'Specialist Consultation', '2023-10-10 19:00:00', 10, 10);

-- Insert sample data into MedicalTreatment table
INSERT INTO MedicalTreatment (id, description, type, Visit_id)
VALUES (1, 'Blood Test', 'Lab', 1),
       (2, 'MRI Scan', 'Imaging', 2),
       (3, 'X-Ray', 'Imaging', 3),
       (4, 'Ultrasound', 'Imaging', 4),
       (5, 'CT Scan', 'Imaging', 5),
       (6, 'Physical Therapy', 'Rehabilitation', 6),
       (7, 'Chemotherapy', 'Oncology', 7),
       (8, 'Radiation Therapy', 'Oncology', 8),
       (9, 'Surgery', 'Surgical', 9),
       (10, 'Blood Transfusion', 'Lab', 10);
