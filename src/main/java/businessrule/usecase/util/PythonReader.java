package businessrule.usecase.util;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PythonReader {

    public String readPythonExec(String command) {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec(command);
            proc.waitFor();
            InputStream inputStream = proc.getInputStream();
            InputStream errorStream = proc.getErrorStream();
            String errorOutput = IOUtils.toString(errorStream, StandardCharsets.UTF_8);
            if (!errorOutput.isEmpty()) {
                System.err.println("Error from Python: " + errorOutput);
            }
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } return null;
    }
}
