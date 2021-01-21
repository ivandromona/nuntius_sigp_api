/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.service;

import ao.adnlogico.nuntius.multitenant.tenant.repository.ProcessRepository;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Process;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author domingos.fernando
 */
@Service
public class ProcessServiceImpl implements ProcessService

{

    @Autowired
    ProcessRepository repository;

    @Override
    public List<Process> getAllProcess()
    {
        return repository.findAll();
    }
}
