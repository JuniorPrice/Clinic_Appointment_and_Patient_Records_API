-- ====================================================
-- CLINIC MANAGEMENT SYSTEM - ORACLE DDL
-- ====================================================

-- ====================================================
-- SEQUENCES FOR AUTO-INCREMENT
-- ====================================================

CREATE SEQUENCE doctors_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE patients_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE slots_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE appointments_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE visits_seq START WITH 1 INCREMENT BY 1;

-- ====================================================
-- ENTITY 1: DOCTORS
-- ====================================================

CREATE TABLE doctors (
    d_id NUMBER(19) PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    specialty VARCHAR2(255) NOT NULL,
    working_hours_start TIMESTAMP NOT NULL,
    working_hours_end TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ====================================================
-- ENTITY 2: PATIENTS
-- ====================================================

CREATE TABLE patients (
    p_id NUMBER(19) PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    phone VARCHAR2(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ====================================================
-- ENTITY 3: SLOTS
-- ====================================================

CREATE TABLE slots (
    s_id NUMBER(19) PRIMARY KEY,
    d_id NUMBER(19) NOT NULL,
    slot_date DATE NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    is_active CHAR(1) DEFAULT '1' CHECK (is_active IN ('0', '1')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_slots_doctor FOREIGN KEY (d_id) REFERENCES doctors(d_id),
    CONSTRAINT uk_slot_doctor_datetime UNIQUE (d_id, slot_date, start_time)
);

-- ====================================================
-- ENTITY 4: APPOINTMENTS
-- ====================================================

CREATE TABLE appointments (
    a_id NUMBER(19) PRIMARY KEY,
    s_id NUMBER(19) NOT NULL,
    p_id NUMBER(19) NOT NULL,
    status VARCHAR2(20) DEFAULT 'BOOKED' CHECK (status IN ('BOOKED', 'CANCELLED', 'COMPLETED', 'RESCHEDULED')),
    rescheduled_to_a_id NUMBER(19),
    booked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cancelled_at TIMESTAMP,
    completed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_appointments_slot FOREIGN KEY (s_id) REFERENCES slots(s_id),
    CONSTRAINT fk_appointments_patient FOREIGN KEY (p_id) REFERENCES patients(p_id),
    CONSTRAINT fk_appointments_rescheduled FOREIGN KEY (rescheduled_to_a_id) REFERENCES appointments(a_id)
);

-- ====================================================
-- ENTITY 5: VISITS
-- ====================================================

CREATE TABLE visits (
    v_id NUMBER(19) PRIMARY KEY,
    a_id NUMBER(19) NOT NULL UNIQUE,
    diagnosis CLOB NOT NULL,
    prescription CLOB,
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_visits_appointment FOREIGN KEY (a_id) REFERENCES appointments(a_id)
);

-- ====================================================
-- INDEXES FOR PERFORMANCE
-- ====================================================

CREATE INDEX idx_slots_doctor_date_active ON slots(d_id, slot_date, is_active);
CREATE INDEX idx_appointments_patient_status ON appointments(p_id, status);
CREATE INDEX idx_appointments_slot_status ON appointments(s_id, status);

-- ====================================================
-- TRIGGERS FOR UPDATED_AT (optional - JPA can handle this)
-- ====================================================

CREATE OR REPLACE TRIGGER doctors_updated_at
BEFORE UPDATE ON doctors
FOR EACH ROW
BEGIN
    :NEW.updated_at := CURRENT_TIMESTAMP;
END;
/

CREATE OR REPLACE TRIGGER patients_updated_at
BEFORE UPDATE ON patients
FOR EACH ROW
BEGIN
    :NEW.updated_at := CURRENT_TIMESTAMP;
END;
/

CREATE OR REPLACE TRIGGER slots_updated_at
BEFORE UPDATE ON slots
FOR EACH ROW
BEGIN
    :NEW.updated_at := CURRENT_TIMESTAMP;
END;
/

CREATE OR REPLACE TRIGGER appointments_updated_at
BEFORE UPDATE ON appointments
FOR EACH ROW
BEGIN
    :NEW.updated_at := CURRENT_TIMESTAMP;
END;
/

CREATE OR REPLACE TRIGGER visits_updated_at
BEFORE UPDATE ON visits
FOR EACH ROW
BEGIN
    :NEW.updated_at := CURRENT_TIMESTAMP;
END;
/