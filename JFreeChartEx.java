package testECGplot;

 
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

//http://www.jfree.org/forum/viewtopic.php?t=117574
public class JFreeChartEx extends ApplicationFrame {
 
  private static final long serialVersionUID = 1L;
 
  public JFreeChartEx(String title) {
    super(title);
    JPanel chartPanel = createDemoPanel();
    chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
    setContentPane(chartPanel);

  }

  private static JFreeChart createChart() {
	   

	 
	 ArrayList<String> signal = new ArrayList<>();
	  Scanner read = null;
	try {
		read = new Scanner (new File("C:\\Users\\Jefferson\\Desktop\\Desktopv2\\ecg_zinaldo.txt"));
	} catch (FileNotFoundException e) {
		 
		e.printStackTrace();
	}
	  read.useDelimiter("	");
	   while (read.hasNext())
	  {
		  signal.add(read.next());  
	  }
	  read.close();
	 
	 System.out.println(signal.size());
	 XYSeriesCollection dataset1 = new XYSeriesCollection();
    
    XYSeries series1 = new XYSeries("measures");
    float fs = 250;
    
    dataset1.addSeries(series1);
    ArrayList<Float> time = new ArrayList<>();
    for (float i = 0; i<signal.size(); i++) {
    	time.add((float) (i/fs));
    }
   
    for (int i = 0; i < signal.size(); i++) {
      series1.add(time.get(i), new Integer(signal.get(i)));
       
    }

    JFreeChart chart =
        ChartFactory.createXYLineChart("Eletrocardiograma", "Tempo (s)", "Medida", dataset1);

    chart.setBackgroundPaint(Color.white);
    XYPlot plot = (XYPlot) chart.getPlot();

 
    return chart;

  }
 
 
  public static JPanel createDemoPanel() {
    JFreeChart chart = createChart();
    return new ChartPanel(chart);
  }
 
  public static void main(String[] args) {

    JFreeChartEx demo = new JFreeChartEx("");
    demo.pack();
    RefineryUtilities.centerFrameOnScreen(demo);
    demo.setVisible(true);
    
  }

}
  
