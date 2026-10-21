import java.util.Vector;
import java.util.Enumeration;

public class Course {
    private String title;
    private Vector<CourseRecord> courseRecords; // Menggunakan generik <CourseRecord>

    Course() { 
        courseRecords = new Vector<CourseRecord>(); 
    }

    public String getTitle() { 
        return title; 
    }
    
    public void setTitle(String t) { 
        title = t; 
    }
    
    public void addCourseRecord(CourseRecord cr) { 
        courseRecords.addElement(cr); 
    }
    
    public Enumeration<CourseRecord> getCourseRecords() { 
        return courseRecords.elements(); 
    }

    public void printCourseStudents() {
        // Mencetak seluruh mahasiswa yang mengambil course ini
        Enumeration<CourseRecord> e = courseRecords.elements();
        while (e.hasMoreElements()) {
            CourseRecord cr = e.nextElement();
            System.out.println(cr.getStudent().getName());
        }
    }

    public void printBestStudent() {
        // Mencetak mahasiswa dengan nilai rata-rata tugas terbaik
        Enumeration<CourseRecord> e = courseRecords.elements();
        CourseRecord bestRecord = null;
        double maxAverage = -1.0;

        while (e.hasMoreElements()) {
            CourseRecord cr = e.nextElement();
            Enumeration<Assignment> assignEnum = cr.getAssignments();
            double totalMark = 0;
            int count = 0;
            boolean valid = true;

            try {
                while (assignEnum.hasMoreElements()) {
                    Assignment a = assignEnum.nextElement();
                    totalMark += a.getMark(); // Bisa melempar NotYetSetException jika belum di-set
                    count++;
                }
            } catch (NotYetSetException ex) {
                valid = false; // Jika ada nilai yang belum di-set, abaikan/batalkan perhitungan untuk mahasiswa ini
            }

            if (valid && count > 0) {
                double average = totalMark / count;
                if (bestRecord == null || average > maxAverage) {
                    maxAverage = average;
                    bestRecord = cr;
                }
            }
        }

        if (bestRecord != null) {
            System.out.println("Best Student: " + bestRecord.getStudent().getName());
        } else {
            System.out.println("Tidak ada mahasiswa dengan nilai lengkap.");
        }
    }

    public void printFinalExamStudents() {
        // Mencetak mahasiswa yang layak ujian akhir 
        // (Syarat: seluruh tugas sudah dinilai / tidak ada NotYetSetException)
        Enumeration<CourseRecord> e = courseRecords.elements();
        while (e.hasMoreElements()) {
            CourseRecord cr = e.nextElement();
            Enumeration<Assignment> assignEnum = cr.getAssignments();
            boolean eligible = true;

            try {
                while (assignEnum.hasMoreElements()) {
                    Assignment a = assignEnum.nextElement();
                    a.getMark(); // Memeriksa apakah semua nilai sudah di-set
                }
            } catch (NotYetSetException ex) {
                eligible = false; // Jika ada yang belum di-set, tidak layak ujian akhir
            }

            if (eligible) {
                System.out.println(cr.getStudent().getName());
            }
        }
    }
}