package com.moxing.ssm.utils;

import com.moxing.ssm.model.Label;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matingfeng on 2017/5/23.
 */
public class CalUtils {
    public static String getCal(Label label1,Label label2){
        Double dist = 0.00;
        int sum1=0;
        int sum2=0;
        int sum3=0;
        List<Integer> labelList1 = new ArrayList<Integer>();
        List<Integer> labelList2 = new ArrayList<Integer>();
        labelList1.add(label1.getAchievementStriving());
        labelList1.add(label1.getActivity());

        labelList1.add(label1.getAltruism());
        labelList1.add(label1.getAssertiveness());
        labelList1.add(label1.getCompetence());

        labelList1.add(label1.getCompliance());
        labelList1.add(label1.getDeliberation());
        labelList1.add(label1.getDutifulness());
        labelList1.add(label1.getExcitementSeeking());
        
        labelList1.add(label1.getGregariousness());
        labelList1.add(label1.getModesty());
        labelList1.add(label1.getOrder0());
        labelList1.add(label1.getPositiveEmotions());
        labelList1.add(label1.getSelfdiscipline());
        labelList1.add(label1.getStraightforwardness());
        labelList1.add(label1.getTendermindedness());
        labelList1.add(label1.getTrust());
        labelList1.add(label1.getWarmth());


        labelList2.add(label2.getAchievementStriving());
        labelList2.add(label2.getActivity());

        labelList2.add(label2.getAltruism());
        labelList2.add(label2.getAssertiveness());
        labelList2.add(label2.getCompetence());

        labelList2.add(label2.getCompliance());
        labelList2.add(label2.getDeliberation());
        labelList2.add(label2.getDutifulness());
        labelList2.add(label2.getExcitementSeeking());

        labelList2.add(label2.getGregariousness());
        labelList2.add(label2.getModesty());
        labelList2.add(label2.getOrder0());
        labelList2.add(label2.getPositiveEmotions());
        labelList2.add(label2.getSelfdiscipline());
        labelList2.add(label2.getStraightforwardness());
        labelList2.add(label2.getTendermindedness());
        labelList2.add(label2.getTrust());
        labelList2.add(label2.getWarmth());
        System.out.println("label:"+labelList1.size());
        for(int k=0;k<17;k++){
            System.out.println("label:"+labelList1.get(k)+"label2:"+labelList2.get(k));
            sum1+=labelList1.get(k)*labelList1.get(k);
            sum2+=labelList2.get(k)*labelList2.get(k);
            sum3+=labelList1.get(k)*labelList2.get(k);

        }
        if (sum1!=0&&sum2!=0&&sum3!=0){
            dist=(double)sum3/Math.sqrt((double)(sum1*sum2))*1.0;
            //假设维度为2，则余弦定理应是  x1y1+x2y2/(sqrt(x1^2+x2^2)*sqrt(y1^2*y2^2))
            //维度为18同理
        }
        else
            dist=0.00;
        return Integer.toString((int)(dist*100));
    }
}
