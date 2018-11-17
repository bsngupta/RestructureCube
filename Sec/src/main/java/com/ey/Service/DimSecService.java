package com.ey.Service;

import com.ey.entities.DimSec;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DimSecService {

    private static List<DimSec> dimSecList = new ArrayList<DimSec>();

    public List<DimSec> DimSecSvc() throws IOException {

        dimSecList.clear();
//        String fName = "f:\\schedules\\overnight\\security\\ExportSecurity\\security.csv";
        String fName = "C:\\security\\ExportSecurity\\security.csv";
        BufferedReader br = new BufferedReader(new FileReader(fName));

        String line = "";
        int i=0;
        while ((line = br.readLine()) != null) {
            // Parse line to extract individual fields
            String[] data = line.split(",");

            DimSec dimSec = new DimSec();
            dimSec.setId(i);
            dimSec.setGroupname(data[0]);
            dimSec.setArtifactname(data[1]);
            dimSec.setArtifacttype(data[2]);
            dimSec.setAccesstype(data[3]);
            dimSec.setAccesslever(data[4]);
            dimSec.setIsuser(data[5]);
            // Add object to list
            dimSecList.add(dimSec);
            i++;
        }
        br.close();

        return dimSecList;
    }
}
