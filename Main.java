public class Main {
    public static void main(String[] args) {
        // 1. Membuat objek Course (Kursus)
        Course javaCourse = new Course();
        javaCourse.setTitle("Pemrograman Berorientasi Objek");

        // 2. Membuat Mahasiswa 1 dan Menambahkan Nilai Tugas
        Student mhs1 = new Student();
        mhs1.setName("Budi");
        
        CourseRecord record1 = new CourseRecord();
        record1.setStudent(mhs1);
        
        Assignment tugas1 = new Assignment();
        tugas1.setMark(85);
        Assignment tugas2 = new Assignment();
        tugas2.setMark(90);
        
        record1.addAssignment(tugas1);
        record1.addAssignment(tugas2);
        javaCourse.addCourseRecord(record1);

        // 3. Membuat Mahasiswa 2 dan Menambahkan Nilai Tugas
        Student mhs2 = new Student();
        mhs2.setName("Ani");
        
        CourseRecord record2 = new CourseRecord();
        record2.setStudent(mhs2);
        
        Assignment tugas3 = new Assignment();
        tugas3.setMark(95);
        Assignment tugas4 = new Assignment();
        tugas4.setMark(100);
        
        record2.addAssignment(tugas3);
        record2.addAssignment(tugas4);
        javaCourse.addCourseRecord(record2);

        // 4. Menguji Metode yang Telah Dibuat
        System.out.println("=== Daftar Mahasiswa Course: " + javaCourse.getTitle() + " ===");
        javaCourse.printCourseStudents();

        System.out.println("\n=== Mahasiswa dengan Nilai Rata-rata Terbaik ===");
        javaCourse.printBestStudent();

        System.out.println("\n=== Mahasiswa yang Layak Ujian Akhir ===");
        javaCourse.printFinalExamStudents();
    }
}