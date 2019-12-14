import dto.RetDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Frankie Yang on 2019-07-19.
 */
@FeignClient(name = "springcloud-provider-multiple", fallbackFactory = InsuranceCompany4OthersHystrixFactory.class)
@RequestMapping("/basebusiness/insurancecompany/4others")
public interface InsuranceCompany4OthersApi {

    @RequestMapping("/getAllInsuranceCompany")
    public RetDTO getAllInsuranceCompany();

}
