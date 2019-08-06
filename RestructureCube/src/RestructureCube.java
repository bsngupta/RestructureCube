import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class RestructureCube {

    private String userName;        // PBCS user name
    private String password;        // PBCS user password
    private String serverUrl;       // PBCS server URL
    private String apiVersion; 	    // Version of the PBCS API that you are developing/compiling with.
    private String applicationName; // PBCS application used in this sample
    private String dbName;

    public static void main(String[] args) {
        try {
            RestructureCube rc = new RestructureCube(args[0],args[1],args[2],args[3],args[4],args[5]);
            rc.restructureCube();
        } catch (Throwable x) {
            System.err.println("Error: " + x.getMessage());
        }
    }

    public RestructureCube(String userName, String password, String serverUrl, String apiVersion, String applicationName, String dbName) throws Exception {
        this.userName = userName;
        this.password = password;
        this.serverUrl = serverUrl;
        this.apiVersion = apiVersion;
        this.applicationName = applicationName;
        this.dbName = dbName;
    }

    //
    // BEGIN - Integration scenarios.
    //
    public void restructureCube() throws Exception {
        String params = "{cubeName:" + dbName + "}";
        executeJob("Restructure Cube", "RestructureCube", params);
//        executeJob("Restructure Cube", "RestructureCube", "{cubeName:COSTRATE}");
    }


    //
    // Common Helper Methods
    //
    private String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    private String executeRequest(String urlString, String requestMethod, String payload, String contentType) throws Exception {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setInstanceFollowRedirects(false);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setRequestProperty("Authorization", "Basic " + new sun.misc.BASE64Encoder().encode((userName + ":" + password).getBytes()));
            connection.setRequestProperty("Content-Type", contentType);
            if (payload != null) {
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(payload);
                writer.flush();
            }
            int status = connection.getResponseCode();
            if (status == 200 || status == 201) {
                return getStringFromInputStream(connection.getInputStream());
            }
            throw new Exception("Http status code: " + status);
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    private void getJobStatus(String pingUrlString, String methodType) throws Exception {
        boolean completed = false;
        while (!completed) {
            String pingResponse = executeRequest(pingUrlString, methodType, null, "application/x-www-form-urlencoded");
            JSONObject json = new JSONObject(pingResponse);
            int status = json.getInt("status");
            if (status == -1) {
                try {
                    System.out.println("Please wait...");
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    completed = true;
                    throw e;
                }
            }
            else {
                if (status > 0) {
//                    System.out.println("Error occurred: " + json.getString("details"));
                    throw new Exception("Error occurred: " + json.getString("details"));
                }
                else {
                    System.out.println("Completed");
                }
                completed = true;
            }
        }
    }


    public String fetchPingUrlFromResponse(String response, String retValue) throws Exception {
        String pingUrlString = null;
        JSONObject jsonObj = new JSONObject(response);
        int resStatus = jsonObj.getInt("status");
        if (resStatus == -1) {
            JSONArray lArray = jsonObj.getJSONArray("links");
            for (int i = 0; i < lArray.length(); i++) {
                JSONObject arr = lArray.getJSONObject(i);
                if (arr.get("rel").equals(retValue))
                    pingUrlString = (String) arr.get("href");
            }
        }
        return pingUrlString;
    }
    //
    // END - Common Helper Methods
    //



    public void executeJob(String jobType, String jobName, String parameters) throws Exception {
        String urlString = String.format("%s/HyperionPlanning/rest/%s/applications/%s/jobs", serverUrl, apiVersion, applicationName);
        JSONObject payload = new JSONObject();
        payload.put("jobName",jobName);
        payload.put("jobType",jobType);
        payload.put("parameters",new JSONObject(parameters));
        String response = executeRequest(urlString, "POST", payload.toString(), "application/json");
        System.out.println("Job started successfully");
        getJobStatus(fetchPingUrlFromResponse(response, "self"), "GET");
    }

}

