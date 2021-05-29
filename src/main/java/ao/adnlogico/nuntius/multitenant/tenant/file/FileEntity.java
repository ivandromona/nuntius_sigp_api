///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ao.adnlogico.nuntius.multitenant.tenant.file;
//
//import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//import javax.persistence.Table;
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
///**
// *
// * @author domingos.fernando
// */
//@Entity
//@ConfigurationProperties(prefix = "file")
//@Table(name = "files")
//@Data
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//public class FileEntity implements Serializable
//{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Basic(optional = false)
//    @Column(name = "file_id")
//    private String fileId;
//
//    @Basic(optional = false)
//    @Column(name = "file_name")
//    private String fileName;
//
//    @Basic(optional = false)
//    @Column(name = "file_type")
//    private String fileType;
//
//    @Basic(optional = false)
//    @Column(name = "extension")
//    private String extension;
//
//    @Column(name = "upload_dir")
//    private String uploadDir;
//
//}
