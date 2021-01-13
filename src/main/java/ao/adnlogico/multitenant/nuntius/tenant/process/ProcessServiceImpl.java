/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.multitenant.nuntius.tenant.process;

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
