package ao.adnlogico.sigp.nuntius.tenant.service;

import ao.adnlogico.sigp.nuntius.tenant.repository.ProductRepository;
import ao.adnlogico.sigp.nuntius.tenant.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Md. Amran Hossain
 */
@Service
public class ProductServiceImpl implements ProductService
{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct()
    {
        return productRepository.findAll();
    }
}
