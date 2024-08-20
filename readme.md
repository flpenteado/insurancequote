# Insurance Quote Application

## Descrição

Este projeto é uma aplicação de cotação de seguros desenvolvida utilizando várias tecnologias modernas. A aplicação seguindo as abordagens de Clean Architecture e Domain-Driven Design (DDD).

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal utilizada para o desenvolvimento da aplicação.
- **Spring Boot**: Framework utilizado para criar aplicações standalone baseadas em Spring que são executáveis.
  - [`@SpringBootApplication`](src/main/java/io/acme/insurancequote/InsurancequoteApplication.java): Ponto de entrada principal da aplicação.
  - [`@Configuration`](src/main/java/io/acme/insurancequote/infrastructure/config/ConfigBeans.java): Configuração de beans.
  - [`@RestController`](src/main/java/io/acme/insurancequote/infrastructure/controllers/QuotationController.java): Controladores REST para gerenciar as requisições HTTP.
  - [`@Service`](src/main/java/io/acme/insurancequote/infrastructure/gateway/CatalogGatewayImpl.java): Serviços para lógica de negócios.
  - [`@RabbitListener`](src/main/java/io/acme/insurancequote/infrastructure/messaging/QuotationMessageAdapter.java): Integração com RabbitMQ para processamento de mensagens.
- **Gradle**: Ferramenta de automação de build utilizada para compilar, testar e empacotar a aplicação.
  - [`gradlew`](gradlew) e [`gradlew.bat`](gradlew.bat): Scripts para executar o Gradle de forma independente da instalação local.
- **Docker**: Utilizado para containerização da aplicação.
  - [`docker-compose.yml`](docker-compose.yml): Arquivo de configuração para orquestração de containers Docker.

## Boas Práticas Utilizadas

- **Injeção de Dependências**: Utilização de anotações como `@Autowired`, `@Service`, `@Repository` e `@Component` para gerenciar dependências e promover a inversão de controle.
- **Configuração Centralizada**: Utilização de classes de configuração como [`RabbitConfig`](src/main/java/io/acme/insurancequote/infrastructure/config/RabbitConfig.java) para centralizar a configuração de componentes.
- **Tratamento de Exceções**: Implementação de [`@ControllerAdvice`](src/main/java/io/acme/insurancequote/infrastructure/controllers/QuotationControllerAdvice.java) para tratamento global de exceções.
- **Testes Automatizados**: Estrutura de diretórios para testes unitários e de integração, garantindo a qualidade do código.
- **Controle de Versão**: Utilização de `.gitignore` para excluir arquivos e diretórios desnecessários do controle de versão.


## Clean Architecture e Domain-Driven Design (DDD)

### Clean Architecture

A Clean Architecture é uma abordagem de design de software que promove a separação de responsabilidades e a independência de frameworks e tecnologias. A estrutura do projeto é organizada em camadas, cada uma com uma responsabilidade específica:

- **Camada de Domínio**: Contém as regras de negócio e entidades do domínio.
  - [`Quotation`](src/main/java/io/acme/insurancequote/domain/models/Quotation.java)
  - [`Product`](src/main/java/io/acme/insurancequote/domain/models/Product.java)
  - [`Offer`](src/main/java/io/acme/insurancequote/domain/models/Offer.java)
  - [`QuotationValidator`](src/main/java/io/acme/insurancequote/domain/service/QuotationValidator.java)
- **Camada de Aplicação**: Contém os casos de uso que orquestram a lógica de negócio.
  - [`CreateQuotationUseCase`](src/main/java/io/acme/insurancequote/application/usecase/CreateQuotationUseCase.java)
  - [`GetQuotationUseCase`](src/main/java/io/acme/insurancequote/application/usecase/GetQuotationUseCase.java)
- **Camada de Interface**: Contém os adaptadores e interfaces para comunicação com o mundo externo (APIs, bancos de dados, etc.).
  - [`QuotationController`](src/main/java/io/acme/insurancequote/infrastructure/controllers/QuotationController.java)
  - [`JpaQuotationRepository`](src/main/java/io/acme/insurancequote/infrastructure/repository/JpaQuotationRepository.java)
  - [`QuotationMessageAdapter`](src/main/java/io/acme/insurancequote/infrastructure/messaging/QuotationMessageAdapter.java)

### Domain-Driven Design (DDD)

O DDD é uma abordagem de design de software que foca na modelagem do domínio de negócio e na colaboração com especialistas do domínio. No projeto, utilizamos os seguintes conceitos de DDD:

- **Entidades**: Objetos que possuem identidade e ciclo de vida.
  - [`Quotation`](src/main/java/io/acme/insurancequote/domain/models/Quotation.java)
  - [`Product`](src/main/java/io/acme/insurancequote/domain/models/Product.java)
  - [`Offer`](src/main/java/io/acme/insurancequote/domain/models/Offer.java)
- **Repositórios**: Interfaces para acesso a dados persistentes.
  - [`QuotationRepository`](src/main/java/io/acme/insurancequote/application/repository/QuotationRepository.java)
- **Serviços de Domínio**: Contêm lógica de negócio que não se encaixa em uma única entidade.
  - [`QuotationValidator`](src/main/java/io/acme/insurancequote/domain/service/QuotationValidator.java)
