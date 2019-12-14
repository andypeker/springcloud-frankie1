import dto.RetDTO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Frankie Yang on 2019-07-19.
 */
@Component
public class InsuranceCompany4OthersHystrixFactory implements FallbackFactory<InsuranceCompany4OthersApi> {

    @Override
    public InsuranceCompany4OthersApi create(Throwable arg0) {
        return new InsuranceCompany4OthersApi() {
            @Override
            public RetDTO getAllInsuranceCompany() {

                RetDTO retDTO = new RetDTO();
                retDTO.setRetStatus(RetDTO.SUCCESS);
                retDTO.setRetData("服务不通");
                return retDTO;
            }

        };

    }

}