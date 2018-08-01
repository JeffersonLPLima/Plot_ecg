package ecgplot;

 
import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

//http://www.jfree.org/forum/viewtopic.php?t=117574
public class JFreeChartEx{
 



  private static JFreeChart createChart() {
	 ArrayList<String> signal = new ArrayList<>();
	  Scanner read = null;
	try {
		read = new Scanner (new File("..."));
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

    JFreeChart chart = ChartFactory.createXYLineChart("Eletrocardiograma 250hz", "Tempo (s)", "Medida", dataset1);
    chart.setBackgroundPaint(Color.white);
 
 
    return chart;

  }
 
 
 
  public static void main(String[] args) {

	  JFreeChart chart = createChart();
      SVGGraphics2D g2 = new SVGGraphics2D(10000,1000); 
      Rectangle r = new Rectangle(0, 0, 10000, 1000); 
      
      chart.draw(g2, r); 
      File f = new File("ECG.svg"); 
      try {
		SVGUtils.writeToSVG(f, g2.getSVGElement());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
  }

}
  
