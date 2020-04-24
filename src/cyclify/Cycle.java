/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyclify;
import cyclify.*;
/**
 *
 * @author prana
 */
class Cycle {
    private String cycleid, make, colour;
    private int mop, gearsystem, rate;

    public Cycle (String cycleid, String make, String colour, int mop, int gearsystem, int rate )
    {
        this.cycleid=cycleid;
        this.make=make;
        this.colour=colour;
        this.mop=mop;
        this.gearsystem=gearsystem;
        this.rate=rate;
    }
    
    public String getcycleid()
    {
        return cycleid;
    }
    public String getmake()
    {
        return make;
    }
    public String getcolour()
    {
        return colour;
    }
    public int getmop()
    {
        return mop;
    }
    public int getgearsystem()
    {
        return gearsystem;
    }
    public int getrate()
    {
        return rate;
    }


    
    
    
}
