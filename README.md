# xy-inc

Solução

Foi utilizado um projeto com dois módulos separados, possibilitando um reuso
 > O módulo core guarda a lógica de negócio e a configuração com a base de dados
 > O módulo web contém dados do controlador e páginas do sistema
 
A base de dados escolhida foi o Postgresql, no projeto suas propriedades estão no arquivo hibernate.properties

Foi utilizado Spring para injeção de dependência, controle de transações e outras configurações

Os testes unitários estão no módulo core

Para facilitar o gerenciamento de dependências e geração de builds, utilizei o Maven

Fiz os deploys no servidor Tomcat

Na parte de front-end foi utilizada JSF com Primefaces
