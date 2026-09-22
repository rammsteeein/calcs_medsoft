package com.example.demo1.controls.APACHEII;
public final class APACHEIICalculator {
    private APACHEIICalculator() { }
    public static APACHEIIResult calc(String[] values, String surgery) {
        int total=0, selected=0;
        for(String v:values) if(v!=null){
            selected++; total+=Integer.parseInt(v.substring(0,v.indexOf('|')));
        }
        if(surgery!=null){
            selected++;
            total+=Integer.parseInt(surgery.substring(0,surgery.indexOf('|')));
        }
        if(selected==0)
            return new APACHEIIResult(0,"Выберите хотя бы один параметр");
        if(selected<values.length+1)
            return new APACHEIIResult(total,String.format("Промежуточный результат (%d из %d): %d балл(ов)",
                    selected,values.length+1,total));
        String mortality;
        boolean operated=surgery.contains("|Оперированный");
        if(total<=4)
            mortality=operated?"1%":"4%"; else if(total<=9)mortality=operated?"3%":"8%";
            else if(total<=14)mortality=operated?"7%":"15%";
            else if(total<=19)mortality=operated?"12%":"24%";
            else if(total<=24)mortality=operated?"30%":"40%";
            else if(total<=29)mortality=operated?"35%":"55%";
            else if(total<=34)mortality="73%";
            else mortality=operated?"88%":"85%";
        return new APACHEIIResult(total,"Ориентировочная летальность: "+mortality);
    }
}