- **Casos de Uso**: Orquestram a execução de operações de negócio.
  - [`CreateQuotationUseCase`](src/main/java/io/acme/insurancequote/application/usecase/CreateQuotationUseCase.java)
  - [`GetQuotationUseCase`](src/main/java/io/acme/insurancequote/application/usecase/GetQuotationUseCase.java)

## Estrutura do Projeto

A estrutura do projeto segue uma organização padrão para aplicações Spring Boot, com a separação de camadas conforme a Clean Architecture:



## Como Executar

1. Certifique-se de ter o Java e o Docker instalados em sua máquina.
2. Configure a variável de ambiente [`JAVA_HOME`](command:_github.copilot.openSymbolFromReferences?%5B%22JAVA_HOME%22%2C%5B%7B%22uri%22%3A%7B%22%24mid%22%3A1%2C%22fsPath%22%3A%22%2FUsers%2Fpenteado%2FDownloads%2Fitau%2Finsurancequote%2Freadme.md%22%2C%22external%22%3A%22file%3A%2F%2F%2FUsers%2Fpenteado%2FDownloads%2Fitau%2Finsurancequote%2Freadme.md%22%2C%22path%22%3A%22%2FUsers%2Fpenteado%2FDownloads%2Fitau%2Finsurancequote%2Freadme.md%22%2C%22scheme%22%3A%22file%22%7D%2C%22pos%22%3A%7B%22line%22%3A73%2C%22character%22%3A37%7D%7D%5D%5D "Go to definition") para apontar para a instalação do Java.
3. Execute o Gradle Wrapper para compilar e executar a aplicação:
   ```sh
   ./gradlew bootRun
   ```

4. Utilize o Docker Compose para iniciar os serviços necessários:
   ```sh
   docker-compose up
   ```

## URLs Importantes

- **RabbitMQ Management Portal**: Acesse o portal de gerenciamento do RabbitMQ em [http://localhost:15672](http://localhost:15672). Use o usuário e senha configurados no [`docker-compose.yml`](command:_github.copilot.openRelativePath?%5B%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2FUsers%2Fpenteado%2FDownloads%2Fitau%2Finsurancequote%2Fdocker-compose.yml%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%5D "/Users/penteado/Downloads/itau/insurancequote/docker-compose.yml").
- **Swagger UI**: Acesse a documentação da API gerada pelo Swagger em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html). 

## Pontos de Melhoria
### Validação de Request:
**Descrição**: Implementar validações robustas para as requisições recebidas pela API. Isso pode ser feito utilizando anotações de validação do Spring, como @Valid e @NotNull, para garantir que os dados recebidos estejam no formato correto e contenham todas as informações necessárias.

**Benefício**: Melhora a segurança e a integridade dos dados processados pela aplicação, reduzindo a possibilidade de erros e ataques maliciosos.

## Dados Sensíveis em Variáveis de Ambiente:

**Descrição**: Armazenar dados sensíveis, como credenciais de banco de dados e chaves de API, em variáveis de ambiente em vez de hardcoded no código ou em arquivos de configuração. Utilizar ferramentas como dotenv ou serviços de gerenciamento de segredos para gerenciar essas variáveis de forma segura.

**Benefício**: Aumenta a segurança da aplicação, evitando a exposição de informações sensíveis e facilitando a gestão de configurações em diferentes ambientes (desenvolvimento, teste, produção).

## Maior Cobertura dos Testes:

**Descrição**: Aumentar a cobertura dos testes unitários e de integração para garantir que todas as partes críticas da aplicação sejam testadas. Utilizar ferramentas de cobertura de código, como JaCoCo, para identificar áreas não cobertas e adicionar testes conforme necessário.

**Benefício**: Reduz a probabilidade de bugs e regressões, garantindo que a aplicação funcione conforme esperado em diferentes cenários.

## Documentação e Comentários:

**Descrição**: Melhorar a documentação do código e adicionar comentários explicativos onde necessário. Garantir que a documentação da API (Swagger) esteja sempre atualizada e completa.

**Benefício**: Facilita a manutenção e a colaboração, permitindo que novos desenvolvedores entendam rapidamente o funcionamento da aplicação.

## Monitoramento e Logging:

**Descrição**: Implementar soluções de monitoramento e logging para acompanhar o desempenho da aplicação e detectar problemas em tempo real. Utilizar ferramentas como ELK Stack (Elasticsearch, Logstash, Kibana) ou Prometheus e Grafana.

**Benefício**: Permite a identificação e resolução rápida de problemas, melhorando a confiabilidade e a disponibilidade da aplicação.

## Automação de Deploy:

**Descrição**: Configurar pipelines de CI/CD (Continuous Integration/Continuous Deployment) para automatizar o processo de build, teste e deploy da aplicação. Utilizar ferramentas como Jenkins, GitHub Actions ou GitLab CI.

**Benefício**: Acelera o ciclo de desenvolvimento, reduzindo o tempo de entrega de novas funcionalidades e correções de bugs.
Implementar essas melhorias pode ajudar a aumentar a qualidade, segurança e manutenibilidade do projeto.