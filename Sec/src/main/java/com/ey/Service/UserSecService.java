package com.ey.Service;


import com.ey.entities.UserSec;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecService {

    private static List<UserSec> userSecList = new ArrayList<UserSec>();

    public List<UserSec> UserSecSvc() throws IOException {

        userSecList.clear();
//        String fName = "F:\\Schedules\\Overnight\\Security\\ExportusersNgroups\\Groups.csv";
        String fName = "C:\\Users\\bestasu\\Desktop\\Groups.csv";
        BufferedReader br = new BufferedReader(new FileReader(fName));
        String line = "";
        br.readLine();
        while ((line = br.readLine()) != null) {
            // Parse line to extract individual fields
            String[] data = line.split(",");
            UserSec usersec = new UserSec();

            usersec.setId(data[0]);
            usersec.setGroup_id("");
            usersec.setGroup_provider("");
            usersec.setUser_id(data[3]);
            usersec.setUser_provider("LDAP_Directory_Provider");

            userSecList.add(usersec);
        }
        br.close();
        return userSecList;
    }
}
