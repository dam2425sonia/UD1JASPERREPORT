package jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import jasper.GenerateReport.Employee;

public class GenerateReport {

    public static class Employee {
        private int id;
        private String name;
        private double salary;
    
        public Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }
    
        public int getId() {
            return id;
        }
    
        public String getName() {
            return name;
        }
    
        public double getSalary() {
            return salary;
        }
    }
    
    public static void main(String[] args) {
        try {
            // Compilar el archivo JRXML a .jasper si aún no está compilado
            String sourceFileName = "jasper/src/main/java/jasper/employee_report.jrxml";
            JasperCompileManager.compileReportToFile(sourceFileName);

            // Crear una lista de objetos que servirá como fuente de datos
            List<Employee> employees = new ArrayList<>();
            employees.add(new Employee(1, "John Doe", 50000.00));
            employees.add(new Employee(2, "Jane Smith", 60000.00));

            // Crear la fuente de datos desde la lista de empleados
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

            // Cargar el informe compilado
            JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileName);

            // Llenar el informe con datos y parámetros (en este caso, no hay parámetros)
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Exportar el informe a un archivo PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "jasper/src/main/java/jasper/employee_report.pdf");

            System.out.println("Informe generado exitosamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

