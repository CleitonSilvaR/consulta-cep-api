# consulta-cep-api

Para o desenvolvimento do projeto foi utilizado a linguagem **java**, juntamente com o framework *Spring*  pra ajudar no processo de desenvolvimento através de produtividade, padronização e a pouca necessidade de configuração para desenvolver um um projeto executar em produção de forma rápida e fácil. 

Além de outra justificativa particular, dado que meu *background* é basicamente todo em java, mas não tinha um contato mas aprofundado com o framework, eu me propus através da criação dessa *API* a desenvolver utilizando *Spring* e conhecer o framework como um todo além dos seus módulos.

A estratégia utilizada para a criação dessa aplicação foi o foco em desenvolver uma *api* simples, ágil e eficaz na consulta de endereço através de um cep. 
Com isso foi utilizado o *framework* *Spring* e implementado com padrão MVC, também é utilizado o padrão de projeto *Dependency Injection* onde é feito o gerenciamento de dependências das classes através dos construtores e delegamos ao Spring que injete de forma automática os *beans*, com isso outro padrão, o *Inversion of Control* (IoC) onde outra vez delegamos ao Spring além do gerenciamento dos *beans* para injeção de dependências a criação e gerencia de conexões com o banco e dados e controle das transações, e também o padrão arquitetural *MVC*:

Arquitetura da Aplicação

Controllers: Camada de *resources* onde ocorreu o desenvolvimento dos *controllers Rest.*

Services: Camada de serviço onde foi implementado a regra de negócio e tratamentos necessários para a consulta.

Repository: Camada onde tem acesso ao nosso modelo de dados e a base de dados.

Um pequeno adendo sobre as especificações para o desenvolvimento da *api* é seguinte, é especificado que *"Os serviços devem receber e responder JSON"*, com isso foi desenvolvido um *endpoint* do tipo *POST* para receber a consulta de cep, no entanto ao meu ver não teria a necessidade de ser *POST*, uma requisição *GET* já resolveria e supriria o problema, eu resolvi desenvolver os dois *endpoints*, tanto o *GET* quanto o *POST* como a especificação, ambos com mesmos tratamentos e validações.
