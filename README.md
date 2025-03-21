# desafio-codigo-GTI

PROCESSO DE SELEÇÃO E RECRUTAMENTO - GTI(01/2025)

## Sistema de Gestão de Visita para Unidade Prisional

### Objetivo :

Desenvolver uma API REST utilizando Spring Boot 3 para gerenciar o fluxo de visitas em uma unidade prisional. O sistema deverá permitir o
agendamento, consulta e cancelamento de visitas, garantindo a segurança dos endpoints por meio de OAuth2 e disponibilizando a documentação
da API com Swagger.

### REQUISITOS FUNCIONAIS

Agendamento de Visitas:
Permitir que o usuário agende uma visita informando dados como:
1. Nome do visitante
2. Identificação do detento (pode ser um código ou nome)
3. Data e hora da visita.

Gerenciamento de Visitas:
- Implementar a estrutura das classes de modelo/entidades seguindo a especificação do modelo de dados na última página.
  Implementar os seguintes endpoints:
1. Criar visita: Agendar uma nova visita.
2. Atualizar visita: Modificar informações de uma visita agendada.
3. Consultar visitas: Listar todas as visitas agendadas, com a possibilidade de filtrar por data, detento ou status.
4. Cancelar visita: Permitir o cancelamento de visitas já agendadas.

Validações de Negócio:

1. Não permitir agendamentos conflitantes para o mesmo detento em um mesmo horário.
2. (Opcional) Validar se a visita está sendo agendada dentro de um horário permitido (por exemplo, apenas em dias e horários específicos).

REQUISITOS NÃO-FUNCIONAIS
1. Framework: Utilizar Spring Boot 3.
2. Banco de Dados: Usar um banco de dados em memória (H2) para persistência dos dados.
3. Segurança: Implementar OAuth2 para proteger os endpoints da API.
4. Documentação: Integrar Swagger (por exemplo, via springdoc-openapi) para que toda a API seja automaticamente documentada e possa
   ser testada interativamente.
5. Utilizar no desenvolvimento a versão do Java 17 ou superior

# Fluxo do Processo
1.  **Cadastro de Unidade Penal:**
    * Primeiramente, é necessário cadastrar a unidade penal.
    * Informações necessárias: Nome da unidade(ex: Penitenciário do Distrito Federal I); Descrição (ex: PDF I)
    * Json Exemplo:
      ```json
      {
      "nome": "Penitenciária do Distrito Federal I",
      "descricao": "PDF I"
      }
        ``` 
2. **Cadastro de Custodiado:**
   * Após cadastrar a unidade, é possível cadastrar os custodiados.
   * Informações necessárias: 
     * Nome Completo;
     * CPF;
     * Data de Nascimento;
     * Numero de Prontuário;
     * Vulgo, se possuir;
     * Descrição da Unidade Penal
   * Este passo vincula o custodiado à unidade penal.
   * Json Exemplo:

     ```json
     {
     "nome": "Paulo Henrique da Silva",
     "cpf": "015.036.114-68",
     "dataNascimento": "02/10/1979",
     "numeroProntuario": "123456",
     "vulgo": "string",
     "descricaoUnidadePenal": "PDF I"
     }
     ```
3. **Cadastro de Visitante:**
    * Em seguida, realizar Cadatro de Visitante.
    * Informações necessárias: 
      * Nome Completo;
      * CPF;
      * Data de Nascimento;
      * Senha OnLine;
    * Este passo cria o perfil do visitante no sistema.
   * Json Exemplo:   
   ```json
   {
   "nome": "Luiz Carlos da Silva Santos",
   "cpf": "058.156.036-74",
   "dataNascimento": "12/05/1986",
   "senhaOnline": "3525@85#ty9"
   } 
    ```
   
