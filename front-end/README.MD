## **Contextualização**

Na Hotmart as demandas internas são tão importantes quanto as externas. Aqui, cuidamos com muito carinho de todas as solicitações dos nosso troopers (do Brasil e de todo o mundo) através de uma plataforma chamada QuickOps. 

Este teste consiste na reprodução de uma das funcionalidades desta plataforma: A tela de solicitação de antecipações e reembolsos.

Para construí-la, você irá receber:

*   O [layout da tela](#layout) disponível no Zeplin
*   A [listagem de endpoints](#endpoints) para trazer os dados da tela


## **O desafio**

Sua tarefa consiste em montar e integrar com a API as seguintes estruturas da página:

1. Header

![Alt text](assets/images/header.jpeg?raw=true "Header")

*   Exibir dados da solicitação
    *   Nome do Solicitante
    *   Email do Solicitante
    *   Justificativa
    *   Finalidade
    *   Centro de Custo
    *   Projeto
    *   Data
    *   Quantidade de pessoas
    *   Flag - café da manhã
*   Permitir Atribuição de Analista

2. Timeline

![Alt text](assets/images/timeline.jpeg?raw=true "Timeline")

*   Criar uma timeline de itens (somente listagem, sem interação)
    *   Cada tipo de item deve apresentar um layout específico;
    *	Itens do tipo EVALUATION devem ser tratados como o item de Aprovação na imagem anterior;
    *	Itens do tipo ACCOUNTABILITY_SUBMITTED e ACCOUNTABILITY_CREATED devem ser tratados como o item de Solicitação na imagem anterior;
    *	Itens do tipo EXPENSE devem ser tratados como o item Hotel na imagem a seguir

![Alt text](assets/images/item.jpeg?raw=true "Item")

3. Sidebar

![Alt text](assets/images/sidebar.jpeg?raw=true "Sidebar")

*   Exibir o status da solicitação
*   Exibir a lista de moedas envolvidas (Todas as moedas que tem alguma despesa)

4. Ações
Implementar apenas a opção "Adicionar Despesa"

![Alt text](assets/images/barra-acoes.jpeg?raw=true "Barra Ações")

*   Adicionar despesa
![Alt text](assets/images/despesa.jpeg?raw=true "Adicionar Despesa")

Obs.: Após selecionar a moeda, dois novos campos deverão ser solicitados: "Valor total da nota/cupom" e "Valor a ser considerado"

Estrutura do body
```javascript
{
   expenseTypeCode: 'hotel-fee', // 'hotel-fee', 'food' or 'transport'
   currencyCode: 'BRL',  // 'BRL', 'USD' or 'MXN'
   amountSpent: 13.00, // Valor a ser considerado
   amountTotal: 71.00, // Valor total da nota/cupom
   notes: 'Descrição da despesa',
   resourceUrl: file, // Arquivo do comprovante (Tamanho máximo: 1MB)
   cardDate: 1585710000000 // Timestamp da data
}
```

Obs.: O [endpoint](#endpoints) associado irá retornar um json da despesa. Essa API é _databaseless_, portanto, não irá retornar em outro momento os dados enviados.


### **O que nós esperamos do seu desafio**

Queremos entender sua proficiência técnica, raciocínio analítico e apresentação de resultados. Para isso fique a vontade para incluir/alterar/remover o que sentir necessidade para atender melhor a demanda.

Mais especificamente na entrega do projeto gostaríamos de ver:


1. Documentação básica para executá-lo;
2. Código escrito da maneira mais legível possível. _Manutenção importa tanto quanto performance, muitas vezes um não precisa comprometer o outro_;
3. Uma boa estruturação do projeto;
4. Utilização das ferramentas adequadas;
5. Utilização correta da API.


### **O que nós ficaríamos felizes de ver em seu desafio**



1. Testes;
2. Análise automatizada de código (Ex.: ESLint);
3. Utilização das APIs nativas do browser;
4. Otimização do tamanho final do seu build.
5. Que todo o código esteja em inglês


### **O que nós não gostaríamos**



*   Descobrir que não foi você quem fez seu teste;
*   Não atendimento aos objetivos propostos;
*   Ver um código muito ilegível.

## 
**O que avaliaremos em seu desafio**

*   Alcance dos objetivos propostos;
*   Semântica, estrutura, legibilidade, manutenibilidade, escalabilidade do seu código e suas tomadas de decisões;
*   Organização do projeto;
*   Arquitetura da solução, utilização de design patterns adequados;
*   Performance, resiliência e tratamento de erros;


### **Pré-requisitos**

*   Utilização de Javascript "puro" ou os frameworks React ou Vue;


### **Endpoints**

* Status da API: `https://api-front-end-challenge.buildstaging.com/api/status`
* Dados do Header: `https://api-front-end-challenge.buildstaging.com/api/header`
* Dados da Timeline: `https://api-front-end-challenge.buildstaging.com/api/timeline`
* Dados da Sidebar: `https://api-front-end-challenge.buildstaging.com/api/sidebar`
* Adicionar Despesa: `https://api-front-end-challenge.buildstaging.com/api/expense/add`

### **Layout**

Crie sua conta grátis no [Zeplin](https://zeplin.io/) e solicite permissão para acessar as [telas](https://zpl.io/VKMGOg8) enviando seu usuário.
