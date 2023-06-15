package com.untad.ferin.spkwpprodukjual;

import android.util.Log;

import com.untad.ferin.spkwpprodukjual.data.Alternatif;
import com.untad.ferin.spkwpprodukjual.data.AlternatifKriteria;
import com.untad.ferin.spkwpprodukjual.data.Alternative;
import com.untad.ferin.spkwpprodukjual.data.Kriteria;
import com.untad.ferin.spkwpprodukjual.data.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kalkulasi {

    List<Alternatif> listAlnternatif ;
    List<Alternative> alternativesValue;
    List<Kriteria> listKriteria;
    List<Result> result;
    double[] normBobot;
    ArrayList<ArrayList<AlternatifKriteria>> listAlternatifKriteria;
    double[] vektorS;
    public double[] vektorV;
    public Kalkulasi(List<Alternatif> listAlnternatif , List<Alternative> value){
        this.listAlnternatif = listAlnternatif;
        alternativesValue = value;

        listAlternatifKriteria = new ArrayList<>();
//        this.listKriteria = listKriteria;
//        this.listAlternatifKriteria = listAlternatifKriteria;
//
//
//        this.listAlnternatif.add(new Alternatif("Nokaya","A1"));
//        this.listAlnternatif.add(new Alternatif("Mato","A2"));
//        this.listAlnternatif.add(new Alternatif("Lenovo","A3"));
//        this.listAlnternatif.add(new Alternatif("BlackBurry","A4"));
//
        listKriteria = new ArrayList<>();
        listKriteria.add(new Kriteria("Modal", 2, false, "C1"));
        listKriteria.add(new Kriteria("Etalase", 4, false, "C2"));
        listKriteria.add(new Kriteria("Umur Jual", 1, false, "C3"));
        listKriteria.add(new Kriteria("Laba", 3, true, "C4"));
        listKriteria.add(new Kriteria("Tren", 5, true, "C5"));

        inputValue();

        printAlternatif();
        printKriteria();
        calcNormBobot();
        //OK
//
//
//        int [] [] listdata = new int [this.listAlnternatif.size()][this.listKriteria.size()];
//        listdata[0][0] = 3500;
//        listdata[0][1] = 70;
//        listdata[0][2] = 10;
//        listdata[0][3] = 80;
//        listdata[0][4] = 3000;
//        listdata[0][5] = 36;
//
//        listdata[1][0] = 4500;
//        listdata[1][1] = 90;
//        listdata[1][2] = 10;
//        listdata[1][3] = 60;
//        listdata[1][4] = 2500;
//        listdata[1][5] = 48;
//
//        listdata[2][0] = 3200;
//        listdata[2][1] = 80;
//        listdata[2][2] = 9;
//        listdata[2][3] = 90;
//        listdata[2][4] = 2000;
//        listdata[2][5] = 48;
//
//        listdata[3][0] = 3000;
//        listdata[3][1] = 70;
//        listdata[3][2] = 8;
//        listdata[3][3] = 50;
//        listdata[3][4] = 1500;
//        listdata[3][5] = 60;

//
//        for ( int i = 0; i < this.listAlnternatif.size();i++){
//            ArrayList<AlternatifKriteria> specAlternatifKriteria = new ArrayList<AlternatifKriteria>();
//
//            for(int j=0;j<this.listKriteria.size();j++){
//
//                specAlternatifKriteria.add(new AlternatifKriteria(this.listAlnternatif.get(i),this.listKriteria.get(j),listdata[i][j]));
//                System.out.println("Loop i="+i+" j="+j+" :"+listdata[i][j]);
//            }
//            this.listAlternatifKriteria.add(specAlternatifKriteria);
//
//            System.out.println("Simpan listalternatif kriteria i="+i);
//        }

        System.out.println(this.listAlternatifKriteria);
////
        this.vektorS = new double[this.listAlternatifKriteria.size()];
        for(int i = 0; i < this.listAlternatifKriteria.size(); i++){
            this.vektorS[i] = (double)(calcVektorS(this.listAlternatifKriteria.get(i)));
            System.out.println("Vektor S dari " + (i +1) + " adalah : " + this.vektorS[i]);
        }
        double jumlahS = 0.0;
        for(int i = 0; i < vektorS.length;i++){
            jumlahS += vektorS[i];
        }

        System.out.println("Jumlah Vektor S : " + jumlahS);

        result = new ArrayList<>();
        this.vektorV = new double[this.vektorS.length];
        for(int i = 0; i < vektorS.length;i++){
            this.vektorV[i] = this.vektorS[i] / jumlahS;
            result.add(new Result(alternativesValue.get(i), vektorV[i]));
            Log.d("KALK", Integer.toString(vektorS.length));
            System.out.println("Vektor V dari " + (i +1) + " adalah : " + this.vektorV[i]);
        }
//
//        double max = 0;
//        for(int i = 0; i < vektorV.length;i++){
//            if(max < vektorV[i]){
//                max = vektorV[i];
//            }
//        }
//
//        for(int i = 0; i < vektorV.length;i++){
//            if(max == vektorV[i]){
//                System.out.println("Alternatif yang terbaik adalah : " + (i+1) + " dengan properti : ");
//                printAlternatif(i);
//            }
//        }

    }

    private void inputValue() {
        for (int i = 0 ; i < listAlnternatif.size() ; i++){
            ArrayList<AlternatifKriteria> specAlternatifKriteria = new ArrayList<AlternatifKriteria>();
            for (int j = 0 ; j < listKriteria.size() ; j++){
                int value = 0;
                if (j == 0){
                    String[] item_modal = {"<1 Juta", "1 Juta - 3 Juta", ">3 Juta - 5 Juta", ">5 Juta - 7 Juta", ">7 Juta - 10 Juta", ">10 Juta"};

                    String value_modal = alternativesValue.get(i).modal;
                    for (int x = 0; x < item_modal.length; x++){
                        if (item_modal[x].equals(value_modal)){
                            value = x + 1;
                            break;
                        }
                    }
                } else if (j == 1){
                    value = alternativesValue.get(i).etalase;
                } else if (j == 2) {
                    String[] item_umur = {"<1 Minggu", "1 Minggu - 1 Bulan", ">1 Bulan - 6 Bulan", ">6 Bulan - 12 Bulan", ">12 Bulan - 24 Bulan", ">24 Bulan - 36 Bulan", ">36 Bulan"};

                    String value_umur = alternativesValue.get(i).umur_simpan;
                    for (int x = 0; x < item_umur.length; x++){
                        if (item_umur[x].equals(value_umur)){
                            value = x + 1;
                            break;
                        }
                    }
                } else if (j == 3) {
                    value = alternativesValue.get(i).laba;
                } else if (j == 4) {
                    value = alternativesValue.get(i).tren;
                }
                specAlternatifKriteria.add(new
                        AlternatifKriteria(listAlnternatif.get(i), listKriteria.get(j), value));
                Log.d("KALK", listAlnternatif.get(i).alternatif);
            }
            listAlternatifKriteria.add(specAlternatifKriteria);
        }
    }

    void printAlternatif(int i){
        System.out.print("Nama Alternatif : "+ this.listAlnternatif.get(i).alternatif);
        System.out.println(", Kode Alternatif : "+ this.listAlnternatif.get(i).kode);
        System.out.println();
    }



    void printAlternatif(){
        int i = 0;
        for(Alternatif a : listAlnternatif){
            i++;
            System.out.println("Alternatif "+i);
            System.out.print("Nama Alternatif : "+a.alternatif);
            System.out.println(", Kode Alternatif : "+a.kode);
            System.out.println();
        }
        System.out.println("Jumlah Alternatif : "+this.listAlnternatif.size());
    }

    void printKriteria(){
        int i = 0;
        for(Kriteria a : listKriteria){
            i++;
            System.out.println("Kriteria "+i);
            System.out.print("Kriteria : "+a.kriteria);
            System.out.print(", Bobot : "+a.bobot);
            System.out.print(", Benefit : "+a.benefit);
            System.out.println(", Kode: "+a.kode);
            System.out.println();
        }
        System.out.println("Jumlah Kriteria : "+this.listKriteria.size());
    }

    void calcNormBobot(){
        this.normBobot = new double[this.listKriteria.size()];
        int jlhBobot = 0;
        for(Kriteria a : listKriteria){
            jlhBobot  +=   a.bobot;
        }

        for(int i=0; i< listKriteria.size(); i++){
            this.normBobot[i] = (double) listKriteria.get(i).bobot / (double) jlhBobot;
        }
        System.out.println("Nomalisasi Bobot : "+ Arrays.toString(this.normBobot));
    }

    double calcPangkat(Kriteria kriteria,double normalisasibobot){
        if(kriteria.benefit){
            return normalisasibobot;
        }else{
            return normalisasibobot * -1.0;
        }
    }

    double calcVektorS(List<AlternatifKriteria> listnya){

        float hasil = 1f;

        for (int i = 0; i < listnya.size(); i++){
            Log.d("VekS", hasil + " " + i);
            Log.d("VekS", Integer.toString(listnya.get(i).value));
            hasil *= Math.pow(listnya.get(i).value, calcPangkat(listnya.get(i).kriteria, normBobot[i])) ;
        }
        Log.d("VEKTOR", Float.toString(hasil));
        return hasil;
    }
}