4. **Cadastro de Status de Visita:**
    * Define-se os possíveis status das visitas (agendada, realizada, cancelada, suspensa).
    * Este passo padroniza o acompanhamento das visitas.
    * REALIZADO COM SCRIPT SQL
   ```sql
   INSERT INTO STATUS (DESCRICAO) VALUES ('AGENDADO');
   INSERT INTO STATUS (DESCRICAO) VALUES ('CANCELADO'); 
   INSERT INTO STATUS (DESCRICAO) VALUES ('REALIZADO');
   INSERT INTO STATUS (DESCRICAO) VALUES ('SUSPENSO');
    ```
5. **Agendamento de Visita:**
   
* Para agendar uma visita, o visitante deve fornecer os seguintes dados:


  **Dados do Visitante:**

* Nome completo do visitante.
* CPF do visitante.

**Dados do Custodiado:**

* Nome completo do custodiado.
* Número de prontuário do custodiado.

**Detalhes da Visita:**

* Data da visita (formato: DD/MM/AAAA).
* Hora da visita (formato: HH:MM).

**O sistema realizará as seguintes verificações antes de confirmar o agendamento:**

**Validação de Cadastro:**

* Verifica se o visitante e o custodiado estão devidamente cadastrados no sistema.
* Caso um ou ambos não estejam cadastrados, o agendamento é negado.

**Disponibilidade de Data e Hora:**

* Verifica se a data e hora da visita estão dentro dos horários permitidos pela unidade penal.
   
**Data e Horarios Autorizados**

- 05/04/2025 08:00
- 05/04/2025 10:00
- 05/04/2025 12:00
- 10/04/2025 08:00
- 10/04/2025 10:00
- 10/04/2025 12:00
- 15/04/2025 08:00
- 15/04/2025 10:00
- 15/04/2025 12:00
- 20/04/2025 08:00
- 20/04/2025 10:00
- 20/04/2025 12:00

* Caso a data ou hora não estejam disponíveis, o agendamento é negado.

**Conflito de Agendamento:**

* Verifica se o custodiado já possui outra visita agendada para o mesmo dia e horário.
* Caso haja conflito, o agendamento é negado.

**Confirmação do Agendamento:**

* Após todas as verificações serem bem-sucedidas, o agendamento é confirmado.


# Instalação

## Clonar o Repositório:

Utilize o comando git clone para copiar o repositório para a sua máquina local:


git clone https://github.com/renatolucianosilva/desafio-codigo-GTI.git


* O projeto utiliza o gerenciador de dependências Maven.
* Todas as dependências necessárias estão listadas no arquivo pom.xml na raiz do projeto.
* Caso você não tenha o Maven instalado, será necessário realizar a instalação.
* Para baixar as dependencias, caso utilize alguma IDE como o Intellij Idea, ou Eclipse, normalmente ao importar o projeto, as dependencias são baixadas automaticamente.
* Caso deseje baixar via terminal, utilize o comando mvn install.

## Configuração do Banco de Dados H2:

* O projeto utiliza o banco de dados H2 para persistência de dados.
* O banco de dados está configurado para armazenar os dados na pasta dados na raiz do projeto.
* A URL de conexão é: jdbc:h2:file:./data/databases/desafio_codigo
* A configuração spring.jpa.hibernate.ddl-auto=update garante que o esquema do banco de dados seja atualizado automaticamente ao iniciar a aplicação, preservando os dados mesmo após reiniciar a aplicação.
* Use spring.sql.init.mode=always para garantir que seus scripts SQL sejam executados corretamente ao iniciar a aplicação.
* SCRIP SQL em (main/java/resourses/V1__CretateTables)
* Não é necessário realizar nenhuma configuração adicional para o banco de dados H2.

### Observações:

* Certifique-se de ter o Git e o Maven instalados em sua máquina antes de seguir estas instruções.
* Caso utilize alguma IDE, como o Intellij Idea, ou Eclipse, a maioria das configurações serão feitas automaticamente.
* Após clonar o repositório e baixar as dependências, você pode executar a aplicação Spring Boot normalmente.
* Caso tenha alguma dúvida, consulte a documentação do Spring Boot e do H2 Database.

