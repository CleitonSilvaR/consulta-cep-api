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


### Scripts

##### DATABASE
```sh
CREATE DATABASE consulta_cep
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
```

##### SCHEMA
```sh
    CREATE SCHEMA cep
    AUTHORIZATION postgres;
```

##### ENDERECO
```sh
    CREATE SEQUENCE cep.endereco_id_multi_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE cep.endereco_id_multi_seq
    OWNER TO postgres;

    CREATE TABLE cep.endereco
(
    id bigint NOT NULL,
    cep character varying(8) COLLATE pg_catalog."default",
    rua character varying(250) COLLATE pg_catalog."default",
    bairro character varying(250) COLLATE pg_catalog."default",
    cidade character varying(100) COLLATE pg_catalog."default",
    estado character varying(2) COLLATE pg_catalog."default",
    CONSTRAINT endereco_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE cep.endereco
    OWNER to postgres;

INSERT INTO cep.endereco( id, bairro, cep, cidade, estado, rua)
VALUES  (nextval('endereco_id_multi_seq'), 'Sibim', '86300000', 'Cornélio Procópio', 'PR', 'Antonio Milanez'),
		(nextval('endereco_id_multi_seq'), 'Bela Suiça', '86047250', 'Londrina', 'PR', 'Avenida Adhemar Pereira de Barros'),
		(nextval('endereco_id_multi_seq'), 'Jardim Lilian', '86015290', 'Londrina', 'PR', 'Rua José Leite de Carvalho'),
		(nextval('endereco_id_multi_seq'), 'Vila Santa Cruz', '14403471', 'Franca', 'SP', 'Rua Arnulpho de Lima');
```

##### USUARIO
```sh
CREATE SEQUENCE public.usuario_id_multi_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.usuario_id_multi_seq
    OWNER TO postgres;

CREATE TABLE public.usuario
(
    id bigint NOT NULL,
    login character varying(50) COLLATE pg_catalog."default",
    senha text COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to postgres;

INSERT INTO public.usuario(id, login, senha)
VALUES (nextval('usuario_id_multi_seq'), 'LOGIN_USUARIO', '$2a$10$VJKsIqdRy/b21v/hZIMR..VC3q5gz44s6lsoNV73o1EXgvYiapKWS');

```

### Documentação 
Obs: Utilizar ```Basic TE9HSU5fVVNVQVJJTzpTRU5IQQ== ``` em **Authorize** para conseguir utilizar o *swagger*.
Ou gerar um [Base64](https://www.base64encode.org/) do login de um usuario usuario cadastrado, no seguinte formato 'LOGIN:SENHA' antecipado por 'Basic '<br />
Ex : ```Basic ``` **+** ``` TE9HSU46U0VOSEE= ```

**Atenção**, exeto ```127.0.0.1:8080/swagger-ui.html``` url da documentação no swagger e ```127.0.0.1:8080/usuarios``` *POST* *endpoint* de cadastro de usuario necessita de autenticação para acessar os outros recursos disponíveis através do *header* *Authorization* também no formato exemplificado acima.


#### Swagger
```sh
127.0.0.1:8080/swagger-ui.html
```


