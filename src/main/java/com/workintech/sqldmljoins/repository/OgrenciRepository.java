package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {

    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "SELECT o.* FROM ogrenci o " +
            "INNER JOIN islem i " +
            "ON o.ogrno = i.ogrno";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();

    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "select o.* from ogrenci o\n" +
            "left join islem i\n" +
            "on o.ogrno = i.ogrno\n" +
            "where i.ogrno is null;\n";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT o.sinif, COUNT(i.kitapno) FROM ogrenci o " +
            "LEFT JOIN islem i " +
            "ON o.ogrno = i.ogrno " +
            "WHERE o.sinif IN ('10A', '10B') " +
            "GROUP BY o.sinif";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin.
    String QUESTION_5 = "SELECT COUNT(o.ogrno) FROM ogrenci o";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT COUNT(DISTINCT o.ad) FROM ogrenci o";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //İsme göre öğrenci sayılarının adedini bulunuz. Örn: Ali: 2, Mehmet: 3
    String QUESTION_7 = "SELECT o.ad, COUNT(*) FROM ogrenci o " +
            "GROUP BY o.ad";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();

    //Her sınıftaki öğrenci sayısını listeleyiniz.
    String QUESTION_8 = "SELECT o.sinif, COUNT(*) FROM ogrenci o " +
            "GROUP BY o.sinif";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    //Ad ve soyada göre öğrenci sayılarının adedini bulunuz.
    String QUESTION_9 = "select o.ad, o.soyad, count(i.kitapno) from ogrenci o inner join islem i on o.ogrno=i.ogrno group by o.ad, o.soyad";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
