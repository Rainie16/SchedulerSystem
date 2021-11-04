package com.mercury.SchedulerSystem.controller;

import com.mercury.SchedulerSystem.bean.Interview;
import com.mercury.SchedulerSystem.http.Response;
import com.mercury.SchedulerSystem.service.InterviewService;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @GetMapping
    public List<Interview> getAll() {
        return interviewService.getAll();
    }

    @GetMapping("/scheduler")
    public List<String> getScheduler() {
        return interviewService.getScheduler();
    }

    @GetMapping("/totalbyscheduler")
    public List<Object> getAmount() {
        System.out.println(interviewService.getInterviewAmount());
        return interviewService.getInterviewAmount();
    }

    @GetMapping("/passrate")
    public HashMap<String, Double> getPassRate() {
        return interviewService.getPassRate();
    }

    @PostMapping
    public Response save(@RequestBody Interview interview, Authentication authentication) {
        return interviewService.save(interview, authentication);
    }

    @PutMapping
    public Response edit(@RequestBody Interview interview) {
        return interviewService.edit(interview);
    }

    @PutMapping("/{id}/{status}")
    public Response updateStatus(@PathVariable int id, @PathVariable String status) {
        System.out.println(status);
        return interviewService.updateStatus(id, status);
    }

    @PostMapping("/import")
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException, ParseException, NullPointerException {

        System.out.println("file" + reapExcelDataFile);
        try {
        List<Interview> tempInterviewList = new ArrayList<Interview>();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int i=1;i<worksheet.getPhysicalNumberOfRows();i++) {
            Interview tempInterview = new Interview();

            XSSFRow row = worksheet.getRow(i);

            String time = row.getCell(1).getStringCellValue();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.mm.dd - HH:mm", Locale.US);
            Date d1 = sdf.parse(time);
            tempInterview.setAppointment(d1);

            tempInterview.setCandidate(row.getCell(2).getStringCellValue());
            tempInterview.setScheduler(row.getCell(3).getStringCellValue().toLowerCase());

            String str = NumberToTextConverter.toText(row.getCell(4).getNumericCellValue());
            tempInterview.setPhone(str);

            tempInterview.setStatus(row.getCell(7).getStringCellValue().toLowerCase());

            if(row.getCell(5)!=null) {
                tempInterview.setEmail(row.getCell(5).getStringCellValue());
            } else {
                tempInterview.setEmail(null);
            }

            if(row.getCell(6)!=null) {
                tempInterview.setComments(row.getCell(6).getStringCellValue());
            } else {
                tempInterview.setComments(null);
            }

            tempInterviewList.add(tempInterview);

            interviewService.saveFromExcel(tempInterview);
        }

    } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
