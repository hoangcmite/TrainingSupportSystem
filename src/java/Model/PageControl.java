/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Legon
 */
public class PageControl {
     private int nrpp;
     private int cp;
     private int size;
    private int np;
    private int begin;
    private int end;
    private int[] arrNrpp={1,3,5,10,20,50,100,200,500,1000,2000};

    public int[] getArrNrpp() {
        return arrNrpp;
    }

    public void setArrNrpp(int[] arrNrpp) {
        this.arrNrpp = arrNrpp;
    }

    public PageControl() {
    }

    public PageControl(int nrpp, int cp, int size, int np, int begin, int end) {
        this.nrpp = nrpp;
        this.cp = cp;
        this.size = size;
        this.np = np;
        this.begin = begin;
        this.end = end;
    }

    public int getNrpp() {
        return nrpp;
    }

    public void setNrpp(int nrpp) {
        this.nrpp = nrpp;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNp() {
        return np;
    }

    public void setNp(int np) {
        this.np = np;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
       public PageControl(int nrpp, int cp, int size) {
        this.nrpp = nrpp;
        this.cp = cp;
        this.size = size;
     
    }
        public void calc1() {
        np = (size+nrpp-1)/nrpp;
        cp = cp<0?0:cp;
        cp = cp>np-1?np-1:cp;
        begin = cp*nrpp;
        end = begin+ nrpp -1;
        end = end>size-1?size-1:end;
        pageStart = cp-2;
        pageStart = pageStart<0?0:pageStart;
        pageEnd = cp+2;
        pageEnd = pageEnd>np-1?np-1:pageEnd;
    }
     public void calc() {
        np = (size+arrNrpp[nrpp]-1)/arrNrpp[nrpp];
        cp = cp<0?0:cp;
        cp = cp>np-1?np-1:cp;
        begin = cp*arrNrpp[nrpp];
        end = begin+ arrNrpp[nrpp] -1;
        end = end>size-1?size-1:end;
        pageStart = cp-2;
        pageStart = pageStart<0?0:pageStart;
        pageEnd = cp+2;
        pageEnd = pageEnd>np-1?np-1:pageEnd;
    }
        private int pageStart;
        private int pageEnd;

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
        
    
    
    
    
}
