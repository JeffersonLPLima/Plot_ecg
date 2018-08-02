package ecgplot;


import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

//http://www.jfree.org/forum/viewtopic.php?t=117574
public class JFreeChartEx{

	private static JFreeChart createChart(ArrayList<String> signal, String user, String classification, String confidence) {
		/*
		signal = new ArrayList<>();
		user = "Fulano";
		classification = "Normal";
		confidence = "93%";
		*/
		if(classification.equals("Normal")) {
			classification="Rítmo Normal";
		}else if(classification.equals("AF")) {
			classification="Fibrilação Atrial";			
		}else if(classification.equals("Other")) {
			classification="Outro Rítmo";		
		}else{
			classification="Ruído";
		}	
		/*
		Scanner read = null;
		try {
			read = new Scanner (new File("ecg.txt"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		read.useDelimiter("	");
		while (read.hasNext())
		{
			signal.add(read.next());  
		}
		read.close();
		*/
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

		JFreeChart chart = ChartFactory.createXYLineChart("Eletrocardiograma (250hz) de \""+ user +"\", "+"  Classficiação:\""+classification +"\"  Confiança:\"" +confidence+"\"", "Tempo (s)", "Medida", dataset1);
		chart.setBackgroundPaint(Color.white);
		
		SVGGraphics2D g2 = new SVGGraphics2D(10000,1000); 
		Rectangle r = new Rectangle(0, 0, 10000, 1000); 
		
		chart.getTitle().setPosition(RectangleEdge.TOP);
		chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
                chart.getTitle().setFont(new Font("Dialog", Font.BOLD, 50));
		chart.draw(g2, r); 
		File f = new File("ECG_"+user+".svg"); 
		try {
			SVGUtils.writeToSVG(f, g2.getSVGElement());
		} catch (IOException e) {
			System.out.println("Não foi possível salvar o arquivo svg contendo o plot.");
			e.printStackTrace();
		} 
		return chart;

	}


/*
	public static void main(String[] args) {

		createChart(null, null, null, null);
		 
	}
*/
}
