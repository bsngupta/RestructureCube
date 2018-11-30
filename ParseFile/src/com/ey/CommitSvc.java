//CommitSvc

package com.ey;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommitSvc {
    String fPath = "C:\\Users\\bestasu\\Desktop\\";
    //    String fPath = "D:\\gpb_tools\\apps\\amt-wls-uat\\AMT\\Commit_Temp\\";
    private List<CommitFile> commitRowsList = new ArrayList<CommitFile>();
    public List<CommitFile> CommitSvc(String fName1, String vName) throws IOException {
        //String fName1 = "";
        String fName = fPath+fName1;
        commitRowsList.clear();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fName), StandardCharsets.UTF_8));
        String line = "";

        while ((line = br.readLine()) != null) {
//            String[] data = line.split("\t");
            String[] data = (line+"\t").split("\t",-1);
            CommitFile cf = new CommitFile();

            cf.setManagement(data[0]);
            cf.setRank(data[1]);
            cf.setServiceline(data[2]);
            cf.setYear(data[3]);
            cf.setScenario(data[4]);
            cf.setVersion(vName);
            cf.setCurrency(data[6]);
            cf.setGeo(data[7]);
            cf.setAccount(data[8]);
            if (data[9].length()==0) {
                cf.setJul("#MISSING");
            } else {
                cf.setJul(data[9]);
            }
            if (data[10].length()==0) {
                cf.setAug("#MISSING");
            } else {
                cf.setAug(data[10]);
            }
            if (data[11].length()==0) {
                cf.setSep("#MISSING");
            } else {
                cf.setSep(data[11]);
            }
            if (data[12].length()==0) {
                cf.setOct("#MISSING");
            } else {
                cf.setOct(data[12]);
            }
            if (data[13].length()==0) {
                cf.setNov("#MISSING");
            } else {
                cf.setNov(data[13]);
            }
            if (data[14].length()==0) {
                cf.setDec("#MISSING");
            } else {
                cf.setDec(data[14]);
            }
            if (data[15].length()==0) {
                cf.setJan("#MISSING");
            } else {
                cf.setJan(data[15]);
            }
            if (data[16].length()==0) {
                cf.setFeb("#MISSING");
            } else {
                cf.setFeb(data[16]);
            }
            if (data[17].length()==0) {
                cf.setMar("#MISSING");
            } else {
                cf.setMar(data[17]);
            }
            if (data[18].length()==0) {
                cf.setApr("#MISSING");
            } else {
                cf.setApr(data[18]);
            }
            if (data[19].length()==0) {
                cf.setMay("#MISSING");
            } else {
                cf.setMay(data[19]);
            }
            if (data[20].length()==0) {
                cf.setJun("#MISSING");
            } else {
                cf.setJun(data[20]);
            }

            commitRowsList.add(cf);
        }
        br.close();
        return commitRowsList;
    }

    public void fmtFile(String fName1) throws IOException {
        String amtLoadFile=fPath+fName1;
//        FileWriter writer = new FileWriter(amtLoadFile);
//        Writer fstream = null;
//        BufferedWriter out = null;
        Writer fstream = new OutputStreamWriter(new FileOutputStream(amtLoadFile), StandardCharsets.UTF_8);


        fstream.write("\"Account\",\"Jul\",\"Aug\",\"Sep\",\"Oct\",\"Nov\",\"Dec\",\"Jan\",\"Feb\",\"Mar\",\"Apr\",\"May\",\"Jun\",\"Point-Of-View\",\"Data Load Cube Name\""+"\n");
        for(CommitFile str: commitRowsList.subList(1,commitRowsList.size())) {
            fstream.write(str.getAccount()+",\""+str.getJul()+"\",\""+str.getAug()+"\",\""+str.getSep()+"\",\""+str.getOct()+"\",\""+
                    str.getNov()+"\",\""+str.getDec()+"\",\""+str.getJan()+"\",\""+str.getFeb()+"\",\""+str.getMar()+"\",\""+str.getApr()+"\",\""+
                    str.getMay()+"\",\""+str.getJun()+"\",\"\""+str.getYear()+"\",\""+str.getScenario()+"\",\""+str.getVersion()+"\",\""+
                    str.getManagement()+"\",\""+str.getServiceline()+"\",\""+str.getRank()+"\",\""+str.getCurrency()+"\"\","+"\""+"PLAN"+"\""+"\n");
        }
        fstream.close();
//        writer.close();

    }
}
