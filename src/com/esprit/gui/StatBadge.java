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
import com.esprit.entities.Badge;
import com.esprit.services.ServiceBadge;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author ksaay
 */
public class StatBadge extends Form{
    
    private boolean drawOnMutableImage;
    int badges_n;
    
    Form current;
Form form;

  public StatBadge(Form previous)  {
        super("Badge Stats", BoxLayout.y());
            current= this;

             createPiehartForm();
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
        //Container = new Container();
        Container element = new Container();
        double total = calcTotal();
        
      
        // System.out.println("BADGES N = "+badges_n);
       //  int[] colors = new int[]{0xf4b342,0x52b29a,0xf4b342,0x52b29a,0xf4b342,0x52b29a,0xf4b342,0x52b29a};
         int[] colors = new int[badges_n];
         for(int i = 0 ; i < badges_n ; i++)
         {
             colors[i]=pickColor(colors,i);
         }
        // System.out.println("COLORS = "+colors);
        DefaultRenderer renderer = buildCatRenderer(colors);
        renderer.setLabelsColor(0x000000);
        
        renderer.setZoomButtonsVisible(true);
        renderer.setLabelsTextSize(50);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(15);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setHighlighted(true);
        
        PieChart chart = new PieChart(buildDateset("title",total),renderer);
        
        
        ChartComponent c = new    ChartComponent(chart);
        
        
        String[]messages = {
            "Stat badge / users "
        };
        
        SpanLabel message = new SpanLabel(messages[0],"Welcome");
        
        Container cnt = BorderLayout.center(message);
        
        cnt.setUIID("Container");
        
        element.add(cnt);
        element.add(c);
         element.setScrollableY(true);
        element.setScrollableX(true);
        add(element);
        
    }
    
    public int pickColor(int[]colors ,int n )
    {
          Random obj = new Random();
int rand_num = obj.nextInt(0xffffff + 1);
/*
for(int i  = 0 ; i<n ; i++)
{
    if(rand_num == colors[i])
    {
        pickColor(colors,n);
    }
    
}*/
        return rand_num;
        
    }
    
    private CategorySeries buildDateset(String Title , double total  ){
        CategorySeries series = new CategorySeries(Title);
          ArrayList<Badge> listb;
        listb = new ArrayList<>();
    listb = ServiceBadge.getInstance().getAllStat(0);
    int sum = 0 ;
    
    for ( Badge ev : listb) {
        if(ev.getNb() != 0)
        {
            double percentage = (ev.getNb()*100)/total;
              series.add(ev.getNomB()+" = "+Double.toString(percentage)+" %",percentage);
              
        }
 
    }
    
       
          
          return series;
       
        
    }
    private int calcTotal ()
    {
            ArrayList<Badge> listb;
    listb = new ArrayList<>();
    listb = ServiceBadge.getInstance().getAllStat(0);
    int sum = 0 ;
    int nb = 0 ; 
    for ( Badge ev : listb) {
        if(ev.getNb()!=0)
        {
             nb ++;
    sum = sum + ev.getNb();
        }
       
    }
    
    badges_n = nb;
        return sum;
        
    }
}
