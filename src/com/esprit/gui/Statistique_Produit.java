/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;


import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.services.ServiceCategories;
import com.esprit.services.ServiceCommandes;
import com.esprit.services.ServiceMarques;
import com.esprit.entities.categories;
import com.esprit.entities.commande;
import com.esprit.entities.marques;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Lord
 */
public class Statistique_Produit extends Form {
    
     private boolean drawOnMutableImage;
   private double nbr1 =0 ;
   private double   nbr2=0;
    
    Form current;
Form form;

  ArrayList<categories> lis2t=ServiceCategories.getInstance().parsecat();
   Map<String, Double> stat = new HashMap<String,Double>();
   
          ArrayList<marques> list=ServiceMarques.getInstance().parseTasks();


  public Statistique_Produit(Form previous)  {
        super("Newsfeed", BoxLayout.y());
            current= this;

   
   
             createPiehartForm();
            
 
        // special case for rotation
              
      
              getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});

        }
    
    
    
    
    public DefaultRenderer buildCatRenderer(int []colors)
    {
      DefaultRenderer renderer = new DefaultRenderer();
      renderer.setLabelsTextSize(15);
      renderer.setLegendTextSize(60);
      renderer.setMargins(new int[] {20,30,15,0});
      
      for(int color : colors){
      SimpleSeriesRenderer simple = new   SimpleSeriesRenderer();
      simple.setColor(color);
      renderer.addSeriesRenderer(simple);
      }
      return renderer;
    }
    
    public void createPiehartForm()
    {
        int x=0;
        int i=0;
        int a=0;
               ArrayList<commande> listc=ServiceCommandes.getInstance().parseTaskscom();
               
               for(marques m : list)
               {
                   stat.put(m.getNomM(),0.0);
               }
               
             /* for(commande com : listc)
              {
                  System.out.println(com.getProduit().getMar().getNomM());
                  if(com.getProduit().getMar().getNomM().equals("puma"))
                  {
                      nbr1++;
                      System.out.println(nbr1);
                  }
                  
                   if(com.getProduit().getMar().getNomM().equals("adidas")){
                        nbr2++;
                        System.out.println(nbr2);
                   }
              }*/
               double total=0;
             
             for(commande com : listc)
              {
                
                      
  stat.put(com.getProduit().getMar().getNomM(),stat.get(com.getProduit().getMar().getNomM())+1);
            
      
                  
                  
                  
              }
             
             System.out.println(stat);
             
             CategorySeries series = new CategorySeries("Title");
             
             int mm=0;
             ArrayList<Integer> ll= new ArrayList<Integer>();
             
             
             Iterator it = stat.entrySet().iterator();
             
             
                int y=0;
                int tt=0;
             
             
             while (it.hasNext()) {
	        Map.Entry<String, Double> entry = (Map.Entry)it.next();
	        System.out.println(entry.getKey() + " = " + entry.getValue()); 
                 total = total+ entry.getValue();
                 
              
                   
                  
                   
                   tt++;
	    }
             
             int[] colors=new int[tt] ;
             int bb=0;
        
                 it = stat.entrySet().iterator(); 
                  
             while (it.hasNext()) {
	        Map.Entry<String, Double> entry = (Map.Entry)it.next();
                
                    Random r= new  Random();
               y = r.nextInt(99999);
                
                 colors[bb]=y;
	       bb++;
                  series.add(entry.getKey(),(entry.getValue()/total)*100);
	    }
        
            
              
              
                    
             

         
        
      //  double prcnt1 = (nbr1 * 100)/total;
        
       //  double prcnt2 = (nbr2 * 100)/total;
         
         
         
         
        DefaultRenderer renderer = buildCatRenderer(colors);
        renderer.setLabelsColor(0x000000);
        
        renderer.setZoomButtonsVisible(true);
        renderer.setLabelsTextSize(50);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setHighlighted(true);
        
        PieChart chart = new PieChart(series,renderer); //new PieChart(buildDateset("title",Math.round(prcnt1),Math.round(prcnt2)),renderer);
        
        
        ChartComponent c = new    ChartComponent(chart);
        
        String[]messages = {
            "Les Marques vendues: "
        };
        
        SpanLabel message = new SpanLabel(messages[0],"Welcome");
        
        Container cnt = BorderLayout.center(message);
        
        cnt.setUIID("Container");
        add(cnt);
        add(c);
        
    }
    
    
    private CategorySeries buildDateset(String Title,double prcnt1, double prcnt2  ){
        CategorySeries series = new CategorySeries(Title);
        series.add("Puma",prcnt1);
        series.add("Adidas",prcnt2);
          
          return series;
       
        
    }
    
    
}