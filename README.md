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


# Funcionalidades Implementadas