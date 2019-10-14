package com.foodSafe.Blockchain.control;

public class Test {
    public static void main(String[] args)
    {
        WorkOutData Vici = new WorkOutData("2","5");
        int[] Degree = Vici.DegreeList();
        int[][] core = Vici.Coreness();
        int[] data = Vici.CornessDis(core);
        Vici.WriteCornessData(data);
        //Vici.WriteDegreeData(Degree);
        //Vici.WriteJson();
    }
}
