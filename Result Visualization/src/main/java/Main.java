import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import org.jfree.chart.ChartFactory;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.*;
import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfWriter;

public class Main {

    public static void main(String[] args) {
        /*double[][] A = {{1, 2, 5}, {3, 4, 0}};

        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("xy", A);    // A wird unter dem Namen xy abgespeichert
        // series2 enthaelt Punkte, die verbunden werden
        XYSeries series1 = new XYSeries("Cassandra");
        series1.add(0, 0);
        series1.add(1, 1);
        series1.add(2, 1);
        series1.add(3, 2);

        XYSeries series2 = new XYSeries("HBase");
        series2.add(1, 2);
        series2.add(2, 3);
        series2.add(3, 4);

        // Hinzufuegen von series1 und series2 zu der Datenmenge dataset
        XYSeriesCollection dataset2 = new XYSeriesCollection();
        dataset2.addSeries(series1);
        dataset2.addSeries(series2);

        XYDotRenderer dot = new XYDotRenderer();
        dot.setDotHeight(5);
        dot.setDotWidth(5);

        NumberAxis xax = new NumberAxis("Servers");
        NumberAxis yax = new NumberAxis("Read latency (ms)");

        XYPlot plot = new XYPlot(dataset2, xax, yax, dot);
        //JFreeChart chart2 = new JFreeChart(plot);
        JFreeChart chart2 = ChartFactory.createXYLineChart("Read Performance","Servers","Read latency (ms)", dataset,PlotOrientation.VERTICAL,true, true, false);

        // Erstellen eines Ausgabefensters
        ApplicationFrame punkteframe = new ApplicationFrame("Punkte"); //"Punkte" entspricht der Ueberschrift des Fensters

        ChartPanel chartPanel2 = new ChartPanel(chart2);
        punkteframe.setContentPane(chartPanel2);
        punkteframe.pack();
        punkteframe.setVisible(true);*/

        final XYSeriesDemo demo = new XYSeriesDemo("Read Performance Testing");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    /**
     * A simple demo showing a dataset created using the {@link XYSeriesCollection} class.
     *
     */
    public static class XYSeriesDemo extends ApplicationFrame {

        /**
         * A demonstration application showing an XY series containing a null value.
         *
         * @param title the frame title.
         */
        public XYSeriesDemo(final String title) {

            super(title);
            XYSeries series = new XYSeries("HBase");
            series.add(1.0, 69.0);
            series.add(3.0, 35.0);
            series.add(6.0, 17.0);
            series.add(8.0, 27.0);
            series.add(10.0, 19.0);
            series.add(12.0, 22.0);

            XYSeries series2 = new XYSeries("Cassandra");
            series2.add(3.0,8.0);
            series2.add(6.0,10.0);
            series2.add(8.0,9.5);
            series2.add(10.0,10.5);
            series2.add(12.0,11.5);

            XYSeries series3 = new XYSeries("PNUTS");
            series3.add(1.0, 8.0);
            series3.add(3.0, 9.0);
            series3.add(6.0, 8.5);
            series3.add(8.0, 6.5);
            series3.add(10.0, 8.5);
            series3.add(12.0, 6.5);

            /*series.add(21.9, null);
            series.add(25.6, 734.4);
            series.add(30.0, 453.2);*/
            XYSeriesCollection data = new XYSeriesCollection();
            data.addSeries(series);
            data.addSeries(series2);
            data.addSeries(series3);
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Read Performance Testing",
                    "Servers",
                    "Read latency (ms)",
                    data,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            final ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(14, 80));
            setContentPane(chartPanel);

            // change the color of the line
            XYPlot plot = (XYPlot) chart.getXYPlot();
            //plot.setDataset(0, date );
            //plot.setDataset(1, xyDataset2);
            XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer();
            //XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
            plot.setRenderer(0, renderer0);
            //plot.setRenderer(1, renderer1);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.BLACK);
            //plot.getRendererForDataset(plot.getDataset(1)).setSeriesPaint(1, Color.RED);

            convertToPdf(chart,640,480,"Test3.pdf");
        }
    }

    public static void convertToPdf (JFreeChart chart, int width, int height, String filename) {

        Document document = new Document(new com.itextpdf.text.Rectangle(width, height));
        try {
            PdfWriter writer;
            writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(width, height);
            Graphics2D g2d = tp.createGraphics(width, height, new DefaultFontMapper());
            Rectangle2D r2d = new Rectangle2D.Double(0, 0, width, height);
            chart.draw(g2d, r2d);
            g2d.dispose();
            cb.addTemplate(tp, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }
}
