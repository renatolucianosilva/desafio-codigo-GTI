package desafio_codigo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatarCpf {



    public String formatarCpf(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

    }
}
