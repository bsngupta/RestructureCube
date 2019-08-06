//CommitSvc

package com.ey;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommitSvc {
    private List<CommitFile> commitRowsList = new ArrayList<CommitFile>();

    public List<CommitFile> CommitSvc(String fName1, String fldrpath) throws IOException {
        String fName = fldrpath + fName1;
        commitRowsList.clear();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fName), StandardCharsets.UTF_8));
        String line = "";
        int i=1;

        while ((line = br.readLine()) != null) {
//            String[] data = (line + ",").split(",", -1);
            String[] data = (line + ",").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            CommitFile cf = new CommitFile();

            cf.setYear(data[0]);
            cf.setScenario(data[1]);
            cf.setVersion(data[2]);
            cf.setManagement(data[3]);
            cf.setServiceline(data[4]);
            cf.setRank(data[5]);
            cf.setCurrency(data[6]);
            cf.setAccount(data[7]);
            try {
                if (data[8].isEmpty() || data[8].equalsIgnoreCase("0.000000")) {
                    cf.setBb("#MISSING");
                } else {
                    cf.setBb(data[8]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setBb("#MISSING");
            }

            try {
                if (data[9].isEmpty() || data[9].equalsIgnoreCase("0.000000")) {
                    cf.setJul("#MISSING");
                } else {
                    cf.setJul(data[9]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setJul("#MISSING");
            }

            try {
                if (data[10].isEmpty() || data[10].equalsIgnoreCase("0.000000")) {
                    cf.setAug("#MISSING");
                } else {
                    cf.setAug(data[10]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setAug("#MISSING");
            }
            try {
                if (data[11].isEmpty() || data[11].equalsIgnoreCase("0.000000")) {
                    cf.setSep("#MISSING");
                } else {
                    cf.setSep(data[11]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setSep("#MISSING");
            }
            try {
                if (data[12].isEmpty() || data[12].equalsIgnoreCase("0.000000")) {
                    cf.setOct("#MISSING");
                } else {
                    cf.setOct(data[12]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setOct("#MISSING");
            }
            try {
                if (data[13].isEmpty() || data[13].equalsIgnoreCase("0.000000")) {
                    cf.setNov("#MISSING");
                } else {
                    cf.setNov(data[13]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setNov("#MISSING");
            }

            try {
                if (data[14].isEmpty() || data[14].equalsIgnoreCase("0.000000")) {
                    cf.setDec("#MISSING");
                } else {
                    cf.setDec(data[14]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setDec("#MISSING");
            }

            try {
                if (data[15].isEmpty() || data[15].equalsIgnoreCase("0.000000")) {
                    cf.setJan("#MISSING");
                } else {
                    cf.setJan(data[15]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setJan("#MISSING");
            }

            try {
                if (data[16].isEmpty() || data[16].equalsIgnoreCase("0.000000")) {
                    cf.setFeb("#MISSING");
                } else {
                    cf.setFeb(data[16]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setFeb("#MISSING");
            }

            try {
                if (data[17].isEmpty() || data[17].equalsIgnoreCase("0.000000")) {
                    cf.setMar("#MISSING");
                } else {
                    cf.setMar(data[17]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setMar("#MISSING");
            }

            try {
                if (data[18].isEmpty() || data[18].equalsIgnoreCase("0.000000")) {
                    cf.setApr("#MISSING");
                } else {
                    cf.setApr(data[18]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setApr("#MISSING");
            }

            try {
                if (data[19].isEmpty() || data[19].equalsIgnoreCase("0.000000")) {
                    cf.setMay("#MISSING");
                } else {
                    cf.setMay(data[19]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setMay("#MISSING");
            }

            try {
                if (data[20].isEmpty() || data[20].equalsIgnoreCase("0.000000")) {
                    cf.setJun("#MISSING");
                } else {
                    cf.setJun(data[20]);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                cf.setJun("#MISSING");
            }


            commitRowsList.add(cf);
            System.out.println("parsed row: " + i); i++;
        }
        br.close();
        return commitRowsList;
    }

    public void fmtFile(String fName1, String fldrpath) throws IOException {
        String amtLoadFile = fldrpath + fName1.substring(0,fName1.length() -  4) + "_parse.txt";
        Writer fstream = new OutputStreamWriter(new FileOutputStream(amtLoadFile), StandardCharsets.UTF_8);

        fstream.write("\"Account\",\"BegBalance\",\"Jul\",\"Aug\",\"Sep\",\"Oct\",\"Nov\",\"Dec\",\"Jan\",\"Feb\",\"Mar\",\"Apr\",\"May\",\"Jun\",\"Point-Of-View\",\"Data Load Cube Name\"" + "\n");
        for (CommitFile str : commitRowsList.subList(1, commitRowsList.size())) {
            fstream.write(str.getAccount() + ",\"" + str.getBb() + "\",\"" + str.getJul() + "\",\"" + str.getAug() + "\",\"" + str.getSep() + "\",\"" + str.getOct() + "\",\"" +
                    str.getNov() + "\",\"" + str.getDec() + "\",\"" + str.getJan() + "\",\"" + str.getFeb() + "\",\"" + str.getMar() + "\",\"" + str.getApr() + "\",\"" +
                    str.getMay() + "\",\"" + str.getJun() + "\",\"\"" + str.getYear() + "\",\"" + str.getScenario() + "\",\"" + str.getVersion() + "\",\"" +
                    str.getManagement() + "\",\"" + str.getServiceline() + "\",\"" + str.getRank() + "\",\"" + str.getCurrency() + "\"\"," + "\"" + "PLAN" + "\"" + "\n");
        }
        fstream.close();
    }
}
