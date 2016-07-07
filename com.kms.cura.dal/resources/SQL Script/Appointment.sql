USE cura;

CREATE TABLE Appointments (
	doctor_id INT NOT NULL,
    patient_id INT NOT NULL,
    appt_day DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    current_status INT NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctor (user_id),
    FOREIGN KEY (patient_id) REFERENCES patient (user_id)
);

INSERT INTO Appointments (doctor_id, patient_id, appt_day, start_time, end_time, current_status) VALUES (3, 2, 20160830, '13:00:00', '14:00:00', 0);
INSERT INTO Appointments (doctor_id, patient_id, appt_day, start_time, end_time, current_status) VALUES (4, 2, 20160825, '09:00:00', '10:00:00', 2);
INSERT INTO Appointments (doctor_id, patient_id, appt_day, start_time, end_time, current_status) VALUES (9, 2, 20160830, '08:00:00', '10:00:00', 1);
INSERT INTO Appointments (doctor_id, patient_id, appt_day, start_time, end_time, current_status) VALUES (5, 2, 20160830, '15:00:00', '16:30:00', 3);
