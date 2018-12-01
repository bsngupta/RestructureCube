package com.ey;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        CommitSvc CS = new CommitSvc();
        List<CommitFile> rowsList = CS.CommitSvc(args[0], args[1], args[2]);
        CS.fmtFile(args[0], args[2]);
    }
}

