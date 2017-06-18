package ee.netgroup.su.diagnostic.web;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class Analyzer {

    public String analyze(final HttpServletRequest request) throws IOException {

        final String input = request.getParameter("input");

        if (input == null)
            return "";

        final StringBuilder result = new StringBuilder();

        final StringReader reader = new StringReader(input);
        final BufferedReader bufferedReader = new BufferedReader(reader);

        int linesCount = 0;

        while (true) {
            final String line = bufferedReader.readLine();
            if (line == null)
                break;

            result.append("<script>console.log('" + line + "')</script>");
            linesCount++;

            // TODO: line parsing logic ...
        }
        System.out.println(input);

        result.append("<br/>Input parsing result:");
        result.append("<br/>Detected " + linesCount + " line(s).");

        return result.toString();
    }

}
