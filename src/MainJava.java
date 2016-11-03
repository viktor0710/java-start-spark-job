import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainJava {

    public static void main(String[] args) {
        String sparkSubmitPath = System.getenv("SPARK_HOME") + "/bin/spark-submit";
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", sparkSubmitPath, "--class", "com.ness.Main", "spark-job.jar");
        pb.redirectErrorStream(true); // Merges stdErr into stdOut

        try {

        Process process = pb.start();
        BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = null;
        while ( (line = processOutput.readLine()) != null) {
            if( line.contains("###")) {
                System.out.println("$$$$$$$$$" + line);
            }
        }

        process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
