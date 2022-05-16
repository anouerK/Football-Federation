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
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Stade;
import com.esprit.gui.HomeForm;
import com.esprit.services.ServiceStade;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lord
 */
public class StatistiquePieForm extends Form {
    
     ArrayList<Stade> mat;


  
    
    public float calcul_nbr_matiere(ArrayList<Stade> r,String ch){
        
         ArrayList<Stade> mat = new ArrayList<Stade>();
         mat =     ServiceStade.getInstance().getAll();

        
    int f=0;
    for(int i=0;i<mat.size();i++){
        if (mat.get(i).getEtat().equals(ch)){ f++;}
    }
    return f;
}
    
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(70);
    renderer.setLegendTextSize(70);
    renderer.setMargins(new int[]{12, 14, 11, 10, 19,0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
    
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
    
    
  protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
        series.add("complet", this.calcul_nbr_matiere(mat, "complet") );
        series.add("en travaux", this.calcul_nbr_matiere(mat, "en travaux") );
         series.add("Olympique", this.calcul_nbr_matiere(mat, "Olympique") );
          
        

    return series;
    
}

public Form createPieChartForm() {
     Resources res=UIManager.initFirstTheme("/theme");

 //this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListTaskStadeF(new ProfileForm(res)).show());

     new Label("Statistiques Stades");
    // Generate the values
    double[] values = new double[]{
        this.calcul_nbr_matiere(mat, "complet"),
        this.calcul_nbr_matiere(mat, "en travaux"),
        this.calcul_nbr_matiere(mat, "Olympique"),
      
        };


    // Set up the renderer
    int[] colors = new int[]{ColorUtil.YELLOW, ColorUtil.GREEN, ColorUtil.CYAN,ColorUtil.BLUE};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
         //   DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setLabelsColor(0x000000);
        
        renderer.setZoomButtonsVisible(true);
        renderer.setLabelsTextSize(50);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setShowLabels(true);
     //   SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setHighlighted(true);






    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Calandrier_ex", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Statistique Stades", new BorderLayout());
    f.addComponent(BorderLayout.CENTER, c);
            //hi.addComponent(BorderLayout.CENTER, clock);

        f.show();

      f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListTaskStadeF(new ProfileForm(res)).show());
    
   
return f;
    


}
    
}
