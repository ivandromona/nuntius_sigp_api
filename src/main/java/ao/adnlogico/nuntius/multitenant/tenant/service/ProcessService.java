/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.service;

import ao.adnlogico.nuntius.multitenant.tenant.entity.Process;
import java.util.List;

/**
 *
 * @author domingos.fernando
 */
public interface ProcessService
{

    List<Process> getAllProcess();
}
