package com.ey.controllers;

import com.ey.Service.DimSecService;
import com.ey.Service.UserSecService;
import com.ey.entities.DimSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@SessionAttributes("groupname")
public class WelcomeController {

    @Autowired
    DimSecService service;
    @Autowired
    UserSecService usrservice;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String dimsecurity(Model model) throws IOException {
        model.addAttribute("message", "EY_PB Application Security");
        System.out.println("entered dimsec");
        model.addAttribute(service.DimSecSvc());
        return "home";
    }

    @RequestMapping("/usersGroups")
    public String usrsecurity(Model model) throws IOException {
        model.addAttribute("message", "EY_PB Application Security");
        model.addAttribute(usrservice.UserSecSvc());
        System.out.println("entered usersngroups");
        return "users";
    }


    @RequestMapping("/ExpSec")
    public String expsecurity(Model model) throws IOException, InterruptedException {

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "exportdimsecurity.cmd");
//        File dir = new File("C:/security/batch");
        File dir = new File("F:\\Schedules\\Overnight\\Security\\batch\\");
        pb.directory(dir);
        Process p = pb.start();
        p.waitFor();
        System.out.println("Export Dimension Security ReturnValue: " + p.exitValue());

//        Runtime rt = Runtime.getRuntime();
//        try {
//            Process proc = rt.exec("C:\\security\\batch\\exportdimsecurity.cmd");
////            Process proc = rt.exec("F:\\Schedules\\Overnight\\Security\\batch\\exportdimsecurity.cmd");
//            proc.waitFor();
//            System.out.println("Export Security ReturnValue: " + proc.exitValue());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "confirm";

    }

    @RequestMapping("/Expgrpsec")
    public String expgrpsecurity(Model model) throws IOException, InterruptedException {

        ProcessBuilder pbgrp;
        pbgrp = new ProcessBuilder("cmd", "/c", "exportusersNgroupssecurity.cmd");
//        File dir = new File("C:/security/batch");
        File grpdir = new File("F:\\Schedules\\Overnight\\Security\\batch\\");
        pbgrp.directory(grpdir);
        Process grpp = pbgrp.start();
        grpp.waitFor();
        System.out.println("Export Group Security ReturnValue: " + grpp.exitValue());

        return "grpconfirm";
    }





    @RequestMapping(value = "/updateSec", method = RequestMethod.POST)
    public ModelAndView updateSec(HttpServletRequest request, HttpServletResponse response,
                                  @ModelAttribute DimSec dimSec) {

       // userService.register(user);
        System.out.println("update sec method called in the controller"+dimSec.getGroupname());
        return new ModelAndView("home", "getGroupname is:", dimSec.getGroupname());

    }

    @RequestMapping(value = "/DwnldSec", method = RequestMethod.GET)
    public void getdimsecFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"security.csv\"");
//        InputStream inputStream = new FileInputStream(new File("C:\\security\\ExportSecurity\\security.csv"));
        InputStream inputStream = new FileInputStream(new File("f:\\schedules\\overnight\\security\\ExportSecurity\\security.csv"));
        int nRead;
        while ((nRead = inputStream.read()) != -1) {
            response.getWriter().write(nRead);
        }
        inputStream.close();
    }


    @RequestMapping(value = "/DwnldgrpSec", method = RequestMethod.GET)
    public void getgroupsecFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"Groups.csv\"");
//        InputStream inputStream = new FileInputStream(new File("C:\\security\\ExportSecurity\\security.csv"));
        InputStream inputStream = new FileInputStream(new File("F:\\Schedules\\Overnight\\Security\\ExportusersNgroups\\Groups.csv"));
        int nRead;
        while ((nRead = inputStream.read()) != -1) {
            response.getWriter().write(nRead);
        }
        inputStream.close();
    }



    @GetMapping("/upload")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws InterruptedException {

        String UPLOADED_FOLDER = "f://schedules//overnight//security//ImportSecurity//";
//        String UPLOADED_FOLDER = "C://security//ImportSecurity//";

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "noFile";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "importdimsecurity.cmd");
//        File dir = new File("C:/security/batch");
            File dir = new File("F:\\Schedules\\Overnight\\Security\\batch\\");
            pb.directory(dir);
            Process p1 = pb.start();
            p1.waitFor();
            System.out.println("Import Dimension Security ReturnValue: " + p1.exitValue());


            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/grpupload")
    public String grpindex() {
        return "grpupload";
    }

    @PostMapping("/grpupload")
    public String singlegrpFileUpload(@RequestParam("grpfile") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws InterruptedException {

        String UPLOADED_FOLDER = "F://Schedules//Overnight//Security//ImportusersNgroups//";
//        String UPLOADED_FOLDER = "C://security//ImportSecurity//";

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "noFile";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "importusersNgroupssecurity.cmd");
//        File dir = new File("C:/security/batch");
            File dir = new File("F:\\Schedules\\Overnight\\Security\\batch\\");
            pb.directory(dir);
            Process p1 = pb.start();
            p1.waitFor();
            System.out.println("Import group Security ReturnValue: " + p1.exitValue());


            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/grpuploadStatus";
    }



    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping("/grpuploadStatus")
    public String grpuploadStatus() {
        return "grpuploadStatus";
    }


}