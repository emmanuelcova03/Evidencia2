import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

// Clase para doctor
class Doctor {
    private int id;
    private String nombre;
    private String especialidad;

    public Doctor(int id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}

// Clase para cita
class Cita {
    private int id;
    private Date fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(int id, Date fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public int getId() {
        return id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}

// Clase para paciente
class Paciente {
    private int id;
    private String nombre;

    public Paciente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}

// Clase para gestionar el sistema de citas
class SistemaCitas {
    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;
    private int siguienteIdDoctor = 1;
    private int siguienteIdPaciente = 1;
    private int siguienteIdCita = 1;

    public SistemaCitas() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
    }

    // Agregar un doctor con un ID específico, nombre y especialidad
    public void agregarDoctor(int id, String nombre, String especialidad) {
        Doctor doctor = new Doctor(id, nombre, especialidad);
        doctores.add(doctor);
        if (id >= siguienteIdDoctor) {
            siguienteIdDoctor = id + 1;
        }
    }

    // Agregar un paciente con un ID específico y nombre
    public void agregarPaciente(int id, String nombre) {
        Paciente paciente = new Paciente(id, nombre);
        pacientes.add(paciente);
        if (id >= siguienteIdPaciente) {
            siguienteIdPaciente = id + 1;
        }
    }

    // Crear una cita con un doctor y paciente específicos, fecha y motivo
    public void crearCita(int idDoctor, int idPaciente, Date fechaHora, String motivo) {
        Doctor doctorSeleccionado = null;
        for (Doctor doctor : doctores) {
            if (doctor.getId() == idDoctor) {
                doctorSeleccionado = doctor;
                break;
            }
        }
        Paciente pacienteSeleccionado = null;
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == idPaciente) {
                pacienteSeleccionado = paciente;
                break;
            }
        }
        if (doctorSeleccionado != null && pacienteSeleccionado != null) {
            Cita cita = new Cita(siguienteIdCita++, fechaHora, motivo, doctorSeleccionado, pacienteSeleccionado);
            citas.add(cita);
        }
    }

    // Obtener la lista de doctores
    public List<Doctor> obtenerDoctores() {
        return doctores;
    }

    // Obtener la lista de pacientes
    public List<Paciente> obtenerPacientes() {
        return pacientes;
    }

    // Obtener la lista de citas
    public List<Cita> obtenerCitas() {
        return citas;
    }
}

public class Evidencia2 {
    public static void main(String[] args) {
        // Crear sistema de citas
        SistemaCitas sistemaCitas = new SistemaCitas();

        // Crear un objeto para manejar la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú Principal:");
            System.out.println("1. Dar de alta Doctor");
            System.out.println("2. Dar de alta Paciente");
            System.out.println("3. Crear Cita");
            System.out.println("4. Mostrar Citas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    // Agregar un doctor
                    System.out.print("ID del doctor: ");
                    int idDoctor = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    System.out.print("Nombre del doctor: ");
                    String nombreDoctor = scanner.nextLine();
                    System.out.print("Especialidad del doctor: ");
                    String especialidadDoctor = scanner.nextLine();
                    sistemaCitas.agregarDoctor(idDoctor, nombreDoctor, especialidadDoctor);
                    System.out.println("Doctor agregado con éxito.");
                    break;
                case 2:
                    // Agregar un paciente
                    System.out.print("ID del paciente: ");
                    int idPaciente = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    System.out.print("Nombre del paciente: ");
                    String nombrePaciente = scanner.nextLine();
                    sistemaCitas.agregarPaciente(idPaciente, nombrePaciente);
                    System.out.println("Paciente agregado con éxito.");
                    break;
                case 3:
                    // Crear una cita
                    System.out.print("ID del doctor: ");
                    int idDoctorCita = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    System.out.print("ID del paciente: ");
                    int idPacienteCita = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    System.out.print("Fecha y Hora de la cita (yyyy-MM-dd HH:mm): ");
                    String fechaHoraStr = scanner.nextLine();
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date fechaHora = dateFormat.parse(fechaHoraStr);
                        System.out.print("Motivo de la cita: ");
                        String motivoCita = scanner.nextLine();
                        sistemaCitas.crearCita(idDoctorCita, idPacienteCita, fechaHora, motivoCita);
                        System.out.println("Cita creada con éxito.");
                    } catch (Exception e) {
                        System.out.println("Error al crear la cita.");
                    }
            }
        }
    }
}
