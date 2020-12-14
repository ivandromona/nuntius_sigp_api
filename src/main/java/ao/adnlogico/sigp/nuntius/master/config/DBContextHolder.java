/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.sigp.nuntius.master.config;

/**
 *
 * @author domingos.fernando
 */
public class DBContextHolder
{

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setCurrentDb(String dbType)
    {
        contextHolder.set(dbType);
    }

    public static String getCurrentDb()
    {
        return contextHolder.get();
    }

    public static void clear()
    {
        contextHolder.remove();
    }
}
