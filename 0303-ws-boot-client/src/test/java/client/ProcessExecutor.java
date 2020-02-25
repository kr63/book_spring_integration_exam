package client;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

/**
 * Class responsible to executing jar files.
 *
 * @author Lubos Krnac
 */
public class ProcessExecutor {
    public static void main(String... args) {
        // this is library, just making spring boot repackage happy
    }

    /**
     * Runs executable jar is separate process and redirects console into "log"
     * file.
     *
     * @param jarName name of the jar to run
     * @return started {@link Process} instance
     * @throws IOException if I/O error occurs
     */
    public final Process execute(String jarName) throws IOException {
        return execute("dependency", jarName);
    }

    /**
     * Runs executable jar is separate process and redirects console into "log"
     * file.
     *
     * @param jarName   name of the jar to run
     * @param directory directory where is dependency located (build folder is root)
     * @return started {@link Process} instance
     * @throws IOException if I/O error occurs
     */
    public final Process execute(String directory, String jarName)
            throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jarName);
        // processBuilder.directory(new File("target" + File.separator + directory));
        // processBuilder.directory(new File("target" + File.separator + directory));
        File log = new File("process-executor.log");
        processBuilder.redirectErrorStream(true);
        processBuilder.redirectOutput(Redirect.appendTo(log));
        return processBuilder.start();
    }
}
