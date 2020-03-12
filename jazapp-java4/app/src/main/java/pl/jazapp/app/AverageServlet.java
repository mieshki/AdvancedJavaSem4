package pl.jazapp.app;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;


class Formatter {
    public static String formatNumber(float number){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(number);
    }
}

@WebServlet("average")
public class AverageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        resp.setContentType("text/plain");

        String numbers = req.getParameter("numbers");
        String[] splittedNumbers = numbers.split(",");


        int numbersCount = splittedNumbers.length;

        float numbersSummary = 0;
        for(String item : splittedNumbers){
            numbersSummary += Integer.parseInt(item);
        }

        var respWriter = resp.getWriter();

        var average = numbersSummary / numbersCount;

        if(numbersCount > 0)
            resp.getWriter().println("Average equals: " + Formatter.formatNumber(average));
        else
            resp.getWriter().println("Please put parameters.");
    }
}
